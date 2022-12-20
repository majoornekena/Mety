package extract.zframework.front.describe;

import javax.swing.JLabel;

import extract.zframework.annotation.EForm;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.JHLine;
import extract.zframework.front.button.general.ExtractButton;
import extract.zframework.hform.HLine;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class JHTr extends JHLine {

    public JHTr(HLine hLine, ObjectBdd<?> object) throws Exception {
        super(hLine, EForm.Liste, object);
    }

    @Override
    public void init() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        super.init();
        ExtractButton extractButton = new EBListe(this.getDao());
        for (int i = 0; i < extractButton.getButton().size(); i++) {
            this.add(extractButton.getButton().get(i));
        }
    }

    @Override
    public void addComponent(HLine hline) {
        String str = "";
        try {
            str = String.valueOf(hline.invokeGet());
        } catch (Exception e) {
            str = e.getMessage();
            e.printStackTrace();
        }
        JLabel jLabel = new JLabel(str);
        jLabel.setPreferredSize(new Dimension(200, 30));
        this.add(jLabel);
    }

    @Override
    public Object loadObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        return this.getDao();
    }

}
