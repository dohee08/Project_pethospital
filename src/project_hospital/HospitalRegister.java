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
			
			Font font = new Font("맑은 고딕", Font.BOLD, 12);
			jf = new JFrame("회원가입 창");	
			jf.setBounds(new Rectangle(380, 0, 380, 370));
			jf.getContentPane().setLayout(null);
			
			JLabel title = new JLabel("< 회원가입 >");		
			title.setBounds(150, 15, 200, 20);
			jf.getContentPane().add(title);
			
			JLabel id = new JLabel("ID");
			id.setBounds(55, 25, 120, 100);
			jf.getContentPane().add(id);
			
			JLabel pass = new JLabel("비밀번호");
			pass.setBounds(55, 50, 120, 125);
			jf.getContentPane().add(pass);
			
			JLabel phone = new JLabel("전화번호");
			phone.setBounds(55, 76, 120, 150);
			jf.getContentPane().add(phone);
			
			JLabel name = new JLabel("동물 이름");
			name.setBounds(55, 103, 120, 175);
			jf.getContentPane().add(name);
			
			JLabel kind = new JLabel("동물 종류");
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
			
			JButton btnRegister = new JButton("회원가입");
			btnRegister.setBounds(65, 270, 90, 25);
			jf.getContentPane().add(btnRegister);
			
			JButton btnReset = new JButton("초기화");
			btnReset.setBounds(160, 270, 70, 25);
			jf.getContentPane().add(btnReset);
			
			JButton btnExit = new JButton("취소");
			btnExit.setBounds(235, 270, 70, 25);
			jf.getContentPane().add(btnExit);
			
			//색깔
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
			
			//이벤트 핸들러 메소드(리스너 메소드) 호출
			JFrameObjectEvent eventObject = new JFrameObjectEvent();
			jf.addWindowListener(eventObject);
			btnRegister.addActionListener(eventObject);
			btnReset.addActionListener(eventObject);
			btnExit.addActionListener(eventObject);
			tpass.addActionListener(eventObject);
		}
		//이벤트 처리 클래스
		class JFrameObjectEvent extends WindowAdapter
													implements ActionListener{
			//윈도우 이벤트 처리
			public void windowClosing(WindowEvent we) {
				//회원가입 창 종료
				jf.setVisible(false);
			}
				
			//액션 이벤트 처리
			public void actionPerformed(ActionEvent e) {
				String bname = e.getActionCommand();

				if(bname.equals("회원가입")) {	
					//회원가입 폼 (유효성 체크 !!!!)
					if(validationCheck()) {
						RegisterCheck();
					}
				}else if(bname.equals("초기화")){
					tid.setText("");
					tpass.setText("");
					tname.setText("");
					tphone.setText("");
					tkind.setText("");
					jf.setVisible(true);
				}else if(bname.equals("취소")) {
					int result = JOptionPane.showConfirmDialog(null, "회원가입을 취소하겠습니까?");
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
			//tid, tpass, tname, tphone에 값이 없으면 메시지를 띄움
			if(tid.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
				tid.requestFocus();
			}else if(tpass.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "패스워드를 입력해주세요");
				tpass.requestFocus();
			}else if(tphone.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요");
				tphone.requestFocus();
			}else if(tname.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
				tname.requestFocus();
			}else if(tkind.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "애견 종류를 입력해주세요");
				tkind.requestFocus();
			}else {
				result = true;
			}
					
			return result;
		}
		
		/** 회원가입 체크 **/
		public void RegisterCheck() {
			//데이터 중복 체크!!!! -> db list
//			String did = "test";  
//			String dpass = "1234";
			
			//회원가입 TextField에서 가져온 값
			String mid = tid.getText().trim();
			String mpass = tpass.getText().trim();
			String mname = tname.getText().trim();
			String mphone = tphone.getText().trim();
			String mkind = tkind.getText().trim();
			
			//mid 중복체크
			if(main.system.searchMid(mid) == 0) {
				vo = new UserVO();
				vo.setMid(mid);
				vo.setMpass(mpass);
				vo.setMname(mname);
				vo.setMphone(mphone);
				vo.setMkind(mkind);
				
				if(main.system.memRegister(vo)) {
					JOptionPane.showMessageDialog(null, "회원가입 완료");
					tid.setText("");
					tpass.setText("");
					tname.setText("");
					tphone.setText("");
					tkind.setText("");
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "기존 회원이 존재합니다");
				tid.setText("");
				tid.requestFocus();
			}
			
		}
		
}
