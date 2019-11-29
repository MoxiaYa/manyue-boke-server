# 个人博客系统后台代码
- 后台：https://github.com/MoxiaYa/manyue-boke-server
- 管理端前端：https://github.com/MoxiaYa/manyue-boke_admin
- 客户端前端：https://github.com/MoxiaYa/manyue-boke-account
## 技术栈:
- springboot
- mybatis
## 如何运行
1. 导入项目到IDEA或者Eclipse中
2. 下载依赖
3. 直接运行DemoApplication即可

## 后台接口响应规范
### code
- 200 : 正常
- 50008 ： 无Token
- 50012 ： Token过期
- 50014 ： 用户不存在

### login
```javascript
{
	code:300,
	message:'pwdError'//密码错误
}
```

## 前台接口响应规范
### code
- 200 : 正常
- 400 : 密码错误
- 500 : 无此账户
