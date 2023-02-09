package pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import pages.Components.TextBox;
import pages.Styles.Colorlib;
import pages.Styles.RoundedBorder;

public class AddEdit implements Colorlib {
    TextBox userTxt,passTxt,siteTxt;
    JButton submit;
    JLabel invalidLabel;
    JFrame frame;
    public AddEdit(String heading){
        frame=new JFrame();

        JPanel mainP=new JPanel();
        mainP.setOpaque(false);
        mainP.setPreferredSize(new Dimension(550,550));
        mainP.setBorder(new RoundedBorder(50, gray));
        mainP.setLayout(null);

        JLabel Header=new JLabel(heading);
        Header.setBounds(220,20,200,100);
        Header.setFont(new Font("Dialog", Font.BOLD, 40));
        Header.setForeground(Color.white);
        mainP.add(Header);

        JLabel ulabel=new JLabel("Username");
        ulabel.setBounds(70,110,100,100);
        ulabel.setFont(new Font("Dialog", Font.BOLD, 20));
        ulabel.setForeground(Color.white);
        mainP.add(ulabel);

        JLabel plabel=new JLabel("Password");
        plabel.setBounds(70,220,100,100);
        plabel.setFont(new Font("Dialog", Font.BOLD, 20));
        plabel.setForeground(Color.white);
        mainP.add(plabel);

        JLabel slabel=new JLabel("Site Name");
        slabel.setBounds(70,330,100,100);
        slabel.setFont(new Font("Dialog", Font.BOLD, 20));
        slabel.setForeground(Color.white);
        mainP.add(slabel);

        invalidLabel=new JLabel("");
        invalidLabel.setBounds(175,420,300,50);
        invalidLabel.setForeground(new Color(red));
        invalidLabel.setOpaque(false);
        mainP.add(invalidLabel);

        userTxt=new TextBox("Enter the username");
        userTxt.setBounds(200,135,300,50);
        mainP.add(userTxt);

        passTxt=new TextBox("Enter the password");
        passTxt.setBounds(200,250,300,50);
        mainP.add(passTxt);

        siteTxt=new TextBox("Enter the siteName");
        siteTxt.setBounds(200,355,300,50);
        mainP.add(siteTxt);

        submit=new JButton("Submit");
        submit.setBackground(Color.green);
        submit.setForeground(Color.black);
        submit.setFont(new Font("Dialog", Font.BOLD, 20));
        submit.setBounds(175,480,200,50);
        submit.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
        mainP.add(submit);

        frame.add(mainP);
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(darkGray));
        frame.addWindowListener(new WindowListener() {

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
        frame.setVisible(true);
    }
}
