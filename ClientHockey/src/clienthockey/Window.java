/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienthockey;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author johan
 */
public class Window {

    // TODO code application logic here
    public Window() {
        final JTextArea text_area = new JTextArea();
        JFrame fenetre = new JFrame();
        fenetre.setVisible(true);
        fenetre.setSize(600,600);
        fenetre.getContentPane().add(text_area, BorderLayout.NORTH);
        JButton b_liste = new JButton("Recuperer liste");
        fenetre.getContentPane().add(b_liste, BorderLayout.SOUTH);
        b_liste.addActionListener(new ActionListener(){
        
           
            public void actionPerformed(ActionEvent e){
                
                
            }
        
        });
        
        JButton b_score = new JButton("Recuperer score");
        fenetre.getContentPane().add(b_score, BorderLayout.CENTER);        
        b_score.addActionListener(new ActionListener(){
        
           
            public void actionPerformed(ActionEvent f){
                
                
            }
        
        });
        text_area.getText();    
        
    }
}
