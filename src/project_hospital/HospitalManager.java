package project_hospital;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class HospitalManager {
	//Field
	JFrame jf;
	JPanel main_panel, button_panel;
	JPanel listPane, updatePane, deletePane;
	JButton btnList, btnUpdate, btnDelete;
	TextArea ta;
	HospitalMgmUI main;

	JTextField tid;  
	JPasswordField tpass;
	JFrame jf_login;
	JPanel jp_deleteSearch;
	JLabel jl_deleteSearchName;
	JTextField jt_deleteSearch;
	JButton jb_deleteButton;
	
	//����
	TextField tf_update;
	int idx;
	ArrayList<TextField> tf_update_list; //�����޴� ���� �� ���ο� ���� �������� ���� ���� �� ����
	String[] form_names = {"�н�����", "��ȭ��ȣ", "�ְ��̸�", "�ְ�����"};
	JButton update_search;
	JPanel update_bottom;
	TextField tf_update_last;
	
	public static Font font = new Font("���� ���", Font.BOLD, 12);
	
	//Constructor
	public HospitalManager(HospitalMgmUI main) {
		this.main = main;
		login();
		
		Font font = new Font("���� ���", Font.BOLD, 12);
		jf = new JFrame("������ ȭ��");	
		
		main_panel = new JPanel();
	    button_panel = new JPanel();
	    listPane = new JPanel();
	    updatePane = new JPanel();
	    deletePane = new JPanel();
	    
	    update_bottom = new JPanel(new BorderLayout());
	    
	    button_panel = new JPanel(new GridLayout(3,1));
	    button_panel.setSize(40,100);
	    
	    //����
	    main_panel.setBackground(Color.WHITE);
	    button_panel.setBackground(Color.WHITE);
	    listPane.setBackground(Color.WHITE);
	    updatePane.setBackground(Color.WHITE);
	    deletePane.setBackground(Color.WHITE);
	    update_bottom.setBackground(Color.WHITE);
	    button_panel.setBackground(Color.WHITE);
	    
	    //������ UI ��ư ����
	    btnList = new JButton("ȸ�� ����Ʈ");
	    btnUpdate = new JButton("ȸ������ ����");
		btnDelete = new JButton("ȸ������ ����");
		btnList.setFont(font);  btnUpdate.setFont(font);  btnDelete.setFont(font);
		//����
		btnList.setBackground(HospitalMgmUI.c1);
		btnUpdate.setBackground(HospitalMgmUI.c2);
		btnDelete.setBackground(HospitalMgmUI.c3);
		
		button_panel.add(btnList);
		button_panel.add(btnUpdate);
		button_panel.add(btnDelete);
		
		//���� UI â ��ġ ����
	    jf.add(BorderLayout.WEST, button_panel);      
	    jf.add(BorderLayout.CENTER, main_panel); //- ������ ���� ���� ����
	    
	    jf.setSize(600,500);
		jf.setVisible(false);
		
		//������ UI ��ư �̺�Ʈ ����
		btnList.addActionListener(new JFrameObjectEvent(this));
		btnUpdate.addActionListener(new JFrameObjectEvent(this));
		btnDelete.addActionListener(new JFrameObjectEvent(this));
		jf.addWindowListener(new JFrameObjectEvent(this));
		
	}
	
	//Method
	/**�α��� �� **/
	public void login() {
		Font font = new Font("���� ���", Font.BOLD, 12);
		jf_login = new JFrame("�α���");	
		JPanel jp_title = new JPanel();
		JPanel jp_id = new JPanel();
		JPanel jp_pass = new JPanel();
		JPanel jp_button = new JPanel();
		
		JLabel title = new JLabel("�α��� ��");		
		JLabel id = new JLabel("���̵�");
		JLabel pass = new JLabel("�н�����");
		tid = new JTextField(15);
		tpass = new JPasswordField(15);
		JButton btnLogin = new JButton("�α���");
		JButton btnReset = new JButton("���");
		JButton btnExit = new JButton("����");
		
		//��Ʈ
		title.setFont(font);  id.setFont(font);   pass.setFont(font);
		btnLogin.setFont(font);    btnReset.setFont(font);
		btnExit.setFont(font);
		//����
		jp_title.setBackground(Color.WHITE);
		jp_id.setBackground(Color.WHITE);
		jp_pass.setBackground(Color.WHITE);
		jp_button.setBackground(Color.WHITE);
		btnLogin.setBackground(HospitalMgmUI.c1);
		btnReset.setBackground(HospitalMgmUI.c2);
		btnExit.setBackground(HospitalMgmUI.c3);
		
		jp_title.add(title);
		jp_id.add(id);     jp_id.add(tid);
		jp_pass.add(pass);  jp_pass.add(tpass);
		jp_button.add(btnLogin);
		jp_button.add(btnReset);
		jp_button.add(btnExit);
		
		jf_login.setLayout(new GridLayout(4,1));
		jf_login.add(jp_title);		jf_login.add(jp_id);		jf_login.add(jp_pass);
		jf_login.add(jp_button);
		
		jf_login.setSize(300,220);	
		jf_login.setBackground(Color.WHITE);
		
		Dimension fsize = jf_login.getSize();
		Dimension scsize = Toolkit.getDefaultToolkit().getScreenSize(); 
		int width = (int)(scsize.getWidth()-fsize.getWidth())/2;
		int height =(int)(scsize.getHeight()-fsize.getHeight())/2;
	
		jf_login.setLocation(width,height);
		jf_login.setVisible(true);
		
		//�̺�Ʈ �ڵ鷯 �޼ҵ�(������ �޼ҵ�) ȣ��
		JFrameObjectEvent eventObject = new JFrameObjectEvent();
		jf_login.addWindowListener(eventObject);
		btnLogin.addActionListener(eventObject);
		btnReset.addActionListener(eventObject);
		btnExit.addActionListener(eventObject);
		tpass.addActionListener(eventObject);
	}

	/** ȸ�� ����Ʈ ȭ�� **/
	public void showList() {
		//listPane
		JLabel jl_list = new JLabel("ȸ�� ����Ʈ");
		jl_list.setFont(font);
		
		//�� ��� ������ �� ���� 
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);	
		
		//Member ���̺��� ������ �������� �۾�
		ArrayList<UserVO>memlist = main.system.memberSelectList();

		//JTable
		Object[] columns = {" ","ID","�н�����","��ȭ��ȣ","�ְ��̸�","�ְ�����"};
		DefaultTableModel model =new DefaultTableModel(columns,0);	
		JTable table= new JTable(model);
		table.setBackground(HospitalMgmUI.c1);
		Object[] row =new Object[6];  //Jtable�� �߰��Ǵ� �ϳ��� row �߰��� ��ü

		if(memlist.size() != 0) {
			model.setNumRows(0);
			
			for(UserVO vo : memlist) {
				if(vo != null) {
					row[0] = vo.getMno();
					row[1] = vo.getMid();
					row[2] = vo.getMpass();
					row[3] = vo.getMphone();
					row[4] = vo.getMname();
					row[5] = vo.getMkind();
				
					model.addRow(row);
				}
			}
			table.repaint();
		}
		
		model.fireTableDataChanged();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.getColumn(" ").setCellRenderer(dtcr);
	    table.getColumn("ID").setCellRenderer(dtcr);
	    table.getColumn("�н�����").setCellRenderer(dtcr);
	    table.getColumn("��ȭ��ȣ").setCellRenderer(dtcr);
	    table.getColumn("�ְ��̸�").setCellRenderer(dtcr);
	    table.getColumn("�ְ�����").setCellRenderer(dtcr);
		
	    JScrollPane pane=new JScrollPane(table);
		pane.setBounds(0,100,600,250);
		listPane.setLayout(new BorderLayout());
		listPane.add(BorderLayout.NORTH, jl_list);
		listPane.add(BorderLayout.CENTER,pane);
		
		jf.add(BorderLayout.CENTER,listPane);
		jf.setVisible(true);
	}
	
	/** ���� �˻� ó�� �޼ҵ� **/
	public void updateSearchProc() {
		String mid = tf_update.getText().trim();
		if(mid.equals("")) {
			//TextField�� �����Ͱ� ���� X
			JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���");
			tf_update.requestFocus();
		}else {
			//TextField�� �����Ͱ� ���� O
			//�˻� ���� --> sms �ý��ۿ��� �ش絥���� ��/�� Ȯ��
			idx = main.system.searchMid(mid);
			if(idx != 0) {
				//sms�� �ش� �й��� �л����� ��������
				UserVO vo = main.system.selectMemInfo(mid);
				//�л����� �����ϴ� ��� > ���� ����
				updateOKForm(vo);					
			}else {
				JOptionPane.showMessageDialog(null, "��� ������ �������� �ʽ��ϴ�");
			}
		}
	}
	/** ���� ó�� �޼ҵ� **/
	public void updateProc() {
		JOptionPane.showMessageDialog(null, "�����Ϸ�");
		
		ArrayList<String> dataList = new ArrayList<String>();
		for(TextField tf : tf_update_list) {
			dataList.add(tf.getText().trim());
		}
		
		UserVO vo = new UserVO();
		vo.setMid(dataList.get(0));
		vo.setMpass(dataList.get(1));
		vo.setMphone(dataList.get(2));
		vo.setMname(dataList.get(3));
		vo.setMkind(dataList.get(4));

		if(main.system.menUpdate(vo)) {
			//�����Ϸ�
			JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
			for(TextField tf : tf_update_list) {
				tf.setText("");
			}
			showList();
//			selectForm(); //��ȸȭ�� ȣ��
		}else {
			//��������
			JOptionPane.showMessageDialog(null, "���� ����");
		}
		
	}
	
	/** ��� ���� ȭ�� **/
	public void updateForm() {
		tf_update_list = new ArrayList<TextField>();
		
		//1. �й� �˻� �� ���� �� �˻���ư�� �̺�Ʈ �߰�
		Panel update_top = new Panel(new BorderLayout());
		Panel search_panel = new Panel();
		
		Label label = new Label("������ ��� ID>");
		tf_update = new TextField(20);
		update_search = new JButton("�˻�"); //Object�� �ּҷ� ActionPerformed
		update_search.setBackground(HospitalMgmUI.c2);
		
		tf_update_list.add(tf_update); //������ ������ �й�
		
		search_panel.add(label);
		search_panel.add(tf_update);
		search_panel.add(update_search);
		
		update_top.add(BorderLayout.CENTER, search_panel);
		
		updatePane.setLayout(new BorderLayout());
		updatePane.add(BorderLayout.NORTH,update_top);		
		jf.add(updatePane);		
		jf.setVisible(true);
		
		tf_update.addActionListener(new JFrameObjectEvent());
		update_search.addActionListener(new JFrameObjectEvent());
	}
	
	/** ���� ������ ��� �� : �н�����, ��ȭ��ȣ, �ְ��̸�, �ְ����� **/
	public void updateOKForm(UserVO vo) {
		update_bottom.removeAll();
		
		Panel label_panel = new Panel(new GridLayout(4,1));
		Panel tf_panel = new Panel(new GridLayout(4,1));
		Panel btn_panel = new Panel();
		Button btn_update = new Button("�����Ϸ�");
		Button btn_reset = new Button("�������");
		btn_panel.add(btn_update);
		btn_panel.add(btn_reset);
		
		String[] data_list = new String[4];
		data_list[0] = vo.getMpass();
		data_list[1] = vo.getMphone();
		data_list[2] = vo.getMname();
		data_list[3] = vo.getMkind();
		
		for(int i=0; i<form_names.length; i++) {
			Panel p1 = new Panel();
			Panel p2 = new Panel();
			Label label = new Label(form_names[i]);
			TextField tf = new TextField(20);
			tf.setText(data_list[i]);
			p1.add(label); 	p2.add(tf);
			label_panel.add(p1);
			tf_panel.add(p2);
			
			tf_update_list.add(tf);
		}
		
		update_bottom.add(BorderLayout.NORTH, new Label());
		update_bottom.add(BorderLayout.WEST, label_panel);
		update_bottom.add(BorderLayout.CENTER, tf_panel);
		update_bottom.add(BorderLayout.SOUTH, btn_panel);
		
		updatePane.add(BorderLayout.CENTER, update_bottom);
		jf.add(updatePane);
		jf.setVisible(true);
		
		btn_update.addActionListener(new JFrameObjectEvent());
		btn_reset.addActionListener(new JFrameObjectEvent());
		tf_update_last = tf_update_list.get(tf_update_list.size()-1);
		tf_update_last.addActionListener(new JFrameObjectEvent());
		
	}
	/** �������� **/
	public void updateFailForm() {
		update_bottom.removeAll();
		update_bottom.add(BorderLayout.NORTH, new Label());
		update_bottom.add(BorderLayout.CENTER, new Label("-- ��� ������ �������� �ʽ��ϴ� --"));
		
		updatePane.add(update_bottom, BorderLayout.CENTER);
		jf.add(BorderLayout.CENTER, update_bottom);
		jf.setVisible(true);
	}

	/** ���� ȭ�� **/
	public void deleteMember() {
		//deletePane
		jp_deleteSearch = new JPanel();
		jp_deleteSearch.setBackground(Color.WHITE);
		jl_deleteSearchName = new JLabel("������ ��� ID >");
		jt_deleteSearch = new JTextField(20);
		jb_deleteButton = new JButton("Ȯ��");
		jb_deleteButton.setBackground(HospitalMgmUI.c3);
		jl_deleteSearchName.setFont(main.getFont());
		
		jp_deleteSearch.add(jl_deleteSearchName);
		jp_deleteSearch.add(jt_deleteSearch);
		jp_deleteSearch.add(jb_deleteButton);
		deletePane.add(jp_deleteSearch);
		
		jt_deleteSearch.addActionListener(new JFrameObjectEvent());	
		jb_deleteButton.addActionListener(new JFrameObjectEvent());	
		
		deletePane.setSize(300,300);
		jf.add(deletePane, BorderLayout.CENTER);
		jf.setVisible(true);
	}
	
	/** ������ ���̵� üũ **/
	public boolean deleteDataCheck(String mid) {
		return main.system.deleteSearch(mid);
	}
	
	/** ���� ���� **/
	public void deleteProc(String mid) {
		boolean result = main.system.delete(mid);
		if(result) {
			JOptionPane.showMessageDialog(null, main.getMsg("���� �Ϸ�~"));
		}else {
			JOptionPane.showMessageDialog(null, main.getMsg("���� ����~"));
		}
	}//deleteProc
	
	/** �г� �����ֱ� ���� **/
	public void resetPane() {
		main_panel.setVisible(false);
		listPane.setVisible(false);
		updatePane.setVisible(false);
		deletePane.setVisible(false);
	}
	
	/** �޴� �̵� ���� **/
	public void switchPane(String menu) {
		resetPane();			
		if(menu.equals("ȸ�� ����Ʈ")) {
			listPane.removeAll();
			showList();
			listPane.setVisible(true);
		}else if(menu.equals("ȸ������ ����")) {		
			updatePane.removeAll();
			updateForm();
			updatePane.setVisible(true);
		}else if(menu.equals("ȸ������ ����")) {		
			deletePane.removeAll();
			deleteMember();
			deletePane.setVisible(true);
		}
	}
		
	/** �Ŵ��� �α��� ������ ��ȿ�� üũ **/
	public boolean validationCheck() {
		boolean result = false;
		//tid, tpass�� ���� ������ �޽����� ���
		if(tid.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���");
			tid.requestFocus();
		}else if(tpass.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "�н����带 �Է����ּ���");
			tpass.requestFocus();
		}else {
			result = true;
		}
		
		return result;
	}
	
	/** �Ŵ��� �α��� üũ **/
	public void loginCheck() {
//		String did = "manager";  
//		String dpass = "123";
		String uid = tid.getText().trim();
		String upass = tpass.getText().trim();
		
		boolean result = main.system.manlogin(uid, upass);
		
		if(result) {
			//�α��μ��� : id,pass ����
			JOptionPane.showMessageDialog(null, "�α��� ����");
			jf_login.setVisible(false);
			jf.setVisible(true);
				
		}else {
			//�α��� ���� : id ����, pass �ٸ�
			JOptionPane.showMessageDialog(null, "�α��� ����");
		}
		
	}	

	/** �̺�Ʈ ó�� Ŭ���� **/
		class JFrameObjectEvent extends WindowAdapter
												implements ActionListener{
			//Field
			HospitalManager main;
			
			//Constructor
			public JFrameObjectEvent() {
		
			}
	
			public JFrameObjectEvent(HospitalManager main) {
				this.main = main;
			}	
			//������ �̺�Ʈ ó��
			public void windowClosing(WindowEvent we) {
				//������ â ����
				jf_login.setVisible(false);
				jf.setVisible(false);
			}
			
			//�׼� �̺�Ʈ ó��
			public void actionPerformed(ActionEvent e) {
				String bname = e.getActionCommand();
				Object obj = e.getSource();
				
				if(bname.equals("ȸ�� ����Ʈ")) {	
					//����Ʈ ���
					main.switchPane("ȸ�� ����Ʈ");
				}else if(bname.equals("ȸ������ ����")){
					// ���� ���� ���
					main.switchPane("ȸ������ ����");
				}else if(bname.equals("ȸ������ ����")){
					// ���� ���� ���
					main.switchPane("ȸ������ ����");
				}else if(bname.equals("�α���") || tpass  == e.getSource()) {	
					if(validationCheck()) 	loginCheck();				
				}else if(bname.equals("���")){
					tid.setText("");
					tpass.setText("");
					jf_login.setVisible(true);
				}else if(obj == update_search || obj == tf_update) {
					updateSearchProc();
				}else if(bname.equals("�����Ϸ�") || obj == tf_update_last) {
					updateProc();
				}else if(bname.equals("�������")) {
					JOptionPane.showMessageDialog(null, "�������");
				}else if(bname.equals("����")) {
					System.out.println("--  ���� ��ư�� Ŭ���Ǿ����ϴ�. --");
					System.exit(0);
				}else if(!jt_deleteSearch.getText().equals("") || jb_deleteButton.equals("Ȯ��")) {
					String mid = jt_deleteSearch.getText().trim();
					
					if(deleteDataCheck(mid)) {
						int result = JOptionPane.showConfirmDialog(null,
								"������ �����Ͻðڽ��ϱ�?");				
						if(result == 0)  deleteProc(mid);
					}else {
						//������ ������ ����
						JOptionPane.showMessageDialog(null,
								"������ �����Ͱ� �������� �ʽ��ϴ�.");
					}
				}else {
					JOptionPane.showMessageDialog(null,
							"�������� �Է����ּ���");
					jt_deleteSearch.requestFocus();
				}
			}
		}
		

			
}
	

	