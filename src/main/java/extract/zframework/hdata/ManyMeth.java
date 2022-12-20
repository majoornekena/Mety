package extract.zframework.hdata;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;

import extract.zframework.annotation.relation.Attribut;
import extract.zframework.dao.ObjectBdd;

public class ManyMeth extends ManyForeign {
    Method meth;

    public ManyMeth(ObjectBdd<?> objectBdd, Method meth, Connection connection) throws Exception {
        super(objectBdd, connection);
        this.setMeth(meth);
    }

    public ArrayList<ObjectBdd<?>> toList(ObjectBdd<?>[] obj) {
        ArrayList<ObjectBdd<?>> ans = new ArrayList<ObjectBdd<?>>();
        for (int i = 0; i < obj.length; i++) {
            ans.add(obj[i]);
        }
        return ans;
    }

    @Override
    public void loadValue(ObjectBdd<?> ans, ObjectBdd<?> answer, CId cid, Connection connection) throws Exception {
        String sql = cid.getSql();
        this.getMeth().setAccessible(true);
        ObjectBdd<?>[] liste = ans.find(connection, sql);
        ArrayList<ObjectBdd<?>> aliste = this.toList(liste);
        this.getMeth().invoke(answer, aliste);

    }

    @Override
    public ObjectBdd<?>[] extractObject() throws Exception {
        this.getMeth().setAccessible(true);
        return (ObjectBdd<?>[]) this.getMeth().invoke(this.getObjectBdd());
    }

    @Override
    public Class<?> extractClass() throws Exception {
        Attribut attr = this.getMeth().getAnnotation(Attribut.class);
        return attr.classes();
    }

    /*****************************************************
     * 
     * FIN TRAITEMENT
     * 
     *****************************************************/

    public Method getMeth() {
        return meth;
    }

    public void setMeth(Method meth) {
        this.meth = meth;
    }

}
