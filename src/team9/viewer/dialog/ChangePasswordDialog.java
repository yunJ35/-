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
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import team9.control.utils.DButils;
import team9.model.User;

@SuppressWarnings("serial")
public class ChangePasswordDialog extends JDialog implements SwingConstants {
	JPanel p;
	JLabel lblName, lbluserName, lblPwd, lblRePwd, lblMsg;
	JPasswordField txtPwd, txtRePwd;
	JButton btnOK, btnCanel;

	public ChangePasswordDialog(JFrame f) {
		super(f, "修改密码", true);

		// 设置窗体的icon
		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		p = new JPanel(null);
		lblName = new JLabel("用户名：");
		lbluserName = new JLabel(User.getUserName());
		lblPwd = new JLabel("新的密码：");
		lblRePwd = new JLabel("确认密码：");
		lblMsg = new JLabel("", CENTER);
		txtPwd = new JPasswordField(20);
		txtRePwd = new JPasswordField(20);
		btnOK = new JButton("修改");
		btnCanel = new JButton("取消");

		lblMsg.setForeground(Color.red);

		lblName.setFont(new Font("宋体", Font.PLAIN, 20));
		lbluserName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblPwd.setFont(new Font("宋体", Font.PLAIN, 20));
		lblRePwd.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 15));
		txtPwd.setFont(new Font("宋体", Font.PLAIN, 20));
		txtRePwd.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String strPwd = new String(txtPwd.getPassword());
				String strRePwd = new String(txtRePwd.getPassword());
				if (strPwd == null || strPwd.equals("")) {
					lblMsg.setText("请输入新的密码！");
					return;
				} else if (strRePwd == null || strRePwd.equals("")) {
					lblMsg.setText("请输入确认密码！");
					return;
				} else if (!strRePwd.equals(strPwd)) {
					lblMsg.setText("两次输入的密码不相同，请重新输入！");
					return;
				} else {
					DButils.updateUserPassword(User.getUserName(), strPwd);
					setVisible(false);
				}
			}
		});
		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		lblName.setBounds(10, 10, 100, 30);
		lbluserName.setBounds(120, 10, 200, 30);
		lblPwd.setBounds(10, 50, 100, 30);
		txtPwd.setBounds(120, 50, 200, 30);
		lblRePwd.setBounds(10, 90, 100, 30);
		txtRePwd.setBounds(120, 90, 200, 30);
		lblMsg.setBounds(20, 130, 300, 30);
		btnOK.setBounds(70, 170, 100, 30);
		btnCanel.setBounds(180, 170, 100, 30);
		p.add(lblName);
		p.add(lbluserName);
		p.add(lblPwd);
		p.add(txtPwd);
		p.add(lblRePwd);
		p.add(txtRePwd);
		p.add(lblMsg);
		p.add(btnOK);
		p.add(btnCanel);
		this.add(p);
		this.pack();
		this.setSize(350, 270);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}