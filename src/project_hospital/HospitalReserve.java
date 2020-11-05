package project_hospital;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class HospitalReserve {
	
	HospitalMgmUI HMui;
	JFrame reserveOKf;
	JPanel HospiResPane, la_HospiResPane;
	JPanel intro_panel, title_panel, hno_panel, visitPurpose_panel, symptom_panel, 
			visitDate_panel, visitTime_panel,btnReserve_panel, reserveOKp, b_panel;
	JLabel la_hno, la_title, la_visit_purpose, la_symptom, la_visit_year,la_visit_month,
			la_visit_day,la_visit_date,la_visit_time;
	JLabel la_intro, la_title1, la_hno1, la_visit_purpose1, la_visit_purpose2, la_symptom1, 
			la_visit_year1, la_visit_month1, la_visit_day1, la_visit_time1;
	JComboBox jc_visit_time;
	JCheckBox jch_visit_p1, jch_visit_p2;
	JTextArea jta_symptom;
	JTextField jt_visit_year, jt_visit_month, jt_visit_day;
	JButton btn_reserve, btn_reserveOK, btn_reset;		
	public static Font font = new Font("����������_ac", Font.BOLD, 15);
	public static Font font1 = new Font("����������_ac", Font.BOLD, 18);
	
	
	public HospitalReserve() {}
	public HospitalReserve(HospitalMgmUI HMui) {
		this.HMui = HMui;
		this.HospiResPane = HMui.HospiResPane;
	}
	
	/** ���� ���� **/
	public void reserve() {
		
		UserVO vo = new UserVO();
		
		 HMui.switchPane(HospitalMgmUI.HOSPIRES);
		
		 b_panel = new JPanel();
		 intro_panel = new JPanel();
		 visitPurpose_panel = new JPanel();
		 symptom_panel = new JPanel();
		 visitDate_panel = new JPanel();
		 visitTime_panel = new JPanel();
		 btnReserve_panel = new JPanel();
		
		 la_intro = new JLabel("==================     ���� ����        ==================");
		 la_visit_purpose = new JLabel("�湮 ����  ");
		 la_symptom = new JLabel("���� / ������ ");
		 la_visit_date = new JLabel("���� ��¥ ");
		 la_visit_year = new JLabel("��");
		 la_visit_month = new JLabel("��");
		 la_visit_day = new JLabel("��");
		 la_visit_time = new JLabel("���� �ð� ");
		
		 la_intro.setHorizontalAlignment(JLabel.CENTER);
		 
		 
		 String[] visit_times = {"�������ּ���","09:00", "10:00","11:00","13:00", 
				 					"14:00","15:00","16:00", "17:00", "18:00"};

		 jch_visit_p1 = new JCheckBox("����");
		 jch_visit_p2 = new JCheckBox("����");
		 jta_symptom = new JTextArea(1,30);
		 jt_visit_year = new JTextField(6);
		 jt_visit_month = new JTextField(6);
		 jt_visit_day = new JTextField(6);
		 jc_visit_time = new JComboBox(visit_times);
		 btn_reserve = new JButton("����");
		 btn_reset = new JButton("���");
		
		 
		 la_intro.setFont(font1);
		 la_visit_purpose.setFont(font);
		 la_symptom.setFont(font);
		 la_visit_date.setFont(font);
		 la_visit_year.setFont(font);
		 la_visit_month.setFont(font);
		 la_visit_day.setFont(font);
		 la_visit_time.setFont(font);
		 jch_visit_p1.setFont(font);
		 jch_visit_p2.setFont(font);
		 
		 
		 jch_visit_p1.setBackground(Color.WHITE);
		 jch_visit_p2.setBackground(Color.WHITE);
		 jc_visit_time.setBackground(Color.WHITE);
		 btn_reserve.setBackground(Color.WHITE);
		 btn_reset.setBackground(Color.WHITE);
		 
		 jta_symptom.setLineWrap(true);
		 jta_symptom.setWrapStyleWord(true);
		 
		 Border lineBorder = BorderFactory.createLineBorder(Color.black, 2);
		 Border emptyBorder = BorderFactory.createEmptyBorder(7, 5, 5, 5);
		 jta_symptom.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		 jt_visit_year.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		 jt_visit_month.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		 jt_visit_day.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

		
		intro_panel.add(la_intro); 		
		visitPurpose_panel.add(la_visit_purpose);
		visitPurpose_panel.add(jch_visit_p1); 	visitPurpose_panel.add(jch_visit_p2);		
		symptom_panel.add(la_symptom); 			symptom_panel.add(jta_symptom);
		visitDate_panel.add(la_visit_date); 	
		visitDate_panel.add(jt_visit_year); 	visitDate_panel.add(la_visit_year);
		visitDate_panel.add(jt_visit_month); 	visitDate_panel.add(la_visit_month); 
		visitDate_panel.add(jt_visit_day);		visitDate_panel.add(la_visit_day);
		visitTime_panel.add(la_visit_time); 	visitTime_panel.add(jc_visit_time);
		btnReserve_panel.add(btn_reserve);		btnReserve_panel.add(btn_reset);
		
		
		HospiResPane.add(intro_panel);
		HospiResPane.add(visitPurpose_panel);
		HospiResPane.add(symptom_panel);
		HospiResPane.add(visitDate_panel);
		HospiResPane.add(visitTime_panel);
		HospiResPane.add(btnReserve_panel);
		
		b_panel.setBackground(Color.WHITE);
		intro_panel.setBackground(Color.WHITE);
		visitPurpose_panel.setBackground(Color.WHITE);
		symptom_panel.setBackground(Color.WHITE);
		visitDate_panel.setBackground(Color.WHITE);
		visitTime_panel.setBackground(Color.WHITE);
		btnReserve_panel.setBackground(Color.WHITE);
		HospiResPane.setBackground(Color.WHITE);
		
		HMui.add(HospiResPane, BorderLayout.CENTER);
		HMui.setVisible(true);
	    
	    Hospital_ReserveEvent reserveEvent = new Hospital_ReserveEvent();
		btn_reserve.addActionListener(reserveEvent);
		btn_reset.addActionListener(reserveEvent);
	}//reserve
	
	
	/** ���� ���� �� üũ **/
	public boolean resFormCheck() {
		boolean result = false;
		
		if(!jch_visit_p1.isSelected() && !jch_visit_p2.isSelected()) {   
			JOptionPane.showMessageDialog(null, HMui.getMsg("�湮 ������ üũ���ּ���"));
			jch_visit_p1.requestFocus();
		}else if(jta_symptom.getText().equals("")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("���� or �������� �Է����ּ���"));
			jta_symptom.requestFocus();
		}else if(jt_visit_year.getText().equals("")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("���� ��¥�� ��Ȯ�� �Է����ּ���"));
			jt_visit_year.requestFocus();
		}else if(jt_visit_month.getText().equals("")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("���� ��¥�� ��Ȯ�� �Է����ּ���"));
			jt_visit_month.requestFocus();
		}else if(jt_visit_day.getText().equals("")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("���� ��¥�� ��Ȯ�� �Է����ּ���"));
			jt_visit_day.requestFocus();
		}else if(jc_visit_time.getSelectedItem().toString().equals("�������ּ���")) {
			JOptionPane.showMessageDialog(null, HMui.getMsg("���� �ð��� �������ּ���"));
			jc_visit_time.requestFocus();
		}
		else {
			result = true;
		}
		
		return result;
	}//regFormCheck
	
	
	/** ���� ����proc **/
	public void reserveProc() {
				
		if(resFormCheck()) {
			
			//UserVO ��ü�� �����Ͽ� ���
			UserVO vo = new UserVO();
			Random rd = new Random();
			
			vo.setHno("H_" + rd.nextInt(10000));	
			vo.setHsymptom(jta_symptom.getText().trim());
			vo.setHyear(jt_visit_year.getText().trim());
			vo.setHmonth(jt_visit_month.getText().trim());
			vo.setHday(jt_visit_day.getText().trim());
			vo.setHtime(String.valueOf(jc_visit_time.getSelectedItem()));
			vo.setHmid(HMui.callId(vo.getMid()));
			
			//���� �ð� �ߺ� Ȯ��
			if(HMui.system.reserveTimeCheck(vo) == 0) {
			//Hbooking ���̺� ���
				if(HMui.system.Hospital_reserve(vo)) {
					JOptionPane.showMessageDialog(null, HMui.getMsg("��� ����!!"));
					reserveOkForm(vo);
				}else {
					JOptionPane.showMessageDialog(null, HMui.getMsg("��� ����!!"));
				}
			}else {
				JOptionPane.showMessageDialog(null, HMui.getMsg("�ش� ����ð��� ������ �� �����ϴ�. �ٸ� �ð��� �������ּ���"));		
			}
		}
	}
	
	
	/** ���� Ȯ�� **/
	public void reserveOkForm(UserVO vo) {
		reserveOKf = new JFrame("���� Ȯ��");
		reserveOKp = new JPanel(new GridLayout(7,1));
			
		 title_panel = new JPanel();
		 hno_panel = new JPanel();
		 visitPurpose_panel = new JPanel();
		 symptom_panel = new JPanel();
		 visitDate_panel = new JPanel();
		 visitTime_panel = new JPanel();
		 btnReserve_panel = new JPanel();
		
		 la_title1 = new JLabel(" ----- �����Ͻ� �����Դϴ� ----- ");
		 la_hno = new JLabel("���� ��ȣ: ");
		 la_visit_purpose = new JLabel("�湮 ���� : ");
		 la_symptom = new JLabel("����/������ :");
		 la_visit_date = new JLabel("���� ��¥ : ");
		 la_visit_time = new JLabel("���� �ð� : ");
		 btn_reserveOK = new JButton("Ȯ�� �Ϸ�");
		 
		 la_hno1 = new JLabel(vo.getHno());
		 la_visit_purpose1 = new JLabel(jch_visit_p1.getText());
		 la_visit_purpose2 = new JLabel(jch_visit_p2.getText());
		 la_symptom1 = new JLabel(jta_symptom.getText());
		 la_visit_year1 = new JLabel(jt_visit_year.getText()+"��");
		 la_visit_month1 = new JLabel(jt_visit_month.getText()+"��");
		 la_visit_day1 = new JLabel(jt_visit_day.getText()+"��");
		 la_visit_time1 = new JLabel(String.valueOf(jc_visit_time.getSelectedItem()));
		 
		 la_title1.setFont(font);
		 la_hno.setFont(font);
		 la_visit_purpose.setFont(font);
		 la_symptom.setFont(font);
		 la_visit_date.setFont(font);
		 la_visit_time.setFont(font);
		 btn_reserveOK.setFont(font);
		 
		 title_panel.add(la_title1);
		 hno_panel.add(la_hno);				hno_panel.add(la_hno1);
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
		 reserveOKp.add(visitPurpose_panel);
		 reserveOKp.add(symptom_panel);
		 reserveOKp.add(visitDate_panel);
		 reserveOKp.add(visitTime_panel);
		 reserveOKp.add(btnReserve_panel);
		 
		 btn_reserveOK.setBackground(Color.LIGHT_GRAY);
		 title_panel.setBackground(Color.WHITE);
		 hno_panel.setBackground(Color.WHITE);
		 visitPurpose_panel.setBackground(Color.WHITE);
		 symptom_panel.setBackground(Color.WHITE);
		 visitDate_panel.setBackground(Color.WHITE);
		 visitTime_panel.setBackground(Color.WHITE);
		 btnReserve_panel.setBackground(Color.WHITE);
		 HospiResPane.setBackground(Color.WHITE);
		 
		
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
				}else {
					JOptionPane.showMessageDialog(null, "���� �̿Ϸ�");
				}
			}else if(btn_reset == obj) {
				jch_visit_p1.setSelected(false);
				jch_visit_p2.setSelected(false);
				jta_symptom.setText("");
				jt_visit_year.setText("");
				jt_visit_month.setText("");
				jt_visit_day.setText("");
				jc_visit_time.setSelectedItem("�������ּ���");
			}
			else if(btn_reserveOK == obj) {
				reserveOKf.setVisible(false);
			}
			
		}
		
	}//event class
	
	
}//class
