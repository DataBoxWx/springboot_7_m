
p2p-admin项目部署步骤：

1、将项目导入到idea工具中
2、在数据库创建一个数据库p2padmin
3、把项目工程下的p2padmin.sql脚本的表和数据导入到p2padmin数据库；
4、在数据库创建一个数据库p2p2
5、把项目工程下的p2p2.sql脚本的表和数据导入到p2p2数据库；
6、修改项目的application.properties中的数据库连接信息配置；
7、运行项目springboot的P2pAdminApplication这个类的main方法启动项目；
8、访问项目，在浏览器地址栏输入：http://localhost:9500/p2p-admin/
9、输入账号：admin 密码：123456 登录系统；