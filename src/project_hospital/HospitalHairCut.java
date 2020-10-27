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
	JTextField tf_year, tf_month, tf_day; //��¥
	JComboBox<String> cb_time; //�ð�
	JRadioButton rb_man, rb_woman; // ��, ��
	JTextArea ta_text; // ���ϰ������
	JButton btn_reserve, btn_reset; // ����, ��ҹ�ư
	
	public HospitalHairCut() {}
	public HospitalHairCut(HospitalMgmUI main) { // ���Կ� ������
		this.main = main; 
		this.SalonResPane = main.SalonResPane;
		
	}// ������

	public void hairCut() {
		main.switchPane(HospitalMgmUI.SALONRES);
		JPanel gender_pane = new JPanel();
		JPanel text_pane = new JPanel();
		JPanel jp_Button = new JPanel();
		JPanel date_pane = new JPanel();
		JPanel time_pane = new JPanel();
		
		JLabel lb_date= new JLabel("��¥ : ");
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
		
		JLabel lb_time = new JLabel("�ð� :");
		String[] arrtime = { "����", "10:00 ~ 11:00", "11:00 ~ 12:00", "12:00 ~ 13:00"
				, "13:00 ~ 14:00", "14:00 ~ 15:00", "15:00 ~ 16:00", "16:00 ~ 17:00", "17:00 ~ 18:00"};
		cb_time = new JComboBox<String>(arrtime);
		JPanel jp_time = new JPanel();
		jp_time.add(cb_time);
		time_pane.add(lb_time);
		time_pane.add(jp_time);

		JLabel lGender = new JLabel("���� :");
		JPanel pGender = new JPanel();
		rb_man = new JRadioButton("����", true);
		rb_woman = new JRadioButton("����", true);
		ButtonGroup group = new ButtonGroup();
		group.add(rb_man);
		group.add(rb_woman);
		pGender.add(rb_man);
		pGender.add(rb_woman);
		gender_pane.add(lGender);
		gender_pane.add(pGender);

		JLabel lb_text = new JLabel("��Ÿ ����:");
		ta_text = new JTextArea(3,20); // �� : ��
		JScrollPane pane = new JScrollPane(ta_text);
		text_pane.add(lb_text);
		text_pane.add(pane);

		btn_reserve = new JButton("����");
		btn_reset = new JButton("���");
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
			JOptionPane.showMessageDialog(null, "��¥�� �Է����ּ���","�˸�",JOptionPane.INFORMATION_MESSAGE);
			tf_year.requestFocus();
		}
		else if (cb_time.getSelectedItem().equals("����")) {
			JOptionPane.showMessageDialog(null, "�ð��� �������ּ���","�˸�",JOptionPane.INFORMATION_MESSAGE);
		} 
		else {
			if(!integerOrNot(date1) || !integerOrNot(date2) || !integerOrNot(date3)){
				JOptionPane.showMessageDialog(null, "��¥�� ���ڸ� �Է��� �� �����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
			}
			result = true;
		}

		return result;
	}// regFormCheck method
	
	public boolean integerOrNot(String strData){ // �Է°��� �������� �������� �Ǻ� : 
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
		// MemberVO ��ü�� �����Ͽ� ���
		UserVO vo = new UserVO();
		
		String time = (String)cb_time.getSelectedItem();
		String gender = "";
		if(rb_man.isSelected()){
			gender = "����";
		}else if(rb_woman.isSelected()){
			gender = "����";
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
		

		// MEMBER ���̺� ���
     	boolean result = system.hairCut(vo);

		if (result) {
			JOptionPane.showMessageDialog(null, "��� ����!!");
			JOptionPane.showMessageDialog(null,"��Ϲ�ȣ : " + vo.getSno() 
			+"\n"+"��¥ : " + vo.getSyear()+"��"+vo.getSmonth()+"��"+vo.getSday()+"��"
			+ "\n" + "�ð� : " + vo.getStime() 
			+ "\n" + "���� : " + vo.getSgender() 
			+ "\n" + "��Ÿ���� : " + vo.getStext() + "\n"
			,"���� Ȯ��",JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "��� ����!!");
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
				cb_time.setSelectedItem("����");
				
			}

		}

	}// event class

}// end