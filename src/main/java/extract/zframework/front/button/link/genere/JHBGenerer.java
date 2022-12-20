package extract.zframework.front.button.link.genere;

import java.awt.event.ActionEvent;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.button.general.JHButton;

public class JHBGenerer extends JHButton {

    public JHBGenerer(ObjectBdd<?> object) throws Exception {
        super("", object);
        this.setText(((BGenerer) object).getBG_label());
    }

    @Override
    public void onClick(ActionEvent e) throws Exception {
        ((BGenerer) this.getObject()).doBGenerer();
    }

    @Override
    public void checkBefore(ObjectBdd<?> o) throws Exception {        
        if (!(o instanceof BGenerer)) {
            throw new Exception("Object not BGenerer");
        }
    }

}
