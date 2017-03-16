/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendmach;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lenovo
 */
public class VendMach extends JFrame  {
 JLabel state;
 int quantity=0;
 int price;
    public VendMach (){
        
     state  = new JLabel("State");
    //    JTextField textstate = new JTextField(20);
        JPanel p = new JPanel(new GridBagLayout());
        JButton buy =  new JButton("Buy");
        JLabel  qua = new JLabel("Quantity : "+quantity);
        JLabel  pri = new JLabel("Quantity : "+quantity);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        JButton pepsi = new JButton("Pepsi");
        JButton coke = new JButton("Coke");
        JButton chips = new JButton("Chips");
        
        constraints.gridx = 0;
        constraints.gridy = 0;   
          p.add(pepsi,constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;     
          p.add(coke,constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;    
          p.add(chips,constraints);
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;    
          p.add(state,constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;    
          p.add(qua,constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;    
          p.add(pri,constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;    
          p.add(buy,constraints);
      
          
          state.setText("State : No Item Selected State");
    JFrame frame = new JFrame("Vending Machine");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(p);
    frame.setSize(300, 200);
    frame.setVisible(true);
    
    
    coke.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
       price = price + 35;
        quantity = quantity + 1;
         qua.setText("Quantity : "+quantity);
         pri.setText("Price : "+price);
        JOptionPane.showMessageDialog(frame.getComponent(0), "Price is Rs. 35");
        state.setText("State : Item Selected State");
       
       
             }
         });
    
     pepsi.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
        quantity = quantity + 1;
        price = price + 30;
        qua.setText("Quantity : "+quantity);
        pri.setText("Price : "+price);
        JOptionPane.showMessageDialog(frame.getComponent(0), "Price is Rs. 30");
        state.setText("State : Item Selected State");
 
             }
         });
     
      chips.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
        quantity = quantity + 1;
        price = price + 20;
        qua.setText("Quantity : "+quantity);
        pri.setText("Price : "+price);
        JOptionPane.showMessageDialog(frame.getComponent(0), "Price is Rs. 20");
       state.setText("State : Item Selected State");
             }
         });
      
      buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            p.setVisible(false);
                
              JPanel mon = new JPanel(new GridBagLayout());
              frame.add(mon);
              mon.add(coke);
              validate();
              setVisible(true);
            }
        });
      
      
      
      
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
            
      new VendMach().setVisible(true);
      
      
      
      
      
    }
    
    
}
