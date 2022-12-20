package extract.zframework.front.describe;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.button.general.ExtractButton;

public class EBListe extends ExtractButton {

    public EBListe(ObjectBdd<?> dao) {
        super(dao);
    }

    @Override
    public void init() {
        this.loadDetails();
    }

}
