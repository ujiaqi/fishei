package cn.fishei.service;

import cn.fishei.bean.Ted;
import cn.fishei.dao.TedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tedService")
public class TedServiceImpl implements TedService{

    @Autowired
    private TedMapper tedMapper;

    @Override
    public List<Ted> queryTeds(String title) {
        return tedMapper.queryTeds(title);
    }
}
