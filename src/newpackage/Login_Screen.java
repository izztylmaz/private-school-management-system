/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author İzzet Yılmaz
 */
public class Login_Screen extends javax.swing.JFrame {
    Set <String> authority = new LinkedHashSet<String>();
    //String selectALL="SELECT * FROM ";

    /**
     * Creates new form Login_Screen
     */
    public Login_Screen() {
        initComponents();
    }
    
    public void logintoDB(){
        int auth=0;
        String query;
        if(studentRB.isSelected()){
            auth = 1;
        }
        else if(staffRB.isSelected()){
            auth = 2;
        }
        if(auth==0){
            JOptionPane.showMessageDialog(this, "PLEASE SELECT YOUR POSITION!");
        }
        else{
            Connect cn = new Connect();
            Connection con=cn.ConnectDB();
            Statement statement = null;
            int control=0;
            String uname = username.getText();
            //int uname = Integer.valueOf(this.username.getText());
            String pword = new String(password.getPassword());
            if(auth==2){
                query= "SELECT staff_id, password,role_id,name FROM staff";
            }
            else{
                query= "SELECT student_id, password, name FROM student";
            }
            String message = "Invalid Username or Password! ";//initial defination
            
            try {
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery(query);

                while(rs.next()){
                    String id;
                    int roleID = 0;
                    if(auth==1){
                        id = rs.getString("student_id");
                    }
                    else{
                        id = rs.getString("staff_id");
                        roleID = rs.getInt("role_id");
                    }
                    String name = rs.getString("name");
                    String pass = rs.getString("password");
                    //System.out.println(id+" "+pass);
                    //System.out.println(uname+" "+pword);
                    if( (uname.equals(id)) && (pword.equals(pass)) ){
                        //System.out.println("Aferin dogru");
                        control = 1;
                        message="Welcome! " + name;
                        if(auth==1){
                            new studentInterface(Integer.valueOf(id)).setVisible(true);
                        }
                        else{
                            if(roleID==1){
                                managerInterface yeni=new managerInterface();
                                yeni.setVisible(true);
                            }
                            else{
                                /*instructorInterface yeni=new instructorInterface();
                                yeni.setVisible(true);*/
                                new instructorInterface(Integer.valueOf(id)).setVisible(true);
                            }
                        }
                        cn.DisconnectDB(con);
                        //con=cn.DisconnectDB();
                        //cn=Connect.DisconnectDB(cn);
                        this.setVisible(false);
                        /*studentInterface yeni=new studentInterface();
                        yeni.setVisible(true);
                        this.setVisible(false);*/
                        //yeni.parent=this;
                        break;
                    }
                }

                if(control==0){
                    message = message + "Try Again!";
                }

                JOptionPane.showMessageDialog(this, message);

                username.setText("");
                password.setText("");

                /*con = cn.DisconnectDB(con);
                System.out.println(con);*/
            } catch (SQLException ex) {
                Logger.getLogger(Login_Screen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Login_Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        login = new javax.swing.JButton();
        studentRB = new javax.swing.JRadioButton();
        staffRB = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
        });

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setText("Username:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setText("Password:");

        login.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        login.setText("LOGIN");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        buttonGroup1.add(studentRB);
        studentRB.setText("Student");
        studentRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentRBActionPerformed(evt);
            }
        });

        buttonGroup1.add(staffRB);
        staffRB.setText("Staff");
        staffRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffRBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(studentRB, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(staffRB)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(username)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(171, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentRB)
                    .addComponent(staffRB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_passwordActionPerformed

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_loginMouseClicked

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        logintoDB();
    }//GEN-LAST:event_loginActionPerformed

    private void staffRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffRBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staffRBActionPerformed

    private void studentRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentRBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentRBActionPerformed

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            logintoDB();
        }
    }//GEN-LAST:event_usernameKeyPressed

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            logintoDB();
        }
    }//GEN-LAST:event_passwordKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login_Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_Screen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JRadioButton staffRB;
    private javax.swing.JRadioButton studentRB;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}