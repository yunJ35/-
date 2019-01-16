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
import team9.model.ClassInfo;

@SuppressWarnings("serial")
public class AlterClassInfoDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblClassId, lblClassName, lblAlterNameMsg;
	private JTextField txtClassName;
	private JButton btnOK, btnCanel;
	private JComboBox<String> cbClassId;

	public AlterClassInfoDialog(JFrame f) {
		super(f, " 修改班级信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);

		lblClassId = new JLabel("编号:", CENTER);
		lblClassName = new JLabel("名称:", CENTER);
		lblAlterNameMsg = new JLabel("", CENTER);

		cbClassId = new JComboBox<String>();
		txtClassName = new JTextField(20);

		btnOK = new JButton("确定");
		btnCanel = new JButton("取消");

		lblAlterNameMsg.setForeground(Color.RED);

		lblClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblClassName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblAlterNameMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		txtClassName.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		try {
			cbClassId.removeAllItems();
			cbClassId.addItem("选择id");
			for (ClassInfo ti : DButils.getAllClassInfoList()) {
				cbClassId.addItem(ti.getId());
			}
		} catch (Exception e2) {
		}

		cbClassId.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					txtClassName.setText(DButils.getClassNameFromId(cbClassId.getSelectedItem().toString()));
				} catch (Exception e2) {
				}
			}
		});

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String classId = cbClassId.getSelectedItem().toString();
				String className = txtClassName.getText();

				if (className == null || className.equals("")) {
					lblAlterNameMsg.setText("名称不能为空！");
				} else {
					DButils.updateClassName(classId, className);
					lblAlterNameMsg.setText("班级信息修改成功");
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
		
		lblClassId.setBounds(panel.getSize().width / 4, panel.getSize().height / 7,
				2 * panel.getSize().width / 10, 35);
		cbClassId.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 7,
				3 * panel.getSize().width / 10, 35);
		lblClassName.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 7,
				2 * panel.getSize().width / 10, 35);
		txtClassName.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 7,
				3 * panel.getSize().width / 10, 35);
		lblAlterNameMsg.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 7,
				panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 6, 4 * panel.getSize().height / 7,
				panel.getSize().width / 6, 35);
		btnCanel.setBounds(3 * panel.getSize().width / 6, 4 * panel.getSize().height / 7,
				panel.getSize().width / 6, 35);

		panel.add(lblClassId);
		panel.add(lblClassName);
		panel.add(cbClassId);
		panel.add(txtClassName);
		panel.add(lblAlterNameMsg);
		panel.add(btnOK);
		panel.add(btnCanel);
		this.add(panel);
		this.pack();
		this.setSize(ScreenSize.WIDTH / 4, ScreenSize.HEIGHT / 4);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}