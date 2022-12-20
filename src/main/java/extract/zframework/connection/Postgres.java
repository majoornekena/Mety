package extract.zframework.connection;

import java.sql.*;

import extract.zframework.hdata.CTable;

public class Postgres implements Bdd {

    public Connection get_Connection(String bdd) {
        if (this.isMe(bdd)) {
            try {
                Class.forName("org.postgresql.Driver");
                Connection conect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + Connect.hbdd,
                        Connect.get_user(), Connect.get_password());
                return conect;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException();
    }

    public String seqNoSeq(CTable table) {
        String pathern = "%s_%s_seq";
        String value = String.format(pathern, table.getNom(), table.getId().getColumn());
        table.setSeq(value);
        return table.getSeq();
    }

    public String getSequence(CTable table) {
        String pathern = "select nextval('%s')";
        String seq = !table.getSeq().equals("") ? table.getSeq() : this.seqNoSeq(table);
        return String.format(pathern, seq);
    }

    public boolean isMe(String base) {
        return base.compareToIgnoreCase("postgres") == 0;
    }
}