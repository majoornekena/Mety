package extract.zframework.connection;

import java.sql.*;

import extract.zframework.hdata.CTable;

public class Oracle implements Bdd {
    public Connection get_Connection(String bdd) {
        if (this.isMe(bdd)) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:dbcours",
                        Connect.get_user(),
                        Connect.get_password());
                return conect;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException();
    }

    public String getSequence(CTable table) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isMe(String base) {
        return base.compareToIgnoreCase("oracle") == 0;
    }
}
