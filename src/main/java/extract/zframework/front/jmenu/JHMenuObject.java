package extract.zframework.front.jmenu;

import extract.affichage.Entree;
import extract.zframework.annotation.EForm;
import extract.zframework.dao.ObjectBdd;

public class JHMenuObject extends JHMenuItem {
    ObjectBdd<?> objectBdd;

    public JHMenuObject(String label, EForm state, ObjectBdd<?> objectBdd) {
        super(label, state);
        this.setText(label);
        this.setObjectBdd(objectBdd);
    }

    @Override
    public void onClick() {
        try {
            Entree.front.setCurrentobject(this.getObjectBdd());
            Entree.front.setState(this.getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObjectBdd<?> getObjectBdd() {
        return objectBdd;
    }

    public void setObjectBdd(ObjectBdd<?> objectBdd) {
        this.objectBdd = objectBdd;
    }

}
