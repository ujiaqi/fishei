package cn.fishei.dao;

import cn.fishei.bean.Ted;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TedMapper {
    /**
     * 搜索， 模糊查询
     * @param title
     * @return
     */
    List<Ted> queryTeds(@Param("title") String title);
}
