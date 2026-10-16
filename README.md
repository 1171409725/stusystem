# 宿舍卫生管理系统

## 项目简介

这是一个基于Java Web技术开发的宿舍卫生管理系统，采用传统的MVC架构，用于管理学生宿舍信息和卫生检查记录。

## 技术栈

- **前端技术**: HTML + CSS + JavaScript + JSP
- **后端技术**: Servlet + MyBatis + MVC架构
- **服务器**: Tomcat 10.1.46
- **JDK版本**: 17
- **数据库**: MySQL 8.0
- **构建工具**: Maven

## 功能模块

1. **宿舍管理**
   - 宿舍信息的增删改查
   - 宿舍状态管理（正常/维修中/已停用）
   - 宿舍人数统计

2. **学生管理**
   - 学生信息的增删改查
   - 学生与宿舍的关联管理
   - 学生基本信息维护

3. **卫生检查**
   - 卫生检查记录的增删改查
   - 评分管理（0-100分）
   - 等级自动评定（优秀/良好/合格/不合格）
   - 检查意见记录

## 项目结构

```
stusystem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/
│   │   │       ├── entity/          # 实体类
│   │   │       ├── mapper/          # Mapper接口
│   │   │       ├── service/         # Service层
│   │   │       ├── servlet/         # Servlet控制器
│   │   │       ├── filter/          # 过滤器
│   │   │       └── util/            # 工具类
│   │   ├── resources/
│   │   │   ├── org/example/mapper/  # MyBatis映射文件
│   │   │   ├── db.properties        # 数据库配置
│   │   │   ├── mybatis-config.xml   # MyBatis配置
│   │   │   └── log4j.properties     # 日志配置
│   │   └── webapp/
│   │       ├── index.jsp            # 首页
│   │       ├── common/              # 公共页面组件
│   │       ├── dormitory/           # 宿舍管理页面
│   │       ├── student/             # 学生管理页面
│   │       └── healthcheck/         # 卫生检查页面
│   └── test/
├── database/
│   └── init.sql                     # 数据库初始化脚本
├── pom.xml                          # Maven配置文件
└── README.md                        # 项目说明文档
```

## 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- Tomcat 10.1.46+

## 安装部署

### 1. 数据库配置

1. 创建MySQL数据库，执行 `database/init.sql` 脚本初始化数据库和表结构
2. 修改 `src/main/resources/db.properties` 中的数据库连接信息：
   ```properties
   db.url=jdbc:mysql://localhost:3306/dormitory_health?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
   db.username=root
   db.password=your_password
   ```

### 2. 项目构建

```bash
# 编译项目
mvn clean compile

# 打包项目
mvn clean package
```

打包后会生成 `target/stusystem-1.0-SNAPSHOT.war` 文件。

### 3. 部署到Tomcat

1. 将生成的 `war` 文件复制到Tomcat的 `webapps` 目录
2. 启动Tomcat服务器
3. 访问 `http://localhost:8080/stusystem/`

## 使用说明

### 宿舍管理

- **查看列表**: 访问 `/dormitory/list` 查看所有宿舍信息
- **添加宿舍**: 点击"添加宿舍"按钮，填写宿舍基本信息
- **编辑宿舍**: 在列表页面点击"编辑"按钮修改宿舍信息
- **删除宿舍**: 在列表页面点击"删除"按钮（会删除关联的检查记录）

### 学生管理

- **查看列表**: 访问 `/student/list` 查看所有学生信息
- **添加学生**: 点击"添加学生"按钮，填写学生基本信息并选择宿舍
- **编辑学生**: 在列表页面点击"编辑"按钮修改学生信息
- **删除学生**: 在列表页面点击"删除"按钮

### 卫生检查

- **查看列表**: 访问 `/healthcheck/list` 查看所有检查记录
- **添加记录**: 点击"添加检查记录"按钮，选择宿舍、填写检查日期、评分等信息
- **编辑记录**: 在列表页面点击"编辑"按钮修改检查记录
- **删除记录**: 在列表页面点击"删除"按钮

### 评分等级说明

- **优秀**: 90-100分
- **良好**: 80-89分
- **合格**: 60-79分
- **不合格**: 0-59分

系统会根据评分自动确定等级。

## 开发说明

### MVC架构说明

- **Model**: 实体类（Entity）和数据库访问层（Mapper）
- **View**: JSP页面，负责展示数据
- **Controller**: Servlet类，处理请求并调用Service层

### 核心类说明

- `BaseServlet`: 基础Servlet类，实现方法分发功能
- `MyBatisUtil`: MyBatis工具类，提供SqlSession获取方法
- `CharacterEncodingFilter`: 字符编码过滤器，统一处理UTF-8编码

### 扩展开发

如需添加新功能模块：

1. 在 `entity` 包下创建实体类
2. 在 `mapper` 包下创建Mapper接口和XML映射文件
3. 在 `service` 包下创建Service类
4. 在 `servlet` 包下创建Servlet控制器（继承BaseServlet）
5. 在 `webapp` 目录下创建对应的JSP页面
6. 在 `web.xml` 中配置Servlet映射

## 注意事项

1. 数据库连接信息需要根据实际情况修改
2. 删除宿舍时，会级联删除该宿舍的所有检查记录
3. 学生删除后，其宿舍关联会被设置为NULL
4. 评分范围是0-100，系统会根据分数自动评定等级

## 许可证

本项目仅用于学习和教学 purposes。

