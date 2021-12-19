package com.yht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootShiroApplication {

	/**
	 * 操作对象：课程 的增删改查 课程名称 课时 课程评分 课程详情
	 * 	用户：用户名，密码，角色
	 * 	管理员：姓名，性别，年龄，方向
	 * 	教师：姓名，性别，年龄，职称
	 *	学生：姓名，性别，年龄，专业
	 * 对于学生，只能看课程信息
	 * 教师：修改和查看课程信息
	 * 管理员对课程进行CRUD
	 */
	public static void main(String[] args)  {
		SpringApplication.run(SpringbootShiroApplication.class, args);
	}

}
