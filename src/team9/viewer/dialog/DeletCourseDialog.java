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
import team9.model.CourseInfo;
import team9.model.ScreenSize;

@SuppressWarnings("serial")
public class DeletCourseDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblCourseId, lblCourseName, lblMsg;
	private JTextField txtCourseName;
	private JComboBox<String> cbCourseId;
	private JButton btnOK, btnCanel;

	public DeletCourseDialog(JFrame f) {
		super(f, " 删除课程信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		panel = new JPanel(null);
		lblCourseId = new JLabel("课程代码:", CENTER);
		lblCourseName = new JLabel("课程名称:", CENTER);
		lblMsg = new JLabel("", CENTER);

		txtCourseName = new JTextField(20);
		txtCourseName.setEditable(false);
		
		btnOK = new JButton("删除");
		btnCanel = new JButton("取消");

		cbCourseId = new JComboBox<>();
		try {
			cbCourseId.removeAllItems();
			cbCourseId.addItem("选择课程");
			for (CourseInfo ci : DButils.getAllCourseInfoList()) {
				cbCourseId.addItem(ci.getId());
			}
		} catch (Exception e2) {
		}

		cbCourseId.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					txtCourseName.setText(DButils.getCourseNameFromeId(cbCourseId.getSelectedItem().toString()));
				} catch (Exception e2) {
				}
			}
		});
		
		lblMsg.setForeground(Color.RED);

		lblCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblCourseName.setFont(new Font("宋体", Font.PLAIN, 20));
		txtCourseName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String courseId = cbCourseId.getSelectedItem().toString();
				DButils.deleteResultInfoFromCourseId(courseId);
				DButils.deleteCourse(courseId);
				lblMsg.setText("课程信息删除成功");
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

		lblCourseId.setBounds(panel.getSize().width / 4, panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		cbCourseId.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 6, 3 * panel.getSize().width / 10,
				35);
		lblCourseName.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 6, 2 * panel.getSize().width / 10,
				35);
		txtCourseName.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 6, 3 * panel.getSize().width / 10,
				35);
		lblMsg.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 6, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7, 35);
		btnCanel.setBounds(4 * panel.getSize().width / 7, 4 * panel.getSize().height / 6, panel.getSize().width / 7,
				35);

		panel.add(lblCourseId);
		panel.add(cbCourseId);
		panel.add(lblCourseName);
		panel.add(txtCourseName);
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