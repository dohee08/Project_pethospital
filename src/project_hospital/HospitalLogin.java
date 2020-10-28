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
			
			Font font = new Font("맑은 고딕", Font.BOLD, 12);
			jf = new JFrame("로그인");	
			JPanel jp_title = new JPanel();
			JPanel jp_id = new JPanel();
			JPanel jp_pass = new JPanel();
			JPanel jp_button = new JPanel();
			
			//색깔
			jp_title.setBackground(Color.WHITE);
			jp_id.setBackground(Color.WHITE);
			jp_pass.setBackground(Color.WHITE);
			jp_button.setBackground(Color.WHITE);
			
			JLabel title = new JLabel("로그인 폼");		
			JLabel id = new JLabel("아이디");
			JLabel pass = new JLabel("패스워드");
			tid = new JTextField(15);
			tpass = new JPasswordField(15);
			JButton btnLogin = new JButton("로그인");
			JButton btnReset = new JButton("취소");
			JButton btnExit = new JButton("종료");
			
			//색깔
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
				}else if(bname.equals("취소")){
					tid.setText("");
					tpass.setText("");
					jf.setVisible(true);
				}else if(bname.equals("종료")) {
					System.out.println("--  종료 버튼이 클릭되었습니다. --");
					System.exit(0);
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
//			String did = "test";  
//			String dpass = "1234";
			String uid = tid.getText().trim();
			String upass = tpass.getText().trim();
			System.out.println("--" + uid);
			
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
