package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.M;

@Service
public class UserService {
	Random random = new Random();
	@Autowired
    UserMapper uDAO;
 
    public User SelById(String id) {
    	return uDAO.SelById(id);
    }
    
    public M findUserById(String id) {
    	Map<String,Object> data = new HashMap<String,Object>();
    	Map<String,Object> map = new HashMap<String,Object>();
    	User user = uDAO.SelById(id);
    	if(user==null) {
    		return M.error("NoUser");
    	}
    	map.put("id", user.getId());
    	map.put("roles", user.getRoles());
    	map.put("routes", user.getRoutes());
    	map.put("name", user.getName());
    	map.put("index_one", user.getIndex_one());
    	map.put("index_two", user.getIndex_two());
    	map.put("index_three", user.getIndex_three());
    	map.put("boke_name",user.getBoke_name());
    	map.put("avatar", user.getAvatar());
    	data.put("data", map);
    	
    	return M.ok(data);
    }
    
    public M login(String id,String pwd){
    	Map<String,Object> data = new HashMap<String,Object>();
    	M res = new M();
    	User user = uDAO.SelById(id);
    	if(!pwd.equals(user.getPwd())) {
    		return M.error(300, "密码错误!");
    	}
    	String token = getToken(user);
    	data.put("Token", token);
    	res.put("data", data);
    	return M.ok(res);
    }
    
    public M editInfo(User user){
    	try{
			uDAO.Edit(user);
		}catch (Exception e){
    		System.out.println(e);
    		return M.error();
		}

    	return M.ok();
	}

    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPwd()));
        return token;
    }


}
