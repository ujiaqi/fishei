package cn.fishei.service;

import cn.fishei.bean.User;

public interface UserService {

    //功能介绍如DAO层相应接口
    boolean register(User user);

    boolean activeAccount(String uuid);

    User login(User user);

    void updateInfo(String username, String password);
}
