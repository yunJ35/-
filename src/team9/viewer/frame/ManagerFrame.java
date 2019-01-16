package team9.viewer.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import team9.control.utils.DButils;
import team9.model.ClassCourse;
import team9.model.ClassInfo;
import team9.model.CourseInfo;
import team9.model.ResultInfo;
import team9.model.ScreenSize;
import team9.model.StudentInfo;
import team9.model.TeacherInfo;
import team9.model.User;
import team9.model.UserInfo;
import team9.viewer.dialog.AlterClassInfoDialog;
import team9.viewer.dialog.AlterClassScheduleInfoDialog;
import team9.viewer.dialog.AlterCourseInfoDialog;
import team9.viewer.dialog.AlterExamInfoDialog;
import team9.viewer.dialog.AlterResultInfoDialog;
import team9.viewer.dialog.AlterStudentInfoDialog;
import team9.viewer.dialog.AlterTeacherInfoDialog;
import team9.viewer.dialog.ChangePasswordDialog;
import team9.viewer.dialog.DeletClassDialog;
import team9.viewer.dialog.DeletClassScheduleDialog;
import team9.viewer.dialog.DeletCourseDialog;
import team9.viewer.dialog.DeletExamDialog;
import team9.viewer.dialog.DeletStudentDialog;
import team9.viewer.dialog.DeletTeacherDialog;
import team9.viewer.dialog.DeletUserDialog;
import team9.viewer.dialog.DeleteResultDialog;
import team9.viewer.dialog.EntryClassInfoDialog;
import team9.viewer.dialog.EntryClassScheduleInfoDialog;
import team9.viewer.dialog.EntryCourseInfoDialog;
import team9.viewer.dialog.EntryExamInfoDialog;
import team9.viewer.dialog.EntryResultInfoDialog;
import team9.viewer.dialog.EntryStudentInfoDialog;
import team9.viewer.dialog.EntryTeacherInfoDialog;
import team9.viewer.dialog.EntryUserInfoDialog;
import team9.viewer.dialog.ResetPasswordDialog;
import team9.viewer.dialog.ShowClassScheduleDialog;

@SuppressWarnings("serial")
public class ManagerFrame extends JFrame implements SwingConstants {
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menuHome, menuDatabases;
	private JMenuItem miHome, miSwitch, miChangePassword, miQuit, miUserInfo, miUsersInfo, miTeacherInfo, miStudentInfo,
			miCourseInfo, miClassInfo, miClassScheduleInfo, miResultInfo, miExamInfo;
	private JLabel lblBackground;
	private JTable table;

	public ManagerFrame() {
		super(" 教务系统-管理员端");

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
		menuDatabases = new JMenu(" 数据库管理 ");

		miUserInfo = new JMenuItem(User.getUserName() + User.getRoleInfo());

		miHome = new JMenuItem("首页 ");
		miSwitch = new JMenuItem("切换用户 ", new ImageIcon("lib/images/Refresh.png"));
		miChangePassword = new JMenuItem("修改密码 ", new ImageIcon("lib/images/Settings.png"));
		miQuit = new JMenuItem("退出 ", new ImageIcon("lib/images/PowerOff.png"));

		miUsersInfo = new JMenuItem(" 用户信息管理 ");
		miTeacherInfo = new JMenuItem(" 教师信息管理 ");
		miStudentInfo = new JMenuItem(" 学生信息管理 ");
		miClassInfo = new JMenuItem(" 班级信息管理 ");
		miCourseInfo = new JMenuItem(" 课程信息管理 ");
		miClassScheduleInfo = new JMenuItem(" 课表信息管理 ");
		miExamInfo = new JMenuItem(" 考试信息管理 ");
		miResultInfo = new JMenuItem(" 成绩信息管理 ");

		miUserInfo.setForeground(Color.LIGHT_GRAY);

		menuHome.setFont(new Font("黑体", Font.PLAIN, 20));
		menuDatabases.setFont(new Font("黑体", Font.PLAIN, 20));

		miUserInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miHome.setFont(new Font("黑体", Font.PLAIN, 20));
		miSwitch.setFont(new Font("黑体", Font.PLAIN, 20));
		miChangePassword.setFont(new Font("黑体", Font.PLAIN, 20));
		miQuit.setFont(new Font("黑体", Font.PLAIN, 20));
		miUsersInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miTeacherInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miStudentInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miClassInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miCourseInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miClassScheduleInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miExamInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miResultInfo.setFont(new Font("黑体", Font.PLAIN, 20));

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
				ManagerFrame.this.setVisible(false);
				new LoginFrame();
			}
		});
		miChangePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordDialog(null).setVisible(true);
				if (!User.passwordVerify()) {
					ManagerFrame.this.setVisible(false);
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
		miUsersInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initUsersInfoGUI();
			}
		});
		miTeacherInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initTeacherInfoGUI();
			}
		});
		miStudentInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initStudentInfoGUI();
			}
		});
		miClassInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initClassInfoGUI();
			}
		});
		miCourseInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initCourseInfoGUI();
			}
		});
		miClassScheduleInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initClassScheduleInfoGUI();
			}
		});
		miExamInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initExamInfoGUI();
			}
		});
		miResultInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initResultInfoGUI();
			}
		});

		menuBar.add(menuHome);
		menuBar.add(menuDatabases);
		menuBar.add(miUserInfo);

		menuHome.add(miHome);
		menuHome.addSeparator();
		menuHome.add(miSwitch);
		menuHome.add(miChangePassword);
		menuHome.add(miQuit);

		menuDatabases.add(miUsersInfo);
		menuDatabases.addSeparator();
		menuDatabases.add(miTeacherInfo);
		menuDatabases.add(miStudentInfo);
		menuDatabases.addSeparator();
		menuDatabases.add(miClassInfo);
		menuDatabases.add(miCourseInfo);
		menuDatabases.add(miClassScheduleInfo);
		menuDatabases.addSeparator();
		menuDatabases.add(miExamInfo);
		menuDatabases.add(miResultInfo);

		this.setJMenuBar(menuBar);
	}

	private void initHomeGUI() {

		panel.removeAll();

		ImageIcon icon = new ImageIcon("lib/images/hello.png");
		icon.setImage(icon.getImage().getScaledInstance(ManagerFrame.this.getWidth(), ManagerFrame.this.getHeight(),
				Image.SCALE_DEFAULT));

		lblBackground = new JLabel(icon, CENTER);

		lblBackground.setSize(ManagerFrame.this.getWidth(), ManagerFrame.this.getHeight());

		panel.add(lblBackground);
		panel.updateUI();

		this.add(panel);
	}

	private void initUsersInfoGUI() {
		panel.removeAll();

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height);

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, " 用户信息 ", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JScrollPane sp = new JScrollPane();

		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("宋体", Font.PLAIN, 20));

		JButton btnShow = new JButton("  查看用户表  ");
		JButton btnEntry = new JButton("  添加用户  ");
		JButton btnReset = new JButton("  重置密码  ");
		JButton btnDelet = new JButton("  删除用户  ");

		btnShow.setFont(new Font("宋体", Font.PLAIN, 15));
		btnEntry.setFont(new Font("宋体", Font.PLAIN, 15));
		btnReset.setFont(new Font("宋体", Font.PLAIN, 15));
		btnDelet.setFont(new Font("宋体", Font.PLAIN, 15));

		JPanel p2 = new JPanel(null);
		p1.add(p2, BorderLayout.CENTER);

		Vector<String> columnNames = new Vector<>();
		columnNames.add("序号");
		columnNames.add("用户名");
		columnNames.add("加密的密码");
		columnNames.add("用户属性");
		Vector<Vector<Object>> tableValue = new Vector<>();

		btnShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p2.removeAll();

				JLabel lblUserName = new JLabel("用户名:");
				JLabel lblRoleInfo = new JLabel("用户角色:");

				JTextField txtUserName = new JTextField(20);
				JTextField txtRoleInfo = new JTextField(10);

				JButton btnQuery = new JButton(" 查询 ");
				JButton btnRefresh = new JButton(" 刷新 ");
				tableShow();

				btnQuery.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String userName = txtUserName.getText();
						String roleInfo = txtRoleInfo.getText();
						try {
							tableValue.clear();
							int n = 1;
							for (UserInfo userInfo : DButils.getUserInfoFromFuzzyQuery(userName, roleInfo)) {
								try {
									Vector<Object> rowValue = new Vector<>();
									rowValue.add(n);
									rowValue.add(userInfo.getUserName());
									rowValue.add(userInfo.getPassword());
									rowValue.add(userInfo.getRoleInfo());
									tableValue.add(rowValue);
									n++;
								} catch (Exception e2) {
								}
							}
							table = new JTable(tableValue, columnNames);

							table.getColumnModel().getColumn(0).setPreferredWidth(getSize().width / 10);
							table.getColumnModel().getColumn(1).setPreferredWidth(2 * getSize().width / 10);
							table.getColumnModel().getColumn(2).setPreferredWidth(5 * getSize().width / 10);
							table.getColumnModel().getColumn(3).setPreferredWidth(2 * getSize().width / 10);

							table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
							table.setFont(new Font("宋体", Font.PLAIN, 20));
							table.setRowHeight(30);
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							sp.setViewportView(table);
							panel.updateUI();
						} catch (Exception e2) {
						} finally {
							table.updateUI();
						}
					}
				});

				btnRefresh.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						txtUserName.setText("");
						txtRoleInfo.setText("");
						tableShow();
					}
				});

				lblUserName.setFont(new Font("宋体", Font.PLAIN, 20));
				lblRoleInfo.setFont(new Font("宋体", Font.PLAIN, 20));
				txtUserName.setFont(new Font("宋体", Font.PLAIN, 20));
				txtRoleInfo.setFont(new Font("宋体", Font.PLAIN, 20));
				btnQuery.setFont(new Font("宋体", Font.PLAIN, 20));
				btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));

				lblUserName.setBounds(20, 10, 100, 35);
				txtUserName.setBounds(120, 10, 100, 35);
				lblRoleInfo.setBounds(230, 10, 100, 35);
				txtRoleInfo.setBounds(330, 10, 100, 35);
				btnQuery.setBounds(440, 10, 100, 35);
				btnRefresh.setBounds(550, 10, 100, 35);

				p2.setBounds(0, toolBar.getSize().height, p1.getSize().width,
						p1.getSize().height - toolBar.getSize().height);
				sp.setBorder(titled);
				sp.setBounds(0, 50, p2.getSize().width, p2.getSize().height - 50);
				p2.add(sp);
				p2.add(lblUserName);
				p2.add(txtUserName);
				p2.add(lblRoleInfo);
				p2.add(txtRoleInfo);
				p2.add(btnQuery);
				p2.add(btnRefresh);
				p2.updateUI();
			}

			private void tableShow() {
				try {
					tableValue.clear();
					int n = 1;
					for (UserInfo userInfo : DButils.getAllUserInfoList()) {
						try {
							Vector<Object> rowValue = new Vector<>();
							rowValue.add(n);
							rowValue.add(userInfo.getUserName());
							rowValue.add(userInfo.getPassword());
							rowValue.add(userInfo.getRoleInfo());
							tableValue.add(rowValue);
							n++;
						} catch (Exception e2) {
						}
					}
					table = new JTable(tableValue, columnNames);

					table.getColumnModel().getColumn(0).setPreferredWidth(getSize().width / 10);
					table.getColumnModel().getColumn(1).setPreferredWidth(2 * getSize().width / 10);
					table.getColumnModel().getColumn(2).setPreferredWidth(5 * getSize().width / 10);
					table.getColumnModel().getColumn(3).setPreferredWidth(2 * getSize().width / 10);

					table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					table.setFont(new Font("宋体", Font.PLAIN, 20));
					table.setRowHeight(30);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp.setViewportView(table);
					panel.updateUI();
				} catch (Exception e2) {
				} finally {
					table.updateUI();
				}
			}
		});

		btnEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EntryUserInfoDialog(null).setVisible(true);
			}
		});

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResetPasswordDialog(null).setVisible(true);
			}
		});

		btnDelet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DeletUserDialog(null).setVisible(true);
			}
		});

		p2.add(sp);
		p2.updateUI();
		toolBar.add(btnShow);
		toolBar.add(btnEntry);
		toolBar.add(btnReset);
		toolBar.add(btnDelet);
		p1.add(toolBar, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		panel.add(p1);
		panel.updateUI();
		this.add(panel);
	}

	private void initTeacherInfoGUI() {
		panel.removeAll();

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height);

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, " 教师信息 ", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JScrollPane sp = new JScrollPane();

		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("宋体", Font.PLAIN, 20));

		JButton btnShow = new JButton("  查看教师信息表  ");
		JButton btnEntry = new JButton("  添加教师信息  ");
		JButton btnAlter = new JButton("  修改教师信息  ");
		JButton btnDelet = new JButton("  删除教师信息  ");

		btnShow.setFont(new Font("宋体", Font.PLAIN, 15));
		btnEntry.setFont(new Font("宋体", Font.PLAIN, 15));
		btnAlter.setFont(new Font("宋体", Font.PLAIN, 15));
		btnDelet.setFont(new Font("宋体", Font.PLAIN, 15));

		JPanel p2 = new JPanel(null);
		p1.add(p2, BorderLayout.CENTER);

		Vector<String> columnNames = new Vector<>();
		columnNames.add("序号");
		columnNames.add("工号");
		columnNames.add("姓名");
		Vector<Vector<Object>> tableValue = new Vector<>();

		btnShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p2.removeAll();

				JLabel lblTeacherId = new JLabel("工号:", RIGHT);
				JLabel lblTeacherName = new JLabel("姓名:", RIGHT);

				JTextField txtTeacherId = new JTextField(20);
				JTextField txtTeacherName = new JTextField(10);

				JButton btnQuery = new JButton(" 查询 ");
				JButton btnRefresh = new JButton(" 刷新 ");
				tableShow();

				btnQuery.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String teacherId = txtTeacherId.getText();
						String teacherName = txtTeacherName.getText();
						try {
							tableValue.clear();
							int n = 1;
							for (TeacherInfo teacherInfo : DButils.getTeacherInfoFromFuzzyQuery(teacherId,
									teacherName)) {
								try {
									Vector<Object> rowValue = new Vector<>();
									rowValue.add(n);
									rowValue.add(teacherInfo.getId());
									rowValue.add(teacherInfo.getName());
									tableValue.add(rowValue);
									n++;
								} catch (Exception e2) {
								}
							}
							table = new JTable(tableValue, columnNames);

							table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
							table.setFont(new Font("宋体", Font.PLAIN, 20));
							table.setRowHeight(30);
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							sp.setViewportView(table);
							panel.updateUI();
						} catch (Exception e2) {
						} finally {
							table.updateUI();
						}
					}
				});

				btnRefresh.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						txtTeacherId.setText("");
						txtTeacherName.setText("");
						tableShow();
					}
				});

				lblTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
				lblTeacherName.setFont(new Font("宋体", Font.PLAIN, 20));
				txtTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
				txtTeacherName.setFont(new Font("宋体", Font.PLAIN, 20));
				btnQuery.setFont(new Font("宋体", Font.PLAIN, 20));
				btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));

				lblTeacherId.setBounds(10, 10, 70, 35);
				txtTeacherId.setBounds(80, 10, 100, 35);
				lblTeacherName.setBounds(190, 10, 70, 35);
				txtTeacherName.setBounds(260, 10, 100, 35);
				btnQuery.setBounds(370, 10, 100, 35);
				btnRefresh.setBounds(480, 10, 100, 35);

				p2.setBounds(0, toolBar.getSize().height, p1.getSize().width,
						p1.getSize().height - toolBar.getSize().height);
				sp.setBorder(titled);
				sp.setBounds(0, 50, p2.getSize().width, p2.getSize().height - 50);
				p2.add(sp);
				p2.add(lblTeacherId);
				p2.add(lblTeacherName);
				p2.add(txtTeacherId);
				p2.add(txtTeacherName);
				p2.add(btnQuery);
				p2.add(btnRefresh);
				p2.updateUI();
			}

			private void tableShow() {
				try {
					tableValue.clear();
					int n = 1;
					for (TeacherInfo teacherInfo : DButils.getAllTeacherInfoList()) {
						try {
							Vector<Object> rowValue = new Vector<>();
							rowValue.add(n);
							rowValue.add(teacherInfo.getId());
							rowValue.add(teacherInfo.getName());
							tableValue.add(rowValue);
							n++;
						} catch (Exception e2) {
						}
					}
					table = new JTable(tableValue, columnNames);

					table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					table.setFont(new Font("宋体", Font.PLAIN, 20));
					table.setRowHeight(30);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp.setViewportView(table);
					panel.updateUI();
				} catch (Exception e2) {
				} finally {
					table.updateUI();
				}
			}
		});

		btnEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EntryTeacherInfoDialog(null).setVisible(true);
			}
		});

		btnAlter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AlterTeacherInfoDialog(null).setVisible(true);
			}
		});

		btnDelet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeletTeacherDialog(null).setVisible(true);
			}
		});

		p2.add(sp);
		p2.updateUI();
		toolBar.add(btnShow);
		toolBar.add(btnEntry);
		toolBar.add(btnAlter);
		toolBar.add(btnDelet);
		p1.add(toolBar, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		panel.add(p1);
		panel.updateUI();
		this.add(panel);
	}

	private void initStudentInfoGUI() {
		panel.removeAll();

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height);

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, " 学生信息 ", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JScrollPane sp = new JScrollPane();

		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("宋体", Font.PLAIN, 20));

		JButton btnShow = new JButton("  查看学生信息表  ");
		JButton btnEntry = new JButton("  添加学生信息  ");
		JButton btnAlter = new JButton("  修改学生信息  ");
		JButton btnDelet = new JButton("  删除学生信息  ");

		btnShow.setFont(new Font("宋体", Font.PLAIN, 15));
		btnEntry.setFont(new Font("宋体", Font.PLAIN, 15));
		btnAlter.setFont(new Font("宋体", Font.PLAIN, 15));
		btnDelet.setFont(new Font("宋体", Font.PLAIN, 15));

		JPanel p2 = new JPanel(null);
		p1.add(p2, BorderLayout.CENTER);

		Vector<String> columnNames = new Vector<>();
		columnNames.add("序号");
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("英文名");
		columnNames.add("性别");
		columnNames.add("年级");
		columnNames.add("学制");
		columnNames.add("院系");
		columnNames.add("专业");
		columnNames.add("学习形式");
		columnNames.add("学历层次");
		columnNames.add("所属校区");
		columnNames.add("所属班级");
		Vector<Vector<Object>> tableValue = new Vector<>();

		btnShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p2.removeAll();

				JLabel lblId = new JLabel("学号:", RIGHT);
				JLabel lblStuName = new JLabel("姓名:", RIGHT);
				JLabel lblGrade = new JLabel("年级:", RIGHT);
				JLabel lblFaculty = new JLabel("院系:", RIGHT);
				JLabel lblMajor = new JLabel("专业:", RIGHT);
				JLabel lblClassId = new JLabel("班级:", RIGHT);

				JTextField txtId = new JTextField(20);
				JTextField txtStuName = new JTextField(10);
				JTextField txtGrade = new JTextField(10);
				JTextField txtFaculty = new JTextField(10);
				JTextField txtMajor = new JTextField(10);
				JTextField txtClassId = new JTextField(10);

				JButton btnQuery = new JButton(" 查询 ");
				JButton btnRefresh = new JButton(" 刷新 ");
				tableShow();

				btnQuery.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						StudentInfo stuInfo = new StudentInfo();
						stuInfo.setId(txtId.getText());
						stuInfo.setStuName(txtStuName.getText());
						stuInfo.setGrade(txtGrade.getText());
						stuInfo.setFaculty(txtFaculty.getText());
						stuInfo.setMajor(txtMajor.getText());
						stuInfo.setClassId(txtClassId.getText());
						try {
							tableValue.clear();
							int n = 1;
							for (StudentInfo studentInfo : DButils.getStudentInfoFromFuzzyQuery(stuInfo)) {
								try {
									Vector<Object> rowValue = new Vector<>();
									rowValue.add(n);
									rowValue.add(studentInfo.getId());
									rowValue.add(studentInfo.getStuName());
									rowValue.add(studentInfo.getEnName());
									rowValue.add(studentInfo.getSex());
									rowValue.add(studentInfo.getGrade());
									rowValue.add(studentInfo.getEduSys());
									rowValue.add(studentInfo.getFaculty());
									rowValue.add(studentInfo.getMajor());
									rowValue.add(studentInfo.getStuMode());
									rowValue.add(studentInfo.getEducation());
									rowValue.add(studentInfo.getCampus());
									rowValue.add(studentInfo.getClassId());
									tableValue.add(rowValue);
									n++;
								} catch (Exception e2) {
								}
							}
							table = new JTable(tableValue, columnNames);

							table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
							table.setFont(new Font("宋体", Font.PLAIN, 20));
							table.setRowHeight(30);
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							sp.setViewportView(table);
							panel.updateUI();
						} catch (Exception e2) {
						} finally {
							table.updateUI();
						}
					}
				});

				btnRefresh.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						txtId.setText("");
						txtStuName.setText("");
						txtGrade.setText("");
						txtFaculty.setText("");
						txtMajor.setText("");
						txtClassId.setText("");
						tableShow();
					}
				});

				lblId.setFont(new Font("宋体", Font.PLAIN, 20));
				lblStuName.setFont(new Font("宋体", Font.PLAIN, 20));
				lblGrade.setFont(new Font("宋体", Font.PLAIN, 20));
				lblFaculty.setFont(new Font("宋体", Font.PLAIN, 20));
				lblMajor.setFont(new Font("宋体", Font.PLAIN, 20));
				lblClassId.setFont(new Font("宋体", Font.PLAIN, 20));
				txtId.setFont(new Font("宋体", Font.PLAIN, 20));
				txtStuName.setFont(new Font("宋体", Font.PLAIN, 20));
				txtGrade.setFont(new Font("宋体", Font.PLAIN, 20));
				txtFaculty.setFont(new Font("宋体", Font.PLAIN, 20));
				txtMajor.setFont(new Font("宋体", Font.PLAIN, 20));
				txtClassId.setFont(new Font("宋体", Font.PLAIN, 20));
				btnQuery.setFont(new Font("宋体", Font.PLAIN, 20));
				btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));

				lblId.setBounds(10, 10, 70, 35);
				txtId.setBounds(80, 10, 100, 35);
				lblStuName.setBounds(190, 10, 70, 35);
				txtStuName.setBounds(260, 10, 100, 35);
				lblGrade.setBounds(370, 10, 70, 35);
				txtGrade.setBounds(440, 10, 100, 35);
				lblFaculty.setBounds(10, 50, 70, 35);
				txtFaculty.setBounds(80, 50, 100, 35);
				lblMajor.setBounds(190, 50, 70, 35);
				txtMajor.setBounds(260, 50, 100, 35);
				lblClassId.setBounds(370, 50, 70, 35);
				txtClassId.setBounds(440, 50, 100, 35);
				btnQuery.setBounds(550, 30, 100, 35);
				btnRefresh.setBounds(660, 30, 100, 35);

				p2.setBounds(0, toolBar.getSize().height, p1.getSize().width,
						p1.getSize().height - toolBar.getSize().height);
				sp.setBorder(titled);
				sp.setBounds(0, 100, p2.getSize().width, p2.getSize().height - 100);
				p2.add(sp);
				p2.add(lblId);
				p2.add(lblStuName);
				p2.add(lblGrade);
				p2.add(lblFaculty);
				p2.add(lblMajor);
				p2.add(lblClassId);
				p2.add(txtId);
				p2.add(txtStuName);
				p2.add(txtGrade);
				p2.add(txtFaculty);
				p2.add(txtMajor);
				p2.add(txtClassId);
				p2.add(btnQuery);
				p2.add(btnRefresh);
				p2.updateUI();
			}

			private void tableShow() {
				try {
					tableValue.clear();
					int n = 1;
					for (StudentInfo studentInfo : DButils.getAllStudentInfoList()) {
						try {
							Vector<Object> rowValue = new Vector<>();
							rowValue.add(n);
							rowValue.add(studentInfo.getId());
							rowValue.add(studentInfo.getStuName());
							rowValue.add(studentInfo.getEnName());
							rowValue.add(studentInfo.getSex());
							rowValue.add(studentInfo.getGrade());
							rowValue.add(studentInfo.getEduSys());
							rowValue.add(studentInfo.getFaculty());
							rowValue.add(studentInfo.getMajor());
							rowValue.add(studentInfo.getStuMode());
							rowValue.add(studentInfo.getEducation());
							rowValue.add(studentInfo.getCampus());
							rowValue.add(studentInfo.getClassId());

							tableValue.add(rowValue);
							n++;
						} catch (Exception e2) {
						}
					}
					table = new JTable(tableValue, columnNames);

					table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					table.setFont(new Font("宋体", Font.PLAIN, 20));
					table.setRowHeight(30);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp.setViewportView(table);
					panel.updateUI();
				} catch (Exception e2) {
				} finally {
					table.updateUI();
				}
			}
		});

		btnEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EntryStudentInfoDialog(null).setVisible(true);
			}
		});

		btnAlter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AlterStudentInfoDialog(null).setVisible(true);
			}
		});

		btnDelet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeletStudentDialog(null).setVisible(true);
			}
		});

		p2.add(sp);
		p2.updateUI();
		toolBar.add(btnShow);
		toolBar.add(btnEntry);
		toolBar.add(btnAlter);
		toolBar.add(btnDelet);
		p1.add(toolBar, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		panel.add(p1);
		panel.updateUI();
		this.add(panel);
	}

	private void initClassInfoGUI() {
		panel.removeAll();

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height);

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, " 班级信息 ", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JScrollPane sp = new JScrollPane();

		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("宋体", Font.PLAIN, 20));

		JButton btnShow = new JButton("  查看班级信息表  ");
		JButton btnEntry = new JButton("  添加班级信息  ");
		JButton btnAlter = new JButton("  修改班级信息  ");
		JButton btnDelet = new JButton("  删除班级信息  ");

		btnShow.setFont(new Font("宋体", Font.PLAIN, 15));
		btnEntry.setFont(new Font("宋体", Font.PLAIN, 15));
		btnAlter.setFont(new Font("宋体", Font.PLAIN, 15));
		btnDelet.setFont(new Font("宋体", Font.PLAIN, 15));

		JPanel p2 = new JPanel(null);
		p1.add(p2, BorderLayout.CENTER);

		Vector<String> columnNames = new Vector<>();
		columnNames.add("序号");
		columnNames.add("班级编号");
		columnNames.add("班级名称");
		columnNames.add("班级人数");
		Vector<Vector<Object>> tableValue = new Vector<>();

		btnShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p2.removeAll();

				JLabel lblClassId = new JLabel("编号:", RIGHT);
				JLabel lblClassName = new JLabel("名称:", RIGHT);

				JTextField txtClassId = new JTextField(20);
				JTextField txtClassName = new JTextField(10);

				JButton btnQuery = new JButton(" 查询 ");
				JButton btnRefresh = new JButton(" 刷新 ");
				tableShow();

				btnQuery.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String classId = txtClassId.getText();
						String className = txtClassName.getText();
						try {
							tableValue.clear();
							int n = 1;
							for (ClassInfo classInfo : DButils.getClassInfoFromFuzzyQuery(classId, className)) {
								try {
									Vector<Object> rowValue = new Vector<>();
									rowValue.add(n);
									rowValue.add(classInfo.getId());
									rowValue.add(classInfo.getName());
									rowValue.add(classInfo.getNumber());
									tableValue.add(rowValue);
									n++;
								} catch (Exception e2) {
								}
							}
							table = new JTable(tableValue, columnNames);

							table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
							table.setFont(new Font("宋体", Font.PLAIN, 20));
							table.setRowHeight(30);
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							sp.setViewportView(table);
							panel.updateUI();
						} catch (Exception e2) {
						} finally {
							table.updateUI();
						}
					}
				});

				btnRefresh.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						txtClassId.setText("");
						txtClassName.setText("");
						tableShow();
					}
				});

				lblClassId.setFont(new Font("宋体", Font.PLAIN, 20));
				lblClassName.setFont(new Font("宋体", Font.PLAIN, 20));
				txtClassId.setFont(new Font("宋体", Font.PLAIN, 20));
				txtClassName.setFont(new Font("宋体", Font.PLAIN, 20));
				btnQuery.setFont(new Font("宋体", Font.PLAIN, 20));
				btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));

				lblClassId.setBounds(10, 10, 70, 35);
				txtClassId.setBounds(80, 10, 100, 35);
				lblClassName.setBounds(190, 10, 70, 35);
				txtClassName.setBounds(260, 10, 100, 35);
				btnQuery.setBounds(370, 10, 100, 35);
				btnRefresh.setBounds(480, 10, 100, 35);

				p2.setBounds(0, toolBar.getSize().height, p1.getSize().width,
						p1.getSize().height - toolBar.getSize().height);
				sp.setBorder(titled);
				sp.setBounds(0, 50, p2.getSize().width, p2.getSize().height - 50);
				p2.add(sp);
				p2.add(lblClassId);
				p2.add(lblClassName);
				p2.add(txtClassId);
				p2.add(txtClassName);
				p2.add(btnQuery);
				p2.add(btnRefresh);
				p2.updateUI();
			}

			private void tableShow() {
				try {
					tableValue.clear();
					int n = 1;
					for (ClassInfo classInfo : DButils.getAllClassInfoList()) {
						try {
							Vector<Object> rowValue = new Vector<>();
							rowValue.add(n);
							rowValue.add(classInfo.getId());
							rowValue.add(classInfo.getName());
							rowValue.add(classInfo.getNumber());
							tableValue.add(rowValue);
							n++;
						} catch (Exception e2) {
						}
					}
					table = new JTable(tableValue, columnNames);

					table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					table.setFont(new Font("宋体", Font.PLAIN, 20));
					table.setRowHeight(30);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp.setViewportView(table);
					panel.updateUI();
				} catch (Exception e2) {
				} finally {
					table.updateUI();
				}
			}
		});

		btnEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EntryClassInfoDialog(null).setVisible(true);
			}
		});

		btnAlter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AlterClassInfoDialog(null).setVisible(true);
			}
		});

		btnDelet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeletClassDialog(null).setVisible(true);
			}
		});

		p2.add(sp);
		p2.updateUI();
		toolBar.add(btnShow);
		toolBar.add(btnEntry);
		toolBar.add(btnAlter);
		toolBar.add(btnDelet);
		p1.add(toolBar, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		panel.add(p1);
		panel.updateUI();
		this.add(panel);
	}

	private void initCourseInfoGUI() {
		panel.removeAll();

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height);

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, " 课程信息 ", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JScrollPane sp = new JScrollPane();

		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("宋体", Font.PLAIN, 20));

		JButton btnShow = new JButton("  查看课程信息表  ");
		JButton btnEntry = new JButton("  添加课程信息  ");
		JButton btnAlter = new JButton("  修改课程信息  ");
		JButton btnDelet = new JButton("  删除课程信息  ");

		btnShow.setFont(new Font("宋体", Font.PLAIN, 15));
		btnEntry.setFont(new Font("宋体", Font.PLAIN, 15));
		btnAlter.setFont(new Font("宋体", Font.PLAIN, 15));
		btnDelet.setFont(new Font("宋体", Font.PLAIN, 15));

		JPanel p2 = new JPanel(null);
		p1.add(p2, BorderLayout.CENTER);

		Vector<String> columnNames = new Vector<>();
		columnNames.add("序号");
		columnNames.add("课程代码");
		columnNames.add("课程名称");
		columnNames.add("课程类别");
		columnNames.add("学分");
		columnNames.add("开课院系");
		Vector<Vector<Object>> tableValue = new Vector<>();

		btnShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p2.removeAll();

				JLabel lblCourseId = new JLabel("课程id:", RIGHT);
				JLabel lblCourseName = new JLabel("名称:", RIGHT);
				JLabel lblCourseType = new JLabel("类别:", RIGHT);

				JTextField txtCourseId = new JTextField(20);
				JTextField txtCourseName = new JTextField(10);
				JTextField txtCourseType = new JTextField(10);

				JButton btnQuery = new JButton(" 查询 ");
				JButton btnRefresh = new JButton(" 刷新 ");
				tableShow();

				btnQuery.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String courseId = txtCourseId.getText();
						String courseName = txtCourseName.getText();
						String courseType = txtCourseType.getText();
						try {
							tableValue.clear();
							int n = 1;
							for (CourseInfo courseInfo : DButils.getCourseInfoFromFuzzyQuery(courseId, courseName,
									courseType)) {
								try {
									Vector<Object> rowValue = new Vector<>();
									rowValue.add(n);
									rowValue.add(courseInfo.getId());
									rowValue.add(courseInfo.getCouName());
									rowValue.add(courseInfo.getCouType());
									rowValue.add(courseInfo.getCreditHour());
									rowValue.add(courseInfo.getCouFaculty());
									tableValue.add(rowValue);
									n++;
								} catch (Exception e2) {
								}
							}
							table = new JTable(tableValue, columnNames);

							table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
							table.setFont(new Font("宋体", Font.PLAIN, 20));
							table.setRowHeight(30);
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							sp.setViewportView(table);
							panel.updateUI();
						} catch (Exception e2) {
						} finally {
							table.updateUI();
						}
					}
				});

				btnRefresh.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						txtCourseId.setText("");
						txtCourseName.setText("");
						txtCourseType.setText("");
						tableShow();
					}
				});

				lblCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
				lblCourseName.setFont(new Font("宋体", Font.PLAIN, 20));
				txtCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
				txtCourseName.setFont(new Font("宋体", Font.PLAIN, 20));
				lblCourseType.setFont(new Font("宋体", Font.PLAIN, 20));
				txtCourseType.setFont(new Font("宋体", Font.PLAIN, 20));
				btnQuery.setFont(new Font("宋体", Font.PLAIN, 20));
				btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));

				lblCourseId.setBounds(10, 10, 70, 35);
				txtCourseId.setBounds(80, 10, 100, 35);
				lblCourseName.setBounds(190, 10, 70, 35);
				txtCourseName.setBounds(260, 10, 100, 35);
				lblCourseType.setBounds(370, 10, 70, 35);
				txtCourseType.setBounds(440, 10, 100, 35);
				btnQuery.setBounds(550, 10, 100, 35);
				btnRefresh.setBounds(660, 10, 100, 35);

				p2.setBounds(0, toolBar.getSize().height, p1.getSize().width,
						p1.getSize().height - toolBar.getSize().height);
				sp.setBorder(titled);
				sp.setBounds(0, 50, p2.getSize().width, p2.getSize().height - 50);
				p2.add(sp);
				p2.add(lblCourseId);
				p2.add(lblCourseName);
				p2.add(txtCourseId);
				p2.add(txtCourseName);
				p2.add(lblCourseType);
				p2.add(txtCourseType);
				p2.add(btnQuery);
				p2.add(btnRefresh);
				p2.updateUI();
			}

			private void tableShow() {
				try {
					tableValue.clear();
					int n = 1;
					for (CourseInfo courseInfo : DButils.getAllCourseInfoList()) {
						try {
							Vector<Object> rowValue = new Vector<>();
							rowValue.add(n);
							rowValue.add(courseInfo.getId());
							rowValue.add(courseInfo.getCouName());
							rowValue.add(courseInfo.getCouType());
							rowValue.add(courseInfo.getCreditHour());
							rowValue.add(courseInfo.getCouFaculty());
							tableValue.add(rowValue);
							n++;
						} catch (Exception e2) {
						}
					}
					table = new JTable(tableValue, columnNames);

					table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					table.setFont(new Font("宋体", Font.PLAIN, 20));
					table.setRowHeight(30);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp.setViewportView(table);
					panel.updateUI();
				} catch (Exception e2) {
				} finally {
					table.updateUI();
				}
			}
		});

		btnEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EntryCourseInfoDialog(null).setVisible(true);
			}
		});

		btnAlter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AlterCourseInfoDialog(null).setVisible(true);
			}
		});

		btnDelet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeletCourseDialog(null).setVisible(true);
			}
		});

		p2.add(sp);
		p2.updateUI();
		toolBar.add(btnShow);
		toolBar.add(btnEntry);
		toolBar.add(btnAlter);
		toolBar.add(btnDelet);
		p1.add(toolBar, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		panel.add(p1);
		panel.updateUI();
		this.add(panel);
	}

	private void initClassScheduleInfoGUI() {
		panel.removeAll();

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height);

		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("宋体", Font.PLAIN, 20));

		JButton btnShow = new JButton("  查看课表信息  ");
		JButton btnEntry = new JButton("  添加课表信息  ");
		JButton btnAlter = new JButton("  修改课表信息  ");
		JButton btnDelet = new JButton("  删除课表信息  ");

		btnShow.setFont(new Font("宋体", Font.PLAIN, 15));
		btnEntry.setFont(new Font("宋体", Font.PLAIN, 15));
		btnAlter.setFont(new Font("宋体", Font.PLAIN, 15));
		btnDelet.setFont(new Font("宋体", Font.PLAIN, 15));

		JPanel p2 = new JPanel(null);
		p1.add(p2, BorderLayout.CENTER);

		btnShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShowClassScheduleDialog(null).setVisible(true);
			}
		});

		btnEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EntryClassScheduleInfoDialog(null).setVisible(true);
			}
		});

		btnAlter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AlterClassScheduleInfoDialog(null).setVisible(true);
			}
		});

		btnDelet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeletClassScheduleDialog(null).setVisible(true);
			}
		});

		p2.updateUI();
		toolBar.add(btnShow);
		toolBar.add(btnEntry);
		toolBar.add(btnAlter);
		toolBar.add(btnDelet);
		p1.add(toolBar, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		panel.add(p1);
		panel.updateUI();
		this.add(panel);
	}

	private void initExamInfoGUI() {
		panel.removeAll();

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height);

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, " 课程信息 ", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JScrollPane sp = new JScrollPane();

		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("宋体", Font.PLAIN, 20));

		JButton btnShow = new JButton("  查看考试信息表  ");
		JButton btnEntry = new JButton("  添加考试信息  ");
		JButton btnAlter = new JButton("  修改考试信息  ");
		JButton btnDelet = new JButton("  删除考试信息  ");

		btnShow.setFont(new Font("宋体", Font.PLAIN, 15));
		btnEntry.setFont(new Font("宋体", Font.PLAIN, 15));
		btnAlter.setFont(new Font("宋体", Font.PLAIN, 15));
		btnDelet.setFont(new Font("宋体", Font.PLAIN, 15));

		JPanel p2 = new JPanel(null);
		p1.add(p2, BorderLayout.CENTER);

		Vector<String> columnNames = new Vector<>();
		columnNames.add("序号");
		columnNames.add("学期");
		columnNames.add("班级");
		columnNames.add("课程");
		columnNames.add("教师");
		columnNames.add("考试类别");
		columnNames.add("考试日期");
		columnNames.add("考试时间");
		columnNames.add("考试地点");
		Vector<Vector<Object>> tableValue = new Vector<>();

		btnShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p2.removeAll();

				JLabel lblSemester = new JLabel("学期:", RIGHT);
				JLabel lblClassId = new JLabel("班级id:", RIGHT);
				JLabel lblCouId = new JLabel("课程id:", RIGHT);

				JTextField txtSemester = new JTextField(20);
				JTextField txtClassId = new JTextField(10);
				JTextField txtCouId = new JTextField(10);

				JButton btnQuery = new JButton(" 查询 ");
				JButton btnRefresh = new JButton(" 刷新 ");
				tableShow();

				btnQuery.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String semester = txtSemester.getText();
						String classId = txtClassId.getText();
						String couId = txtCouId.getText();
						try {
							tableValue.clear();
							int n = 1;
							for (ClassCourse cc : DButils.getClassCourseFromFuzzyQuery(semester, classId, couId)) {
								try {
									Vector<Object> rowValue = new Vector<>();
									rowValue.add(n);
									rowValue.add(cc.getSemester());
									rowValue.add(cc.getClassId());
									rowValue.add(cc.getCouId());
									rowValue.add(cc.getTeaId());
									rowValue.add(cc.getExamType());
									rowValue.add(cc.getExamDate());
									rowValue.add(cc.getExamTime());
									rowValue.add(cc.getExamAddr());
									tableValue.add(rowValue);
									n++;
								} catch (Exception e2) {
								}
							}
							table = new JTable(tableValue, columnNames);

							table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
							table.setFont(new Font("宋体", Font.PLAIN, 20));
							table.setRowHeight(30);
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							sp.setViewportView(table);
							panel.updateUI();
						} catch (Exception e2) {
						} finally {
							table.updateUI();
						}
					}
				});

				btnRefresh.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						txtSemester.setText("");
						txtClassId.setText("");
						txtCouId.setText("");
						tableShow();
					}
				});

				lblSemester.setFont(new Font("宋体", Font.PLAIN, 20));
				lblClassId.setFont(new Font("宋体", Font.PLAIN, 20));
				txtSemester.setFont(new Font("宋体", Font.PLAIN, 20));
				txtClassId.setFont(new Font("宋体", Font.PLAIN, 20));
				lblCouId.setFont(new Font("宋体", Font.PLAIN, 20));
				txtCouId.setFont(new Font("宋体", Font.PLAIN, 20));
				btnQuery.setFont(new Font("宋体", Font.PLAIN, 20));
				btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));

				lblSemester.setBounds(10, 10, 70, 35);
				txtSemester.setBounds(80, 10, 100, 35);
				lblClassId.setBounds(190, 10, 70, 35);
				txtClassId.setBounds(260, 10, 100, 35);
				lblCouId.setBounds(370, 10, 70, 35);
				txtCouId.setBounds(440, 10, 100, 35);
				btnQuery.setBounds(550, 10, 100, 35);
				btnRefresh.setBounds(660, 10, 100, 35);

				p2.setBounds(0, toolBar.getSize().height, p1.getSize().width,
						p1.getSize().height - toolBar.getSize().height);
				sp.setBorder(titled);
				sp.setBounds(0, 50, p2.getSize().width, p2.getSize().height - 50);
				p2.add(sp);
				p2.add(lblSemester);
				p2.add(lblClassId);
				p2.add(txtSemester);
				p2.add(txtClassId);
				p2.add(lblCouId);
				p2.add(txtCouId);
				p2.add(btnQuery);
				p2.add(btnRefresh);
				p2.updateUI();
			}

			private void tableShow() {
				try {
					tableValue.clear();
					int n = 1;
					for (ClassCourse cc : DButils.getAllClassCourseList()) {
						try {
							Vector<Object> rowValue = new Vector<>();
							rowValue.add(n);
							rowValue.add(cc.getSemester());
							rowValue.add(cc.getClassId());
							rowValue.add(cc.getCouId());
							rowValue.add(cc.getTeaId());
							rowValue.add(cc.getExamType());
							rowValue.add(cc.getExamDate());
							rowValue.add(cc.getExamTime());
							rowValue.add(cc.getExamAddr());
							tableValue.add(rowValue);
							n++;
						} catch (Exception e2) {
						}
					}
					table = new JTable(tableValue, columnNames);

					table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					table.setFont(new Font("宋体", Font.PLAIN, 20));
					table.setRowHeight(30);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp.setViewportView(table);
					panel.updateUI();
				} catch (Exception e2) {
				} finally {
					table.updateUI();
				}
			}
		});

		btnEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EntryExamInfoDialog(null).setVisible(true);
			}
		});

		btnAlter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AlterExamInfoDialog(null).setVisible(true);
			}
		});

		btnDelet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeletExamDialog(null).setVisible(true);
			}
		});

		p2.add(sp);
		p2.updateUI();
		toolBar.add(btnShow);
		toolBar.add(btnEntry);
		toolBar.add(btnAlter);
		toolBar.add(btnDelet);
		p1.add(toolBar, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		panel.add(p1);
		panel.updateUI();
		this.add(panel);
	}


	private void initResultInfoGUI() {
		panel.removeAll();

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBounds(0, 0, panel.getSize().width, panel.getSize().height);

		Border border = BorderFactory.createLineBorder(Color.lightGray, 3);
		Border titled = BorderFactory.createTitledBorder(border, " 成绩信息 ", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		JScrollPane sp = new JScrollPane();

		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("宋体", Font.PLAIN, 20));

		JButton btnShow = new JButton("  查看成绩信息表  ");
		JButton btnEntry = new JButton("  添加成绩信息  ");
		JButton btnAlter = new JButton("  修改成绩信息  ");
		JButton btnDelet = new JButton("  删除成绩信息  ");

		btnShow.setFont(new Font("宋体", Font.PLAIN, 15));
		btnEntry.setFont(new Font("宋体", Font.PLAIN, 15));
		btnAlter.setFont(new Font("宋体", Font.PLAIN, 15));
		btnDelet.setFont(new Font("宋体", Font.PLAIN, 15));

		JPanel p2 = new JPanel(null);
		p1.add(p2, BorderLayout.CENTER);

		Vector<String> columnNames = new Vector<>();
		columnNames.add("序号");
		columnNames.add("学期");
		columnNames.add("学号");
		columnNames.add("课程代码");
		columnNames.add("成绩");
		Vector<Vector<Object>> tableValue = new Vector<>();

		btnShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p2.removeAll();

				JLabel lblSemester = new JLabel("学期:", RIGHT);
				JLabel lblStudentId = new JLabel("学号:", RIGHT);
				JLabel lblCourseId = new JLabel("课程id:", RIGHT);

				JTextField txtSemester = new JTextField(20);
				JTextField txtStudentId = new JTextField(10);
				JTextField txtCourseId = new JTextField(10);

				JButton btnQuery = new JButton(" 查询 ");
				JButton btnRefresh = new JButton(" 刷新 ");
				tableShow();

				btnQuery.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String semester = txtSemester.getText();
						String studentId = txtStudentId.getText();
						String courseId = txtCourseId.getText();
						try {
							tableValue.clear();
							int n = 1;
							for (ResultInfo resultInfo : DButils.getResultInfoFromFuzzyQuery(semester, studentId,
									courseId)) {
								try {
									Vector<Object> rowValue = new Vector<>();
									rowValue.add(n);
									rowValue.add(resultInfo.getSemester());
									rowValue.add(resultInfo.getId());
									rowValue.add(resultInfo.getCouId());
									rowValue.add(resultInfo.getResult());
									tableValue.add(rowValue);
									n++;
								} catch (Exception e2) {
								}
							}
							table = new JTable(tableValue, columnNames);

							table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
							table.setFont(new Font("宋体", Font.PLAIN, 20));
							table.setRowHeight(30);
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							sp.setViewportView(table);
							panel.updateUI();
						} catch (Exception e2) {
						} finally {
							table.updateUI();
						}
					}
				});

				btnRefresh.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						txtSemester.setText("");
						txtStudentId.setText("");
						txtCourseId.setText("");
						tableShow();
					}
				});

				lblSemester.setFont(new Font("宋体", Font.PLAIN, 20));
				lblStudentId.setFont(new Font("宋体", Font.PLAIN, 20));
				lblCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
				txtSemester.setFont(new Font("宋体", Font.PLAIN, 20));
				txtStudentId.setFont(new Font("宋体", Font.PLAIN, 20));
				txtCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
				btnQuery.setFont(new Font("宋体", Font.PLAIN, 20));
				btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));

				lblSemester.setBounds(10, 10, 70, 35);
				txtSemester.setBounds(80, 10, 100, 35);
				lblStudentId.setBounds(190, 10, 70, 35);
				txtStudentId.setBounds(260, 10, 100, 35);
				lblCourseId.setBounds(370, 10, 70, 35);
				txtCourseId.setBounds(440, 10, 100, 35);
				btnQuery.setBounds(550, 10, 100, 35);
				btnRefresh.setBounds(660, 10, 100, 35);

				p2.setBounds(0, toolBar.getSize().height, p1.getSize().width,
						p1.getSize().height - toolBar.getSize().height);
				sp.setBorder(titled);
				sp.setBounds(0, 50, p2.getSize().width, p2.getSize().height - 50);
				p2.add(sp);
				p2.add(lblSemester);
				p2.add(lblStudentId);
				p2.add(lblCourseId);
				p2.add(txtSemester);
				p2.add(txtStudentId);
				p2.add(txtCourseId);
				p2.add(btnQuery);
				p2.add(btnRefresh);
				p2.updateUI();
			}

			private void tableShow() {
				try {
					tableValue.clear();
					int n = 1;
					for (ResultInfo resultInfo : DButils.getAllResultInfoList()) {
						try {
							Vector<Object> rowValue = new Vector<>();
							rowValue.add(n);
							rowValue.add(resultInfo.getSemester());
							rowValue.add(resultInfo.getId());
							rowValue.add(resultInfo.getCouId());
							rowValue.add(resultInfo.getResult());
							tableValue.add(rowValue);
							n++;
						} catch (Exception e2) {
						}
					}
					table = new JTable(tableValue, columnNames);

					table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					table.setFont(new Font("宋体", Font.PLAIN, 20));
					table.setRowHeight(30);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp.setViewportView(table);
					panel.updateUI();
				} catch (Exception e2) {
				} finally {
					table.updateUI();
				}
			}
		});

		btnEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EntryResultInfoDialog(null).setVisible(true);
			}
		});

		btnAlter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AlterResultInfoDialog(null).setVisible(true);
			}
		});

		btnDelet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteResultDialog(null).setVisible(true);
			}
		});

		p2.add(sp);
		p2.updateUI();
		toolBar.add(btnShow);
		toolBar.add(btnEntry);
		toolBar.add(btnAlter);
		toolBar.add(btnDelet);
		p1.add(toolBar, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		panel.add(p1);
		panel.updateUI();
		this.add(panel);
	}
}