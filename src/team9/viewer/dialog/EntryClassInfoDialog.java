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
public class EntryClassInfoDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblClassId, lblClassName, lblMsg;
	private JTextField txtClassId, txtClassName;
	private JButton btnOK, btnCanel;

	public EntryClassInfoDialog(JFrame f) {
		super(f, " 添加班级信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);
		lblClassId = new JLabel("编号:", CENTER);
		lblClassName = new JLabel("名称:", CENTER);
		lblMsg = new JLabel("", CENTER);

		txtClassId = new JTextField(20);
		txtClassName = new JTextField(20);

		btnOK = new JButton("确定");
		btnCanel = new JButton("取消");

		lblMsg.setForeground(Color.RED);

		lblClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblClassName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		txtClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		txtClassName.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String classId = txtClassId.getText();
				String className = txtClassName.getText();

				if (classId == null || classId.equals("")) {
					lblMsg.setText("编号不能为空！");
				} else if (className == null || className.equals("")) {
					lblMsg.setText("名称不能为空！");
				} else {
					if (classId.matches("^[0-9]{10}$")) {
						DButils.insertClass(classId, className, "0");
						lblMsg.setText("班级信息添加成功");
					} else {
						lblMsg.setText("编号格式错误！");
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

		lblClassId.setBounds(panel.getSize().width / 4, panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		txtClassId.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 6,
				3 * panel.getSize().width / 10, 35);
		lblClassName.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 6,
				2 * panel.getSize().width / 10, 35);
		txtClassName.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 6,
				3 * panel.getSize().width / 10, 35);
		lblMsg.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 6, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 6, 4 * panel.getSize().height / 6, panel.getSize().width / 6, 35);
		btnCanel.setBounds(3 * panel.getSize().width / 6, 4 * panel.getSize().height / 6, panel.getSize().width / 6,
				35);

		panel.add(lblClassId);
		panel.add(lblClassName);
		panel.add(txtClassId);
		panel.add(txtClassName);
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