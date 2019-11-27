package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.utils.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Boke;
import com.example.demo.mapper.BokesMapper;

@Service
public class BokesService {
	@Autowired
    BokesMapper bm;
    public List<Boke> SelEnabled(){
        return bm.SelEnabled();
    }

    public M SelAll(){
        Map<String,Object> map = new HashMap<>();
        map.put("data",bm.SelAll());
        return M.ok(map);

    }

    /**
     * 增
     * @param boke
     * @return
     */
    public M add(Boke boke) {
    	bm.add(boke);
        return M.ok();
    }

    /**
     * 批量删
     * @return
     */
    public M delMore(List<Integer> ids){
        System.out.println(ids);
        try{
            bm.delMore(ids);
        }catch (Exception e){
            System.out.println(e);
        }
        return M.ok();
    }

    /**
     * 单个删除
     * @param id
     * @return
     */
    public M del(Integer id){
        bm.del(id);
        return M.ok();
    }

    /**
     * 修改是否启用
     * @param id
     * @param is_enabled
     * @return
     */
    public M editEnabled(Integer id,Integer is_enabled) {
        bm.editEnabled(id,is_enabled);
        return M.ok();
    }

    /**
     * 改
     * @param boke
     * @return
     */
    public M update(Boke boke){
       bm.update(boke);
       return M.ok();
    }

    /**
     * 根据标题查找
     * @param title
     * @return
     */
    public M selByTitle(String title){
        List<Boke> bokes = bm.selByTitle(title);
        Map<String,Object> map = new HashMap<>();
        map.put("bokes",bokes);
        Map<String,Object> data = new HashMap<>();
        data.put("data",map);
        return M.ok(data);

    }

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    public M selById(Integer id){
        Boke boke = bm.selById(id);
        if(boke == null){
            return M.error(400,"博客已删除");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("boke",boke);
        Map<String,Object> data = new HashMap<>();
        data.put("data",map);
        return M.ok(data);
    }

}
