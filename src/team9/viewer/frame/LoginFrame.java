package team9.viewer.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import team9.control.utils.MD5Utils;
import team9.control.utils.UserVerify;
import team9.model.User;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements SwingConstants {
	private JPanel panel;
	private JLabel lblBackground, lblLogo, lblName, lblPwd, lblMsg;
	private JTextField txtName;
	private JPasswordField txtPwd;
	private JButton btnOK;

	public LoginFrame() {
		super(" 教务系统登录");

		ImageIcon icon = new ImageIcon("lib/images/Users.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);

		lblBackground = new JLabel(new ImageIcon("lib/images/background.png"), CENTER);
		lblLogo = new JLabel("学生教务系统", new ImageIcon("lib/images/Users.png"), CENTER);
		lblName = new JLabel(new ImageIcon("lib/images/User.png"), CENTER);
		lblPwd = new JLabel(new ImageIcon("lib/images/Lock.png"), CENTER);
		lblMsg = new JLabel("", CENTER);
		lblMsg.setForeground(Color.red);

		txtName = new JTextField(20);
		txtPwd = new JPasswordField(20);
		txtPwd.setEchoChar('*');

		btnOK = new JButton("登录");

		lblLogo.setFont(new Font("黑体", Font.PLAIN, 35));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		txtName.setFont(new Font("宋体", Font.PLAIN, 20));
		txtPwd.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		lblBackground.setBounds(0, 0, 600, 400);
		lblLogo.setBounds(100, 30, 400, 70);
		lblName.setBounds(120, 130, 40, 40);
		txtName.setBounds(170, 130, 300, 40);
		lblPwd.setBounds(120, 180, 40, 40);
		txtPwd.setBounds(170, 180, 300, 40);
		lblMsg.setBounds(200, 220, 200, 50);
		btnOK.setBounds(250, 270, 100, 50);
		panel.add(lblLogo);
		panel.add(lblName);
		panel.add(txtName);
		panel.add(lblPwd);
		panel.add(txtPwd);
		panel.add(lblMsg);
		panel.add(btnOK);
		panel.add(lblBackground);

		txtName.setBackground(Color.lightGray);
		txtPwd.setBackground(Color.lightGray);

		this.add(panel);
		this.setSize(600, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void login() {
		String userName = txtName.getText();
		String password = new String(txtPwd.getPassword());

		if (userName == null || userName.equals("")) {
			JOptionPane.showMessageDialog(null, "\n用户名不能为空！\n ", "错误信息", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (password == null || password.equals("")) {
			JOptionPane.showMessageDialog(null, "\n密码不能为空！\n ", "错误信息", JOptionPane.ERROR_MESSAGE);
			return;
		} else if(UserVerify.userVerify(userName, MD5Utils.md5(password))){
			LoginFrame.this.setVisible(false);
			User.setUserName(userName);
			User.setPassword(MD5Utils.md5(password));
			User.setRoleInfo(UserVerify.userQuerry(userName));
			frameJump(userName);
		} else {
			JOptionPane.showMessageDialog(null, "\n用户名或密码输入错误,请重新输入！\n ", "错误信息", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private void frameJump(String userName) {
		switch (UserVerify.userQuerry(userName)) {
		case "manager":
			new ManagerFrame();
			break;
		case "student":
			new StudentFrame();
			break;
		case "teacher":
			new TeacherFrame();
			break;
		default:
			// 错误窗口, 用户信息错误，请联系
			break;
		}		
	}
}