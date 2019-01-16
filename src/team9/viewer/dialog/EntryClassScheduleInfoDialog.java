package team9.viewer.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import team9.control.utils.DButils;
import team9.model.ClassCourse;
import team9.model.ClassInfo;
import team9.model.ClassScheduleInfo;
import team9.model.ScreenSize;

@SuppressWarnings("serial")
public class EntryClassScheduleInfoDialog extends JDialog implements SwingConstants {
	private JPanel panel, p2;
	private JLabel lblSemester, lblClassId, lbl, lblMon, lblTue, lblWed, lblThers, lblFri, lblNo1, lblNo2, lblNo3,
			lblNo4, lblMsg;
	private JButton btnRefresh, btnOK, btnCanel;
	private ClassScheduleInfo classScheduleInfo;
	private ClassCourse classCourse;
	private List<JComboBox<String>> list;
	JComboBox<String> cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12, cb13, cb14, cb15, cb16, cb17, cb18,
			cb19, cb20;

	public EntryClassScheduleInfoDialog(JFrame f) {
		super(f, " 添加课表信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		classScheduleInfo = new ClassScheduleInfo();
		classCourse = new ClassCourse();

		panel = new JPanel(null);
		p2 = new JPanel();
		p2.setLayout(new GridLayout(5, 6));

		lblSemester = new JLabel("学期", CENTER);
		lblClassId = new JLabel("班级", CENTER);
		lbl = new JLabel("", CENTER);
		lblMon = new JLabel("星期一", CENTER);
		lblTue = new JLabel("星期二", CENTER);
		lblWed = new JLabel("星期三", CENTER);
		lblThers = new JLabel("星期四", CENTER);
		lblFri = new JLabel("星期五", CENTER);
		lblNo1 = new JLabel("第一节", CENTER);
		lblNo2 = new JLabel("第二节", CENTER);
		lblNo3 = new JLabel("第三节", CENTER);
		lblNo4 = new JLabel("第四节", CENTER);
		lblMsg = new JLabel("", CENTER);

		list = new ArrayList<>();

		cb1 = new JComboBox<>();
		cb2 = new JComboBox<>();
		cb3 = new JComboBox<>();
		cb4 = new JComboBox<>();
		cb5 = new JComboBox<>();
		cb6 = new JComboBox<>();
		cb7 = new JComboBox<>();
		cb8 = new JComboBox<>();
		cb9 = new JComboBox<>();
		cb10 = new JComboBox<>();
		cb11 = new JComboBox<>();
		cb12 = new JComboBox<>();
		cb13 = new JComboBox<>();
		cb14 = new JComboBox<>();
		cb15 = new JComboBox<>();
		cb16 = new JComboBox<>();
		cb17 = new JComboBox<>();
		cb18 = new JComboBox<>();
		cb19 = new JComboBox<>();
		cb20 = new JComboBox<>();

		list.add(cb1);
		list.add(cb2);
		list.add(cb3);
		list.add(cb4);
		list.add(cb5);
		list.add(cb6);
		list.add(cb7);
		list.add(cb8);
		list.add(cb9);
		list.add(cb10);
		list.add(cb11);
		list.add(cb12);
		list.add(cb13);
		list.add(cb14);
		list.add(cb15);
		list.add(cb16);
		list.add(cb17);
		list.add(cb18);
		list.add(cb19);
		list.add(cb20);

		JComboBox<String> cbSemester = new JComboBox<>();
		cbSemester.addItem("选择学期");
		cbSemester.addItem("第一学期");
		cbSemester.addItem("第二学期");
		cbSemester.addItem("第三学期");
		cbSemester.addItem("第四学期");
		cbSemester.addItem("第五学期");
		cbSemester.addItem("第六学期");
		cbSemester.addItem("第七学期");
		cbSemester.addItem("第八学期");

		cbSemester.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				classCourse.setSemester(cbSemester.getSelectedItem().toString());
			}
		});

		JComboBox<String> cbClassId = new JComboBox<>();
		try {
			cbClassId.removeAllItems();
			cbClassId.addItem("选择班级");
			for (ClassInfo ti : DButils.getAllClassInfoList()) {
				cbClassId.addItem(ti.getId());
			}
		} catch (Exception e2) {
		}

		cbClassId.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				classCourse.setClassId(cbClassId.getSelectedItem().toString());
			}
		});

		btnRefresh = new JButton("刷新");
		btnOK = new JButton("确定");
		btnCanel = new JButton("取消");

		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				p2.removeAll();
				try {
					for (int i = 0; i < 20; i++) {
						list.get(i).addItem("");
					}
					for (ClassCourse cc : DButils.getClassCourseListFromClassIdAndSemester(classCourse.getClassId(),
							classCourse.getSemester())) {
						String str = DButils.getCourseInfoFromId(cc.getCouId()).getCouName();
						for (int i = 0; i < 20; i++) {
							list.get(i).addItem(str);
						}
					}
					p2.add(lbl);
					p2.add(lblMon);
					p2.add(lblTue);
					p2.add(lblWed);
					p2.add(lblThers);
					p2.add(lblFri);

					p2.add(lblNo1);
					p2.add(list.get(0));
					p2.add(list.get(1));
					p2.add(list.get(2));
					p2.add(list.get(3));
					p2.add(list.get(4));

					p2.add(lblNo2);
					p2.add(list.get(5));
					p2.add(list.get(6));
					p2.add(list.get(7));
					p2.add(list.get(8));
					p2.add(list.get(9));

					p2.add(lblNo3);
					p2.add(list.get(10));
					p2.add(list.get(11));
					p2.add(list.get(12));
					p2.add(list.get(13));
					p2.add(list.get(14));

					p2.add(lblNo4);
					p2.add(list.get(15));
					p2.add(list.get(16));
					p2.add(list.get(17));
					p2.add(list.get(18));
					p2.add(list.get(19));
				} catch (Exception e2) {
					System.out.println("asdasdsa");
				}
				panel.add(btnOK);
				panel.add(btnCanel);
				p2.updateUI();
				panel.updateUI();
			}
		});

		lblMsg.setForeground(Color.RED);

		lblSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		lblClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMon.setFont(new Font("宋体", Font.PLAIN, 20));
		lblTue.setFont(new Font("宋体", Font.PLAIN, 20));
		lblWed.setFont(new Font("宋体", Font.PLAIN, 20));
		lblThers.setFont(new Font("宋体", Font.PLAIN, 20));
		lblFri.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNo1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNo2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNo3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNo4.setFont(new Font("宋体", Font.PLAIN, 20));
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 20));
		cb1.setFont(new Font("宋体", Font.PLAIN, 20));
		cb2.setFont(new Font("宋体", Font.PLAIN, 20));
		cb3.setFont(new Font("宋体", Font.PLAIN, 20));
		cb4.setFont(new Font("宋体", Font.PLAIN, 20));
		cb5.setFont(new Font("宋体", Font.PLAIN, 20));
		cb6.setFont(new Font("宋体", Font.PLAIN, 20));
		cb7.setFont(new Font("宋体", Font.PLAIN, 20));
		cb8.setFont(new Font("宋体", Font.PLAIN, 20));
		cb9.setFont(new Font("宋体", Font.PLAIN, 20));
		cb10.setFont(new Font("宋体", Font.PLAIN, 20));
		cb11.setFont(new Font("宋体", Font.PLAIN, 20));
		cb12.setFont(new Font("宋体", Font.PLAIN, 20));
		cb13.setFont(new Font("宋体", Font.PLAIN, 20));
		cb14.setFont(new Font("宋体", Font.PLAIN, 20));
		cb15.setFont(new Font("宋体", Font.PLAIN, 20));
		cb16.setFont(new Font("宋体", Font.PLAIN, 20));
		cb17.setFont(new Font("宋体", Font.PLAIN, 20));
		cb18.setFont(new Font("宋体", Font.PLAIN, 20));
		cb19.setFont(new Font("宋体", Font.PLAIN, 20));
		cb20.setFont(new Font("宋体", Font.PLAIN, 20));
		cbSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		cbClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCanel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				classScheduleInfo.setClassId(classCourse.getClassId());
				classScheduleInfo.setSemester(classCourse.getSemester());
				classScheduleInfo.setMonNo1(list.get(0).getSelectedItem().toString());
				classScheduleInfo.setTueNo1(list.get(1).getSelectedItem().toString());
				classScheduleInfo.setWedNo1(list.get(2).getSelectedItem().toString());
				classScheduleInfo.setThursNo1(list.get(3).getSelectedItem().toString());
				classScheduleInfo.setFriNo1(list.get(4).getSelectedItem().toString());

				classScheduleInfo.setMonNo2(list.get(5).getSelectedItem().toString());
				classScheduleInfo.setTueNo2(list.get(6).getSelectedItem().toString());
				classScheduleInfo.setWedNo2(list.get(7).getSelectedItem().toString());
				classScheduleInfo.setThursNo2(list.get(8).getSelectedItem().toString());
				classScheduleInfo.setFriNo2(list.get(9).getSelectedItem().toString());

				classScheduleInfo.setMonNo3(list.get(10).getSelectedItem().toString());
				classScheduleInfo.setTueNo3(list.get(11).getSelectedItem().toString());
				classScheduleInfo.setWedNo3(list.get(12).getSelectedItem().toString());
				classScheduleInfo.setThursNo3(list.get(13).getSelectedItem().toString());
				classScheduleInfo.setFriNo3(list.get(14).getSelectedItem().toString());

				classScheduleInfo.setMonNo4(list.get(15).getSelectedItem().toString());
				classScheduleInfo.setTueNo4(list.get(16).getSelectedItem().toString());
				classScheduleInfo.setWedNo4(list.get(17).getSelectedItem().toString());
				classScheduleInfo.setThursNo4(list.get(18).getSelectedItem().toString());
				classScheduleInfo.setFriNo4(list.get(19).getSelectedItem().toString());

				DButils.insertClassSchedule(classScheduleInfo);
				lblMsg.setText("课程信息添加成功");
			}
		});
		btnCanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.setSize(ScreenSize.WIDTH / 2, ScreenSize.HEIGHT / 2);
		panel.setSize(this.getSize());
		p2.setBounds(0, panel.getSize().height / 8, 9 * panel.getSize().width / 10, 5 * panel.getSize().height / 8);

		lblSemester.setBounds(10, 10, 70, 35);
		cbSemester.setBounds(80, 10, 150, 35);
		lblClassId.setBounds(240, 10, 70, 35);
		cbClassId.setBounds(310, 10, 150, 35);
		btnRefresh.setBounds(470, 10, 100, 35);

		lblMsg.setBounds(0, 6 * panel.getSize().height / 8, panel.getSize().width, 35);
		btnOK.setBounds(panel.getSize().width / 2 - 205, 7 * panel.getSize().height / 8 - 20, 100, 35);
		btnCanel.setBounds(panel.getSize().width / 2 + 105, 7 * panel.getSize().height / 8 - 20, 100, 35);

		panel.add(p2);
		panel.add(lblSemester);
		panel.add(cbSemester);
		panel.add(lblClassId);
		panel.add(cbClassId);
		panel.add(btnRefresh);
		panel.add(lblMsg);

		this.add(panel);
		this.pack();
		this.setSize(ScreenSize.WIDTH / 2, ScreenSize.HEIGHT / 2);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}