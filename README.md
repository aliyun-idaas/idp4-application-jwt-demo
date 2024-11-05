# idp4-application-jwt-demo
JWT 应用插件 demo

**main**分支使用框架：[jose4j](https://bitbucket.org/b_c/jose4j/wiki/Home) 来解析jwt token  
**jjwt**分支使用框架：[jjwt](https://github.com/jwtk/jjwt?tab=readme-ov-file#example-jws-hs) 来解析jwt token


## 文件清单
1. idp4-application-jwt-demo.war
2. application.properties


## 请按以下步骤操作

### 1. 前提
本DEMO环境依赖于JDK 1.8，请先提前安装   
修改配置文件：application.properties (主要是jwt.host)

### 2.启动, 初始化账号与登录

使用java -jar启动程序：
> java -jar idp4-application-jwt-demo.war

访问 {host}/     如: http://localhost:8080/

第一次需要初始一个账号, 浏览器访问  {host}/public/initial_user

初始账号/密码为: admin/admin@jwtdemo
然后使用初始账号/密码 登录


### 3.idaas中先创建一个jwt应用
"redirect_uri" 先随便填写一个  
"target_url"可不填写，若已存在请忽略。

### 4.jwt-demo中为idaas配置单点登录
登录jwt-demo后, 点击左侧 "单点登录配置" 菜单，右上角点击 "添加SSO配置"，在出现的表单中  
publicKey填写从idaas上JWT应用详情中复制 "JWT PublicKey" 内容 (注意前后不要有空格)，创建完成后，会生成一个"SSO URL"地址


### 5.在idaas中配置jwt-demo应用
修改刚创建的jwt应用  
"redirect_uri" 填写jwt-demo中刚创建单点登录配置的"SSO URL"  
"target_url"   按实际情况填写


### 6.在idaas中将jwt应用启用, 授权, 并SSO
若在jwt-demo应用中没有从idaas SSO过来的账号（username { xxxx } not exist）, 可在jwt-demo应用中，登录后左侧菜单 "用户" 中创建对应的账户xxxx即可（账户名称和idaas中一致即可）.