package project_hospital;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

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

public class HospitalManager {
	//Field
	JFrame jf;
	JPanel main_panel, button_panel;
	JPanel listPane, updatePane, deletePane, replyPane;
	JButton btnList, btnUpdate, btnDelete, btnReply;
	TextArea ta;
	HospitalMgmUI main;

	JTextField tid;  
	JPasswordField tpass;
	JFrame jf_login;
	JPanel jp_deleteSearch;
	JLabel jl_deleteSearchName;
	JTextField jt_deleteSearch;
	JButton jb_deleteButton;
	
	//수정
	TextField tf_update;
	int idx;
	ArrayList<TextField> tf_update_list; //수정메뉴 선택 시 새로운 값을 가져오기 위해 선언만 해 놓음
	String[] form_names = {"패스워드", "전화번호", "애견이름", "애견종류"};
	JButton update_search;
	JPanel update_bottom;
	TextField tf_update_last;
	
	//답변
	private JPanel receive_panel;
	private JPanel send_panel;
	ArrayList<UserVO> list, list2;
	DefaultTableModel model, model2;
	Object[] row, row2;
	JTable table, table2;
	private JPanel table_panel;
	
	String id;	//Login에서 가져온 id
	JLabel jl_status;
	private JPanel status_panel;
	
	public String sname;	//답장보낼 매니저 이름 구하기
	
	public static Font font = new Font("맑은 고딕", Font.BOLD, 12);

	//Constructor
	public HospitalManager(HospitalMgmUI main) {
		this.main = main;
		login();
		showManager();
	}
	
	//Method
	/** 로그인 성공 시 아이디 불러오기 **/
	public String callId(String uid) {
		if(uid != null) {
			this.id = uid;
			System.out.println("-----"+id);
		}
		return id;
	}
	
	/** 로그인 성공 시 매니저 이름 불러오기 **/
	public String callName(String uid) {
		if(uid != null) {
			sname = main.system.renameMan(id);
			this.sname = sname;
			System.out.println("-----"+sname);
		}
		return sname;
	}
	
	/** 매니저 로그인 후 화면 **/
	public void showManager() {
		Font font = new Font("맑은 고딕", Font.BOLD, 12);
		jf = new JFrame("관리자 화면");	
		main_panel = new JPanel();
	    button_panel = new JPanel();
	    listPane = new JPanel();
	    updatePane = new JPanel();
	    deletePane = new JPanel();
	    replyPane = new JPanel();
	    
	    update_bottom = new JPanel(new BorderLayout());
	    
	    button_panel = new JPanel(new GridLayout(4,1));
	    button_panel.setSize(40,100);
	    
	    status_panel = new JPanel(new BorderLayout());
	    status_panel.setBackground(Color.WHITE);
	    jl_status = new JLabel("-- " + id + "가 로그인 하였습니다 --");
	    jl_status.setFont(font);
	    jl_status.setBackground(Color.WHITE);
	    status_panel.add(jl_status, BorderLayout.WEST);
	    
	    //색깔
	    main_panel.setBackground(Color.WHITE);
	    button_panel.setBackground(Color.WHITE);
	    listPane.setBackground(Color.WHITE);
	    updatePane.setBackground(Color.WHITE);
	    deletePane.setBackground(Color.WHITE);
	    replyPane.setBackground(Color.WHITE);
	    update_bottom.setBackground(Color.WHITE);
	    button_panel.setBackground(Color.WHITE);
	    
	    //관리자 UI 버튼 정의
	    btnList = new JButton("회원 리스트");
	    btnUpdate = new JButton("회원정보 수정");
		btnDelete = new JButton("회원정보 삭제");
		btnReply = new JButton("답변하기");
		btnList.setFont(font);  btnUpdate.setFont(font);  btnDelete.setFont(font); btnReply.setFont(font);
		//색깔
		btnList.setBackground(HospitalMgmUI.c1);
		btnUpdate.setBackground(HospitalMgmUI.c2);
		btnDelete.setBackground(HospitalMgmUI.c3);
		btnReply.setBackground(HospitalMgmUI.c1);
		
		button_panel.add(btnList);
		button_panel.add(btnUpdate);
		button_panel.add(btnDelete);
		button_panel.add(btnReply);
		
		//메인 UI 창 위치 정의
	    jf.add(BorderLayout.WEST, button_panel);      
	    jf.add(BorderLayout.CENTER, main_panel); //- 원래는 메인 먼저 띄우기
	    jf.add(BorderLayout.SOUTH, status_panel);
	    
	    jf.setSize(600,500);
		jf.setVisible(false);
		
		//관리자 UI 버튼 이벤트 정의
		btnList.addActionListener(new JFrameObjectEvent(this));
		btnUpdate.addActionListener(new JFrameObjectEvent(this));
		btnDelete.addActionListener(new JFrameObjectEvent(this));
		btnReply.addActionListener(new JFrameObjectEvent(this));
		jf.addWindowListener(new JFrameObjectEvent(this));
		
	}
	
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
		
		//폰트
		title.setFont(font);  id.setFont(font);   pass.setFont(font);
		btnLogin.setFont(font);    btnReset.setFont(font);
		btnExit.setFont(font);
		//색깔
		jp_title.setBackground(Color.WHITE);
		jp_id.setBackground(Color.WHITE);
		jp_pass.setBackground(Color.WHITE);
		jp_button.setBackground(Color.WHITE);
		btnLogin.setBackground(HospitalMgmUI.c1);
		btnReset.setBackground(HospitalMgmUI.c2);
		btnExit.setBackground(HospitalMgmUI.c3);
		
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
		jf_login.setBackground(Color.WHITE);
		
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
			callId(uid);
			callName(uid);
			showManager();
			jf.setVisible(true);
				
		}else {
			//로그인 실패 : id 동일, pass 다름
			JOptionPane.showMessageDialog(null, "로그인 실패");
		}
	}	

			
	/** 회원 리스트 화면 **/
	public void showList() {
		//listPane
		JLabel jl_list = new JLabel("회원 리스트");
		jl_list.setFont(font);
		
		//셀 가운데 정렬할 모델 생성 
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);	
		
		//Member 테이블에서 데이터 가져오는 작업
		ArrayList<UserVO>memlist = main.system.memberSelectList();

		//JTable
		Object[] columns = {" ","ID","패스워드","전화번호","애견이름","애견종류"};
		DefaultTableModel model =new DefaultTableModel(columns,0);	
		JTable table= new JTable(model);
		table.setBackground(HospitalMgmUI.c1);
		Object[] row =new Object[6];  //Jtable에 추가되는 하나의 row 추가될 객체

		if(memlist.size() != 0) {
			model.setNumRows(0);
			
			for(UserVO vo : memlist) {
				if(vo != null) {
					row[0] = vo.getMno();
					row[1] = vo.getMid();
					row[2] = vo.getMpass();
					row[3] = vo.getMphone();
					row[4] = vo.getMname();
					row[5] = vo.getMkind();
				
					model.addRow(row);
				}
			}
			table.repaint();
		}
		
		model.fireTableDataChanged();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.getColumn(" ").setCellRenderer(dtcr);
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
	
	/** 수정 검색 처리 메소드 **/
	public void updateSearchProc() {
		String mid = tf_update.getText().trim();
		if(mid.equals("")) {
			//TextField에 데이터가 존재 X
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			tf_update.requestFocus();
		}else {
			//TextField에 데이터가 존재 O
			//검색 가능 --> sms 시스템에서 해당데이터 유/무 확인
			idx = main.system.searchMid(mid);
			if(idx != 0) {
				//sms에 해당 학번의 학생정보 가져오기
				UserVO vo = main.system.selectMemInfo(mid);
				//학생정보 존재하는 경우 > 수정 가능
				updateOKForm(vo);					
			}else {
				JOptionPane.showMessageDialog(null, "멤버 정보가 존재하지 않습니다");
			}
		}
	}
	
	/** 수정 처리 메소드 **/
	public void updateProc() {
		JOptionPane.showMessageDialog(null, "수정완료");
		
		ArrayList<String> dataList = new ArrayList<String>();
		for(TextField tf : tf_update_list) {
			dataList.add(tf.getText().trim());
		}
		
		UserVO vo = new UserVO();
		vo.setMid(dataList.get(0));
		vo.setMpass(dataList.get(1));
		vo.setMphone(dataList.get(2));
		vo.setMname(dataList.get(3));
		vo.setMkind(dataList.get(4));

		if(main.system.menUpdate(vo)) {
			//수정완료
			JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
			for(TextField tf : tf_update_list) {
				tf.setText("");
			}
			showList();
//			selectForm(); //조회화면 호출
		}else {
			//수정실패
			JOptionPane.showMessageDialog(null, "수정 실패");
		}
		
	}
	
	/** 멤버 수정 화면 **/
	public void updateForm() {
		tf_update_list = new ArrayList<TextField>();
		
		//1. 학번 검색 폼 생성 및 검색버튼의 이벤트 추가
		Panel update_top = new Panel(new BorderLayout());
		Panel search_panel = new Panel();
		
		Label label = new Label("수정할 멤버 ID>");
		tf_update = new TextField(20);
		update_search = new JButton("검색"); //Object의 주소로 ActionPerformed
		update_search.setBackground(HospitalMgmUI.c2);
		
		tf_update_list.add(tf_update); //수정을 진행할 학번
		
		search_panel.add(label);
		search_panel.add(tf_update);
		search_panel.add(update_search);
		
		update_top.add(BorderLayout.CENTER, search_panel);
		
		updatePane.setLayout(new BorderLayout());
		updatePane.add(BorderLayout.NORTH,update_top);		
		jf.add(updatePane);		
		jf.setVisible(true);
		
		tf_update.addActionListener(new JFrameObjectEvent());
		update_search.addActionListener(new JFrameObjectEvent());
	}
	
	/** 수정 데이터 등록 폼 : 패스워드, 전화번호, 애견이름, 애견종류 **/
	public void updateOKForm(UserVO vo) {
		update_bottom.removeAll();
		
		Panel label_panel = new Panel(new GridLayout(4,1));
		Panel tf_panel = new Panel(new GridLayout(4,1));
		Panel btn_panel = new Panel();
		Button btn_update = new Button("수정완료");
		Button btn_reset = new Button("수정취소");
		btn_panel.add(btn_update);
		btn_panel.add(btn_reset);
		
		String[] data_list = new String[4];
		data_list[0] = vo.getMpass();
		data_list[1] = vo.getMphone();
		data_list[2] = vo.getMname();
		data_list[3] = vo.getMkind();
		
		for(int i=0; i<form_names.length; i++) {
			Panel p1 = new Panel();
			Panel p2 = new Panel();
			Label label = new Label(form_names[i]);
			TextField tf = new TextField(20);
			tf.setText(data_list[i]);
			p1.add(label); 	p2.add(tf);
			label_panel.add(p1);
			tf_panel.add(p2);
			
			tf_update_list.add(tf);
		}
		
		update_bottom.add(BorderLayout.NORTH, new Label());
		update_bottom.add(BorderLayout.WEST, label_panel);
		update_bottom.add(BorderLayout.CENTER, tf_panel);
		update_bottom.add(BorderLayout.SOUTH, btn_panel);
		
		updatePane.add(BorderLayout.CENTER, update_bottom);
		jf.add(updatePane);
		jf.setVisible(true);
		
		btn_update.addActionListener(new JFrameObjectEvent());
		btn_reset.addActionListener(new JFrameObjectEvent());
		tf_update_last = tf_update_list.get(tf_update_list.size()-1);
		tf_update_last.addActionListener(new JFrameObjectEvent());
		
	}
	
	/** 수정실패 **/
	public void updateFailForm() {
		update_bottom.removeAll();
		update_bottom.add(BorderLayout.NORTH, new Label());
		update_bottom.add(BorderLayout.CENTER, new Label("-- 멤버 정보가 존재하지 않습니다 --"));
		
		updatePane.add(update_bottom, BorderLayout.CENTER);
		jf.add(BorderLayout.CENTER, update_bottom);
		jf.setVisible(true);
	}

	/** 삭제 화면 **/
	public void deleteMember() {
		//deletePane
		jp_deleteSearch = new JPanel();
		jp_deleteSearch.setBackground(Color.WHITE);
		jl_deleteSearchName = new JLabel("삭제할 멤버 ID >");
		jt_deleteSearch = new JTextField(20);
		jb_deleteButton = new JButton("확인");
		jb_deleteButton.setBackground(HospitalMgmUI.c3);
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
	
	/** 답변하기 **/
	public void replyPost() {
		//replyPane
		JButton btn_reset = new JButton("새로고침");
		btn_reset.setFont(font);
		btn_reset.setBackground(main.c3);
		JPanel reset_panel = new JPanel();
		reset_panel.setSize(20, 20);
		reset_panel.add(btn_reset);
		reset_panel.setBackground(Color.WHITE);
		
		//1.답변 받은 패널
		receive_panel = new JPanel(new BorderLayout());	
		receive_panel.setBackground(Color.WHITE);
		//라벨
		JLabel jl_list = new JLabel("답변할 게시글");
		jl_list.setFont(font);
		
		//테이블
		list = new ArrayList<UserVO>();
		Object[] columns = {"번호","게시글 코드","제목","글내용","멤버이름","날짜"};
		model =new DefaultTableModel(columns,0);
		table= new JTable(model);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		row =new Object[columns.length];
		
		list = main.system.getSendInfo(id);
		
		for(UserVO vo : list) {
			if(vo != null) {
				row[0]= vo.getRno();
				row[1]= vo.getBid();
				row[2]= vo.getBtitle();
				row[3]= vo.getBtext();
				row[4]= vo.getBmname();
				row[5]= vo.getBdate();
				
				model.addRow(row);
			}
			table.repaint();
		}
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = table.getSelectedRow();
                UserVO vo = new UserVO();
                vo = list.get(rowNum);
                HospitalManagerPopUp popup = new HospitalManagerPopUp(vo);
                popup.callId(id);
                popup.callName(sname);
            }
        });
	 
		model.fireTableDataChanged();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.getColumn("번호").setCellRenderer(dtcr);
		table.getColumn("게시글 코드").setCellRenderer(dtcr);
	    table.getColumn("제목").setCellRenderer(dtcr);
	    table.getColumn("글내용").setCellRenderer(dtcr);
	    table.getColumn("멤버이름").setCellRenderer(dtcr);
	    table.getColumn("날짜").setCellRenderer(dtcr);
	    
	    
	    JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0,100,600,250);
		
		receive_panel.add(jl_list, BorderLayout.NORTH);
		receive_panel.add(pane, BorderLayout.CENTER);
		
		//2.답변 완료 패널
		send_panel = new JPanel(new BorderLayout());
		//라벨
		JLabel jl_list2 = new JLabel("답변완료 게시글");
		jl_list2.setFont(font);
				
		//테이블
		list2 = new ArrayList<UserVO>();
		Object[] columns2 = {"번호","게시글 코드","제목","글내용","매니저이름","멤버이름","날짜"};
		model2 =new DefaultTableModel(columns,0);
		table2= new JTable(model2);
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);
				
		row2 =new Object[columns2.length];
				
		list2 = main.system.getReceiveInfo(id);
		
		for(UserVO vo : list2) {
			if(vo != null) {
				row2[0]= vo.getRno();
				row2[1]= vo.getAid();
				row2[2]= vo.getAtitle();
				row2[3]= vo.getAtext();
				row2[4]= vo.getAsname();
				row2[5]= vo.getAmname();
				row2[6]= vo.getAdate();
						
				model2.addRow(row2);
			}
			table2.repaint();
		}
		
		table2.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent e) {
				int rowNum = table2.getSelectedRow();
				UserVO vo = new UserVO();
				vo = list2.get(rowNum);
				new HospitalManagerComPopUp(vo);
			}
		});
			 
		model2.fireTableDataChanged();
		model2.setColumnIdentifiers(columns2);
		table2.setModel(model2);
				
		table2.getColumn("번호").setCellRenderer(dtcr2);
		table2.getColumn("게시글 코드").setCellRenderer(dtcr2);
	    table2.getColumn("제목").setCellRenderer(dtcr2);
	    table2.getColumn("글내용").setCellRenderer(dtcr2);
	    table2.getColumn("매니저이름").setCellRenderer(dtcr2);
	    table2.getColumn("멤버이름").setCellRenderer(dtcr2);
	    table2.getColumn("날짜").setCellRenderer(dtcr2);
			    
			    
	    JScrollPane pane2 = new JScrollPane(table2);
		pane2.setBounds(0,100,600,250);	
		
		send_panel.add(jl_list2, BorderLayout.NORTH);
		send_panel.add(pane2, BorderLayout.CENTER);
		send_panel.setBackground(Color.WHITE);
		
		table_panel = new JPanel(new GridLayout(2,1));
		table_panel.add(receive_panel);
		table_panel.add(send_panel);
		table_panel.setBackground(Color.WHITE);
		
		replyPane = new JPanel(new BorderLayout());
		replyPane.add(table_panel, BorderLayout.CENTER);
		replyPane.add(reset_panel, BorderLayout.SOUTH);
		replyPane.setBackground(Color.WHITE);
		
		btn_reset.addActionListener(new JFrameObjectEvent());
		replyPane.setSize(200,200);
		jf.add(replyPane, BorderLayout.CENTER);
		jf.setVisible(true);
	}
	
	
	/** 답변테이블 새로고침 **/
	public void view() {
		this.list = new ArrayList<UserVO>();
		
		list = main.system.getSendInfo(id);
		list2 = main.system.getReceiveInfo(id);
		
		this.model.setNumRows(0);
		this.model2.setNumRows(0);
		
		for(UserVO vo : list) {
			if(vo != null) {
				row[0] = vo.getRno();
				row[1] = vo.getBid();
				row[2] = vo.getBtitle();
				row[3] = vo.getBtext();
				row[4] = vo.getBmname();
				row[5] = vo.getBdate();

				this.model.addRow(row);
			}
			this.table.repaint();
		}
		
		for(UserVO vo : list2) {
			if(vo != null) {
				row2[0] = vo.getRno();
				row2[1] = vo.getAid();
				row2[2] = vo.getAtitle();
				row2[3] = vo.getAtext();
				row2[4] = vo.getAsname();
				row2[5] = vo.getAmname();
				row2[6] = vo.getAdate();
				
				this.model2.addRow(row2);
			}
			this.table2.repaint();
		}
		this.model.fireTableDataChanged();
		this.model2.fireTableDataChanged();
		
	}
	
	
	
	/** 패널 보여주기 삭제 **/
	public void resetPane() {
		main_panel.setVisible(false);
		listPane.setVisible(false);
		updatePane.setVisible(false);
		deletePane.setVisible(false);
		replyPane.setVisible(false);
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
			updateForm();
			updatePane.setVisible(true);
		}else if(menu.equals("회원정보 삭제")) {		
			deletePane.removeAll();
			deleteMember();
			deletePane.setVisible(true);
		}else if(menu.equals("답변하기")) {		
			replyPane.removeAll();
			replyPost();
			replyPane.setVisible(true);
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
				Object obj = e.getSource();
				
				if(bname.equals("회원 리스트")) {	
					//리스트 출력
					main.switchPane("회원 리스트");
				}else if(bname.equals("회원정보 수정")){
					// 정보 수정 출력
					main.switchPane("회원정보 수정");
				}else if(bname.equals("회원정보 삭제")){
					// 정보 삭제 출력
					main.switchPane("회원정보 삭제");
				}else if(bname.equals("답변하기")){
					// 답변 출력
					main.switchPane("답변하기");
				}else if(bname.equals("새로고침")) {
					view();
				}else if(bname.equals("로그인") || tpass  == e.getSource()) {	
					if(validationCheck()) 	loginCheck();				
				}else if(bname.equals("취소")){
					tid.setText("");
					tpass.setText("");
					jf_login.setVisible(true);
				}else if(obj == update_search || obj == tf_update) {
					updateSearchProc();
				}else if(bname.equals("수정완료") || obj == tf_update_last) {
					updateProc();
				}else if(bname.equals("수정취소")) {
					JOptionPane.showMessageDialog(null, "수정취소");
				}else if(bname.equals("종료")) {
					jf_login.setVisible(false);
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
	

	