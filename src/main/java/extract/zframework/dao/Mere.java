package extract.zframework.dao;

import java.sql.Connection;

import extract.zframework.hdata.CId;

public abstract class Mere<T extends ObjectBdd<?>> extends ObjectBdd<T> {

    Fille<?>[] filles;
    Class<?> fillesCls;

    public Mere(Class<?> fillesCls) {
        this.setFillesCls(fillesCls);
    }

    public abstract CId extractMId();

    public void verifFille() throws FilleExist {
        if (this.getFilles() != null) {
            throw new FilleExist();
        }
    }

    public void loadFilles(Connection connection) throws Exception {
        this.verifFille();
        Fille<?> f = (Fille<?>) this.getFillesCls().getDeclaredConstructor().newInstance();
        f.setMere(this);
        f.configMereId();
        Fille<?>[] ans = f.getByMere(connection);
        this.setFilles(ans);
    }

    public Fille<?>[] getFilles(Connection connection) throws Exception {
        try {
            this.loadFilles(connection);
        } catch (FilleExist e) {

        }
        return this.getFilles();
    }

    public Fille<?>[] getFilles() {
        return filles;
    }

    public void setFilles(Fille<?>[] filles) {
        this.filles = filles;
    }

    @Override
    public void create(Connection connection) throws Exception {
        super.create(connection);
        for (int i = 0; i < filles.length; i++) {
            this.getFilles()[i].configMereId();
            this.getFilles()[i].create(connection);
        }
    }

    public Class<?> getFillesCls() {
        return fillesCls;
    }

    public void setFillesCls(Class<?> fillesCls) {
        this.fillesCls = fillesCls;
    }

}
