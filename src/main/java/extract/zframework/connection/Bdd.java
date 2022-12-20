package extract.zframework.connection;

import java.sql.*;

import extract.zframework.hdata.CTable;

public interface Bdd {
    public boolean isMe(String base);

    public Connection get_Connection(String bdd);

    public String getSequence(CTable table);
}