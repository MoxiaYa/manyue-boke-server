package com.example.demo.main;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.Account;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;
import com.example.demo.utils.M;
import com.example.demo.utils.PassToken;

@CrossOrigin
@Controller
@RequestMapping("/account/")
public class AccountController {
	@Autowired
    AccountService as;
	
	@PassToken
	@ResponseBody
	@RequestMapping(path={"/login"},method = {RequestMethod.POST})
	public M login(@RequestBody Account account) {
		System.out.println(account);
		M map =  as.login(account);
		return map;
		
	}
}
