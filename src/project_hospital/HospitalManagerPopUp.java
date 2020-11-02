package project_hospital;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HospitalManagerPopUp{
	JTextField b_title;
    JTextField b_writer;
    
    JFrame jf_post, jf_reply;
    JTextField title_tf;
	JTextArea post;
	private JButton btn_insert;
	private JButton btn_nop;
	private JButton btnReply;
	
	UserVO vo;
	private JButton btnClose;
	
	String Bmname, bid;
	String sid, bsname;
	
    public HospitalManagerPopUp(UserVO vo) {
    	this.vo = vo;
    	jf_post = new JFrame();
    	jf_reply = new JFrame();
    	jf_reply.setVisible(false);
    	ManagerPopUp();
    	replyForm();
    }
    
    public void ManagerPopUp() {
        jf_post.setBounds(new Rectangle(600, 0, 450, 280));
        jf_post.setTitle("멤버 게시글");
        jf_post.getContentPane().setLayout(null);
 
        JLabel lblNewLabel = new JLabel("제목");
        lblNewLabel.setBounds(12, 25, 57, 15);
        jf_post.getContentPane().add(lblNewLabel);
 
        b_title = new JTextField(vo.getBtitle());
        b_title.setBounds(81, 22, 340, 21);
        jf_post.getContentPane().add(b_title);
        b_title.setColumns(10);
 
        JLabel lblNewLabel_1 = new JLabel("글내용");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        jf_post.getContentPane().add(lblNewLabel_1);
 
        JTextArea textArea = new JTextArea(vo.getBtext());
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBounds(81, 53, 340, 69);
        jf_post.getContentPane().add(textArea);
 
        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 140, 57, 15);
        jf_post.getContentPane().add(lblNewLabel_2);
        
        Bmname = vo.getBmname();
        b_writer = new JTextField(Bmname);
        b_writer.setBounds(81, 137, 116, 21);
        jf_post.getContentPane().add(b_writer);
        b_writer.setColumns(10);
 
        btnReply = new JButton("답장하기");
        btnReply.setBounds(100, 180, 97, 23);
        
        btnClose = new JButton("닫기");
        btnClose.setBounds(299, 180, 97, 23);
        
        jf_post.getContentPane().add(btnReply);
        jf_post.getContentPane().add(btnClose);
        
        btnReply.addActionListener(new JFrameObjectEvent());
        btnClose.addActionListener(new JFrameObjectEvent());
        jf_post.setVisible(true);
    }
    
    //답장 창 띄우기
    public void replyForm() {
    	jf_reply.setTitle("답변 창");
    	JPanel content_panel = new JPanel(new BorderLayout());
    	JPanel label_panel = new JPanel();
		JPanel post_panel = new JPanel();
		JPanel btns_panel = new JPanel();
		JPanel top = new JPanel();
		
		bid = vo.getBid();
		JLabel b_id = new JLabel("답장할 게시물 코드 : " + bid);
		JLabel bmname = new JLabel("멤버이름 : " + vo.getBmname());
		label_panel.add(b_id);	label_panel.add(bmname);
		
		btn_insert = new JButton("등록하기");
		btn_nop = new JButton("취소");
		btns_panel.add(btn_insert); btns_panel.add(btn_nop);
		
		JLabel title_la = new JLabel("제목");
		JLabel title_o = new JLabel(":");
		title_tf = new JTextField(30);
		top.add(title_la);  top.add(title_o); top.add(title_tf);
		
		post = new JTextArea(30,30);
		post_panel.add(post);
		
		content_panel.add(BorderLayout.WEST, label_panel);
		content_panel.add(BorderLayout.NORTH,top);
		content_panel.add(BorderLayout.CENTER,post_panel);
		content_panel.add(BorderLayout.SOUTH,btns_panel);
		
		btn_insert.addActionListener(new JFrameObjectEvent());
		btn_nop.addActionListener(new JFrameObjectEvent());	
		
		jf_reply.add(content_panel);
		jf_reply.setSize(500, 500);
    }
    
    /** 답변 정보 가져오기 **/
    public String callId(String sid) {
    	this.sid = sid;
    	return sid;
    }
    /** 답변 정보 가져오기 **/
    public String callName(String sname) {
    	this.bsname = sname;
    	return sname;
    }
    
    /** 답변 창 쓰기 **/
    public void write(String sid, String sname) {
    	this.sid = sid;
    	this.bsname = sname;
    	
		Calendar cal = Calendar.getInstance();
	      SimpleDateFormat s1 = new SimpleDateFormat("yy/mm/dd");
	      int year = cal.get(Calendar.YEAR);

	      int month = cal.get(Calendar.MONTH)+1;

	      int day = cal.get(Calendar.DAY_OF_MONTH);

	      String date = s1.format(cal.getTime());
	      
		UserVO vo = new UserVO();
		Random rd = new Random();
		HospitalMgmSystem system = new HospitalMgmSystem();
		
		vo.setAid("A_" + rd.nextInt(10000));
		vo.setAtitle(title_tf.getText());
		vo.setAtext(post.getText());
		vo.setAdate(year+"/"+month+"/"+day);
		vo.setAmname(Bmname);
		vo.setAsname(system.renameMan(sid));
		vo.setMid(system.getMid(Bmname));
		vo.setSid(sid);
		vo.setBid(bid);
		
		if(system.ainsert(vo)) {
			JOptionPane.showMessageDialog(null, "입력이 완료되었습니다.");
			
		}else {
			JOptionPane.showMessageDialog(null, "입력실패");
		}
		
	}
    
    /** 이벤트 처리 클래스 **/
	class JFrameObjectEvent extends WindowAdapter
											implements ActionListener{
		//Field
		HospitalManagerPopUp main;
		
		//Constructor
		public JFrameObjectEvent() {
	
		}

		public JFrameObjectEvent(HospitalManagerPopUp main) {
			this.main = main;
		}	
		//윈도우 이벤트 처리
		public void windowClosing(WindowEvent we) {
			//답변창 종료
			jf_post.setVisible(false);
			jf_reply.setVisible(false);
		}
		
		//액션 이벤트 처리
		public void actionPerformed(ActionEvent e) {
			String bname = e.getActionCommand();
			Object obj = e.getSource();
			
			if(btnReply == obj) {	
				// 답장 창 띄우기
				jf_reply.setVisible(true);
			}else if(btnClose == obj){
				// 게시글 창 닫기
				jf_post.setVisible(false);
				jf_reply.setVisible(false);
			}else if(btn_insert == obj){
				// 등록 완료 확인
				callId(sid);
				callName(bsname);
				write(sid, bsname);
			}else if(btn_nop == obj){
				// 취소
				JOptionPane.showMessageDialog(null, "취소");
			}
		}
	}
}
