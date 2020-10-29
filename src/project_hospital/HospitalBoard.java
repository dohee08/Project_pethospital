package project_hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import project_hospital.HospitalManager.JFrameObjectEvent;

public class HospitalBoard extends WindowAdapter implements ActionListener {
	JFrame jf,bif,bdf; 
	HospitalMgmUI HMui;
	UserVO vo;
	
	JPanel BoardPane, insert_panel;
	JLabel la_text;
	JTextField jtf_text;
	JButton btn_insert, btn_delete;
	JTextField title_tf;
	JTextArea post ;
	
	ArrayList<UserVO> list;
	
	public static Font font = new Font("나눔스퀘어_ac", Font.BOLD, 12);
	public static Font font1 = new Font("나눔스퀘어_ac", Font.BOLD, 16);
	
	public HospitalBoard() {}
	public HospitalBoard(HospitalMgmUI HMui) {
		this.HMui = HMui;
		this.BoardPane = HMui.BoardPane;
	}
	
	
	Object[] columns = {"번호","닉네임","내용","날짜"};
	DefaultTableModel model =new DefaultTableModel(columns,0);
	JTable table= new JTable(model);
	Object[] row =new Object[4];
	private JPanel jp_deleteSearch;
	private JTextField jt_deleteSearch;
	private JLabel jl_deleteSearchName;
	private JButton jb_deleteButton;
	private JPanel deletePane;
	
	public void HospitalBoard(){
		HMui.switchPane(HospitalMgmUI.BOARD);
		
		JLabel jl_list = new JLabel("게시판");
		jl_list.setFont(font);
		
//		la_text = new JLabel("내용 :");
		insert_panel = new JPanel();
		jtf_text = new JTextField(40);
		btn_insert = new JButton("글올리기");
		JButton btn_reset = new JButton("새로고침");
		btn_delete = new JButton("글삭제");
		
//		insert_panel.add(la_text);
//		insert_panel.add(jtf_text);
		insert_panel.add(btn_insert);
		insert_panel.add(btn_reset);
		insert_panel.add(btn_delete);
		
		list = new ArrayList<UserVO>();
		

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		
	   list = HMui.system.gettext();
		

		
		for(UserVO vo : list) {
			if(vo != null) {
				row[0]= vo.getRno();
				row[1]=vo.getPname();
				row[2]=vo.getPtext();
				row[3]=vo.getPdate();
				
				model.addRow(row);
			}
			table.repaint();
		}
		
		model.fireTableDataChanged();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.getColumn("번호").setCellRenderer(dtcr);
	    table.getColumn("닉네임").setCellRenderer(dtcr);
	    table.getColumn("내용").setCellRenderer(dtcr);
	    table.getColumn("날짜").setCellRenderer(dtcr);
	    
	    
	    JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0,100,600,250);
		
		pane.setBackground(Color.WHITE);
		insert_panel.setBackground(Color.WHITE);
		BoardPane.setLayout(new BorderLayout());
		BoardPane.add(pane,BorderLayout.CENTER);
		BoardPane.add(jl_list, BorderLayout.NORTH);
		BoardPane.add(insert_panel, BorderLayout.SOUTH);
		
		
		HMui.add(BoardPane,BorderLayout.CENTER);
		HMui.setVisible(true);
		
		
		btn_insert.addActionListener(this);
		btn_reset.addActionListener(this);
		btn_delete.addActionListener(this);
	}
	
	
	public void boardinsert() {
		 bif = new JFrame();
		JPanel content_panel = new JPanel(new BorderLayout());
		JPanel post_panel = new JPanel();
		JPanel btns_panel = new JPanel();
		JPanel top = new JPanel();
		
		JButton btn_insert = new JButton("등록하기");
		JButton btn_nop = new JButton("취소");
		btns_panel.add(btn_insert); btns_panel.add(btn_nop);
		
		JLabel title_la = new JLabel("제목");
		JLabel title_o = new JLabel(":");
		title_tf = new JTextField(30);
		top.add(title_la);  top.add(title_o); top.add(title_tf);
		
		post = new JTextArea(30,30);
		post_panel.add(post);
		
		content_panel.add(BorderLayout.NORTH,top);
		content_panel.add(BorderLayout.CENTER,post_panel);
		content_panel.add(BorderLayout.SOUTH,btns_panel);
		
		bif.add(content_panel);
		bif.setSize(500, 500);
		bif.setVisible(true);
	
		btn_insert.addActionListener(this);
		btn_nop.addActionListener(this);	
		
	}
	
	public void write() {
		
		Date time = new Date();
		
		UserVO vo = new UserVO();
		Random rd = new Random();
		
		String a = HMui.system.rename(HMui.id);
		vo.setPno("P_" + rd.nextInt(10000));
		vo.setPname(a);
		vo.setPtitle(title_tf.getText());
		vo.setPtext(post.getText());
		vo.setPdate(time.getYear()+"/"+time.getMonth()+"/"+time.getDay());
		vo.setMid(HMui.id);
		
		if(HMui.system.pinsert(vo)) {
			JOptionPane.showMessageDialog(null, "입력이 완료되었습니다.");
			
		}else {
			JOptionPane.showMessageDialog(null, "입력실패");
		}
		
	}
	
	public void view() {
		list = new ArrayList<UserVO>();
		
		list = HMui.system.gettext();
		
		model.setNumRows(0);
		
		for(UserVO vo : list) {
			if(vo != null) {
				row[0]= vo.getRno();
				row[1]=vo.getPname();
				row[2]=vo.getPtext();
				row[3]=vo.getPdate();
				
				
				model.addRow(row);
			}
			table.repaint();
		}
		model.fireTableDataChanged();
		
	}
	
	//삭제
	public void boarddelete() {
		bdf = new JFrame();
		jp_deleteSearch = new JPanel();
		jp_deleteSearch.setBackground(Color.WHITE);
		jl_deleteSearchName = new JLabel("삭제할 게시글 번호 >");
		jt_deleteSearch = new JTextField(20);
		jb_deleteButton = new JButton("확인");
		jb_deleteButton.setBackground(HospitalMgmUI.c3);
		jl_deleteSearchName.setFont(HMui.getFont());
		
		jp_deleteSearch.add(jl_deleteSearchName);
		jp_deleteSearch.add(jt_deleteSearch);
		jp_deleteSearch.add(jb_deleteButton);
		
		jt_deleteSearch.addActionListener(this);	
		jb_deleteButton.addActionListener(this);	
		
		bdf.setSize(400,300);
		bdf.add(jp_deleteSearch, BorderLayout.CENTER);
		bdf.setVisible(true);
	}
	
	/** 삭제할 아이디 체크 **/
	public boolean deleteDataCheck(String pno) {
		return HMui.system.deletePostSearch(pno);
	}
	
	/** 삭제 진행 **/
	public void deleteProc(String pno) {
		boolean result = HMui.system.deletePost(pno);
		if(result) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("삭제 완료~"));
		}else {
			JOptionPane.showMessageDialog(null, HMui.getMsg("삭제 실패~"));
		}
	}//deleteProc
	
	
	//윈도우 이벤트 처리
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand().trim();
		Object obj = e.getSource();
		
		if(name.equals("글올리기")) {
			boardinsert();
//			write();
		}else if(name.equals("새로고침")) {
			view();
		}else if(name.equals("등록하기")) {
			write();
		}else if(name.equals("취소")){
			bif.setVisible(false);
		}else if(name.equals("글삭제")) {
			boarddelete();
		}else if(!jt_deleteSearch.getText().equals("") || jb_deleteButton.equals("확인")) {
			String pno = jt_deleteSearch.getText().trim();
			
			if(deleteDataCheck(pno)) {
				int result = JOptionPane.showConfirmDialog(null,
						"정말로 삭제하시겠습니까?");				
				if(result == 0)  deleteProc(pno);
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
		
		
	}//action
	


}
