
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class SQLRunner {
  private Connection connection;

  private SQLRunner() {
  }

  public static SQLRunner getInstance() throws ClassNotFoundException, SQLException {
    SQLRunner ret = new SQLRunner();
    Class.forName("oracle.jdbc.driver.OracleDriver");
    ret.connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.64.2:1521:xe", "juna", "1234");
    return ret;
  }

  /**
   * 
   * @param statement
   * @return ResultSet or Null (on SQLException)
   */
  public ResultSet runStatement(String statement) throws SQLException {
    try {
      Statement statementClass = connection.createStatement();
      return statementClass.executeQuery("SELECT * FROM tab");
    } catch (SQLException e) {
      System.err.println("SQL Execution Failed: " + statement);
      throw e;
    }
  }

  public void dispose() {
    try {
      connection.close();
    } catch (SQLException e) {
      System.err.println("Error on disposal");
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
