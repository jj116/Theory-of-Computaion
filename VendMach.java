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
 JPanel p;
 JFrame frame;
 JButton coke;
 int total;
 int tens=0;
 int fives=0;
 JTextField ten,five;
    public VendMach (){
        
     state  = new JLabel("State");
    //    JTextField textstate = new JTextField(20);
         p = new JPanel(new GridBagLayout());
        JButton buy =  new JButton("Buy");
        JLabel  qua = new JLabel("Quantity : "+quantity);
        JLabel  pri = new JLabel("Quantity : "+quantity);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        JButton pepsi = new JButton("Pepsi");
         coke = new JButton("Coke");
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
    frame = new JFrame("Vending Machine");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(p);
    frame.setSize(500,300);
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
          paymoney();
            }
        });
      
      
      
      
    }
    
    public void paymoney ()
    {
          p.setVisible(false);
               
              JPanel mon = new JPanel(new GridBagLayout());
              JLabel cost = new JLabel("Cost Rs."+ price);
              JLabel tenl = new JLabel("Ten");
              JLabel fivel = new JLabel("Five");
              JButton ok = new JButton("Ok");
              ten = new JTextField(3);
              five = new JTextField(3);
              
              state.setText("State : No Money state  ");
              
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;   
          mon.add(cost,c);
         c.gridx = 0;
         c.gridy = 3;
           mon.add(state,c);    
         c.gridx = 1;
         c.gridy = 3;  
            mon.add(ok,c);
        c.gridx = 0;
        c.gridy = 1;
           mon.add(tenl,c);
        c.gridx = 1;
        c.gridy = 1;
           mon.add(ten,c);
        c.gridx = 2;
        c.gridy = 1;
           mon.add(fivel,c);
        c.gridx = 3;
        c.gridy = 1;
           mon.add(five,c);
           
          
           
           
           JLabel monent = new JLabel("Money Entered "+ total);
           c.gridx = 0;
           c.gridy = 2;
           mon.add(monent);
           
           ok.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
           if(ten != null || five != null){
                if(ten.getText().equals("")){
         
                }
                else
                {
                   tens = Integer.parseInt(ten.getText());   
                }
                 if(five.getText().equals("")){
          
                 }
                 else
                 {
                      fives = Integer.parseInt(five.getText());
                 }
           
           total = tens*10 + fives*5;
           
            monent.setText("Money Entered "+ total);
            state.setText("State : Money Entered State");
            
                    if(total == price)
                    {
                         JOptionPane.showMessageDialog(frame.getComponent(0), "Collect your items");
                         new VendMach().setVisible(true);
                    }
                    else if(total<price)
                    {   int diff = price-total;
                         JOptionPane.showMessageDialog(frame.getComponent(0),"Add Rs."+diff);
                    }
           }
           
                
              }
          });
                    
              frame.add(mon);
              
              validate();
              setVisible(true);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
            
      new VendMach().setVisible(true);
      
      
      
      
      
    }
    
    
}
