package project_hospital;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class HospitalMyPage extends WindowAdapter implements ActionListener{
	//Field
		Frame f;
		JPanel jp_change,content_panel,menu_panel,check_panel,search_panel,update_panel,update_bottom;
		JTextField  jt_kind, jt_delete;
		TextField tf_update,tf_up_last;
		JButton  view, update, change,update_search;
		String[] form_names = {"���̵�","��й�ȣ","��ȭ��ȣ","�����̸�","����","������"};
		String[] up_names = {"���೯¥","����ð�"};
		ArrayList<TextField> tf_change_list;
		ArrayList<TextField> tf_update_list;
		
		Object[] columns = {"�����ڵ�","�̸�","����","��¥","�ð�"};
		DefaultTableModel model =new DefaultTableModel(columns,0);
		JTable table= new JTable(model);
		Object[] row =new Object[5];  //JTable�� �߰��Ǵ� �ϳ��� row �߰��� ��ü
		
		
		public static final int SELECT = 1;
		public static final int CHANGE = 2;
		public static final int UPDATE = 3;
		
		//Constructor
		public HospitalMyPage(){
			f= new Frame("��������");
			jp_change = new JPanel();
			content_panel = new JPanel();
			menu_panel = new JPanel(new GridLayout(3,1));
			check_panel = new JPanel();
			update_panel = new JPanel();
			search_panel = new JPanel();
			
			JButton view = new JButton("���೻��");
			JButton update = new JButton("�������");
			JButton change = new JButton("����������");
			
			menu_panel.add(view);
			menu_panel.add(update);
			menu_panel.add(change);
			
			
			
			
			f.add(BorderLayout.WEST,menu_panel);
			f.add(BorderLayout.CENTER,content_panel);
			
			f.setSize(500,400);
			f.setVisible(true);
			
//			change();
//			checklist();
			update();
		
			
			f.addWindowListener(this);
		}
		//Method
		public void change() {
			switchPanel(CHANGE);
			tf_change_list = new ArrayList<TextField>();
		
			
			
			String title = "--- �� ���� �����ϱ� ---";
			JLabel title_label = new JLabel(title);
			JPanel label_panel = new JPanel(new GridLayout(6,1));
			JPanel tf_panel = new JPanel(new GridLayout(6,1));
			JPanel btn_panel = new JPanel();
			JButton btn_insert = new JButton("�����ϱ�");
			JButton btn_reset = new JButton("���");
			btn_panel.add(btn_insert); btn_panel.add(btn_reset);
			
			for(String name :form_names) {
				JPanel p1 = new JPanel(); //label
				JPanel p2 = new JPanel(); //tf
				JLabel label = new JLabel(name);
				TextField tf = new TextField(30);
				p1.add(label); label_panel.add(p1);
				p2.add(tf);	   tf_panel.add(p2);
				
				tf_change_list.add(tf);
					
			}
			
			jp_change.setLayout(new BorderLayout());
			jp_change.add(BorderLayout.NORTH,title_label);
			jp_change.add(BorderLayout.WEST,label_panel);
			jp_change.add(BorderLayout.CENTER,tf_panel);
			jp_change.add(BorderLayout.SOUTH,btn_panel);
			
			content_panel.add(jp_change);

			f.setSize(500,400);
			f.setVisible(true);
			
			btn_insert.addActionListener(this);
			btn_reset.addActionListener(this);
			
		}
		
//		public void checklist() {
//			switchPanel(SELECT);
//			
//			model.setColumnIdentifiers(columns);
//			table.setModel(model);
//			
//			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
//		    TableColumnModel tcm = table.getColumnModel();
//		    
//		    table.getColumn("�����ȣ").setCellRenderer(dtcr);
//		    table.getColumn("��������").setCellRenderer(dtcr);
//		    table.getColumn("�̸�").setCellRenderer(dtcr);
//		    table.getColumn("����").setCellRenderer(dtcr);
//		    table.getColumn("��¥").setCellRenderer(dtcr);
//		    table.getColumn("�ð�").setCellRenderer(dtcr);
//		    
//		    JScrollPane pane=new JScrollPane(table);
//			pane.setBounds(0,100,880,250);
//			
//			check_panel.setLayout(new BorderLayout());
//			check_panel.add(BorderLayout.NORTH,new Label("������Ȳ"));
//			check_panel.add(BorderLayout.CENTER,pane);
//			content_panel.add(check_panel);
//			
//			
//			
//		}
		
		public void update() {
			switchPanel(UPDATE);
			tf_update_list = new ArrayList<TextField>();
			
			//�й� �˻� ������ �� �˻���ư�� �̺�Ʈ �߰�
			JPanel update_top = new JPanel(new BorderLayout());
			JPanel search_panel = new JPanel();
			String title = "������ �����ȣ�� �Է����ּ���";
			Label title_label = new Label(title);
			Label label = new Label("�����ȣ");
			tf_update = new TextField(20);
			update_search = new JButton("�˻�");
			
			tf_update_list.add(tf_update);
			
			search_panel.add(label);
			search_panel.add(tf_update);
			search_panel.add(update_search);
			
			update_top.add(BorderLayout.NORTH,title_label);
			update_top.add(BorderLayout.CENTER,search_panel);
			
			update_panel.setLayout(new BorderLayout());
			update_panel.add(BorderLayout.NORTH,update_top);
			content_panel.add(update_panel);
			f.setVisible(true);
			
			tf_update.addActionListener(this);
			update_search.addActionListener(this);
			
		}
		
		/** ���� ������ ��� �� */
		public void updateOKForm() {
			Panel update_bottom = new Panel(new BorderLayout());
			Panel label_panel = new Panel(new GridLayout(4,1));
			Panel tf_panel = new Panel(new GridLayout(4,1));
			Panel btn_panel = new Panel();
			Button btn_update = new Button("�����Ϸ�");
			Button btn_reset = new Button("�������");
			btn_panel.add(btn_update);
			btn_panel.add(btn_reset);
			
			for(int i=0;i<up_names.length;i++) {
				Panel p1 = new Panel();
				Panel p2 = new Panel();
				Label label = new Label(up_names[i]);
				TextField tf = new TextField(20);
				p1.add(label);   p2.add(tf);
				label_panel.add(p1);
				tf_panel.add(p2);
				
			}	
			update_bottom.add(BorderLayout.NORTH, new Label());
			update_bottom.add(BorderLayout.WEST, label_panel);
			update_bottom.add(BorderLayout.CENTER, tf_panel);
			update_bottom.add(BorderLayout.SOUTH, btn_panel);
			
			update_panel.add(BorderLayout.CENTER, update_bottom);
			content_panel.add(update_panel);
			f.setVisible(true);
			tf_up_last = tf_update_list.get(tf_update_list.size()-1);
			tf_up_last.addActionListener(this);
			
		}
		
		/** ���������� ������ */
		public void updateFailForm() {
			update_bottom.removeAll();
			update_bottom.add(BorderLayout.NORTH, new Label());
			update_bottom.add(BorderLayout.CENTER, new Label("-- ���� ������ �������� �ʽ��ϴ� --"));
			
			update_panel.add(BorderLayout.CENTER, update_bottom);
			content_panel.add(update_panel);
			f.setVisible(true);
		}	
		
		/** �޴� ���ý� �г� ����Ī  */
		public void switchPanel(int menu) {
			resetMenuPanel();
			// 1�� ����Ȯ�� 2�� ���ຯ��� ���� 3�� ������ ����
			switch(menu) {
			case 1:
				check_panel.setVisible(true); break;
			case 2:
				update_panel.setVisible(true); break;
			case 3:
				jp_change.setVisible(true); break;
			}
		}
		
		/**
		 * ��� �޴��� �гε��� ��Ȱ��
		 */
		public void resetMenuPanel() {
			check_panel.setVisible(false);
			update_panel.setVisible(false);
			jp_change.setVisible(false);
		}
		
		
		//������ �̺�Ʈ ó��
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
				
				
				
		//�׼� �̺�Ʈ ó��
				public void actionPerformed(ActionEvent e) {
					String name = e.getActionCommand().trim();
					Object obj = e.getSource();
					
					if(name.equals("�����ϱ�")) {
						JOptionPane.showMessageDialog(null, "�����Ϸ�");
					}else if(name.equals("���")) {
						JOptionPane.showMessageDialog(null, "�������");
					}else if(name.equals("���೻��")) {
//						checklist();
					}else if(name.equals("�������")) {
						update();
					}else if(name.equals("����������")) {
						change();
					}
				}
}//class
