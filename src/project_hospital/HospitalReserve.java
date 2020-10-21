package project_hospital;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class HospitalReserve {

	HospitalMgmUI main;
	JPanel HospiResPane;
	JPanel animal_panel, visitPurpose_panel, symptom_panel, visitDate_panel, visitTime_panel,btnReserve_panel;
	JLabel la_animal, la_visit_purpose, la_symptom, la_visit_date,la_visit_time;
	JComboBox jc_animal, jc_visit_time;
	JCheckBox jch_visit_p1, jch_visit_p2;
	JTextField jt_symptom, jt_visit_date;
	Button btn_reserve;					////////////////
	
	public HospitalReserve() {}
	public HospitalReserve(HospitalMgmUI main) {
		this.main = main;
		this.HospiResPane = main.HospiResPane;
	}
	
	public void reserve() {
		main.switchPane(HospitalMgmUI.HOSPIRES);
		
		 animal_panel = new JPanel();
		 visitPurpose_panel = new JPanel();
		 symptom_panel = new JPanel();
		 visitDate_panel = new JPanel();
		 visitTime_panel = new JPanel();
		 btnReserve_panel = new JPanel();
		
		 la_animal = new JLabel("동물");
		 la_visit_purpose = new JLabel("방문 이유");
		 la_symptom = new JLabel("증상");
		 la_visit_date = new JLabel("예약 날짜");
		 la_visit_time = new JLabel("예약 시간");
		
		 String[] animal_kinds = {"선택해주세요","강아지", "고양이","햄스터","토끼", "병아리","기타 등" };
		 String[] visit_times = {"선택해주세요","09:00 ~ 10:00", "10:00 ~ 11:00","11:00 ~ 12:00","13:00 ~ 14:00", 
				 "14:00 ~ 15:00","15:00 ~ 16:00","16:00 ~ 17:00", "17:00 ~ 18:00", "18:00 ~ 19:00"};
		 
		 jc_animal = new JComboBox(animal_kinds);
		 jch_visit_p1 = new JCheckBox("진료");
		 jch_visit_p2 = new JCheckBox("접종");
		 jt_symptom = new JTextField(20);
		 jt_visit_date = new JTextField(20);
		 jc_visit_time = new JComboBox(visit_times);
		 
		 btn_reserve = new Button("예약하기");
		
		
		animal_panel.add(la_animal); 			animal_panel.add(jc_animal);
		visitPurpose_panel.add(la_visit_purpose);
		visitPurpose_panel.add(jch_visit_p1); visitPurpose_panel.add(jch_visit_p2);		
		symptom_panel.add(la_symptom); symptom_panel.add(jt_symptom);
		visitDate_panel.add(la_visit_date); 	visitDate_panel.add(jt_visit_date);
		visitTime_panel.add(la_visit_time); 	visitTime_panel.add(jc_visit_time);
		btnReserve_panel.add(btn_reserve);
		
		
		HospiResPane.add(animal_panel);
		HospiResPane.add(visitPurpose_panel);
		HospiResPane.add(symptom_panel);
		HospiResPane.add(visitDate_panel);
		HospiResPane.add(visitTime_panel);
		
		HospiResPane.add(btnReserve_panel);
	
	    main.add(HospiResPane, BorderLayout.CENTER);
		main.setVisible(true);
	    
	    Hospital_ReserveEvent reserveEvent = new Hospital_ReserveEvent();
		btn_reserve.addActionListener(reserveEvent);
		
	}//reserve
	
	public boolean regFormCheck() {
		boolean result = false;
		
		if(jc_animal.getSelectedItem().toString().equals("선택해주세요")) {
			JOptionPane.showMessageDialog(null, main.getMsg("동물을 선택해주세요"));
			jc_animal.requestFocus();
		}else if(!jch_visit_p1.isSelected() && !jch_visit_p2.isSelected()) {   
			JOptionPane.showMessageDialog(null, main.getMsg("방문 이유를 체크해주세요"));
			jch_visit_p1.requestFocus();
		}else if(jt_symptom.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("증상을 입력해주세요"));
			jt_symptom.requestFocus();
		}else if(jt_visit_date.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("예약 날짜를 입력해주세요"));
			jt_visit_date.requestFocus();
		}else if(jc_visit_time.getSelectedItem().toString().equals("선택해주세요")) {
			JOptionPane.showMessageDialog(null, main.getMsg("예약 시간을 선택해주세요"));
			jc_visit_time.requestFocus();
		}
		else {
			result = true;
		}
		
		return result;
	}//regFormCheck method
	
	//registerProc
	public void registerProc() {
		//MemberVO 객체를 생성하여 등록
//		MemberVO vo = new MemberVO();
//		vo.setName(jt_name.getText().trim());
//		vo.setAddr(jt_addr.getText().trim());
//		vo.setId(jt_id.getText().trim());
//		vo.setPass(jt_pass.getText().trim());
//		
		
		//MEMBER 테이블에 등록
//		boolean result = main.system.register(vo);
//		if(result) {
//			JOptionPane.showMessageDialog(null, main.getMsg("등록 성공!!"));
//		}else {
//			JOptionPane.showMessageDialog(null, main.getMsg("등록 실패!!"));
//		}
//		if(MemberMgmUI.list.add(vo))
//			JOptionPane.showMessageDialog(null, main.getMsg("등록 성공!!"));
		//예약 값 가져오기
		System.out.println(jc_animal.getSelectedItem());
		System.out.println(jch_visit_p1.getText());
		System.out.println(jch_visit_p2.getText());
		System.out.println(jt_symptom.getText());
		System.out.println(jt_visit_date.getText());
		System.out.println(jc_visit_time.getSelectedItem());
		
	}
	public void reserveOkForm() {
		JFrame jf = new JFrame("예약 확인");
		JPanel jp = new JPanel(new GridLayout(7,1));
		
		 animal_panel = new JPanel();
		 visitPurpose_panel = new JPanel();
		 symptom_panel = new JPanel();
		 visitDate_panel = new JPanel();
		 visitTime_panel = new JPanel();
		 btnReserve_panel = new JPanel();
		
		 la_animal = new JLabel("동물");
		 la_visit_purpose = new JLabel("방문 이유");
		 la_symptom = new JLabel("증상");
		 la_visit_date = new JLabel("예약 날짜");
		 la_visit_time = new JLabel("예약 시간");
		
		 animal_panel.add(la_animal); 		jc_animal.getSelectedItem();	
		 visitPurpose_panel.add(la_visit_purpose);
		 symptom_panel.add(la_symptom); 
		 visitDate_panel.add(la_visit_date); 	
		 visitTime_panel.add(la_visit_time); 	
		 
		 	jp.add(animal_panel);
			jp.add(visitPurpose_panel);
			jp.add(symptom_panel);
			jp.add(visitDate_panel);
			jp.add(visitTime_panel);
			
			jf.add(jp, BorderLayout.CENTER);
			jf.setVisible(true);
			jf.setSize(400,400);
	}
	
	

	class Hospital_ReserveEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			
			if(btn_reserve == obj) {
				if(regFormCheck()) { 		
					registerProc();	
					JOptionPane.showMessageDialog(null, "예약 완료");
					reserveOkForm();
				}else {
					JOptionPane.showMessageDialog(null, "예약 미완료");
				}
			}
		}
		
	}//event class
	
	
}//class
