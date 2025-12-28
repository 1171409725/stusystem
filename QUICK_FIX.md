# 404错误快速修复指南

## 问题
登录系统后，点击首页功能卡片出现404错误。

## 已修复的问题

1. ✅ **ProfileServlet缺少list方法** - 已添加
2. ✅ **BaseServlet路径解析优化** - 已改进
3. ✅ **错误处理改进** - 已优化

## 解决步骤

### 步骤1：重新编译项目
```bash
cd d:\javaweb\stusystem
mvn clean compile
```

或者完整打包：
```bash
mvn clean package
```

### 步骤2：重新部署到Tomcat

**方法A：使用war文件部署**
1. 停止Tomcat
2. 删除 `webapps/stusystem` 目录（如果存在）
3. 复制 `target/stusystem-1.0-SNAPSHOT.war` 到 `webapps/`
4. 启动Tomcat

**方法B：使用IDE部署**
1. 在IDE中重新部署项目
2. 重启Tomcat服务器

### 步骤3：清除浏览器缓存
- 按 `Ctrl + F5` 强制刷新
- 或清除浏览器缓存后重新访问

### 步骤4：测试访问
1. 访问：`http://localhost:8080/stusystem/login.jsp`
2. 使用测试账户登录（如：admin / 123456）
3. 点击"个人信息"卡片
4. 应该能正常访问 `/profile/view` 页面

## 验证修复

访问以下URL，应该都能正常工作：
- `/profile/view` - 个人信息
- `/healthcheck/list` - 卫生检查
- `/dormitory/list` - 宿舍管理（需要teacher/admin权限）
- `/student/list` - 学生管理（需要teacher/admin权限）
- `/class/list` - 班级管理（需要teacher/admin权限）
- `/user/list` - 用户管理（需要admin权限）
- `/permission/myList` - 我的申请
- `/permission/pendingList` - 待审核申请（需要teacher/admin权限）

## 如果仍然出现404

### 检查Tomcat日志
查看 `catalina.out` 或Tomcat日志，查找：
- `BaseServlet - URI: ...`
- `BaseServlet - Path: ...`
- `BaseServlet - Method: ...`

这些日志会显示路径解析的过程。

### 检查Session
确认登录后session中有user对象：
- 在浏览器控制台检查Cookie
- 或在JSP页面使用 `${sessionScope.user}` 检查

### 检查web.xml
确认所有Servlet映射都已正确配置。

## 常见问题

**Q: 重新编译后还是404？**
A: 确保Tomcat已完全重启，并且使用的是新编译的class文件。

**Q: 某些功能可以访问，某些不行？**
A: 检查权限设置，某些功能需要特定角色才能访问。

**Q: 登录后直接跳转到404？**
A: 检查 `index.jsp` 中的链接路径是否正确。

## 联系支持

如果以上方法都无法解决问题，请提供：
1. Tomcat日志中的错误信息
2. 浏览器控制台的错误信息
3. 访问的具体URL

