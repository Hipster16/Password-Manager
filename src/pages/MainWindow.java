package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import database.MainConnector;
import pages.Components.PasswordBlock;
import pages.Styles.Colorlib;
import pages.Styles.RoundedBorder;

public class MainWindow implements Colorlib {
    public JPanel centerpane;
    public JFrame frame;
    String tableName;
    public MainWindow(String username){
        tableName=username;
        frame= new JFrame();
        frame.setSize(1100,800);

        JPanel Topbar=new JPanel();
        Topbar.setBackground(new Color(darkGreen));
        Topbar.setPreferredSize(new Dimension(660,frame.getHeight()/4));

        JPanel labelpanel=new JPanel();
        labelpanel.setBorder(new RoundedBorder(20, 0x000000));
        labelpanel.setOpaque(false);
        labelpanel.setLayout(new GridLayout(1,4));
        labelpanel.add(new simplelabel("Username"));
        labelpanel.add(new simplelabel("Password"));
        labelpanel.add(new simplelabel("Site name"));

        JPanel addP=new JPanel();
        JButton addB=new JButton();
        addB.setPreferredSize(new Dimension(150,50));
        addP.add(addB);
        addP.setOpaque(false);

        labelpanel.add(addP);        

        centerpane=new JPanel();
        centerpane.setOpaque(false);
        centerpane.setLayout(new BoxLayout(centerpane, BoxLayout.Y_AXIS));
        centerpane.add(labelpanel);
        centerpane.add(panecreator(5));

        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(30,5));
        frame.add(Box.createHorizontalStrut(30),BorderLayout.WEST);
        frame.add(centerpane,BorderLayout.CENTER);
        frame.add(Box.createHorizontalStrut(30),BorderLayout.EAST);
        frame.add(Topbar,BorderLayout.NORTH);
        frame.add(Box.createVerticalStrut(5),BorderLayout.SOUTH);
        frame.setMinimumSize(new Dimension(1100,800));
        frame.getContentPane().setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public JScrollPane panecreator(int val){
        JPanel panel=new JPanel();
        panel.setBackground(Color.black);
        JScrollPane centerbar=new JScrollPane(panel);
        try{
        MainConnector obj= new MainConnector(tableName);
        obj.rs=obj.statement.executeQuery(obj.selectAllFromtable());
        centerbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        centerbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        centerbar.getViewport().setBackground(new Color(darkGray));
        centerbar.setLayout(new ScrollPaneLayout());
        centerbar.setBorder(null);
        ((JPanel)centerbar.getViewport().getView()).setLayout(new GridLayout(5,1,0,10));
        
        while(obj.rs.next()){
            ((JPanel)centerbar.getViewport().getView()).add(new PasswordBlock(500, 75,this,obj.rs));
        }
        obj.close();
    }
    catch(Exception e){
        System.out.println("err");
    }
    finally{
        return centerbar;
    }

    } 
}

class simplelabel extends JLabel implements Colorlib{
    simplelabel(String content){
        setText(content);
        setForeground(Color.WHITE);
        setOpaque(false);
        setFont(new Font("Dialog", Font.PLAIN, 20));
    }
}
