/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment Two - Hotel Booking System
 *
 */
package bedam;

import javax.swing.JOptionPane;

/**
 *
 * @author benr0
 */
public class CancelBookingJFrame extends javax.swing.JFrame { //Class is used to cancel bookings

    /**
     * Creates new form CancelBookingJFrame
     */
    public CancelBookingJFrame() {
        initComponents(); //Initialises components from JSwing
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        cancelTitle = new javax.swing.JLabel();
        infoLabel = new javax.swing.JLabel();
        bookingNumTextField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        titleLabel.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(172, 140, 71));
        titleLabel.setText("About Us");
        titleLabel.setRequestFocusEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cancelTitle.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        cancelTitle.setForeground(new java.awt.Color(172, 140, 71));
        cancelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cancelTitle.setText("Cancel Booking");
        cancelTitle.setRequestFocusEnabled(false);

        infoLabel.setText("Enter your booking number below:");

        bookingNumTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingNumTextFieldActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel Booking");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(356, Short.MAX_VALUE)
                .addComponent(infoLabel)
                .addGap(357, 357, 357))
            .addComponent(cancelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(383, 383, 383)
                        .addComponent(bookingNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(cancelButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(cancelTitle)
                .addGap(64, 64, 64)
                .addComponent(infoLabel)
                .addGap(36, 36, 36)
                .addComponent(bookingNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(backButton)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bookingNumTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingNumTextFieldActionPerformed
        // TODO add your handling code here:            Users will type a number into this text field
    }//GEN-LAST:event_bookingNumTextFieldActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        BedamJFrame.main(null); //Goes back to main menu and will dispose this frame.
        CancelBookingJFrame.super.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        String bNumString = bookingNumTextField.getText(); //Gets the value inside of the TextField
        try {
            int bookingNum = Integer.parseInt(bNumString); //Tries to parse the string to an Int
            if (DatabaseBedam.checkBookingNumInDB(bookingNum)) { //Checks if the booking number provided is a booking number currently in the database
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this booking?\nPlease note there are no refunds.", "Cancel Booking", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    DatabaseBedam.removeBookingFromDB(bookingNum); //If they click yes to the confirmation button. This will remove the booking number and the rest of that primary key's details from the database
                    JOptionPane.showMessageDialog(null, "Booking cancelled!", "Booking cancelled", JOptionPane.INFORMATION_MESSAGE); //Confirmation of cancellation
                    
                        backButtonActionPerformed(null);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Order number doesn't exist!"); //If the TextField can be parsed as an Int but that value isn't in the database.
            }
        } catch (NumberFormatException e) {
          //  System.out.println("err");
            JOptionPane.showMessageDialog(null, "Invalid input"); //TextField isn't an integer
            bookingNumTextField.setText(""); //Resets the textField to blank
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CancelBookingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CancelBookingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CancelBookingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CancelBookingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new CancelBookingJFrame().setVisible(true); //Runs the JFrame

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField bookingNumTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel cancelTitle;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
