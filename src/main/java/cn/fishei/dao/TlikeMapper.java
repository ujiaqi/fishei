package cn.fishei.dao;

import cn.fishei.bean.Ted;
import cn.fishei.bean.Tlike;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;

public interface TlikeMapper {
    //用户是否收藏
    Tlike ifLike(@Param("uid") int uid, @Param("tid") int tid);
    //添加收藏
    void addLike(Tlike tlike);
    //取消收藏
    void removeLike(@Param("uid")int uid, @Param("tid")int tid);
    //查询所有TED数据
    int queryAll();
    //查询TED详细内容
    List<Ted> queryDetail(@Param("one") int one, @Param("two") int two);
    //查询所有收藏
    List<Ted> allLike(@Param("uid") int uid);


}
