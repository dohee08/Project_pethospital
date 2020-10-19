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
		
		public static final int REGISTER = 1;
		public static final int LIST = 2;
		public static final int SEARCH = 3;
		public static final int UPDATE = 4;
		public static final int DELETE = 5;
		
		JPanel showPane, showButtonPane;
		JButton btnLogin, btnJoin;
		JPanel mainPane, menuPane;	
		JButton btnReg, btnList, btnSearch, btnUpdate, btnDelete,	btnExit;
		JLabel jl_title, jl_img;
		JFrame fmain;
		JPanel main_panel, button_panel, insert_panel;


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
			fmain = new JFrame("���� ���� ���� �ý���");
			showPane = new JPanel();
			showButtonPane = new JPanel();
			btnLogin = new JButton("�α���");
			btnJoin = new JButton("ȸ������");
			
			jl_title = new JLabel("\n== �ְߺ��� �ý��ۿ� ���Ű��� ȯ���մϴ� ==");
			jl_img = new JLabel(new ImageIcon("images/main.png"));
			jl_title.setFont(new Font("���� ���",Font.BOLD,16));
			btnLogin.setFont(font);
			btnJoin.setFont(font);
			
			showPane.add(jl_img);
			showPane.add(jl_title);	
			showButtonPane.add(btnLogin);
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
			btnJoin.addActionListener(new HospitalMgmUIEvent(this));
		}
		
		public void start() {	//���� UI ����!!!
			fmain.setVisible(false);
			showPane.setVisible(false);
			showButtonPane.setVisible(false);
			
		    main_panel = new JPanel();
		    button_panel = new JPanel();
		    insert_panel = new JPanel();
		    String[] names = {"���� ����","�̿� ����", "������"};
		      
		    main_panel = new JPanel();
		    insert_panel = new JPanel(new BorderLayout());
		    button_panel = new JPanel(new GridLayout(1,3));
		    button_panel.setSize(300,300);
		      
		      
		    for(String name: names) {
		      JButton button = new JButton(name);
		      button_panel.add(button);
//		       button.addActionListener(this);
		    }
		     
		    fmain.add(BorderLayout.NORTH, button_panel);      
//		    f.add(BorderLayout.CENTER, main_panel); - ������ ���� ���� ����
		    fmain.add(BorderLayout.CENTER, insert_panel);
//		    insertForm();
		      
		      
		    fmain.setSize(500,400);
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
			if(menu.equals("register")) {
				regPane.removeAll();
				regPane.setVisible(true);
			}else if(menu.equals("list")) {		
				listPane.removeAll();
				listPane.setVisible(true);
			}else if(menu.equals("search")) {		
				searchPane.removeAll();
				searchPane.setVisible(true);
			}else if(menu.equals("update")) {		
				updatePane.removeAll();
				updatePane.setVisible(true);
			}else if(menu.equals("delete")) {		
				deletePane.removeAll();
				deletePane.setVisible(true);
			}
		}
		
		public void switchPane(int menu) {
			resetPane();
			switch(menu) {
			case 1 : 
				regPane.removeAll();
				regPane.setVisible(true);
				break;
			case 2 : 
				listPane.removeAll();
				listPane.setVisible(true);
				break;
			case 3 : 
				searchPane.removeAll();
				searchPane.setVisible(true);
				break;
			case 4 : 
				updatePane.removeAll();
				updatePane.setVisible(true);
				break;
			case 5 : 
				deletePane.removeAll();
				deletePane.setVisible(true);
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
				}else if(btnReg == obj) {
//					new MemberRegister(main).register();
				}else if(btnList == obj) {
//					new MemberList(main).list();
				}else if(btnSearch == obj) {
//					new MemberSearch(main).search();
				}else if(btnUpdate == obj) {
					//update();
//					new MemberUpdate(main).update();
				}else if(btnDelete == obj) {
					//delete();
//					new MemberDelete(main).delete();
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
