package project_hospital;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JTextField;

public class HospitalRegister {
	//Field
		JFrame jf;
		JTextField tid, tpass, tname, tphone, tkind;  
		HospitalMgmUI main;
		UserVO vo;
		
		//Constructor
		public HospitalRegister(HospitalMgmUI main) {	
			this.main = main;
			
			Font font = new Font("���� ���", Font.BOLD, 12);
			jf = new JFrame("ȸ������");	
			JPanel jp_title = new JPanel();
			JPanel jp_id = new JPanel();
			JPanel jp_pass = new JPanel();
			JPanel jp_name = new JPanel();
			JPanel jp_phone = new JPanel();
			JPanel jp_kind = new JPanel();
			JPanel jp_button = new JPanel();
				
			JLabel title = new JLabel("ȸ������");		
			JLabel id = new JLabel("���̵�");
			JLabel pass = new JLabel("�н�����");
			JLabel phone = new JLabel("��ȭ��ȣ");
			JLabel name = new JLabel("������ �̸�");
			JLabel kind = new JLabel("������ ����");
			
			
			tid = new JTextField(20);
			tpass = new JTextField(20);
			tname = new JTextField(20);
			tphone = new JTextField(20);
			tkind = new JTextField(20);
			
			JButton btnRegister = new JButton("ȸ������");
			JButton btnReset = new JButton("���");
			JButton btnExit = new JButton("����");
			
			title.setFont(font);  id.setFont(font);   pass.setFont(font);
			name.setFont(font);   phone.setFont(font); kind.setFont(font);
			btnRegister.setFont(font);    btnReset.setFont(font);
			btnExit.setFont(font);
				
			jp_title.add(title);
			jp_id.add(id);       jp_id.add(tid);
			jp_pass.add(pass);   jp_pass.add(tpass);
			jp_name.add(name);   jp_name.add(tname);
			jp_phone.add(phone); jp_phone.add(tphone);
			jp_kind.add(kind);   jp_kind.add(tkind);
			
			jp_button.add(btnRegister);
			jp_button.add(btnReset);
			jp_button.add(btnExit);
				
			jf.setLayout(new GridLayout(7,1));
			jf.add(jp_title);		jf.add(jp_id);		jf.add(jp_pass);
			jf.add(jp_phone);		jf.add(jp_name); 	jf.add(jp_kind);
			jf.add(jp_button);
				
			jf.setSize(400,500);	
				
			Dimension fsize = jf.getSize();
			Dimension scsize = Toolkit.getDefaultToolkit().getScreenSize(); 
			int width = (int)(scsize.getWidth()-fsize.getWidth())/2;
			int height =(int)(scsize.getHeight()-fsize.getHeight())/2;
			
			jf.setLocation(width,height);
			jf.setVisible(true);	
			
			//�̺�Ʈ �ڵ鷯 �޼ҵ�(������ �޼ҵ�) ȣ��
			JFrameObjectEvent eventObject = new JFrameObjectEvent();
			jf.addWindowListener(eventObject);
			btnRegister.addActionListener(eventObject);
			btnReset.addActionListener(eventObject);
			btnExit.addActionListener(eventObject);
			tpass.addActionListener(eventObject);
		}
		//�̺�Ʈ ó�� Ŭ����
		class JFrameObjectEvent extends WindowAdapter
													implements ActionListener{
			//������ �̺�Ʈ ó��
			public void windowClosing(WindowEvent we) {
				//ȸ������ â ����
				jf.setVisible(false);
			}
				
			//�׼� �̺�Ʈ ó��
			public void actionPerformed(ActionEvent e) {
				String bname = e.getActionCommand();

				if(bname.equals("ȸ������")) {	
					//ȸ������ �� (��ȿ�� üũ !!!!)
					if(validationCheck()) {
						RegisterCheck();
					}
				}else if(bname.equals("���")){
					tid.setText("");
					tpass.setText("");
					tname.setText("");
					tphone.setText("");
					tkind.setText("");
					jf.setVisible(true);
				}else if(bname.equals("����")) {
					System.out.println("--  ���� ��ư�� Ŭ���Ǿ����ϴ�. --");
					System.exit(0);
				}
			}
				
		}
		
		//Method
		/** ������ ��ȿ�� üũ **/
		public boolean validationCheck() {
			boolean result = false;
			//tid, tpass, tname, tphone�� ���� ������ �޽����� ���
			if(tid.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���");
				tid.requestFocus();
			}else if(tpass.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�н����带 �Է����ּ���");
				tpass.requestFocus();
			}else if(tphone.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �Է����ּ���");
				tphone.requestFocus();
			}else if(tname.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���");
				tname.requestFocus();
			}else if(tkind.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�ְ� ������ �Է����ּ���");
				tkind.requestFocus();
			}else {
				result = true;
			}
					
			return result;
		}
		
		/** ȸ������ üũ **/
		public void RegisterCheck() {
			//������ �ߺ� üũ!!!! -> db list
//			String did = "test";  
//			String dpass = "1234";
			
			//ȸ������ TextField���� ������ ��
			String mid = tid.getText().trim();
			String mpass = tpass.getText().trim();
			String mname = tname.getText().trim();
			String mphone = tphone.getText().trim();
			String mkind = tkind.getText().trim();
			
			vo = new UserVO();
			vo.setMid(mid);
			vo.setMpass(mpass);
			vo.setMname(mname);
			vo.setMphone(mphone);
			vo.setMkind(mkind);
			
			if(main.system.memRegister(vo)) {
				JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�");
				tid.setText("");
				tpass.setText("");
				tname.setText("");
				tphone.setText("");
				tkind.setText("");
			}
			
		}
		
}
