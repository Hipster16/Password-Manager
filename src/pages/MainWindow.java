package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.LineBorder;

import database.MainConnector;
import pages.Components.PasswordBlock;
import pages.Styles.Colorlib;
import pages.Styles.RoundedBorder;

public class MainWindow implements Colorlib {
    public JPanel centerpane;
    public JScrollPane centerbar;
    public JFrame frame;
    String tableName;
    MainWindow Mframe;
    private String user;
    public MainWindow(String username){
        user=username;
        Mframe=this;
        tableName=username;
        frame= new JFrame();
        frame.requestFocus();
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
        JButton addB=new JButton("Add");
        addB.setBackground(Color.green);
        addB.setForeground(Color.BLACK);
        addB.setFont(new Font("Dialog", Font.BOLD, 20));;
        addB.setPreferredSize(new Dimension(150,50));
        addB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddEdit a=new AddEdit("Add");
                a.submit.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String users=a.userTxt.getText();
                        String site=a.siteTxt.getText();
                        String pass=a.passTxt.getText();
                        try{
                            MainConnector obj=new MainConnector(username);
                            obj.rs=obj.statement.executeQuery(obj.selectAllFromtable());
                            boolean flag=true;
                            while(obj.rs.next()){
                                if(users.equals(obj.rs.getString("username")) && site.equals(obj.rs.getString("Site")) ){
                                    flag=false;
                                    break;
                                }
                            }
                            if(flag){
                                 obj.statement.executeUpdate(obj.insertInTable(users, pass, site));
                                 obj.rs=obj.statement.executeQuery("select * from '"+user+"' where username='"+users+"' and Site='"+site+"'");
                                 ((JPanel)centerbar.getViewport().getView()).add(new PasswordBlock(500, 75,Mframe,obj.rs,user));
                                 obj.rs=obj.statement.executeQuery(obj.getCount());
                                 int c=obj.rs.getInt("count(*)");
                                if(c<5){
                                    ((JPanel)centerbar.getViewport().getView()).setLayout(new GridLayout(5,1,0,10));
                                }
                                 else{
                                    ((JPanel)centerbar.getViewport().getView()).setLayout(new GridLayout(c,1,0,10));
                                }
                                Mframe.frame.setVisible(false);
                                Mframe.frame.setVisible(true);
                                a.frame.dispose();
                            }
                            else{
                                a.invalidLabel.setText("This account already exist");
					            a.userTxt.setText("");
					            a.passTxt.setText("");
                                a.siteTxt.setText("");
					            a.userTxt.setBorder(new LineBorder(new Color(red),2));
					            a.passTxt.setBorder(new LineBorder(new Color(red),2));
                                a.siteTxt.setBorder(new LineBorder(new Color(red),2));
                                a.frame.addMouseMotionListener(new MouseMotionListener() {

                                    @Override
                                    public void mouseDragged(MouseEvent e) {
                                        // TODO Auto-generated method stub
                                        
                                    }

                                    @Override
                                    public void mouseMoved(MouseEvent e) {
                                        if(a.invalidLabel.getText().equals("This account already exist")){
                                            a.invalidLabel.setText("");
                                            a.userTxt.requestFocus();
                                            a.passTxt.requestFocus();
                                            a.siteTxt.requestFocus();
                                            a.frame.requestFocus();
                                        }
                                        
                                    }
                                    
                                });
                            }
                            obj.close();
                        }
                        catch(Exception err){
                            System.out.println(err);
                        }
                    }
                    
                });
            }
            
        });


        addP.add(addB);
        addP.setOpaque(false);

        labelpanel.add(addP);        

        centerpane=new JPanel();
        centerpane.setOpaque(false);
        centerpane.setLayout(new BoxLayout(centerpane, BoxLayout.Y_AXIS));
        centerpane.add(labelpanel);
        centerpane.add(panecreator(5));
        centerpane.setBorder(null);

        frame.setTitle("Main Window");
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(30,5));
        frame.add(Box.createHorizontalStrut(30),BorderLayout.WEST);
        frame.add(centerpane,BorderLayout.CENTER);
        frame.add(Box.createHorizontalStrut(30),BorderLayout.EAST);
        frame.add(Topbar,BorderLayout.NORTH);
        frame.add(Box.createVerticalStrut(5),BorderLayout.SOUTH);
        frame.setMinimumSize(new Dimension(1100,970));
        frame.getContentPane().setBackground(new Color(0x101010));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int bounds[]={180,40,200,100};
                Popups f=new Popups("Logout or Exit", "Logout", "Exit",bounds);
                f.b2.setBackground(new Color(red));
                f.b2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                    
                });
                f.b.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        f.f.dispose();
                        new Login();
                    }
                    
                });
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
    public JScrollPane panecreator(int val){
        JPanel panel=new JPanel();
        panel.setBackground(new Color(0x101010));
        centerbar=new JScrollPane(panel);
        try{
        MainConnector obj= new MainConnector(tableName);
        obj.rs=obj.statement.executeQuery(obj.getCount());
        int c=obj.rs.getInt("count(*)");
        if(c<=5){
            ((JPanel)centerbar.getViewport().getView()).setLayout(new GridLayout(5,1,0,10));}
        else{
            ((JPanel)centerbar.getViewport().getView()).setLayout(new GridLayout(c,1,0,10));}
        obj.rs=obj.statement.executeQuery(obj.selectAllFromtable());
        centerbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        centerbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        centerbar.getViewport().setBackground(new Color(0x101010));
        centerbar.setLayout(new ScrollPaneLayout());
        centerbar.setBorder(null);
        
        while(obj.rs.next()){
            ((JPanel)centerbar.getViewport().getView()).add(new PasswordBlock(500, 75,this,obj.rs,user));
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
