package extract.zframework.front.form;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;

import extract.zframework.annotation.EForm;
import extract.zframework.front.JHLine;
import extract.zframework.hform.HLine;

public class JHLineForm extends JHLine {

    public JHLineForm(HLine hLine) throws Exception {
        super(hLine, EForm.Form);
    }

    @Override
    public void addComponent(HLine hLine) {
        JHSetters jhSetters = new JHSetters(hLine);
        JComponent jc = jhSetters.getjComponent();
        jc.setPreferredSize(new Dimension(200, 25));
        this.add(jc);
    }

    @Override
    public Object loadObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        return this.loadNormalObject();
    }

}
