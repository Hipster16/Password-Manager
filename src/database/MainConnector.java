package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class dbFunctions{
    String tablename;
    public void getName(String tablename){
      this.tablename=tablename;
    }
  
    public String selectAllFromtable(){
      return "select * from "+this.tablename;
    }   
  }


public class MainConnector extends dbFunctions
    {
      public ResultSet rs;
      public Connection connection = null;
      public Statement statement;
      public MainConnector(String tablename)
      {
        getName(tablename);
        try
        {
          // create a database connection
          connection = DriverManager.getConnection("jdbc:sqlite:people.db");
          statement = connection.createStatement();
          statement.setQueryTimeout(30);  // set timeout to 30 sec.
          
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
          
      }
      public void close(){
        try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
      }

      public String getCount(){
        return "select count(*) from "+tablename;
      }
  
      public String deleteFromTable(String user,String pass,String site){
        return "delete from "+tablename+" where username='"+user+"' and password='"+pass+"' and Site='"+site+"'";
      }

      public String insertInTable(String username,String password,String sitename){
        return "insert into "+tablename+" values (1,'"+username+"','"+password+"','"+sitename+"')";
      }

      public String updateTable(String cuser,String cpass,String csite,String user,String site){
        return "update "+tablename+" set username='"+cuser+"',password='"+cpass+"',Site='"+csite+"' where username='"+user+"' and Site='"+site+"'";
      }
    }