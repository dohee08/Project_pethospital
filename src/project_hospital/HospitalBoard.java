package project_hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HospitalBoard {
	JFrame jf; 
	HospitalMgmUI HMui;
	UserVO vo;
	
	JPanel BoardPane, insert_panel;
	JLabel la_text;
	JTextField jtf_text;
	JButton btn_insert;
	
	
	
	public static Font font = new Font("나눔스퀘어_ac", Font.BOLD, 12);
	public static Font font1 = new Font("나눔스퀘어_ac", Font.BOLD, 16);
	
	public HospitalBoard() {}
	public HospitalBoard(HospitalMgmUI HMui) {
		this.HMui = HMui;
		this.BoardPane = HMui.BoardPane;
	}
	
	
	public void HospitalBoard(){
		HMui.switchPane(HospitalMgmUI.BOARD);
		
		JLabel jl_list = new JLabel("게시판");
		jl_list.setFont(font);
		
		la_text = new JLabel("내용 :");
		insert_panel = new JPanel();
		jtf_text = new JTextField(40);
		btn_insert = new JButton("글올리기");
		
		
		insert_panel.add(la_text);
		insert_panel.add(jtf_text);
		insert_panel.add(btn_insert);
		
		
		
		ArrayList<UserVO>boardlist = HMui.system.memberSelectList();

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		Object[] columns = {"번호","닉네임","내용","날짜"};
		DefaultTableModel model =new DefaultTableModel(columns,0);
		JTable table= new JTable(model);
		Object[] row =new Object[4];
		
		if(boardlist.size() != 0) {
			model.setNumRows(0);
			
			for(UserVO vo : boardlist) {
				if(vo != null) {
					row[0] = vo.getPno();
					row[1] = vo.getMname();
					row[2] = vo.getPost();
					row[3] = vo.getPdate();
	
					model.addRow(row);
				}
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
	}
	


}
