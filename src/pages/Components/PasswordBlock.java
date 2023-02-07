package pages.Components;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

import database.MainConnector;
import pages.MainWindow;
import pages.Styles.Colorlib;
import pages.Styles.RoundedBorder;

public class PasswordBlock extends JPanel implements Colorlib {
    public JButton delete;
    public MainWindow parent;
    public PasswordBlock block;
    public ResultSet row;
    public String user,field1,field2,field3;
    public PasswordBlock (int width,int height,MainWindow parent,ResultSet row,String user) throws SQLException{
        this.parent=parent;
        this.block=this;
        this.row=row;
        this.user=user;
        field1=row.getString("username");
        field2=row.getString("password");
        field3=row.getString("Site");
        setBorder(new RoundedBorder(20, lightGray));
        setOpaque(false);
        setPreferredSize(new Dimension(width,height));
        setLayout(new GridLayout(1,4));

        JLabel userpane=new JLabel(field1);
        userpane.setForeground(Color.white);

        JLabel passpane=new JLabel(row.getString("password"));
        passpane.setForeground(Color.white);

        JLabel sitepane=new JLabel(field2);
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

        delete = new JButton("X");
        delete.setBackground(new Color(red));
        delete.setForeground(Color.white);
        delete.setPreferredSize(new Dimension(30,40));
        delete.setBorder(BorderFactory.createRaisedBevelBorder());
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f=new JFrame();
                JLabel msg=new JLabel("Are You You want to delete the password");
                msg.setBounds(60, 20, 400, 100);
                msg.setForeground(Color.white);
                msg.setFont(new Font("Dialog",Font.PLAIN,20));
                JButton b=new JButton("delete");
                b.setBounds(100,150,100,50);
                b.setBackground(new Color(red));
                b.setForeground(Color.white);
                b.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((JPanel)parent.centerbar.getViewport().getView()).remove(block);
                        try{
                            MainConnector con=new MainConnector(user);
                            con.statement.executeUpdate(con.deleteFromTable(field1,field2,field3));
                            con.rs=con.statement.executeQuery(con.getCount());
                            int c=con.rs.getInt("count(*)");
                            if(c<=5){
                                ((JPanel)parent.centerbar.getViewport().getView()).setLayout(new GridLayout(5,1,0,10));
                            }
                            else{
                                ((JPanel)parent.centerbar.getViewport().getView()).setLayout(new GridLayout(c,1,0,10));
                            }
                            con.close();
                        }
                        catch(Exception erors){
                            System.out.println(erors);
                        }
                        parent.frame.setVisible(false);
                        parent.frame.setVisible(true);
                        f.dispose();
                    }
                    
                });

                JButton b2=new JButton("Go back");
                b2.setBounds(300, 150, 100, 50);
                b2.setBackground(Color.GREEN);
                b2.setForeground(Color.white);
                b2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.dispose();
                        
                    }
                    
                });

                f.add(msg);
                f.add(b);
                f.add(b2);
                f.setLayout(null);
                f.getContentPane().setBackground(new Color(darkGray));
                f.setResizable(false);
                f.setSize(500,300);
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                f.setVisible(true);
            }
            
        });

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
