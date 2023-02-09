package pages.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import database.MainConnector;
import pages.AddEdit;
import pages.MainWindow;
import pages.Popups;
import pages.Styles.Colorlib;
import pages.Styles.RoundedBorder;

public class PasswordBlock extends JPanel implements Colorlib {
    public JButton delete;
    public MainWindow parent;
    public PasswordBlock block;
    public ResultSet row;
    public String user, field1, field2, field3;

    public PasswordBlock(int width, int height, MainWindow parent, ResultSet row, String user) throws SQLException {
        this.parent = parent;
        this.block = this;
        this.row = row;
        this.user = user;
        field1 = row.getString("username");
        field2 = row.getString("password");
        field3 = row.getString("Site");
        setBorder(new RoundedBorder(20, lightGray));
        setOpaque(false);
        setPreferredSize(new Dimension(width, height));
        setLayout(new GridLayout(1, 4));

        JLabel userpane = new JLabel(field1);
        userpane.setForeground(Color.white);

        JLabel passpane = new JLabel(field2);
        passpane.setForeground(Color.white);

        JLabel sitepane = new JLabel(field3);
        sitepane.setForeground(Color.white);

        JButton edit = new JButton("edit");
        edit.setForeground(Color.black);
        edit.setFont(new Font("Dialog", Font.BOLD, 15));
        edit.setBackground(Color.green);
        edit.setPreferredSize(new Dimension(100, 30));
        edit.setBorder(BorderFactory.createRaisedBevelBorder());
        edit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddEdit a = new AddEdit("Edit");
                a.userTxt.setText(field1);
                a.userTxt.setFont(new Font("Dialog", Font.PLAIN, 17));
                a.userTxt.setForeground(Color.white);
                a.passTxt.setText(field2);
                a.passTxt.setFont(new Font("Dialog", Font.PLAIN, 17));
                a.passTxt.setForeground(Color.white);
                a.siteTxt.setText(field3);
                a.siteTxt.setFont(new Font("Dialog", Font.PLAIN, 17));
                a.siteTxt.setForeground(Color.white);
                a.submit.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String users = a.userTxt.getText();
                        String site = a.siteTxt.getText();
                        String pass = a.passTxt.getText();
                        try {
                            MainConnector obj = new MainConnector(parent.user);
                            boolean flag = true;
                            if (((users.equals("Enter the username") || site.equalsIgnoreCase("Enter the password") ||
                                    site.equals("Enter the siteName")))) {
                                a.invalidLabel.setText("Fill the form properly");
                                flag = false;
                            } else if (((users.equals("") || site.equalsIgnoreCase("") ||
                                    site.equals("")))) {
                                a.invalidLabel.setText("Fill the form properly");
                                flag = false;
                            }
                            if (flag) {
                                String userpaneTxt = userpane.getText();
                                String sitepaneTxt = sitepane.getText();
                                obj.statement
                                        .executeUpdate(obj.updateTable(users, pass, site, userpaneTxt, sitepaneTxt));
                                field1 = users;
                                field2 = pass;
                                field3 = site;
                                userpane.setText(users);
                                passpane.setText(pass);
                                sitepane.setText(site);
                                parent.frame.setVisible(false);
                                parent.frame.setVisible(true);
                                a.frame.dispose();
                            } else {
                                a.userTxt.setText("");
                                a.passTxt.setText("");
                                a.siteTxt.setText("");
                                a.userTxt.setBorder(new LineBorder(new Color(red), 2));
                                a.passTxt.setBorder(new LineBorder(new Color(red), 2));
                                a.siteTxt.setBorder(new LineBorder(new Color(red), 2));
                                a.frame.addMouseMotionListener(new MouseMotionListener() {

                                    @Override
                                    public void mouseDragged(MouseEvent e) {
                                        // TODO Auto-generated method stub

                                    }

                                    @Override
                                    public void mouseMoved(MouseEvent e) {
                                        if (a.invalidLabel.getText().equals("This account already exist")
                                                || a.invalidLabel.getText().equals("Fill the form properly")) {
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
                        } catch (Exception err) {
                            System.out.println(err);
                        }

                    }

                });
            }

        });

        delete = new JButton("X");
        delete.setBackground(new Color(red));
        delete.setForeground(Color.white);
        delete.setPreferredSize(new Dimension(30, 30));
        delete.setBorder(BorderFactory.createRaisedBevelBorder());
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int Bounds[] = { 60, 20, 400, 100 };
                Popups f = new Popups("Are You You want to delete this password", "Delete", "Go Back", Bounds);
                f.b.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((JPanel) parent.centerbar.getViewport().getView()).remove(block);
                        try {
                            MainConnector con = new MainConnector(user);
                            con.statement.executeUpdate(con.deleteFromTable(field1, field2, field3));
                            con.rs = con.statement.executeQuery(con.getCount());
                            int c = con.rs.getInt("count(*)");
                            if (c < 5) {
                                ((JPanel) parent.centerbar.getViewport().getView())
                                        .setLayout(new GridLayout(5, 1, 0, 10));
                            } else {
                                ((JPanel) parent.centerbar.getViewport().getView())
                                        .setLayout(new GridLayout(c, 1, 0, 10));
                            }
                            con.close();
                        } catch (Exception erors) {
                            System.out.println(erors);
                        }
                        parent.frame.setVisible(false);
                        parent.frame.setVisible(true);
                        f.f.dispose();
                    }

                });

                f.b2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.f.dispose();

                    }

                });
            }

        });

        JPanel buttonpane = new JPanel();
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
