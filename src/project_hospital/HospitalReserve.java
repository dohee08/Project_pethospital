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
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class HospitalReserve {

	HospitalMgmUI main;
	JPanel HospiResPane;
	JPanel title_panel, animal_panel, visitPurpose_panel, symptom_panel, visitDate_panel, visitTime_panel,btnReserve_panel;
	JLabel la_animal, la_visit_purpose, la_symptom, la_visit_year,la_visit_month,la_visit_day,la_visit_date,la_visit_time;
	JLabel la_title, la_animal1, la_visit_purpose1, la_visit_purpose2, la_symptom1, la_visit_year1,la_visit_month1,la_visit_day1, la_visit_time1;
	JComboBox jc_animal, jc_visit_time;
	JCheckBox jch_visit_p1, jch_visit_p2;
	JTextArea jta_symptom;
	JTextField jt_visit_year, jt_visit_month, jt_visit_day;
	Button btn_reserve;					
	
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
		
		 la_animal = new JLabel("����");
		 la_visit_purpose = new JLabel("�湮 ����");
		 la_symptom = new JLabel("����");
		 la_visit_date = new JLabel("���� ��¥");
		 la_visit_year = new JLabel("��");
		 la_visit_month = new JLabel("��");
		 la_visit_day = new JLabel("��");
		 
		 
		 la_visit_time = new JLabel("���� �ð�");
		 
		
		 String[] animal_kinds = {"�������ּ���","������", "�����","�ܽ���","�䳢", "���Ƹ�","��Ÿ ��" };
		 String[] visit_times = {"�������ּ���","09:00 ~ 10:00", "10:00 ~ 11:00","11:00 ~ 12:00","13:00 ~ 14:00", 
				 "14:00 ~ 15:00","15:00 ~ 16:00","16:00 ~ 17:00", "17:00 ~ 18:00", "18:00 ~ 19:00"};
		 
		 jc_animal = new JComboBox(animal_kinds);
		 jch_visit_p1 = new JCheckBox("����");
		 jch_visit_p2 = new JCheckBox("����");
		 jta_symptom = new JTextArea();
		 jt_visit_year = new JTextField(7);
		 jt_visit_month = new JTextField(7);
		 jt_visit_day = new JTextField(7);
		 jc_visit_time = new JComboBox(visit_times);
		 
		 btn_reserve = new Button("�����ϱ�");
		
		
		animal_panel.add(la_animal); 			animal_panel.add(jc_animal);
		visitPurpose_panel.add(la_visit_purpose);
		visitPurpose_panel.add(jch_visit_p1); visitPurpose_panel.add(jch_visit_p2);		
		symptom_panel.add(la_symptom); symptom_panel.add(jta_symptom);
		visitDate_panel.add(la_visit_date); 	visitDate_panel.add(jt_visit_year); visitDate_panel.add(la_visit_year);
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
	
	    main.add(HospiResPane, BorderLayout.CENTER);
		main.setVisible(true);
	    
	    Hospital_ReserveEvent reserveEvent = new Hospital_ReserveEvent();
		btn_reserve.addActionListener(reserveEvent);
		
	}//reserve
	
	public boolean regFormCheck() {
		boolean result = false;
		
		if(jc_animal.getSelectedItem().toString().equals("�������ּ���")) {
			JOptionPane.showMessageDialog(null, main.getMsg("������ �������ּ���"));
			jc_animal.requestFocus();
		}else if(!jch_visit_p1.isSelected() && !jch_visit_p2.isSelected()) {   
			JOptionPane.showMessageDialog(null, main.getMsg("�湮 ������ üũ���ּ���"));
			jch_visit_p1.requestFocus();
		}else if(jta_symptom.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("������ �Է����ּ���"));
			jta_symptom.requestFocus();
		}else if(jt_visit_year.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("���� ��¥�� �� �Է����ּ���"));
			jt_visit_year.requestFocus();
		}else if(jt_visit_month.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("���� ��¥�� �� �Է����ּ���"));
			jt_visit_month.requestFocus();
		}else if(jt_visit_day.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("���� ��¥�� �� �Է����ּ���"));
			jt_visit_day.requestFocus();
		}
		else if(jc_visit_time.getSelectedItem().toString().equals("�������ּ���")) {
			JOptionPane.showMessageDialog(null, main.getMsg("���� �ð��� �������ּ���"));
			jc_visit_time.requestFocus();
		}
		else {
			result = true;
		}
		
		return result;
	}//regFormCheck method
	
	//registerProc
	public void registerProc() {
		//MemberVO ��ü�� �����Ͽ� ���
//		MemberVO vo = new MemberVO();
//		vo.setName(jt_name.getText().trim());
//		vo.setAddr(jt_addr.getText().trim());
//		vo.setId(jt_id.getText().trim());
//		vo.setPass(jt_pass.getText().trim());
//		
		
		//MEMBER ���̺� ���
//		boolean result = main.system.register(vo);
//		if(result) {
//			JOptionPane.showMessageDialog(null, main.getMsg("��� ����!!"));
//		}else {
//			JOptionPane.showMessageDialog(null, main.getMsg("��� ����!!"));
//		}
//		if(MemberMgmUI.list.add(vo))
//			JOptionPane.showMessageDialog(null, main.getMsg("��� ����!!"));
		//���� �� ��������
		System.out.println(jc_animal.getSelectedItem());
		System.out.println(jch_visit_p1.getText());
		System.out.println(jch_visit_p2.getText());
		System.out.println(jta_symptom.getText());
		System.out.println(jt_visit_date.getText());
		System.out.println(jc_visit_time.getSelectedItem());
		
	}
	public void reserveOkForm() {
		JFrame jf = new JFrame("���� Ȯ��");
		JPanel jp = new JPanel(new GridLayout(7,1));
			
		 title_panel = new JPanel();
		 animal_panel = new JPanel();
		 visitPurpose_panel = new JPanel();
		 symptom_panel = new JPanel();
		 visitDate_panel = new JPanel();
		 visitTime_panel = new JPanel();
		 btnReserve_panel = new JPanel();
		
		 la_title = new JLabel(" -- �����Ͻ� �����Դϴ�.  --");
		 la_animal = new JLabel("���� : ");
		 la_visit_purpose = new JLabel("�湮 ���� : ");
		 la_symptom = new JLabel("���� :");
		 la_visit_date = new JLabel("���� ��¥ : ");
		 la_visit_time = new JLabel("���� �ð� : ");
		 
		 la_animal1 = new JLabel(String.valueOf(jc_animal.getSelectedItem()));
//		 if(jch_visit_p1.isSelected()) {
		 la_visit_purpose1 = new JLabel(jch_visit_p1.getText());
//		 if(jch_visit_p2.isSelected()) {
		 la_visit_purpose2 = new JLabel(jch_visit_p2.getText());

		 la_symptom1 = new JLabel(jta_symptom.getText());
		 la_visit_year1 = new JLabel(jt_visit_year.getText()+"��");
		 la_visit_month1 = new JLabel(jt_visit_month.getText()+"��");
		 la_visit_day1 = new JLabel(jt_visit_day.getText()+"��");
		 la_visit_time1 = new JLabel(String.valueOf(jc_visit_time.getSelectedItem()));
		 
		 
		 
		 title_panel.add(la_title);
		 animal_panel.add(la_animal); 		animal_panel.add(la_animal1);	
		 visitPurpose_panel.add(la_visit_purpose);  
		 if(jch_visit_p1.isSelected()) {
		 visitPurpose_panel.add(la_visit_purpose1); 
		 }
		 if(jch_visit_p2.isSelected()) {
			 visitPurpose_panel.add(la_visit_purpose2);
		 }
		 symptom_panel.add(la_symptom);  symptom_panel.add(la_symptom1); 
		 visitDate_panel.add(la_visit_date); 	
		 visitDate_panel.add(la_visit_year1); 
		 visitDate_panel.add(la_visit_month1);
		 visitDate_panel.add(la_visit_day1);
		 
		 visitTime_panel.add(la_visit_time); 	visitTime_panel.add(la_visit_time1);
		 
		 jp.add(title_panel);
		 jp.add(animal_panel);       	  
		 jp.add(visitPurpose_panel);
		 jp.add(symptom_panel);
		 jp.add(visitDate_panel);
		 jp.add(visitTime_panel);
			
		 jf.add(jp, BorderLayout.CENTER);
		 jf.setVisible(true);
		 jf.setSize(400,300);
	}
	
	

	class Hospital_ReserveEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			
			if(btn_reserve == obj) {
				if(regFormCheck()) { 		
					registerProc();
					reserveOkForm();
//					JOptionPane.showMessageDialog(null, "���� �Ϸ�");
					
				}else {
					JOptionPane.showMessageDialog(null, "���� �̿Ϸ�");
				}
			}
		}
		
	}//event class
	
	
}//class
