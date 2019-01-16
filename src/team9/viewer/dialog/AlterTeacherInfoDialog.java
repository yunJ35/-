package team9.viewer.dialog;

import java.awt.CardLayout;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import team9.control.utils.DButils;
import team9.model.ScreenSize;
import team9.model.TeacherInfo;

@SuppressWarnings("serial")
public class AlterTeacherInfoDialog extends JDialog implements SwingConstants {
	private JPanel panel, pAlterName, pAlterId;
	private JTabbedPane showPane;
	private JLabel lblTeacherId, lblTeacherName, lblAlterIdMsg, lblAlterNameMsg;
	private JTextField txtTeacherId, txtTeacherName;
	private JButton btnOK, btnCanel;
	private JComboBox<String> cbTeacherId, cbTeacherName;

	public AlterTeacherInfoDialog(JFrame f) {
		super(f, " 修改教师信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel();

		panel.setLayout(new CardLayout());
		showPane = new JTabbedPane(JTabbedPane.TOP);

		panel.add(showPane);

		this.setSize(ScreenSize.WIDTH / 4, ScreenSize.HEIGHT / 4);
		panel.setSize(this.getSize());

		initAlterNameGUI();
		initAlterIdGUI();

		this.add(panel);
		this.pack();
		this.setSize(ScreenSize.WIDTH / 4, ScreenSize.HEIGHT / 4);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	private void initAlterNameGUI() {

		pAlterName = new JPanel(null);
		showPane.addTab("<html><font size=4>修改教师姓名</font></html>", pAlterName);

		lblTeacherId = new JLabel("工号:", CENTER);
		lblTeacherName = new JLabel("姓名:", CENTER);
		lblAlterNameMsg = new JLabel("", CENTER);

		cbTeacherId = new JComboBox<String>();
		txtTeacherName = new JTextField(20);

		btnOK = new JButton("确定");
		btnCanel = new JButton("取消");

		lblAlterNameMsg.setForeground(Color.RED);

		lblTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblTeacherName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblAlterNameMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
		txtTeacherName.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

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

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String teacherId = cbTeacherId.getSelectedItem().toString();
				String teacherName = txtTeacherName.getText();

				if (teacherName == null || teacherName.equals("")) {
					lblAlterNameMsg.setText("姓名不能为空！");
				} else {
					DButils.updateTeacherName(teacherId, teacherName);
					lblAlterNameMsg.setText("教师信息修改成功");
				}
			}
		});

		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		pAlterName.setSize(panel.getSize());

		lblTeacherId.setBounds(pAlterName.getSize().width / 4, pAlterName.getSize().height / 7,
				2 * pAlterName.getSize().width / 10, 35);
		cbTeacherId.setBounds(9 * pAlterName.getSize().width / 20, pAlterName.getSize().height / 7,
				3 * pAlterName.getSize().width / 10, 35);
		lblTeacherName.setBounds(pAlterName.getSize().width / 4, 2 * pAlterName.getSize().height / 7,
				2 * pAlterName.getSize().width / 10, 35);
		txtTeacherName.setBounds(9 * pAlterName.getSize().width / 20, 2 * pAlterName.getSize().height / 7,
				3 * pAlterName.getSize().width / 10, 35);
		lblAlterNameMsg.setBounds(pAlterName.getSize().width / 4, 3 * pAlterName.getSize().height / 7,
				pAlterName.getSize().width / 2, 35);
		btnOK.setBounds(2 * pAlterName.getSize().width / 6, 4 * pAlterName.getSize().height / 7,
				pAlterName.getSize().width / 6, 35);
		btnCanel.setBounds(3 * pAlterName.getSize().width / 6, 4 * pAlterName.getSize().height / 7,
				pAlterName.getSize().width / 6, 35);

		pAlterName.add(lblTeacherId);
		pAlterName.add(lblTeacherName);
		pAlterName.add(cbTeacherId);
		pAlterName.add(txtTeacherName);
		pAlterName.add(lblAlterNameMsg);
		pAlterName.add(btnOK);
		pAlterName.add(btnCanel);
	}

	private void initAlterIdGUI() {

		pAlterId = new JPanel(null);
		showPane.addTab("<html><font size=4>修改教师工号</font></html>", pAlterId);

		lblTeacherId = new JLabel("工号:", CENTER);
		lblTeacherName = new JLabel("姓名:", CENTER);
		lblAlterIdMsg = new JLabel("", CENTER);

		cbTeacherName = new JComboBox<String>();

		txtTeacherId = new JTextField(20);

		btnOK = new JButton("确定");
		btnCanel = new JButton("取消");

		lblAlterIdMsg.setForeground(Color.RED);

		lblTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblTeacherName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblAlterIdMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbTeacherName.setFont(new Font("宋体", Font.PLAIN, 20));
		txtTeacherId.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		try {
			cbTeacherName.removeAllItems();
			cbTeacherName.addItem("选择姓名");
			for (TeacherInfo ti : DButils.getAllTeacherInfoList()) {
				cbTeacherName.addItem(ti.getName());
			}
		} catch (Exception e2) {
		}

		cbTeacherName.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					txtTeacherId.setText(DButils.getTeacherIdFromeName(cbTeacherName.getSelectedItem().toString()));
				} catch (Exception e2) {
				}
			}
		});

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String teacherId = txtTeacherId.getText();
				String teacherName = cbTeacherName.getSelectedItem().toString();

				if (teacherId == null || teacherId.equals("")) {
					lblAlterIdMsg.setText("工号不能为空！");
				} else {
					DButils.updateUserId(DButils.getTeacherIdFromeName(cbTeacherName.getSelectedItem().toString()),
							teacherId);
					DButils.updateUserPassword(teacherId, "p" + teacherId);
					DButils.updateTeacherId(teacherId, teacherName);
					lblAlterIdMsg.setText("教师信息修改成功");
				}
			}
		});

		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		pAlterId.setSize(panel.getSize());

		lblTeacherName.setBounds(pAlterId.getSize().width / 4, pAlterId.getSize().height / 7,
				2 * pAlterId.getSize().width / 10, 35);
		cbTeacherName.setBounds(9 * pAlterId.getSize().width / 20, pAlterId.getSize().height / 7,
				3 * pAlterId.getSize().width / 10, 35);
		lblTeacherId.setBounds(pAlterId.getSize().width / 4, 2 * pAlterId.getSize().height / 7,
				2 * pAlterId.getSize().width / 10, 35);
		txtTeacherId.setBounds(9 * pAlterId.getSize().width / 20, 2 * pAlterId.getSize().height / 7,
				3 * pAlterId.getSize().width / 10, 35);
		lblAlterIdMsg.setBounds(pAlterId.getSize().width / 4, 3 * pAlterId.getSize().height / 7, pAlterId.getSize().width / 2,
				35);
		btnOK.setBounds(2 * pAlterId.getSize().width / 6, 4 * pAlterId.getSize().height / 7,
				pAlterId.getSize().width / 6, 35);
		btnCanel.setBounds(3 * pAlterId.getSize().width / 6, 4 * pAlterId.getSize().height / 7,
				pAlterId.getSize().width / 6, 35);

		pAlterId.add(lblTeacherId);
		pAlterId.add(lblTeacherName);
		pAlterId.add(txtTeacherId);
		pAlterId.add(cbTeacherName);
		pAlterId.add(lblAlterIdMsg);
		pAlterId.add(btnOK);
		pAlterId.add(btnCanel);
	}
}