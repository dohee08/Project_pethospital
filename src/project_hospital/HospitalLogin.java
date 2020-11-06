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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HospitalLogin {
		//Field
		JFrame jf;
		JTextField tid;  
		JPasswordField tpass;
		HospitalHairCut hc = new HospitalHairCut();
		HospitalMgmUI main;
		
		public HospitalLogin() {
			
		}
		//Constructor
		public HospitalLogin(HospitalMgmUI main) {	
			this.main = main;
			
			Font font = new Font("���� ���", Font.BOLD, 12);
			jf = new JFrame("ȸ�� �α��� â");	
			jf.setBounds(new Rectangle(300, 0, 320, 240));
			jf.getContentPane().setLayout(null);
			
			JLabel title = new JLabel("< ȸ�� �α��� >");		
			title.setBounds(110, 15, 150, 15);
			jf.getContentPane().add(title);
			
			JLabel id = new JLabel("ID");
			id.setBounds(40, 25, 120, 90);
			jf.getContentPane().add(id);
			
			JLabel pass = new JLabel("��й�ȣ");
			pass.setBounds(40, 50, 120, 125);
			jf.getContentPane().add(pass);
			
			tid = new JTextField(15);
			tid.setBounds(95, 62, 175, 21);
			jf.getContentPane().add(tid);
			
			tpass = new JPasswordField(15);
			tpass.setBounds(95, 102, 175, 21);
			jf.getContentPane().add(tpass);
			
			JButton btnLogin = new JButton("�α���");
			btnLogin.setBounds(45, 155, 70, 25);
			jf.getContentPane().add(btnLogin);
			
			JButton btnReset = new JButton("�ʱ�ȭ");
			btnReset.setBounds(120, 155, 70, 25);
			jf.getContentPane().add(btnReset);
			
			JButton btnExit = new JButton("���");
			btnExit.setBounds(195, 155, 70, 25);
			jf.getContentPane().add(btnExit);
			
			//����
			btnLogin.setBackground(HospitalMgmUI.c1);
			btnReset.setBackground(HospitalMgmUI.c2);
			btnExit.setBackground(HospitalMgmUI.c3);
			
			title.setFont(font);  id.setFont(font);   pass.setFont(font);
			btnLogin.setFont(font);    btnReset.setFont(font);
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
			btnLogin.addActionListener(eventObject);
			btnReset.addActionListener(eventObject);
			btnExit.addActionListener(eventObject);
			tpass.addActionListener(eventObject);
		}

		//�̺�Ʈ ó�� Ŭ����
		class JFrameObjectEvent extends WindowAdapter
													implements ActionListener{
			//������ �̺�Ʈ ó��
			public void windowClosing(WindowEvent we) {
				//�α��� â ����
				jf.setVisible(false);
			}
			
			//�׼� �̺�Ʈ ó��
			public void actionPerformed(ActionEvent e) {
				String bname = e.getActionCommand();

				if(bname.equals("�α���") || tpass  == e.getSource()) {	
					if(validationCheck()) 	loginCheck();				
				}else if(bname.equals("�ʱ�ȭ")){
					tid.setText("");
					tpass.setText("");
					jf.setVisible(true);
				}else if(bname.equals("����")) {
					jf.setVisible(false);
				}else if(bname.equals("���")) {
					int result = JOptionPane.showConfirmDialog(null, "�α����� ����ϰڽ��ϱ�?");
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
		
		/** �α��� üũ **/
		public void loginCheck() {
			
			String uid = tid.getText().trim();
			String upass = tpass.getText().trim();
			
			boolean result = main.system.memlogin(uid, upass);
			
			if(result) {
				//�α��μ��� : id,pass ����
				JOptionPane.showMessageDialog(null, "�α��� ����");
				jf.setVisible(false);
				main.callId(uid);
				main.start();
					
			}else {
				//�α��� ���� : id ����, pass �ٸ�
				JOptionPane.showMessageDialog(null, "�α��� ����");
			}
		}
		
}
