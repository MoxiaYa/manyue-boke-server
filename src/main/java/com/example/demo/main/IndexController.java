package com.example.demo.main;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.bean.Discuss;
import com.example.demo.service.DiscussService;
import com.example.demo.utils.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.Account;
import com.example.demo.bean.Boke;
import com.example.demo.service.AccountService;
import com.example.demo.service.BokesService;
import com.example.demo.service.UserService;
import com.example.demo.utils.AccountLoginToken;
import com.example.demo.utils.M;
import com.example.demo.utils.PassToken;

@CrossOrigin
@Controller
@RequestMapping("/index/")
/**
 * 前台所有的请求
 */
public class IndexController {

	@Autowired
    UserService uService;
	
	@Autowired
    AccountService as;

	@Autowired
	BokesService bs;

	@Autowired
	DiscussService ds;
	
	@PassToken
	@RequestMapping("/selEnabled")
	@ResponseBody
	public List<Boke> selEnabled() {
		return bs.SelEnabled();
	}
	
	@PassToken
	@RequestMapping("/indexData")
	@ResponseBody
	public M indexData() {
		return uService.findUserById("z123456");
	}
	
	@PassToken
	@ResponseBody
	@RequestMapping(path={"/login"},method = {RequestMethod.POST})
	public M login(@RequestBody Account account) {
		M map =  as.login(account);
		return map;
		
	}
	
	@PassToken
	@ResponseBody
	@RequestMapping(path={"/register"},method = {RequestMethod.POST})
	public M register(@RequestBody Account account) {
		M map =  as.register(account);
		return map;
		
	}
	
	/**
	 * 判断登录是否有效
	 * @return
	 */
	@AccountLoginToken
	@ResponseBody
	@RequestMapping(path={"/testLogin"},method = {RequestMethod.GET})
	public M testLogin(HttpServletRequest request) {
		String id = (String) request.getAttribute("id");
		Account account = as.SelById(id);
		System.out.println(account);
		 Map<String,Object> data = new HashMap<String,Object>();
		 Map<String,Object> m = new HashMap<String,Object>();
		 account.setPwd("");
		 data.put("account", account);
		 m.put("data", data);
		return M.ok(m);
	}

	@UserLoginToken
	@ResponseBody
	@RequestMapping(path={"/selByTitle"},method = {RequestMethod.GET})
	public M selByTitle(@RequestParam("title") String title){
		return bs.selByTitle(title);
	}

	@ResponseBody
	@RequestMapping(path={"/selById"},method = {RequestMethod.GET})
	public M selById(@RequestParam("id") Integer id){
		return bs.selById(id);
	}

	/**
	 * 根据博客查找评论列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path={"/selDisByBoke"},method = {RequestMethod.GET})
	public M selDisByBoke(@RequestParam("id") Integer id){
		return ds.selByBoke(id);
	}

    /**
     * 发表评论
     * @param dis
     * @return
     */
	@AccountLoginToken
    @ResponseBody
    @RequestMapping(path={"/addDiscuss"},method = {RequestMethod.POST})
	public M addDiscuss(@RequestBody Discuss dis){
	    return ds.addDiscuss(dis);
    }

	@ResponseBody
	@RequestMapping(path={"/editInfo"},method = {RequestMethod.POST})
	@AccountLoginToken
    public M editInfo(@RequestBody Account account,HttpServletRequest request){
		account.setId((String)request.getAttribute("id"));
    	return as.update(account);
	}

}
