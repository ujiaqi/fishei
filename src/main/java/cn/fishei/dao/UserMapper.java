package cn.fishei.dao;

import cn.fishei.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    //查找用户
    User findUsername(String username);
    //注册，保存用户
    void saveAccount(User user);
    //查询uuid是否存在
    User findUuid(String uuid);
    //更新状态
    void updateStatus(User user);
    //校验账号密码
    User login(@Param("username") String username, @Param("password") String password);
    //更新用户数据
    void updateInfo(@Param("username") String username, @Param("password") String password);
}
