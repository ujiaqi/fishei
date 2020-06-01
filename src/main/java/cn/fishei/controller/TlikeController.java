package cn.fishei.controller;

import cn.fishei.bean.BackInfo;
import cn.fishei.bean.Ted;
import cn.fishei.bean.User;
import cn.fishei.service.TlikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/tlike")
/**
 * 有关收藏的控制层
 */
public class TlikeController extends BaseController {
    @Autowired
    private TlikeService tlikeService;

    /**
     * 用户是否收藏
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/ifLike")
    public void ifLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前收藏的ted的ID
        String tid = request.getParameter("tid");

        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        int uid = 0;
        if(user == null){
            uid = 0; //未登录
        }else {
            uid = user.getUid();  //用户登录了
        }
        //BackInfo info = new BackInfo();
        //是否收藏
        Boolean flag = tlikeService.ifLike(uid, tid);
        writeValue(flag, response);
    }

    /**
     * 添加收藏（首页）
     * @param request
     * @param response
     */
    @RequestMapping("/addLike")
    public void addLike(HttpServletRequest request, HttpServletResponse response){
        String tid = request.getParameter("tid");
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        tlikeService.addLike(uid, tid);
    }

    /**
     * 这是用户管理后台添加收藏的方法，添加后重定向展示所有收藏
      * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addLike2")
    public String addLike2(HttpServletRequest request, HttpServletResponse response){
        String tid = request.getParameter("tid");
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        tlikeService.addLike(uid, tid);
        return "redirect:/tlike/allLike";
    }

    /**
     * 取消收藏（首页）
     * @param request
     * @param response
     */
    @RequestMapping("/removeLike")
    public void removeLike(HttpServletRequest request, HttpServletResponse response){
        String tid = request.getParameter("tid");
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        tlikeService.removeLike(uid, tid);
    }

    //这是用户管理后台取消收藏的方法，添加后重定向展示所有收藏
    @RequestMapping("/removeLike2")
    public String removeLike2(HttpServletRequest request, HttpServletResponse response){
        String tid = request.getParameter("tid");
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        tlikeService.removeLike(uid, tid);
        return "redirect:/tlike/allLike";
    }

    /**
     * 前端展示数据
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getDetail")
    public void getDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取所有的TED表的元组数
        int count = tlikeService.queryAll();
        //生成两个不同的随机数
        Random random = new Random();
        int one = random.nextInt(count)+1;
        int two = random.nextInt(count)+1;
        while(one == two){
            one = random.nextInt(count)+1;
            two = random.nextInt(count)+1;
        }
        //查询这两个随机数的TED详细数据
        List<Ted> teds = tlikeService.queryDetail(one, two);
        //返回JSON数据
        writeValue(teds,response);
    }

    /**
     * 展示所有用户的收藏数据
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/allLike")
    public String allLike(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<Ted> teds = tlikeService.allLike(user);
        model.addAttribute("list", teds);
        return "control";
    }
}
