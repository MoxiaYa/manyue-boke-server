package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.bean.Account;
import com.example.demo.bean.User;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.utils.M;

@Service
public class AccountService {

	@Autowired
    AccountMapper aDAO;
	
	 public Account SelById(String id) {
	    return aDAO.SelById(id);
	 }
	 
	 public M register(Account account) {
		 try {
			 int i = aDAO.AddAccount(account);
			 if(i == 1) {
				 return M.ok(); 
			 }else {
				 System.out.println("出现未知注册异常");
				 return M.error(500, "未知异常");
			 } 
		 }catch(Exception e) {
			 return M.error(400,"账号已存在");
		 }
		 
	 }
	 
	 /**
	  * 用户登录接口
	  * @param account
	  * @return
	  * 2019.11.12
	  * 
	  */
	 public M login(Account account) {
		 System.out.println("收到登录请求--"+new Date().toString());
		 String id = account.getId();
		 Account ok = aDAO.SelById(id);
		 if(ok==null) {
			 System.out.println("数据库没查到此账户");
			 return M.error(500, "no account");
		 }else {
			 if(ok.getPwd().equals(account.getPwd())) {
				 Map<String,Object> data = new HashMap<String,Object>();
				 Map<String,Object> m = new HashMap<String,Object>();
				 
				 System.out.println("密码正确，登录成功");
				 String token = getToken(account);
				 data.put("account", ok);
				 data.put("token", token);
				 m.put("data", data);
				 
				 return M.ok(m);
			 }else {
				 System.out.println("密码错误，登陆失败"+ok.getPwd()+"---"+account.getPwd());
				 return M.error(400,"pwd error");
			 }
		 }
	 }

	/**
	 * 用户修改资料接口
	 * @param account
	 * @return
	 */
	 public M update(Account account){
	 	Integer a = aDAO.update(account);
	 	if(a == 1){
	 		return M.ok();
		}else{
	 		return M.error(400,"未知异常");
		}
	 }

	 public String getToken(Account user) {
	        String token="";
	        token= JWT.create().withAudience(user.getId().toString())
	                .sign(Algorithm.HMAC256(user.getPwd()));
	        return token;
	    }
}
