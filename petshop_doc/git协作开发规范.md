# git协作开发规范

[TOC]

## 背景

我们使用git进行项目的同步管理，维护的项目可以在gitlab上查看：
http://gitlab.ext.upa.com

## git分支角色

* master: 主分支，维护当前项目的最新的可发布状态
* release-a.b.c：发布分支，每一个发布就是一个包含相应特性的产品，其中a,b,c三级编号含义为：
  * a:  大版本号，包含重大更新
  * b:  小版本号，包含新功能 
  * c:  小版本号，修复完善已有功能
* dev：开发分支，维护当前项目的最新状态，用来合并所有开发者的更新
* dev-username: 每个开发者自己的分支

## git开发规范

* 每个开发者只能在自己的分支上开发，并提交到自己的分支
* 项目负责人审核通过后将开发者分支合并到dev分支
* 开发者定期从dev分支拉取代码，维护最新代码

## git开发流程实例说明

**1 克隆git代码到本地**
```
git clone http://gitlab.ext.upa.com/yangyifan/upa_data_fetch.git
```

**2 跟踪远程分支dev**
即同步该分支内容到本地
```
git checkout --track origin/dev
```

**3 创建自己的分支**
```
git branch dev-username
git checkout dev-username
```

**4 添加已经修改的文件到缓存区**
假设已经修改的文件为xxx1, xxx2
```
git add xxx1
git add xxx2
```

**5 提交修改到自己的分支上**
```
git commit -m "添加了文件xxx1,xxx2，一定要写注释"
```

**6 推送自己的分支到远程服务器上**
```
git push -u origin dev-username
```
上述命令等同于
```
git push --set-upstream origin dev-username
```
第二次以后的提交，可以直接使用命令
```
git push
```

**7 定期同步更新dev分支的内容**
拉取远程dev分支内容到本地
```
git fetch origin dev
```
合并
```
git merge origin/dev
```
推送分支到远程服务器上
```
git push origin dev-username
```

**8 设置用户名密码（避免每次都输入用户名密码）**
```
git config --global user.name [username]
git config --global user.email [email]
#查看已有配置
git config --list
```

**9 设置代理（访问特殊服务器）**
```
#使用代理127.0.0.1:1080
git config --global http.proxy http://127.0.0.1:1080
#取消使用代理
git config --global --unset http.proxy
```



## git的使用说明

教程：  
官方文档：https://git-scm.com/book/zh/v1  
廖雪峰的git教程：http://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/  



