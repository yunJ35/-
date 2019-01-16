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
import team9.model.ScreenSize;
import team9.model.TeacherInfo;

@SuppressWarnings("serial")
public class DeletTeacherDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblTeacherId, lblTeacherName, lblMsg;
	private JTextField txtTeacherName;
	private JComboBox<String> cbTeacherId;
	private JButton btnOK, btnCanel;

	public DeletTeacherDialog(JFrame f) {
		super(f, " 删除教师信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);
		lblTeacherId = new JLabel("工号:", CENTER);
		lblTeacherName = new JLabel("姓名:", CENTER);
		lblMsg = new JLabel("", CENTER);

		txtTeacherName = new JTextField(20);
		txtTeacherName.setEditable(false);
		
		btnOK = new JButton("删除");
		btnCanel = new JButton("取消");

		cbTeacherId = new JComboBox<>();
		try {
			cbTeacherId.removeAllItems();
			cbTeacherId.addItem("选择工号");
			for (TeacherInfo ti : DButils.getAllTeacherInfoList()) {
				cbTeacherId.addItem(ti.getId());
			}
		} catch (Exception e2) {
		}

		cbTeacherId.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					txtTeacherName.setText(DButils.getTeacherNameFromeId(cbTeacherId.getSelectedItem().toString()));
				} catch (Exception e2) {
				}
			}
		});
		
		lblMsg.setForeground(Color.RED);

		lblTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblTeacherName.setFont(new Font("宋体", Font.PLAIN, 20));
		txtTeacherName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String teacherId = cbTeacherId.getSelectedItem().toString();
				DButils.deleteTeacher(teacherId);
				DButils.deleteUser(teacherId);
				lblMsg.setText("教师信息删除成功");
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

		lblTeacherId.setBounds(panel.getSize().width / 4, panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		cbTeacherId.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 6, 3 * panel.getSize().width / 10,
				35);
		lblTeacherName.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		txtTeacherName.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 6, 3 * panel.getSize().width / 10,
				35);
		lblMsg.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 6, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7, 35);
		btnCanel.setBounds(4 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7,
				35);

		panel.add(lblTeacherId);
		panel.add(cbTeacherId);
		panel.add(lblTeacherName);
		panel.add(txtTeacherName);
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