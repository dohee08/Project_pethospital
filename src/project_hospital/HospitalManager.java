package project_hospital;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JTextField;

public class HospitalManager {
	JFrame jf;
	JPanel main_panel, button_panel;
	JPanel listPane, deletePane;
	JButton btnList, btnDelete;
	TextArea ta;
	HospitalMgmUI main;

	JTextField tid;  
	JPasswordField tpass;
	JFrame jf_login;
	JPanel jp_deleteSearch;
	JLabel jl_deleteSearchName;
	JTextField jt_deleteSearch;
	
	public HospitalManager(HospitalMgmUI main) {
		this.main = main;
		login();
		
		Font font = new Font("���� ���", Font.BOLD, 12);
		jf = new JFrame("������ ȭ��");	
		
		main_panel = new JPanel();
	    button_panel = new JPanel();
	    listPane = new JPanel();
	    deletePane = new JPanel();
	    
	    button_panel = new JPanel(new GridLayout(2,1));
	    button_panel.setSize(50,100);
	    
	    //������ UI ��ư ����
	    btnList = new JButton("ȸ�� ����Ʈ");
		btnDelete = new JButton("ȸ�� ����");
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
	//�α��� ��
	public void login() {
		Font font = new Font("���� ���", Font.BOLD, 12);
		jf_login = new JFrame("�α���");	
		JPanel jp_title = new JPanel();
		JPanel jp_id = new JPanel();
		JPanel jp_pass = new JPanel();
		JPanel jp_button = new JPanel();
		
		JLabel title = new JLabel("�α��� ��");		
		JLabel id = new JLabel("���̵�   ");
		JLabel pass = new JLabel("�н�����   ");
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

	//ȸ�� ����Ʈ ȭ��
	public void showList() {
		//listPane
		JLabel jl_list = new JLabel("����Ʈ ȭ��");
		listPane.add(jl_list);
		ta = new TextArea(28,65);
		ta.setEditable(false);
		
		//Member ���̺��� ������ �������� �۾�
//		ArrayList<MemberVO>mlist = main.system.list();
		
//		if(mlist.size() != 0) {
			ta.append("\t\t ȸ�� ����Ʈ ���\n");
			ta.append("------------------------------------------------------\n");			
			ta.append("��ȣ\t\t�̸�\t\t�ּ�\n");
			ta.append("------------------------------------------------------\n");	
			
//			for(MemberVO vo : mlist) {
//				ta.append(vo.getRno() + "\t\t");
//				ta.append(vo.getName() + "\t\t");
//				ta.append(vo.getAddr() + "\n");				
//			}
//		}else {
//			ta.append("--  �����Ͱ� �������� �ʽ��ϴ�.  --" );
//		}
		listPane.add(ta);	
		listPane.setSize(300,300);
		jf.add(listPane, BorderLayout.CENTER);
		jf.setVisible(true);
	}
	
	//���� ȭ��
	public void deleteMember() {
		//deletePane
		JLabel jl_delete = new JLabel("���� ȭ��");
		deletePane.add(jl_delete);
		
		jp_deleteSearch = new JPanel();
		jl_deleteSearchName = new JLabel("������>>");
		jt_deleteSearch = new JTextField(20);
//		jl_deleteSearchName.setFont(MemberMgmUI.font);
		
		jp_deleteSearch.add(jl_deleteSearchName);
		jp_deleteSearch.add(jt_deleteSearch);
		deletePane.add(jp_deleteSearch);
		
//		jt_deleteSearch.addActionListener(new MemberDeleteEvent());	
		
		deletePane.setSize(300,300);
		jf.add(deletePane, BorderLayout.CENTER);
		jf.setVisible(true);
	}
	
	//�޴� �̵� ����
		public void resetPane() {
			main_panel.setVisible(false);
			listPane.setVisible(false);
			deletePane.setVisible(false);
		}
//			
		public void switchPane(String menu) {
			resetPane();			
			if(menu.equals("ȸ�� ����Ʈ")) {
				listPane.removeAll();
				showList();
				listPane.setVisible(true);
			}else if(menu.equals("ȸ�� ����")) {		
				deletePane.removeAll();
				deleteMember();
				deletePane.setVisible(true);
			}
		}
		
		
	//�̺�Ʈ ó�� Ŭ����
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
				}else if(bname.equals("ȸ�� ����")){
					// ���� ���� ���
					main.switchPane("ȸ�� ����");
				}else if(bname.equals("�α���") || tpass  == e.getSource()) {	
					if(validationCheck()) 	loginCheck();				
				}else if(bname.equals("���")){
					tid.setText("");
					tpass.setText("");
					jf_login.setVisible(true);
				}else if(bname.equals("����")) {
					System.out.println("--  ���� ��ư�� Ŭ���Ǿ����ϴ�. --");
					System.exit(0);
				}
			}
			//Method
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
				String did = "manager";  
				String dpass = "123";
				String uid = tid.getText().trim();
				String upass = tpass.getText().trim();
				
//				boolean result = main.system.login(uid, upass);
				
				if(did.equals(uid) ) {
					
					if(dpass.equals(upass)) {
						//�α��μ��� : id,pass ����
						JOptionPane.showMessageDialog(null, "�α��� ����");
						jf_login.setVisible(false);
						jf.setVisible(true);
//						main.callId(uid);
//						main.start();
						
					}else {
						//�α��� ���� : id ����, pass �ٸ�
							JOptionPane.showMessageDialog(null, "�α��� ����");
					}
				}else {
					//System.out.println("�α��� ����");
					JOptionPane.showMessageDialog(null, "�α��� ����, ���̵� �ٸ�");
				}
			}
			
		}
	}

	