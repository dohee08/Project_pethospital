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

		public static Font font = new Font("���� ���", Font.BOLD, 12);
		
		//Constructor
		public HospitalMgmUI() {
			showMain();
			//start();
		}
		
		//Method
		public void showMain() {
			fmain = new JFrame("�ְߺ��� ���� �ý���");
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
		
		public void start() {	//���� UI ����!!!
			fmain.setVisible(false);
			showPane.setVisible(false);
			showButtonPane.setVisible(false);
			
		    main_panel = new JPanel();
		    button_panel = new JPanel();
		    insert_panel = new JPanel();	// --������ �α��� �Ͽ����ϴ�--
//		    String[] names = {"���� ����", "�̿� ����", "ȸ�� ����", "����"};
		      
		    insert_panel = new JPanel(new BorderLayout());
		    button_panel = new JPanel(new GridLayout(1,4));
		    button_panel.setSize(300,300);
		    
		    //���� UI ��ư ����
		    btnHospiRes = new JButton("���� ����");
			btnSalonRes = new JButton("�̿� ����");
			btnMyPage = new JButton("ȸ�� ����");
			btnExit = new JButton("����");
			
			button_panel.add(btnHospiRes);
			button_panel.add(btnSalonRes);
			button_panel.add(btnMyPage);
			button_panel.add(btnExit);
			
			//���� UI ��ư �̺�Ʈ ����
			HospitalMgmUIEvent eventObj = new HospitalMgmUIEvent(this);
			
			btnHospiRes.addActionListener(eventObj);
			btnSalonRes.addActionListener(eventObj);
			btnMyPage.addActionListener(eventObj);
			btnExit.addActionListener(eventObj);
			fmain.addWindowListener(eventObj);
		     
			//���� UI â ��ġ ����
		    fmain.add(BorderLayout.NORTH, button_panel);      
//		    f.add(BorderLayout.CENTER, main_panel); - ������ ���� ���� ����
		    fmain.add(BorderLayout.CENTER, insert_panel);
//		    insertForm();
		      
		    //���� UI â ȭ�� ����
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
//			jl_title = new JLabel("\n== �ְߺ��� �ý��ۿ� ���Ű��� ȯ���մϴ� ==");
//			jl_img = new JLabel(new ImageIcon("src/member/main.png"));
//			
//			btnReg = new JButton("ȸ�����");
//			btnList = new JButton("ȸ������Ʈ");
//			btnSearch = new JButton("ȸ���˻�");
//			btnUpdate = new JButton("ȸ������");
//			btnDelete = new JButton("ȸ������");
//			btnExit = new JButton("����");
//			
//			jl_title.setFont(new Font("���� ���",Font.BOLD,16));
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
		
		
		//�޴� �̵� ����
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
			if(menu.equals("���� ����")) {
				HospiResPane.removeAll();
				HospiResPane.setVisible(true);
			}else if(menu.equals("�̿� ����")) {		
				SalonResPane.removeAll();
				SalonResPane.setVisible(true);
			}else if(menu.equals("ȸ�� ����")) {		
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
				//JOptionPane.showMessageDialog(null,getMsg("���α׷� ����!!!"));
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
					JOptionPane.showMessageDialog(null, "��������");
//					new MemberRegister(main).register();
				}else if(btnSalonRes == obj) {
					// �̿뿹�� â���� �ѱ��!
					JOptionPane.showMessageDialog(null, "�̿뿹��");
//					new MemberList(main).list();
				}else if(btnMyPage == obj) {
					// ȸ�� ���� â���� �ѱ��!
					JOptionPane.showMessageDialog(null, "ȸ������");
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
