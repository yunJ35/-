package team9.viewer.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import team9.control.utils.DButils;
import team9.control.utils.QueryUtil;
import team9.model.ClassCourse;
import team9.model.ClassScheduleInfo;
import team9.model.ResultInfo;
import team9.model.ScreenSize;
import team9.model.User;
import team9.viewer.dialog.ChangePasswordDialog;
import team9.viewer.frame.LoginFrame;

@SuppressWarnings("serial")
public class StudentFrame extends JFrame implements SwingConstants {
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menuHome, menuMyInfo, menuCourseManagement;
	private JMenuItem miHome, miSwitch, miChangePassword, miQuit, miRollInfo, miTraPlan, miClassSchedule, miExam,
			miGrade, miUserInfo;
	private JLabel lblBackground;
	private JTable tab;

	public StudentFrame() {
		super(" 教务系统-学生端");

		ImageIcon icon = new ImageIcon("lib/images/Users.png");
		this.setIconImage(icon.getImage());

		this.setSize(2 * ScreenSize.WIDTH / 3, 2 * ScreenSize.HEIGHT / 3);

		panel = new JPanel(null);

		initMenu();
		initHomeGUI();

		// 设置窗口大小不可变
		this.setResizable(false);
		// 设置窗口初始化居中
		this.setLocationRelativeTo(null);
		// 设定窗口默认关闭方式为退出程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口可视
		this.setVisible(true);
	}

	private void initMenu() {

		menuBar = new JMenuBar();

		menuHome = new JMenu(" 首页 ");
		menuMyInfo = new JMenu(" 我的信息 ");
		menuCourseManagement = new JMenu(" 课程管理 ");

		miUserInfo = new JMenuItem(DButils.getStudentNameFromeId(User.getUserName()) + "(" + User.getUserName() + ") "
				+ User.getRoleInfo());
		miHome = new JMenuItem("首页 ");
		miSwitch = new JMenuItem("切换用户 ", new ImageIcon("lib/images/Refresh.png"));
		miChangePassword = new JMenuItem("修改密码 ", new ImageIcon("lib/images/Settings.png"));
		miQuit = new JMenuItem("退出 ", new ImageIcon("lib/images/PowerOff.png"));
		miRollInfo = new JMenuItem(" 学籍信息 ");
		miTraPlan = new JMenuItem(" 培养计划 ");
		miClassSchedule = new JMenuItem(" 我的课表 ");
		miExam = new JMenuItem(" 我的考试 ");
		miGrade = new JMenuItem(" 我的成绩 ");

		miUserInfo.setForeground(Color.LIGHT_GRAY);

		menuHome.setFont(new Font("黑体", Font.PLAIN, 20));
		menuMyInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		menuCourseManagement.setFont(new Font("黑体", Font.PLAIN, 20));

		miUserInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miHome.setFont(new Font("黑体", Font.PLAIN, 20));
		miSwitch.setFont(new Font("黑体", Font.PLAIN, 20));
		miChangePassword.setFont(new Font("黑体", Font.PLAIN, 20));
		miQuit.setFont(new Font("黑体", Font.PLAIN, 20));
		miRollInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miTraPlan.setFont(new Font("黑体", Font.PLAIN, 20));
		miClassSchedule.setFont(new Font("黑体", Font.PLAIN, 20));
		miExam.setFont(new Font("黑体", Font.PLAIN, 20));
		miGrade.setFont(new Font("黑体", Font.PLAIN, 20));

		// 注册监听
		miHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initHomeGUI();
			}
		});
		miSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentFrame.this.setVisible(false);
				new LoginFrame();
			}
		});
		miChangePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordDialog(null).setVisible(true);
				if (!User.passwordVerify()) {
					StudentFrame.this.setVisible(false);
					new LoginFrame();
				}
			}

		});
		miQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "您确定要退出系统么？", "退出系统",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		miRollInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initRollInfoGUI();
			}
		});
		miTraPlan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initTraPlanGUI();
			}
		});
		miClassSchedule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initClassScheduleGUI();
			}
		});
		miExam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initExamGUI();

			}
		});
		miGrade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initGradeGUI();

			}
		});

		menuBar.add(menuHome);
		menuBar.add(menuMyInfo);
		menuBar.add(menuCourseManagement);
		menuBar.add(miUserInfo);

		menuHome.add(miHome);
		menuHome.addSeparator();

		menuHome.add(miSwitch);
		menuHome.add(miChangePassword);
		menuHome.add(miQuit);

		menuMyInfo.add(miRollInfo);
		menuMyInfo.add(miTraPlan);

		menuCourseManagement.add(miClassSchedule);
		menuCourseManagement.add(miExam);
		menuCourseManagement.add(miGrade);

		this.setJMenuBar(menuBar);
	}

	private void initHomeGUI() {

		panel.removeAll();

		ImageIcon icon = new ImageIcon("lib/images/hello.png");
		icon.setImage(icon.getImage().getScaledInstance(StudentFrame.this.getWidth(), StudentFrame.this.getHeight(),
				Image.SCALE_DEFAULT));

		lblBackground = new JLabel(icon, CENTER);

		lblBackground.setSize(StudentFrame.this.getWidth(), StudentFrame.this.getHeight());

		panel.add(lblBackground);
		panel.updateUI();

		this.add(panel);
	}

	private void initRollInfoGUI() {
		panel.removeAll();

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		p1.setLayout(new BorderLayout());
		p2.setLayout(new GridLayout(8, 4));

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, "学籍信息", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);
		p1.setBorder(titled);

		Map<String, Object> map = QueryUtil.rollInfoQuery(User.getUserName());

		JLabel[] lbl = new JLabel[8];
		for (int i = 0; i < lbl.length; i++) {
			lbl[i] = new JLabel();
		}

		JLabel lblID = new JLabel("学号：", RIGHT);
		JLabel lblIDInfo = new JLabel((String) map.get("id"));
		JLabel lblName = new JLabel("姓名：", RIGHT);
		JLabel lblNameInfo = new JLabel((String) map.get("stuName"));
		JLabel lblEnName = new JLabel("英文名：", RIGHT);
		JLabel lblEnNameInfo = new JLabel((String) map.get("enName"));
		JLabel lblSex = new JLabel("性别：", RIGHT);
		JLabel lblSexInfo = new JLabel((String) map.get("sex"));
		JLabel lblGrade = new JLabel("年级：", RIGHT);
		JLabel lblGradeInfo = new JLabel((String) map.get("grade"));
		JLabel lblEduSys = new JLabel("学制：", RIGHT);
		JLabel lblEduSysInfo = new JLabel((String) map.get("eduSys"));
		JLabel lblFaculty = new JLabel("院系：", RIGHT);
		JLabel lblFacultyInfo = new JLabel((String) map.get("faculty"));
		JLabel lblMajor = new JLabel("专业：", RIGHT);
		JLabel lblMajorInfo = new JLabel((String) map.get("major"));
		JLabel lblMode = new JLabel("学习形式：", RIGHT);
		JLabel lblModeInfo = new JLabel((String) map.get("stuMode"));
		JLabel lblEducation = new JLabel("学历层次：", RIGHT);
		JLabel lblEducationInfo = new JLabel((String) map.get("education"));
		JLabel lblCampus = new JLabel("所属校区：", RIGHT);
		JLabel lblCampusInfo = new JLabel((String) map.get("campus"));
		JLabel lblClass = new JLabel("所属班级：", RIGHT);
		JLabel lblClassInfo = new JLabel(DButils.getClassNameFromId((String) map.get("classId")));

		JLabel lblLeft = new JLabel("            ");
		JLabel lblRight = new JLabel("            ");

		lblLeft.setFont(new Font("黑体", Font.PLAIN, 50));
		lblRight.setFont(new Font("黑体", Font.PLAIN, 50));

		lblID.setFont(new Font("黑体", Font.PLAIN, 20));
		lblName.setFont(new Font("黑体", Font.PLAIN, 20));
		lblEnName.setFont(new Font("黑体", Font.PLAIN, 20));
		lblSex.setFont(new Font("黑体", Font.PLAIN, 20));
		lblGrade.setFont(new Font("黑体", Font.PLAIN, 20));
		lblEduSys.setFont(new Font("黑体", Font.PLAIN, 20));
		lblFaculty.setFont(new Font("黑体", Font.PLAIN, 20));
		lblMajor.setFont(new Font("黑体", Font.PLAIN, 20));
		lblMode.setFont(new Font("黑体", Font.PLAIN, 20));
		lblEducation.setFont(new Font("黑体", Font.PLAIN, 20));
		lblCampus.setFont(new Font("黑体", Font.PLAIN, 20));
		lblClass.setFont(new Font("黑体", Font.PLAIN, 20));
		lblIDInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNameInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblEnNameInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblSexInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblGradeInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblEduSysInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblFacultyInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblMajorInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblModeInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblEducationInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblCampusInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		lblClassInfo.setFont(new Font("黑体", Font.PLAIN, 20));

		p2.add(lbl[0]);
		p2.add(lbl[1]);
		p2.add(lbl[2]);
		p2.add(lbl[3]);
		p2.add(lblID);
		p2.add(lblIDInfo);
		p2.add(lblName);
		p2.add(lblNameInfo);
		p2.add(lblEnName);
		p2.add(lblEnNameInfo);
		p2.add(lblSex);
		p2.add(lblSexInfo);
		p2.add(lblGrade);
		p2.add(lblGradeInfo);
		p2.add(lblEduSys);
		p2.add(lblEduSysInfo);
		p2.add(lblFaculty);
		p2.add(lblFacultyInfo);
		p2.add(lblMajor);
		p2.add(lblMajorInfo);
		p2.add(lblMode);
		p2.add(lblModeInfo);
		p2.add(lblEducation);
		p2.add(lblEducationInfo);
		p2.add(lblCampus);
		p2.add(lblCampusInfo);
		p2.add(lblClass);
		p2.add(lblClassInfo);
		p2.add(lbl[4]);
		p2.add(lbl[5]);
		p2.add(lbl[6]);
		p2.add(lbl[7]);

		p1.setSize(panel.getWidth(), 2 * panel.getHeight() / 3);
		p2.setSize(p1.getWidth() / 2, p1.getHeight() / 2);

		p1.add(lblLeft, BorderLayout.WEST);
		p1.add(lblRight, BorderLayout.EAST);
		p1.add(p2, BorderLayout.CENTER);
		panel.setSize(StudentFrame.this.getWidth(), StudentFrame.this.getHeight());

		panel.add(p1);
		panel.updateUI();
		this.add(panel);
	}

	private void initTraPlanGUI() {
		panel.removeAll();

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, "培养计划", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JScrollPane sp = new JScrollPane();

		Vector<String> columnNames = new Vector<>();

		columnNames.add("课程类别");
		columnNames.add("课程代码");
		columnNames.add("课程名称");
		columnNames.add("学分");
		columnNames.add("开设院系");

		Vector<Vector<String>> tableValue = new Vector<>();

		List<Map<String, Object>> list = DButils.classCourseMapListHandler("2016010301");
		for (Map<String, Object> map : list) {
			Vector<String> rowValue = new Vector<>();
			rowValue.add((String) map.get("couType"));
			rowValue.add((String) map.get("id"));
			rowValue.add((String) map.get("couName"));
			rowValue.add((String) map.get("creditHour"));
			rowValue.add((String) map.get("couFaculty"));
			tableValue.add(rowValue);
		}

		JTable tab = new JTable(tableValue, columnNames);

		tab.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
		tab.setFont(new Font("黑体", Font.PLAIN, 20));
		tab.setRowHeight(30);
		tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sp.setViewportView(tab);
		sp.setSize(panel.getSize());
		sp.setBorder(titled);
		panel.add(sp);
		panel.updateUI();
		this.add(panel);
	}

	private void initClassScheduleGUI() {
		panel.removeAll();

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, "我的课表", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JPanel p1 = new JPanel(null);
		panel.add(p1);

		JButton btnRefresh = new JButton("刷新");

		JComboBox<String> cbSemester = new JComboBox<String>();
		cbSemester.addItem("选择学期");
		cbSemester.addItem("第一学期");
		cbSemester.addItem("第二学期");
		cbSemester.addItem("第三学期");
		cbSemester.addItem("第四学期");
		cbSemester.addItem("第五学期");
		cbSemester.addItem("第六学期");
		cbSemester.addItem("第七学期");
		cbSemester.addItem("第八学期");

		p1.add(cbSemester);

		cbSemester.setBounds(10, 10, 200, 35);
		cbSemester.setFont(new Font("宋体", Font.PLAIN, 20));

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(5, 6));
		panel.add(p2);

		Map<String, Object> map = QueryUtil.rollInfoQuery(User.getUserName());
		String classId = (String) map.get("classId");

		JButton[] button = new JButton[30];
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton();
			button[i].setFont(new Font("宋体", Font.PLAIN, 20));
		}
		button[0].setText("");
		button[1].setText("星期一");
		button[2].setText("星期二");
		button[3].setText("星期三");
		button[4].setText("星期四");
		button[5].setText("星期五");
		button[6].setText("第一节");
		button[12].setText("第二节");
		button[18].setText("第三节");
		button[24].setText("第四节");

		ClassCourse classCourse = new ClassCourse();

		cbSemester.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				classCourse.setSemester(cbSemester.getSelectedItem().toString());
			}
		});

		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ClassScheduleInfo csinfo = DButils.classScheduleInfoBeanHandler(classId, classCourse.getSemester());

					button[7].setText(csinfo.getMonNo1());
					button[8].setText(csinfo.getTueNo1());
					button[9].setText(csinfo.getWedNo1());
					button[10].setText(csinfo.getThursNo1());
					button[11].setText(csinfo.getFriNo1());

					button[13].setText(csinfo.getMonNo2());
					button[14].setText(csinfo.getTueNo2());
					button[15].setText(csinfo.getWedNo2());
					button[16].setText(csinfo.getThursNo2());
					button[17].setText(csinfo.getFriNo2());

					button[19].setText(csinfo.getMonNo3());
					button[20].setText(csinfo.getTueNo3());
					button[21].setText(csinfo.getWedNo3());
					button[22].setText(csinfo.getThursNo3());
					button[23].setText(csinfo.getFriNo3());

					button[25].setText(csinfo.getMonNo4());
					button[26].setText(csinfo.getTueNo4());
					button[27].setText(csinfo.getWedNo4());
					button[28].setText(csinfo.getThursNo4());
					button[29].setText(csinfo.getFriNo4());

					p2.updateUI();
				} catch (Exception exception) {
					button[7].setText("");
					button[8].setText("");
					button[9].setText("");
					button[10].setText("");
					button[11].setText("");

					button[13].setText("");
					button[14].setText("");
					button[15].setText("");
					button[16].setText("");
					button[17].setText("");

					button[19].setText("");
					button[20].setText("");
					button[21].setText("");
					button[22].setText("");
					button[23].setText("");

					button[25].setText("");
					button[26].setText("");
					button[27].setText("");
					button[28].setText("");
					button[29].setText("");
				}
			}
		});

		for (JButton jButton : button) {
			p2.add(jButton);
		}
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height / 10);
		p2.setBounds(0, panel.getSize().height / 10, panel.getSize().width, 9 * panel.getSize().height / 10);
		btnRefresh.setBounds(220, 10, 100, 35);
		btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));

		p1.add(btnRefresh);
		p2.setBorder(titled);
		panel.updateUI();
		this.add(panel);
	}

	private void initExamGUI() {
		panel.removeAll();

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, "我的考试", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JPanel p1 = new JPanel(null);

		JScrollPane sp = new JScrollPane();

		JButton btnRefresh = new JButton("刷新");

		ClassCourse classCourse = new ClassCourse();
		Map<String, Object> map = QueryUtil.rollInfoQuery(User.getUserName());
		String classId = (String) map.get("classId");

		JComboBox<String> cbSemester = new JComboBox<String>();
		cbSemester.addItem("选择学期");
		cbSemester.addItem("第一学期");
		cbSemester.addItem("第二学期");
		cbSemester.addItem("第三学期");
		cbSemester.addItem("第四学期");
		cbSemester.addItem("第五学期");
		cbSemester.addItem("第六学期");
		cbSemester.addItem("第七学期");
		cbSemester.addItem("第八学期");
		cbSemester.setFont(new Font("宋体", Font.PLAIN, 20));

		Vector<String> columnNames = new Vector<>();
		columnNames.add("课程代码");
		columnNames.add("课程名称");
		columnNames.add("考试类别");
		columnNames.add("考试日期");
		columnNames.add("考试时间");
		columnNames.add("考试地点");
		columnNames.add("监考老师");

		Vector<Vector<String>> tableValue = new Vector<>();

		cbSemester.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				classCourse.setSemester(cbSemester.getSelectedItem().toString());
			}
		});

		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						// list.clear();
						tableValue.clear();
						for (Map<String, Object> map : DButils.examListHandler(classId, classCourse.getSemester())) {
							Vector<String> rowValue = new Vector<>();
							rowValue.add((String) map.get("couId"));

							rowValue.add(DButils.getCourseNameFromeId((String) map.get("couId")));
							rowValue.add((String) map.get("examType"));
							rowValue.add((String) map.get("examDate"));
							rowValue.add((String) map.get("examTime"));
							rowValue.add((String) map.get("examAddr"));
							rowValue.add(DButils.getTeacherNameFromeId((String) map.get("teaId")));
							tableValue.add(rowValue);
						}
					} catch (Exception e2) {
					}
					tab = new JTable(tableValue, columnNames);
					tab.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					tab.setFont(new Font("宋体", Font.PLAIN, 20));
					tab.setRowHeight(30);
					tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp.setViewportView(tab);
					panel.updateUI();
				} catch (Exception e2) {
				} finally {
					tab.updateUI();
				}
			}
		});

		tab = new JTable(tableValue, columnNames);
		tab.setRowHeight(30);
		tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));
		tab.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
		tab.setFont(new Font("宋体", Font.PLAIN, 20));

		btnRefresh.setBounds(220, 10, 100, 35);
		cbSemester.setBounds(10, 10, 200, 35);
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height / 10);
		sp.setBounds(0, panel.getSize().height / 10, panel.getSize().width, 9 * panel.getSize().height / 10);

		sp.setViewportView(tab);
		sp.setBorder(titled);

		p1.add(btnRefresh);
		p1.add(cbSemester);
		panel.add(p1);
		panel.add(sp);
		panel.updateUI();
		this.add(panel);
	}

	private void initGradeGUI() {
		panel.removeAll();

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, "我的成绩", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JPanel p1 = new JPanel(null);
		JScrollPane sp = new JScrollPane();
		JButton btnRefresh = new JButton("刷新");

		Vector<String> columnNames = new Vector<>();
		columnNames.add("序号");
		columnNames.add("学期");
		columnNames.add("课程代码");
		columnNames.add("课程名称");
		columnNames.add("课程类别");
		columnNames.add("学分");
		columnNames.add("成绩");

		ClassCourse classCourse = new ClassCourse();

		Vector<Vector<Object>> tableValue = new Vector<>();

		JComboBox<String> cbSemester = new JComboBox<String>();
		cbSemester.addItem("选择学期");
		cbSemester.addItem("第一学期");
		cbSemester.addItem("第二学期");
		cbSemester.addItem("第三学期");
		cbSemester.addItem("第四学期");
		cbSemester.addItem("第五学期");
		cbSemester.addItem("第六学期");
		cbSemester.addItem("第七学期");
		cbSemester.addItem("第八学期");

		cbSemester.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				classCourse.setSemester(cbSemester.getSelectedItem().toString());
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tableValue.clear();
					int i = 1;
					for (ResultInfo resultInfo : DButils.getResultListFromId(User.getUserName(),
							classCourse.getSemester())) {
						try {
							Vector<Object> rowValue = new Vector<>();
							rowValue.add(i);
							rowValue.add(classCourse.getSemester());
							rowValue.add(resultInfo.getCouId());
							rowValue.add(DButils.getCourseNameFromeId(resultInfo.getCouId()));

							rowValue.add(
									DButils.getCourseBeanFromeName(DButils.getCourseNameFromeId(resultInfo.getCouId()))
											.getCouType());
							rowValue.add(
									DButils.getCourseBeanFromeName(DButils.getCourseNameFromeId(resultInfo.getCouId()))
											.getCreditHour());
							rowValue.add(resultInfo.getResult());
							tableValue.add(rowValue);
							i++;
						} catch (Exception e2) {
						}
					}
					tab = new JTable(tableValue, columnNames);
					tab.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					tab.setFont(new Font("宋体", Font.PLAIN, 20));
					tab.setRowHeight(30);
					tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp.setViewportView(tab);
					panel.updateUI();
				} catch (Exception e2) {
				} finally {
					tab.updateUI();
				}
			}
		});

		tab = new JTable(tableValue, columnNames);
		tab.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
		tab.setFont(new Font("宋体", Font.PLAIN, 20));
		tab.setRowHeight(30);
		tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sp.setViewportView(tab);

		btnRefresh.setBounds(220, 10, 100, 35);
		btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));
		cbSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		p1.add(cbSemester);
		p1.add(btnRefresh);
		panel.add(p1);
		sp.setBorder(titled);
		cbSemester.setBounds(10, 10, 200, 35);
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height / 10);
		sp.setBounds(0, panel.getSize().height / 10, panel.getSize().width, 9 * panel.getSize().height / 10);
		panel.add(sp);
		panel.updateUI();
		this.add(panel);
	}
}