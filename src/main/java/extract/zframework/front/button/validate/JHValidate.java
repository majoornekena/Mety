package extract.zframework.front.button.validate;

import java.awt.event.ActionEvent;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.dao.Validable;
import extract.zframework.front.button.general.JHButton;

public class JHValidate extends JHButton {

    public JHValidate(Validable object) throws Exception {
        super("Valider", (ObjectBdd<?>) object);
    }

    @Override
    public void onClick(ActionEvent e) throws Exception {
        ((Validable) this.getObject()).validate(null);
    }

    @Override
    public void checkBefore(ObjectBdd<?> o) throws Exception {
        // TODO Auto-generated method stub
        
    }

}
