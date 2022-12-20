package extract.zframework.hdata;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;

import extract.zframework.annotation.BaseState;
import extract.zframework.annotation.relation.Attribut;
import extract.zframework.dao.ObjectBdd;

public class CAttMeth extends CAttribut {
    Method meth;

    public CAttMeth(Method meth, Object object)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.setMeth(meth);
        Attribut attribut = extract(meth);
        String value = "";
        if (!this.isSelect(attribut)) {
            value = String.valueOf(meth.invoke(object));
        }
        this.init(attribut.value(), value, attribut.varchar());
    }

    public boolean isSelect(Attribut attribut) {
        for (int i = 0; i < attribut.state().length; i++) {
            if (attribut.state()[i].equals(BaseState.SELECT)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <T extends ObjectBdd<?>> void load(T ans, String type, String value, Connection connection)
            throws Exception {
        ans.setField(this.getMeth(), type, value, connection);
    }

    public static Attribut extract(Method meth) {
        Attribut attribut = meth.getAnnotation(Attribut.class);
        return attribut;
    }

    public Method getMeth() {
        return meth;
    }

    public void setMeth(Method meth) {
        this.meth = meth;
    }

}
