package project_hospital;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class HospitalManagerComPopUp extends JFrame {
 
    JTextField title;
    JTextField writer;
    
    Font font = new Font("¸¼Àº °íµñ", Font.BOLD, 12);
	Font font2 = new Font("¸¼Àº °íµñ", Font.BOLD, 10);
	Color c1 = new Color(255,231,159);
	Color c2 = new Color(238,217,154);
	Color c3 = new Color(229,197,148);
	
	Border lineBorder;
	Border emptyBorder;
 
    public HospitalManagerComPopUp(UserVO vo) {
 
        setBounds(new Rectangle(600, 0, 450, 280));
        setTitle("´äº¯¿Ï·á °Ô½Ã±Û");
        super.setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        lineBorder = BorderFactory.createLineBorder(Color.black, 1);
		emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
 
        JLabel lblNewLabel = new JLabel("Á¦¸ñ");
        lblNewLabel.setFont(font);
        lblNewLabel.setBounds(12, 25, 57, 15);
        getContentPane().add(lblNewLabel);
 
        title = new JTextField(vo.getAtitle());
        title.setBounds(81, 22, 340, 21);
        title.setEditable(false);
        title.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        title.setBackground(Color.WHITE);
        getContentPane().add(title);
        title.setColumns(10);
 
        JLabel lblNewLabel_1 = new JLabel("±Û³»¿ë");
        lblNewLabel_1.setFont(font);
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_1);
 
        JTextArea textArea = new JTextArea(vo.getAtext());
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBounds(81, 53, 340, 69);
        textArea.setEditable(false);
        
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
		textArea.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        textArea.setBackground(Color.WHITE);
        getContentPane().add(textArea);
 
        JLabel lblNewLabel_2 = new JLabel("ÀÛ¼ºÀÚ");
        lblNewLabel_2.setFont(font);
        lblNewLabel_2.setBounds(12, 140, 57, 15);
        getContentPane().add(lblNewLabel_2);
 
        writer = new JTextField(vo.getAsname());
        writer.setBounds(81, 137, 116, 21);
        writer.setEditable(false);
        writer.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        writer.setBackground(Color.WHITE);
        getContentPane().add(writer);
        writer.setColumns(10);
 
       
        JButton btnClose = new JButton("´Ý±â");
        btnClose.setFont(font);
        btnClose.setBackground(c2);
        btnClose.setBounds(299, 180, 97, 23);
        btnClose.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        getContentPane().add(btnClose);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
 
    }
}
