package cn.fishei.service;

import cn.fishei.bean.Ted;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TedService {
    /**
     * 搜索， 模糊查询
     * @param title
     * @return
     */
    List<Ted> queryTeds(String title);
}
