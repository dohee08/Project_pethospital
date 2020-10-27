package project_hospital;

import java.awt.*; 
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class HospitalHairCut extends JFrame {
	HospitalMgmUI main;
	HospitalMgmSystem system = new HospitalMgmSystem();
	
	JPanel SalonResPane;
	JTextField tf_year, tf_month, tf_day; //날짜
	JComboBox<String> cb_time; //시간
	JRadioButton rb_man, rb_woman; // 남, 여
	JTextArea ta_text; // 더하고싶은말
	JButton btn_reserve, btn_reset; // 예약, 취소버튼
	
	public HospitalHairCut() {}
	public HospitalHairCut(HospitalMgmUI main) { // 가입용 생성자
		this.main = main; 
		this.SalonResPane = main.SalonResPane;
		
	}// 생성자

	public void hairCut() {
		main.switchPane(HospitalMgmUI.SALONRES);
		JPanel gender_pane = new JPanel();
		JPanel text_pane = new JPanel();
		JPanel jp_Button = new JPanel();
		JPanel date_pane = new JPanel();
		JPanel time_pane = new JPanel();
		
		JLabel lb_date= new JLabel("날짜 : ");
		tf_year = new JTextField(6);
		tf_month = new JTextField(6);
		tf_day = new JTextField(6);
		JPanel jp_date = new JPanel();
		jp_date.add(tf_year);
		jp_date.add(new JLabel("/"));
		jp_date.add(tf_month);
		jp_date.add(new JLabel("/"));
		jp_date.add(tf_day);
		date_pane.add(lb_date);
		date_pane.add(jp_date);
		
		JLabel lb_time = new JLabel("시간 :");
		String[] arrtime = { "선택", "10:00 ~ 11:00", "11:00 ~ 12:00", "12:00 ~ 13:00"
				, "13:00 ~ 14:00", "14:00 ~ 15:00", "15:00 ~ 16:00", "16:00 ~ 17:00", "17:00 ~ 18:00"};
		cb_time = new JComboBox<String>(arrtime);
		JPanel jp_time = new JPanel();
		jp_time.add(cb_time);
		time_pane.add(lb_time);
		time_pane.add(jp_time);

		JLabel lGender = new JLabel("성별 :");
		JPanel pGender = new JPanel();
		rb_man = new JRadioButton("수컷", true);
		rb_woman = new JRadioButton("암컷", true);
		ButtonGroup group = new ButtonGroup();
		group.add(rb_man);
		group.add(rb_woman);
		pGender.add(rb_man);
		pGender.add(rb_woman);
		gender_pane.add(lGender);
		gender_pane.add(pGender);

		JLabel lb_text = new JLabel("기타 사항:");
		ta_text = new JTextArea(3,20); // 행 : 열
		JScrollPane pane = new JScrollPane(ta_text);
		text_pane.add(lb_text);
		text_pane.add(pane);

		btn_reserve = new JButton("예약");
		btn_reset = new JButton("취소");
		jp_Button.add(btn_reserve);
		jp_Button.add(btn_reset);
		
		SalonResPane.add(date_pane);
		SalonResPane.add(time_pane);
		SalonResPane.add(gender_pane);
		SalonResPane.add(text_pane);
		SalonResPane.add(jp_Button);
	
		
		HospitalHairEvent hairEvent = new HospitalHairEvent();
		btn_reserve.addActionListener(hairEvent);
		btn_reset.addActionListener(hairEvent);

		
		main.add(SalonResPane);
		main.setVisible(true);

	}// createUI

	public boolean regFormCheck() {
		boolean result = false;
		String date1 = tf_year.getText();
		String date2 = tf_month.getText();
		String date3 = tf_day.getText();
		
	    if (date1.trim().equals("") || date2.trim().equals("") || date3.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "날짜를 입력해주세요","알림",JOptionPane.INFORMATION_MESSAGE);
			tf_year.requestFocus();
		}
		else if (cb_time.getSelectedItem().equals("선택")) {
			JOptionPane.showMessageDialog(null, "시간을 선택해주세요","알림",JOptionPane.INFORMATION_MESSAGE);
		} 
		else {
			if(!integerOrNot(date1) || !integerOrNot(date2) || !integerOrNot(date3)){
				JOptionPane.showMessageDialog(null, "날짜는 문자를 입력할 수 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
			result = true;
		}

		return result;
	}// regFormCheck method
	
	public boolean integerOrNot(String strData){ // 입력값이 숫자인지 문자인지 판별 : 
		char[] charData = strData.toCharArray();
		boolean check=true;
		while(check){
			for(int i=0; i<charData.length; i++){		
				if(!Character.isDigit(charData[i])){
						check = !check;
						break;
				}
			}
			break;	
		}return check;
	}

	public void registerProc() {
		// MemberVO 객체를 생성하여 등록
		UserVO vo = new UserVO();
		
		String time = (String)cb_time.getSelectedItem();
		String gender = "";
		if(rb_man.isSelected()){
			gender = "수컷";
		}else if(rb_woman.isSelected()){
			gender = "암컷";
		}
	
		Random rd = new Random();
		vo.setSno("S_"+ rd.nextInt(10000));		
		vo.setSyear(tf_year.getText().trim());
		vo.setSmonth(tf_month.getText().trim());
		vo.setSday(tf_day.getText().trim());
		vo.setStime(time);
		vo.setSgender(gender);
		vo.setStext(ta_text.getText().trim());
		vo.setMid(null);
		

		// MEMBER 테이블에 등록
     	boolean result = system.hairCut(vo);

		if (result) {
			JOptionPane.showMessageDialog(null, "등록 성공!!");
			JOptionPane.showMessageDialog(null,"등록번호 : " + vo.getSno() 
			+"\n"+"날짜 : " + vo.getSyear()+"년"+vo.getSmonth()+"월"+vo.getSday()+"일"
			+ "\n" + "시간 : " + vo.getStime() 
			+ "\n" + "성별 : " + vo.getSgender() 
			+ "\n" + "기타사항 : " + vo.getStext() + "\n"
			,"예약 확인",JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "등록 실패!!");
		}

	}

	class HospitalHairEvent implements ActionListener {

		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if (btn_reserve == obj) {
				if (regFormCheck())
					registerProc();
			} else if (btn_reset == obj) {
				ta_text.setText("");
				tf_year.setText("");
				tf_month.setText("");
				tf_day.setText("");
				cb_time.setSelectedItem("선택");
				
			}

		}

	}// event class

}// end