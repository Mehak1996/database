
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manpreetdhillon
 */
public class setRecruiter extends javax.swing.JFrame {

    /**
     * Creates new form setRecruiter
     */
    Connection connection = null;
    Statement statement = null;
    JSONArray array = new JSONArray();
    JSONObject getItem;
    String stuId;
    String stuName;
   

    public setRecruiter() throws SQLException {
        initComponents();
    }
     public setRecruiter(String id,String name, int percentage) throws SQLException {
      initComponents();
      stuId = id;
      stuName = name;
      DatabaseConnection db = new DatabaseConnection();
      connection = db.getConnection();
      statement = db.getStatement(connection);
      studentName.setText(name);
      studentID.setText(id); 
      studentPercentage.setText(Integer.toString(percentage));
      getRecruitersList(percentage);
      System.out.println(array);
      for(int i=0;i<array.length();i++){
          getItem = array.getJSONObject(i);
          recruiterList.addItem(getItem.getString("name")+", "+getItem.getString("companyname"));
      }
     // recruiterList.setSelectedIndex(null);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        studentName = new javax.swing.JLabel();
        studentID = new javax.swing.JLabel();
        studentPercentage = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        recruiterList = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Student Name :");

        jLabel2.setText("Student  ID :");

        jLabel3.setText("Percentage :");

        jLabel4.setText("Select Recruiter : ");

        studentName.setText("Student Name :");

        studentID.setText("Student Name :");

        studentPercentage.setText("Student Name :");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentPercentage)
                            .addComponent(studentID)
                            .addComponent(studentName)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 163, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(recruiterList, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(studentName))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(studentPercentage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(recruiterList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121)
                .addComponent(jButton1)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int index = recruiterList.getSelectedIndex();
        getItem = array.getJSONObject(index);
        int recruiterId = getItem.getInt("id");
        String recruiterName = getItem.getString("name");
        String companyName = getItem.getString("companyname");
        insertIntoAdminTable(recruiterId, recruiterName, companyName);
        System.out.print("recruiter id is "+recruiterId);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    void getRecruitersList(int percentage){
            try {
            //String query = "select * from STUDENT ";
            String query = "select * from RECRUITER where minpercentage <= "+percentage;
            ResultSet rs = statement.executeQuery(query);
            System.out.print("result set is "+rs);
            while(rs.next()) {
                 JSONObject item = new JSONObject();
                 item.put("name", rs.getString("name"));
                 item.put("companyname", rs.getString("companyname"));
                 item.put("id", rs.getInt("id"));
                 array.put(item);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
            java.util.logging.Logger.getLogger(setRecruiter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(setRecruiter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(setRecruiter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(setRecruiter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new setRecruiter().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(setRecruiter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    void insertIntoAdminTable(int recruiterId, String recruiterName, String companyName)
    {
            try{         
             String query = "insert into ADMIN(studentid, recruiterid) values("+"'"+stuId+"'"+","+recruiterId+")";
             System.out.print(query);
             
                       int success=statement.executeUpdate(query);
                            if(success==1)
                            {
                                JOptionPane.showMessageDialog(this, "Student with student id = "+stuId+" and name = "+stuName+" is employed by employer "+recruiterName+", "+companyName);
                               //emp1.showMessageDialog(this, "Problem in Saving. Retry");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(this, "Error in assigning student with id = "+stuId+" and name = "+stuName+" to employer "+recruiterName+", "+companyName+". Please try again!");
                            }
                        try {
    if (connection != null)
     connection.close();
   } catch (SQLException se) {
    se.printStackTrace();
   }
        }

    catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> recruiterList;
    private javax.swing.JLabel studentID;
    private javax.swing.JLabel studentName;
    private javax.swing.JLabel studentPercentage;
    // End of variables declaration//GEN-END:variables
}
