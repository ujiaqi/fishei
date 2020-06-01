package cn.fishei.controller;

import cn.fishei.bean.BackInfo;
import cn.fishei.bean.Ted;
import cn.fishei.bean.User;
import cn.fishei.service.TedService;
import cn.fishei.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private TedService tedService;

    /**
     * 注册
     * @param user
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/register")
    public void register(User user, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取前端的验证码
        String checkCode = request.getParameter("checkCode");
        //获取存入SESSION的验证码
        HttpSession session = request.getSession();
        String createCode = (String) session.getAttribute("createCode");
        //移除验证码的Session使得验证码失效
        session.removeAttribute("createCode");
        //验证码是否正确
        if (createCode == null || ! createCode.equalsIgnoreCase(checkCode)){
            BackInfo info = new BackInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            String json = writeValueAsString(info, response);

            //将json写入到客户端
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);

            return;
        }
        //前端传来注册的数据封装
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //注册用户
        boolean flag = userService.register(user);
        BackInfo info = new BackInfo();

        if(flag){
            //注册成功
            info.setFlag(true);
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        String json = writeValueAsString(info, response);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    /**
     * 激活用户
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/activeAccount")
    public void activeAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = request.getParameter("uuid");
        //判断uuid是否正确
        if (uuid != null){
            boolean flag = userService.activeAccount(uuid);
            String msg = null;
            if (flag){
                msg = "激活成功，请<a href='http://localhost:8080/login.html'>登录</a>!";
            }else{
                msg = "激活失败！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    /**
     * 登录操作
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        String createCode = (String) session.getAttribute("createCode");
        session.removeAttribute("createCode");

        if (createCode == null || ! createCode.equalsIgnoreCase(checkCode)){
            BackInfo info = new BackInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            String json = writeValueAsString(info, response);

            //将json写入到客户端
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);

            return;
        }

        Map<String, String> map= request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        User u = userService.login(user);

        BackInfo info = new BackInfo();

        if (u == null){
            //用户不存在或者账号密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或者密码错误！");
        }

        //是否激活
        if(u != null && !"Y".equals(u.getStatus())){
            //未激活
            info.setFlag(false);
            info.setErrorMsg("账户未激活！请前往邮箱激活！");
        }

        if(u != null && "Y".equals(u.getStatus())){
            request.getSession().setAttribute("user", u);
            info.setErrorMsg("登录成功！");
            info.setFlag(true);
        }
        writeValue(info, response);
    }

    /**
     * 是否有用户登录
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/ifLogin")
    public void ifLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object user = request.getSession().getAttribute("user");
        boolean flag = false;
        if (user != null){
            flag = true;
        }
        writeValue(flag, response);
    }


    /**
     * Control页进入时是否登录用户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/control")
    public String control(HttpServletRequest request, HttpServletResponse response){
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        boolean flag = false;
        if(user == null){

            //writeValue(flag, response);
            return "redirect:/login.html";

        }else {

            return "redirect:/tlike/allLike";
        }
    }

    /**
     * 更新个人信息跳转页面
     * @return
     */
    @RequestMapping("/toUpdateInfo")
    public String toUpdateInfo(){
        return "updateInfo";
    }

    /**
     * 更新个人信息
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/updateInfo")
    public String updateInfo(String username, String password, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        String username_now = user.getUsername();
        userService.updateInfo(username_now, password);
        return "redirect:/tlike/allLike";
    }

    /**
     * 更据title查询相关Ted信息
     * @param title
     * @param model
     * @return
     */
    @RequestMapping("/queryTeds")
    public String queryTeds(String title,Model model){
        List<Ted> teds = tedService.queryTeds(title);

        if (teds.isEmpty()){
            model.addAttribute("msg","查询结果为空！");

        }else{
            model.addAttribute("msg", "查询成功！");
            model.addAttribute("list_query",teds);
        }
        return "control";
    }

    /**
     * 跳转登录主页
     * @return
     */
    @RequestMapping("/goLogin")
    public String goLogin(){
        return "redirect:/tlike/allLike";
    }

    /**
     * 退出用户
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        boolean flag = false;
        if (user!=null){
            //销毁session
            request.getSession().invalidate();
            writeValue(!flag, response);
        }else{
            writeValue(flag, response);
        }


    }
}


