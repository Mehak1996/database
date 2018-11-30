import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mehakluthra
 */
public class DatabaseConnection {
 static Connection ConnectDb() {
  throw new UnsupportedOperationException("Not supported yet.");
 }
 private Connection con = null;
 private Statement st = null;
 private ResultSet rs;

 public DatabaseConnection() throws SQLException{
   con = getConnection();
   getStatement(con);
   createTables(); 
}
 public Connection getConnection() {
  try {
   Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
   con = DriverManager.getConnection("jdbc:derby://localhost:1527/placementdatabase", "user1", "1234");
  } catch (Exception e) {
   System.out.println("Error" + e);
  }
  return con;
 }

 public Statement getStatement(Connection connection) {
  try {
   st = connection.createStatement();

  } catch (Exception e) {
   System.out.println("Error" + e);
  }
  return st;
 }

 private void createTables() throws SQLException {
   DatabaseMetaData dbm = (DatabaseMetaData) con.getMetaData();
   ResultSet studentTable = dbm.getTables(null, null, "STUDENT", null);
    if (studentTable.next()) {
     // Table exists
    }
   else {
    createStudentTable();
    } 
    ResultSet recruiterTable = dbm.getTables(null, null, "RECRUITER", null);
    if (recruiterTable.next()) {
     // Table exists
    }
   else {
    createRecruiterTable();
    }
    ResultSet adminTable = dbm.getTables(null, null, "ADMIN", null);
    if (adminTable.next()) {
     // Table exists
    }
   else {
    createAdminTable();
    }
 }

 private void createStudentTable() {
  Connection connection = null;
  Statement stmt = null;
  try {
   connection = getConnection();
   stmt = getStatement(connection);

   String createStudentTableSQL = "CREATE TABLE STUDENT " +
    "(id VARCHAR(255) not NULL, " +
    " name VARCHAR(255), " +
    " percentage INTEGER, " +
    " placed BOOLEAN, " +
    " PRIMARY KEY ( id ))";

   stmt.executeUpdate(createStudentTableSQL);
   System.out.println("Created student table in given database...");
  } catch (SQLException se) {
   //Handle errors for JDBC
   se.printStackTrace();
  } catch (Exception e) {
   //Handle errors for Class.forName
   e.printStackTrace();
  } finally {
   try {
    if (stmt != null)
     connection.close();
   } catch (SQLException se) {} // do nothing
   try {
    if (connection != null)
     connection.close();
   } catch (SQLException se) {
    se.printStackTrace();
   } //end finally try
  } //end try
 }
 private void createRecruiterTable() {
  Connection connection = null;
  Statement stmt = null;
  try {
   connection = getConnection();
   stmt = getStatement(connection);

   String createRecruiterTableSQL = "CREATE TABLE RECRUITER " +
    "(id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
    " name VARCHAR(255), " +
    " companyname VARCHAR(255), " +
    " PRIMARY KEY ( id ))";

   stmt.executeUpdate(createRecruiterTableSQL);
   System.out.println("Created recruiter table in given database...");
  } catch (SQLException se) {
   //Handle errors for JDBC
   se.printStackTrace();
  } catch (Exception e) {
   //Handle errors for Class.forName
   e.printStackTrace();
  } finally {
   try {
    if (stmt != null)
     connection.close();
   } catch (SQLException se) {} // do nothing
   try {
    if (connection != null)
     connection.close();
   } catch (SQLException se) {
    se.printStackTrace();
   } //end finally try
  } //end try
 }
 private void createAdminTable(){
     Connection connection = null;
  Statement stmt = null;
  try {
   connection = getConnection();
   stmt = getStatement(connection);

   String createAdminTableSQL = "CREATE TABLE ADMIN " +
    "(studentid VARCHAR(255) not NULL, " +
    " recruiterid int NOT NULL, " +
    " CONSTRAINT studentId_fk FOREIGN KEY (studentid) REFERENCES STUDENT(id), " +
    " CONSTRAINT recruiterId_fk FOREIGN KEY (recruiterid) REFERENCES RECRUITER(id))";

   stmt.executeUpdate(createAdminTableSQL);
   System.out.println("Created admin table in given database...");
  } catch (SQLException se) {
   //Handle errors for JDBC
   se.printStackTrace();
  } catch (Exception e) {
   //Handle errors for Class.forName
   e.printStackTrace();
  } finally {
   try {
    if (stmt != null)
     connection.close();
   } catch (SQLException se) {} // do nothing
   try {
    if (connection != null)
     connection.close();
   } catch (SQLException se) {
    se.printStackTrace();
   } //end finally try
  } //end try
 }
}
