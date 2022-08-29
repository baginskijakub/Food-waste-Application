package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class responsible for actual connection with database, except one method which is changed and that will be documented here.
 * It is the same as MyDatabase jar provided during the semester.
 * Java doc documentation for this jar can be found under following link:
 * http://ict-engineering.dk/javadoc/MyDatabase/utility/persistence/MyDatabase.html
 */

public class MyDatabasev2

{

  private String url;
  private String user;
  private String pw;
  private Connection connection;
  private static final String DRIVER = "com.mysql.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost/";
  private static final String USER = "root";
  private static final String PASSWORD = "";

  public MyDatabasev2(String driver, String url, String user, String pw)
      throws ClassNotFoundException
  {
    this.url = url;
    this.user = user;
    this.pw = pw;
    this.connection = null;
    Class.forName(driver);
  }

  public MyDatabasev2(String databaseName, String user, String pw)
      throws ClassNotFoundException
  {
    this("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/" + databaseName,
        user, pw);
  }

  public MyDatabasev2(String databaseName) throws ClassNotFoundException
  {
    this("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/" + databaseName,
        "root", "");
  }

  public MyDatabasev2() throws ClassNotFoundException
  {
    this("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/", "root", "");
  }

  private void openDatabase() throws SQLException
  {
    this.connection = DriverManager.getConnection(this.url, this.user, this.pw);
  }

  private void closeDatabase() throws SQLException
  {
    this.connection.close();
  }

  public ArrayList<Object[]> query(String sql, Object... statementElements)
      throws SQLException
  {
    this.openDatabase();
    PreparedStatement statement = null;
    ArrayList<Object[]> list = null;
    ResultSet resultSet = null;
    if (sql != null && statement == null)
    {
      statement = this.connection.prepareStatement(sql);
      if (statementElements != null)
      {
        for (int i = 0; i < statementElements.length; ++i)
        {
          statement.setObject(i + 1, statementElements[i]);
        }
      }
    }

    resultSet = statement.executeQuery();
    list = new ArrayList();

    while (resultSet.next())
    {
      Object[] row = new Object[resultSet.getMetaData().getColumnCount()];

      for (int i = 0; i < row.length; ++i)
      {
        row[i] = resultSet.getObject(i + 1);
      }

      list.add(row);
    }

    if (resultSet != null)
    {
      resultSet.close();
    }

    if (statement != null)
    {
      statement.close();
    }

    this.closeDatabase();
    return list;
  }

  /**
   * Method which runs SQL updates in database, differs from the one from jar by returning different data
   *
   * @param sql               the sql updates to execute. The sql statement could start with words INSERT, UPDATE, DELETE, CREATE,... (not SELECT)
   * @param statementElements elements representing the values that should be put in sql statement instead of placeholders
   * @return array of objects, first object is number of rows updated, second one is result set of generated keys (for example while inserting new tuple) - this second object is a difference between method from jar and this one
   * @throws SQLException if something went wrong in the connection or query
   */

  public Object[] update(String sql, Object... statementElements)
      throws SQLException
  {
    this.openDatabase();
    PreparedStatement statement = this.connection.prepareStatement(sql,
        PreparedStatement.RETURN_GENERATED_KEYS);
    int i;
    if (statementElements != null)
    {
      for (i = 0; i < statementElements.length; ++i)
      {
        statement.setObject(i + 1, statementElements[i]);
      }
    }

    i = statement.executeUpdate();
    ResultSet keys = statement.getGeneratedKeys();

    Object[] objects = {i, keys};
    this.closeDatabase();
    return objects;
  }

  public int[] updateAll(ArrayList<String> sqlList) throws SQLException
  {
    if (sqlList == null)
    {
      return null;
    }
    else
    {
      this.openDatabase();
      int[] results = new int[sqlList.size()];

      for (int i = 0; i < sqlList.size(); ++i)
      {
        PreparedStatement statement = this.connection.prepareStatement(
            (String) sqlList.get(i));
        results[i] = statement.executeUpdate();
      }

      this.closeDatabase();
      return results;
    }
  }

  public int[] updateAll(String fileName)
      throws SQLException, FileNotFoundException
  {
    ArrayList<String> sqlList = this.readFile(fileName, ";");
    return this.updateAll(sqlList);
  }

  private ArrayList<String> readFile(String filename, String deliminator)
      throws FileNotFoundException
  {
    Scanner input = new Scanner(new FileInputStream(filename));
    ArrayList<String> list = new ArrayList();
    String sql = "";

    while (true)
    {
      while (input.hasNext())
      {
        sql = sql + input.nextLine();
        if (deliminator != null && !sql.trim().endsWith(deliminator))
        {
          if (sql.length() > 0)
          {
            sql = sql + "\n";
          }
        }
        else
        {
          list.add(sql);
          sql = "";
        }
      }

      input.close();
      return list;
    }
  }
}
