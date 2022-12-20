package extract.zframework.dao;

import java.sql.Connection;

import extract.zframework.annotation.relation.Attribut;
import extract.zframework.annotation.relation.Id;

public class User<T extends ObjectBdd<?>> extends ObjectBdd<T> {
    @Id
    @Attribut
    int id;

    @Attribut(varchar = true)
    String email;

    @Attribut(varchar = true)
    String password;

    @Override
    public void configId(String id) {
        this.setId(Integer.parseInt(id));
    }

    @Override
    public void activeAll() {
        this.activeBase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
