package pages.Components;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pages.Styles.Colorlib;
import pages.Styles.RoundedBorder;

public class PasswordBlock extends JPanel implements Colorlib {
    public PasswordBlock (int width,int height){
        setBorder(new RoundedBorder(20, lightGray));
        setOpaque(false);
        setPreferredSize(new Dimension(width,height));
        setLayout(new GridLayout(1,4));

        JLabel userpane=new JLabel("user space");
        userpane.setForeground(Color.white);

        JLabel passpane=new JLabel("password space");
        passpane.setForeground(Color.white);

        JLabel sitepane=new JLabel("sitespace space");
        sitepane.setForeground(Color.white);
        
        JButton edit = new JButton();
        edit.setBackground(Color.green);
        edit.setPreferredSize(new Dimension(30,40));
        edit.setBorder(BorderFactory.createRaisedBevelBorder());

        JButton delete = new JButton();
        delete.setBackground(new Color(red));
        delete.setForeground(Color.white);
        delete.setPreferredSize(new Dimension(30,40));
        delete.setBorder(BorderFactory.createRaisedBevelBorder());

        JPanel buttonpane=new JPanel();
        buttonpane.setLayout(new FlowLayout());
        buttonpane.add(edit);
        buttonpane.add(Box.createHorizontalStrut(20));
        buttonpane.add(delete);
        buttonpane.setOpaque(false);

        add(userpane);
        add(passpane);
        add(sitepane);
        add(buttonpane);
    }

}
