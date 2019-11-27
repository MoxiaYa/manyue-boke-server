package com.example.demo.service;

import com.example.demo.bean.Discuss;
import com.example.demo.mapper.DiscussMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiscussService {

    @Autowired
    DiscussMapper dD;

    /**
     * 查找该博客的评论
     * @param id
     * @return
     */
    public M selByBoke(Integer id){
        List<Discuss> discusses = dD.selByBoke(id);
        Map<String,Object> map = new HashMap<>();
        map.put("discusses",discusses);
        Map<String,Object> data = new HashMap<>();
        data.put("data",map);
        return M.ok(data);
    }

    /**
     * 发表评论
     * @param dis
     * @return
     */
    public M addDiscuss(Discuss dis){
        dD.addDiscuss(dis);
        return M.ok();
    }

    public M delDiscuss(Integer id){
        dD.delDiscuss(id);
        return M.ok();
    }
}
