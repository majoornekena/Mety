package extract.zframework.hdata;

import java.sql.Connection;
import extract.zframework.dao.ObjectBdd;

public abstract class CAttribut {
    String column;
    String value;
    boolean isVarchar;

    public CAttribut() {
    }

    public CAttribut(String column, String value, boolean isVarchar) {
        this.init(column, value, isVarchar);
    }

    public void init(String column, String value, boolean isVarchar) {
        this.setColumn(column);
        this.setValue(value);
        this.setVarchar(isVarchar);
    }

    public abstract <T extends ObjectBdd<?>> void load(T ans, String type, String value, Connection connection)
            throws Exception;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getPureValue() {
        return this.value;
    }

    public String getValue() {
        String sep = this.isVarchar() ? "'" : "";
        return sep + this.getPureValue() + sep;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isVarchar() {
        return isVarchar;
    }

    public void setVarchar(boolean isVarchar) {
        this.isVarchar = isVarchar;
    }

}
