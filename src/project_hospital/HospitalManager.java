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
		
		Font font = new Font("���� ���", Font.BOLD, 12);
		jf = new JFrame("������ ȭ��");	
		
		main_panel = new JPanel();
	    button_panel = new JPanel();
	    
	    button_panel = new JPanel(new GridLayout(2,1));
	    button_panel.setSize(50,100);
	    
	    //������ UI ��ư ����
	    btnList = new JButton("����Ʈ");
		btnDelete = new JButton("���� ����");
		btnList.setFont(font);  btnDelete.setFont(font);	
		
		button_panel.add(btnList);
		button_panel.add(btnDelete);
		
		//���� UI â ��ġ ����
	    jf.add(BorderLayout.WEST, button_panel);      
	    jf.add(BorderLayout.CENTER, main_panel); //- ������ ���� ���� ����
	    
//	    jf.add(BorderLayout.CENTER, insert_panel);
	    
	    jf.setSize(600,500);
		jf.setVisible(true);
		
		//������ UI ��ư �̺�Ʈ ����
		btnList.addActionListener(new JFrameObjectEvent(this));
		btnDelete.addActionListener(new JFrameObjectEvent(this));
		jf.addWindowListener(new JFrameObjectEvent(this));
		
	}
	//�̺�Ʈ ó�� Ŭ����
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
		//������ �̺�Ʈ ó��
		public void windowClosing(WindowEvent we) {
			//������ â ����
			jf.setVisible(false);
		}
			
		//�׼� �̺�Ʈ ó��
		public void actionPerformed(ActionEvent e) {
			String bname = e.getActionCommand();

			if(bname.equals("����Ʈ")) {	
				//����Ʈ ���
				JOptionPane.showMessageDialog(null, "����Ʈ");			
			}else if(bname.equals("���� ����")){
				// ���� ���� ���
				JOptionPane.showMessageDialog(null, "���� ����");		
			}else if(bname.equals("����")) {
				System.out.println("--  ���� ��ư�� Ŭ���Ǿ����ϴ�. --");
				System.exit(0);
			}
		}
			
	}
	
}
	