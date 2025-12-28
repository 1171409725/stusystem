-- 宿舍卫生管理系统数据库初始化脚本

-- 创建数据库
CREATE DATABASE IF NOT EXISTS dormitory_health DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE dormitory_health;

-- 用户表（包含所有角色：学生、卫生检查员、教师、系统管理员）
CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名（学号或工号）',
    password VARCHAR(100) NOT NULL COMMENT '密码（MD5加密）',
    role VARCHAR(20) NOT NULL DEFAULT 'student' COMMENT '角色（student/inspector/teacher/admin）',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    gender VARCHAR(10) COMMENT '性别',
    phone VARCHAR(20) COMMENT '电话',
    email VARCHAR(100) COMMENT '邮箱',
    status VARCHAR(20) DEFAULT 'active' COMMENT '状态（active/inactive）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 班级表
CREATE TABLE IF NOT EXISTS class (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '班级ID',
    class_no VARCHAR(50) NOT NULL UNIQUE COMMENT '班级编号',
    class_name VARCHAR(100) NOT NULL COMMENT '班级名称',
    major VARCHAR(100) COMMENT '专业',
    grade VARCHAR(20) COMMENT '年级',
    description TEXT COMMENT '班级描述',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表';

-- 宿舍表
CREATE TABLE IF NOT EXISTS dormitory (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '宿舍ID',
    building VARCHAR(50) NOT NULL COMMENT '楼栋号',
    room_number VARCHAR(50) NOT NULL COMMENT '房间号',
    capacity INT NOT NULL COMMENT '容纳人数',
    current_count INT DEFAULT 0 COMMENT '当前人数',
    status VARCHAR(20) DEFAULT '正常' COMMENT '状态（正常/维修中/已停用）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_building_room (building, room_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宿舍表';

-- 学生表（关联用户表和班级表）
CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '学生ID',
    user_id INT NOT NULL UNIQUE COMMENT '用户ID',
    student_no VARCHAR(50) NOT NULL UNIQUE COMMENT '学号',
    class_id INT COMMENT '班级ID',
    dormitory_id INT COMMENT '宿舍ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES class(id) ON DELETE SET NULL,
    FOREIGN KEY (dormitory_id) REFERENCES dormitory(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- 卫生检查记录表（关联检查人员用户ID）
CREATE TABLE IF NOT EXISTS health_check (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '检查记录ID',
    dormitory_id INT NOT NULL COMMENT '宿舍ID',
    check_date DATE NOT NULL COMMENT '检查日期',
    score DECIMAL(5,2) NOT NULL COMMENT '评分（0-100）',
    grade VARCHAR(20) COMMENT '等级（优秀/良好/合格/不合格）',
    comment TEXT COMMENT '检查意见',
    checker_id INT COMMENT '检查人员ID',
    checker_name VARCHAR(50) COMMENT '检查人员姓名（冗余字段）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (dormitory_id) REFERENCES dormitory(id) ON DELETE CASCADE,
    FOREIGN KEY (checker_id) REFERENCES user(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卫生检查记录表';

-- 权限申请表
CREATE TABLE IF NOT EXISTS permission_application (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '申请ID',
    user_id INT NOT NULL COMMENT '申请人ID',
    applicant_name VARCHAR(50) COMMENT '申请人姓名',
    application_type VARCHAR(20) NOT NULL COMMENT '申请类型（inspector）',
    reason TEXT COMMENT '申请理由',
    status VARCHAR(20) DEFAULT 'pending' COMMENT '状态（pending/approved/rejected）',
    reviewer_id INT COMMENT '审核人ID',
    reviewer_name VARCHAR(50) COMMENT '审核人姓名',
    review_comment TEXT COMMENT '审核意见',
    apply_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
    review_time DATETIME COMMENT '审核时间',
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (reviewer_id) REFERENCES user(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限申请表';

-- 插入测试数据
-- 插入班级数据
INSERT INTO class (class_no, class_name, major, grade) VALUES
('CS2024-01', '计算机科学2024级1班', '计算机科学与技术', '2024'),
('CS2024-02', '计算机科学2024级2班', '计算机科学与技术', '2024'),
('SE2024-01', '软件工程2024级1班', '软件工程', '2024');

-- 插入宿舍数据
INSERT INTO dormitory (building, room_number, capacity, current_count, status) VALUES
('1号宿舍楼', '101', 4, 0, '正常'),
('1号宿舍楼', '102', 4, 0, '正常'),
('1号宿舍楼', '103', 4, 0, '正常'),
('2号宿舍楼', '201', 6, 0, '正常'),
('2号宿舍楼', '202', 6, 0, '正常');

-- 插入用户数据（密码使用明码：123456）
INSERT INTO user (username, password, role, name, gender, phone, email) VALUES
('admin', '123456', 'admin', '系统管理员', '男', '13800000000', 'admin@example.com'),
('teacher001', '123456', 'teacher', '张老师', '男', '13800000001', 'teacher001@example.com'),
('inspector001', '123456', 'inspector', '卫生检查员1', '女', '13800000002', 'inspector001@example.com'),
('2024001', '123456', 'student', '张三', '男', '13800138001', '2024001@example.com'),
('2024002', '123456', 'student', '李四', '男', '13800138002', '2024002@example.com'),
('2024003', '123456', 'student', '王五', '男', '13800138003', '2024003@example.com'),
('2024004', '123456', 'student', '赵六', '男', '13800138004', '2024004@example.com');

-- 插入学生数据
INSERT INTO student (user_id, student_no, class_id, dormitory_id) VALUES
(4, '2024001', 1, 1),
(5, '2024002', 1, 1),
(6, '2024003', 2, 2),
(7, '2024004', 2, 2);

-- 插入卫生检查记录
INSERT INTO health_check (dormitory_id, check_date, score, grade, comment, checker_id, checker_name) VALUES
(1, '2025-01-15', 95.00, '优秀', '宿舍整洁，物品摆放整齐', 3, '卫生检查员1'),
(2, '2025-01-15', 88.00, '良好', '整体良好，个别细节需要改进', 3, '卫生检查员1'),
(3, '2025-01-15', 75.00, '合格', '基本达标，需要继续保持', 2, '张老师');

