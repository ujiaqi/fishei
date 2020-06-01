package cn.fishei.service;

import cn.fishei.bean.Ted;
import cn.fishei.bean.Tlike;
import cn.fishei.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TlikeService {
    //功能介绍见dao层接口
    Boolean ifLike(int uid, String tid);

    void addLike(int uid, String tid);

    void removeLike(int uid, String tid);

    int queryAll();

    List<Ted> queryDetail(int one, int two);

    List<Ted> allLike(User user);

}
