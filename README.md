# 工程简介
java 版的Akun Wallpaper，可以拿来用于代码审计练习。

# 数据库导入
根目录中有images.sql文件，将这个文件导入到数据库中即可。
```mysql
mysql -uroot -proot
create database images
mysql -uroot -proot images<images.sql
```

# 配置文件介绍
配置文件路径：src/main/application.yml

```yml
file:
  upload:
    path: F:\upload\    图片上传路径，路径后的斜杠不能省略
    extension: jpg,jpeg,png,gif   允许上传文件的扩展名
```
