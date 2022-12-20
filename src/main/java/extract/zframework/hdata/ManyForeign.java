package extract.zframework.hdata;

import java.lang.reflect.Field;
import java.sql.Connection;

import extract.zframework.annotation.BaseState;
import extract.zframework.annotation.relation.MappedBy;
import extract.zframework.controlExcept.NoFieldMapped;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.tools.OwnTool;

public abstract class ManyForeign {
    ObjectBdd<?> objectBdd;

    public ManyForeign(ObjectBdd<?> objectBdd, Connection connection)
            throws Exception {
        this.setObjectBdd(objectBdd);
    }

    public abstract ObjectBdd<?>[] extractObject() throws Exception;

    public void loadInsert(Connection connection) {
        if (this.getObjectBdd().getBaseState().equals(BaseState.INSERT)) {
            ObjectBdd<?>[] bdd;
            try {
                bdd = this.extractObject();
                if (bdd != null) {
                    for (int i = 0; i < bdd.length; i++) {
                        if (!bdd[i].isFromBase()) {
                            bdd[i].create(connection);
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Field getFieldMap(Class<?> cls, Connection connection) throws NoFieldMapped {
        Field[] fields = OwnTool.getAnnotations(cls, MappedBy.class);
        return OwnTool.getFieldMap(fields, this.getObjectBdd().getClass());
    }

    public CId loadCid(Field field, ObjectBdd<?> ans) throws IllegalArgumentException, IllegalAccessException {
        CId cid = new CId(field, ans);
        cid.setValue(this.getObjectBdd().extractId());
        return cid;
    }

    public abstract void loadValue(ObjectBdd<?> ans, ObjectBdd<?> answer, CId cid, Connection connection)
            throws Exception;

    public abstract Class<?> extractClass() throws Exception;

    public void load(ObjectBdd<?> answer, Connection connection) throws Exception {
        Class<?> cls = this.extractClass();
        Field f = this.getFieldMap(cls, connection);
        ObjectBdd<?> ans = (ObjectBdd<?>) cls.getDeclaredConstructor().newInstance();
        ans.setRecursive(true);
        CId cid = this.loadCid(f, ans);
        this.loadValue(ans, answer, cid, connection);
    }

    /*****************************************************
     * 
     * FIN TRAITEMENT
     * 
     *****************************************************/

    public ObjectBdd<?> getObjectBdd() {
        return objectBdd;
    }

    public void setObjectBdd(ObjectBdd<?> objectBdd) {
        this.objectBdd = objectBdd;
    }

}
