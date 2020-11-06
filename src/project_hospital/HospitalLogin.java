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
			
			Font font = new Font("맑은 고딕", Font.BOLD, 12);
			jf = new JFrame("회원 로그인 창");	
			jf.setBounds(new Rectangle(300, 0, 320, 240));
			jf.getContentPane().setLayout(null);
			
			JLabel title = new JLabel("< 회원 로그인 >");		
			title.setBounds(110, 15, 150, 15);
			jf.getContentPane().add(title);
			
			JLabel id = new JLabel("ID");
			id.setBounds(40, 25, 120, 90);
			jf.getContentPane().add(id);
			
			JLabel pass = new JLabel("비밀번호");
			pass.setBounds(40, 50, 120, 125);
			jf.getContentPane().add(pass);
			
			tid = new JTextField(15);
			tid.setBounds(95, 62, 175, 21);
			jf.getContentPane().add(tid);
			
			tpass = new JPasswordField(15);
			tpass.setBounds(95, 102, 175, 21);
			jf.getContentPane().add(tpass);
			
			JButton btnLogin = new JButton("로그인");
			btnLogin.setBounds(45, 155, 70, 25);
			jf.getContentPane().add(btnLogin);
			
			JButton btnReset = new JButton("초기화");
			btnReset.setBounds(120, 155, 70, 25);
			jf.getContentPane().add(btnReset);
			
			JButton btnExit = new JButton("취소");
			btnExit.setBounds(195, 155, 70, 25);
			jf.getContentPane().add(btnExit);
			
			//색깔
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
			
			//이벤트 핸들러 메소드(리스너 메소드) 호출
			JFrameObjectEvent eventObject = new JFrameObjectEvent();
			jf.addWindowListener(eventObject);
			btnLogin.addActionListener(eventObject);
			btnReset.addActionListener(eventObject);
			btnExit.addActionListener(eventObject);
			tpass.addActionListener(eventObject);
		}

		//이벤트 처리 클래스
		class JFrameObjectEvent extends WindowAdapter
													implements ActionListener{
			//윈도우 이벤트 처리
			public void windowClosing(WindowEvent we) {
				//로그인 창 종료
				jf.setVisible(false);
			}
			
			//액션 이벤트 처리
			public void actionPerformed(ActionEvent e) {
				String bname = e.getActionCommand();

				if(bname.equals("로그인") || tpass  == e.getSource()) {	
					if(validationCheck()) 	loginCheck();				
				}else if(bname.equals("초기화")){
					tid.setText("");
					tpass.setText("");
					jf.setVisible(true);
				}else if(bname.equals("종료")) {
					jf.setVisible(false);
				}else if(bname.equals("취소")) {
					int result = JOptionPane.showConfirmDialog(null, "로그인을 취소하겠습니까?");
					if(result == 0)  {
						jf.setVisible(false);
					}
				}
			}
			
		}
		
		//Method
		/** 데이터 유효성 체크 **/
		public boolean validationCheck() {
			boolean result = false;
			//tid, tpass에 값이 없으면 메시지를 띄움
			if(tid.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
				tid.requestFocus();
			}else if(tpass.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "패스워드를 입력해주세요");
				tpass.requestFocus();
			}else {
				result = true;
			}
			
			return result;
		}
		
		/** 로그인 체크 **/
		public void loginCheck() {
			
			String uid = tid.getText().trim();
			String upass = tpass.getText().trim();
			
			boolean result = main.system.memlogin(uid, upass);
			
			if(result) {
				//로그인성공 : id,pass 동일
				JOptionPane.showMessageDialog(null, "로그인 성공");
				jf.setVisible(false);
				main.callId(uid);
				main.start();
					
			}else {
				//로그인 실패 : id 동일, pass 다름
				JOptionPane.showMessageDialog(null, "로그인 실패");
			}
		}
		
}
