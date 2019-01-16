package team9.control.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import team9.control.utils.DataSourceUtil;
import team9.control.utils.MD5Utils;
import team9.model.ClassCourse;
import team9.model.ClassInfo;
import team9.model.ClassScheduleInfo;
import team9.model.CourseInfo;
import team9.model.ResultInfo;
import team9.model.StudentInfo;
import team9.model.TeacherInfo;
import team9.model.UserInfo;

public class DButils {
	// user表操作

	/**
	 * 添加用户信息
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param rolInfo  角色
	 */
	public static void insertUser(String username, String password, String rolInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into user values(?, ?, ?)";
			qr.update(sql, username, MD5Utils.md5(password), rolInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertTeacher(String teacherId, String teacherName) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into teacher_info values(?, ?)";
			qr.update(sql, teacherId, teacherName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertStudent(StudentInfo studentInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into student_info values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			qr.update(sql, studentInfo.getId(), studentInfo.getStuName(), studentInfo.getEnName(), studentInfo.getSex(),
					studentInfo.getGrade(), studentInfo.getEduSys(), studentInfo.getFaculty(), studentInfo.getMajor(),
					studentInfo.getStuMode(), studentInfo.getEducation(), studentInfo.getCampus(),
					studentInfo.getClassId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertClass(String classId, String className, String number) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into class_info values(?, ?, ?)";
			qr.update(sql, classId, className, number);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertCourse(CourseInfo courseInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into course_info values(?, ?, ?, ?, ?)";
			qr.update(sql, courseInfo.getId(), courseInfo.getCouName(), courseInfo.getCouType(),
					courseInfo.getCreditHour(), courseInfo.getCouFaculty());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertResultInfo(ResultInfo resultInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into result_info values(?, ?, ?, ?)";
			qr.update(sql, resultInfo.getSemester(), resultInfo.getId(), resultInfo.getCouId(), resultInfo.getResult());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertClassSchedule(ClassScheduleInfo classScheduleInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into class_schedule_info values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			qr.update(sql, classScheduleInfo.getClassId(), classScheduleInfo.getSemester(),
					classScheduleInfo.getMonNo1(), classScheduleInfo.getMonNo2(), classScheduleInfo.getMonNo3(),
					classScheduleInfo.getMonNo4(), classScheduleInfo.getTueNo1(), classScheduleInfo.getTueNo2(),
					classScheduleInfo.getTueNo3(), classScheduleInfo.getTueNo4(), classScheduleInfo.getWedNo1(),
					classScheduleInfo.getWedNo2(), classScheduleInfo.getWedNo3(), classScheduleInfo.getWedNo4(),
					classScheduleInfo.getThursNo1(), classScheduleInfo.getThursNo2(), classScheduleInfo.getThursNo3(),
					classScheduleInfo.getThursNo4(), classScheduleInfo.getFriNo1(), classScheduleInfo.getFriNo2(),
					classScheduleInfo.getFriNo3(), classScheduleInfo.getFriNo4());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertClassCourse(ClassCourse classCourse) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into class_course values(?, ?, ?, ?, ?, ?, ?, ?)";
			qr.update(sql, classCourse.getSemester(), classCourse.getClassId(), classCourse.getCouId(),
					classCourse.getTeaId(), classCourse.getExamType(), classCourse.getExamDate(),
					classCourse.getExamTime(), classCourse.getExamAddr());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改用户密码
	 * 
	 * @param username 用户名
	 * @param password 新密码
	 */
	public static void updateUserPassword(String username, String password) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update user set password = ? where userName = ?";
			password = MD5Utils.md5(password);
			qr.update(sql, password, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateUserId(String userName, String newUserName) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update user set userName = ? where userName = ?";
			qr.update(sql, newUserName, userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateTeacherName(String teacherId, String teacherName) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update teacher_info set name = ? where id = ?";
			qr.update(sql, teacherName, teacherId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateTeacherId(String teacherId, String teacherName) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update teacher_info set id = ? where name = ?";
			qr.update(sql, teacherId, teacherName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateStudentInfo(StudentInfo studentInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update student_info set stuName = ? , enName = ? , sex = ? , grade = ? , eduSys = ? , faculty = ? , major = ? , stuMode = ? , education = ? , campus = ? , classId = ? where id = ?";
			qr.update(sql, studentInfo.getStuName(), studentInfo.getEnName(), studentInfo.getSex(),
					studentInfo.getGrade(), studentInfo.getEduSys(), studentInfo.getFaculty(), studentInfo.getMajor(),
					studentInfo.getStuMode(), studentInfo.getEducation(), studentInfo.getCampus(),
					studentInfo.getClassId(), studentInfo.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateCourseInfo(CourseInfo courseInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update course_info set couName = ? , couType = ? , creditHour = ? , couFaculty = ? where id = ?";
			qr.update(sql, courseInfo.getCouName(), courseInfo.getCouType(), courseInfo.getCreditHour(),
					courseInfo.getCouFaculty(), courseInfo.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateClassName(String classId, String className) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update class_info set name = ? where id = ?";
			qr.update(sql, classId, className);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateResultInfo(ResultInfo resultInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update result_info set result = ? where semester = ? and id = ? and couId = ?";
			qr.update(sql, resultInfo.getResult(), resultInfo.getSemester(), resultInfo.getId(), resultInfo.getCouId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void updateClassCourse(ClassCourse classCourse) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update class_course set examType = ?, examDate = ?, examTime = ?, examAddr = ? where semester = ? and classId = ? and couId = ? and teaId = ?";
			qr.update(sql, classCourse.getExamType(), classCourse.getExamDate(), classCourse.getExamTime(), classCourse.getExamAddr(), classCourse.getSemester(), classCourse.getClassId(), classCourse.getCouId(), classCourse.getTeaId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateClassSchedule(ClassScheduleInfo classScheduleInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update class_schedule_info set semester = ? , monNo1 = ? , monNo2 = ? , monNo3 = ? , monNo4 = ? , tueNo1 = ? , tueNo2 = ? , tueNo3 = ? , tueNo4 = ? , wedNo1 = ? , wedNo2 = ? , wedNo3 = ? , wedNo4 = ? , thursNo1 = ? , thursNo2 = ? , thursNo3 = ? , thursNo4 = ? , friNo1 = ? , friNo2 = ? , friNo3 = ? , friNo4 = ? where classId = ?";
			qr.update(sql, classScheduleInfo.getSemester(), classScheduleInfo.getMonNo1(),
					classScheduleInfo.getMonNo2(), classScheduleInfo.getMonNo3(), classScheduleInfo.getMonNo4(),
					classScheduleInfo.getTueNo1(), classScheduleInfo.getTueNo2(), classScheduleInfo.getTueNo3(),
					classScheduleInfo.getTueNo4(), classScheduleInfo.getWedNo1(), classScheduleInfo.getWedNo2(),
					classScheduleInfo.getWedNo3(), classScheduleInfo.getWedNo4(), classScheduleInfo.getThursNo1(),
					classScheduleInfo.getThursNo2(), classScheduleInfo.getThursNo3(), classScheduleInfo.getThursNo4(),
					classScheduleInfo.getFriNo1(), classScheduleInfo.getFriNo2(), classScheduleInfo.getFriNo3(),
					classScheduleInfo.getFriNo4(), classScheduleInfo.getClassId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除用户信息
	 * 
	 * @param username 用户名
	 */
	public static void deleteUser(String username) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from user where userName = ?";
			qr.update(sql, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteTeacher(String teacherId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from teacher_info where id = ?";
			qr.update(sql, teacherId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteStudent(String studentId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from student_info where id = ?";
			qr.update(sql, studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteClass(String classId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from class_info where id = ?";
			qr.update(sql, classId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteCourse(String courseId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from course_info where id = ?";
			qr.update(sql, courseId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteResultInfoFromStudentId(String studentId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from result_info where id = ?";
			qr.update(sql, studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteResultInfoFromCourseId(String courseId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from result_info where couId = ?";
			qr.update(sql, courseId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteClassCourseFromCourseId(String courseId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from class_course where couId = ?";
			qr.update(sql, courseId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteStudentInfoFromClassId(String classId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from student_info where classId = ?";
			qr.update(sql, classId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteClassCourseFromClassId(String classId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from class_course where classId = ?";
			qr.update(sql, classId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteClassScheduleInfoFromClassId(String classId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from class_schedule_info where classId = ?";
			qr.update(sql, classId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteClassScheduleInfoFromClassIdAndSemester(String classId, String semester) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from class_schedule_info where classId = ? and semester = ?";
			qr.update(sql, classId, semester);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteResultInfoFromSemesterAndStudentIdAndCouId(String semester, String studentId, String couId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from result_info where semester = ? and id = ? and couId = ?";
			qr.update(sql, semester, studentId, couId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteClassCourseFromSemesterAndClassIdAndCouIdAndTeaId(String semester, String classId, String couId, String teaId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from class_course where semester = ? and classId = ? and couId = ? and teaId = ?";
			qr.update(sql, semester, classId, couId, teaId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// class_schedule_info 表操作

	/**
	 * 添加课表信息
	 * 
	 * @param csinfo 课表信息对象
	 */
	public static void insert(ClassScheduleInfo csinfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into class_schedule_info values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			qr.update(sql, csinfo.getClassId(), csinfo.getSemester(), csinfo.getMonNo1(), csinfo.getMonNo2(),
					csinfo.getMonNo3(), csinfo.getMonNo4(), csinfo.getTueNo1(), csinfo.getTueNo2(), csinfo.getTueNo3(),
					csinfo.getTueNo4(), csinfo.getWedNo1(), csinfo.getWedNo2(), csinfo.getWedNo3(), csinfo.getWedNo4(),
					csinfo.getThursNo1(), csinfo.getThursNo2(), csinfo.getThursNo3(), csinfo.getThursNo4(),
					csinfo.getFriNo1(), csinfo.getFriNo2(), csinfo.getFriNo3(), csinfo.getFriNo4());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ClassScheduleInfo classScheduleInfoBeanHandler(String classId, String semester) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_schedule_info where classId = '";
			sql = sql + classId + "' and semester = '" + semester + "'";
			ClassScheduleInfo bean;
			bean = qr.query(sql, new BeanHandler<>(ClassScheduleInfo.class));
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// class_info 表操作

	/**
	 * 添加班级信息
	 * 
	 * @param cinfo 班级信息对象
	 */
	public static void insert(ClassInfo cinfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into class_info values(?, ?, ?)";
			qr.update(sql, cinfo.getId(), cinfo.getName(), cinfo.getNumber());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ClassInfo classNameFromIdListHandler(String classId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_info where id = '" + classId + "'";
			;
			ClassInfo bean;
			bean = qr.query(sql, new BeanHandler<>(ClassInfo.class));
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ClassInfo classIdFromNameListHandler(String name) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_info where name = '" + name + "'";
			;
			ClassInfo bean;
			bean = qr.query(sql, new BeanHandler<>(ClassInfo.class));
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// student_info 表操作

	/**
	 * 添加学生信息
	 * 
	 * @param stuInfo 学生信息对象
	 */
	public static void insert(StudentInfo stuInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into student_info values(?,?,?,?,?,?,?,?,?,?,?,?)";
			qr.update(sql, stuInfo.getId(), stuInfo.getStuName(), stuInfo.getEnName(), stuInfo.getSex(),
					stuInfo.getGrade(), stuInfo.getEduSys(), stuInfo.getFaculty(), stuInfo.getMajor(),
					stuInfo.getStuMode(), stuInfo.getEducation(), stuInfo.getCampus(), stuInfo.getClassId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 学生学籍信息查询
	 * 
	 * @param userName 用户名（学生）
	 * @return map 学籍信息
	 */
	public static StudentInfo studentInfoBeanHandler(String userName) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from student_info where userName = '";
			sql = sql + userName + "'";
			StudentInfo bean;
			bean = qr.query(sql, new BeanHandler<>(StudentInfo.class));
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询指定班级已有的人数
	 * 
	 * @param classId class_id 班级编号
	 * @return 班级人数
	 */
	public static int studentScalarHandler(String classId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select count(*) from student_info where class_id = ";
			sql += classId;
			String strCount = qr.query(sql, new ScalarHandler()).toString();
			int count = TypeSwichUtils.stringToInt(strCount);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// course_info 表操作

	/**
	 * 添加课程信息
	 * 
	 * @param couInfo
	 */
	public static void insert(CourseInfo couInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into course_info values(?,?,?,?,?)";
			qr.update(sql, couInfo.getId(), couInfo.getCouName(), couInfo.getCouType(), couInfo.getCreditHour(),
					couInfo.getCouFaculty());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getCourseNameFromeId(String courseId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from course_info where id = '" + courseId + "'";
			;
			CourseInfo bean;
			bean = qr.query(sql, new BeanHandler<>(CourseInfo.class));
			String courseName = bean.getCouName();
			return courseName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// teacher_info 表操作

	/**
	 * 添加教师信息
	 * 
	 * @param teaInfo
	 */
	public static void insert(TeacherInfo teaInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into teacher_info values(?,?)";
			qr.update(sql, teaInfo.getId(), teaInfo.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getTeacherNameFromeId(String teacherId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from teacher_info where id = '" + teacherId + "'";
			TeacherInfo bean;
			bean = qr.query(sql, new BeanHandler<>(TeacherInfo.class));
			String teacherName = bean.getName();
			return teacherName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getTeacherIdFromeName(String teacherName) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from teacher_info where name = '" + teacherName + "'";
			TeacherInfo bean;
			bean = qr.query(sql, new BeanHandler<>(TeacherInfo.class));
			String teacherId = bean.getId();
			return teacherId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getStudentNameFromeId(String studentId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from student_info where id = '" + studentId + "'";
			StudentInfo bean;
			bean = qr.query(sql, new BeanHandler<>(StudentInfo.class));
			String studentName = bean.getStuName();
			return studentName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// class_course 表操作

	/**
	 * 添加班级课程表信息
	 * 
	 * @param claCourse
	 */
	public static void insert(ClassCourse claCourse) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into class_course values(?,?,?,?,?,?,?,?)";
			qr.update(sql, claCourse.getSemester(), claCourse.getClassId(), claCourse.getCouId(), claCourse.getTeaId(),
					claCourse.getExamType(), claCourse.getExamDate(), claCourse.getExamTime(), claCourse.getExamAddr());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询指定班级和学期的课程数量
	 * 
	 * @param classId  班级编号
	 * @param semester 学期
	 * @return 课程数量
	 */
	public static int courseScalarHandler(String classId, String semester) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select count(*) from class_course where id = '";
			sql = sql + classId + "' and semester = '" + semester + "'";
			String strCount = qr.query(sql, new ScalarHandler()).toString();
			int count = TypeSwichUtils.stringToInt(strCount);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static List<ClassCourse> classIdFromTeaIdBeanHandler(String teaId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select classId from class_course where teaId = '" + teaId + "'";
			List<ClassCourse> list;
			list = qr.query(sql, new BeanListHandler<>(ClassCourse.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ClassCourse> courseIdFromClassCourseBeanHandler(String teaId, String classId, String semester) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_course where teaId = '" + teaId + "' and classId = '" + classId
					+ "' and semester = '" + semester + "'";
			List<ClassCourse> list;
			list = qr.query(sql, new BeanListHandler<>(ClassCourse.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询指定班级的全部课程代码
	 * 
	 * @param classId
	 * @return
	 */
	public static List<ClassCourse> courseIdListHandler(String classId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select couId from class_course where classId = '";
			sql = sql + classId + "'";
			List<ClassCourse> list;
			list = qr.query(sql, new BeanListHandler<>(ClassCourse.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 培养计划查询
	 * 
	 * @param classId
	 * @return
	 */
	public static List<Map<String, Object>> classCourseMapListHandler(String classId) {
		try {
			List<Map<String, Object>> courseList = new ArrayList<>();
			List<ClassCourse> list = DButils.courseIdListHandler(classId);
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());

			for (ClassCourse classCourse : list) {
				String sql = "select * from course_info where id = '";
				sql = sql + classCourse.getCouId() + "'";
				Map<String, Object> map = qr.query(sql, new MapHandler());
				courseList.add(map);
			}
			return courseList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Map<String, Object>> examListHandler(String classId, String semester) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_course where classId = '" + classId + "' and semester = '" + semester
					+ "'";
			List<Map<String, Object>> list;
			list = qr.query(sql, new MapListHandler());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param classId
	 * @return List<StudentInfo>
	 */
	public static List<StudentInfo> getStudentListFromClassId(String classId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from student_info where classId = '" + classId + "'";
			List<StudentInfo> list;
			list = qr.query(sql, new BeanListHandler<>(StudentInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static CourseInfo getCourseBeanFromeName(String couName) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from course_info where couName = '" + couName + "'";
			CourseInfo bean;
			bean = qr.query(sql, new BeanHandler<>(CourseInfo.class));
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getResultFromeIdAndCouId(String stuId, String couId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from result_info where id = '" + stuId + "' and couId = '" + couId + "'";
			ResultInfo bean;
			bean = qr.query(sql, new BeanHandler<>(ResultInfo.class));
			String result = bean.getResult();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getResultFromeSemesterAndStudentIdAndCouId(String semester, String stuId, String couId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from result_info where semester = '" + semester + "' and id = '" + stuId
					+ "' and couId = '" + couId + "'";
			ResultInfo bean;
			bean = qr.query(sql, new BeanHandler<>(ResultInfo.class));
			String result = bean.getResult();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getStudentNameFromId(String studentId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from student_info where id = '" + studentId + "'";
			StudentInfo bean;
			bean = qr.query(sql, new BeanHandler<>(StudentInfo.class));
			String studentName = bean.getStuName();
			return studentName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getClassNameFromId(String classId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_info where id = '" + classId + "'";
			ClassInfo bean;
			bean = qr.query(sql, new BeanHandler<>(ClassInfo.class));
			String studentName = bean.getName();
			return studentName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ClassCourse> getClassCourseListFromClassIdAndSemester(String classId, String semester) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_course where classId = '" + classId + "' and semester = '" + semester
					+ "'";
			List<ClassCourse> list;
			list = qr.query(sql, new BeanListHandler<>(ClassCourse.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ClassCourse getClassCourseFromClassIdAndSemesterAndCouIdAndTeaId(String classId, String semester,
			String couId, String teaId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_course where classId = '" + classId + "' and semester = '" + semester
					+ "' and couId = '" + couId + "' and teaId = '" + teaId + "'";
			ClassCourse bean;
			bean = qr.query(sql, new BeanHandler<>(ClassCourse.class));
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static StudentInfo getStudentInfoFromId(String studentId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from student_info where id = '" + studentId + "'";
			StudentInfo bean;
			bean = qr.query(sql, new BeanHandler<>(StudentInfo.class));
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static CourseInfo getCourseInfoFromId(String courseId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from course_info where id = '" + courseId + "'";
			CourseInfo bean;
			bean = qr.query(sql, new BeanHandler<>(CourseInfo.class));
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ClassScheduleInfo getClassScheduleInfoFromClassIdAndSemester(String classId, String semester) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_schedule_info where classId = '" + classId + "' and semester =  '"
					+ semester + "'";
			ClassScheduleInfo bean;
			bean = qr.query(sql, new BeanHandler<>(ClassScheduleInfo.class));
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void insertResult(ResultInfo resultInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "insert into result_info values(?,?,?,?)";
			qr.update(sql, resultInfo.getSemester(), resultInfo.getId(), resultInfo.getCouId(), resultInfo.getResult());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateResult(ResultInfo resultInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "update result_info set result = ? where semester = ? and id = ? and couId = ?";
			qr.update(sql, resultInfo.getResult(), resultInfo.getSemester(), resultInfo.getId(), resultInfo.getCouId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<ResultInfo> getResultListFromId(String id, String semester) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from result_info where id = '" + id + "' and semester = '" + semester + "'";
			List<ResultInfo> list;
			list = qr.query(sql, new BeanListHandler<>(ResultInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<UserInfo> getAllUserInfoList() {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from user";
			List<UserInfo> list;
			list = qr.query(sql, new BeanListHandler<>(UserInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<TeacherInfo> getAllTeacherInfoList() {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from teacher_info";
			List<TeacherInfo> list;
			list = qr.query(sql, new BeanListHandler<>(TeacherInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<StudentInfo> getAllStudentInfoList() {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from student_info";
			List<StudentInfo> list;
			list = qr.query(sql, new BeanListHandler<>(StudentInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<ClassInfo> getAllClassInfoList() {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_info";
			List<ClassInfo> list;
			list = qr.query(sql, new BeanListHandler<>(ClassInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<CourseInfo> getAllCourseInfoList() {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from course_info";
			List<CourseInfo> list;
			list = qr.query(sql, new BeanListHandler<>(CourseInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<ClassScheduleInfo> getAllClassScheduleInfoList() {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_schedule_info";
			List<ClassScheduleInfo> list;
			list = qr.query(sql, new BeanListHandler<>(ClassScheduleInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<ClassCourse> getAllClassCourseList() {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_course";
			List<ClassCourse> list;
			list = qr.query(sql, new BeanListHandler<>(ClassCourse.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<ResultInfo> getAllResultInfoList() {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from result_info";
			List<ResultInfo> list;
			list = qr.query(sql, new BeanListHandler<>(ResultInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<UserInfo> getUserInfoFromFuzzyQuery(String userName, String roleInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from user where userName like '%" + userName + "%' and roleInfo like '%" + roleInfo
					+ "%'";
			List<UserInfo> list;
			list = qr.query(sql, new BeanListHandler<>(UserInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<TeacherInfo> getTeacherInfoFromFuzzyQuery(String teacherId, String teacherName) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from teacher_info where id like '%" + teacherId + "%' and name like '%" + teacherName
					+ "%'";
			List<TeacherInfo> list;
			list = qr.query(sql, new BeanListHandler<>(TeacherInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<StudentInfo> getStudentInfoFromFuzzyQuery(StudentInfo stuInfo) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from student_info where id like '%" + stuInfo.getId() + "%' and stuName like '%"
					+ stuInfo.getStuName() + "%' and grade like '%" + stuInfo.getGrade() + "%' and faculty like '%"
					+ stuInfo.getFaculty() + "%' and major like '%" + stuInfo.getMajor() + "%' and classId like '%"
					+ stuInfo.getClassId() + "%'";
			List<StudentInfo> list;
			list = qr.query(sql, new BeanListHandler<>(StudentInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<ClassInfo> getClassInfoFromFuzzyQuery(String classId, String className) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_info where id like '%" + classId + "%' and name like '%" + className
					+ "%'";
			List<ClassInfo> list;
			list = qr.query(sql, new BeanListHandler<>(ClassInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<CourseInfo> getCourseInfoFromFuzzyQuery(String courseId, String courseName, String courseType) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from course_info where id like '%" + courseId + "%' and couName like '%" + courseName
					+ "%' and couType like '%" + courseType + "%'";
			List<CourseInfo> list;
			list = qr.query(sql, new BeanListHandler<>(CourseInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<ClassScheduleInfo> getClassScheduleInfoFromFuzzyQuery(String classId, String semester) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_schedule_info where classId like '%" + classId + "%' and semester like '%"
					+ semester + "%'";
			List<ClassScheduleInfo> list;
			list = qr.query(sql, new BeanListHandler<>(ClassScheduleInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<ClassCourse> getClassCourseFromFuzzyQuery(String semester, String classId, String couId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from class_course where semester like '%" + semester + "%' and classId like '%"
					+ classId + "%' and couId like '%" + couId + "%'";
			List<ClassCourse> list;
			list = qr.query(sql, new BeanListHandler<>(ClassCourse.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<ResultInfo> getResultInfoFromFuzzyQuery(String semester, String studentId, String couId) {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from result_info where semester like '%" + semester + "%' and id like '%" + studentId
					+ "%' and couId like '%" + couId + "%'";
			List<ResultInfo> list;
			list = qr.query(sql, new BeanListHandler<>(ResultInfo.class));
			return list;
		} catch (SQLException e) {
		}
		return null;
	}

	public static List<Map<String, Object>> studentMapListHandler() {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "select * from student_info";
			List<Map<String, Object>> list;
			list = qr.query(sql, new MapListHandler());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}