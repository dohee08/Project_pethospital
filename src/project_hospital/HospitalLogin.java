package project_hospital;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
			jf = new JFrame("�α���");	
			JPanel jp_title = new JPanel();
			JPanel jp_id = new JPanel();
			JPanel jp_pass = new JPanel();
			JPanel jp_button = new JPanel();
			
			//����
			jp_title.setBackground(Color.WHITE);
			jp_id.setBackground(Color.WHITE);
			jp_pass.setBackground(Color.WHITE);
			jp_button.setBackground(Color.WHITE);
			
			JLabel title = new JLabel("�α��� ��");		
			JLabel id = new JLabel("���̵�");
			JLabel pass = new JLabel("�н�����");
			tid = new JTextField(15);
			tpass = new JPasswordField(15);
			JButton btnLogin = new JButton("�α���");
			JButton btnReset = new JButton("���");
			JButton btnExit = new JButton("����");
			
			//����
			btnLogin.setBackground(HospitalMgmUI.c1);
			btnReset.setBackground(HospitalMgmUI.c2);
			btnExit.setBackground(HospitalMgmUI.c3);
			
			title.setFont(font);  id.setFont(font);   pass.setFont(font);
			btnLogin.setFont(font);    btnReset.setFont(font);
			btnExit.setFont(font);
			
			jp_title.add(title);
			jp_id.add(id);     jp_id.add(tid);
			jp_pass.add(pass);  jp_pass.add(tpass);
			jp_button.add(btnLogin);
			jp_button.add(btnReset);
			jp_button.add(btnExit);
			
			jf.setLayout(new GridLayout(4,1));
			jf.add(jp_title);		jf.add(jp_id);		jf.add(jp_pass);
			jf.add(jp_button);
			
			jf.setSize(300,220);	
			
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
				}else if(bname.equals("���")){
					tid.setText("");
					tpass.setText("");
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
//			String did = "test";  
//			String dpass = "1234";
			String uid = tid.getText().trim();
			String upass = tpass.getText().trim();
			System.out.println("--" + uid);
			
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
