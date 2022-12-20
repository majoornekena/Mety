package extract.zframework.hdata;

import java.lang.reflect.Field;

public class CId extends CAttField {

    public CId(Field field, Object object) throws IllegalArgumentException, IllegalAccessException {
        super(field, object);
    }

    public CId(String column, String value, boolean isVarchar) {
        super(column, value, isVarchar);
    }

    public String getSql() {
        String sql = " and %s=%s";
        return String.format(sql, this.getColumn(), this.getValue());
    }
}
