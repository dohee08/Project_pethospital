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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class HospitalMyPage extends WindowAdapter implements ActionListener{
	//Field
		HospitalMgmUI main;
		JPanel MyPagePane;
		JPanel mypage_menu_panel;
		JPanel jp_change,content_panel,menu_panel,check_panel,search_panel,update_panel,update_bottom
				,select_panel,login_panel,all_panel;
		JTextField  jt_kind, jt_delete,tf_id;
		TextField tf_update,tf_up_last;
		JPasswordField tf_pass;
		JButton  view, update, change;
		Button update_search;
		String[] form_names = {"���̵�","��й�ȣ","��ȭ��ȣ","�����̸�","����","������"};
		String[] up_names = {"��������","�̸�","���೯¥","����ð�"};
		ArrayList<TextField> tf_change_list;
		ArrayList<TextField> tf_update_list;
		HospitalMgmSystem hms;
		int idx = -1;
		
		public HospitalMyPage() {}
		public HospitalMyPage(HospitalMgmUI main) {
			this.main = main;
			this.MyPagePane = main.MyPagePane;
			this.mypage_menu_panel = main.mypage_menu_panel;
		}
		
		//JTable
		Object[] columns = {"�����ȣ","��������","�̸�","��¥","�ð�"};
		DefaultTableModel model =new DefaultTableModel(columns,0);
		JTable table= new JTable(model);
		Object[] row =new Object[5];  //Jtable�� �߰��Ǵ� �ϳ��� row �߰��� ��ü
		
		
		public static final int SELECT = 1;
		public static final int UPDATE = 2;
//		public static final int CHANGE = 3;
		public static final int CHECK = 3;
		
		//Constructor
		public void MyPage(){
			main.switchPane(HospitalMgmUI.MYPAGE);
			
			hms = new HospitalMgmSystem();
			
			jp_change = new JPanel();
			content_panel = new JPanel();
			menu_panel = new JPanel(new GridLayout(3,1));
			check_panel = new JPanel();
			update_panel = new JPanel();
			search_panel = new JPanel();
			select_panel = new JPanel();
			update_bottom = new JPanel();
			login_panel = new JPanel(new BorderLayout());
			all_panel = new JPanel(new BorderLayout());
			
			
			
			JButton view = new JButton("���೻��");
			JButton update = new JButton("�������");
			JButton change = new JButton("����������");
			
//			menu_panel.add(view);
//			menu_panel.add(update);
//			menu_panel.add(change);
			
			mypage_menu_panel.add(view);
			mypage_menu_panel.add(update);
			mypage_menu_panel.add(change);
			 
			MyPagePane.add(BorderLayout.CENTER,content_panel);
			
			
			main.add(MyPagePane);
			main.setVisible(true);
			
			view.addActionListener(this);
			update.addActionListener(this);
			change.addActionListener(this);
		}
		//Method
		/** ������ ���� â */
		public void change() {
		//	resetMenuPanel();
		//	switchPanel(CHANGE);
			jp_change.removeAll();
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

			MyPagePane.add(content_panel);
			
			btn_insert.addActionListener(this);
			btn_reset.addActionListener(this);
			
		}
		
		
		public void change(String id, String pass) {
			//	resetMenuPanel();
			//	switchPanel(CHANGE);
				jp_change.removeAll();
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

				MyPagePane.add(content_panel);
				
				btn_insert.addActionListener(this);
				btn_reset.addActionListener(this);
				
			}
		
		/** �������� ����â */
		public void checklist() {
			resetMenuPanel();
			switchPanel(SELECT);
			select_panel.removeAll();
			
			createJtableData();	//��µǴ� ������ ��������
			model.setColumnIdentifiers(columns);
			table.setModel(model);
			
			

			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		    TableColumnModel tcm = table.getColumnModel();
		    
		    table.getColumn("�����ȣ").setCellRenderer(dtcr);
		    table.getColumn("��������").setCellRenderer(dtcr);
		    table.getColumn("�̸�").setCellRenderer(dtcr);
		    table.getColumn("��¥").setCellRenderer(dtcr);
		    table.getColumn("�ð�").setCellRenderer(dtcr);
			
		    JScrollPane pane=new JScrollPane(table);
			pane.setBounds(0,100,600,250);
			
			select_panel.setLayout(new BorderLayout());
			select_panel.add(BorderLayout.NORTH, new Label("������ȸ"));
			select_panel.add(BorderLayout.CENTER,pane);
			content_panel.add(select_panel);
			
			MyPagePane.add(content_panel);    
		}
		
		/** �������� ����â */
		public void update() {
			tf_update_list = new ArrayList<TextField>();
			resetMenuPanel();
			switchPanel(UPDATE);
			update_panel.removeAll();
			
			//�й� �˻� ������ �� �˻���ư�� �̺�Ʈ �߰�
			JPanel update_top = new JPanel(new BorderLayout());
			JPanel search_panel = new JPanel();
			String title = "������ �����ȣ�� �Է����ּ���";
			Label title_label = new Label(title);
			Label label = new Label("�����ȣ");
			tf_update = new TextField(20);
			update_search = new Button("�˻�");
			
			tf_update_list.add(tf_update);
			
			search_panel.add(label);
			search_panel.add(tf_update);
			search_panel.add(update_search);
			
			update_top.add(BorderLayout.NORTH,title_label);
			update_top.add(BorderLayout.CENTER,search_panel);
			
			update_panel.setLayout(new BorderLayout());
			update_panel.add(BorderLayout.NORTH,update_top);
			content_panel.add(update_panel);
			
			MyPagePane.add(content_panel);
			
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
			MyPagePane.add(content_panel);
			tf_up_last = tf_update_list.get(tf_update_list.size()-1);
			tf_up_last.addActionListener(this);
			
		}
		
		
		/** ���� ������ ��� �� */
		public void updateOKForm(UserVO vo) {
			update_bottom.removeAll();
			
			Panel update_bottom = new Panel(new BorderLayout());
			Panel label_panel = new Panel(new GridLayout(4,1));
			Panel tf_panel = new Panel(new GridLayout(4,1));
			Panel btn_panel = new Panel();
			Button btn_update = new Button("�����Ϸ�");
			Button btn_reset = new Button("�������");
			btn_panel.add(btn_update);
			btn_panel.add(btn_reset);
			
			String[] data_list = new String[4];
			data_list[0] = vo.getRkind();
			data_list[1]=vo.getRname();
			data_list[2]=vo.getRdate();
			data_list[3]=vo.getRtime();
			
			
			for(int i=0;i<up_names.length;i++) {
				Panel p1 = new Panel();
				Panel p2 = new Panel();
				Label label = new Label(up_names[i]);
				TextField tf = new TextField(20);
				tf.setText(data_list[i]);
	;			p1.add(label);   p2.add(tf);
				label_panel.add(p1);
				tf_panel.add(p2);
				
				tf_update_list.add(tf);
				
			}	
			update_bottom.add(BorderLayout.NORTH, new Label());
			update_bottom.add(BorderLayout.WEST, label_panel);
			update_bottom.add(BorderLayout.CENTER, tf_panel);
			update_bottom.add(BorderLayout.SOUTH, btn_panel);
			
			update_panel.add(BorderLayout.CENTER, update_bottom);
			content_panel.add(update_panel);
			content_panel.setVisible(true);
			MyPagePane.add(content_panel);
			
			btn_update.addActionListener(this);
			btn_reset.addActionListener(this);
			
		}
		
		/** ����ó�� �޼ҵ� */
		public void updateProc() {
			ArrayList<String> dataList = new ArrayList<String>();
			
			for(TextField tf : tf_update_list) {
				dataList.add(tf.getText().trim());
			}
			
			UserVO vo = new UserVO();
			
			vo.setRno(dataList.get(0));
			vo.setRkind(dataList.get(1));
			vo.setRname(dataList.get(2));
			vo.setRdate(dataList.get(3));
			vo.setRtime(dataList.get(4));
			
			if(hms.update(vo)) {
				//���� ����
				JOptionPane.showMessageDialog(null, "�����Ϸ�!");
			}else {
				JOptionPane.showMessageDialog(null, "��������");
			}
		} //����ó��
		
		
		/** ���� �˻�ó�� */
		public void updateSearchProc() {
			update_bottom.removeAll();
			String rno = tf_update.getText().trim();
			if(rno.equals("")) {
				JOptionPane.showMessageDialog(null, "�����ȣ�� �Է����ּ���");
			}else {
				idx = hms.searchRno(rno);
				if(idx != 0) {
					UserVO vo = hms.selectUser(rno);
					updateOKForm(vo);
				}else {
					updateFailForm();
				}
			}
		}
		
		/** ���������� ������ */
		public void updateFailForm() {
			update_bottom.removeAll();
			update_bottom.add(BorderLayout.NORTH, new Label());
			update_bottom.add(BorderLayout.CENTER, new Label("-- ���� ������ �������� �ʽ��ϴ� --"));
			
			update_panel.add(BorderLayout.CENTER, update_bottom);
			content_panel.add(update_panel);
			MyPagePane.add(content_panel);
		}	
		
		/** �޴� ���ý� �г� ����Ī  */
		public void switchPanel(int menu) {
			resetMenuPanel();
			// 1�� ����Ȯ�� 2�� ���ຯ��� ���� 3�� ������ ����
			switch(menu) {
			case 1:
				select_panel.setVisible(true); break;
			case 2:
				update_panel.setVisible(true); break;
			case 3:
				login_panel.setVisible(true); break;
				//jp_change.setVisible(true); break;
			}
		}
		
		/**
		 * ��� �޴��� �гε��� ��Ȱ��
		 */
		public void resetMenuPanel() {
			select_panel.setVisible(false);
			update_panel.setVisible(false);
			jp_change.setVisible(false);
			login_panel.setVisible(false);
		}
		
		
		
		/** ���� ��Ͽ��� ��µ� ������ �������� */
		public void createJtableData() {
			
			ArrayList<UserVO> list = hms.selectList();
			
			model.setNumRows(0);
			for(UserVO vo : list) {
				if(vo != null) {
					row[0]=vo.getRno();
					row[1]=vo.getRkind();
					row[2]=vo.getRname();
					row[3]=vo.getRdate();
					row[4]=vo.getRtime();
					
					model.addRow(row);
				}
				table.repaint();
			}
			model.fireTableDataChanged();
		}
		
		/** ������ �������� �α��� â */
		public void logincheck() {
			
		//	resetMenuPanel();
			switchPanel(CHECK);
			login_panel.removeAll();
			
			
			Panel ldpa_panel = new Panel();
			Label label_id = new Label("���̵�");
			Label label_pass = new Label("��й�ȣ");
			tf_id = new JTextField(9);
			tf_pass = new JPasswordField(9);
			Button btn_login = new Button("�α���");
			
			ldpa_panel.add(label_id);
			ldpa_panel.add(tf_id);
			ldpa_panel.add(new Label());
			ldpa_panel.add(label_pass);
			ldpa_panel.add(tf_pass);
			ldpa_panel.add(new Label());
			ldpa_panel.add(btn_login);
			
			login_panel.add(BorderLayout.CENTER,ldpa_panel);
			
			content_panel.add(login_panel);
			
			
			MyPagePane.add(content_panel);
			
			btn_login.addActionListener(this);
			tf_pass.addActionListener(this);
			
		}
		
		/** �α��� ó�� */
		public void loginProc() {
			String id = tf_id.getText().trim();
			String pass = tf_pass.getText().trim();
			

			if(id.equals("")) {
				JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���");
				tf_id.requestFocus();
			}else if(pass.equals("")) {
				JOptionPane.showMessageDialog(null, "�н����带 �Է����ּ���");
				tf_pass.requestFocus();
			}else {
				 boolean result = hms.login(id, pass);
				
				if(result) {
					JOptionPane.showMessageDialog(null, "�α��� ����!");
					login_panel.setVisible(false);
					jp_change.setVisible(true);
					change(id,pass);
				}else {
					JOptionPane.showMessageDialog(null, "���̵� �Ǵ� �н����尡 ��ġ���� �ʽ��ϴ�");
					tf_id.setText("");  tf_pass.setText("");
					tf_id.requestFocus();
				}
		}
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
						checklist();
					}else if(name.equals("�������")) {
						update();
					}else if(name.equals("����������")) {
						logincheck();
					}else if(obj == update_search || obj == tf_update) {
						updateSearchProc();			
					}else if(name.equals("�����Ϸ�") || obj == tf_up_last) {
						updateProc();
					}else if(name.equals("�������")) {
						JOptionPane.showMessageDialog(null,"�������");
					}else if(name.equals("�α���")) {
						loginProc();
					}
				}
}//class
