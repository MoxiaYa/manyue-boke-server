package com.example.demo.handler;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.bean.Account;
import com.example.demo.bean.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;
import com.example.demo.utils.AccountLoginToken;
import com.example.demo.utils.PassToken;
import com.example.demo.utils.UserLoginToken;

public class AuthenticationInterceptor implements HandlerInterceptor {
	@Autowired
    UserService userService;
	
	@Autowired
    AccountService accountService;
	
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("Token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要管理员用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                	httpServletResponse.getWriter().append("{'code':50008,'message':'No Token'}");
                	setCorsMappings(httpServletRequest, httpServletResponse);
                	return false;
                    //throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                	httpServletResponse.getWriter().append("{'code':50008,'message':'No Token'}");
                	setCorsMappings(httpServletRequest, httpServletResponse);
                	return false;
                    //throw new RuntimeException("401");
                }
                User user = userService.SelById(userId);
                if (user == null) {
                	httpServletResponse.getWriter().append("{'code':50014,'message':'No User'}");
                	setCorsMappings(httpServletRequest, httpServletResponse);
                	return false;
                    //throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPwd())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                	httpServletResponse.getWriter().append("{'code':50012,'message':'Token Out'}");
                	setCorsMappings(httpServletRequest, httpServletResponse);
                	return false;
                }
                httpServletRequest.setAttribute("userID",user.getId());
                System.out.println("拦截器截取的userID:"+user.getId());
                return true;
            }
        }else {
        	//检查有没有需要普通用户权限的注解
            if (method.isAnnotationPresent(AccountLoginToken.class)) {
            	AccountLoginToken accountLoginToken = method.getAnnotation(AccountLoginToken.class);
                if (accountLoginToken.required()) {
                    // 执行认证
                    if (token == null) {
                    	httpServletResponse.getWriter().append("{'code':50001,'message':'No Token'}");
                    	setCorsMappings(httpServletRequest, httpServletResponse);
                    	return false;
                       // throw new RuntimeException("无token，请重新登录");
                    }
                    // 获取 token 中的 user id
                    String accountId;
                    try {
                    	accountId = JWT.decode(token).getAudience().get(0);
                    } catch (JWTDecodeException j) {
                    	httpServletResponse.getWriter().append("{'code':50002,'message':'No Token'}");
                    	setCorsMappings(httpServletRequest, httpServletResponse);
                    	return false;
                        //throw new RuntimeException("401");
                    }
                    /**
                     * 以下暂未改动完成
                     * 2019.11.12'
                     * by 张
                     */
                    Account account = accountService.SelById(accountId);
                    if (account == null) {
                    	httpServletResponse.getWriter().append("{'code':50003,'message':'No User'}");
                    	setCorsMappings(httpServletRequest, httpServletResponse);
                    	return false;
                        
                    }
                    // 验证 token
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(account.getPwd())).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (JWTVerificationException e) {
                    	httpServletResponse.getWriter().append("{'code':50004,'message':'Token Out'}");
                    	setCorsMappings(httpServletRequest, httpServletResponse);
                    	return false;
                    }
                    accountId = JWT.decode(token).getAudience().get(0);
                    httpServletRequest.setAttribute("id",accountId);
                    //httpServletRequest.setAttribute("account",account);
                    return true;
                }
            }
        }
      
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, 
                                  HttpServletResponse httpServletResponse, 
                            Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, 
                                          HttpServletResponse httpServletResponse, 
                                          Object o, Exception e) throws Exception {


    }
    
    private void setCorsMappings(HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
    }
}
