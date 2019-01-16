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
import team9.model.StudentInfo;

@SuppressWarnings("serial")
public class DeletStudentDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblStudentId, lblStudentName, lblMsg;
	private JTextField txtStudentName;
	private JComboBox<String> cbStudentId;
	private JButton btnOK, btnCanel;

	public DeletStudentDialog(JFrame f) {
		super(f, " 删除学生信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);
		lblStudentId = new JLabel("学号:", CENTER);
		lblStudentName = new JLabel("姓名:", CENTER);
		lblMsg = new JLabel("", CENTER);

		txtStudentName = new JTextField(20);
		txtStudentName.setEditable(false);
		
		btnOK = new JButton("删除");
		btnCanel = new JButton("取消");

		cbStudentId = new JComboBox<>();
		try {
			cbStudentId.removeAllItems();
			cbStudentId.addItem("选择学号");
			for (StudentInfo si : DButils.getAllStudentInfoList()) {
				cbStudentId.addItem(si.getId());
			}
		} catch (Exception e2) {
		}

		cbStudentId.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					txtStudentName.setText(DButils.getStudentNameFromeId(cbStudentId.getSelectedItem().toString()));
				} catch (Exception e2) {
				}
			}
		});
		
		lblMsg.setForeground(Color.RED);

		lblStudentId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentName.setFont(new Font("宋体", Font.PLAIN, 20));
		txtStudentName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbStudentId.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentId = cbStudentId.getSelectedItem().toString();
				DButils.deleteResultInfoFromStudentId(studentId);
				DButils.deleteStudent(studentId);
				DButils.deleteUser(studentId);
				lblMsg.setText("学生信息删除成功");
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

		lblStudentId.setBounds(panel.getSize().width / 4, panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		cbStudentId.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 6, 3 * panel.getSize().width / 10,
				35);
		lblStudentName.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		txtStudentName.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 6, 3 * panel.getSize().width / 10,
				35);
		lblMsg.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 6, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7, 35);
		btnCanel.setBounds(4 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7,
				35);

		panel.add(lblStudentId);
		panel.add(cbStudentId);
		panel.add(lblStudentName);
		panel.add(txtStudentName);
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