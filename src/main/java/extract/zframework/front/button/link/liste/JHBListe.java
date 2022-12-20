package extract.zframework.front.button.link.liste;

import java.awt.event.ActionEvent;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.button.general.JHButton;

public class JHBListe extends JHButton {

    public JHBListe(ObjectBdd<?> object) throws Exception {
        super("", object);
        this.setText(((BListable) object).getBL_label());
    }

    @Override
    public void onClick(ActionEvent e) throws Exception {
        ((BListable) this.getObject()).doList();
    }

    @Override
    public void checkBefore(ObjectBdd<?> o) throws Exception {
        if (!(o instanceof BListable)) {
            throw new Exception("Object not BListable");
        }
    }

}
