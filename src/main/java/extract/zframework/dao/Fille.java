package extract.zframework.dao;

import java.lang.reflect.Field;
import java.sql.Connection;

import extract.zframework.annotation.relation.Attribut;
import extract.zframework.annotation.relation.ForeignKey;
import extract.zframework.hdata.CId;
import extract.zframework.tools.OwnTool;

public abstract class Fille<T extends ObjectBdd<?>> extends ObjectBdd<T> {
    protected String colMere;

    @ForeignKey
    @Attribut
    Mere<?> mere;

    public Fille(String colMere) {
        this.colMere = colMere;
    }

    public String getMereCondition() {
        CId cid = this.getMere().extractMId();
        cid.setColumn(this.getColMere());
        return cid.getSql();
    }

    public Fille<?>[] getByMere(Connection connection) throws Exception {
        String where = this.getMereCondition();
        Fille<?>[] fille = (Fille[]) this.find(connection, where);
        return fille;
    }

    @Override
    public String getFKNameId(Field field, Attribut attribut) {
        if (field.getType().equals(this.getMere().getClass())) {
            return this.getColMere();
        }
        return OwnTool.getNameId(field, null);
    }

    public Mere<?> getMere() {
        return mere;
    }

    public void setMere(Mere<?> mere) {
        this.mere = mere;
        this.getMere().activeAll();
    }

    public abstract void configMereId();

    public String getColMere() {
        return colMere;
    }

    public void setColMere(String colMere) {
        this.colMere = colMere;
    }
}
