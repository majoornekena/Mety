package extract.zframework.front.button.link.add;

import java.awt.event.ActionEvent;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.button.general.JHButton;

public class JHBFormable extends JHButton {

    public JHBFormable(ObjectBdd<?> object) throws Exception {
        super("", object);
        this.setText(((BFormable) object).getBF_label());
    }

    @Override
    public void onClick(ActionEvent e) throws Exception {
        ((BFormable) this.getObject()).doBFormable();
    }

    @Override
    public void checkBefore(ObjectBdd<?> o) throws Exception {
        if (!(o instanceof BFormable)) {
            throw new Exception("Object not BFormable");
        }
    }

}
