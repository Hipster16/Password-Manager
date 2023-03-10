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

public class LoginConnector extends dbFunctions
    {
      public ResultSet rs;
      public Connection connection = null;
      public Statement statement;
      public LoginConnector(String tablename)
      {
        getName(tablename);
        try
        {
          // create a database connection
          connection = DriverManager.getConnection("jdbc:sqlite:new.db");
          statement = connection.createStatement();
          statement.setQueryTimeout(30);  // set timeout to 30 sec.

          rs = statement.executeQuery(selectAllFromtable());
        }
        catch(SQLException e)
        {
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
            System.err.println(e.getMessage());
          }
      }
    }