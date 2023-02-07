package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import database.LoginConnector;
import pages.Components.Pass;
import pages.Components.TextBox;
import pages.Styles.Colorlib;
import pages.Styles.RoundedBorder;

public class Login extends JFrame implements Colorlib{
	Login pg;
	FormField user;
	PassField pass;
	JLabel invalid;
    public Login(){
		pg=this;
		this.getContentPane().setBackground(new Color(0x101010));
		
        JPanel form1=new JPanel();
		form1.setLayout(new FlowLayout());
        form1.setPreferredSize(new Dimension(300,600));
        form1.setBackground(new Color(0x101010));
		form1.setBorder(new RoundedBorder(50,0x181818));
		
		JLabel Log=new JLabel();
		Log.setPreferredSize(new Dimension(300,110));
		Log.setText("Log in");
		Log.setFont(new Font("dialog",Font.BOLD,50));
		Log.setForeground(Color.white);
		Log.setHorizontalTextPosition(JLabel.CENTER);

		invalid=new JLabel();
		invalid.setText("");
		invalid.setForeground(new Color(red));
		invalid.setHorizontalAlignment(SwingConstants.CENTER);
		invalid.setPreferredSize(new Dimension(300,75));

		form1.add(Log);
		user=new FormField("Username","Enter the username");
		form1.add(user,FlowLayout.CENTER);
		pass=new PassField("Password","Enter the password");
		form1.add(pass);

		form1.add(invalid);

		JPanel buttonBox=new JPanel();

		JButton SignIn=new JButton();
		SignIn.setBackground(new Color(darkGreen));
		SignIn.setText("Log In");
		SignIn.setPreferredSize(new Dimension(200,50));
		SignIn.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
		SignIn.setForeground(Color.white);
		SignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag=false;
				String username=user.Tx.getText();
				try{
					LoginConnector c=new LoginConnector("person");
					username=user.Tx.getText();
					String password=pass.Tx.getText();
					while(c.rs.next()){
						if(username.equals(c.rs.getString("username")) && password.equals(c.rs.getString("password")) ){
							flag=true;
						}
					}
					c.close();
				}
				catch(SQLException err){
					System.out.println("error");
				}
				if(flag){
					pg.dispose();
					new MainWindow(username);
				}
				else{
					invalid.setText("Invalid username or password");
					user.Tx.setText("");
					pass.Tx.setText("");
					user.Tx.setBorder(new LineBorder(new Color(red),2));
					pass.Tx.setBorder(new LineBorder(new Color(red),2));
				}
			}
		});

		buttonBox.add(SignIn);

		buttonBox.setLayout(new GridBagLayout());
		buttonBox.setOpaque(false);
		buttonBox.setPreferredSize(new Dimension(300,250));
		form1.add(buttonBox);


		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(Box.createVerticalGlue());
        box.add(form1);     
        box.add(Box.createVerticalGlue());

		Box box2=new Box(BoxLayout.X_AXIS);
		box2.add(Box.createHorizontalGlue());
        box2.add(box);     
        box2.add(Box.createHorizontalGlue());

		this.setTitle("Login");
		this.add(box2);
		this.setResizable(false);
        this.setSize(550,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
        this.setVisible(true);

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if(invalid.getText().equals("Invalid username or password")){
					invalid.setText("");
					user.Tx.requestFocus();
					pass.Tx.requestFocus();
					pg.requestFocus();
				}
			}
			
		});
    }
}

class FormField extends JPanel{
	TextBox Tx;
	FormField(String La,String placeholder){
		setLayout(new BorderLayout(0,10));
		JLabel lb=new JLabel();
		lb.setText(La);
		lb.setFont(new Font("dialog",Font.PLAIN,15));
		lb.setForeground(Color.white);
		Tx = new TextBox(placeholder);
		
		setBackground(new Color(0x181818));
		add(lb,BorderLayout.NORTH);
		add(Tx,BorderLayout.SOUTH);
	}
}

class PassField extends JPanel implements Colorlib{
	Pass Tx;
	PassField(String La,String placeholder){
		setLayout(new BorderLayout(0,10));
		JLabel lb=new JLabel();
		lb.setText(La);
		lb.setFont(new Font("dialog",Font.PLAIN,15));
		lb.setForeground(Color.white);

		Tx=new Pass(placeholder);

		setBackground(new Color(0x181818));
		add(lb,BorderLayout.NORTH);
		add(Tx,BorderLayout.SOUTH);
	}
}
