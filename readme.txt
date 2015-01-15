EasyGo B2C Web Proj
实现了后台管理（商品、用户、订单管理），以及用户从 注册——查找&浏览商品——下单——付款——签收 一条线的流程。

项目编码：
	UTF-8

项目配置：
	1、MyEclipse 引入工程
	2、安装mysql及mysql gui tools；找到项目目录下/sql_script/easygo_bak.sql，通过mysql administrator恢复数据
	3、安装tomcat，找到项目目录下/configs/easygo.xml，将easygo.xml复制到 %TomcatRoot%\conf\Catalina\localhost\
	目录下，其中%TomcatRoot%为tomcat的根目录。
	4、打开easygo.xml，将docbase里的内容替换为/WebRoot的绝对路径（如：docBase="D:\Documents\\easygo\WebRoot"）。
	5、启动tomcat，用浏览器浏览 localhost:8080/easygo；以admin/123登录。
	