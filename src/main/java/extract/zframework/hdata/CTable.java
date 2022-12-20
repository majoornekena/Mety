package extract.zframework.hdata;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import extract.zframework.connection.Connect;
import extract.zframework.dao.DataAccess;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.tools.OwnTool;

public class CTable {
    protected final String insert = "insert into %s(%s) values(%s)";
    protected final String select = "select %s from %s where 1=1 ";
    String nom;
    String seq;
    CId id;
    ArrayList<CAttribut> attributs;
    ArrayList<ManyForeign> manyForeigns;
    Field[] fields;
    Method[] methods;

    @Override
    public String toString() {
        return this.getNom() + ";" + this.getId() + ";" + this.getSeq();
    }

    public CTable() {
    }

    public CTable(String nom, String seq, CId id) {
        this(nom, seq);
        this.setId(id);
    }

    public void loadFields(ObjectBdd<?> object) {
        this.setFields(OwnTool.toFields(OwnTool.getAllFields(object.getClass())));
    }

    public void loadMeth(ObjectBdd<?> object) {
        this.setMethods(OwnTool.toMeths(OwnTool.getAllMethods(object.getClass())));
    }

    public CTable(ObjectBdd<?> object, Connection connection) throws Exception {
        this.loadFields(object);
        this.loadMeth(object);
        Field field = OwnTool.getFieldId(this.getFields());
        String tname = object.getTableName();
        String seq = field == null ? "" : OwnTool.getSeq(field);
        CId id = field == null ? null : object.getId(field, connection);
        this.setId(id);
        this.setSeq(seq);
        this.setNom(tname);
        this.setAttributs(object.toAttr(this, connection));
        this.setManyForeigns(object.toManyForeign(this, connection));
    }

    public CTable(String nom, String seq) {
        this.setNom(nom);
        this.setSeq(seq);
    }

    public void loadManyForeignInsert(Connection connection) {
        for (int i = 0; i < this.getManyForeigns().size(); i++) {
            this.getManyForeigns().get(i).loadInsert(connection);
        }
    }

    public void loadManyForeign(ObjectBdd<?> object, Connection connection) throws Exception {
        for (int i = 0; i < this.getManyForeigns().size(); i++) {
            this.getManyForeigns().get(i).load(object, connection);
        }
    }

    public <T extends ObjectBdd<?>> void loadAttr(T object, String[] type, String[] value, Connection connection)
            throws Exception {
        if (this.getId() != null) {
            object.configId(value[0]);
        }
        int plus = this.getId() != null ? 1 : 0;
        for (int i = 0; i < this.getAttributs().size(); i++) {
            this.getAttributs().get(i).load(object, type[i + plus], value[i + plus], connection);
        }
    }

    public String getNextId(ObjectBdd<?> object, Connection connection) throws SQLException {
        if (object.isAllocated()) {
            return String.valueOf(this.getId().getValue());
        }
        String seq = Connect.getActif().getSequence(this);
        DataAccess data = new DataAccess();
        String ans = data.fetch(seq, connection).get(1)[0];
        return ans;
    }

    protected String gatherColIns() {
        String ans = this.getId() != null ? this.getId().getColumn() : "";
        for (int i = 0; i < this.getAttributs().size(); i++) {
            ans += (i == 0 && this.getId() == null ? "" : ",") + this.getAttributs().get(i).getColumn();
        }
        return ans;
    }

    protected String gatherValuesIns() {
        String ans = this.getId() != null ? this.getId().getValue() : "";
        for (int i = 0; i < this.getAttributs().size(); i++) {
            ans += (i == 0 && this.getId() == null ? "" : ",") + this.getAttributs().get(i).getValue();
        }
        return ans;
    }

    public String toInsert() {
        String col = this.gatherColIns();
        String data = this.gatherValuesIns();
        return String.format(this.insert, this.getNom(), col, data);
    }

    public String getIdSql() {
        return this.getId().getSql();
    }

    public String toSelect(String... where) {
        String col = this.gatherColIns();
        String sql = String.format(this.select, col, this.getNom());
        for (int i = 0; i < where.length; i++) {
            sql += where[i];
        }
        return sql;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public CId getId() {
        return id;
    }

    public void setId(CId id) {
        this.id = id;
    }

    public ArrayList<CAttribut> getAttributs() {
        return attributs;
    }

    public void setAttributs(ArrayList<CAttribut> attributs) {
        this.attributs = attributs;
    }

    public String getInsert() {
        return insert;
    }

    public String getSelect() {
        return select;
    }

    public ArrayList<ManyForeign> getManyForeigns() {
        return manyForeigns;
    }

    public void setManyForeigns(ArrayList<ManyForeign> manyForeigns) {
        this.manyForeigns = manyForeigns;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public Method[] getMethods() {
        return methods;
    }

    public void setMethods(Method[] methods) {
        this.methods = methods;
    }

}
