package com.example.demo.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.example.demo.bean.User;
import com.example.demo.service.BokesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UserService;
import com.example.demo.utils.M;
import com.example.demo.utils.PassToken;
import com.example.demo.utils.UserLoginToken;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@Controller
@RequestMapping("/admin/user/")
/**
 * 后台用户相关的请求
 */
public class UserController {
	
	@Autowired
    UserService uService;


	
	@PassToken
	@ResponseBody
	@RequestMapping(path={"/login"},method = {RequestMethod.POST})
	public M login(@RequestBody HashMap<String, String> data) {
		System.out.println(data);
		M map =  uService.login("z123456", "1234567");
		System.out.println(map);
		return map;
		
	}
	
	@PassToken
	@ResponseBody
	@RequestMapping(path={"/logout"},method = {RequestMethod.POST})
	public M logout() {
		Map<String, Object> map =  new HashMap<>();
		return M.ok(map);
		
	}
	@UserLoginToken
	@ResponseBody
	@RequestMapping(path={"/edit"},method = {RequestMethod.POST})
	public M edit(@RequestBody User user){
		System.out.println(user);
		return uService.editInfo(user);
	}




	@UserLoginToken
	@ResponseBody
	@RequestMapping(path={"/info"})
	public M info(String token) {
		return uService.findUserById("z123456");
	}
}
