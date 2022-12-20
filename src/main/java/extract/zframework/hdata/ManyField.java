package extract.zframework.hdata;

import java.lang.reflect.Field;
import java.sql.Connection;

import extract.zframework.dao.ObjectBdd;

public class ManyField extends ManyForeign {
    Field field;

    public ManyField(ObjectBdd<?> objectBdd, Field field, Connection connection) throws Exception {
        super(objectBdd, connection);
        this.setField(field);
    }

    @Override
    public Class<?> extractClass() throws Exception {
        return this.getField().getType().getComponentType();
    }

    @Override
    public void loadValue(ObjectBdd<?> ans, ObjectBdd<?> answer, CId cid, Connection connection) throws Exception {
        String sql = cid.getSql();
        this.getField().setAccessible(true);
        ObjectBdd<?>[] liste = ans.find(connection, sql);
        this.getField().set(answer, liste);
    }

    @Override
    public ObjectBdd<?>[] extractObject() throws IllegalArgumentException, IllegalAccessException {
        this.getField().setAccessible(true);
        return (ObjectBdd<?>[]) this.getField().get(this.getObjectBdd());
    }

    /*****************************************************
     * 
     * FIN TRAITEMENT
     * 
     *****************************************************/

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

}
