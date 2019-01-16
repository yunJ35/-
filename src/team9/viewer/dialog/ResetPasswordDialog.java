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
import javax.swing.SwingConstants;

import team9.control.utils.DButils;
import team9.model.ScreenSize;
import team9.model.UserInfo;

@SuppressWarnings("serial")
public class ResetPasswordDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblUserName, lblMsg;
	JComboBox<String> cbUserName;
	private JButton btnOK, btnCanel;

	public ResetPasswordDialog(JFrame f) {
		super(f, " 重置用户密码", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);
		lblUserName = new JLabel("用户名", CENTER);
		lblMsg = new JLabel("", CENTER);

		btnOK = new JButton("重置");
		btnCanel = new JButton("取消");

		cbUserName = new JComboBox<>();
		for (UserInfo userInfo : DButils.getAllUserInfoList()) {
			try {
				cbUserName.addItem(userInfo.getUserName());
			} catch (Exception e2) {
			}
		}
		cbUserName.setEditable(true);

		lblMsg.setForeground(Color.RED);

		lblUserName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbUserName.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = cbUserName.getSelectedItem().toString();
				if (userName.matches("^[0-9]{6}$")) {
					DButils.updateUserPassword(userName, "p" + userName);
					lblMsg.setText("密码重置成功");
				} else if (userName.matches("^[0-9]{12}$")) {
					DButils.updateUserPassword(userName, userName.substring(6));
					lblMsg.setText("密码重置成功");
				} else if (userName.matches("^[a-zA-Z]{4}$")) {
					DButils.updateUserPassword(userName, userName);
					lblMsg.setText("密码重置成功");
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
		cbUserName.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 6, 3 * panel.getSize().width / 10,
				35);
		lblMsg.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 6, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7, 35);
		btnCanel.setBounds(4 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7,
				35);

		panel.add(lblUserName);
		panel.add(cbUserName);
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