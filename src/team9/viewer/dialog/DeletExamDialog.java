package team9.viewer.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
import team9.model.ScreenSize;
import team9.model.TeacherInfo;

@SuppressWarnings("serial")
public class DeletExamDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblSemester, lblClassId, lblCouId, lblTeaId, lblExamType, lblExamDate, lblExamTime, lblExamAddr,
			lblMsg;
	private JTextField txtExamType, txtExamDate, txtExamTime, txtExamAddr;
	private JButton btnOK, btnCanel;
	private ClassCourse oldClassCourse, newClassCourse;
	private JComboBox<String> cbSemester, cbClassId, cbCourseId, cbTeacherId;

	public DeletExamDialog(JFrame f) {
		super(f, " 删除考试信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		oldClassCourse = new ClassCourse();
		newClassCourse = new ClassCourse();

		panel = new JPanel(null);
		lblSemester = new JLabel("学期:", RIGHT);
		lblClassId = new JLabel("班级代码:", RIGHT);
		lblCouId = new JLabel("课程代码:", RIGHT);
		lblTeaId = new JLabel("教师工号:", RIGHT);
		lblExamType = new JLabel("考试类型:", RIGHT);
		lblExamDate = new JLabel("考试日期:", RIGHT);
		lblExamTime = new JLabel("考试时间:", RIGHT);
		lblExamAddr = new JLabel("考试地点:", RIGHT);
		lblMsg = new JLabel("", CENTER);

		cbSemester = new JComboBox<>();
		cbSemester.addItem("选择学期");
		cbSemester.addItem("第一学期");
		cbSemester.addItem("第二学期");
		cbSemester.addItem("第三学期");
		cbSemester.addItem("第四学期");
		cbSemester.addItem("第五学期");
		cbSemester.addItem("第六学期");
		cbSemester.addItem("第七学期");
		cbSemester.addItem("第八学期");

		cbClassId = new JComboBox<>();
		try {
			cbClassId.removeAllItems();
			cbClassId.addItem("选择班级");
			for (ClassInfo ci : DButils.getAllClassInfoList()) {
				cbClassId.addItem(ci.getId());
			}
		} catch (Exception e2) {
		}

		cbCourseId = new JComboBox<>();
		try {
			cbCourseId.removeAllItems();
			cbCourseId.addItem("选择课程");
			for (CourseInfo ci : DButils.getAllCourseInfoList()) {
				cbCourseId.addItem(ci.getId());
			}
		} catch (Exception e2) {
		}
		cbTeacherId = new JComboBox<>();
		try {
			cbTeacherId.removeAllItems();
			cbTeacherId.addItem("选择教师");
			for (TeacherInfo ti : DButils.getAllTeacherInfoList()) {
				cbTeacherId.addItem(ti.getId());
			}
		} catch (Exception e2) {
		}

		txtExamType = new JTextField(20);
		txtExamDate = new JTextField(20);
		txtExamTime = new JTextField(20);
		txtExamAddr = new JTextField(20);
		txtExamType.setEditable(false);
		txtExamDate.setEditable(false);
		txtExamTime.setEditable(false);
		txtExamAddr.setEditable(false);

		cbSemester.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtExamType.setText("");
				txtExamDate.setText("");
				txtExamTime.setText("");
				txtExamAddr.setText("");
				try {
					oldClassCourse.setSemester(cbSemester.getSelectedItem().toString());
					oldClassCourse.setClassId((cbClassId.getSelectedItem().toString()));
					oldClassCourse.setCouId((cbCourseId.getSelectedItem().toString()));
					oldClassCourse.setTeaId((cbTeacherId.getSelectedItem().toString()));
					ClassCourse classCourse = DButils.getClassCourseFromClassIdAndSemesterAndCouIdAndTeaId(oldClassCourse.getClassId(),oldClassCourse.getSemester(),oldClassCourse.getCouId(),oldClassCourse.getTeaId());
					txtExamType.setText(classCourse.getExamType());
					txtExamDate.setText(classCourse.getExamDate());
					txtExamTime.setText(classCourse.getExamTime());
					txtExamAddr.setText(classCourse.getExamAddr());
				} catch (Exception e2) {
				}
			}
		});
		
		cbClassId.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtExamType.setText("");
				txtExamDate.setText("");
				txtExamTime.setText("");
				txtExamAddr.setText("");
				try {
					oldClassCourse.setSemester(cbSemester.getSelectedItem().toString());
					oldClassCourse.setClassId((cbClassId.getSelectedItem().toString()));
					oldClassCourse.setCouId((cbCourseId.getSelectedItem().toString()));
					oldClassCourse.setTeaId((cbTeacherId.getSelectedItem().toString()));
					ClassCourse classCourse = DButils.getClassCourseFromClassIdAndSemesterAndCouIdAndTeaId(oldClassCourse.getClassId(),oldClassCourse.getSemester(),oldClassCourse.getCouId(),oldClassCourse.getTeaId());
					txtExamType.setText(classCourse.getExamType());
					txtExamDate.setText(classCourse.getExamDate());
					txtExamTime.setText(classCourse.getExamTime());
					txtExamAddr.setText(classCourse.getExamAddr());
				} catch (Exception e2) {
				}
			}
		});
		
		cbCourseId.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtExamType.setText("");
				txtExamDate.setText("");
				txtExamTime.setText("");
				txtExamAddr.setText("");
				try {
					oldClassCourse.setSemester(cbSemester.getSelectedItem().toString());
					oldClassCourse.setClassId((cbClassId.getSelectedItem().toString()));
					oldClassCourse.setCouId((cbCourseId.getSelectedItem().toString()));
					oldClassCourse.setTeaId((cbTeacherId.getSelectedItem().toString()));
					ClassCourse classCourse = DButils.getClassCourseFromClassIdAndSemesterAndCouIdAndTeaId(oldClassCourse.getClassId(),oldClassCourse.getSemester(),oldClassCourse.getCouId(),oldClassCourse.getTeaId());
					txtExamType.setText(classCourse.getExamType());
					txtExamDate.setText(classCourse.getExamDate());
					txtExamTime.setText(classCourse.getExamTime());
					txtExamAddr.setText(classCourse.getExamAddr());
				} catch (Exception e2) {
				}
			}
		});
		
		cbTeacherId.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtExamType.setText("");
				txtExamDate.setText("");
				txtExamTime.setText("");
				txtExamAddr.setText("");
				try {
					oldClassCourse.setSemester(cbSemester.getSelectedItem().toString());
					oldClassCourse.setClassId((cbClassId.getSelectedItem().toString()));
					oldClassCourse.setCouId((cbCourseId.getSelectedItem().toString()));
					oldClassCourse.setTeaId((cbTeacherId.getSelectedItem().toString()));
					ClassCourse classCourse = DButils.getClassCourseFromClassIdAndSemesterAndCouIdAndTeaId(oldClassCourse.getClassId(),oldClassCourse.getSemester(),oldClassCourse.getCouId(),oldClassCourse.getTeaId());
					txtExamType.setText(classCourse.getExamType());
					txtExamDate.setText(classCourse.getExamDate());
					txtExamTime.setText(classCourse.getExamTime());
					txtExamAddr.setText(classCourse.getExamAddr());
				} catch (Exception e2) {
				}
			}
		});

		btnOK = new JButton("删除");
		btnCanel = new JButton("取消");

		lblMsg.setForeground(Color.RED);

		lblSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		lblClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblCouId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblTeaId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblExamType.setFont(new Font("宋体", Font.PLAIN, 20));
		lblExamDate.setFont(new Font("宋体", Font.PLAIN, 20));
		lblExamTime.setFont(new Font("宋体", Font.PLAIN, 20));
		lblExamAddr.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		cbClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		cbCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
		cbTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
		txtExamType.setFont(new Font("宋体", Font.PLAIN, 20));
		txtExamDate.setFont(new Font("宋体", Font.PLAIN, 20));
		txtExamTime.setFont(new Font("宋体", Font.PLAIN, 20));
		txtExamAddr.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newClassCourse.setSemester(cbSemester.getSelectedItem().toString());
				newClassCourse.setClassId(cbClassId.getSelectedItem().toString());
				newClassCourse.setCouId(cbCourseId.getSelectedItem().toString());
				newClassCourse.setTeaId(cbTeacherId.getSelectedItem().toString());
				newClassCourse.setExamType(txtExamType.getText());
				newClassCourse.setExamDate(txtExamDate.getText());
				newClassCourse.setExamTime(txtExamTime.getText());
				newClassCourse.setExamAddr(txtExamAddr.getText());

				DButils.deleteClassCourseFromSemesterAndClassIdAndCouIdAndTeaId(newClassCourse.getSemester(), newClassCourse.getClassId(), newClassCourse.getCouId(), newClassCourse.getTeaId());
				lblMsg.setText("考试信息删除成功");
			}
		});
		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.setSize(ScreenSize.WIDTH / 3, ScreenSize.HEIGHT / 2);
		panel.setSize(this.getSize());

		lblSemester.setBounds(panel.getSize().width / 4, panel.getSize().height / 12, 2 * panel.getSize().width / 10,
				35);
		cbSemester.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 12,
				3 * panel.getSize().width / 10, 35);
		lblClassId.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 12, 2 * panel.getSize().width / 10,
				35);
		cbClassId.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 12,
				3 * panel.getSize().width / 10, 35);
		lblCouId.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 12, 2 * panel.getSize().width / 10,
				35);
		cbCourseId.setBounds(9 * panel.getSize().width / 20, 3 * panel.getSize().height / 12,
				3 * panel.getSize().width / 10, 35);
		lblTeaId.setBounds(panel.getSize().width / 4, 4 * panel.getSize().height / 12, 2 * panel.getSize().width / 10,
				35);
		cbTeacherId.setBounds(9 * panel.getSize().width / 20, 4 * panel.getSize().height / 12,
				3 * panel.getSize().width / 10, 35);
		lblExamType.setBounds(panel.getSize().width / 4, 5 * panel.getSize().height / 12,
				2 * panel.getSize().width / 10, 35);
		txtExamType.setBounds(9 * panel.getSize().width / 20, 5 * panel.getSize().height / 12,
				3 * panel.getSize().width / 10, 35);
		lblExamDate.setBounds(panel.getSize().width / 4, 6 * panel.getSize().height / 12,
				2 * panel.getSize().width / 10, 35);
		txtExamDate.setBounds(9 * panel.getSize().width / 20, 6 * panel.getSize().height / 12,
				3 * panel.getSize().width / 10, 35);
		lblExamTime.setBounds(panel.getSize().width / 4, 7 * panel.getSize().height / 12,
				2 * panel.getSize().width / 10, 35);
		txtExamTime.setBounds(9 * panel.getSize().width / 20, 7 * panel.getSize().height / 12,
				3 * panel.getSize().width / 10, 35);
		lblExamAddr.setBounds(panel.getSize().width / 4, 8 * panel.getSize().height / 12,
				2 * panel.getSize().width / 10, 35);
		txtExamAddr.setBounds(9 * panel.getSize().width / 20, 8 * panel.getSize().height / 12,
				3 * panel.getSize().width / 10, 35);
		lblMsg.setBounds(panel.getSize().width / 4, 9 * panel.getSize().height / 12, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 6, 10 * panel.getSize().height / 12, panel.getSize().width / 6, 35);
		btnCanel.setBounds(3 * panel.getSize().width / 6, 10 * panel.getSize().height / 12, panel.getSize().width / 6,
				35);

		panel.add(lblSemester);
		panel.add(lblClassId);
		panel.add(lblCouId);
		panel.add(lblTeaId);
		panel.add(lblExamType);
		panel.add(lblExamDate);
		panel.add(lblExamTime);
		panel.add(lblExamAddr);
		panel.add(cbSemester);
		panel.add(cbClassId);
		panel.add(cbCourseId);
		panel.add(cbTeacherId);
		panel.add(txtExamType);
		panel.add(txtExamDate);
		panel.add(txtExamTime);
		panel.add(txtExamAddr);
		panel.add(lblMsg);
		panel.add(btnOK);
		panel.add(btnCanel);

		this.add(panel);
		this.pack();
		this.setSize(ScreenSize.WIDTH / 3, ScreenSize.HEIGHT / 2);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}