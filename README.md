﻿
<p align="center">
<img src="https://github.com/15838028035/flows/blob/master/src/main/resources/banner.svg" alt="upm" align="middle" width="50%" height="50%" />
<p>

# 测试是否正常
 启动项目
访问
http://127.0.0.1:8087/swagger-ui.html#/

登陆首页:
http://127.0.0.1:8087/login.html

默认账号:
    admin 
默认密码:
    123456

# 打包操作步驟
    (1)清理log目录下日志文件
    (2)修改src/main/resources/application.properties地址为127.0.0.1
    (3)修改src/main/bin/ shutdown.sh、start.sh 文件中的項目版本信息,
    (4)执行maven打包命令mvn clean install -Pjar-with-dependencies -X,打包完成后到target复制zip
