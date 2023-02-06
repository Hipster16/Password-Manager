package pages.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pages.Styles.Colorlib;

public class Pass extends JPasswordField implements Colorlib{
    String placeHolder;
    public Pass(String placeHolder){
        this.placeHolder=placeHolder;
        setPreferredSize(new Dimension(300,50));
        setText(placeHolder);
        setBackground(new Color(lightGray));
        setForeground(Color.gray);
        setFont(new Font("Dialog", Font.PLAIN, 13));
        setBorder(new EmptyBorder(3,3,3,3));
        setCaretColor(Color.WHITE);
        setEchoChar((char)0);
        
        addFocusListener(new FocusListener(){

            
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(new LineBorder(Color.green, 2, true));
                if(getText().equals(placeHolder)){
                    setText("");
                    setEchoChar('*');
                    setFont(new Font("Dialog", Font.PLAIN, 20));
                    setForeground(Color.white);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(new EmptyBorder(3,3,3,3));
                if(getText().equals("")){
                    setText(placeHolder);
                    setEchoChar((char)0);
                    setFont(new Font("Dialog", Font.PLAIN, 13));
                    setForeground(Color.gray);
                }
            }
            
        });

    }   
}
