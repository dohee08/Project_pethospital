package project_hospital;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class HospitalBoard extends WindowAdapter implements ActionListener {
	JFrame jf,bif,bdf; 
	HospitalMgmUI HMui;
	UserVO vo;
	
	JPanel BoardPane, insert_panel, btnMenu_panel,intro_panel,viewer_panel,receive_panel,rselect_panel
		   ,send_panel,sselect_panel;
	JButton btn_insert,btn_reset, btn_delete;
	JButton btn_board, btn_onetoone;
	JTextField title_tf;
	JTextArea post ;
	
	ArrayList<UserVO> list;
	
	

	
	

	public static final int BOARD = 1;
	public static final int ONETOONE = 2;
	
	public static Font font = new Font("����������_ac", Font.BOLD, 12);
	public static Font font1 = new Font("����������_ac", Font.PLAIN, 14);
	
	public HospitalBoard() {}
	public HospitalBoard(HospitalMgmUI HMui) {
		this.HMui = HMui;
		this.BoardPane = HMui.BoardPane;
	}
	
	


	private JPanel jp_deleteSearch;
	private JTextField jt_deleteSearch;
	private JLabel jl_deleteSearchName;
	private JButton jb_deleteButton;
	private JPanel deletePane;
	Object[] row =new Object[5];
	
	
	public void HospitalBoard(){
		HMui.switchPane(HospitalMgmUI.BOARD);
		
		insert_panel = new JPanel();
		btnMenu_panel = new JPanel(new GridLayout(2,1));
		
		btn_board = new JButton("���� �Խ���");
		btn_onetoone = new JButton("1��1 ����");
		btn_insert = new JButton("�ۿø���");
		btn_reset = new JButton("���ΰ�ħ");
		btn_delete = new JButton("�ۻ���");
		
		
		intro_panel = new JPanel();
		viewer_panel = new JPanel();
		receive_panel = new JPanel();
		rselect_panel = new JPanel();
		send_panel = new JPanel();
		sselect_panel = new JPanel();
		
		
		Color c1 = new Color(255,231,159);
		Color c3 = new Color(229,197,148);
		btn_board.setBackground(c3);
		btn_onetoone.setBackground(c1);
		
		btn_insert.setBackground(Color.lightGray);
		btn_reset.setBackground(Color.lightGray);
		btn_delete.setBackground(Color.lightGray);

		
		btnMenu_panel.add(btn_board);
		btnMenu_panel.add(btn_onetoone);

		insert_panel.add(btn_insert);
		insert_panel.add(btn_reset);
		insert_panel.add(btn_delete);
		
//		list = new ArrayList<UserVO>();
//		
//
//		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
//		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
//
//		
//	   list = HMui.system.gettext();
//		
//
//		
//		for(UserVO vo : list) {
//			if(vo != null) {
//				row[0]= vo.getRno();
//				row[1]= vo.getPno();
//				row[2]=vo.getPname();
//				row[3]=vo.getPtitle();
//				row[4]=vo.getPdate();
//				
//				model.addRow(row);
//			}
//			table.repaint();
//		}
//		
//		 table.addMouseListener(new MouseAdapter() {
//	            @Override
//	            public void mouseClicked(MouseEvent e) {
//	                int rowNum = table.getSelectedRow();
//	                UserVO vo = new UserVO();
//	                vo = list.get(rowNum);
//	                new HospitalBoardPopUp(vo);
//	            }
//	        });
//		 
//		model.fireTableDataChanged();
//		model.setColumnIdentifiers(columns);
//		table.setModel(model);
//		
//		table.getColumn("��ȣ").setCellRenderer(dtcr);
//		table.getColumn("�ڵ�").setCellRenderer(dtcr);
//	    table.getColumn("�г���").setCellRenderer(dtcr);
//	    table.getColumn("����").setCellRenderer(dtcr);
//	    table.getColumn("��¥").setCellRenderer(dtcr);
//	    table.setBackground(Color.WHITE);
//	    
//	    JScrollPane pane = new JScrollPane(table);
//		pane.setBounds(0,100,600,250);
//
//		pane.setBackground(Color.WHITE);
		insert_panel.setBackground(Color.WHITE);
		btnMenu_panel.setBackground(Color.WHITE);
		BoardPane.setBackground(Color.WHITE);
//		
//		
		BoardPane.setLayout(new BorderLayout());
//		BoardPane.add(pane,BorderLayout.CENTER);
//		BoardPane.add(jl_list, BorderLayout.NORTH);
		BoardPane.add(btnMenu_panel, BorderLayout.WEST);
		BoardPane.add(insert_panel, BorderLayout.SOUTH);
		
		
		HMui.add(BoardPane,BorderLayout.CENTER);
		HMui.setVisible(true);
		
		
		btn_board.addActionListener(this);
		btn_onetoone.addActionListener(this);
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
	
	public void viewer() {
		
		Object[] columns = {"��ȣ","�ڵ�","�г���","����","��¥"};
		DefaultTableModel model =new DefaultTableModel(columns,0);
		JTable table= new JTable(model);
		
		BoardPane.removeAll();
		table.removeAll();
		
		
		JPanel btnMenu_panel = new JPanel(new GridLayout(2,1));
		JPanel insert_panel = new JPanel();
		
		JButton btn_board = new JButton("���� �Խ���");
		JButton btn_onetoone = new JButton("1��1 ����");
		JButton btn_insert = new JButton("�ۿø���");
		JButton btn_reset = new JButton("���ΰ�ħ");
		JButton btn_delete = new JButton("�ۻ���");
		
	

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		
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
		
		 table.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int rowNum = table.getSelectedRow();
	                UserVO vo = new UserVO();
	                vo = list.get(rowNum);
	                new HospitalBoardPopUp(vo);
	            }
	        });
		 
		model.fireTableDataChanged();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.getColumn("��ȣ").setCellRenderer(dtcr);
		table.getColumn("�ڵ�").setCellRenderer(dtcr);
	    table.getColumn("�г���").setCellRenderer(dtcr);
	    table.getColumn("����").setCellRenderer(dtcr);
	    table.getColumn("��¥").setCellRenderer(dtcr);
	    table.setBackground(Color.WHITE);
	    
	    JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0,100,600,250);

		
		
		Color c1 = new Color(255,231,159);
		Color c3 = new Color(229,197,148);
		btn_board.setBackground(c3);
		btn_onetoone.setBackground(c1);
		
		btn_insert.setBackground(Color.lightGray);
		btn_reset.setBackground(Color.lightGray);
		btn_delete.setBackground(Color.lightGray);
		
		pane.setBackground(Color.WHITE);
		insert_panel.setBackground(Color.WHITE);
		btnMenu_panel.setBackground(Color.WHITE);
		BoardPane.setBackground(Color.WHITE);

		btnMenu_panel.add(btn_board);
		btnMenu_panel.add(btn_onetoone);
		
		insert_panel.add(btn_insert);
		insert_panel.add(btn_reset);
		insert_panel.add(btn_delete);
		
		BoardPane.setLayout(new BorderLayout());
		BoardPane.add(pane,BorderLayout.CENTER);
//		BoardPane.add(jl_list, BorderLayout.NORTH);
		BoardPane.add(btnMenu_panel, BorderLayout.WEST);
		BoardPane.add(insert_panel, BorderLayout.SOUTH);
		
		
		HMui.add(BoardPane,BorderLayout.CENTER);
		HMui.setVisible(true);
		
		
		btn_board.addActionListener(this);
		btn_onetoone.addActionListener(this);
		btn_insert.addActionListener(this);
		btn_reset.addActionListener(this);
		btn_delete.addActionListener(this);
		
	}
	
//	public void view() {
//		list = new ArrayList<UserVO>();
//		
//		list = HMui.system.gettext();
//		
//		model.setNumRows(0);
//		
//		for(UserVO vo : list) {
//			if(vo != null) {
//				row[0]= vo.getRno();
//				row[1]= vo.getPno();
//				row[2]=vo.getPname();
//				row[3]=vo.getPtitle();
//				row[4]=vo.getPdate();
//				
//				
//				model.addRow(row);
//			}
//			table.repaint();
//		}
//		model.fireTableDataChanged();
//		
//		
//	}
	
	//����
	public void boarddelete() {
		bdf = new JFrame();
		jp_deleteSearch = new JPanel();
		jp_deleteSearch.setBackground(Color.WHITE);
		jl_deleteSearchName = new JLabel("������ �Խñ� ��ȣ >");
		jt_deleteSearch = new JTextField(20);
		jb_deleteButton = new JButton("Ȯ��");
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
	
	/** ������ ���̵� üũ **/
	public boolean deleteDataCheck(String pno) {
		return HMui.system.deletePostSearch(pno);
	}
	
	/** ���� ���� **/
	public void deleteProc(String pno) {
		String name = HMui.system.rename(HMui.id);
		boolean result = HMui.system.deletePost(pno,name);
		if(result) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("���� �Ϸ�~"));
		}else {
			JOptionPane.showMessageDialog(null, HMui.getMsg("���� ����! ������ �Խñ۸� ���� �����մϴ�."));
		}
	}//deleteProc
	
	/** 1�� 1 �ʱ�â
	 * */
	public void intro11() {
		
		resetMenuPanel();
		intro_panel.removeAll();
		BoardPane.removeAll();
		
		intro_panel.setLayout(new GridLayout(1,2));
		Button s = new Button("1��1 ���� ������");
		Button r = new Button("1��1 �亯 Ȯ��");
		
		intro_panel.add(s);
		intro_panel.add(r);
		
		receive_panel.setLayout(new BorderLayout());
		receive_panel.add(BorderLayout.NORTH,intro_panel);
		
		BoardPane.add(btnMenu_panel, BorderLayout.WEST);
		BoardPane.add(receive_panel,BorderLayout.CENTER);
		
		BoardPane.setBackground(Color.white);
		intro_panel.setBackground(Color.white);
		s.setBackground(Color.white);
		r.setBackground(Color.white);
		
		HMui.add(BoardPane,BorderLayout.CENTER);
		receive_panel.setVisible(true);
		HMui.setVisible(true);
		
		r.addActionListener(this);
		s.addActionListener(this);
	}
	
	
	/** 1��1 �亯 �޾ƿ���
	 */
	public void receive() {
		resetMenuPanel();
		
			
		receive_panel.setVisible(true);
		receive_panel.removeAll();
		rselect_panel.removeAll();
		
		Object[] rcolumns = {"����","�ڵ�","����","��¥","�������","�����ߴ� ����"};
		DefaultTableModel rmodel =new DefaultTableModel(rcolumns,0);
		JTable rtable= new JTable(rmodel);
		Object[] rrow =new Object[6];
		
		list = new ArrayList<UserVO>();
		
		list = HMui.system.receive(HMui.id);
		
		rmodel.setNumRows(0);
		
		for(UserVO vo : list) {
			if(vo != null) {
				rrow[0]= vo.getRno();
				rrow[1]= vo.getAid();
				rrow[2]=vo.getAtitle();
				rrow[3]=vo.getAdate();
				rrow[4]=vo.getAsname();
				rrow[5]=vo.getBid();
				
				rmodel.addRow(rrow);
			}
			rtable.repaint();
		}
		rmodel.fireTableDataChanged();
		
		
		rtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNum = rtable.getSelectedRow();
				UserVO vo = new UserVO();
				vo = list.get(rowNum);
				new HospitalBoardPopUpR(vo);
			}
		});
		
		rmodel.fireTableDataChanged();
		rmodel.setColumnIdentifiers(rcolumns);
		rtable.setModel(rmodel);
		
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	    TableColumnModel tcm = rtable.getColumnModel();
	    
	    rtable.getColumn("����").setCellRenderer(dtcr);
	    rtable.getColumn("�ڵ�").setCellRenderer(dtcr);
	    rtable.getColumn("����").setCellRenderer(dtcr);
	    rtable.getColumn("��¥").setCellRenderer(dtcr);
	    rtable.getColumn("�������").setCellRenderer(dtcr);
	    rtable.getColumn("�����ߴ� ����").setCellRenderer(dtcr);
		
	    JScrollPane pane=new JScrollPane(rtable);
		pane.setBounds(0,100,600,250);
		
		rselect_panel.setLayout(new BorderLayout());
		rselect_panel.add(BorderLayout.CENTER,pane);
		rselect_panel.add(BorderLayout.NORTH, new Label("1��1���� �亯 ����"));
		receive_panel.add(BorderLayout.CENTER,rselect_panel);
		receive_panel.add(BorderLayout.NORTH,intro_panel);
		BoardPane.add(BorderLayout.CENTER,receive_panel);
		
		pane.setBackground(Color.WHITE);
		rselect_panel.setBackground(Color.white);
		receive_panel.setBackground(Color.white);
		BoardPane.setBackground(Color.white);
		
		HMui.add(BoardPane,BorderLayout.CENTER);
		HMui.setVisible(true);    
	}
	
//	public void rcreateJtableData() {
//		list = new ArrayList<UserVO>();
//		
//		list = HMui.system.receive(HMui.id);
//		
//		rmodel.setNumRows(0);
//		
//		for(UserVO vo : list) {
//			if(vo != null) {
//				rrow[0]= vo.getRno();
//				rrow[1]= vo.getAid();
//				rrow[2]=vo.getAtitle();
//				rrow[3]=vo.getAdate();
//				rrow[4]=vo.getAsname();
//				rrow[5]=vo.getBid();
//				
//				rmodel.addRow(rrow);
//			}
//			rtable.repaint();
//		}
//		rmodel.fireTableDataChanged();
//		
//	}
	
	public void send() {
		resetMenuPanel();
		
		receive_panel.setVisible(true);
		receive_panel.removeAll();
		sselect_panel.removeAll();
		
		Button send = new Button("1��1 �����ϱ�");
		
		Object[] scolumns = {"����","�ڵ�","����","��¥","�޴»��"};
		DefaultTableModel smodel =new DefaultTableModel(scolumns,0);
		JTable stable= new JTable(smodel);
		Object[] srow =new Object[5];
		
		list = new ArrayList<UserVO>();
		
		list = HMui.system.send(HMui.id);
		
		smodel.setNumRows(0);
		
		for(UserVO vo : list) {
			if(vo != null) {
				srow[0]= vo.getRno();
				srow[1]= vo.getBid();
				srow[2]=vo.getBtitle();
				srow[3]=vo.getBdate();
				srow[4]=vo.getBsname();
				
				smodel.addRow(srow);
			}
			stable.repaint();
		}
		smodel.fireTableDataChanged();
		
		
		stable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNum = stable.getSelectedRow();
				UserVO vo = new UserVO();
				vo = list.get(rowNum);
				new HospitalBoardPopUpSe(vo);
			}
		});
		
		smodel.fireTableDataChanged();
		smodel.setColumnIdentifiers(scolumns);
		stable.setModel(smodel);
		
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	    TableColumnModel tcm = stable.getColumnModel();
	    
	    stable.getColumn("����").setCellRenderer(dtcr);
	    stable.getColumn("�ڵ�").setCellRenderer(dtcr);
	    stable.getColumn("����").setCellRenderer(dtcr);
	    stable.getColumn("��¥").setCellRenderer(dtcr);
	    stable.getColumn("�޴»��").setCellRenderer(dtcr);
		
	    JScrollPane pane=new JScrollPane(stable);
		pane.setBounds(0,100,600,250);
		
		sselect_panel.setLayout(new BorderLayout());
		sselect_panel.add(BorderLayout.CENTER,pane);
		sselect_panel.add(BorderLayout.NORTH, new Label("1��1 ���� ����"));
		receive_panel.add(BorderLayout.CENTER,sselect_panel);
		receive_panel.add(BorderLayout.NORTH,intro_panel);
		receive_panel.add(BorderLayout.SOUTH,send);
		BoardPane.add(BorderLayout.CENTER,receive_panel);
		
		pane.setBackground(Color.WHITE);
		sselect_panel.setBackground(Color.white);
		receive_panel.setBackground(Color.white);
		BoardPane.setBackground(Color.white);
		send.setBackground(Color.white);
		
		HMui.add(BoardPane,BorderLayout.CENTER);
		HMui.setVisible(true);    
		
		send.addActionListener(this);
	}
	
//	public void screateJtableData() {
//		list = new ArrayList<UserVO>();
//		
//		list = HMui.system.send(HMui.id);
//		
//		smodel.setNumRows(0);
//		
//		for(UserVO vo : list) {
//			if(vo != null) {
//				srow[0]= vo.getRno();
//				srow[1]= vo.getBid();
//				srow[2]=vo.getBtitle();
//				srow[3]=vo.getBdate();
//				srow[4]=vo.getBsname();
//				
//				smodel.addRow(srow);
//			}
//			stable.repaint();
//		}
//		smodel.fireTableDataChanged();
//		
//	}
	
	public void resetMenuPanel() {
//		receive_panel.setVisible(false);
//		content_panel.setVisible(false);
//		insert_panel.setVisible(false);
	}
	
	//������ �̺�Ʈ ó��
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand().trim();
		Object obj = e.getSource();
		
		if(name.equals("���� �Խ���")) {
			viewer();	
		}else if(name.equals("1��1 ����")) {
			intro11();
		}else if(name.equals("�ۿø���")) {
			boardinsert();
		}else if(name.equals("���ΰ�ħ")) {
			viewer();
		}else if(name.equals("����ϱ�")) {
			write();
		}else if(name.equals("���")){
			bif.setVisible(false);
		}else if(name.equals("�ۻ���")) {
			boarddelete();
		}else if(name.contentEquals("1��1 �亯 Ȯ��")) {
			receive();	
		}else if(name.contentEquals("1��1 ���� ������")) {
			send();
		}else if(name.contentEquals("1��1 �����ϱ�")){
			new HospitalBoardPopUpS(HMui).sendMessage();
		}
		else if(!jt_deleteSearch.getText().equals("") || jb_deleteButton.equals("Ȯ��")) {
			String pno = jt_deleteSearch.getText().trim();
			
			if(deleteDataCheck(pno)) {
				int result = JOptionPane.showConfirmDialog(null,
						"������ �����Ͻðڽ��ϱ�?");				
				if(result == 0)  deleteProc(pno);
			}else {
				//������ ������ ����
				JOptionPane.showMessageDialog(null,
						"������ �����Ͱ� �������� �ʽ��ϴ�.");
			}
		}else {
			JOptionPane.showMessageDialog(null,
					"�������� �Է����ּ���");
			jt_deleteSearch.requestFocus();
		}
		
		
	}//action
	


}
