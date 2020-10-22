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
		
		Font font = new Font("맑은 고딕", Font.BOLD, 12);
		jf = new JFrame("관리자 화면");	
		
		main_panel = new JPanel();
	    button_panel = new JPanel();
	    listPane = new JPanel();
	    deletePane = new JPanel();
	    
	    button_panel = new JPanel(new GridLayout(2,1));
	    button_panel.setSize(50,100);
	    
	    //관리자 UI 버튼 정의
	    btnList = new JButton("회원 리스트");
		btnDelete = new JButton("회원 삭제");
		btnList.setFont(font);  btnDelete.setFont(font);	
		
		button_panel.add(btnList);
		button_panel.add(btnDelete);
		
		//메인 UI 창 위치 정의
	    jf.add(BorderLayout.WEST, button_panel);      
	    jf.add(BorderLayout.CENTER, main_panel); //- 원래는 메인 먼저 띄우기
	    
	    jf.setSize(600,500);
		jf.setVisible(false);
		
		//관리자 UI 버튼 이벤트 정의
		btnList.addActionListener(new JFrameObjectEvent(this));
		btnDelete.addActionListener(new JFrameObjectEvent(this));
		jf.addWindowListener(new JFrameObjectEvent(this));
		
	}
	//Method
	//로그인 폼
	public void login() {
		Font font = new Font("맑은 고딕", Font.BOLD, 12);
		jf_login = new JFrame("로그인");	
		JPanel jp_title = new JPanel();
		JPanel jp_id = new JPanel();
		JPanel jp_pass = new JPanel();
		JPanel jp_button = new JPanel();
		
		JLabel title = new JLabel("로그인 폼");		
		JLabel id = new JLabel("아이디   ");
		JLabel pass = new JLabel("패스워드   ");
		tid = new JTextField(15);
		tpass = new JPasswordField(15);
		JButton btnLogin = new JButton("로그인");
		JButton btnReset = new JButton("취소");
		JButton btnExit = new JButton("종료");
		
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
		
		//이벤트 핸들러 메소드(리스너 메소드) 호출
		JFrameObjectEvent eventObject = new JFrameObjectEvent();
		jf_login.addWindowListener(eventObject);
		btnLogin.addActionListener(eventObject);
		btnReset.addActionListener(eventObject);
		btnExit.addActionListener(eventObject);
		tpass.addActionListener(eventObject);
	}

	//회원 리스트 화면
	public void showList() {
		//listPane
		JLabel jl_list = new JLabel("리스트 화면");
		listPane.add(jl_list);
		ta = new TextArea(28,65);
		ta.setEditable(false);
		
		//Member 테이블에서 데이터 가져오는 작업
//		ArrayList<MemberVO>mlist = main.system.list();
		
//		if(mlist.size() != 0) {
			ta.append("\t\t 회원 리스트 출력\n");
			ta.append("------------------------------------------------------\n");			
			ta.append("번호\t\t이름\t\t주소\n");
			ta.append("------------------------------------------------------\n");	
			
//			for(MemberVO vo : mlist) {
//				ta.append(vo.getRno() + "\t\t");
//				ta.append(vo.getName() + "\t\t");
//				ta.append(vo.getAddr() + "\n");				
//			}
//		}else {
//			ta.append("--  데이터가 존재하지 않습니다.  --" );
//		}
		listPane.add(ta);	
		listPane.setSize(300,300);
		jf.add(listPane, BorderLayout.CENTER);
		jf.setVisible(true);
	}
	
	//삭제 화면
	public void deleteMember() {
		//deletePane
		JLabel jl_delete = new JLabel("삭제 화면");
		deletePane.add(jl_delete);
		
		jp_deleteSearch = new JPanel();
		jl_deleteSearchName = new JLabel("삭제명>>");
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
	
	//메뉴 이동 제어
		public void resetPane() {
			main_panel.setVisible(false);
			listPane.setVisible(false);
			deletePane.setVisible(false);
		}
//			
		public void switchPane(String menu) {
			resetPane();			
			if(menu.equals("회원 리스트")) {
				listPane.removeAll();
				showList();
				listPane.setVisible(true);
			}else if(menu.equals("회원 삭제")) {		
				deletePane.removeAll();
				deleteMember();
				deletePane.setVisible(true);
			}
		}
		
		
	//이벤트 처리 클래스
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
			//윈도우 이벤트 처리
			public void windowClosing(WindowEvent we) {
				//관리자 창 종료
				jf_login.setVisible(false);
				jf.setVisible(false);
			}
			
			//액션 이벤트 처리
			public void actionPerformed(ActionEvent e) {
				String bname = e.getActionCommand();
				if(bname.equals("회원 리스트")) {	
					//리스트 출력
					main.switchPane("회원 리스트");
				}else if(bname.equals("회원 삭제")){
					// 정보 삭제 출력
					main.switchPane("회원 삭제");
				}else if(bname.equals("로그인") || tpass  == e.getSource()) {	
					if(validationCheck()) 	loginCheck();				
				}else if(bname.equals("취소")){
					tid.setText("");
					tpass.setText("");
					jf_login.setVisible(true);
				}else if(bname.equals("종료")) {
					System.out.println("--  종료 버튼이 클릭되었습니다. --");
					System.exit(0);
				}
			}
			//Method
			/** 매니저 로그인 데이터 유효성 체크 **/
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
			
			/** 매니저 로그인 체크 **/
			public void loginCheck() {
				String did = "manager";  
				String dpass = "123";
				String uid = tid.getText().trim();
				String upass = tpass.getText().trim();
				
//				boolean result = main.system.login(uid, upass);
				
				if(did.equals(uid) ) {
					
					if(dpass.equals(upass)) {
						//로그인성공 : id,pass 동일
						JOptionPane.showMessageDialog(null, "로그인 성공");
						jf_login.setVisible(false);
						jf.setVisible(true);
//						main.callId(uid);
//						main.start();
						
					}else {
						//로그인 실패 : id 동일, pass 다름
							JOptionPane.showMessageDialog(null, "로그인 실패");
					}
				}else {
					//System.out.println("로그인 실패");
					JOptionPane.showMessageDialog(null, "로그인 실패, 아이디 다름");
				}
			}
			
		}
	}

	