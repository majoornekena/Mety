package extract.zframework.front.button.general;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.JHLineMere;

public class JHPButton extends JHLineMere {
    ObjectBdd<?> objectBdd;

    public JHPButton(ObjectBdd<?> objectBdd) {
        super();
        this.setObjectBdd(objectBdd);
        this.init();
        this.arrange();
    }

    public void load() {
        ExtractButton extractButton = new EBDesc(this.getObjectBdd());
        for (int i = 0; i < extractButton.getButton().size(); i++) {
            this.add(extractButton.getButton().get(i));
        }
    }

    public void init() {
        this.load();
        this.arrange();
    }

    public ObjectBdd<?> getObjectBdd() {
        return objectBdd;
    }

    public void setObjectBdd(ObjectBdd<?> objectBdd) {
        this.objectBdd = objectBdd;
    }

}
