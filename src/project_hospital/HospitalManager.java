package project_hospital;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class HospitalManager {
	//Field
	JFrame jf;
	JPanel main_panel, button_panel;
	JPanel listPane, updatePane, deletePane;
	JButton btnList, btnDelete;
	TextArea ta;
	HospitalMgmUI main;

	JTextField tid;  
	JPasswordField tpass;
	JFrame jf_login;
	JPanel jp_deleteSearch;
	JLabel jl_deleteSearchName;
	JTextField jt_deleteSearch;
	JButton jb_deleteButton;
//	private JPanel jp_updateMid;
//	private JPanel jp_updateMpass;
//	private JPanel jp_updateMphone;
//	private JPanel jp_updateMname;
//	private JPanel jp_updateMkind;
//	private JLabel jl_updateMid;
//	private JLabel jl_updateMpass;
//	private JLabel jl_updateMkind;
//	private JLabel jl_updateMname;
//	private JLabel jl_updateMphone;
//	private JLabel jl_updateSearchName;
//	private JTextField jt_updateMpass;
//	private JTextField jt_updateMphone;
//	private JTextField jt_updateMname;
//	private JButton btnUpdate;
//	private JTextField jt_updateMkind;
//	private JButton btnUpdateReset;
//	private JTextField jt_updateSearch;
	
	public static Font font = new Font("맑은 고딕", Font.BOLD, 12);
	
	//Constructor
	public HospitalManager(HospitalMgmUI main) {
		this.main = main;
		login();
		
		Font font = new Font("맑은 고딕", Font.BOLD, 12);
		jf = new JFrame("관리자 화면");	
		
		main_panel = new JPanel();
	    button_panel = new JPanel();
	    listPane = new JPanel();
	    deletePane = new JPanel();
	    updatePane = new JPanel();
	    
	    button_panel = new JPanel(new GridLayout(2,1));
	    button_panel.setSize(50,100);
	    
	    //관리자 UI 버튼 정의
	    btnList = new JButton("회원 리스트");
		btnDelete = new JButton("회원정보 삭제");
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
	/**로그인 폼 **/
	public void login() {
		Font font = new Font("맑은 고딕", Font.BOLD, 12);
		jf_login = new JFrame("로그인");	
		JPanel jp_title = new JPanel();
		JPanel jp_id = new JPanel();
		JPanel jp_pass = new JPanel();
		JPanel jp_button = new JPanel();
		
		JLabel title = new JLabel("로그인 폼");		
		JLabel id = new JLabel("아이디");
		JLabel pass = new JLabel("패스워드");
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

	/** 회원 리스트 화면 **/
	public void showList() {
		//listPane
		JLabel jl_list = new JLabel("회원 리스트");
		
		//셀 가운데 정렬할 모델 생성 
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);	
		
		//Member 테이블에서 데이터 가져오는 작업
		ArrayList<UserVO>memlist = main.system.memberSelectList();

		//JTable
		Object[] columns = {"ID","패스워드","전화번호","애견이름","애견종류"};
		DefaultTableModel model =new DefaultTableModel(columns,0);	
		JTable table= new JTable(model);
		Object[] row =new Object[5];  //Jtable에 추가되는 하나의 row 추가될 객체

		if(memlist.size() != 0) {
			model.setNumRows(0);
			
			for(UserVO vo : memlist) {
				if(vo != null) {
					row[0] = vo.getMid();
					row[1] = vo.getMpass();
					row[2] = vo.getMphone();
					row[3] = vo.getMname();
					row[4] = vo.getMkind();
				
					model.addRow(row);
				}
			}
			table.repaint();
		}
		
		model.fireTableDataChanged();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
	    table.getColumn("ID").setCellRenderer(dtcr);
	    table.getColumn("패스워드").setCellRenderer(dtcr);
	    table.getColumn("전화번호").setCellRenderer(dtcr);
	    table.getColumn("애견이름").setCellRenderer(dtcr);
	    table.getColumn("애견종류").setCellRenderer(dtcr);
		
	    JScrollPane pane=new JScrollPane(table);
		pane.setBounds(0,100,600,250);
		listPane.setLayout(new BorderLayout());
		listPane.add(BorderLayout.NORTH, jl_list);
		listPane.add(BorderLayout.CENTER,pane);
		
		jf.add(BorderLayout.CENTER,listPane);
		jf.setVisible(true);
	}
	
	/**
	public void updateMember() {
		
		jp_updateMid = new JPanel();
		jp_updateMpass = new JPanel();
		jp_updateMphone = new JPanel();
		jp_updateMname = new JPanel();
		jp_updateMkind = new JPanel();
		
		jl_updateMid = new JLabel("ID");
		jl_updateMpass = new JLabel("패스워드");
		jl_updateMphone = new JLabel("전화번호");
		jl_updateMname = new JLabel("애견이름");
		jl_updateMkind = new JLabel("애견종류");
		
		jl_updateSearchName = new JLabel("수정 ID >");	
		jt_updateSearch = new JTextField(20);
		
		jt_updateMpass = new JTextField(20);
		jt_updateMphone = new JTextField(20);
		jt_updateMname = new JTextField(20);
		jt_updateMkind = new JTextField(20);
		
		btnUpdate = new JButton("수정완료");
		btnUpdateReset = new JButton("다시쓰기");
		
		jl_updateMpass.setFont(font);
		jl_updateMphone.setFont(font);
		jl_updateMname.setFont(font);
		jl_updateMkind.setFont(font);
		jl_updateSearchName.setFont(font);
		btnUpdate.setFont(font);
		btnUpdateReset.setFont(font);
		
		
		jp_updateSearch.add(jl_updateSearchName);
		jp_updateSearch.add(jt_updateSearch);
		jp_updateName.add(jl_updateName);
		jp_updateName.add(jt_updateName);
		jp_updateAddr.add(jl_updateAddr);
		jp_updateAddr.add(jt_updateAddr);
		jp_updateButton.add(btnUpdate);
		jp_updateButton.add(btnUpdateReset);
		
		updatePane.add(jp_updateSearch);
		updatePane.add(jp_updateName);
		updatePane.add(jp_updateAddr);
		updatePane.add(jp_updateButton);
		
		main.add(updatePane, BorderLayout.CENTER);
		main.setVisible(true);
		
		jt_updateSearch.addActionListener(new JFrameObjectEvent());
		btnUpdate.addActionListener(new JFrameObjectEvent());
		btnUpdateReset.addActionListener(new JFrameObjectEvent());
		
		
	}//update method
	
	//updateProc
	public void updateProc() {
		String name = jt_updateSearch.getText().trim();
		
		MemberVO rvo = main.system.search(name);
		
		if(rvo != null) {
			jt_updateName.setText(rvo.getName());
			jt_updateAddr.setText(rvo.getAddr());
		}else {
			JOptionPane.showMessageDialog(null,
					main.getMsg("수정할 데이터가 존재하지 않습니다."));
		}		
	}//updateProc
	
	//updateFormCheck
	public boolean updateFormCheck() {
		boolean result = false;
		
		if(jt_updateSearch.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					main.getMsg("수정명을 입력해주세요"));
			jt_updateSearch.requestFocus();
		}else if(jt_updateName.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					main.getMsg("이름을 입력해주세요"));
			jt_updateName.requestFocus();
		}else if(jt_updateAddr.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					main.getMsg("주소를 입력해주세요"));
			jt_updateAddr.requestFocus();
		}else {
			result = true;
		}
		
		return result;
	}//updateFormCheck
	
	//updateResult
	public void updateResult() {
		MemberVO vo = new MemberVO();
		vo.setUp_name(jt_updateSearch.getText().trim());
		vo.setName(jt_updateName.getText().trim());
		vo.setAddr(jt_updateAddr.getText().trim());
		
		boolean result = main.system.update(vo);
		if(result) {
			JOptionPane.showMessageDialog(null, "수정완료~");
		}else {
			JOptionPane.showMessageDialog(null, "수정실패~");
		}
		
//		String name = jt_updateSearch.getText().trim();
//		for(MemberVO vo : MemberMgmUI.list) {
//			if(vo.getName().equals(name)) {
//				vo.setName(jt_updateName.getText());
//				vo.setAddr(jt_updateAddr.getText());
//				JOptionPane.showMessageDialog(null,
//						main.getMsg("수정이 완료되었습니다."));
//			}
//		}	
	}//updateResult
	**/
	/** 삭제 화면 **/
	public void deleteMember() {
		//deletePane
		jp_deleteSearch = new JPanel();
		jl_deleteSearchName = new JLabel("삭제 ID >");
		jt_deleteSearch = new JTextField(20);
		jb_deleteButton = new JButton("확인");
		jl_deleteSearchName.setFont(main.getFont());
		
		jp_deleteSearch.add(jl_deleteSearchName);
		jp_deleteSearch.add(jt_deleteSearch);
		jp_deleteSearch.add(jb_deleteButton);
		deletePane.add(jp_deleteSearch);
		
		jt_deleteSearch.addActionListener(new JFrameObjectEvent());	
		jb_deleteButton.addActionListener(new JFrameObjectEvent());	
		
		deletePane.setSize(300,300);
		jf.add(deletePane, BorderLayout.CENTER);
		jf.setVisible(true);
	}
	
	/** 삭제할 아이디 체크 **/
	public boolean deleteDataCheck(String mid) {
		return main.system.deleteSearch(mid);
	}
	
	/** 삭제 진행 **/
	public void deleteProc(String mid) {
		boolean result = main.system.delete(mid);
		if(result) {
			JOptionPane.showMessageDialog(null, main.getMsg("삭제 완료~"));
		}else {
			JOptionPane.showMessageDialog(null, main.getMsg("삭제 실패~"));
		}
	}//deleteProc
	
	/** 패널 보여주기 삭제 **/
	public void resetPane() {
		main_panel.setVisible(false);
		listPane.setVisible(false);
		updatePane.setVisible(false);
		deletePane.setVisible(false);
	}
	
	/** 메뉴 이동 제어 **/
	public void switchPane(String menu) {
		resetPane();			
		if(menu.equals("회원 리스트")) {
			listPane.removeAll();
			showList();
			listPane.setVisible(true);
		}else if(menu.equals("회원정보 수정")) {		
			updatePane.removeAll();
//			updateMember();
			updatePane.setVisible(true);
		}else if(menu.equals("회원정보 삭제")) {		
			deletePane.removeAll();
			deleteMember();
			deletePane.setVisible(true);
		}
	}
		
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
//		String did = "manager";  
//		String dpass = "123";
		String uid = tid.getText().trim();
		String upass = tpass.getText().trim();
		
		boolean result = main.system.manlogin(uid, upass);
		
		if(result) {
			//로그인성공 : id,pass 동일
			JOptionPane.showMessageDialog(null, "로그인 성공");
			jf_login.setVisible(false);
			jf.setVisible(true);
				
		}else {
			//로그인 실패 : id 동일, pass 다름
			JOptionPane.showMessageDialog(null, "로그인 실패");
		}
		
	}	

	/** 이벤트 처리 클래스 **/
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
				}else if(bname.equals("회원정보 삭제")){
					// 정보 삭제 출력
					main.switchPane("회원정보 삭제");
				}else if(bname.equals("로그인") || tpass  == e.getSource()) {	
					if(validationCheck()) 	loginCheck();				
				}else if(bname.equals("취소")){
					tid.setText("");
					tpass.setText("");
					jf_login.setVisible(true);
				}else if(bname.equals("종료")) {
					System.out.println("--  종료 버튼이 클릭되었습니다. --");
					System.exit(0);
				}else if(!jt_deleteSearch.getText().equals("") || jb_deleteButton.equals("확인")) {
					String mid = jt_deleteSearch.getText().trim();
					
					if(deleteDataCheck(mid)) {
						int result = JOptionPane.showConfirmDialog(null,
								"정말로 삭제하시겠습니까?");				
						if(result == 0)  deleteProc(mid);
					}else {
						//삭제할 데이터 없음
						JOptionPane.showMessageDialog(null,
								"삭제할 데이터가 존재하지 않습니다.");
					}
				}else {
					JOptionPane.showMessageDialog(null,
							"삭제명을 입력해주세요");
					jt_deleteSearch.requestFocus();
				}
			}
		}
		

			
}
	

	