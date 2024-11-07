import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

class DBex extends JFrame {
  SQLRunner runner;

  private void init() {
    setTitle("Database Test");
    setVisible(true);
    setSize(600, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    LayoutManager layout = new BorderLayout();
    setLayout(layout);

    //JImage // TODO: Use JLabel
  }

  public DBex(SQLRunner runner) {
    super();
    this.runner = runner;

    init();
  }

  public static void main(String[] args) {
    SQLRunner runner = null;
    try {

      runner = SQLRunner.getInstance();
      
      new DBex(runner);

    } catch (ClassNotFoundException e) {
      System.out.println("Error: JDBC Not Found.");
    } catch (SQLException e) {
      System.out.println("SQL Fail");
      System.out.println(e);
    } finally {
      runner.dispose();
    }
  }
}