package cn.fishei.service;


import cn.fishei.bean.Ted;
import cn.fishei.bean.Tlike;
import cn.fishei.bean.User;
import cn.fishei.dao.TlikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("tlikeService")
public class TlikeServiceImpl implements TlikeService{

    @Autowired
    private TlikeMapper tlikeMapper;

    @Override
    public Boolean ifLike(int uid, String tid) {
        //查询是否收藏
        Tlike tlike = tlikeMapper.ifLike(uid, Integer.parseInt(tid));
        return tlike != null;
    }

    @Override
    public void addLike(int uid, String tid) {
        //添加收藏
        Tlike tlike = new Tlike();
        tlike.setUid(uid);
        tlike.setTid(Integer.parseInt(tid));
        //生成日期
        tlike.setDate(new Date());
        tlikeMapper.addLike(tlike);
    }

    @Override
    public void removeLike(int uid, String tid) {
        tlikeMapper.removeLike(uid, Integer.parseInt(tid));
    }

    @Override
    public int queryAll() {
        return tlikeMapper.queryAll();
    }

    @Override
    public List<Ted> queryDetail(int one, int two) {
        return tlikeMapper.queryDetail(one, two);
    }

    @Override
    public List<Ted> allLike(User user) {
        return tlikeMapper.allLike(user.getUid());
    }


}
