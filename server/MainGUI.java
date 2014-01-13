package server;

//import java.io.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import static server.ServerCapsule.myServer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class MainGUI extends javax.swing.JFrame {

    NewServer t;

    void setConnected(boolean b) {
        isConnected = b;
        if (b == true) {
            connectionPane.setText("    ONLINE");
        } else {
            connectionPane.setText("    OFFLINE");
        }
    }

    public MainGUI() {
        initComponents();
        isRunning = false;
        setConnected(false);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (isConnected) {
                    t.closeServer();
                    System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        }
        );

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverOutput = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        portPane = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        connectionPane = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel1.setText("START UP THE SERVER!");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 6, 282, -1));

        startButton.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        startButton.setText("START!");
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startButtonMouseClicked(evt);
            }
        });
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        getContentPane().add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 52, -1, -1));

        stopButton.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        stopButton.setText("STOP!");
        stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopButtonMouseClicked(evt);
            }
        });
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stopButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 52, -1, -1));

        jScrollPane1.setViewportView(serverOutput);
        StyledDocument doc = serverOutput.getStyledDocument();
        serverOutput.setText("Hello!\n Turn on the server by pressing START\n");

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 203, 282, 63));

        jLabel2.setText("Server output:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 171, -1, -1));

        portPane.setColumns(1);
        portPane.setRows(1);
        jScrollPane2.setViewportView(portPane);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 119, 100, -1));

        jLabel4.setText("Start server on port:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 99, -1, -1));

        connectionPane.setColumns(1);
        connectionPane.setRows(1);
        jScrollPane3.setViewportView(connectionPane);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 119, 103, -1));

        jLabel5.setText("Connection status:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 99, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed

    }//GEN-LAST:event_startButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed

    }//GEN-LAST:event_stopButtonActionPerformed

    private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
        StyledDocument doc = serverOutput.getStyledDocument();
        try {
            if (!isConnected) {
                int newPort = Integer.parseInt(portPane.getText());
                t = new NewServer(this, newPort);
                t.start();
                Thread.sleep(100);
                if (isConnected) {
                    doc.insertString(doc.getLength(), "> The server is now ONLINE!\n", null);
                } else {
                    doc.insertString(doc.getLength(), "> The selected PORT is already taken!\n", null);
                }

            } else {
                doc.insertString(doc.getLength(), "> The server is already online!\n", null);
            }
        } catch (Exception ex) {

        }

    }//GEN-LAST:event_startButtonMouseClicked

    private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseClicked
        StyledDocument doc = serverOutput.getStyledDocument();
        try {
            if (isConnected) {
                doc.insertString(doc.getLength(), "> The server is now OFFLINE!\n", null);
                setConnected(false);
                t.closeServer();
            } else {
                doc.insertString(doc.getLength(), "> The server is already offline!\n", null);
            }
        } catch (BadLocationException ex) {
        }
    }//GEN-LAST:event_stopButtonMouseClicked

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
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainGUI().setVisible(true);

            }
        });
    }

    boolean isRunning;
    boolean isConnected;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea connectionPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea portPane;
    private javax.swing.JTextPane serverOutput;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
}
