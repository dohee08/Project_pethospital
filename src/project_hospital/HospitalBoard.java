package project_hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HospitalBoard extends WindowAdapter implements ActionListener {
	JFrame jf,bif; 
	HospitalMgmUI HMui;
	UserVO vo;
	
	JPanel BoardPane, insert_panel;
	JLabel la_text;
	JTextField jtf_text;
	JButton btn_insert;
	JTextField title_tf;
	JTextArea post ;
	
	ArrayList<UserVO> list;
	
	public static Font font = new Font("����������_ac", Font.BOLD, 12);
	public static Font font1 = new Font("����������_ac", Font.BOLD, 16);
	
	public HospitalBoard() {}
	public HospitalBoard(HospitalMgmUI HMui) {
		this.HMui = HMui;
		this.BoardPane = HMui.BoardPane;
	}
	
	
	Object[] columns = {"��ȣ","�ڵ�","�г���","����","��¥"};
	DefaultTableModel model =new DefaultTableModel(columns,0);
	JTable table= new JTable(model);
	Object[] row =new Object[5];
	
	public void HospitalBoard(){
		HMui.switchPane(HospitalMgmUI.BOARD);
		
		JLabel jl_list = new JLabel("�Խ���");
		jl_list.setFont(font);
		

		insert_panel = new JPanel();
		jtf_text = new JTextField(40);
		btn_insert = new JButton("�ۿø���");
		JButton btn_reset = new JButton("���ΰ�ħ");
		
		insert_panel.add(btn_insert);
		insert_panel.add(btn_reset);
		
		list = new ArrayList<UserVO>();
		

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		
	   list = HMui.system.gettext();
		

		
		for(UserVO vo : list) {
			if(vo != null) {
				row[0]= vo.getRno();
				row[1]= vo.getPno();
				row[2]=vo.getPname();
				row[3]=vo.getPtitle();
				row[4]=vo.getPdate();
				
				model.addRow(row);
			}
			table.repaint();
		}
		
		model.fireTableDataChanged();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.getColumn("��ȣ").setCellRenderer(dtcr);
		table.getColumn("�ڵ�").setCellRenderer(dtcr);
	    table.getColumn("�г���").setCellRenderer(dtcr);
	    table.getColumn("����").setCellRenderer(dtcr);
	    table.getColumn("��¥").setCellRenderer(dtcr);
	    
	    
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
	}
	
	
	public void boardinsert() {
		 bif = new JFrame();
		JPanel content_panel = new JPanel(new BorderLayout());
		JPanel post_panel = new JPanel();
		JPanel btns_panel = new JPanel();
		JPanel top = new JPanel();
		
		JButton btn_insert = new JButton("����ϱ�");
		JButton btn_nop = new JButton("���");
		btns_panel.add(btn_insert); btns_panel.add(btn_nop);
		
		JLabel title_la = new JLabel("����");
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
		
		Calendar cal = Calendar.getInstance();
	      SimpleDateFormat s1 = new SimpleDateFormat("yy/mm/dd");
	      int year = cal.get(Calendar.YEAR);

	      int month = cal.get(Calendar.MONTH)+1;

	      int day = cal.get(Calendar.DAY_OF_MONTH);

	      String date = s1.format(cal.getTime());
	      
		UserVO vo = new UserVO();
		Random rd = new Random();
		
		String a = HMui.system.rename(HMui.id);
		vo.setPno("P_" + rd.nextInt(10000));
		vo.setPname(a);
		vo.setPtitle(title_tf.getText());
		vo.setPtext(post.getText());
		vo.setPdate(year+"/"+month+"/"+day);
		vo.setMid(HMui.id);
		
		if(HMui.system.pinsert(vo)) {
			JOptionPane.showMessageDialog(null, "�Է��� �Ϸ�Ǿ����ϴ�.");
			
		}else {
			JOptionPane.showMessageDialog(null, "�Է½���");
		}
		
	}
	
	public void view() {
		list = new ArrayList<UserVO>();
		
		list = HMui.system.gettext();
		
		model.setNumRows(0);
		
		for(UserVO vo : list) {
			if(vo != null) {
				row[0]= vo.getRno();
				row[1]= vo.getPno();
				row[2]=vo.getPname();
				row[3]=vo.getPtitle();
				row[4]=vo.getPdate();
				
				
				model.addRow(row);
			}
			table.repaint();
		}
		model.fireTableDataChanged();
		
	}
	
	
	
	
	//������ �̺�Ʈ ó��
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand().trim();
		Object obj = e.getSource();
		
		if(name.equals("�ۿø���")) {
			boardinsert();
//			write();
		}else if(name.equals("���ΰ�ħ")) {
			view();
		}else if(name.equals("����ϱ�")) {
			write();
		}else if(name.equals("���")){
			bif.setVisible(false);
		}
		
		
	}//action
	


}
