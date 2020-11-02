package project_hospital;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
 
public class HospitalBoardPopUpR extends JFrame {
 
    JTextField title;
    JTextField writer;
 
    public HospitalBoardPopUpR(UserVO vo) {
 
        setBounds(new Rectangle(600, 0, 450, 280));
        setTitle("게시글");
        getContentPane().setLayout(null);
 
        JLabel lblNewLabel = new JLabel("제목");
        lblNewLabel.setBounds(12, 25, 57, 15);
        getContentPane().add(lblNewLabel);
 
        title = new JTextField(vo.getAtitle());
        title.setBounds(81, 22, 340, 21);
        getContentPane().add(title);
        title.setColumns(10);
 
        JLabel lblNewLabel_1 = new JLabel("글내용");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_1);
 
        JTextArea textArea = new JTextArea(vo.getAtext());
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBounds(81, 53, 340, 69);
        getContentPane().add(textArea);
 
        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 140, 57, 15);
        getContentPane().add(lblNewLabel_2);
 
        writer = new JTextField(vo.getAsname());
        writer.setBounds(81, 137, 116, 21);
        getContentPane().add(writer);
        writer.setColumns(10);
 
       
        JButton btnClose = new JButton("닫기");
        btnClose.setBounds(299, 180, 97, 23);
        btnClose.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        getContentPane().add(btnClose);
        setVisible(true);
 
    }
}






