package team9.viewer.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import team9.control.utils.DButils;
import team9.model.ClassScheduleInfo;
import team9.model.ScreenSize;

@SuppressWarnings("serial")
public class ShowClassScheduleDialog extends JDialog implements SwingConstants {

	private JPanel p, p2;
	private JTable table1, table2;
	private JScrollPane sp1, sp2;
	private Border border, titled;
	private Vector<String> columnNames1, columnNames2;
	private Vector<Vector<Object>> tableValue1, tableValue2;

	public ShowClassScheduleDialog(JFrame f) {
		super(f, " 修改班级信息", true);

		ImageIcon icon = new ImageIcon("lib/images/Settings.png");
		this.setIconImage(icon.getImage());

		p = new JPanel(null);
		p2 = new JPanel(null);

		border = BorderFactory.createLineBorder(Color.lightGray, 3);
		titled = BorderFactory.createTitledBorder(border, " 课表信息 ", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("宋体", Font.PLAIN, 20), Color.black);

		sp1 = new JScrollPane();
		sp2 = new JScrollPane();

		columnNames1 = new Vector<>();
		columnNames1.add("序号");
		columnNames1.add("班级编号");
		columnNames1.add("学期");
		columnNames1.add("星期一第一节");
		columnNames1.add("星期一第二节");
		columnNames1.add("星期一第三节");
		columnNames1.add("星期一第四节");
		columnNames1.add("星期二第一节");
		columnNames1.add("星期二第二节");
		columnNames1.add("星期二第三节");
		columnNames1.add("星期二第四节");
		columnNames1.add("星期三第一节");
		columnNames1.add("星期三第二节");

		columnNames2 = new Vector<>();
		columnNames2.add("序号");
		columnNames2.add("班级编号");
		columnNames2.add("学期");
		columnNames2.add("星期三第三节");
		columnNames2.add("星期三第四节");
		columnNames2.add("星期四第一节");
		columnNames2.add("星期四第二节");
		columnNames2.add("星期四第三节");
		columnNames2.add("星期四第四节");
		columnNames2.add("星期五第一节");
		columnNames2.add("星期五第二节");
		columnNames2.add("星期五第三节");
		columnNames2.add("星期五第四节");

		tableValue1 = new Vector<>();
		tableValue2 = new Vector<>();

		JLabel lblClassId = new JLabel("班级id:", RIGHT);
		JLabel lblSemester = new JLabel("学期:", RIGHT);

		JTextField txtClassId = new JTextField(20);
		JTextField txtSemester = new JTextField(10);

		JButton btnQuery = new JButton(" 查询 ");
		JButton btnRefresh = new JButton(" 刷新 ");
		tableShow();

		btnQuery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String classId = txtClassId.getText();
				String semester = txtSemester.getText();
				try {
					tableValue1.clear();
					tableValue2.clear();
					int n = 1;
					for (ClassScheduleInfo classScheduleInfo : DButils.getClassScheduleInfoFromFuzzyQuery(classId,
							semester)) {
						try {
							Vector<Object> rowValue1 = new Vector<>();
							Vector<Object> rowValue2 = new Vector<>();
							rowValue1.add(n);
							rowValue1.add(classScheduleInfo.getClassId());
							rowValue1.add(classScheduleInfo.getSemester());
							rowValue1.add(classScheduleInfo.getMonNo1());
							rowValue1.add(classScheduleInfo.getMonNo2());
							rowValue1.add(classScheduleInfo.getMonNo3());
							rowValue1.add(classScheduleInfo.getMonNo4());
							rowValue1.add(classScheduleInfo.getTueNo1());
							rowValue1.add(classScheduleInfo.getTueNo2());
							rowValue1.add(classScheduleInfo.getTueNo3());
							rowValue1.add(classScheduleInfo.getTueNo4());
							rowValue1.add(classScheduleInfo.getWedNo1());
							rowValue1.add(classScheduleInfo.getWedNo2());
							tableValue1.add(rowValue1);
							rowValue2.add(n);
							rowValue2.add(classScheduleInfo.getClassId());
							rowValue2.add(classScheduleInfo.getSemester());
							rowValue2.add(classScheduleInfo.getWedNo3());
							rowValue2.add(classScheduleInfo.getWedNo4());
							rowValue2.add(classScheduleInfo.getThursNo1());
							rowValue2.add(classScheduleInfo.getThursNo2());
							rowValue2.add(classScheduleInfo.getThursNo3());
							rowValue2.add(classScheduleInfo.getThursNo4());
							rowValue2.add(classScheduleInfo.getFriNo1());
							rowValue2.add(classScheduleInfo.getFriNo2());
							rowValue2.add(classScheduleInfo.getFriNo3());
							rowValue2.add(classScheduleInfo.getFriNo4());
							tableValue2.add(rowValue2);
							n++;
						} catch (Exception e2) {
						}
					}
					table1 = new JTable(tableValue1, columnNames1);
					table2 = new JTable(tableValue1, columnNames2);

					for (int i = 0; i < 13; i++) {
						table1.getColumnModel().getColumn(i).setPreferredWidth(getSize().width / 13);
						table1.getColumnModel().getColumn(i).setMinWidth(100);
						table2.getColumnModel().getColumn(i).setPreferredWidth(getSize().width / 13);
						table2.getColumnModel().getColumn(i).setMinWidth(100);
					}
					table1.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					table1.setFont(new Font("宋体", Font.PLAIN, 20));
					table1.setRowHeight(30);
					table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp1.setViewportView(table1);
					table2.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
					table2.setFont(new Font("宋体", Font.PLAIN, 20));
					table2.setRowHeight(30);
					table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp2.setViewportView(table2);
					p.updateUI();
				} catch (Exception e2) {
				} finally {
					table1.updateUI();
					table2.updateUI();
				}
			}
		});

		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtClassId.setText("");
				txtSemester.setText("");
				tableShow();
			}
		});

		lblClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		lblSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		txtClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		txtSemester.setFont(new Font("宋体", Font.PLAIN, 20));
		btnQuery.setFont(new Font("宋体", Font.PLAIN, 20));
		btnRefresh.setFont(new Font("宋体", Font.PLAIN, 20));

		lblClassId.setBounds(10, 10, 70, 35);
		txtClassId.setBounds(80, 10, 100, 35);
		lblSemester.setBounds(190, 10, 70, 35);
		txtSemester.setBounds(260, 10, 100, 35);
		btnQuery.setBounds(370, 10, 100, 35);
		btnRefresh.setBounds(480, 10, 100, 35);

		this.setSize(ScreenSize.WIDTH, ScreenSize.HEIGHT);
		p.setSize(this.getSize().width, this.getSize().height);
		p2.setBounds(0, 50, p.getSize().width, p.getSize().height - 50);
		sp1.setBounds(0, 0, p2.getSize().width, p2.getSize().height / 2);
		sp2.setBounds(0, p2.getSize().height / 2, p2.getSize().width, p2.getSize().height / 2);

		p2.setBorder(titled);
		p2.add(sp1);
		p2.add(sp2);
		p.add(lblClassId);
		p.add(lblSemester);
		p.add(txtClassId);
		p.add(txtSemester);
		p.add(btnQuery);
		p.add(btnRefresh);
		p.add(p2);
		this.add(p);
		this.pack();
		this.setSize(ScreenSize.WIDTH, ScreenSize.HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	private void tableShow() {
		try {
			tableValue1.clear();
			tableValue2.clear();
			int n = 1;
			for (ClassScheduleInfo classScheduleInfo : DButils.getAllClassScheduleInfoList()) {
				try {
					Vector<Object> rowValue1 = new Vector<>();
					Vector<Object> rowValue2 = new Vector<>();
					rowValue1.add(n);
					rowValue1.add(classScheduleInfo.getClassId());
					rowValue1.add(classScheduleInfo.getSemester());
					rowValue1.add(classScheduleInfo.getMonNo1());
					rowValue1.add(classScheduleInfo.getMonNo2());
					rowValue1.add(classScheduleInfo.getMonNo3());
					rowValue1.add(classScheduleInfo.getMonNo4());
					rowValue1.add(classScheduleInfo.getTueNo1());
					rowValue1.add(classScheduleInfo.getTueNo2());
					rowValue1.add(classScheduleInfo.getTueNo3());
					rowValue1.add(classScheduleInfo.getTueNo4());
					rowValue1.add(classScheduleInfo.getWedNo1());
					rowValue1.add(classScheduleInfo.getWedNo2());
					tableValue1.add(rowValue1);
					rowValue2.add(n);
					rowValue2.add(classScheduleInfo.getClassId());
					rowValue2.add(classScheduleInfo.getSemester());
					rowValue2.add(classScheduleInfo.getWedNo3());
					rowValue2.add(classScheduleInfo.getWedNo4());
					rowValue2.add(classScheduleInfo.getThursNo1());
					rowValue2.add(classScheduleInfo.getThursNo2());
					rowValue2.add(classScheduleInfo.getThursNo3());
					rowValue2.add(classScheduleInfo.getThursNo4());
					rowValue2.add(classScheduleInfo.getFriNo1());
					rowValue2.add(classScheduleInfo.getFriNo2());
					rowValue2.add(classScheduleInfo.getFriNo3());
					rowValue2.add(classScheduleInfo.getFriNo4());
					tableValue2.add(rowValue2);
					n++;
				} catch (Exception e2) {
				}
			}
			table1 = new JTable(tableValue1, columnNames1);
			table2 = new JTable(tableValue2, columnNames2);

			for (int i = 0; i < 13; i++) {
				table1.getColumnModel().getColumn(i).setPreferredWidth(getSize().width / 12);
				table2.getColumnModel().getColumn(i).setPreferredWidth(getSize().width / 12);
//				table.getColumnModel().getColumn(i).setMinWidth(100);
			}

			table1.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
			table1.setFont(new Font("宋体", Font.PLAIN, 20));
			table1.setRowHeight(30);
			table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			sp1.setViewportView(table1);
			table2.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 20));
			table2.setFont(new Font("宋体", Font.PLAIN, 20));
			table2.setRowHeight(30);
			table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			sp2.setViewportView(table2);
			p.updateUI();
		} catch (Exception e2) {
		} finally {
			table1.updateUI();
			table2.updateUI();
		}
	}
}