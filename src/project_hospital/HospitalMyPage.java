package project_hospital;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class HospitalMyPage extends WindowAdapter implements ActionListener{
	//Field
		HospitalMgmUI main;
		JPanel MyPagePane;
		JPanel mypage_menu_panel;
		JPanel jp_change,content_panel,check_panel,search_panel,update_panel,update_bottom
				,hselect_panel,login_panel,all_panel,sselect_panel,allselect_panel,intro_panel;
		JTextField  jt_kind, jt_delete,tf_id;
		TextField tf_update,tf_up_last,hyear,hmonth,hday,mkind,mname,syear,smonth,sday,stime;
		JComboBox jc_visit_time;
		JPasswordField tf_pass;
		JButton  view, update, change;
		Button update_search;
		String[] form_names = {"아이디","비밀번호","전화번호","동물이름","종류"};
		String[] up_names = {"예약번호","이름","년","월","일","예약시간"};
		ArrayList<TextField> tf_change_list;
		ArrayList<TextField> tf_update_list;
		int idx = -1;
		
		 String[] visit_times = {"선택해주세요","09:00", "10:00","11:00","12:00","13:00", 
				 "14:00","15:00","16:00", "17:00", "18:00"};
		 
		
		public HospitalMyPage() {}
		public HospitalMyPage(HospitalMgmUI main) {
			this.main = main;
			this.MyPagePane = main.MyPagePane;
			this.mypage_menu_panel = main.mypage_menu_panel;
		}
		
		//JTable
		Object[] columns = {"예약번호","이름","년","월","일","시간"};
		DefaultTableModel model =new DefaultTableModel(columns,0);
		JTable table= new JTable(model);
		Object[] row =new Object[6];  //Jtable에 추가되는 하나의 row 추가될 객체
		
		
		//JTable
		Object[] scolumns = {"예약번호","이름","년","월","일","시간"};
		DefaultTableModel smodel =new DefaultTableModel(scolumns,0);
		JTable stable= new JTable(model);
		Object[] srow =new Object[6];  //Jtable에 추가되는 하나의 row 추가될 객체
		
		public static final int SELECT = 1;
		public static final int UPDATE = 2;
		public static final int CHECK = 3;
		
		//Constructor
		public void MyPage(){
			main.switchPane(HospitalMgmUI.MYPAGE);
			
			
			jp_change = new JPanel();
			content_panel = new JPanel();
			check_panel = new JPanel();
			update_panel = new JPanel();
			search_panel = new JPanel();
			hselect_panel = new JPanel();
			sselect_panel = new JPanel();
			allselect_panel = new JPanel();
			intro_panel = new JPanel();
			update_bottom = new JPanel();
			login_panel = new JPanel(new BorderLayout());
			all_panel = new JPanel(new BorderLayout());
			
			jp_change.setBackground(Color.white);
			content_panel.setBackground(Color.white);
			check_panel.setBackground(Color.white);
			update_panel.setBackground(Color.white);
			search_panel.setBackground(Color.white);
			hselect_panel.setBackground(Color.white);
			sselect_panel.setBackground(Color.white);
			allselect_panel.setBackground(Color.white);
			intro_panel.setBackground(Color.white);
			update_panel.setBackground(Color.white);
			login_panel.setBackground(Color.white);
			all_panel.setBackground(Color.white);
			MyPagePane.setBackground(Color.white);
			
			Color c1 = new Color(255,231,159);
			Color c2 = new Color(238,217,154);
			Color c3 = new Color(229,197,148);
			
			JButton view = new JButton("예약내역");
			JButton update = new JButton("예약수정");
			JButton change = new JButton("내정보수정");
			
			view.setBackground(c1);
			update.setBackground(c2);
			change.setBackground(c3);
			
//			menu_panel.add(view);
//			menu_panel.add(update);
//			menu_panel.add(change);
			
			mypage_menu_panel.add(view);
			mypage_menu_panel.add(update);
			mypage_menu_panel.add(change);
			 
			MyPagePane.add(BorderLayout.CENTER,content_panel);
			
			
			main.add(MyPagePane);
			main.setVisible(true);
			
			view.addActionListener(this);
			update.addActionListener(this);
			change.addActionListener(this);
		}
		
		
		//Method
		/** 내정보 수정 창 */
		public void change(UserVO vo) {
			jp_change.removeAll();
			tf_change_list = new ArrayList<TextField>();
			
			
			
			String title = "--- 내 정보 수정하기 ---";
			JLabel title_label = new JLabel(title);
			JPanel label_panel = new JPanel(new GridLayout(5,1));
			JPanel tf_panel = new JPanel(new GridLayout(5,1));
			JPanel btn_panel = new JPanel();
			Button mem_insert = new Button("정보 수정");
			Button mem_reset = new Button("취소");
			Button mem_delete = new Button("내정보 삭제");
			btn_panel.add(mem_insert); btn_panel.add(mem_reset);
			btn_panel.add(mem_delete);
			
			title_label.setHorizontalAlignment(JLabel.CENTER);
			label_panel.setBackground(Color.white);
			tf_panel.setBackground(Color.white);
			btn_panel.setBackground(Color.white);
			
			String[] data_list = new String[5];
			data_list[0]= vo.getMid();
			data_list[1]= vo.getMpass();
			data_list[2]= vo.getMphone();
			data_list[3]= vo.getMname();
			data_list[4]= vo.getMkind();
			
			
			int i = 0;
			
			for(String name :form_names) {
				JPanel p1 = new JPanel(); //label
				JPanel p2 = new JPanel(); //tf
				JLabel label = new JLabel(name);
				TextField tf = new TextField(30);
				tf.setText(data_list[i]);
				
				p1.setBackground(Color.white);
				p2.setBackground(Color.white);
				
				if(i==0) {
					tf.setEditable(false);
				}
				
				p1.add(label); label_panel.add(p1);
				p2.add(tf);	   tf_panel.add(p2);
				
				tf_change_list.add(tf);
				i++;
					
			}
			
			jp_change.setLayout(new BorderLayout());
			jp_change.add(BorderLayout.NORTH,title_label);
			jp_change.add(BorderLayout.WEST,label_panel);
			jp_change.add(BorderLayout.CENTER,tf_panel);
			jp_change.add(BorderLayout.SOUTH,btn_panel);
			
			content_panel.add(jp_change);

			MyPagePane.add(content_panel);
			
			mem_insert.addActionListener(this);
			mem_reset.addActionListener(this);
			mem_delete.addActionListener(this);
			
		}
		
		
		/** 내정보 수정처리 메소드 
		 * */
		public void memberupdateProc() {
			ArrayList<String> dataList = new ArrayList<String>();
			
			for(TextField tf :  tf_change_list) {
				dataList.add(tf.getText().trim());
			}
			
			UserVO vo = new UserVO();
			
			vo.setMid(dataList.get(0));
			vo.setMpass(dataList.get(1));
			vo.setMphone(dataList.get(2));
			vo.setMname(dataList.get(3));
			vo.setMkind(dataList.get(4));
					
			
			if(main.system.memberupdate(vo)) {
				//수정 성공
				JOptionPane.showMessageDialog(null, "수정완료!");
			}else {
				JOptionPane.showMessageDialog(null, "수정실패");
			}
		} //수정처리
		
		
		/**
		 * 내정보 삭제
		 */
		public void memberDelete() {
			String mid = main.id;
			
			if(main.system.lastcheckh(mid)==0) {
				if(main.system.lastchecks(mid)==0) {
			if(main.system.delete(mid)) {
				JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
				System.exit(0);
			}else {
				JOptionPane.showMessageDialog(null, "삭제 실패");
			}
			}else {
				JOptionPane.showMessageDialog(null, "미용 예약 삭제를 먼저 해주세요");
			}}
			else {
				JOptionPane.showMessageDialog(null, "병원 예약 삭제를 먼저 해주세요");
			}
		}
		
		
		/** 예약정보 초기창 
		 * */
		public void introCheckList() {
			resetMenuPanel();
			switchPanel(SELECT);
			intro_panel.removeAll();
			
			
			
			Button h = new Button("병원예약확인");
			Button s = new Button("미용예약확인");
			
			intro_panel.add(h);
			intro_panel.add(s);
			
			content_panel.add(BorderLayout.NORTH,intro_panel);
			main.setVisible(true);
			
			h.addActionListener(this);
			s.addActionListener(this);
		}
		
			
		/** 병원 예약정보 보는창 */
		public void checklisth() {
			resetMenuPanel();
			switchPanel(SELECT);
			allselect_panel.removeAll();
			hselect_panel.removeAll();
			
			hcreateJtableData();	//출력되는 데이터 가져오기
			model.setColumnIdentifiers(columns);
			table.setModel(model);
			
			screateJtableData();	//출력되는 데이터 가져오기
			model.setColumnIdentifiers(columns);
			table.setModel(model);
			
			
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		    TableColumnModel tcm = table.getColumnModel();
		    
		    table.getColumn("예약번호").setCellRenderer(dtcr);
		    table.getColumn("이름").setCellRenderer(dtcr);
		    table.getColumn("년").setCellRenderer(dtcr);
		    table.getColumn("월").setCellRenderer(dtcr);
		    table.getColumn("일").setCellRenderer(dtcr);
		    table.getColumn("시간").setCellRenderer(dtcr);
			
		    JScrollPane pane=new JScrollPane(table);
			pane.setBounds(0,100,600,250);
			
			hselect_panel.setLayout(new BorderLayout());
			hselect_panel.add(BorderLayout.NORTH, new Label("병원예약조회"));
			hselect_panel.add(BorderLayout.CENTER,pane);
			allselect_panel.add(hselect_panel);
			content_panel.add(BorderLayout.CENTER,allselect_panel);
			main.setVisible(true);
			
			MyPagePane.add(content_panel);    
		}
		
		
		/** 미용예약정보 보는창 */
		public void checklists() {
			resetMenuPanel();
			switchPanel(SELECT);
			allselect_panel.removeAll();
			sselect_panel.removeAll();

			screateJtableData();	//출력되는 데이터 가져오기
			smodel.setColumnIdentifiers(scolumns);
			stable.setModel(smodel);
			
			
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		    TableColumnModel tcm = stable.getColumnModel();
		    
		    stable.getColumn("예약번호").setCellRenderer(dtcr);
		    stable.getColumn("이름").setCellRenderer(dtcr);
		    stable.getColumn("년").setCellRenderer(dtcr);
		    stable.getColumn("월").setCellRenderer(dtcr);
		    stable.getColumn("일").setCellRenderer(dtcr);
		    stable.getColumn("시간").setCellRenderer(dtcr);
			
		    JScrollPane pane=new JScrollPane(stable);
			pane.setBounds(0,100,200,250);
			
			sselect_panel.setLayout(new BorderLayout());
			sselect_panel.add(BorderLayout.NORTH, new Label("미용예약조회"));
			sselect_panel.add(BorderLayout.CENTER,pane);
			allselect_panel.add(sselect_panel);
			allselect_panel.setVisible(true);
			content_panel.add(BorderLayout.CENTER,allselect_panel);
			
			MyPagePane.add(content_panel);    
		}
		
		
		/** 예약정보 수정창 */
		public void update() {
			tf_update_list = new ArrayList<TextField>();
			resetMenuPanel();
			switchPanel(UPDATE);
			update_panel.removeAll();
			
			//학번 검색 폼생성 및 검색버튼에 이벤트 추가
			JPanel update_top = new JPanel(new BorderLayout());
			JPanel search_panel = new JPanel();
			String title = "수정할 예약번호를 입력해주세요";
			JLabel title_label = new JLabel(title);
			Label label = new Label("예약번호");
			tf_update = new TextField(20);
			update_search = new Button("검색");
			
			title_label.setHorizontalAlignment(JLabel.CENTER);
			update_top.setBackground(Color.white);
			search_panel.setBackground(Color.white);
			
			tf_update_list.add(tf_update);
			
			search_panel.add(label);
			search_panel.add(tf_update);
			search_panel.add(update_search);
			
			update_top.add(BorderLayout.NORTH,title_label);
			update_top.add(BorderLayout.CENTER,search_panel);
			
			update_panel.setLayout(new BorderLayout());
			update_panel.add(BorderLayout.NORTH,update_top);
			content_panel.add(update_panel);
			
			MyPagePane.add(content_panel);
			main.setVisible(true);
			
			tf_update.addActionListener(this);
			update_search.addActionListener(this);
			
		}
		
		
		/** 병원수정 데이터 등록 폼 */
		public void hupdateOKForm(UserVO vo) {
			update_bottom.removeAll();
			
			Panel update_bottom = new Panel(new BorderLayout());
			Panel label_panel = new Panel(new GridLayout(6,1));
			Panel tf_panel = new Panel(new GridLayout(6,1));
			Panel btn_panel = new Panel();
			Button btn_hupdate = new Button("병원예약수정");
			Button btn_reset = new Button("수정취소");
			Button btn_hdelete = new Button("병원예약삭제");
			btn_panel.add(btn_hupdate);
			btn_panel.add(btn_reset);
			btn_panel.add(btn_hdelete);
			
			
			update_bottom.setBackground(Color.white);
			label_panel.setBackground(Color.white);
			tf_panel.setBackground(Color.white);
			btn_panel.setBackground(Color.white);
			
			jc_visit_time = new JComboBox(visit_times);
			
			String[] data_list = new String[7];
	
			data_list[0]=vo.getMname();
			data_list[1]=vo.getHyear();
			data_list[2]=vo.getHmonth();
			data_list[3]=vo.getHday();

			Panel date = new Panel();
			

			
			Label lname = new Label("이름");
			mname = new TextField(20);
			mname.setText(data_list[0]);
			mname.setEditable(false);
			
			Label ldate = new Label("예약날짜") ;
			
			Label lyear = new Label("년");
			hyear = new TextField(5);
			hyear.setText(data_list[1]);
			
			Label lmonth = new Label("월");
			hmonth = new TextField(5);
			hmonth.setText(data_list[2]);
			
			Label lday = new Label("일");
			hday = new TextField(5);
			hday.setText(data_list[3]);
			
			date.add(hyear); date.add(lyear);
			date.add(hmonth); date.add(lmonth); 
			date.add(hday);date.add(lday);
			
			
			Label ltime = new Label("예약시간");
			
			
			label_panel.add(new Label("병원예약입니다"));
			label_panel.add(lname);	
			label_panel.add(ldate);
			label_panel.add(ltime);
			label_panel.add(new Label());
			label_panel.add(new Label());
	
			
			tf_panel.add(new Label());
			tf_panel.add(mname);
			tf_panel.add(date);
			tf_panel.add(jc_visit_time);
			tf_panel.add(new Label());
			tf_panel.add(new Label());

			
			update_bottom.add(BorderLayout.NORTH, new Label());
			update_bottom.add(BorderLayout.WEST, label_panel);
			update_bottom.add(BorderLayout.CENTER, tf_panel);
			update_bottom.add(BorderLayout.SOUTH, btn_panel);
			
			update_panel.add(BorderLayout.CENTER, update_bottom);
			content_panel.add(update_panel);
			content_panel.setVisible(true);
			MyPagePane.add(content_panel);
			
			jc_visit_time.addActionListener(this);
			btn_hupdate.addActionListener(this);
			btn_reset.addActionListener(this);
			btn_hdelete.addActionListener(this);
			
		}
		
		/** 미용수정 데이터 등록 폼 */
		public void supdateOKForm(UserVO vo) {
			update_bottom.removeAll();
			
			Panel update_bottom = new Panel(new BorderLayout());
			Panel label_panel = new Panel(new GridLayout(6,1));
			Panel tf_panel = new Panel(new GridLayout(6,1));
			Panel btn_panel = new Panel();
			Button btn_supdate = new Button("미용예약수정");
			Button btn_reset = new Button("수정취소");
			Button btn_sdelete = new Button("미용예약삭제");
			btn_panel.add(btn_supdate);
			btn_panel.add(btn_reset);
			btn_panel.add(btn_sdelete);
			

			update_bottom.setBackground(Color.white);
			label_panel.setBackground(Color.white);
			tf_panel.setBackground(Color.white);
			btn_panel.setBackground(Color.white);
			
			jc_visit_time = new JComboBox(visit_times);
			
			String[] data_list = new String[7];
	
			data_list[0]=vo.getMname();
			data_list[1]=vo.getSyear();
			data_list[2]=vo.getSmonth();
			data_list[3]=vo.getSday();

			Panel date = new Panel();
			
			
			
			Label lname = new Label("이름");
			mname = new TextField(20);
			mname.setText(data_list[0]);
			mname.setEditable(false);
			
			Label ldate = new Label("예약날짜") ;
			
			Label lyear = new Label("년");
			syear = new TextField(5);
			syear.setText(data_list[1]);
			
			Label lmonth = new Label("월");
			smonth = new TextField(5);
			smonth.setText(data_list[2]);
			
			Label lday = new Label("일");
			sday = new TextField(5);
			sday.setText(data_list[3]);
			
			date.add(syear); date.add(lyear);
			date.add(smonth); date.add(lmonth); 
			date.add(sday);date.add(lday);
			
			
			Label ltime = new Label("예약시간");
			
			label_panel.add(new Label("미용예약입니다"));
			label_panel.add(lname);	
			label_panel.add(ldate);
			label_panel.add(ltime);
			label_panel.add(new Label());
			label_panel.add(new Label());
			
			
			tf_panel.add(new Label());
			tf_panel.add(mname);
			tf_panel.add(date);
			tf_panel.add(jc_visit_time);
			tf_panel.add(new Label());
			tf_panel.add(new Label());
			
			update_bottom.add(BorderLayout.NORTH, new Label());
			update_bottom.add(BorderLayout.WEST, label_panel);
			update_bottom.add(BorderLayout.CENTER, tf_panel);
			update_bottom.add(BorderLayout.SOUTH, btn_panel);
			
			update_panel.add(BorderLayout.CENTER, update_bottom);
			content_panel.add(update_panel);
			main.setVisible(true);
			MyPagePane.add(content_panel);
			
			jc_visit_time.addActionListener(this);
			btn_supdate.addActionListener(this);
			btn_reset.addActionListener(this);
			btn_sdelete.addActionListener(this);
			
		}
		
		
		/** 수정 검색처리 */
		public void updateSearchProc() {
			update_bottom.removeAll();
			String rno = tf_update.getText().trim();
			if(rno.equals("")) {
				JOptionPane.showMessageDialog(null, "예약번호를 입력해주세요");
			}else {
				if(rno.indexOf("S")>=0) {
					idx = main.system.ssearchsno(rno);
					if(idx != 0) {
						UserVO vo = main.system.sselectUser(rno);
						supdateOKForm(vo);						
					}
					else {
						updateFailForm();
					}
				}else if(rno.indexOf("H")>=0) {
					idx =main.system.hsearchhno(rno);
					if(idx != 0) {
						UserVO vo = main.system.hselectUser(rno);
						hupdateOKForm(vo);						
					}else {
						updateFailForm();
					
					}
				}else {
					updateFailForm();
				}
			}
		}
		

		/** 병원수정처리 메소드 */
		public void hupdateProc(String time) {
			ArrayList<String> dataList = new ArrayList<String>();
			
			dataList.add(tf_update.getText());
			dataList.add(hyear.getText());
			dataList.add(hmonth.getText());
			dataList.add(hday.getText());
			dataList.add(time);
			
			UserVO vo = new UserVO();
			
						
			vo.setHno(dataList.get(0));
			vo.setHyear(dataList.get(1));
			vo.setHmonth(dataList.get(2));
			vo.setHday(dataList.get(3));
			vo.setHtime(dataList.get(4));
			
			if(main.system.hcheck(vo)==0) {
			if(main.system.update(vo)) {
				//수정 성공
				JOptionPane.showMessageDialog(null, "수정완료!");
			}else {
				JOptionPane.showMessageDialog(null, "수정실패");
			}}else {
				JOptionPane.showMessageDialog(null, "예약시간을 다시 선택해주세요.");
			}
		} //수정처리
	
		/** 미용수정처리 메소드 */
		public void supdateProc(String time) {
			ArrayList<String> dataList = new ArrayList<String>();
			
			dataList.add(tf_update.getText());
			dataList.add(syear.getText());
			dataList.add(smonth.getText());
			dataList.add(sday.getText());
			dataList.add(time);
			
			UserVO vo = new UserVO();
			
						
			vo.setSno(dataList.get(0));
			vo.setSyear(dataList.get(1));
			vo.setSmonth(dataList.get(2));
			vo.setSday(dataList.get(3));
			vo.setStime(dataList.get(4));
			
			if(main.system.scheck(vo)==0) {
			if(main.system.supdate(vo)) {
				//수정 성공
				JOptionPane.showMessageDialog(null, "수정완료!");
			}else {
				JOptionPane.showMessageDialog(null, "수정실패");
			}}else {
				JOptionPane.showMessageDialog(null, "예약시간을 다시 선택해주세요.");
			}
		} //수정처리
		
		/** 미용예약 삭제 
		 * */
		public void sdelete() {
			String sno = tf_update.getText();
			if(main.system.sdelete(sno)) {
				JOptionPane.showMessageDialog(null, "미용예약이 취소되었습니다.");
			}else {
				JOptionPane.showMessageDialog(null, "취소실패");
			}
			
		}
		
		/** 병원예약 삭제 
		 * */
		public void hdelete() {
			String hno = tf_update.getText();
			if(main.system.hdelete(hno)) {
				JOptionPane.showMessageDialog(null, "병원예약이 취소되었습니다.");
			}else {
				JOptionPane.showMessageDialog(null, "취소실패");
			}
			
		}
		
		
		/** 예약정보가 없을때 */
		public void updateFailForm() {
			update_bottom.removeAll();
			update_bottom.add(BorderLayout.NORTH, new Label());
			update_bottom.add(BorderLayout.CENTER, new Label("-- 예약 정보가 존재하지 않습니다 --"));
			update_bottom.setBackground(Color.white);
			
			update_panel.add(BorderLayout.CENTER, update_bottom);
			content_panel.add(update_panel);
			MyPagePane.add(content_panel);
		}	
		
		/** 메뉴 선택시 패널 스위칭  */
		public void switchPanel(int menu) {
			resetMenuPanel();
			// 1번 예약확인 2번 예약변경및 수정 3번 내정보 수정
			switch(menu) {
			case 1:
				intro_panel.setVisible(true);  break;
			case 2:
				update_panel.setVisible(true); break;
			case 3:
				login_panel.setVisible(true); break;
				//jp_change.setVisible(true); break;
			}
		}
		
		/**
		 * 모든 메뉴의 패널들을 비활성
		 */
		public void resetMenuPanel() {
			intro_panel.setVisible(false);
			allselect_panel.setVisible(false);
			update_panel.setVisible(false);
			jp_change.setVisible(false);
			login_panel.setVisible(false);
		}
		
		
		
		/** 예약 목록에서 출력될 병원데이터 가져오기 */
		public void hcreateJtableData() {
			
			ArrayList<UserVO> list = main.system.hselectList(main.id);
			
			model.setNumRows(0);
			for(UserVO vo : list) {
				if(vo != null) {
					row[0]=vo.getHno();
					row[1]=vo.getMname();
					row[2]=vo.getHyear();
					row[3]=vo.getHmonth();
					row[4]=vo.getHday();
					row[5]=vo.getHtime();
					model.addRow(row);
				}
				table.repaint();
			}
			model.fireTableDataChanged();
		}
		
		/** 예약 목록에서 출력될 미용데이터 가져오기 */
		public void screateJtableData() {
			
			ArrayList<UserVO> list = main.system.sselectList(main.id);
			
			smodel.setNumRows(0);
			for(UserVO vo : list) {
				if(vo != null) {
					srow[0]=vo.getSno();
					srow[1]=vo.getMname();
					srow[2]=vo.getSyear();
					srow[3]=vo.getSmonth();
					srow[4]=vo.getSday();
					srow[5]=vo.getStime();
					
					smodel.addRow(srow);
				}
				stable.repaint();
			}
			smodel.fireTableDataChanged();
		}
		
		/** 내정보 들어가기전에 로그인 창 */
		public void logincheck() {
			
		//	resetMenuPanel();
			switchPanel(CHECK);
			login_panel.removeAll();
			
			
			Panel ldpa_panel = new Panel();
			Label label_id = new Label("아이디");
			Label label_pass = new Label("비밀번호");
			tf_id = new JTextField(9);
			tf_pass = new JPasswordField(9);
			Button btn_login = new Button("로그인");
			
			ldpa_panel.setBackground(Color.white);
			
			ldpa_panel.add(label_id);
			ldpa_panel.add(tf_id);
			ldpa_panel.add(new Label());
			ldpa_panel.add(label_pass);
			ldpa_panel.add(tf_pass);
			ldpa_panel.add(new Label());
			ldpa_panel.add(btn_login);
			
			login_panel.add(BorderLayout.CENTER,ldpa_panel);
			
			content_panel.add(login_panel);
			
			
			MyPagePane.add(content_panel);
			main.setVisible(true);
			
			btn_login.addActionListener(this);
			tf_pass.addActionListener(this);
			
		}
		
		/** 로그인 처리 */
		public void loginProc() {
			String id = tf_id.getText().trim();
			String pass = tf_pass.getText().trim();
			

			if(id.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
				tf_id.requestFocus();
			}else if(pass.equals("")) {
				JOptionPane.showMessageDialog(null, "패스워드를 입력해주세요");
				tf_pass.requestFocus();
			}else {
				 boolean result = main.system.login(id, pass);
				
				if(result) {
					UserVO vo = main.system.selectMember(main.id);
					JOptionPane.showMessageDialog(null, "로그인 성공!");
					login_panel.setVisible(false);
					jp_change.setVisible(true);
					change(vo);
				}else {
					JOptionPane.showMessageDialog(null, "아이디 또는 패스워드가 일치하지 않습니다");
					tf_id.setText("");  tf_pass.setText("");
					tf_id.requestFocus();
				}
		}
		}
		//윈도우 이벤트 처리
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
				
				
				
		//액션 이벤트 처리
				public void actionPerformed(ActionEvent e) {
					String name = e.getActionCommand().trim();
					Object obj = e.getSource();
					
					if(name.equals("수정하기")) {
						JOptionPane.showMessageDialog(null, "수정완료");
					}else if(name.equals("취소")) {
						logincheck();
					}else if(name.equals("예약내역")) {
						introCheckList();
					}else if(name.equals("예약수정")) {
						update();
					}else if(name.equals("내정보수정")) {
						logincheck();
					}else if(obj == update_search || obj == tf_update) {
						updateSearchProc();			
					}else if(name.equals("병원예약수정") || obj == tf_up_last) {
						String time = jc_visit_time.getSelectedItem().toString();
						hupdateProc(time);
					}else if(name.equals("수정취소")) {
						update();
					}else if(name.equals("로그인")) {
						loginProc();
					}else if(name.equals("정보 수정")) {
						memberupdateProc();			
					}else if(name.equals("미용예약수정") || obj == tf_up_last) {
						String time = jc_visit_time.getSelectedItem().toString();
						supdateProc(time);
					}else if(name.equals("병원예약삭제")) {
						String msg = "정말로 예약을 삭제하시겠습니까?";
						int result = JOptionPane.showConfirmDialog(null,msg);
						if(result == 0)  {
							hdelete();
					}
				 }else if(name.equals("미용예약삭제")) {
						String msg = "정말로 예약을 삭제하시겠습니까?";
						int result = JOptionPane.showConfirmDialog(null,msg);
						if(result == 0)  {
							sdelete();
					}
				 }else if(name.equals("병원예약확인")) {
					 checklisth();
				 }else if(name.equals("미용예약확인")) {
					 checklists();
				 }
				 else if(name.equals("내정보 삭제")) {
					 String msg = "정말로 삭제하시겠습니까?";
						int result = JOptionPane.showConfirmDialog(null,msg);
						if(result == 0)  {
							memberDelete();
					}
				 }		
				}//end
}//class
