# 学生信息管理系统

一个基于Java和MySQL的学生信息管理系统，支持学生信息的增删改查操作及数据备份与恢复功能。

## 项目介绍

该学生信息管理系统采用控制台交互方式，提供了便捷的学生信息管理功能，适合小型教育机构或班级使用。系统使用JDBC技术实现与MySQL数据库的交互，通过模块化设计保证了代码的可维护性和可扩展性。

## 功能特点

- 学生信息管理：支持添加、查询、修改、删除学生信息
- 批量操作：可查看所有学生信息并按学号排序
- 数据安全：提供数据备份与恢复功能，防止数据丢失
- 异常处理：完善的输入验证和错误处理机制

## 技术栈

- 编程语言：Java (JDK 1.8+)
- 数据库：MySQL 8.0
- 数据库连接：JDBC
- 数据备份：对象序列化

## 系统结构

```
学生信息管理系统
├── 主程序模块（Main）：菜单展示与用户交互
├── 数据访问模块（DBUtil）：数据库连接与资源管理
├── 业务逻辑模块（StudentService）：核心功能实现
├── 实体类（Student）：学生信息数据结构定义
└── 工具类
    ├── TypeConverter：数据类型转换
    ├── ReflectionUtil：反射工具
    └── BackupUtil：数据备份与恢复
```

## 数据库设计

学生表（student）结构：

| 字段名 | 数据类型 | 说明 | 约束 |
|--------|----------|------|------|
| student_no | VARCHAR(20) | 学号 | 主键，非空 |
| name | VARCHAR(50) | 姓名 | 非空 |
| gender | VARCHAR(2) | 性别 | 取值为"男"或"女" |
| birthday | DATE | 出生日期 | 允许为空 |
| major | VARCHAR(100) | 专业 | 允许为空 |
| score | DOUBLE | 成绩 | 0-100 |

## 快速开始

### 前提条件

- 安装JDK 1.8或更高版本
- 安装MySQL 8.0数据库
- 配置MySQL连接信息（修改DBUtil类中的URL、用户名和密码）

### 运行步骤

1. 克隆仓库到本地
   ```
   git clone https://github.com/DT-rui/student-management-system.git
   ```

2. 导入项目到IDE（如IntelliJ IDEA或Eclipse）

3. 配置数据库连接信息
   在`DBUtil.java`中修改数据库连接参数：
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/student_db";
   private static final String USER = "your_username";
   private static final String PASSWORD = "your_password";
   ```

4. 运行Main类的main方法启动系统

## 使用说明

系统启动后，通过控制台菜单进行操作：
- 选择对应数字进行功能操作
- 按照提示输入相关信息
- 输入0退出系统

## 数据备份与恢复

- 备份：选择菜单中的"备份数据"选项，系统会将数据保存到`students_backup.dat`文件
- 恢复：选择菜单中的"恢复数据"选项，系统会从备份文件恢复数据

## 联系方式

如有问题或建议，请联系：[1639481472@qq.com]
