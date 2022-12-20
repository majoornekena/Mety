package extract.zframework.front.jmenu;

import extract.affichage.Entree;
import extract.zframework.annotation.EForm;
import extract.zframework.dao.ObjectBdd;

public class JHMenuArray extends JHMenuItem {
    ObjectBdd<?>[] array;

    public JHMenuArray(String label, EForm state, ObjectBdd<?>[] array) {
        super(label, state);
        this.setArray(array);
        this.setText(label);
    }

    @Override
    public void onClick() {
        Entree.front.setCurrentArray(this.getArray());
        try {
            Entree.front.setState(this.getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObjectBdd<?>[] getArray() {
        return array;
    }

    public void setArray(ObjectBdd<?>[] array) {
        this.array = array;
    }

}
