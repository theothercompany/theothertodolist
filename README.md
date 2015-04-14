theothertodolist
=====


To get started:


* Create two databases in Mysql (theothertodolist and theothertodolisttest), tables would be automatically created
* Create 2 properties files below (in /opt/j2ee/theothercompany/settings) 

####theothertodolist-config.properties
```sh
jdbc.url=jdbc:mysql://localhost:3306/theothertodolist
jdbc.username=root
jdbc.password=password
```
###theothertodolist-test-config.properties
```sh
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/theothertodolisttest
jdbc.username=root
jdbc.password=password



