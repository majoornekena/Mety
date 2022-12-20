package extract.zframework.front.button.create;

import java.awt.event.*;

import javax.swing.JOptionPane;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.button.general.JHButton;

public class JHCreate extends JHButton {

    public JHCreate(ObjectBdd<?> dao) throws Exception {
        super("Sauvegarder", dao);
    }

    @Override
    public void onClick(ActionEvent e) {
        try {
            this.getObject().create(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void checkBefore(ObjectBdd<?> o) throws Exception {

    }

}
