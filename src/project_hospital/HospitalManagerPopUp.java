package project_hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
	
	Font font = new Font("���� ���", Font.BOLD, 12);
	Font font2 = new Font("���� ���", Font.BOLD, 10);
	Color c1 = new Color(255,231,159);
	Color c2 = new Color(238,217,154);
	Color c3 = new Color(229,197,148);
	
	Border lineBorder;
	Border emptyBorder;
	
    public HospitalManagerPopUp(UserVO vo) {
    	this.vo = vo;
    	jf_post = new JFrame();
    	jf_reply = new JFrame();
    	jf_reply.setVisible(false);
    	ManagerPopUp();
    	replyForm();
    }
    
    /** �亯 ���� �������� **/
    public String callId(String sid) {
    	this.sid = sid;
    	return sid;
    }
    /** �亯 ���� �������� **/
    public String callName(String sname) {
    	this.bsname = sname;
    	return sname;
    }
    
    public void ManagerPopUp() {
        jf_post.setBounds(new Rectangle(600, 0, 450, 280));
        jf_post.setTitle("��� �Խñ�");
        jf_post.getContentPane().setLayout(null);
        
        lineBorder = BorderFactory.createLineBorder(Color.black, 1);
		emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		
        JLabel lblNewLabel = new JLabel("����");
        lblNewLabel.setFont(font);
        lblNewLabel.setBounds(12, 25, 57, 15);
        jf_post.getContentPane().add(lblNewLabel);
 
        b_title = new JTextField(vo.getBtitle());
        b_title.setBounds(81, 22, 340, 21);
        b_title.setEditable(false);
        b_title.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        b_title.setBackground(Color.WHITE);
        jf_post.getContentPane().add(b_title);
        b_title.setColumns(10);
 
        JLabel lblNewLabel_1 = new JLabel("�۳���");
        lblNewLabel_1.setFont(font);
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        jf_post.getContentPane().add(lblNewLabel_1);
 
        JTextArea textArea = new JTextArea(vo.getBtext());
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBounds(81, 53, 340, 69);
        textArea.setEditable(false);
        
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
		textArea.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        textArea.setBackground(Color.WHITE);
        jf_post.getContentPane().add(textArea);
 
        JLabel lblNewLabel_2 = new JLabel("�ۼ���");
        lblNewLabel_2.setFont(font);
        lblNewLabel_2.setBounds(12, 140, 57, 15);
        jf_post.getContentPane().add(lblNewLabel_2);
        
        Bmname = vo.getBmname();
        b_writer = new JTextField(Bmname);
        b_writer.setBounds(81, 137, 116, 21);
        b_writer.setEditable(false);
        b_writer.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        b_writer.setBackground(Color.WHITE);
        jf_post.getContentPane().add(b_writer);
        b_writer.setColumns(10);
 
        btnReply = new JButton("�����ϱ�");
        btnReply.setFont(font);
        btnReply.setBackground(c1);
        btnReply.setBounds(220, 180, 97, 23);
        
        btnClose = new JButton("�ݱ�");
        btnClose.setFont(font);
        btnClose.setBackground(c2);
        btnClose.setBounds(320, 180, 97, 23);
        
        jf_post.getContentPane().add(btnReply);
        jf_post.getContentPane().add(btnClose);
        jf_post.getContentPane().setBackground(Color.WHITE);
        
        btnReply.addActionListener(new JFrameObjectEvent());
        btnClose.addActionListener(new JFrameObjectEvent());
        jf_post.setVisible(true);
    }
    
    //���� â ����
    public void replyForm() {
    	jf_reply.setTitle("�亯 â");
    	JPanel content_panel = new JPanel(new BorderLayout());
    	JPanel label_panel = new JPanel();
		JPanel post_panel = new JPanel();
		JPanel btns_panel = new JPanel();
		JPanel top = new JPanel();
		
		content_panel.setBackground(Color.WHITE);
		label_panel.setBackground(Color.WHITE);
		post_panel.setBackground(Color.WHITE);
		btns_panel.setBackground(Color.WHITE);
		top.setBackground(Color.WHITE);
		
		bid = vo.getBid();
		label_panel.setLayout(new BoxLayout(label_panel, BoxLayout.Y_AXIS));
		JLabel b_id = new JLabel("������ �Խù� �ڵ� : " + bid);
		JLabel bmname = new JLabel("To : " + vo.getBmname());
		JLabel re_bsname = new JLabel("From : " + vo.getBsname());
		b_id.setFont(font2);  bmname.setFont(font2);  re_bsname.setFont(font2);
		label_panel.add(b_id);	label_panel.add(bmname);  label_panel.add(re_bsname);
		
		btn_insert = new JButton("�亯���");
		btn_nop = new JButton("�亯���");
		btn_insert.setFont(font);  btn_nop.setFont(font);
		btns_panel.add(btn_insert); btns_panel.add(btn_nop);
		btn_insert.setBackground(c2); btn_nop.setBackground(c3);
		
		JLabel title_la = new JLabel("����");
		title_la.setFont(font);
		JLabel title_o = new JLabel(":");
		title_tf = new JTextField(27);
		top.add(title_la);  top.add(title_o); top.add(title_tf);
		
		post = new JTextArea(21,30);
		post.setLineWrap(true);
		post.setWrapStyleWord(true);
		 
		Border lineBorder = BorderFactory.createLineBorder(Color.black, 1);
		Border emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		post.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		post_panel.add(post);
		
		JPanel tp_panel = new JPanel();
		tp_panel.setLayout(new BoxLayout(tp_panel, BoxLayout.Y_AXIS));
		tp_panel.add(top);  tp_panel.add(post_panel);
		
		content_panel.add(BorderLayout.WEST, label_panel);
		content_panel.add(BorderLayout.CENTER,tp_panel);
		content_panel.add(BorderLayout.SOUTH,btns_panel);
		content_panel.setBackground(Color.WHITE);
		
		btn_insert.addActionListener(new JFrameObjectEvent());
		btn_nop.addActionListener(new JFrameObjectEvent());	
		
		jf_reply.add(content_panel);
		jf_reply.setBackground(Color.WHITE);
		jf_reply.setSize(500, 500);
    }
    
    /** �亯 â ���� **/
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
			JOptionPane.showMessageDialog(null, "�Է��� �Ϸ�Ǿ����ϴ�.");
			
		}else {
			JOptionPane.showMessageDialog(null, "�Է½���");
		}
		
	}
    
    /** ������ ��ȿ�� üũ **/
	public boolean validationCheck() {
		boolean result = false;
		if(title_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "������ �Է����ּ���");
			title_tf.requestFocus();
		}else if(post.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "������ �Է����ּ���");
			post.requestFocus();
		}else {
			result = true;
		}
		
		return result;
	}
    
    /** �̺�Ʈ ó�� Ŭ���� **/
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
		//������ �̺�Ʈ ó��
		public void windowClosing(WindowEvent we) {
			//�亯â ����
			jf_post.setVisible(false);
			jf_reply.setVisible(false);
		}
		
		//�׼� �̺�Ʈ ó��
		public void actionPerformed(ActionEvent e) {
			String bname = e.getActionCommand();
			Object obj = e.getSource();
			
			if(btnReply == obj) {	
				// ���� â ����
				jf_reply.setVisible(true);
			}else if(btnClose == obj){
				// �Խñ� â �ݱ�
				jf_post.setVisible(false);
				jf_reply.setVisible(false);
			}else if(btn_insert == obj){
				// ��� �Ϸ� Ȯ��
				if(validationCheck()) {
					callId(sid);
					callName(bsname);
					write(sid, bsname);
					title_tf.setText("");
					post.setText("");
				}
			}else if(btn_nop == obj){
				// ���
				int result = JOptionPane.showConfirmDialog(null, "�亯�� ����ϰڽ��ϱ�?");
				if(result == 0)  {
					jf_reply.setVisible(false);
				}
			}
		}
	}
}
