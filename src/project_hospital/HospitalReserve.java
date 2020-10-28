package project_hospital;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class HospitalReserve {

	HospitalMgmUI HMui;
	JFrame reserveOKf;
	JPanel HospiResPane, la_HospiResPane;
	JPanel title_panel, hno_panel, animal_panel, visitPurpose_panel, symptom_panel, 
			visitDate_panel, visitTime_panel,btnReserve_panel, reserveOKp;
	JLabel la_hno, la_animal, la_visit_purpose, la_symptom, la_visit_year,la_visit_month,
			la_visit_day,la_visit_date,la_visit_time;
	JLabel la_title, la_hno1, la_animal1, la_visit_purpose1, la_visit_purpose2, la_symptom1, 
			la_visit_year1, la_visit_month1, la_visit_day1, la_visit_time1;
	JComboBox jc_animal, jc_visit_time;
	JCheckBox jch_visit_p1, jch_visit_p2;
	JTextArea jta_symptom;
	JTextField jt_visit_year, jt_visit_month, jt_visit_day;
	Button btn_reserve, btn_reserveOK;		
	
//	String id;
	
	public HospitalReserve() {}
	public HospitalReserve(HospitalMgmUI HMui) {
		this.HMui = HMui;
		this.HospiResPane = HMui.HospiResPane;
	}
	
	
	/** 병원 예약 **/
	public void reserve() {
		 HMui.switchPane(HospitalMgmUI.HOSPIRES);
		
		 animal_panel = new JPanel();
		 visitPurpose_panel = new JPanel();
		 symptom_panel = new JPanel();
		 visitDate_panel = new JPanel();
		 visitTime_panel = new JPanel();
		 btnReserve_panel = new JPanel();
		
		 la_animal = new JLabel("동물");
		 la_visit_purpose = new JLabel("방문 이유");
		 la_symptom = new JLabel("증상 / 접종명");
		 la_visit_date = new JLabel("예약 날짜");
		 la_visit_year = new JLabel("년");
		 la_visit_month = new JLabel("월");
		 la_visit_day = new JLabel("일");
		 la_visit_time = new JLabel("예약 시간");
		 
		 String[] animal_kinds = {"선택해주세요","강아지", "고양이","햄스터","토끼", "병아리","기타 등" };
		 String[] visit_times = {"선택해주세요","09:00", "10:00","11:00","13:00", 
				 					"14:00","15:00","16:00", "17:00", "18:00"};
		 
		 jc_animal = new JComboBox(animal_kinds);
		 jch_visit_p1 = new JCheckBox("진료");
		 jch_visit_p2 = new JCheckBox("접종");
		 jta_symptom = new JTextArea(5,30);
		 jt_visit_year = new JTextField(5);
		 jt_visit_month = new JTextField(5);
		 jt_visit_day = new JTextField(5);
		 jc_visit_time = new JComboBox(visit_times);
		 btn_reserve = new Button("예약하기");
		

		animal_panel.add(la_animal); 			animal_panel.add(jc_animal);
		visitPurpose_panel.add(la_visit_purpose);
		visitPurpose_panel.add(jch_visit_p1); 	visitPurpose_panel.add(jch_visit_p2);		
		symptom_panel.add(la_symptom); 			symptom_panel.add(jta_symptom);
		visitDate_panel.add(la_visit_date); 	
		visitDate_panel.add(jt_visit_year); 	visitDate_panel.add(la_visit_year);
		visitDate_panel.add(jt_visit_month); 	visitDate_panel.add(la_visit_month); 
		visitDate_panel.add(jt_visit_day);		visitDate_panel.add(la_visit_day);
		visitTime_panel.add(la_visit_time); 	visitTime_panel.add(jc_visit_time);
		btnReserve_panel.add(btn_reserve);
		
		
		HospiResPane.add(animal_panel);
		HospiResPane.add(visitPurpose_panel);
		HospiResPane.add(symptom_panel);
		HospiResPane.add(visitDate_panel);
		HospiResPane.add(visitTime_panel);
		HospiResPane.add(btnReserve_panel);
		

		HMui.add(HospiResPane, BorderLayout.CENTER);
		HMui.setVisible(true);
	    
	    Hospital_ReserveEvent reserveEvent = new Hospital_ReserveEvent();
		btn_reserve.addActionListener(reserveEvent);
	}//reserve
	
	
	/** 병원 예약 폼 체크 **/
	public boolean resFormCheck() {
		boolean result = false;
		
		if(jc_animal.getSelectedItem().toString().equals("선택해주세요")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("동물을 선택해주세요"));
			jc_animal.requestFocus();
		}else if(!jch_visit_p1.isSelected() && !jch_visit_p2.isSelected()) {   
			JOptionPane.showMessageDialog(null, HMui.getMsg("방문 이유를 체크해주세요"));
			jch_visit_p1.requestFocus();
		}else if(jta_symptom.getText().equals("")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("증상 or 접종명을 입력해주세요"));
			jta_symptom.requestFocus();
		}else if(jt_visit_year.getText().equals("")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("예약 날짜를 정확히 입력해주세요"));
			jt_visit_year.requestFocus();
		}else if(jt_visit_month.getText().equals("")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("예약 날짜를 정확히 입력해주세요"));
			jt_visit_month.requestFocus();
		}else if(jt_visit_day.getText().equals("")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("예약 날짜를 정확히 입력해주세요"));
			jt_visit_day.requestFocus();
		}else if(jc_visit_time.getSelectedItem().toString().equals("선택해주세요")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("예약 시간을 선택해주세요"));
			jc_visit_time.requestFocus();
		}
		else {
			result = true;
		}
		
		return result;
	}//regFormCheck
	
	
	/** 병원 예약proc **/
	public void reserveProc() {
				
		if(resFormCheck()) {
				
			//UserVO 객체를 생성하여 등록
			UserVO vo = new UserVO();
			Random rd = new Random();
		
			vo.setHno("H_" + rd.nextInt(10000));	
			vo.setHsymptom(jta_symptom.getText().trim());
			vo.setHyear(jt_visit_year.getText().trim());
			vo.setHmonth(jt_visit_month.getText().trim());
			vo.setHday(jt_visit_day.getText().trim());
			vo.setHtime(String.valueOf(jc_visit_time.getSelectedItem()));
			vo.setHmid(HMui.callId(vo.getMid()));
			
			//Hbooking 테이블에 등록
			if(HMui.system.Hospital_reserve(vo)) {
				JOptionPane.showMessageDialog(null, HMui.getMsg("등록 성공!!"));
				reserveOkForm(vo);
			}else {
				JOptionPane.showMessageDialog(null, HMui.getMsg("등록 실패!!"));
			}
//			if(HospitalMgmUI.list.add(vo))
//				JOptionPane.showMessageDialog(null, HMui.getMsg("등록 성공!!"));
			
		}
	}
	
	public void reserveOkForm(UserVO vo) {
		reserveOKf = new JFrame("예약 확인");
		reserveOKp = new JPanel(new GridLayout(8,1));
			
		 title_panel = new JPanel();
		 hno_panel = new JPanel();
		 animal_panel = new JPanel();
		 visitPurpose_panel = new JPanel();
		 symptom_panel = new JPanel();
		 visitDate_panel = new JPanel();
		 visitTime_panel = new JPanel();
		 btnReserve_panel = new JPanel();
		
		 la_title = new JLabel("\n -- 예약하신 내용입니다.  -- \n");
		 la_hno = new JLabel("예약 번호: ");
		 la_animal = new JLabel("동물 : ");
		 la_visit_purpose = new JLabel("방문 이유 : ");
		 la_symptom = new JLabel("증상/접종명 :");
		 la_visit_date = new JLabel("예약 날짜 : ");
		 la_visit_time = new JLabel("예약 시간 : ");
		 btn_reserveOK = new Button("확인 완료");
		 
		 la_hno1 = new JLabel(vo.getHno());
		 la_animal1 = new JLabel(String.valueOf(jc_animal.getSelectedItem()));
		 la_visit_purpose1 = new JLabel(jch_visit_p1.getText());
		 la_visit_purpose2 = new JLabel(jch_visit_p2.getText());
		 la_symptom1 = new JLabel(jta_symptom.getText());
		 la_visit_year1 = new JLabel(jt_visit_year.getText()+"년");
		 la_visit_month1 = new JLabel(jt_visit_month.getText()+"월");
		 la_visit_day1 = new JLabel(jt_visit_day.getText()+"일");
		 la_visit_time1 = new JLabel(String.valueOf(jc_visit_time.getSelectedItem()));
		 
		 
		 
		 title_panel.add(la_title);
		 hno_panel.add(la_hno);				hno_panel.add(la_hno1);
		 animal_panel.add(la_animal); 		animal_panel.add(la_animal1);	
		 visitPurpose_panel.add(la_visit_purpose);  
		 if(jch_visit_p1.isSelected()) {
			 visitPurpose_panel.add(la_visit_purpose1); 
		 }
		 if(jch_visit_p2.isSelected()) {
			 visitPurpose_panel.add(la_visit_purpose2);
		 }
		 symptom_panel.add(la_symptom);  		symptom_panel.add(la_symptom1); 
		 visitDate_panel.add(la_visit_date); 	visitDate_panel.add(la_visit_year1);
		 visitDate_panel.add(la_visit_month1);  visitDate_panel.add(la_visit_day1);
		 visitTime_panel.add(la_visit_time); 	visitTime_panel.add(la_visit_time1);
		 btnReserve_panel.add(btn_reserveOK);
		 
		 reserveOKp.add(title_panel);
		 reserveOKp.add(hno_panel);
		 reserveOKp.add(animal_panel);       	  
		 reserveOKp.add(visitPurpose_panel);
		 reserveOKp.add(symptom_panel);
		 reserveOKp.add(visitDate_panel);
		 reserveOKp.add(visitTime_panel);
		 reserveOKp.add(btnReserve_panel);
			
		 reserveOKf.add(reserveOKp, BorderLayout.CENTER);
		 reserveOKf.setVisible(true);
		 reserveOKf.setSize(400,400);
		 
		 Hospital_ReserveEvent reserveEvent = new Hospital_ReserveEvent();
			btn_reserveOK.addActionListener(reserveEvent);
	}
	
	

	class Hospital_ReserveEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			
			if(btn_reserve == obj) {
				if(resFormCheck()) { 		
					reserveProc();
//					JOptionPane.showMessageDialog(null, "예약 완료");					
				}else {
					JOptionPane.showMessageDialog(null, "예약 미완료");
				}
			}else if(btn_reserveOK == obj) {
				reserveOKf.setVisible(false);
			}
			
		}
		
	}//event class
	
	
}//class
