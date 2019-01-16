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
import team9.model.CourseInfo;
import team9.model.ScreenSize;

@SuppressWarnings("serial")
public class AlterCourseInfoDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblCourseId, lblCourseName, lblCourseType, lblCourseCreditHour, lblCourseFaculty, lblMsg;
	private JTextField txtCourseName, txtCourseType, txtCourseCreditHour, txtCourseFaculty;
	private JButton btnOK, btnCanel;
	private JComboBox<String> cbCourseId;
	private CourseInfo oldCourseInfo, newCourseInfo;

	public AlterCourseInfoDialog(JFrame f) {
		super(f, " 修改课程信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		newCourseInfo = new CourseInfo();

		panel = new JPanel(null);
		lblCourseId = new JLabel("课程代码:", RIGHT);
		lblCourseName = new JLabel("课程名称:", RIGHT);
		lblCourseType = new JLabel("课程类别:", RIGHT);
		lblCourseCreditHour = new JLabel("学分:", RIGHT);
		lblCourseFaculty = new JLabel("开课院系:", RIGHT);
		lblMsg = new JLabel("", CENTER);
		
		cbCourseId = new JComboBox<>();
		try {
			cbCourseId.removeAllItems();
			cbCourseId.addItem("选择课程");
			for (CourseInfo ci : DButils.getAllCourseInfoList()) {
				cbCourseId.addItem(ci.getId());
			}
		} catch (Exception e2) {
		}

		txtCourseName = new JTextField(20);
		txtCourseType = new JTextField(20);
		txtCourseCreditHour = new JTextField(20);
		txtCourseFaculty = new JTextField(20);

		btnOK = new JButton("修改");
		btnCanel = new JButton("取消");

		lblMsg.setForeground(Color.RED);

		lblCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblCourseName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblCourseType.setFont(new Font("宋体", Font.PLAIN, 20));
		lblCourseCreditHour.setFont(new Font("宋体", Font.PLAIN, 20));
		lblCourseFaculty.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
		txtCourseName.setFont(new Font("宋体", Font.PLAIN, 20));
		txtCourseType.setFont(new Font("宋体", Font.PLAIN, 20));
		txtCourseCreditHour.setFont(new Font("宋体", Font.PLAIN, 20));
		txtCourseFaculty.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		cbCourseId.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				oldCourseInfo = DButils.getCourseInfoFromId(cbCourseId.getSelectedItem().toString());
				txtCourseName.setText(oldCourseInfo.getCouName());
				txtCourseType.setText(oldCourseInfo.getCouType());
				txtCourseCreditHour.setText(oldCourseInfo.getCreditHour());
				txtCourseFaculty.setText(oldCourseInfo.getCouFaculty());
			}
		});;
		
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newCourseInfo.setId(cbCourseId.getSelectedItem().toString());
				newCourseInfo.setCouName(txtCourseName.getText());
				newCourseInfo.setCouType(txtCourseType.getText());
				newCourseInfo.setCreditHour(txtCourseCreditHour.getText());
				newCourseInfo.setCouFaculty(txtCourseFaculty.getText());

				if (newCourseInfo.getId() == null || newCourseInfo.getId().equals("")) {
					lblMsg.setText("课程代码不能为空！");
				} else if (newCourseInfo.getCouName() == null || newCourseInfo.getCouName().equals("")) {
					lblMsg.setText("课程名称不能为空！");
				} else {
					if (newCourseInfo.getId().matches("^[A-Z0-9]{6}$")) {
						DButils.updateCourseInfo(newCourseInfo);
						lblMsg.setText("课程信息修改成功");
					} else {
						lblMsg.setText("课程代码格式错误！");
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

		this.setSize(ScreenSize.WIDTH / 3, ScreenSize.HEIGHT / 2);
		panel.setSize(this.getSize());

		lblCourseId.setBounds(panel.getSize().width / 4, panel.getSize().height / 9, 2 * panel.getSize().width / 10,
				35);
		cbCourseId.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 9,
				3 * panel.getSize().width / 10, 35);
		lblCourseName.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 9,
				2 * panel.getSize().width / 10, 35);
		txtCourseName.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 9,
				3 * panel.getSize().width / 10, 35);
		lblCourseType.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 9,
				2 * panel.getSize().width / 10, 35);
		txtCourseType.setBounds(9 * panel.getSize().width / 20, 3 * panel.getSize().height / 9,
				3 * panel.getSize().width / 10, 35);
		lblCourseCreditHour.setBounds(panel.getSize().width / 4, 4 * panel.getSize().height / 9,
				2 * panel.getSize().width / 10, 35);
		txtCourseCreditHour.setBounds(9 * panel.getSize().width / 20, 4 * panel.getSize().height / 9,
				3 * panel.getSize().width / 10, 35);
		lblCourseFaculty.setBounds(panel.getSize().width / 4, 5 * panel.getSize().height / 9,
				2 * panel.getSize().width / 10, 35);
		txtCourseFaculty.setBounds(9 * panel.getSize().width / 20, 5 * panel.getSize().height / 9,
				3 * panel.getSize().width / 10, 35);
		lblMsg.setBounds(panel.getSize().width / 4, 6 * panel.getSize().height / 9, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 6, 7 * panel.getSize().height / 9, panel.getSize().width / 6, 35);
		btnCanel.setBounds(3 * panel.getSize().width / 6, 7 * panel.getSize().height / 9, panel.getSize().width / 6,
				35);

		panel.add(lblCourseId);
		panel.add(lblCourseName);
		panel.add(lblCourseType);
		panel.add(lblCourseCreditHour);
		panel.add(lblCourseFaculty);
		panel.add(cbCourseId);
		panel.add(txtCourseName);
		panel.add(txtCourseType);
		panel.add(txtCourseCreditHour);
		panel.add(txtCourseFaculty);
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