package extract.zframework.hdata;

import java.lang.reflect.Field;
import java.sql.Connection;

import extract.zframework.annotation.relation.Attribut;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.tools.OwnTool;

public class CAttField extends CAttribut {
    Field field;

    public CAttField(Field field, Object object) throws IllegalArgumentException, IllegalAccessException {
        Attribut attribut = extract(field);
        this.setField(field);
        this.init(OwnTool.getNameId(field, attribut), String.valueOf(field.get(object)), attribut.varchar());
    }

    public static Attribut extract(Field field) {
        field.setAccessible(true);
        Attribut attribut = field.getAnnotation(Attribut.class);
        return attribut;
    }

    public CAttField(String column, String value, boolean isVarchar) {
        super(column, value, isVarchar);
    }

    public <T extends ObjectBdd<?>> void load(T ans, String type, String value, Connection connection)
            throws Exception {
        Field field = this.getField();
        ans.setField(field, type, value, connection);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

}
