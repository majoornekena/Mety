package extract.zframework.dao;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import extract.zframework.annotation.BaseState;
import extract.zframework.annotation.EForm;
import extract.zframework.annotation.relation.Attribut;
import extract.zframework.annotation.relation.ForeignKey;
import extract.zframework.annotation.relation.Id;
import extract.zframework.annotation.relation.Relation;
import extract.zframework.annotation.relation.Table;
import extract.zframework.hdata.CAttField;
import extract.zframework.hdata.CAttMeth;
import extract.zframework.hdata.CAttribut;
import extract.zframework.hdata.CId;
import extract.zframework.hdata.CTable;
import extract.zframework.hdata.ManyField;
import extract.zframework.hdata.ManyForeign;
import extract.zframework.hdata.ManyMeth;
import extract.zframework.hdata.NoForeign;
import extract.zframework.hform.HCForm;
import extract.zframework.tools.OwnTool;

public abstract class ObjectBdd<T extends ObjectBdd<?>> {
    protected boolean fromBase;
    protected boolean recursive;
    protected boolean ischecked;
    protected boolean isAllocated;
    public String pureValue;
    public String idValue;
    public CTable table;
    public BaseState baseState;

    public ObjectBdd() {

    }

    /**
     * Override this method if you want to add verification before create
     * 
     * @param connection
     * @throws Exception
     */
    public abstract void verif(Connection connection) throws Exception;

    public abstract void configId(String id);

    public abstract void activeAll();

    protected Field getFieldId(ObjectBdd<?> g, Field field) throws Exception {
        field.setAccessible(true);
        Class<?> cls = g == null ? field.getType() : g.getClass();
        Field[] fields = OwnTool.toFields(OwnTool.getAllFields(cls));
        return OwnTool.getFieldId(fields);
    }

    public Field getFieldId() throws Exception {
        Field[] ans = OwnTool.toFields(OwnTool.getAllFields(this.getClass()));
        return OwnTool.getFieldId(ans);
    }

    public String getTableName() {
        Table table = this.getClass().getAnnotation(Table.class);
        String ans = table.value();
        if (ans.equals("")) {
            String[] tname = this.getClass().getName().split("\\.");
            ans = tname[tname.length - 1];
        }
        return ans;
    }

    protected ForeignKey getForeignKey(Field field) throws NoForeign {
        ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);
        if (foreignKey == null) {
            throw new NoForeign("Not ForeignKey");
        }
        return foreignKey;
    }

    protected ObjectBdd<?> loadForeign(ObjectBdd<?> g, Connection connection) throws Exception {
        if (g == null)
            return g;
        if (!g.isFromBase() && this.getBaseState().equals(BaseState.INSERT))
            g.create(connection);
        return g;
    }

    public String getFKNameId(Field field, Attribut attribut) {
        return OwnTool.getNameId(field, null);
    }

    protected CId getForeign(Field field, Connection connection) throws Exception {
        field.setAccessible(true);
        this.getForeignKey(field);
        ObjectBdd<?> g = (ObjectBdd<?>) field.get(this);
        g = this.loadForeign(g, connection);
        Field fieldid = this.getFieldId(g, field);
        Attribut attribut = OwnTool.loadAttribut(fieldid, null);
        String name = this.getFKNameId(field, null);
        String value = OwnTool.extractValue(g, fieldid);
        CId cid = new CId(name, value, attribut.varchar());
        return cid;
    }

    protected CId getOnlyId(Field field) throws IllegalArgumentException, IllegalAccessException {
        return new CId(field, this);
    }

    public CId getId(Field field, Connection connection) throws Exception {
        try {
            return this.getForeign(field, connection);
        } catch (NoForeign e) {
            return this.getOnlyId(field);
        }
    }

    protected void toTable(Connection connection) throws Exception {
        CTable table = new CTable(this, connection);
        this.setTable(table);
    }

    public void loadId(Connection connection) throws SQLException {
        String ans = this.getTable().getNextId(this, connection);
        this.getTable().getId().setValue(ans);
    }

    public CTable allocateId(Connection connection) throws Exception {
        this.toTable(connection);
        if (this.getTable().getId() != null) {
            this.loadId(connection);
        }
        this.setAllocated(true);
        return table;
    }

    protected void isId(Field field) throws Exception {
        Id id = field.getAnnotation(Id.class);
        if (id != null) {
            throw new Exception("Is Id colomn");
        }
    }

    protected void skypeMany(Field field) throws Exception {
        try {
            ForeignKey fk = this.getForeignKey(field);
            if (fk.relation().equals(Relation.OneToMany) || fk.relation().equals(Relation.ManyToMany)) {
                throw new Exception("Is many relation");
            }
        } catch (NoForeign e) {
        }
    }

    protected void shouldSkype(Field field) throws Exception {
        OwnTool.isAttribut(field);
        this.isId(field);
        this.skypeMany(field);
    }

    public void matchBase(Attribut attribut) throws Exception {
        for (int i = 0; i < attribut.state().length; i++) {
            if (attribut.state()[i].equals(this.getBaseState())) {
                return;
            }
        }
        throw new Exception("Not BasePath");
    }

    protected void shouldSkype(Method field) throws Exception {
        Attribut attribut = OwnTool.isAttribut(field);
        this.matchBase(attribut);
    }

    protected CAttribut toAttr(Field field, Connection connection) throws Exception {
        this.shouldSkype(field);
        CAttribut ans = this.getId(field, connection);
        ((CAttField) ans).setField(field);
        return ans;
    }

    protected CAttribut toAttr(Method field, Connection connection) throws Exception {
        this.shouldSkype(field);
        CAttribut ans = new CAttMeth(field, this);
        return ans;
    }

    public void isManyForeign(Field field) throws Exception {
        ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);
        if (foreignKey == null) {
            throw new Exception("Not Many ForeignKey");
        }
        if (!(foreignKey.relation().equals(Relation.OneToMany)
                || foreignKey.relation().equals(Relation.ManyToMany))) {
            throw new Exception("Not 1 Many ForeignKey");
        }
    }

    public void isManyForeign(Method field) throws Exception {
        ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);
        if (foreignKey == null) {
            throw new Exception("Not Many ForeignKey");
        }
        if (!(foreignKey.relation().equals(Relation.OneToMany)
                || foreignKey.relation().equals(Relation.ManyToMany))) {
            throw new Exception("Not 1 Many ForeignKey");
        }
    }

    public ArrayList<ManyForeign> toManyForeign(CTable table, Connection connection) throws Exception {
        ArrayList<ManyForeign> ans = this.toManyForeign(table.getFields(), connection);
        this.toManyForeign(ans, table.getMethods(), connection);
        return ans;
    }

    public ArrayList<ManyForeign> toManyForeign(Connection connection) throws Exception {
        Field[] fields = OwnTool.toFields(OwnTool.getAllFields(this.getClass()));
        ArrayList<ManyForeign> ans = this.toManyForeign(fields, connection);
        this.toManyForeign(ans, OwnTool.toMeths(OwnTool.getAllMethods(this.getClass())), connection);
        return ans;
    }

    public void toManyForeign(ArrayList<ManyForeign> ans, Method[] fields, Connection connection) throws Exception {
        for (int i = 0; i < fields.length; i++) {
            try {
                this.isManyForeign(fields[i]);
                ManyForeign mForeign = new ManyMeth(this, fields[i], connection);
                ans.add(mForeign);
            } catch (Exception e) {
            }
        }
    }

    public ArrayList<ManyForeign> toManyForeign(Field[] fields, Connection connection) throws Exception {
        ArrayList<ManyForeign> ans = new ArrayList<ManyForeign>();
        for (int i = 0; i < fields.length; i++) {
            try {
                this.isManyForeign(fields[i]);
                ManyForeign mForeign = new ManyField(this, fields[i], connection);
                ans.add(mForeign);
            } catch (Exception e) {
            }
        }
        return ans;
    }

    public ArrayList<CAttribut> toAttr(CTable table, Connection connection) throws Exception {
        ArrayList<CAttribut> ans = this.toAttr(table.getFields(), connection);
        this.toAttr(ans, table.getMethods(), connection);
        return ans;
    }

    public ArrayList<CAttribut> toAttr(Connection connection) throws Exception {
        Field[] fields = OwnTool.toFields(OwnTool.getAllFields(this.getClass()));
        ArrayList<CAttribut> ans = this.toAttr(fields, connection);
        this.toAttr(ans, this.getClass().getDeclaredMethods(), connection);
        return ans;
    }

    public void toAttr(ArrayList<CAttribut> ans, Method[] meth, Connection connection) {
        for (int i = 0; i < meth.length; i++) {
            try {
                CAttribut catt = this.toAttr(meth[i], connection);
                ans.add(catt);
            } catch (Exception e) {

            }
        }
    }

    public ArrayList<CAttribut> toAttr(Field[] fields, Connection connection) throws Exception {
        ArrayList<CAttribut> ans = new ArrayList<CAttribut>();
        for (int i = 0; i < fields.length; i++) {
            try {
                CAttribut catt = this.toAttr(fields[i], connection);
                ans.add(catt);
            } catch (Exception e) {

            }
        }
        return ans;
    }

    public void setConfig() {
        this.setConfig(this.getTable());
    }

    public void setConfig(CTable table) {
        this.configId(table.getId() != null ? table.getId().getPureValue() : "");
    }

    public void shouldVerif(Connection connection) throws Exception {
        if (!this.isIschecked()) {
            this.verif(connection);
        }
    }

    public void create(Connection connection) throws Exception {
        this.activeAll();
        this.shouldVerif(connection);
        BaseState temp = this.getBaseState();
        this.setBaseState(BaseState.INSERT);
        DataAccess data = new DataAccess();
        CTable table = this.allocateId(connection);
        String sql = table.toInsert();
        data.executeQuery(sql, connection);
        this.setConfig(table);
        this.setFromBase(true);
        table.loadManyForeignInsert(connection);
        this.setBaseState(temp);
    }

    public T[] select(Connection connection, String... where) throws Exception {
        DataAccess data = new DataAccess();
        this.activeAll();
        String sql = this.getTable().toSelect(where);
        ArrayList<String[]> liste = data.fetch(sql, connection);
        T[] ans = this.toObject(this.getTable(), liste, connection);
        return ans;
    }

    public T[] find(Connection connection, String... where) throws Exception {
        BaseState temp = this.getBaseState();
        this.setBaseState(BaseState.SELECT);
        this.toTable(connection);
        this.setBaseState(temp);
        return this.select(connection, where);
    }

    public T findById(Connection connection) throws Exception {
        BaseState temp = this.getBaseState();
        this.setBaseState(BaseState.SELECT);
        this.toTable(connection);
        this.setBaseState(temp);
        String sql = table.getIdSql();
        T[] ans = this.select(connection, sql);
        return ans.length > 0 ? ans[0] : null;
    }

    protected Object fromOneTOne(Field field, String value, Connection connection)
            throws Exception {
        Object ans = field.getType().getDeclaredConstructor().newInstance();
        ((ObjectBdd<?>) ans).configId(value);
        ((ObjectBdd<?>) ans).setRecursive(true);
        ans = ((ObjectBdd<?>) ans).findById(connection);
        return ans;
    }

    public abstract String extractId();

    public void activeAll(ObjectBdd<?>[] liste) {
        for (int i = 0; i < liste.length; i++) {
            liste[i].activeAll();
        }
    }

    protected Object haRelation(ForeignKey fr, Field field, String value, Connection connection) throws Exception {
        switch (fr.relation()) {
            case OneToOne:
                return this.fromOneTOne(field, value, connection);
            case ManyToOne:
                return this.fromOneTOne(field, value, connection);
            default:
                throw new Exception("No relation Match");
        }
    }

    public void setField(Method meth, String type, String value, Connection connection) throws Exception {
        Object val = OwnTool.cast(value, type);
        meth.setAccessible(true);
        meth.invoke(this, val);
    }

    public void setField(Field field, String type, String value, Connection connection) throws Exception {
        Object val = OwnTool.cast(value, type);
        Object temp = val;
        field.setAccessible(true);
        try {
            ForeignKey fr = this.getForeignKey(field);
            if (!this.isRecursive()) {
                temp = this.haRelation(fr, field, value, connection);
            }
        } catch (NoForeign e) {
        }
        field.set(this, temp);
    }

    public T toObject(CTable table, String[] type, String[] value, Connection connection)
            throws Exception {
        T ans = (T) this.getClass().getDeclaredConstructor().newInstance();
        table.loadAttr(ans, type, value, connection);
        if (!this.isRecursive()) {
            table.loadManyForeign(ans, connection);
        }
        return ans;
    }

    public T[] toObject(CTable table, ArrayList<String[]> liste,
            Connection connection)
            throws Exception {
        T[] ans = (T[]) Array.newInstance(this.getClass(), liste.size() - 1);
        for (int i = 1; i < liste.size(); i++) {
            ans[i - 1] = this.toObject(table, liste.get(0), liste.get(i), connection);
        }
        return ans;
    }

    public boolean isFromBase() {
        return fromBase;
    }

    public void desactiveBase() {
        this.setFromBase(false);
    }

    public void activeBase() {
        this.setFromBase(true);
    }

    public void setFromBase(boolean fromBase) {
        this.fromBase = fromBase;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public HCForm toInsForm(EForm eform) throws NoSuchMethodException, SecurityException {
        HCForm ans = new HCForm(this, eform);
        return ans;
    }

    public boolean isIschecked() {
        return ischecked;
    }

    public void desactiveCheck() {
        this.setIschecked(false);
    }

    public void activeCheck() {
        this.setIschecked(true);
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public boolean isAllocated() {
        return isAllocated;
    }

    public void setAllocated(boolean isAllocated) {
        this.isAllocated = isAllocated;
    }

    public String getPureValue() {
        return pureValue;
    }

    public void setPureValue(String pureValue) {
        this.pureValue = pureValue;
    }

    public void loadPureValue() {
        this.setPureValue(this.toString());
    }

    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    public void loadIdValue() {
        this.setIdValue(this.extractId());
    }

    public void loadFront() {
        this.loadIdValue();
        this.loadPureValue();
    }

    public CTable getTable() {
        return table;
    }

    public void setTable(CTable table) {
        this.table = table;
    }

    public BaseState getBaseState() {
        return baseState;
    }

    public void setBaseState(BaseState baseState) {
        this.baseState = baseState;
    }

}
