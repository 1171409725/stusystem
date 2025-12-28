# 项目开发状态说明

## 已完成的工作

### 1. 数据库设计 ✅
- ✅ 用户表（user）- 包含角色、密码等字段
- ✅ 班级表（class）
- ✅ 宿舍表（dormitory）
- ✅ 学生表（student）- 关联用户表和班级表
- ✅ 卫生检查记录表（health_check）- 关联检查人员用户ID
- ✅ 权限申请表（permission_application）
- ✅ 数据库初始化脚本（database/init.sql）

### 2. 实体类 ✅
- ✅ User（用户）
- ✅ Class（班级）
- ✅ Dormitory（宿舍）
- ✅ Student（学生）- 已更新为关联用户和班级
- ✅ HealthCheck（卫生检查记录）- 已更新关联检查人员
- ✅ PermissionApplication（权限申请）

### 3. 数据访问层 ✅
- ✅ UserMapper 接口和XML
- ✅ ClassMapper 接口和XML
- ✅ StudentMapper 接口和XML（已更新）
- ✅ DormitoryMapper 接口和XML
- ✅ HealthCheckMapper 接口和XML（已更新）
- ✅ PermissionApplicationMapper 接口和XML

### 4. 业务逻辑层 ✅
- ✅ UserService
- ✅ ClassService
- ✅ StudentService（已添加findByUserId方法）
- ✅ DormitoryService
- ✅ HealthCheckService（已更新）
- ✅ PermissionApplicationService（包含审核功能）

### 5. 工具类 ✅
- ✅ MyBatisUtil
- ✅ MD5Util（密码加密）

### 6. 过滤器 ✅
- ✅ CharacterEncodingFilter（字符编码）
- ✅ AuthFilter（权限认证）

### 7. 控制器层（部分完成）
- ✅ LoginServlet（登录）
- ✅ LogoutServlet（退出）
- ✅ BaseServlet（基础Servlet，方法分发）
- ✅ DormitoryServlet（宿舍管理）
- ✅ HealthCheckServlet（卫生检查）
- ✅ StudentServlet（学生管理）
- ⚠️ UserServlet（用户管理）- **需要创建**
- ⚠️ ClassServlet（班级管理）- **需要创建**
- ⚠️ PermissionApplicationServlet（权限申请）- **需要创建**

### 8. Web配置 ✅
- ✅ web.xml - 已配置过滤器和Servlet映射

### 9. 前端页面（部分完成）
- ✅ login.jsp（登录页面）
- ✅ index.jsp（首页）
- ✅ common/header.jsp（公共头部）
- ✅ common/footer.jsp（公共底部）
- ✅ dormitory/*.jsp（宿舍管理页面）
- ✅ student/*.jsp（学生管理页面）
- ✅ healthcheck/*.jsp（卫生检查页面）
- ⚠️ user/*.jsp（用户管理页面）- **需要创建**
- ⚠️ class/*.jsp（班级管理页面）- **需要创建**
- ⚠️ permission/*.jsp（权限申请页面）- **需要创建**
- ⚠️ profile.jsp（个人信息页面）- **需要创建**

## 待完成的工作

### 1. 创建UserServlet（用户管理）
功能需求：
- 列表查询（需要权限控制：teacher/admin）
- 添加用户（需要权限控制：teacher/admin）
- 编辑用户（需要权限控制：teacher/admin）
- 删除用户（需要权限控制：admin）
- 修改密码（所有用户都可以修改自己的密码）

### 2. 创建ClassServlet（班级管理）
功能需求：
- 列表查询（需要权限控制：teacher/admin）
- 添加班级（需要权限控制：teacher/admin）
- 编辑班级（需要权限控制：teacher/admin）
- 删除班级（需要权限控制：teacher/admin）

### 3. 创建PermissionApplicationServlet（权限申请管理）
功能需求：
- 学生提交申请（student角色）
- 查看自己的申请列表（student角色）
- 教师查看待审核申请列表（teacher/admin角色）
- 审核申请（通过/拒绝）（teacher/admin角色）
- 查看申请详情

### 4. 创建个人信息管理功能
功能需求：
- 查看个人信息（所有用户）
- 修改密码（所有用户）
- 修改联系方式（所有用户）
- 学生查看自己和宿舍的卫生检查结果

### 5. 更新现有Servlet的权限控制
需要在各个Servlet方法中添加权限检查：
- DormitoryServlet - 需要teacher/admin权限
- StudentServlet - 需要teacher/admin权限
- HealthCheckServlet - 需要inspector/teacher/admin权限（学生只能查看）
- 添加权限检查工具类或方法

### 6. 更新HealthCheckServlet
需要：
- 在添加/编辑检查记录时，自动设置checkerId为当前登录用户ID
- 学生只能查看自己宿舍的检查记录

### 7. 更新首页index.jsp
根据用户角色显示不同的菜单：
- 学生：个人信息、我的宿舍检查记录、提交权限申请
- 卫生检查员：学生功能 + 卫生检查管理
- 教师：检查员功能 + 学生管理、班级管理、宿舍管理、审核申请
- 管理员：教师功能 + 用户管理

### 8. 更新StudentServlet
需要：
- 添加学生时同时创建用户
- 编辑时同时更新用户信息
- 删除时同时删除用户

## 权限说明

### 角色定义
- **student（学生）**：基础权限
- **inspector（卫生检查员）**：包含学生权限 + 卫生检查管理
- **teacher（教师）**：包含检查员权限 + 学生/班级/宿舍管理 + 审核申请
- **admin（系统管理员）**：包含教师权限 + 用户管理

### 权限矩阵

| 功能 | student | inspector | teacher | admin |
|------|---------|-----------|---------|-------|
| 查看个人信息 | ✅ | ✅ | ✅ | ✅ |
| 修改密码/联系方式 | ✅ | ✅ | ✅ | ✅ |
| 查看自己和宿舍检查记录 | ✅ | ✅ | ✅ | ✅ |
| 提交权限申请 | ✅ | ✅ | ✅ | ✅ |
| 卫生检查管理（增删改） | ❌ | ✅ | ✅ | ✅ |
| 学生管理（增删改） | ❌ | ❌ | ✅ | ✅ |
| 班级管理（增删改） | ❌ | ❌ | ✅ | ✅ |
| 宿舍管理（增删改） | ❌ | ❌ | ✅ | ✅ |
| 审核权限申请 | ❌ | ❌ | ✅ | ✅ |
| 用户管理（增删改） | ❌ | ❌ | ❌ | ✅ |

## 下一步开发建议

1. **优先级1**：创建UserServlet和用户管理页面，实现用户管理功能
2. **优先级2**：创建ClassServlet和班级管理页面，实现班级管理功能
3. **优先级3**：创建PermissionApplicationServlet和权限申请页面
4. **优先级4**：创建个人信息管理功能（profile.jsp和ProfileServlet）
5. **优先级5**：在所有Servlet中添加权限检查
6. **优先级6**：更新首页，根据角色显示不同菜单
7. **优先级7**：完善细节和测试

## 注意事项

1. 所有密码使用MD5加密存储（默认密码：123456，MD5：e10adc3949ba59abbe56e057f20f883e）
2. 删除操作需要谨慎，某些操作会级联删除
3. 权限检查应该在每个需要权限的方法中进行
4. Session中存储的user对象包含当前登录用户信息
5. 学生角色需要从student表中查询额外信息

## 测试账户

数据库初始化脚本中已包含测试数据：
- 管理员：admin / 123456
- 教师：teacher001 / 123456
- 卫生检查员：inspector001 / 123456
- 学生：2024001 / 123456

