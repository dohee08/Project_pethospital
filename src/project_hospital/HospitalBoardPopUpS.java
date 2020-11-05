package project_hospital;


import java.awt.Color;
import java.awt.Rectangle;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


 
public class HospitalBoardPopUpS extends JFrame {
    HospitalMgmSystem system = new HospitalMgmSystem();
    HospitalMgmUI HMui;
    String mid;
    JTextField title;
    JTextArea textArea;
    JTextField writer;
    JRadioButton rb_hos;
    JRadioButton rb_sal;
    
    public HospitalBoardPopUpS() {
    	sendMessage(); 
    }
	public HospitalBoardPopUpS(HospitalMgmUI HMui) {
		this.HMui = HMui;
		this.mid = HMui.id;
	}
	
    public void sendMessage() {
        setBounds(new Rectangle(600, 0, 500, 280));
        setTitle("1:1문의등록");
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("원장님 선택");
        lblNewLabel_1.setBounds(12, 1, 150, 20);
        getContentPane().add(lblNewLabel_1);
        
        rb_hos = new JRadioButton("병원장", true);
        rb_sal = new JRadioButton("미용장", false);
        ButtonGroup group = new ButtonGroup();
        rb_hos.setBounds(81, 1, 72, 15);
        rb_sal.setBounds(150, 1, 100, 15);
        group.add(rb_hos);
        group.add(rb_sal);
        getContentPane().add(rb_hos);
        getContentPane().add(rb_sal);
        
        JLabel lblNewLabel_2 = new JLabel("글제목");
        lblNewLabel_2.setBounds(12, 25, 57, 15);
        getContentPane().add(lblNewLabel_2);
 
        title = new JTextField();
        title.setBounds(81, 22, 340, 21);
        getContentPane().add(title);
        title.setColumns(10);
 
        JLabel lblNewLabel_3 = new JLabel("글내용");
        lblNewLabel_3.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_3);
 
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBounds(81, 53, 340, 69);
        getContentPane().add(textArea);
 
        JLabel lblNewLabel_4 = new JLabel("작성자");
        lblNewLabel_4.setBounds(12, 140, 57, 15);
        getContentPane().add(lblNewLabel_4);
        
        writer = new JTextField(system.rename(mid));
        writer.setBounds(81, 137, 116, 21);
        getContentPane().add(writer);
        writer.setColumns(10);
 
        JButton btnWrite = new JButton("작성완료");
        btnWrite.setBackground(Color.WHITE);
        btnWrite.setBounds(81, 180, 116, 23);
        btnWrite.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
              if(sendFormCheck()) {
            	  sendProc();
              }
            }
        });
        getContentPane().add(btnWrite);
 
        JButton btnClose = new JButton("닫기");
        btnClose.setBackground(Color.WHITE);
        btnClose.setBounds(209, 180, 97, 23);
        btnClose.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
 
                setVisible(false);
 
            }
        });
        getContentPane().add(btnClose);
 
        setVisible(true);
 
    }
    
    public boolean sendFormCheck() {
		boolean result = false;

		if (title.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "제목을 입력해주세요", "알림", JOptionPane.INFORMATION_MESSAGE);
			title.requestFocus();
		} else if (textArea.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "내용을 입력해주세요", "알림", JOptionPane.INFORMATION_MESSAGE);
		} 
		else{
			result = true;
		}
		return result;
	}// regFormCheck method
    
    public void sendProc() {
		// MemberVO 객체를 생성하여 등록
    	UserVO vo = new UserVO();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s1 = new SimpleDateFormat("yy/mm/dd");
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String date = s1.format(cal.getTime());
        Random rd = new Random();
        String hosandsal = "";
         if (rb_hos.isSelected()) {
            hosandsal = "병원장";
        } else if (rb_sal.isSelected()) {
           hosandsal = "미용장";
        }             
         
         vo.setBid("S_" + rd.nextInt(10000));//bid
         vo.setBtitle(title.getText().trim());
         vo.setBtext(textArea.getText().trim());
         vo.setMid(mid);
         vo.setSid(system.getManagerId(hosandsal));
         vo.setBmname(writer.getText().trim());
         vo.setBdate(year+"/"+month+"/"+day);
         vo.setBsname(hosandsal);
         
         
         system.send(vo);
         JOptionPane.showMessageDialog(null, "등록완료");
         setVisible(false);

	}
    }