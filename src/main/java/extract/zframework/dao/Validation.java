package extract.zframework.dao;

import java.sql.Connection;
import java.sql.Date;

import extract.zframework.annotation.relation.Attribut;
import extract.zframework.annotation.relation.ForeignKey;
import extract.zframework.annotation.relation.Id;

public class Validation<T extends ObjectBdd<?>> extends ObjectBdd<T> {
    @Id
    @Attribut
    int id;

    @ForeignKey
    @Attribut(value = "iduser")
    User<?> user;

    @Attribut(varchar = true)
    Date dateins;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void configId(String id) {
        this.setId(Integer.parseInt(id));
    }

    @Override
    public void activeAll() {
        this.activeBase();
    }

    @Override
    public void verif(Connection connection) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public String extractId() {
        return String.valueOf(this.getId());
    }

}
