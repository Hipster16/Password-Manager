package pages.Components;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pages.MainWindow;
import pages.Styles.Colorlib;
import pages.Styles.RoundedBorder;

public class PasswordBlock extends JPanel implements Colorlib {
    public JButton delete;
    public MainWindow parent;
    public PasswordBlock (int width,int height,MainWindow parent,ResultSet row) throws SQLException{
        this.parent=parent;
        setBorder(new RoundedBorder(20, lightGray));
        setOpaque(false);
        setPreferredSize(new Dimension(width,height));
        setLayout(new GridLayout(1,4));

        JLabel userpane=new JLabel(row.getString("username"));
        userpane.setForeground(Color.white);

        JLabel passpane=new JLabel(row.getString("password"));
        passpane.setForeground(Color.white);

        JLabel sitepane=new JLabel(row.getString("Site"));
        sitepane.setForeground(Color.white);
        
        JButton edit = new JButton();
        edit.setBackground(Color.green);
        edit.setPreferredSize(new Dimension(100,40));
        edit.setBorder(BorderFactory.createRaisedBevelBorder());
        edit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parent.frame.dispose();
                JFrame f=new JFrame();
                f.setSize(800,1100);
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setVisible(true);
            }
            
        });

        delete = new JButton();
        delete.setBackground(new Color(red));
        delete.setForeground(Color.white);
        delete.setPreferredSize(new Dimension(30,40));
        delete.setBorder(BorderFactory.createRaisedBevelBorder());

        JPanel buttonpane=new JPanel();
        buttonpane.setLayout(new FlowLayout());
        buttonpane.add(edit);
        buttonpane.add(Box.createHorizontalStrut(20));
        //buttonpane.add(delete);
        buttonpane.setOpaque(false);

        add(userpane);
        add(passpane);
        add(sitepane);
        add(buttonpane);
    }

}
