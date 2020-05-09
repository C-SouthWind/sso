# sso

sso

	sso
	浏览器打开  http://127.0.0.1:8080
	点击下单 进入登录页面 输入正确的账号密码后 返回下单页面 再次点击直接进入不再登录
	关闭浏览器再次打开 需要再走一遍流程  cookie存在浏览器中  cookie的value是redis的key
	根据cookie的key对应的value去查redis中存的用户信息
	跨域请求分为 空间跨域和域名跨域   不能串起来用否则不生效
	




sso
	包括：

	1.sso
		sso-portal
		
		sso-login
