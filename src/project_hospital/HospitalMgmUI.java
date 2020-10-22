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
		
		public static final int HOSPIRES = 1;
		public static final int SALONRES = 2;
		public static final int MYPAGE = 3;
		
		JPanel showPane, showButtonPane;
		JPanel mainPane, menuPane, buttonPane,mypage_menu_panel;
		JButton btnLogin, btnJoin, btnManager;
		JButton btnHospiRes, btnSalonRes, btnMyPage, btnExit;
		JLabel jl_title, jl_img;
		
		JPanel status_panel;
		JLabel jl_status, jl_main;
		
		String id;	//Login���� ������ id

		JPanel HospiResPane = new JPanel(new GridLayout(8,1));
		JPanel SalonResPane = new JPanel();
		JPanel MyPagePane = new JPanel();
		
		public static Font font = new Font("���� ���", Font.BOLD, 12);
		
		//Constructor
		public HospitalMgmUI() {
			showMain();
			//start();
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
			
			jl_title = new JLabel("\n== �ְߺ��� �ý��ۿ� ���Ű��� ȯ���մϴ� ==");
			jl_img = new JLabel(new ImageIcon("images/main.png"));
			jl_title.setFont(new Font("���� ���",Font.BOLD,16));
			btnLogin.setFont(font);
			btnJoin.setFont(font);
			btnManager.setFont(font);
			
			showPane.add(jl_img);
			showPane.add(jl_title);	
			showButtonPane.add(btnLogin);
			showButtonPane.add(btnManager);
			showButtonPane.add(btnJoin);
			
			add(showPane, BorderLayout.CENTER);
			add(showButtonPane, BorderLayout.SOUTH);
			
			setSize(600,500);
			setVisible(true);
			
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
		      
		    status_panel = new JPanel(new BorderLayout());
		    menuPane = new JPanel(new GridLayout(1,4));
		    menuPane.setSize(300,300);
		    mypage_menu_panel  = new JPanel(new GridLayout(3,1)); //���������� �޴�
		    
		    //���� UI> ȸ�� ���̵� ���� ����
		    jl_status = new JLabel("-- " + id + "�� �α��� �Ͽ����ϴ� --");
		    jl_status.setFont(font);
		    status_panel.add(jl_status);
			
		    //���� UI> ��ư ����
		    btnHospiRes = new JButton("���� ����");
			btnSalonRes = new JButton("�̿� ����");
			btnMyPage = new JButton("ȸ�� ����");
			btnExit = new JButton("����");
			
			btnHospiRes.setFont(font);
			btnSalonRes.setFont(font);
			btnMyPage.setFont(font);
			btnExit.setFont(font);
			
			menuPane.add(btnHospiRes);
			menuPane.add(btnSalonRes);
			menuPane.add(btnMyPage);
			menuPane.add(btnExit);
			
			//���� UI> ��ư �̺�Ʈ ����
			HospitalMgmUIEvent eventObj = new HospitalMgmUIEvent(this);
			
			btnHospiRes.addActionListener(eventObj);
			btnSalonRes.addActionListener(eventObj);
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
//		    Dimension fsize = getSize();
//			Dimension scsize = Toolkit.getDefaultToolkit().getScreenSize(); 
//			int width = (int)(scsize.getWidth()-fsize.getWidth())/2;
//			int height =(int)(scsize.getHeight()-fsize.getHeight())/2;
//			
//			setLocation(width, height);
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
				MyPagePane.removeAll();
				mypage_menu_panel.removeAll();
				MyPagePane.setVisible(true);
				mypage_menu_panel.setVisible(true);
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
//				system.dao.close();
				System.exit(0);
			}
			
			//�׼� �̺�Ʈ ó��
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
					// �������� â���� �ѱ��!
					new HospitalReserve(main).reserve();
//					new MemberRegister(main).register();
				}else if(btnSalonRes == obj) {
					// �̿뿹�� â���� �ѱ��!
					JOptionPane.showMessageDialog(null, "�̿뿹��");
//					new MemberList(main).list();
				}else if(btnMyPage == obj) {
					// ȸ�� ���� â���� �ѱ��!
					JOptionPane.showMessageDialog(null, "ȸ������");
					new HospitalMyPage(main).MyPage();
//					new MemberSearch(main).search();
//				}else if(btnUpdate == obj) {
//					//update();
////					new MemberUpdate(main).update();
//				}else if(btnDelete == obj) {
//					//delete();
////					new MemberDelete(main).delete();
//				}
				}else if(btnExit == obj) {
					String msg = "���α׷��� �����Ͻðڽ��ϱ�?";
					int result = JOptionPane.showConfirmDialog(null, getMsg(msg));
					if(result == 0)  {
						//DB close
//						system.dao.close();		//�θ� �޼ҵ� ��밡��
						System.exit(0);
					}
				}
			}		
		}//event class	
}
