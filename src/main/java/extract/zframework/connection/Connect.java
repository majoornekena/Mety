package extract.zframework.connection;

import java.sql.*;

public class Connect {
    protected static String user;
    protected static String password;
    protected static String hbdd;
    protected static String db;
    protected static boolean active = false;
    protected static boolean allowSql = false;
    static Bdd actif;
    static Bdd[] bdd = new Bdd[] { new Oracle(), new Postgres() };

    public static boolean canShowSql() {
        return Connect.allowSql;
    }

    public static void disableSql() {
        Connect.allowSql = false;
    }

    public static void allowSql() {
        Connect.allowSql = true;
    }

    public static void init(String user, String password, String... db) {
        if (!Connect.active) {
            Connect.user = user;
            Connect.password = password;
            Connect.db = db[0];
            if (db.length > 1) {
                Connect.hbdd = db[1];
            }
            Connect.active = true;
        }
    }

    public static Bdd getActif() {
        Connect.loadBdd(Connect.db);
        return Connect.actif;
    }

    public static String get_user() {
        return Connect.user;
    }

    public static String get_password() {
        return Connect.password;
    }

    public static Connection get_connection() {
        return Connect.get_connection(Connect.db);
    }

    public static void loadBdd(String base) {
        if (Connect.actif != null)
            return;
        for (int i = 0; i < Connect.bdd.length; i++) {
            if (Connect.bdd[i].isMe(base)) {
                Connect.actif = Connect.bdd[i];
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public static Connection get_connection(String bdd) {
        Connect.loadBdd(bdd);
        return Connect.actif.get_Connection(bdd);
    }

}
