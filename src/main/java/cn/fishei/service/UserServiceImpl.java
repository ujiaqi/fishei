package cn.fishei.service;

import cn.fishei.bean.User;
import cn.fishei.dao.UserMapper;
import cn.fishei.util.MailUtil;
import cn.fishei.util.Md5Util;
import cn.fishei.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        User username = userMapper.findUsername(user.getUsername());
        if (username != null){
            //用户存在
            return false;
        }

        //设置URL激活后缀
        user.setUuid(UuidUtil.getUuid());
        try {
            //对密码进行Md5加密
            user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置status激活状态默认
        user.setStatus("N");
        userMapper.saveAccount(user);

        //发送激活邮件
        String content = "<a href='http://localhost:8080/user/activeAccount?uuid="+user.getUuid()+"'>点击此处立即激活账户</a>";
        MailUtil.sendMail(user.getEmail(),content,"欢迎注册【Fishei】，这是封激活邮件");
        return true;
    }

    /**
     * 激活用户
     * @param uuid
     * @return
     */
    @Override
    public boolean activeAccount(String uuid) {

        User user = userMapper.findUuid(uuid);

        if(user != null){
            userMapper.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User login(User user) {
        try {
            //前端传来的密码进行Md5处理后校验
            return userMapper.login(user.getUsername(), Md5Util.encodeByMd5(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateInfo(String username, String password) {
        try {
            String pwd = Md5Util.encodeByMd5(password);
            userMapper.updateInfo(username, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
