package extract.zframework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import extract.zframework.connection.Connect;

public class DataAccess {
    protected Statement statement;
    protected ResultSet res;
    protected boolean active;

    /**
     * @param sql
     * @param connection
     * @throws SQLException
     */
    public void executeQuery(String sql, Connection connection) throws SQLException {
        this.showSql(sql);
        connection = this.verifConnection(connection);
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.executeUpdate();
        pre.close();
        this.close(connection);
    }

    /**
     * @param sql
     * @param connection
     * @return
     * @throws SQLException
     */
    public ArrayList<String[]> fetch(String sql, Connection connection) throws SQLException {
        this.showSql(sql);
        connection = this.verifConnection(connection);
        this.loadStatement(connection, sql);
        int len = res.getMetaData().getColumnCount();
        ArrayList<String[]> answer = this.fetch(this.getRes(), len);
        this.close();
        this.close(connection);
        return answer;
    }

    protected void showSql(String sql) {
        if (Connect.canShowSql()) {
            System.out.println(sql);
        }
    }

    protected boolean shouldReload(Connection connection) throws SQLException {
        boolean verif = connection == null;
        return verif ? verif : connection.isClosed();
    }

    protected Connection verifConnection(Connection connection) throws SQLException {
        if (this.shouldReload(connection)) {
            this.setActive(true);
            connection = Connect.get_connection();
        }
        return connection;
    }

    protected String[] getType(ResultSet res, int len) throws SQLException {
        String[] ans = new String[len];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.getMetaData().getColumnTypeName(i + 1);
        }
        return ans;
    }

    protected String[] toList(ResultSet res, int len) throws SQLException {
        String[] liste = new String[len];
        for (int i = 0; i < len; i++) {
            liste[i] = res.getString(i + 1);
        }
        return liste;
    }

    protected ArrayList<String[]> fetch(ResultSet res, int len) throws SQLException {
        ArrayList<String[]> answer = new ArrayList<String[]>();
        answer.add(this.getType(res, len));
        while (res.next()) {
            answer.add(this.toList(res, len));
        }
        return answer;
    }

    protected void loadStatement(Connection connection, String sql) throws SQLException {
        this.setStatement(connection.createStatement());
        this.setRes(this.getStatement().executeQuery(sql));
    }

    protected void closeStatement() throws SQLException {
        if (this.getStatement() != null) {
            if (!this.getStatement().isClosed()) {
                this.getStatement().close();
            }
        }
    }

    protected void closeResultSet() throws SQLException {
        if (this.getRes() != null) {
            if (!this.getRes().isClosed()) {
                this.getRes().close();
            }
        }
    }

    protected void close(Connection connection) throws SQLException {
        if (this.isActive()) {
            connection.close();
            this.setActive(false);
        }
    }

    protected void close() throws SQLException {
        this.closeResultSet();
        this.closeStatement();
    }

    protected Statement getStatement() {
        return statement;
    }

    protected void setStatement(Statement statement) {
        this.statement = statement;
    }

    protected ResultSet getRes() {
        return res;
    }

    protected void setRes(ResultSet res) {
        this.res = res;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
