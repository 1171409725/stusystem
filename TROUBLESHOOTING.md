# 404错误排查指南

## 问题描述
登录系统后，点击首页的各个功能卡片时出现404错误。

## 可能的原因和解决方案

### 1. 项目未重新编译
**问题**：修改代码后没有重新编译项目。

**解决方案**：
```bash
# 在项目根目录执行
mvn clean compile
# 或者
mvn clean package
```

### 2. Tomcat未重新部署
**问题**：即使重新编译了，Tomcat可能还在使用旧的class文件。

**解决方案**：
1. 停止Tomcat服务器
2. 删除 `webapps/stusystem` 目录（如果存在）
3. 重新部署war文件或项目
4. 启动Tomcat服务器

### 3. 路径映射问题
**问题**：Servlet路径映射不正确。

**检查方法**：
- 确认 `web.xml` 中的Servlet映射正确
- 确认访问的URL格式正确：`/stusystem/profile/view`

### 4. 方法不存在
**问题**：BaseServlet无法找到对应的方法。

**已修复**：
- 已为ProfileServlet添加了list方法
- BaseServlet现在会正确处理路径解析

### 5. 权限过滤器拦截
**问题**：AuthFilter可能错误地拦截了请求。

**检查方法**：
- 检查 `AuthFilter.java` 中的路径排除规则
- 确认登录后session中有user对象

## 验证步骤

1. **检查编译**
   ```bash
   mvn clean package
   ```
   确认没有编译错误

2. **检查部署**
   - 确认war文件已部署到Tomcat
   - 检查Tomcat日志是否有错误

3. **测试访问**
   - 访问：`http://localhost:8080/stusystem/login.jsp`
   - 登录后访问：`http://localhost:8080/stusystem/profile/view`
   - 检查浏览器控制台和Tomcat日志

4. **检查Session**
   - 确认登录后session中有user对象
   - 可以在JSP中使用 `${sessionScope.user}` 检查

## 常见错误

### 错误1：找不到方法
**错误信息**：`方法调用失败: view`

**原因**：BaseServlet无法找到view方法

**解决**：已添加list方法作为默认方法

### 错误2：JSP文件不存在
**错误信息**：`/profile/view.jsp (系统找不到指定的文件)`

**原因**：JSP文件路径不正确

**解决**：确认JSP文件在 `src/main/webapp/profile/view.jsp`

### 错误3：权限被拒绝
**错误信息**：`403 Forbidden`

**原因**：权限检查失败

**解决**：确认用户角色和权限设置正确

## 调试建议

1. **添加日志**
   在BaseServlet中添加日志输出，查看方法名提取是否正确：
   ```java
   System.out.println("Method name: " + methodName);
   System.out.println("Path: " + path);
   ```

2. **检查Tomcat日志**
   查看 `catalina.out` 或Tomcat日志文件，查找错误信息

3. **使用浏览器开发者工具**
   - 查看Network标签，确认请求的URL
   - 查看Response，确认服务器返回的内容

## 快速修复

如果以上方法都不行，尝试：

1. **完全重新部署**
   ```bash
   # 停止Tomcat
   # 删除webapps下的项目
   # 重新打包
   mvn clean package
   # 重新部署war文件
   # 启动Tomcat
   ```

2. **检查web.xml**
   确认所有Servlet映射都已正确配置

3. **检查JSP文件**
   确认所有JSP文件都在正确的位置

