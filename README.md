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