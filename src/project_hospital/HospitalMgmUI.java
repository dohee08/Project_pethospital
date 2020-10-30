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
		
		String id;	//Login���� ������ id
		JButton btnLogout;


		JPanel HospiResPane = new JPanel(new GridLayout(8,1));
		JPanel SalonResPane = new JPanel(new GridLayout(7,1));
		JPanel MyPagePane = new JPanel();
		JPanel BoardPane = new JPanel();
		
		//�ٹ̱�
		public static Font font = new Font("���� ���", Font.BOLD, 12);
		public static Color c1 = new Color(255,231,159);
		public static Color c2 = new Color(238,217,154);
		public static Color c3 = new Color(229,197,148);
		
		//Constructor
		public HospitalMgmUI() {
			super("�ְߺ����ý���");
			showMain();
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(600,500);
			setVisible(true);
		}
		
		//Method
		//LoginŬ�������� ���̵� �ҷ�����
		public String callId(String uid) {
			if(uid != null) {
				this.id = uid;
			}
			return id;
		}
		
		public void showMain() {	//ó�� ����ȭ��
			showPane = new JPanel();
			showButtonPane = new JPanel();
			btnLogin = new JButton("�α���");
			btnManager = new JButton("������");
			btnJoin = new JButton("ȸ������");
			
			jl_title = new JLabel("\n �ְߺ��� �ý��ۿ� ���Ű��� ȯ���մϴ� ");
			ImageIcon image_icon = new ImageIcon("images/pet.jpg");
			Image image = image_icon.getImage();
			Image changedImg = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(changedImg);
			jl_img = new JLabel(icon);
			
			jl_title.setFont(new Font("���� ���",Font.BOLD,16));
			btnLogin.setFont(font);
			btnJoin.setFont(font);
			btnManager.setFont(font);
			
			showPane.add(jl_title);	
			showPane.add(jl_img);
			showButtonPane.add(btnLogin);
			showButtonPane.add(btnManager);
			showButtonPane.add(btnJoin);
			
			//Panel ����
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
		
		public void start() {	//���� UI ����!!!
			showPane.setVisible(false);
			showButtonPane.setVisible(false);
			
		    mainPane = new JPanel();
		    menuPane = new JPanel();
		    status_panel = new JPanel();	// --������ �α��� �Ͽ����ϴ�--
		    
		    //Panel ����
		    mainPane.setBackground(Color.WHITE);
		    menuPane.setBackground(Color.WHITE);
		      
		    status_panel = new JPanel(new BorderLayout());
		    menuPane = new JPanel(new GridLayout(1,5));
		    menuPane.setSize(300,300);
		    mypage_menu_panel  = new JPanel(new GridLayout(3,1)); //���������� �޴�
		    
		    //���� UI> ȸ�� ���̵� ���� ����
		    jl_status = new JLabel("-- " + id + "�� �α��� �Ͽ����ϴ� --");
		    jl_status.setFont(font);
		    jl_status.setBackground(Color.WHITE);
		    status_panel.setLayout(new BorderLayout());
		    status_panel.add(BorderLayout.WEST,jl_status);
		    
		    //���� UI> ó������ ����
		    btnLogout = new JButton("�α׾ƿ�");
		    btnLogout.setBackground(c3);
		    status_panel.add(BorderLayout.EAST,btnLogout);
		    status_panel.setBackground(Color.WHITE);
			
		    //���� UI> 1.��ư ����
		    btnHospiRes = new JButton("���� ����");
			btnSalonRes = new JButton("�̿� ����");
			btnMyPage = new JButton("ȸ�� ����");
			btnBoard = new JButton("�Խ���");
			btnExit = new JButton("����");
			
			//2.��ư ��Ʈ
			btnHospiRes.setFont(font);
			btnSalonRes.setFont(font);
			btnMyPage.setFont(font);
			btnBoard.setFont(font);
			btnExit.setFont(font);
			
			//3.��ư ����
			btnHospiRes.setBackground(c1);
			btnSalonRes.setBackground(c2);
			btnMyPage.setBackground(c3);
			btnBoard.setBackground(c1);
			btnExit.setBackground(Color.LIGHT_GRAY);
			
			//4.��ư ����
			menuPane.add(btnHospiRes);
			menuPane.add(btnSalonRes);
			menuPane.add(btnMyPage);
			menuPane.add(btnBoard);
			menuPane.add(btnExit);
			
			//���� UI> ��ư �̺�Ʈ ����
			HospitalMgmUIEvent eventObj = new HospitalMgmUIEvent(this);
			
			btnLogout.addActionListener(eventObj);
			btnHospiRes.addActionListener(eventObj);
			btnSalonRes.addActionListener(eventObj);
			btnBoard.addActionListener(eventObj);
			btnMyPage.addActionListener(eventObj);
			btnExit.addActionListener(eventObj);
			addWindowListener(eventObj);
			
			//���� UI> ���� ȭ�� 
//			jl_main = new JLabel("���� ȭ��");
//			jl_main.setFont(font);
//			mainPane.add(jl_main);
		     
			//���� UI â ��ġ ����
		    add(BorderLayout.NORTH, menuPane);      
		    add(BorderLayout.CENTER, mainPane); 
		    add(BorderLayout.SOUTH, status_panel);
		    add(BorderLayout.WEST,mypage_menu_panel);
		    
		    //���� UI â ȭ�� ����
		    Dimension fsize = getSize();
			Dimension scsize = Toolkit.getDefaultToolkit().getScreenSize(); 
			int width = (int)(scsize.getWidth()-fsize.getWidth())/2;
			int height =(int)(scsize.getHeight()-fsize.getHeight())/2;
			
			setLocation(width, height);
		    setSize(800,600);
		    setVisible(true);
		 }//start method
		
		
		//�޴� �̵� ����
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
			if(menu.equals("���� ����")) {
				HospiResPane.removeAll();
				HospiResPane.setVisible(true);
			}else if(menu.equals("�̿� ����")) {		
				SalonResPane.removeAll();
				SalonResPane.setVisible(true);
			}else if(menu.equals("ȸ�� ����")) {		
				MyPagePane.removeAll();
				mypage_menu_panel.removeAll();
				MyPagePane.setVisible(true);
				mypage_menu_panel.setVisible(true);
			}else if(menu.equals("�Խ���")) {		
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

		//�޽����� �Է¹޾� JLabel �����ϰ� ��Ʈ�� �����Ͽ� ����
		public JLabel getMsg(String msg) {
			JLabel label = new JLabel(msg);
			label.setFont(font);
			return label;
		}
		
		//�̺�Ʈ ó�� Ŭ����
		class HospitalMgmUIEvent extends WindowAdapter
								implements ActionListener{
			//Field
			HospitalMgmUI main;
			
			//Constructor
			public HospitalMgmUIEvent() {}
			
			public HospitalMgmUIEvent(HospitalMgmUI main) {
				this.main = main;
			}		
			//������ �̺�Ʈ ó��
			public void windowClosing(WindowEvent we) {
				JOptionPane.showMessageDialog(null,getMsg("���α׷� ����!!!"));
				system.dao.close();
				System.exit(0);
			}
			
			//�׼� �̺�Ʈ ó��
			public void actionPerformed(ActionEvent ae) {
				Object obj = ae.getSource();

				if(btnLogin ==obj) {
					new HospitalLogin(main);
				}else if(btnJoin == obj) {
					new HospitalRegister(main);
				}else if(btnManager == obj) {
					new HospitalManager(main);
				}else if(btnLogout == obj) {	//�α׾ƿ�
					callId("");
					id = "";
					dispose();
					new HospitalMgmUI();
				}else if(btnHospiRes == obj) {
					// �������� â���� �ѱ��!
					new HospitalReserve(main).reserve();
				}else if(btnSalonRes == obj) {
					// �̿뿹�� â���� �ѱ��!
					new HospitalHairCut(main).hairCut();
				}else if(btnMyPage == obj) {
					// ȸ�� ���� â���� �ѱ��!
					new HospitalMyPage(main).MyPage();
				}else if(btnBoard == obj) {
					// �Խ��� â���� �ѱ��!
					new HospitalBoard(main).HospitalBoard();
				}else if(btnExit == obj) {
					String msg = "���α׷��� �����Ͻðڽ��ϱ�?";
					int result = JOptionPane.showConfirmDialog(null, getMsg(msg));
					if(result == 0)  {
						//DB close
						system.dao.close();		//�θ� �޼ҵ� ��밡��
						System.exit(0);
					}
				}
			}		
		}//event class	
}
