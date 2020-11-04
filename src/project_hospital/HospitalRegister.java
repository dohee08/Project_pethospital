package project_hospital;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
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
			jf = new JFrame("ȸ������ â");	
			jf.setBounds(new Rectangle(380, 0, 380, 370));
			jf.getContentPane().setLayout(null);
			
			JLabel title = new JLabel("< ȸ������ >");		
			title.setBounds(150, 15, 200, 20);
			jf.getContentPane().add(title);
			
			JLabel id = new JLabel("ID");
			id.setBounds(55, 25, 120, 100);
			jf.getContentPane().add(id);
			
			JLabel pass = new JLabel("��й�ȣ");
			pass.setBounds(55, 50, 120, 125);
			jf.getContentPane().add(pass);
			
			JLabel phone = new JLabel("��ȭ��ȣ");
			phone.setBounds(55, 76, 120, 150);
			jf.getContentPane().add(phone);
			
			JLabel name = new JLabel("���� �̸�");
			name.setBounds(55, 103, 120, 175);
			jf.getContentPane().add(name);
			
			JLabel kind = new JLabel("���� ����");
			kind.setBounds(55, 130, 120, 200);
			jf.getContentPane().add(kind);
			
			tid = new JTextField(20);
			tid.setBounds(120, 65, 200, 20);
			jf.getContentPane().add(tid);
			
			tpass = new JTextField(20);
			tpass.setBounds(120, 104, 200, 20);
			jf.getContentPane().add(tpass);
			
			tphone = new JTextField(20);
			tphone.setBounds(120, 144, 200, 20);
			jf.getContentPane().add(tphone);
			
			tname = new JTextField(20);
			tname.setBounds(120, 184, 200, 20);
			jf.getContentPane().add(tname);
			
			tkind = new JTextField(20);
			tkind.setBounds(120, 224, 200, 20);
			jf.getContentPane().add(tkind);
			
			JButton btnRegister = new JButton("ȸ������");
			btnRegister.setBounds(65, 270, 90, 25);
			jf.getContentPane().add(btnRegister);
			
			JButton btnReset = new JButton("�ʱ�ȭ");
			btnReset.setBounds(160, 270, 70, 25);
			jf.getContentPane().add(btnReset);
			
			JButton btnExit = new JButton("���");
			btnExit.setBounds(235, 270, 70, 25);
			jf.getContentPane().add(btnExit);
			
			//����
			btnRegister.setBackground(HospitalMgmUI.c1);
			btnReset.setBackground(HospitalMgmUI.c2);
			btnExit.setBackground(HospitalMgmUI.c3);
			
			title.setFont(font);  id.setFont(font);   pass.setFont(font);
			name.setFont(font);   phone.setFont(font); kind.setFont(font);
			btnRegister.setFont(font);    btnReset.setFont(font);
			btnExit.setFont(font);
				
			jf.setBackground(Color.WHITE);
			jf.getContentPane().setBackground(Color.WHITE);
				
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
				}else if(bname.equals("�ʱ�ȭ")){
					tid.setText("");
					tpass.setText("");
					tname.setText("");
					tphone.setText("");
					tkind.setText("");
					jf.setVisible(true);
				}else if(bname.equals("���")) {
					int result = JOptionPane.showConfirmDialog(null, "ȸ�������� ����ϰڽ��ϱ�?");
					if(result == 0)  {
						jf.setVisible(false);
					}
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
			
			//mid �ߺ�üũ
			if(main.system.searchMid(mid) == 0) {
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
				
			}else {
				JOptionPane.showMessageDialog(null, "���� ȸ���� �����մϴ�");
				tid.setText("");
				tid.requestFocus();
			}
			
		}
		
}
