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
import team9.model.ResultInfo;
import team9.model.ScreenSize;
import team9.model.StudentInfo;

@SuppressWarnings("serial")
public class DeleteResultDialog extends JDialog implements SwingConstants {
	private JPanel panel;
	private JLabel lblCourseId, lblStudentId, lblSemester, lblResult, lblMsg;
	private JTextField txtResult;
	private JButton btnOK, btnCanel;
	private ResultInfo oldResultInfo, newResultInfo;
	private JComboBox<String> cbStudentId, cbSemester, cbCourseId;

	public DeleteResultDialog(JFrame f) {
		super(f, " 删除成绩信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		oldResultInfo = new ResultInfo();
		newResultInfo = new ResultInfo();

		panel = new JPanel(null);
		lblSemester = new JLabel("学期:", RIGHT);
		lblStudentId = new JLabel("学生学号:", RIGHT);
		lblCourseId = new JLabel("课程代码:", RIGHT);
		lblResult = new JLabel("成绩:", RIGHT);
		lblMsg = new JLabel("", CENTER);

		cbSemester = new JComboBox<>();
		cbSemester.addItem("选择学期");
		cbSemester.addItem("第一学期");
		cbSemester.addItem("第二学期");
		cbSemester.addItem("第三学期");
		cbSemester.addItem("第四学期");
		cbSemester.addItem("第五学期");
		cbSemester.addItem("第六学期");
		cbSemester.addItem("第七学期");
		cbSemester.addItem("第八学期");

		cbStudentId = new JComboBox<>();
		try {
			cbStudentId.removeAllItems();
			cbStudentId.addItem("选择学号");
			for (StudentInfo si : DButils.getAllStudentInfoList()) {
				cbStudentId.addItem(si.getId());
			}
		} catch (Exception e2) {
		}

		cbCourseId = new JComboBox<>();
		try {
			cbCourseId.removeAllItems();
			cbCourseId.addItem("选择课程");
			for (CourseInfo ci : DButils.getAllCourseInfoList()) {
				cbCourseId.addItem(ci.getId());
			}
		} catch (Exception e2) {
		}

		cbSemester.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				txtResult.setText("");
				try {
					oldResultInfo.setSemester(cbSemester.getSelectedItem().toString());
					oldResultInfo.setId(cbStudentId.getSelectedItem().toString());
					oldResultInfo.setCouId(cbCourseId.getSelectedItem().toString());
					txtResult.setText(DButils.getResultFromeSemesterAndStudentIdAndCouId(oldResultInfo.getSemester(), oldResultInfo.getId(), oldResultInfo.getCouId()));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		cbStudentId.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				txtResult.setText("");
				try {
					oldResultInfo.setSemester(cbSemester.getSelectedItem().toString());
					oldResultInfo.setId(cbStudentId.getSelectedItem().toString());
					oldResultInfo.setCouId(cbCourseId.getSelectedItem().toString());
					txtResult.setText(DButils.getResultFromeSemesterAndStudentIdAndCouId(oldResultInfo.getSemester(), oldResultInfo.getId(), oldResultInfo.getCouId()));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		cbCourseId.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				txtResult.setText("");
				try {
					oldResultInfo.setSemester(cbSemester.getSelectedItem().toString());
					oldResultInfo.setId(cbStudentId.getSelectedItem().toString());
					oldResultInfo.setCouId(cbCourseId.getSelectedItem().toString());
					txtResult.setText(DButils.getResultFromeSemesterAndStudentIdAndCouId(oldResultInfo.getSemester(), oldResultInfo.getId(), oldResultInfo.getCouId()));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		txtResult = new JTextField(20);
		txtResult.setEditable(false);

		btnOK = new JButton("删除");
		btnCanel = new JButton("取消");

		lblMsg.setForeground(Color.RED);

		lblCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblStudentId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		lblResult.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cbSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		cbStudentId.setFont(new Font("宋体", Font.PLAIN, 20));
		cbCourseId.setFont(new Font("宋体", Font.PLAIN, 20));
		txtResult.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newResultInfo.setSemester(cbSemester.getSelectedItem().toString());
				newResultInfo.setId(cbStudentId.getSelectedItem().toString());
				newResultInfo.setCouId(cbCourseId.getSelectedItem().toString());
				newResultInfo.setResult(txtResult.getText());

				DButils.deleteResultInfoFromSemesterAndStudentIdAndCouId(newResultInfo.getSemester(), newResultInfo.getId(), newResultInfo.getCouId());
				lblMsg.setText("成绩信息删除成功");
			}
		});
		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.setSize(ScreenSize.WIDTH / 3, ScreenSize.HEIGHT / 2);
		panel.setSize(this.getSize());

		lblSemester.setBounds(panel.getSize().width / 4, panel.getSize().height / 8, 2 * panel.getSize().width / 10,
				35);
		cbSemester.setBounds(9 * panel.getSize().width / 20, panel.getSize().height / 8, 3 * panel.getSize().width / 10,
				35);
		lblStudentId.setBounds(panel.getSize().width / 4, 2 * panel.getSize().height / 8,
				2 * panel.getSize().width / 10, 35);
		cbStudentId.setBounds(9 * panel.getSize().width / 20, 2 * panel.getSize().height / 8,
				3 * panel.getSize().width / 10, 35);
		lblCourseId.setBounds(panel.getSize().width / 4, 3 * panel.getSize().height / 8, 2 * panel.getSize().width / 10,
				35);
		cbCourseId.setBounds(9 * panel.getSize().width / 20, 3 * panel.getSize().height / 8,
				3 * panel.getSize().width / 10, 35);
		lblResult.setBounds(panel.getSize().width / 4, 4 * panel.getSize().height / 8, 2 * panel.getSize().width / 10,
				35);
		txtResult.setBounds(9 * panel.getSize().width / 20, 4 * panel.getSize().height / 8,
				3 * panel.getSize().width / 10, 35);
		lblMsg.setBounds(panel.getSize().width / 4, 5 * panel.getSize().height / 8, panel.getSize().width / 2, 35);
		btnOK.setBounds(2 * panel.getSize().width / 6, 6 * panel.getSize().height / 8, panel.getSize().width / 6, 35);
		btnCanel.setBounds(3 * panel.getSize().width / 6, 6 * panel.getSize().height / 8, panel.getSize().width / 6,
				35);

		panel.add(lblStudentId);
		panel.add(lblSemester);
		panel.add(lblCourseId);
		panel.add(lblResult);
		panel.add(cbSemester);
		panel.add(cbStudentId);
		panel.add(cbCourseId);
		panel.add(txtResult);
		panel.add(lblMsg);
		panel.add(btnOK);
		panel.add(btnCanel);

		this.add(panel);
		this.pack();
		this.setSize(ScreenSize.WIDTH / 3, ScreenSize.HEIGHT / 2);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}