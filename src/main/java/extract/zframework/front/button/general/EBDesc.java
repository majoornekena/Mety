package extract.zframework.front.button.general;

import extract.zframework.dao.ObjectBdd;

public class EBDesc extends ExtractButton {

    public EBDesc(ObjectBdd<?> dao) {
        super(dao);
    }

    @Override
    public void init() {
        this.loadAdd();
        this.loadListe();
        this.loadGenere();
    }

}
