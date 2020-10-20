package project_hospital;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HospitalMgmUI extends JFrame{
	//Field
//		public static final ArrayList<MemberVO> list =new ArrayList<MemberVO>();
//		public MemberMgmSystem system = new MemberMgmSystem();
		
//		public static final int REGISTER = 1;
//		public static final int LIST = 2;
//		public static final int SEARCH = 3;
		
		public static final int REGISTER = 1;
		public static final int LIST = 2;
		public static final int SEARCH = 3;
		public static final int UPDATE = 4;
		public static final int DELETE = 5;
		
		JPanel showPane, showButtonPane;
		JButton btnLogin, btnJoin, btnManager;
		JPanel mainPane, menuPane;	
		JButton btnReg, btnList, btnSearch, btnUpdate, btnDelete;
		JButton btnHospiRes, btnSalonRes, btnMyPage, btnExit;
		JLabel jl_title, jl_img;
		JFrame fmain;
		JPanel main_panel, button_panel, insert_panel;

		JPanel HospiResPane = new JPanel();
		JPanel SalonResPane = new JPanel();
		JPanel MemInfoPane = new JPanel();
		
		JPanel regPane = new JPanel(new GridLayout(10,1));
		JPanel listPane = new JPanel();
		JPanel searchPane = new JPanel();
		JPanel updatePane = new JPanel(new GridLayout(10,1));
		JPanel deletePane = new JPanel();	

		public static Font font = new Font("맑은 고딕", Font.BOLD, 12);
		
		//Constructor
		public HospitalMgmUI() {
			showMain();
			//start();
		}
		
		//Method
		public void showMain() {
			fmain = new JFrame("애견병원 예약 시스템");
			showPane = new JPanel();
			showButtonPane = new JPanel();
			btnLogin = new JButton("로그인");
			btnManager = new JButton("관리자");
			btnJoin = new JButton("회원가입");
			
			jl_title = new JLabel("\n== 애견병원 시스템에 오신것을 환영합니다 ==");
			jl_img = new JLabel(new ImageIcon("images/main.png"));
			jl_title.setFont(new Font("맑은 고딕",Font.BOLD,16));
			btnLogin.setFont(font);
			btnJoin.setFont(font);
			btnManager.setFont(font);
			
			showPane.add(jl_img);
			showPane.add(jl_title);	
			showButtonPane.add(btnLogin);
			showButtonPane.add(btnManager);
			showButtonPane.add(btnJoin);
			
			fmain.add(showPane, BorderLayout.CENTER);
			fmain.add(showButtonPane, BorderLayout.SOUTH);
			
			fmain.setSize(600,500);
			fmain.setVisible(true);
			
			Dimension fsize = getSize();
			Dimension scsize = Toolkit.getDefaultToolkit().getScreenSize(); 
			int width = (int)(scsize.getWidth()-fsize.getWidth())/2;
			int height =(int)(scsize.getHeight()-fsize.getHeight())/2;
			
			fmain.setLocation(width, height);
			fmain.setVisible(true);
			
			btnLogin.addActionListener(new HospitalMgmUIEvent(this));
			btnManager.addActionListener(new HospitalMgmUIEvent(this));
			btnJoin.addActionListener(new HospitalMgmUIEvent(this));
			fmain.addWindowListener(new HospitalMgmUIEvent(this));
		}
		
		public void start() {	//메인 UI 연결!!!
			fmain.setVisible(false);
			showPane.setVisible(false);
			showButtonPane.setVisible(false);
			
		    main_panel = new JPanel();
		    button_panel = new JPanel();
		    insert_panel = new JPanel();	// --ㅇㅇ가 로그인 하였습니다--
//		    String[] names = {"병원 예약", "미용 예약", "회원 정보", "종료"};
		      
		    insert_panel = new JPanel(new BorderLayout());
		    button_panel = new JPanel(new GridLayout(1,4));
		    button_panel.setSize(300,300);
		    
		    //메인 UI 버튼 정의
		    btnHospiRes = new JButton("병원 예약");
			btnSalonRes = new JButton("미용 예약");
			btnMyPage = new JButton("회원 정보");
			btnExit = new JButton("종료");
			
			button_panel.add(btnHospiRes);
			button_panel.add(btnSalonRes);
			button_panel.add(btnMyPage);
			button_panel.add(btnExit);
			
			//메인 UI 버튼 이벤트 정의
			HospitalMgmUIEvent eventObj = new HospitalMgmUIEvent(this);
			
			btnHospiRes.addActionListener(eventObj);
			btnSalonRes.addActionListener(eventObj);
			btnMyPage.addActionListener(eventObj);
			btnExit.addActionListener(eventObj);
			fmain.addWindowListener(eventObj);
		     
			//메인 UI 창 위치 정의
		    fmain.add(BorderLayout.NORTH, button_panel);      
//		    f.add(BorderLayout.CENTER, main_panel); - 원래는 메인 먼저 띄우기
		    fmain.add(BorderLayout.CENTER, insert_panel);
//		    insertForm();
		      
		    //메인 UI 창 화면 띄우기
		    Dimension fsize = getSize();
			Dimension scsize = Toolkit.getDefaultToolkit().getScreenSize(); 
			int width = (int)(scsize.getWidth()-fsize.getWidth())/2;
			int height =(int)(scsize.getHeight()-fsize.getHeight())/2;
			
			setLocation(width, height);
		    fmain.setSize(1000,700);
		    fmain.setVisible(true);
//		    f.addWindowListener(this);   
		 }

//			mainPane = new JPanel();	
//			menuPane = new JPanel(new GridLayout(10,1));
//			jl_title = new JLabel("\n== 애견병원 시스템에 오신것을 환영합니다 ==");
//			jl_img = new JLabel(new ImageIcon("src/member/main.png"));
//			
//			btnReg = new JButton("회원등록");
//			btnList = new JButton("회원리스트");
//			btnSearch = new JButton("회원검색");
//			btnUpdate = new JButton("회원수정");
//			btnDelete = new JButton("회원삭제");
//			btnExit = new JButton("종료");
//			
//			jl_title.setFont(new Font("맑은 고딕",Font.BOLD,16));
//			btnReg.setFont(font);	btnList.setFont(font);  btnSearch.setFont(font);
//			btnUpdate.setFont(font);  btnDelete.setFont(font);  
//			btnExit.setFont(font);
//			mainPane.add(jl_img);
//			mainPane.add(jl_title);		
//			
//			menuPane.add(btnReg);
//			menuPane.add(btnList);
//			menuPane.add(btnSearch);
//			menuPane.add(btnUpdate);
//			menuPane.add(btnDelete);
//			menuPane.add(btnExit);
//
//			add(menuPane, BorderLayout.WEST);
//			add(mainPane, BorderLayout.CENTER);
//			
//			setSize(600,500);
//			
//			Dimension fsize = getSize();
//			Dimension scsize = Toolkit.getDefaultToolkit().getScreenSize(); 
//			int width = (int)(scsize.getWidth()-fsize.getWidth())/2;
//			int height =(int)(scsize.getHeight()-fsize.getHeight())/2;
//			
//			setLocation(width, height);
//			setVisible(true);
//			
//			HospitalMgmUIEvent eventObj = new HospitalMgmUIEvent(this);
//			addWindowListener(eventObj);
//			btnReg.addActionListener(eventObj);
//			btnList.addActionListener(eventObj);
//			btnSearch.addActionListener(eventObj);
//			btnUpdate.addActionListener(eventObj);
//			btnDelete.addActionListener(eventObj);
//			btnExit.addActionListener(eventObj);

		//start method
		
		
		//메뉴 이동 제어
		public void resetPane() {
			showPane.setVisible(false);
			showButtonPane.setVisible(false);
			mainPane.setVisible(false);
			regPane.setVisible(false);
			listPane.setVisible(false);
			searchPane.setVisible(false);
			updatePane.setVisible(false);
			deletePane.setVisible(false);
		}
		
		public void switchPane(String menu) {
			resetPane();			
			if(menu.equals("병원 예약")) {
				HospiResPane.removeAll();
				HospiResPane.setVisible(true);
			}else if(menu.equals("미용 예약")) {		
				SalonResPane.removeAll();
				SalonResPane.setVisible(true);
			}else if(menu.equals("회원 정보")) {		
				MemInfoPane.removeAll();
				MemInfoPane.setVisible(true);
//			}else if(menu.equals("update")) {		
//				updatePane.removeAll();
//				updatePane.setVisible(true);
//			}else if(menu.equals("delete")) {		
//				deletePane.removeAll();
//				deletePane.setVisible(true);
			}
		}
		
		public void switchPane(int menu) {
			resetPane();
			switch(menu) {
			case 1 : 
				HospiResPane.removeAll();
				HospiResPane.setVisible(true);
				break;
			case 2 : 
				SalonResPane.removeAll();
				SalonResPane.setVisible(true);
				break;
			case 3 : 
				MemInfoPane.removeAll();
				MemInfoPane.setVisible(true);
				break;
//			case 4 : 
//				updatePane.removeAll();
//				updatePane.setVisible(true);
//				break;
//			case 5 : 
//				deletePane.removeAll();
//				deletePane.setVisible(true);
//				break;	
			}
		}//switchPane method

		//메시지를 입력받아 JLabel 생성하고 폰트를 수정하여 리턴
		public JLabel getMsg(String msg) {
			JLabel label = new JLabel(msg);
			label.setFont(font);
			return label;
		}
		
		
		//이벤트 처리 클래스
		class HospitalMgmUIEvent extends WindowAdapter
								implements ActionListener{
			//Field
			HospitalMgmUI main;
			
			//Constructor
			public HospitalMgmUIEvent() {}
			
			public HospitalMgmUIEvent(HospitalMgmUI main) {
				this.main = main;
			}		
			
			//윈도우 이벤트 처리
			public void windowClosing(WindowEvent we) {
				//JOptionPane.showMessageDialog(null,getMsg("프로그램 종료!!!"));
//				system.dao.close();
				System.exit(0);
			}
			
			//액션 이벤트 처리
			public void actionPerformed(ActionEvent ae) {
				Object obj = ae.getSource();

				if(btnLogin ==obj) {
					new HospitalLogin(main);
//					new MemberLogin(main);
				}else if(btnJoin == obj) {
					new HospitalRegister(main);
//					start();
				}else if(btnManager == obj) {
					new HospitalManager(main);
				}else if(btnHospiRes == obj) {
					// 병원예약 창으로 넘기기!
					JOptionPane.showMessageDialog(null, "병원예약");
//					new MemberRegister(main).register();
				}else if(btnSalonRes == obj) {
					// 미용예약 창으로 넘기기!
					JOptionPane.showMessageDialog(null, "미용예약");
//					new MemberList(main).list();
				}else if(btnMyPage == obj) {
					// 회원 정보 창으로 넘기기!
					JOptionPane.showMessageDialog(null, "회원정보");
//					new MemberSearch(main).search();
//				}else if(btnUpdate == obj) {
//					//update();
////					new MemberUpdate(main).update();
//				}else if(btnDelete == obj) {
//					//delete();
////					new MemberDelete(main).delete();
//				}
				}else if(btnExit == obj) {
					String msg = "프로그램을 종료하시겠습니까?";
					int result = JOptionPane.showConfirmDialog(null, getMsg(msg));
					if(result == 0)  {
						//DB close
//						system.dao.close();		//부모 메소드 사용가능
						System.exit(0);
					}
				}
			}		
		}//event class	
}
