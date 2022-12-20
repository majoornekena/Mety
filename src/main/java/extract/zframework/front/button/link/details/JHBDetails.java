package extract.zframework.front.button.link.details;

import java.awt.event.ActionEvent;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.button.general.JHButton;

public class JHBDetails extends JHButton {

    public JHBDetails(ObjectBdd<?> object) throws Exception {
        super("", object);
        this.setText(((BDetails) object).getBD_label());
    }

    @Override
    public void onClick(ActionEvent e) throws Exception {
        ((BDetails) this.getObject()).doBDetails();
    }

    @Override
    public void checkBefore(ObjectBdd<?> o) throws Exception {
        if (!(o instanceof BDetails)) {
            throw new Exception("Object not BDetails");
        }
    }

}
