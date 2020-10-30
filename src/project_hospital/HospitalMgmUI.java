package project_hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HospitalMgmUI extends JFrame{
		//Field
		public static final ArrayList<UserVO> list =new ArrayList<UserVO>();
		public HospitalMgmSystem system = new HospitalMgmSystem();
		
		public static final int HOSPIRES = 1;
		public static final int SALONRES = 2;
		public static final int MYPAGE = 3;
		public static final int BOARD = 4;

		JPanel showPane, showButtonPane;
		JPanel mainPane, menuPane;

		JPanel  buttonPane,mypage_menu_panel;
		JButton btnLogin, btnJoin, btnManager;
		JButton btnHospiRes, btnSalonRes, btnMyPage, btnBoard, btnExit;
		JLabel jl_title, jl_img;
		
		JPanel status_panel;
		JLabel jl_status, jl_main;
		
		String id;	//Login에서 가져온 id
		JButton btnLogout;


		JPanel HospiResPane = new JPanel(new GridLayout(8,1));
		JPanel SalonResPane = new JPanel(new GridLayout(7,1));
		JPanel MyPagePane = new JPanel();
		JPanel BoardPane = new JPanel();
		
		//꾸미기
		public static Font font = new Font("맑은 고딕", Font.BOLD, 12);
		public static Color c1 = new Color(255,231,159);
		public static Color c2 = new Color(238,217,154);
		public static Color c3 = new Color(229,197,148);
		
		//Constructor
		public HospitalMgmUI() {
			super("애견병원시스템");
			showMain();
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(600,500);
			setVisible(true);
		}
		
		//Method
		//Login클래스에서 아이디 불러오기
		public String callId(String uid) {
			if(uid != null) {
				this.id = uid;
			}
			return id;
		}
		
		public void showMain() {	//처음 시작화면
			showPane = new JPanel();
			showButtonPane = new JPanel();
			btnLogin = new JButton("로그인");
			btnManager = new JButton("관리자");
			btnJoin = new JButton("회원가입");
			
			jl_title = new JLabel("\n 애견병원 시스템에 오신것을 환영합니다 ");
			ImageIcon image_icon = new ImageIcon("images/pet.jpg");
			Image image = image_icon.getImage();
			Image changedImg = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(changedImg);
			jl_img = new JLabel(icon);
			
			jl_title.setFont(new Font("맑은 고딕",Font.BOLD,16));
			btnLogin.setFont(font);
			btnJoin.setFont(font);
			btnManager.setFont(font);
			
			showPane.add(jl_title);	
			showPane.add(jl_img);
			showButtonPane.add(btnLogin);
			showButtonPane.add(btnManager);
			showButtonPane.add(btnJoin);
			
			//Panel 색깔
			showPane.setBackground(Color.WHITE);
			showButtonPane.setBackground(Color.WHITE);
			
			add(showPane, BorderLayout.CENTER);
			add(showButtonPane, BorderLayout.SOUTH);
			
			
			
			
			
			btnLogin.setBackground(c1);
			btnManager.setBackground(c2);
			btnJoin.setBackground(c3);
			
			
			
			btnLogin.addActionListener(new HospitalMgmUIEvent(this));
			btnManager.addActionListener(new HospitalMgmUIEvent(this));
			btnJoin.addActionListener(new HospitalMgmUIEvent(this));
			addWindowListener(new HospitalMgmUIEvent(this));
		}
		
		public void start() {	//메인 UI 연결!!!
			showPane.setVisible(false);
			showButtonPane.setVisible(false);
			
		    mainPane = new JPanel();
		    menuPane = new JPanel();
		    status_panel = new JPanel();	// --ㅇㅇ가 로그인 하였습니다--
		    
		    //Panel 색깔
		    mainPane.setBackground(Color.WHITE);
		    menuPane.setBackground(Color.WHITE);
		      
		    status_panel = new JPanel(new BorderLayout());
		    menuPane = new JPanel(new GridLayout(1,5));
		    menuPane.setSize(300,300);
		    mypage_menu_panel  = new JPanel(new GridLayout(3,1)); //마이페이지 메뉴
		    
		    //메인 UI> 회원 아이디 상태 띄우기
		    jl_status = new JLabel("-- " + id + "가 로그인 하였습니다 --");
		    jl_status.setFont(font);
		    jl_status.setBackground(Color.WHITE);
		    status_panel.setLayout(new BorderLayout());
		    status_panel.add(BorderLayout.WEST,jl_status);
		    
		    //메인 UI> 처음으로 가기
		    btnLogout = new JButton("로그아웃");
		    btnLogout.setBackground(c3);
		    status_panel.add(BorderLayout.EAST,btnLogout);
		    status_panel.setBackground(Color.WHITE);
			
		    //메인 UI> 1.버튼 정의
		    btnHospiRes = new JButton("병원 예약");
			btnSalonRes = new JButton("미용 예약");
			btnMyPage = new JButton("회원 정보");
			btnBoard = new JButton("게시판");
			btnExit = new JButton("종료");
			
			//2.버튼 폰트
			btnHospiRes.setFont(font);
			btnSalonRes.setFont(font);
			btnMyPage.setFont(font);
			btnBoard.setFont(font);
			btnExit.setFont(font);
			
			//3.버튼 색깔
			btnHospiRes.setBackground(c1);
			btnSalonRes.setBackground(c2);
			btnMyPage.setBackground(c3);
			btnBoard.setBackground(c1);
			btnExit.setBackground(Color.LIGHT_GRAY);
			
			//4.버튼 삽입
			menuPane.add(btnHospiRes);
			menuPane.add(btnSalonRes);
			menuPane.add(btnMyPage);
			menuPane.add(btnBoard);
			menuPane.add(btnExit);
			
			//메인 UI> 버튼 이벤트 정의
			HospitalMgmUIEvent eventObj = new HospitalMgmUIEvent(this);
			
			btnLogout.addActionListener(eventObj);
			btnHospiRes.addActionListener(eventObj);
			btnSalonRes.addActionListener(eventObj);
			btnBoard.addActionListener(eventObj);
			btnMyPage.addActionListener(eventObj);
			btnExit.addActionListener(eventObj);
			addWindowListener(eventObj);
			
			//메인 UI> 시작 화면 
//			jl_main = new JLabel("시작 화면");
//			jl_main.setFont(font);
//			mainPane.add(jl_main);
		     
			//메인 UI 창 위치 정의
		    add(BorderLayout.NORTH, menuPane);      
		    add(BorderLayout.CENTER, mainPane); 
		    add(BorderLayout.SOUTH, status_panel);
		    add(BorderLayout.WEST,mypage_menu_panel);
		    
		    //메인 UI 창 화면 띄우기
		    Dimension fsize = getSize();
			Dimension scsize = Toolkit.getDefaultToolkit().getScreenSize(); 
			int width = (int)(scsize.getWidth()-fsize.getWidth())/2;
			int height =(int)(scsize.getHeight()-fsize.getHeight())/2;
			
			setLocation(width, height);
		    setSize(800,600);
		    setVisible(true);
		 }//start method
		
		
		//메뉴 이동 제어
		public void resetPane() {
			mypage_menu_panel.setVisible(false);
			showPane.setVisible(false);
			showButtonPane.setVisible(false);
			mainPane.setVisible(false);
			HospiResPane.setVisible(false);
			SalonResPane.setVisible(false);
			MyPagePane.setVisible(false);
			BoardPane.setVisible(false);
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
				MyPagePane.removeAll();
				mypage_menu_panel.removeAll();
				MyPagePane.setVisible(true);
				mypage_menu_panel.setVisible(true);
			}else if(menu.equals("게시판")) {		
				BoardPane.removeAll();
				BoardPane.setVisible(true);
			}
		}
		
		public void switchPane(int menu) {
			resetPane();
			switch(menu) {
			case 1 : 
				HospiResPane.removeAll();
				HospiResPane.setBackground(Color.WHITE);
				HospiResPane.setVisible(true);
				break;
			case 2 : 
				SalonResPane.removeAll();
				SalonResPane.setBackground(Color.WHITE);
				SalonResPane.setVisible(true);
				break;
			case 3 : 
				MyPagePane.removeAll();
				mypage_menu_panel.removeAll();
				MyPagePane.setBackground(Color.WHITE);
				mypage_menu_panel.setBackground(Color.WHITE);
				MyPagePane.setVisible(true);
				mypage_menu_panel.setVisible(true);
				break;
			case 4 : 
				BoardPane.removeAll();
				BoardPane.setVisible(true);
				BoardPane.setBackground(Color.WHITE);
				break;
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
				JOptionPane.showMessageDialog(null,getMsg("프로그램 종료!!!"));
				system.dao.close();
				System.exit(0);
			}
			
			//액션 이벤트 처리
			public void actionPerformed(ActionEvent ae) {
				Object obj = ae.getSource();

				if(btnLogin ==obj) {
					new HospitalLogin(main);
				}else if(btnJoin == obj) {
					new HospitalRegister(main);
				}else if(btnManager == obj) {
					new HospitalManager(main);
				}else if(btnLogout == obj) {	//로그아웃
					callId("");
					id = "";
					dispose();
					new HospitalMgmUI();
				}else if(btnHospiRes == obj) {
					// 병원예약 창으로 넘기기!
					new HospitalReserve(main).reserve();
				}else if(btnSalonRes == obj) {
					// 미용예약 창으로 넘기기!
					new HospitalHairCut(main).hairCut();
				}else if(btnMyPage == obj) {
					// 회원 정보 창으로 넘기기!
					new HospitalMyPage(main).MyPage();
				}else if(btnBoard == obj) {
					// 게시판 창으로 넘기기!
					new HospitalBoard(main).HospitalBoard();
				}else if(btnExit == obj) {
					String msg = "프로그램을 종료하시겠습니까?";
					int result = JOptionPane.showConfirmDialog(null, getMsg(msg));
					if(result == 0)  {
						//DB close
						system.dao.close();		//부모 메소드 사용가능
						System.exit(0);
					}
				}
			}		
		}//event class	
}
