package pages.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pages.Styles.Colorlib;

public class TextBox extends JTextField implements Colorlib{
    public TextBox(String placeHolder){
        setPreferredSize(new Dimension(300,50));
        setText(placeHolder);
        setForeground(Color.gray);
        setFont(new Font("Dialog", Font.PLAIN, 13));
        setBackground(new Color(lightGray));
        setBorder(new EmptyBorder(3,3,3,3));
        setCaretColor(Color.WHITE);
        addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(new LineBorder(Color.green, 2, true));
                if(getText().equals(placeHolder)){
                    setText("");
                    setFont(new Font("Dialog", Font.PLAIN, 17));
                    setForeground(Color.white);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(new EmptyBorder(3,3,3,3));
                if(getText().equals("")){
                    setText(placeHolder);
                    setFont(new Font("Dialog", Font.PLAIN, 13));
                    setForeground(Color.gray);
                }
            }
            
        });
    }   
}
