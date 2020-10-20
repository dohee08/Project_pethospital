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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HospitalManager {
	JFrame jf;
	JPanel main_panel, button_panel;
	JTextField tid, tpass, tname, tphone;  
	JButton btnList, btnDelete;
	HospitalMgmUI main;
	
	public HospitalManager(HospitalMgmUI main) {
		this.main = main;
		
		Font font = new Font("맑은 고딕", Font.BOLD, 12);
		jf = new JFrame("관리자 화면");	
		
		main_panel = new JPanel();
	    button_panel = new JPanel();
	    
	    button_panel = new JPanel(new GridLayout(2,1));
	    button_panel.setSize(50,100);
	    
	    //관리자 UI 버튼 정의
	    btnList = new JButton("리스트");
		btnDelete = new JButton("정보 삭제");
		btnList.setFont(font);  btnDelete.setFont(font);	
		
		button_panel.add(btnList);
		button_panel.add(btnDelete);
		
		//메인 UI 창 위치 정의
	    jf.add(BorderLayout.WEST, button_panel);      
	    jf.add(BorderLayout.CENTER, main_panel); //- 원래는 메인 먼저 띄우기
	    
//	    jf.add(BorderLayout.CENTER, insert_panel);
	    
	    jf.setSize(600,500);
		jf.setVisible(true);
		
		//관리자 UI 버튼 이벤트 정의
		btnList.addActionListener(new JFrameObjectEvent(this));
		btnDelete.addActionListener(new JFrameObjectEvent(this));
		jf.addWindowListener(new JFrameObjectEvent(this));
		
	}
	//이벤트 처리 클래스
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
			jf.setVisible(false);
		}
			
		//액션 이벤트 처리
		public void actionPerformed(ActionEvent e) {
			String bname = e.getActionCommand();

			if(bname.equals("리스트")) {	
				//리스트 출력
				JOptionPane.showMessageDialog(null, "리스트");			
			}else if(bname.equals("정보 삭제")){
				// 정보 삭제 출력
				JOptionPane.showMessageDialog(null, "정보 삭제");		
			}else if(bname.equals("종료")) {
				System.out.println("--  종료 버튼이 클릭되었습니다. --");
				System.exit(0);
			}
		}
			
	}
	
}
	