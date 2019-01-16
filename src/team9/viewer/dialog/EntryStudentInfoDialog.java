package team9.viewer.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import team9.control.utils.DButils;
import team9.model.ClassInfo;
import team9.model.ScreenSize;
import team9.model.StudentInfo;

@SuppressWarnings("serial")
public class EntryStudentInfoDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblStudentId, lblStudentName, lblStudentEnName, lblStudentSex, lblStudentGrade, lblStudentEduSys,
			lblStudentFaculty, lblStudentMajor, lblStudentMode, lblStudentEducation, lblStudentCampus,
			lblStudentClassId, lblMsg;
	private JTextField txtStudentId, txtStudentName, txtStudentEnName, txtStudentEduSys, txtStudentFaculty,
			txtStudentMajor, txtStudentMode, txtStudentEducation, txtStudentCampus;
	private JButton btnOK, btnCanel;
	private JComboBox<String> cbClassId, cbGrade, cbSex;

	private StudentInfo studentInfo;

	public EntryStudentInfoDialog(JFrame f) {
		super(f, " 添加学生信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);
		lblStudentId = new JLabel("学号:", RIGHT);
		lblStudentName = new JLabel("姓名:", RIGHT);
		lblStudentEnName = new JLabel("英文名:", RIGHT);
		lblStudentSex = new JLabel("性别:", RIGHT);
		lblStudentGrade = new JLabel("年级:", RIGHT);
		lblStudentEduSys = new JLabel("学制:", RIGHT);
		lblStudentFaculty = new JLabel("院系:", RIGHT);
		lblStudentMajor = new JLabel("专业:", RIGHT);
		lblStudentMode = new JLabel("学习形式:", RIGHT);
		lblStudentEducation = new JLabel("学历层次:", RIGHT);
		lblStudentCampus = new JLabel("所属校区:", RIGHT);
		lblStudentClassId = new JLabel("所属班级:", RIGHT);
		lblMsg = new JLabel("", CENTER);

		cbClassId = new JComboBox<>();
		try {
			cbClassId.removeAllItems();
			for (ClassInfo ci : DButils.getAllClassInfoList()) {
				cbClassId.addItem(ci.getId());
			}
		} catch (Exception e2) {
		}

		cbGrade = new JComboBox<>();
		cbGrade.addItem("2015");
		cbGrade.addItem("2016");
		cbGrade.addItem("2017");
		cbGrade.addItem("2018");

		cbSex = new JComboBox<>();
		cbSex.addItem("男");
		cbSex.addItem("女");

		txtStudentId = new JTextField(20);
		txtStudentName = new JTextField(20);
		txtStudentEnName = new JTextField(20);
		txtStudentEduSys = new JTextField(20);
		txtStudentFaculty = new JTextField(20);
		txtStudentMajor = new JTextField(20);
		txtStudentMode = new JTextField(20);
		txtStudentEducation = new JTextField(20);
		txtStudentCampus = new JTextField(20);

		txtStudentEduSys.setText("4");
		txtStudentMode.setText("普通全日制");
		txtStudentEducation.setText("本科");
		txtStudentCampus.setText("鄠邑校区");

		txtStudentEduSys.setEditable(false);
		txtStudentMode.setEditable(false);
		txtStudentEducation.setEditable(false);
		txtStudentCampus.setEditable(false);

		btnOK = new JButton("确定");
		btnCanel = new JButton("取消");

		studentInfo = new StudentInfo();

		lblMsg.setForeground(Color.RED);

		lblStudentId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentEnName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentSex.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentGrade.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentEduSys.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentFaculty.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentMajor.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentMode.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentEducation.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentCampus.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		txtStudentId.setFont(new Font("宋体", Font.PLAIN, 20));
		txtStudentName.setFont(new Font("宋体", Font.PLAIN, 20));
		txtStudentEnName.setFont(new Font("宋体", Font.PLAIN, 20));
		cbSex.setFont(new Font("宋体", Font.PLAIN, 20));
		cbGrade.setFont(new Font("宋体", Font.PLAIN, 20));
		txtStudentEduSys.setFont(new Font("宋体", Font.PLAIN, 20));
		txtStudentFaculty.setFont(new Font("宋体", Font.PLAIN, 20));
		txtStudentMajor.setFont(new Font("宋体", Font.PLAIN, 20));
		txtStudentMode.setFont(new Font("宋体", Font.PLAIN, 20));
		txtStudentEducation.setFont(new Font("宋体", Font.PLAIN, 20));
		txtStudentCampus.setFont(new Font("宋体", Font.PLAIN, 20));
		cbClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				studentInfo.setId(txtStudentId.getText());
				studentInfo.setStuName(txtStudentName.getText());
				studentInfo.setEnName(txtStudentEnName.getText());
				studentInfo.setSex(cbSex.getSelectedItem().toString());
				studentInfo.setGrade(cbGrade.getSelectedItem().toString());
				studentInfo.setEduSys(txtStudentEduSys.getText());
				studentInfo.setFaculty(txtStudentFaculty.getText());
				studentInfo.setMajor(txtStudentMajor.getText());
				studentInfo.setStuMode(txtStudentMode.getText());
				studentInfo.setEducation(txtStudentEducation.getText());
				studentInfo.setCampus(txtStudentCampus.getText());
				studentInfo.setClassId(cbClassId.getSelectedItem().toString());

				if (studentInfo.getId() == null || studentInfo.getId().equals("")) {
					lblMsg.setText("学号不能为空！");
				} else if (studentInfo.getStuName() == null || studentInfo.getStuName().equals("")) {
					lblMsg.setText("姓名不能为空！");
				} else {
					if (studentInfo.getId().matches("^[0-9]{12}$")) {
						DButils.insertStudent(studentInfo);
						DButils.insertUser(studentInfo.getId(), studentInfo.getId().substring(6), "student");
						lblMsg.setText("学生信息添加成功");
					} else {
						lblMsg.setText("学号格式错误！");
					}
				}
			}
		});

		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.setSize(ScreenSize.WIDTH / 2, ScreenSize.HEIGHT / 2);
		panel.setSize(this.getSize());

		lblStudentId.setBounds(panel.getSize().width / 9, panel.getSize().height / 17, panel.getSize().width / 9,
				panel.getSize().height / 17);
		txtStudentId.setBounds(2 * panel.getSize().width / 9, panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);
		lblStudentName.setBounds(5 * panel.getSize().width / 9, panel.getSize().height / 17, panel.getSize().width / 9,
				panel.getSize().height / 17);
		txtStudentName.setBounds(6 * panel.getSize().width / 9, panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);
		lblStudentEnName.setBounds(panel.getSize().width / 9, 3 * panel.getSize().height / 17,
				panel.getSize().width / 9, panel.getSize().height / 17);
		txtStudentEnName.setBounds(2 * panel.getSize().width / 9, 3 * panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);
		lblStudentSex.setBounds(5 * panel.getSize().width / 9, 3 * panel.getSize().height / 17,
				panel.getSize().width / 9, panel.getSize().height / 17);
		cbSex.setBounds(6 * panel.getSize().width / 9, 3 * panel.getSize().height / 17, 2 * panel.getSize().width / 9,
				panel.getSize().height / 17);
		lblStudentGrade.setBounds(panel.getSize().width / 9, 5 * panel.getSize().height / 17, panel.getSize().width / 9,
				panel.getSize().height / 17);
		cbGrade.setBounds(2 * panel.getSize().width / 9, 5 * panel.getSize().height / 17, 2 * panel.getSize().width / 9,
				panel.getSize().height / 17);
		lblStudentEduSys.setBounds(5 * panel.getSize().width / 9, 5 * panel.getSize().height / 17,
				panel.getSize().width / 9, panel.getSize().height / 17);
		txtStudentEduSys.setBounds(6 * panel.getSize().width / 9, 5 * panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);
		lblStudentFaculty.setBounds(panel.getSize().width / 9, 7 * panel.getSize().height / 17,
				panel.getSize().width / 9, panel.getSize().height / 17);
		txtStudentFaculty.setBounds(2 * panel.getSize().width / 9, 7 * panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);
		lblStudentMajor.setBounds(5 * panel.getSize().width / 9, 7 * panel.getSize().height / 17,
				panel.getSize().width / 9, panel.getSize().height / 17);
		txtStudentMajor.setBounds(6 * panel.getSize().width / 9, 7 * panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);
		lblStudentMode.setBounds(panel.getSize().width / 9, 9 * panel.getSize().height / 17, panel.getSize().width / 9,
				panel.getSize().height / 17);
		txtStudentMode.setBounds(2 * panel.getSize().width / 9, 9 * panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);
		lblStudentEducation.setBounds(5 * panel.getSize().width / 9, 9 * panel.getSize().height / 17,
				panel.getSize().width / 9, panel.getSize().height / 17);
		txtStudentEducation.setBounds(6 * panel.getSize().width / 9, 9 * panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);
		lblStudentCampus.setBounds(panel.getSize().width / 9, 11 * panel.getSize().height / 17,
				panel.getSize().width / 9, panel.getSize().height / 17);
		txtStudentCampus.setBounds(2 * panel.getSize().width / 9, 11 * panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);
		lblStudentClassId.setBounds(5 * panel.getSize().width / 9, 11 * panel.getSize().height / 17,
				panel.getSize().width / 9, panel.getSize().height / 17);
		cbClassId.setBounds(6 * panel.getSize().width / 9, 11 * panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);
		lblMsg.setBounds(panel.getSize().width / 9, 13 * panel.getSize().height / 17, 7 * panel.getSize().width / 9,
				panel.getSize().height / 17);
		btnOK.setBounds(2 * panel.getSize().width / 9, 14 * panel.getSize().height / 17, 2 * panel.getSize().width / 9,
				panel.getSize().height / 17);
		btnCanel.setBounds(5 * panel.getSize().width / 9, 14 * panel.getSize().height / 17,
				2 * panel.getSize().width / 9, panel.getSize().height / 17);

		panel.add(lblStudentId);
		panel.add(lblStudentName);
		panel.add(lblStudentEnName);
		panel.add(lblStudentSex);
		panel.add(lblStudentGrade);
		panel.add(lblStudentEduSys);
		panel.add(lblStudentFaculty);
		panel.add(lblStudentMajor);
		panel.add(lblStudentMode);
		panel.add(lblStudentEducation);
		panel.add(lblStudentCampus);
		panel.add(lblStudentClassId);
		panel.add(txtStudentId);
		panel.add(txtStudentName);
		panel.add(txtStudentEnName);
		panel.add(cbSex);
		panel.add(cbGrade);
		panel.add(txtStudentEduSys);
		panel.add(txtStudentFaculty);
		panel.add(txtStudentMajor);
		panel.add(txtStudentMode);
		panel.add(txtStudentEducation);
		panel.add(txtStudentCampus);
		panel.add(cbClassId);
		panel.add(lblMsg);
		panel.add(btnOK);
		panel.add(btnCanel);

		this.add(panel);
		this.pack();
		this.setSize(ScreenSize.WIDTH / 2, ScreenSize.HEIGHT / 2);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}