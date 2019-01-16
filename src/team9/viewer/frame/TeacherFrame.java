package team9.viewer.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import team9.control.utils.DButils;
import team9.model.ClassCourse;
import team9.model.ClassInfo;
import team9.model.CourseInfo;
import team9.model.ScreenSize;
import team9.model.StudentInfo;
import team9.model.User;
import team9.viewer.dialog.AlterRecordDialog;
import team9.viewer.dialog.ChangePasswordDialog;
import team9.viewer.dialog.EntryRecordDialog;

@SuppressWarnings("serial")
public class TeacherFrame extends JFrame implements SwingConstants {
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menuHome, menuPerformanceManagement;
	private JMenuItem miHome, miSwitch, miChangePassword, miQuit, miEntryRecord, miUserInfo;
	private JLabel lblBackground;
	private JTable tab;

	public TeacherFrame() {
		super(" 教务系统-教师端");

		ImageIcon icon = new ImageIcon("lib/images/Users.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);
		tab = new JTable();

		this.setSize(2 * ScreenSize.WIDTH / 3, 2 * ScreenSize.HEIGHT / 3);
		this.add(panel);

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
		menuPerformanceManagement = new JMenu(" 成绩管理 ");

		miUserInfo = new JMenuItem(DButils.getTeacherNameFromeId(User.getUserName())+"(" + User.getUserName() + ") " + User.getRoleInfo());

		miHome = new JMenuItem("首页 ");
		miSwitch = new JMenuItem("切换用户 ", new ImageIcon("lib/images/Refresh.png"));
		miChangePassword = new JMenuItem("修改密码 ", new ImageIcon("lib/images/Settings.png"));
		miQuit = new JMenuItem("退出 ", new ImageIcon("lib/images/PowerOff.png"));
		miEntryRecord = new JMenuItem(" 录入成绩 ");

		miUserInfo.setForeground(Color.LIGHT_GRAY);

		menuHome.setFont(new Font("黑体", Font.PLAIN, 20));
		menuPerformanceManagement.setFont(new Font("黑体", Font.PLAIN, 20));

		miUserInfo.setFont(new Font("黑体", Font.PLAIN, 20));
		miHome.setFont(new Font("黑体", Font.PLAIN, 20));
		miSwitch.setFont(new Font("黑体", Font.PLAIN, 20));
		miChangePassword.setFont(new Font("黑体", Font.PLAIN, 20));
		miQuit.setFont(new Font("黑体", Font.PLAIN, 20));
		miEntryRecord.setFont(new Font("黑体", Font.PLAIN, 20));

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
				TeacherFrame.this.setVisible(false);
				new LoginFrame();
			}
		});
		miChangePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordDialog(null).setVisible(true);
				if (!User.passwordVerify()) {
					TeacherFrame.this.setVisible(false);
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
		miEntryRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initEntryRecordGUI();
			}
		});
		menuBar.add(menuHome);
		menuBar.add(menuPerformanceManagement);
		menuBar.add(miUserInfo);

		menuHome.add(miHome);
		menuHome.addSeparator();

		menuHome.add(miSwitch);
		menuHome.add(miChangePassword);
		menuHome.add(miQuit);

		menuPerformanceManagement.add(miEntryRecord);

		this.setJMenuBar(menuBar);
	}

	private void initHomeGUI() {

		panel.removeAll();

		ImageIcon icon = new ImageIcon("lib/images/hello.png");
		icon.setImage(icon.getImage().getScaledInstance(TeacherFrame.this.getWidth(), TeacherFrame.this.getHeight(),
				Image.SCALE_DEFAULT));

		lblBackground = new JLabel(icon, CENTER);

		lblBackground.setSize(TeacherFrame.this.getWidth(), TeacherFrame.this.getHeight());

		panel.add(lblBackground);
		panel.updateUI();

		this.add(panel);
	}

	private void initEntryRecordGUI() {
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

		JButton btnShow = new JButton("  查看  ");
		JButton btnEntry = new JButton("  录入  ");
		JButton btnAlter = new JButton("  修改  ");
		btnShow.setFont(new Font("宋体", Font.PLAIN, 15));
		btnEntry.setFont(new Font("宋体", Font.PLAIN, 15));
		btnAlter.setFont(new Font("宋体", Font.PLAIN, 15));

		JPanel p2 = new JPanel(null);
		p1.add(p2, BorderLayout.CENTER);

		ClassCourse classCourse = new ClassCourse();
		ClassInfo classInfo = new ClassInfo();

		Vector<String> columnNames = new Vector<>();
		columnNames.add("序号");
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("课程名称");
		columnNames.add("课程类别");
		columnNames.add("学分");
		columnNames.add("成绩");
		Vector<Vector<Object>> tableValue = new Vector<>();

		List<StudentInfo> siList = new ArrayList<>();
		CourseInfo csiBean = new CourseInfo();
		
		btnShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				p2.removeAll();

				JLabel[] label = new JLabel[3];
				label[0] = new JLabel("学期:");
				label[1] = new JLabel("班级:");
				label[2] = new JLabel("课程:");

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

				JComboBox<String> cbClass = new JComboBox<String>();
				cbClass.addItem("选择班级");

				cbSemester.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						classCourse.setSemester(cbSemester.getSelectedItem().toString());
					}
				});

				List<ClassCourse> list = DButils.classIdFromTeaIdBeanHandler(User.getUserName());
				Iterator<ClassCourse> it = list.iterator();
				int m = 0, n = 0;
				while (it.hasNext()) {
					ClassCourse cc = it.next();
					for (int i = 0; i < n; i++) {
						m = 0;
						if (cc.getClassId().equals(list.get(i).getClassId())) {
							m = 1;
							break;
						}
					}
					if (m == 0) {
						ClassInfo ci = DButils.classNameFromIdListHandler(cc.getClassId());
						cbClass.addItem(ci.getName());
					}
					n++;
				}

				JComboBox<String> cbCourse = new JComboBox<String>();
				cbCourse.addItem("选择课程");

				cbClass.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						cbCourse.removeAllItems();
						cbCourse.addItem("选择课程");
						try {
							classInfo.setName(cbClass.getSelectedItem().toString());
							classInfo.setId(DButils.classIdFromNameListHandler(classInfo.getName()).getId());
							List<ClassCourse> list = DButils.courseIdFromClassCourseBeanHandler(User.getUserName(),
									classInfo.getId(), classCourse.getSemester());
							for (ClassCourse bean : list) {
								cbCourse.addItem(DButils.getCourseNameFromeId(bean.getCouId()));
							}
						} catch (Exception e2) {
						}
					}
				});
				
				cbCourse.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						try {
							csiBean.setCouName(DButils.getCourseBeanFromeName(cbCourse.getSelectedItem().toString()).getCouName());
							csiBean.setCouType(DButils.getCourseBeanFromeName(cbCourse.getSelectedItem().toString()).getCouType());
							csiBean.setCreditHour(DButils.getCourseBeanFromeName(cbCourse.getSelectedItem().toString()).getCreditHour());
							csiBean.setId(DButils.getCourseBeanFromeName(cbCourse.getSelectedItem().toString()).getId());
							siList.clear();
							for (StudentInfo si : DButils.getStudentListFromClassId(classInfo.getId())) {
								siList.add(si);
							}
						} catch (Exception e2) {
						} 
					}
				});
				
				JButton btnRefresh = new JButton("刷新");
				
				btnRefresh.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						try {
							tableValue.clear();
							int i = 1;
							for (StudentInfo si : siList) {
								try {
									Vector<Object> rowValue = new Vector<>();
									rowValue.add(i);
									rowValue.add(si.getId());
									rowValue.add(si.getStuName());
									rowValue.add(csiBean.getCouName());
									rowValue.add(csiBean.getCouType());
									rowValue.add(csiBean.getCreditHour());
									rowValue.add(DButils.getResultFromeIdAndCouId(si.getId(), csiBean.getId()));
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

				label[0].setFont(new Font("宋体", Font.PLAIN, 20));
				label[1].setFont(new Font("宋体", Font.PLAIN, 20));
				label[2].setFont(new Font("宋体", Font.PLAIN, 20));
				cbClass.setFont(new Font("宋体", Font.PLAIN, 20));
				cbSemester.setFont(new Font("宋体", Font.PLAIN, 20));
				cbCourse.setFont(new Font("宋体", Font.PLAIN, 20));
				btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));

				label[0].setBounds(20, 10, 50, 35);
				cbSemester.setBounds(70, 10, 150, 35);
				label[1].setBounds(240, 10, 50, 35);
				cbClass.setBounds(290, 10, 150, 35);
				label[2].setBounds(460, 10, 50, 35);
				cbCourse.setBounds(510, 10, 150, 35);
				btnRefresh.setBounds(680, 10, 100, 35);

				p2.setBounds(0, toolBar.getSize().height, p1.getSize().width,
						p1.getSize().height - toolBar.getSize().height);
				sp.setBorder(titled);
				sp.setBounds(0, 50, p2.getSize().width, p2.getSize().height - 50);
				p2.add(sp);

				p2.add(label[0]);
				p2.add(label[1]);
				p2.add(label[2]);
				p2.add(cbClass);
				p2.add(cbSemester);
				p2.add(cbCourse);
				p2.add(btnRefresh);
				p2.updateUI();
			}

		});

		btnEntry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new EntryRecordDialog(null).setVisible(true);
			}
		});
		btnAlter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AlterRecordDialog(null).setVisible(true);
			}
		});

		toolBar.add(btnShow);
		toolBar.add(btnEntry);
		toolBar.add(btnAlter);
		p1.add(toolBar, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		panel.add(p1);
		panel.updateUI();
		this.add(panel);
	}
}