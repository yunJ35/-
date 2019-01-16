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
import team9.model.ScreenSize;

@SuppressWarnings("serial")
public class EntryUserInfoDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblUserName, lblRoleInfo, lblMsg;
	private JTextField txtUserName;
	JComboBox<String> cbRoleInfo;
	private JButton btnOK, btnCanel;

	public EntryUserInfoDialog(JFrame f) {
		super(f, " 添加用户信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);
		lblUserName = new JLabel("用户名", CENTER);
		lblRoleInfo = new JLabel("角色", CENTER);
		lblMsg = new JLabel("", CENTER);

		txtUserName = new JTextField(20);

		btnOK = new JButton("确定");
		btnCanel = new JButton("取消");

		cbRoleInfo = new JComboBox<String>();
		cbRoleInfo.addItem("manager");
		cbRoleInfo.addItem("teacher");
		cbRoleInfo.addItem("student");

		lblMsg.setForeground(Color.RED);

		lblUserName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblRoleInfo.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		txtUserName.setFont(new Font("宋体", Font.PLAIN, 20));
		cbRoleInfo.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = txtUserName.getText();
				String roleInfo = cbRoleInfo.getSelectedItem().toString();
				if (userName.length() == 12 || userName.length() == 6 || userName.length() == 4) {
					if (userName.matches("^[0-9]{6}$") && roleInfo.equals("teacher")) {
						DButils.insertUser(userName, "p" + userName, roleInfo);
						lblMsg.setText("用户添加成功");
					} else if (userName.matches("^[0-9]{12}$") && roleInfo.equals("student")) {
						DButils.insertUser(userName, userName.substring(6), roleInfo);
						lblMsg.setText("用户添加成功");
					} else if (userName.matches("^[a-zA-Z]{4}$") && roleInfo.equals("manager")) {
						DButils.insertUser(userName, userName, roleInfo);
						lblMsg.setText("用户添加成功");
					} else {
						lblMsg.setText("用户名格式错误或者角色选择错误！");
					}
				} else {
					lblMsg.setText("用户名格式错误！");
				}
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
		
		lblUserName.setBounds(panel.getSize().width / 4, panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		txtUserName.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 6,
				3 * panel.getSize().width / 10, 35);
		lblRoleInfo.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		cbRoleInfo.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 6,
				3 * panel.getSize().width / 10, 35);
		lblMsg.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 6, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7, 35);
		btnCanel.setBounds(4 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7,
				35);

		panel.add(lblUserName);
		panel.add(lblRoleInfo);
		panel.add(txtUserName);
		panel.add(cbRoleInfo);
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