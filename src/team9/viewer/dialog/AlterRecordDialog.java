package team9.viewer.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import team9.model.ClassCourse;
import team9.model.ClassInfo;
import team9.model.CourseInfo;
import team9.model.ResultInfo;
import team9.model.ScreenSize;
import team9.model.StudentInfo;
import team9.model.User;

@SuppressWarnings("serial")
public class AlterRecordDialog extends JDialog implements SwingConstants {
	JPanel p;
	JLabel[] label;
	JLabel lblMsg;
	JTextField txtResult;
	JButton btnOK, btnCanel;
	CourseInfo courseInfo;
	ClassCourse classCourse;
	ClassInfo classInfo;
	StudentInfo studentInfo;

	public AlterRecordDialog(JFrame f) {
		super(f, " 修改成绩", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		p = new JPanel(null);
		label = new JLabel[6];
		label[0] = new JLabel("学期:", RIGHT);
		label[1] = new JLabel("班级:", RIGHT);
		label[2] = new JLabel("课程:", RIGHT);
		label[3] = new JLabel("学号:", RIGHT);
		label[4] = new JLabel("姓名:", RIGHT);
		label[5] = new JLabel("成绩:", RIGHT);
		txtResult = new JTextField(3);
		lblMsg = new JLabel("", CENTER);
		btnOK = new JButton("确定");
		btnCanel = new JButton("取消");

		JComboBox<String> cbSemester = new JComboBox<String>();
		JComboBox<String> cbClass = new JComboBox<String>();
		JComboBox<String> cbCourse = new JComboBox<String>();
		JComboBox<String> cbStudentId = new JComboBox<String>();
		JComboBox<String> cbStudentName = new JComboBox<String>();

		classCourse = new ClassCourse();
		classInfo = new ClassInfo();
		studentInfo = new StudentInfo();

		List<StudentInfo> siList = new ArrayList<>();

		lblMsg.setForeground(Color.RED);

		label[0].setFont(new Font("宋体", Font.PLAIN, 20));
		label[1].setFont(new Font("宋体", Font.PLAIN, 20));
		label[2].setFont(new Font("宋体", Font.PLAIN, 20));
		label[3].setFont(new Font("宋体", Font.PLAIN, 20));
		label[4].setFont(new Font("宋体", Font.PLAIN, 20));
		label[5].setFont(new Font("宋体", Font.PLAIN, 20));
		txtResult.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		cbClass.setFont(new Font("宋体", Font.PLAIN, 20));
		cbCourse.setFont(new Font("宋体", Font.PLAIN, 20));
		cbStudentId.setFont(new Font("宋体", Font.PLAIN, 20));
		cbStudentName.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		cbSemester.addItem("选择学期");
		cbSemester.addItem("第一学期");
		cbSemester.addItem("第二学期");
		cbSemester.addItem("第三学期");
		cbSemester.addItem("第四学期");
		cbSemester.addItem("第五学期");
		cbSemester.addItem("第六学期");
		cbSemester.addItem("第七学期");
		cbSemester.addItem("第八学期");
		cbClass.addItem("选择班级");
		cbCourse.addItem("选择课程");
		cbStudentId.addItem("学生学号");
		cbStudentName.removeAll();

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

		cbSemester.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				classCourse.setSemester(cbSemester.getSelectedItem().toString());
			}
		});

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
				cbStudentId.removeAllItems();
				cbStudentId.addItem("学生学号");
				try {
					courseInfo = DButils.getCourseBeanFromeName(cbCourse.getSelectedItem().toString());
					siList.clear();
					for (StudentInfo si : DButils.getStudentListFromClassId(classInfo.getId())) {
						siList.add(si);
					}
					for (StudentInfo si : siList) {
						cbStudentId.addItem(si.getId());
					}
				} catch (Exception e2) {
				}
			}
		});

		cbStudentId.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cbStudentName.removeAllItems();
				try {
					txtResult.setEditable(true);
					txtResult.setText("");
					studentInfo.setId(cbStudentId.getSelectedItem().toString());
					studentInfo.setStuName(DButils.getStudentNameFromId(studentInfo.getId()));
					cbStudentName.addItem(studentInfo.getStuName());

					if (DButils.getResultFromeIdAndCouId(studentInfo.getId(), courseInfo.getId()) == null) {
						txtResult.setEditable(false);
					} else {
						txtResult.setText(DButils.getResultFromeIdAndCouId(studentInfo.getId(), courseInfo.getId()));
					}
				} catch (Exception e2) {
				}
			}
		});

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (classCourse.getSemester().equals("选择学期")) {
					lblMsg.setText("请选择学期");
				} else if (studentInfo.getId().equals("学生学号")) {
					lblMsg.setText("请选择学生学号");
				} else if (courseInfo.getId().equals("选择课程")) {
					lblMsg.setText("请选择课程");
				} else if (txtResult.getText().equals("")) {
					lblMsg.setText("请输入成绩");
				} else {
					try {
						ResultInfo resultInfo = new ResultInfo();
						resultInfo.setSemester(classCourse.getSemester());
						resultInfo.setId(studentInfo.getId());
						resultInfo.setCouId(courseInfo.getId());
						resultInfo.setResult(txtResult.getText());
						DButils.updateResult(resultInfo);
						lblMsg.setText("成绩修改成功");
					} catch (Exception e2) {
						lblMsg.setText("成绩修改失败，请重新修改");
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
		p.setSize(this.getSize());
		label[0].setBounds(2 * p.getSize().width / 30, p.getSize().height / 4, 2 * p.getSize().width / 15, 35);
		label[1].setBounds(10 * p.getSize().width / 30, p.getSize().height / 4, 2 * p.getSize().width / 15, 35);
		label[2].setBounds(18 * p.getSize().width / 30, p.getSize().height / 4, 2 * p.getSize().width / 15, 35);
		label[3].setBounds(2 * p.getSize().width / 30, 3 * p.getSize().height / 8, 2 * p.getSize().width / 15, 35);
		label[4].setBounds(10 * p.getSize().width / 30, 3 * p.getSize().height / 8, 2 * p.getSize().width / 15, 35);
		label[5].setBounds(18 * p.getSize().width / 30, 3 * p.getSize().height / 8, 2 * p.getSize().width / 15, 35);
		cbSemester.setBounds(6 * p.getSize().width / 30, p.getSize().height / 4, 2 * p.getSize().width / 15, 35);
		cbClass.setBounds(14 * p.getSize().width / 30, p.getSize().height / 4, 2 * p.getSize().width / 15, 35);
		cbCourse.setBounds(22 * p.getSize().width / 30, p.getSize().height / 4, 2 * p.getSize().width / 15, 35);
		cbStudentId.setBounds(6 * p.getSize().width / 30, 3 * p.getSize().height / 8, 2 * p.getSize().width / 15, 35);
		cbStudentName.setBounds(14 * p.getSize().width / 30, 3 * p.getSize().height / 8, 2 * p.getSize().width / 15,
				35);
		txtResult.setBounds(22 * p.getSize().width / 30, 3 * p.getSize().height / 8, 2 * p.getSize().width / 15, 35);
		lblMsg.setBounds(0, p.getSize().height / 2, p.getSize().width, 35);
		btnOK.setBounds(2 * p.getSize().width / 7, 5 * p.getSize().height / 8, p.getSize().width / 7, 35);
		btnCanel.setBounds(4 * p.getSize().width / 7, 5 * p.getSize().height / 8, p.getSize().width / 7, 35);

		p.add(label[0]);
		p.add(label[1]);
		p.add(label[2]);
		p.add(label[3]);
		p.add(label[4]);
		p.add(label[5]);
		p.add(cbSemester);
		p.add(cbCourse);
		p.add(cbClass);
		p.add(cbStudentId);
		p.add(cbStudentName);
		p.add(txtResult);
		p.add(lblMsg);
		p.add(btnOK);
		p.add(btnCanel);
		
		this.add(p);
		this.pack();
		this.setSize(ScreenSize.WIDTH / 2, ScreenSize.HEIGHT / 2);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}