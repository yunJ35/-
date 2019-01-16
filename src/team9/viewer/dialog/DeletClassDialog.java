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
public class DeletClassDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblClassId, lblClassName, lblMsg;
	private JTextField txtClassName;
	private JComboBox<String> cbClassId;
	private JButton btnOK, btnCanel;

	public DeletClassDialog(JFrame f) {
		super(f, " 删除班级信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);
		lblClassId = new JLabel("编号:", CENTER);
		lblClassName = new JLabel("名称:", CENTER);
		lblMsg = new JLabel("", CENTER);

		txtClassName = new JTextField(20);
		txtClassName.setEditable(false);
		
		btnOK = new JButton("删除");
		btnCanel = new JButton("取消");

		cbClassId = new JComboBox<>();
		try {
			cbClassId.removeAllItems();
			cbClassId.addItem("选择id");
			for (ClassInfo si : DButils.getAllClassInfoList()) {
				cbClassId.addItem(si.getId());
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
		
		lblMsg.setForeground(Color.RED);

		lblClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblClassName.setFont(new Font("宋体", Font.PLAIN, 20));
		txtClassName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String classId = cbClassId.getSelectedItem().toString();
				DButils.deleteStudentInfoFromClassId(classId);
				DButils.deleteClassCourseFromClassId(classId);
				DButils.deleteClassScheduleInfoFromClassId(classId);
				DButils.deleteClass(classId);
				lblMsg.setText("班级信息删除成功");
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

		lblClassId.setBounds(panel.getSize().width / 4, panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		cbClassId.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 6, 3 * panel.getSize().width / 10,
				35);
		lblClassName.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		txtClassName.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 6, 3 * panel.getSize().width / 10,
				35);
		lblMsg.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 6, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7, 35);
		btnCanel.setBounds(4 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7,
				35);

		panel.add(lblClassId);
		panel.add(cbClassId);
		panel.add(lblClassName);
		panel.add(txtClassName);
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