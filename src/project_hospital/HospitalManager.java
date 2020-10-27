package project_hospital;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
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
	JButton btnList, btnDelete;
	TextArea ta;
	HospitalMgmUI main;

	JTextField tid;  
	JPasswordField tpass;
	JFrame jf_login;
	JPanel jp_deleteSearch;
	JLabel jl_deleteSearchName;
	JTextField jt_deleteSearch;
	JButton jb_deleteButton;
//	private JPanel jp_updateMid;
//	private JPanel jp_updateMpass;
//	private JPanel jp_updateMphone;
//	private JPanel jp_updateMname;
//	private JPanel jp_updateMkind;
//	private JLabel jl_updateMid;
//	private JLabel jl_updateMpass;
//	private JLabel jl_updateMkind;
//	private JLabel jl_updateMname;
//	private JLabel jl_updateMphone;
//	private JLabel jl_updateSearchName;
//	private JTextField jt_updateMpass;
//	private JTextField jt_updateMphone;
//	private JTextField jt_updateMname;
//	private JButton btnUpdate;
//	private JTextField jt_updateMkind;
//	private JButton btnUpdateReset;
//	private JTextField jt_updateSearch;
	
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
	    deletePane = new JPanel();
	    updatePane = new JPanel();
	    
	    button_panel = new JPanel(new GridLayout(2,1));
	    button_panel.setSize(50,100);
	    
	    //������ UI ��ư ����
	    btnList = new JButton("ȸ�� ����Ʈ");
		btnDelete = new JButton("ȸ������ ����");
		btnList.setFont(font);  btnDelete.setFont(font);	
		
		button_panel.add(btnList);
		button_panel.add(btnDelete);
		
		//���� UI â ��ġ ����
	    jf.add(BorderLayout.WEST, button_panel);      
	    jf.add(BorderLayout.CENTER, main_panel); //- ������ ���� ���� ����
	    
	    jf.setSize(600,500);
		jf.setVisible(false);
		
		//������ UI ��ư �̺�Ʈ ����
		btnList.addActionListener(new JFrameObjectEvent(this));
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
		
		title.setFont(font);  id.setFont(font);   pass.setFont(font);
		btnLogin.setFont(font);    btnReset.setFont(font);
		btnExit.setFont(font);
		
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
		
		//�� ��� ������ �� ���� 
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);	
		
		//Member ���̺��� ������ �������� �۾�
		ArrayList<UserVO>memlist = main.system.memberSelectList();

		//JTable
		Object[] columns = {"ID","�н�����","��ȭ��ȣ","�ְ��̸�","�ְ�����"};
		DefaultTableModel model =new DefaultTableModel(columns,0);	
		JTable table= new JTable(model);
		Object[] row =new Object[5];  //Jtable�� �߰��Ǵ� �ϳ��� row �߰��� ��ü

		if(memlist.size() != 0) {
			model.setNumRows(0);
			
			for(UserVO vo : memlist) {
				if(vo != null) {
					row[0] = vo.getMid();
					row[1] = vo.getMpass();
					row[2] = vo.getMphone();
					row[3] = vo.getMname();
					row[4] = vo.getMkind();
				
					model.addRow(row);
				}
			}
			table.repaint();
		}
		
		model.fireTableDataChanged();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
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
	
	/**
	public void updateMember() {
		
		jp_updateMid = new JPanel();
		jp_updateMpass = new JPanel();
		jp_updateMphone = new JPanel();
		jp_updateMname = new JPanel();
		jp_updateMkind = new JPanel();
		
		jl_updateMid = new JLabel("ID");
		jl_updateMpass = new JLabel("�н�����");
		jl_updateMphone = new JLabel("��ȭ��ȣ");
		jl_updateMname = new JLabel("�ְ��̸�");
		jl_updateMkind = new JLabel("�ְ�����");
		
		jl_updateSearchName = new JLabel("���� ID >");	
		jt_updateSearch = new JTextField(20);
		
		jt_updateMpass = new JTextField(20);
		jt_updateMphone = new JTextField(20);
		jt_updateMname = new JTextField(20);
		jt_updateMkind = new JTextField(20);
		
		btnUpdate = new JButton("�����Ϸ�");
		btnUpdateReset = new JButton("�ٽþ���");
		
		jl_updateMpass.setFont(font);
		jl_updateMphone.setFont(font);
		jl_updateMname.setFont(font);
		jl_updateMkind.setFont(font);
		jl_updateSearchName.setFont(font);
		btnUpdate.setFont(font);
		btnUpdateReset.setFont(font);
		
		
		jp_updateSearch.add(jl_updateSearchName);
		jp_updateSearch.add(jt_updateSearch);
		jp_updateName.add(jl_updateName);
		jp_updateName.add(jt_updateName);
		jp_updateAddr.add(jl_updateAddr);
		jp_updateAddr.add(jt_updateAddr);
		jp_updateButton.add(btnUpdate);
		jp_updateButton.add(btnUpdateReset);
		
		updatePane.add(jp_updateSearch);
		updatePane.add(jp_updateName);
		updatePane.add(jp_updateAddr);
		updatePane.add(jp_updateButton);
		
		main.add(updatePane, BorderLayout.CENTER);
		main.setVisible(true);
		
		jt_updateSearch.addActionListener(new JFrameObjectEvent());
		btnUpdate.addActionListener(new JFrameObjectEvent());
		btnUpdateReset.addActionListener(new JFrameObjectEvent());
		
		
	}//update method
	
	//updateProc
	public void updateProc() {
		String name = jt_updateSearch.getText().trim();
		
		MemberVO rvo = main.system.search(name);
		
		if(rvo != null) {
			jt_updateName.setText(rvo.getName());
			jt_updateAddr.setText(rvo.getAddr());
		}else {
			JOptionPane.showMessageDialog(null,
					main.getMsg("������ �����Ͱ� �������� �ʽ��ϴ�."));
		}		
	}//updateProc
	
	//updateFormCheck
	public boolean updateFormCheck() {
		boolean result = false;
		
		if(jt_updateSearch.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					main.getMsg("�������� �Է����ּ���"));
			jt_updateSearch.requestFocus();
		}else if(jt_updateName.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					main.getMsg("�̸��� �Է����ּ���"));
			jt_updateName.requestFocus();
		}else if(jt_updateAddr.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					main.getMsg("�ּҸ� �Է����ּ���"));
			jt_updateAddr.requestFocus();
		}else {
			result = true;
		}
		
		return result;
	}//updateFormCheck
	
	//updateResult
	public void updateResult() {
		MemberVO vo = new MemberVO();
		vo.setUp_name(jt_updateSearch.getText().trim());
		vo.setName(jt_updateName.getText().trim());
		vo.setAddr(jt_updateAddr.getText().trim());
		
		boolean result = main.system.update(vo);
		if(result) {
			JOptionPane.showMessageDialog(null, "�����Ϸ�~");
		}else {
			JOptionPane.showMessageDialog(null, "��������~");
		}
		
//		String name = jt_updateSearch.getText().trim();
//		for(MemberVO vo : MemberMgmUI.list) {
//			if(vo.getName().equals(name)) {
//				vo.setName(jt_updateName.getText());
//				vo.setAddr(jt_updateAddr.getText());
//				JOptionPane.showMessageDialog(null,
//						main.getMsg("������ �Ϸ�Ǿ����ϴ�."));
//			}
//		}	
	}//updateResult
	**/
	/** ���� ȭ�� **/
	public void deleteMember() {
		//deletePane
		jp_deleteSearch = new JPanel();
		jl_deleteSearchName = new JLabel("���� ID >");
		jt_deleteSearch = new JTextField(20);
		jb_deleteButton = new JButton("Ȯ��");
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
//			updateMember();
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
				if(bname.equals("ȸ�� ����Ʈ")) {	
					//����Ʈ ���
					main.switchPane("ȸ�� ����Ʈ");
				}else if(bname.equals("ȸ������ ����")){
					// ���� ���� ���
					main.switchPane("ȸ������ ����");
				}else if(bname.equals("�α���") || tpass  == e.getSource()) {	
					if(validationCheck()) 	loginCheck();				
				}else if(bname.equals("���")){
					tid.setText("");
					tpass.setText("");
					jf_login.setVisible(true);
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
	

	