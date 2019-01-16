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
import team9.model.ScreenSize;

@SuppressWarnings("serial")
public class DeletClassScheduleDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblClassId, lblClassName, lblSemester, lblMsg;
	private JTextField txtClassName;
	private JComboBox<String> cbClassId, cbSemester;
	private JButton btnOK, btnCanel;
	private ClassCourse classCourse;

	public DeletClassScheduleDialog(JFrame f) {
		super(f, " 删除课表信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		classCourse = new ClassCourse();
		
		panel = new JPanel(null);
		lblClassId = new JLabel("班级id:", RIGHT);
		lblClassName = new JLabel("班级名称:", RIGHT);
		lblSemester = new JLabel("学期:", RIGHT);
		lblMsg = new JLabel("", CENTER);

		txtClassName = new JTextField(20);
		txtClassName.setEditable(false);
		
		btnOK = new JButton("删除");
		btnCanel = new JButton("取消");

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

		cbSemester.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				classCourse.setSemester(cbSemester.getSelectedItem().toString());
			}
		});
		cbClassId.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				txtClassName.setText(DButils.getClassNameFromId(cbClassId.getSelectedItem().toString()));
			}
		});
		
		lblMsg.setForeground(Color.RED);

		lblClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblClassName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		txtClassName.setFont(new Font("宋体", Font.PLAIN, 20));
		cbSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		cbClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String classId = cbClassId.getSelectedItem().toString();
				String semester = cbSemester.getSelectedItem().toString();
				DButils.deleteClassScheduleInfoFromClassIdAndSemester(classId, semester);
				lblMsg.setText("课程信息删除成功");
			}
		});
		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.setSize(ScreenSize.WIDTH / 3, ScreenSize.HEIGHT / 3);
		panel.setSize(this.getSize());

		lblSemester.setBounds(panel.getSize().width / 4, panel.getSize().height / 7, 2 * panel.getSize().width / 10,
				35);
		cbSemester.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 7, 3 * panel.getSize().width / 10, 35);
		lblClassId.setBounds(panel.getSize().width / 4, 2* panel.getSize().height / 7, 2 * panel.getSize().width / 10,
				35);
		cbClassId.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 7, 3 * panel.getSize().width / 10, 35);
		lblClassName.setBounds(panel.getSize().width / 4, 3*panel.getSize().height / 7, 2 * panel.getSize().width / 10,
				35);
		txtClassName.setBounds(9 * panel.getSize().width / 20, 3 * panel.getSize().height / 7, 3 * panel.getSize().width / 10,
				35);
		lblMsg.setBounds(panel.getSize().width / 4, 4 * panel.getSize().height / 7, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 7, 5 * panel.getSize().height / 7, panel.getSize().width / 7, 35);
		btnCanel.setBounds(4 * panel.getSize().width / 7, 5 * panel.getSize().height / 7, panel.getSize().width / 7,
				35);

		panel.add(lblClassId);
		panel.add(lblClassName);
		panel.add(lblSemester);
		panel.add(txtClassName);
		panel.add(cbSemester);
		panel.add(cbClassId);
		panel.add(lblMsg);
		panel.add(btnOK);
		panel.add(btnCanel);

		this.add(panel);
		this.pack();
		this.setSize(ScreenSize.WIDTH / 3, ScreenSize.HEIGHT / 3);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}