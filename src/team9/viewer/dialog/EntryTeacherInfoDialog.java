package team9.viewer.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import team9.control.utils.DButils;
import team9.model.ScreenSize;

@SuppressWarnings("serial")
public class EntryTeacherInfoDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblTeacherId, lblTeacherName, lblMsg;
	private JTextField txtTeacherId, txtTeacherName;
	private JButton btnOK, btnCanel;

	public EntryTeacherInfoDialog(JFrame f) {
		super(f, " 添加教师信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);
		lblTeacherId = new JLabel("工号:", CENTER);
		lblTeacherName = new JLabel("姓名:", CENTER);
		lblMsg = new JLabel("", CENTER);

		txtTeacherId = new JTextField(20);
		txtTeacherName = new JTextField(20);

		btnOK = new JButton("确定");
		btnCanel = new JButton("取消");

		lblMsg.setForeground(Color.RED);

		lblTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblTeacherName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		txtTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
		txtTeacherName.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String teacherId = txtTeacherId.getText();
				String teacherName = txtTeacherName.getText();

				if (teacherId == null || teacherId.equals("")) {
					lblMsg.setText("工号不能为空！");
				} else if (teacherName == null || teacherName.equals("")) {
					lblMsg.setText("姓名不能为空！");
				} else {
					if (teacherId.matches("^[0-9]{6}$")) {
						DButils.insertTeacher(teacherId, teacherName);
						DButils.insertUser(teacherId, "p" + teacherId, "teacher");
						lblMsg.setText("教师信息添加成功");
					} else {
						lblMsg.setText("工号格式错误！");
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

		this.setSize(ScreenSize.WIDTH / 4, ScreenSize.HEIGHT / 4);
		panel.setSize(this.getSize());

		lblTeacherId.setBounds(panel.getSize().width / 4, panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		txtTeacherId.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 6,
				3 * panel.getSize().width / 10, 35);
		lblTeacherName.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 6,
				2 * panel.getSize().width / 10, 35);
		txtTeacherName.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 6,
				3 * panel.getSize().width / 10, 35);
		lblMsg.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 6, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 6, 4 * panel.getSize().height / 6, panel.getSize().width / 6, 35);
		btnCanel.setBounds(3 * panel.getSize().width / 6, 4 * panel.getSize().height / 6, panel.getSize().width / 6,
				35);

		panel.add(lblTeacherId);
		panel.add(lblTeacherName);
		panel.add(txtTeacherId);
		panel.add(txtTeacherName);
		panel.add(lblMsg);
		panel.add(btnOK);
		panel.add(btnCanel);

		this.add(panel);
		this.pack();
		this.setSize(ScreenSize.WIDTH / 4, ScreenSize.HEIGHT / 4);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}