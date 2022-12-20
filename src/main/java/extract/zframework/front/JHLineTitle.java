package extract.zframework.front;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JLabel;

import extract.zframework.annotation.EForm;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.hform.HLine;

public class JHLineTitle extends JHLine {

    public JHLineTitle(HLine hLine, EForm eForm, ObjectBdd<?>... dao)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        super(hLine, eForm, dao);
    }

    @Override
    public void addComponent(HLine hLine) {
        JLabel jLabel = new JLabel(hLine.getLabel());
        jLabel.setPreferredSize(new Dimension(200, 25));
        this.add(jLabel);
    }

    @Override
    public Object loadObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        switch (this.getEform()) {
            case Form:
                return this.loadNormalObject();
            case DESC:
                return this.getDao();
            case Liste:
                return this.getDao();
            default:
                return this.loadNormalObject();
        }
    }

}
