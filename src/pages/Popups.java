package pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import pages.Styles.Colorlib;

public class Popups implements Colorlib {
    public JButton b,b2;
    public JFrame f;
    public JLabel msg;
    public Popups(String msgs,String bText,String b2Text,int bounds[]){
        f=new JFrame();
        msg=new JLabel(msgs);
        msg.setBounds(bounds[0],bounds[1],bounds[2],bounds[3]);
        msg.setForeground(Color.white);
        msg.setFont(new Font("Dialog",Font.BOLD,20));
        b=new JButton(bText);
        b.setBounds(100,150,100,50);
        b.setBackground(new Color(red));
        b.setForeground(Color.white);

        b2=new JButton(b2Text);
        b2.setBounds(300, 150, 100, 50);
        b2.setBackground(new Color(lightGray));
        b2.setForeground(Color.white);

        f.add(msg);
        f.add(b);
        f.add(b2);
        f.setLayout(null);
        f.getContentPane().setBackground(new Color(darkGray));
        
        f.setResizable(false);
        f.setSize(500,300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });
    }
}