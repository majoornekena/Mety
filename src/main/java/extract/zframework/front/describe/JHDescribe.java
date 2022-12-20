package extract.zframework.front.describe;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JPanel;

import extract.zframework.annotation.EForm;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.JHShow;
import extract.zframework.front.JHTextField;
import extract.zframework.front.button.general.JHPButton;
import extract.zframework.hform.HLine;

public class JHDescribe extends JHShow {
    public JHDescribe(ObjectBdd<?> object)
            throws Exception {
        super(object, EForm.DESC);
    }

    public void loadTabTitle(HLine hLine, ObjectBdd<?>... dao) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        ObjectBdd<?> d = (ObjectBdd<?>) hLine.getMeth().getReturnType().getComponentType().getDeclaredConstructor()
                .newInstance();
        super.loadTabTitle(hLine, d);
    }

    @Override
    public void doAfter() {
        JHPButton btn = new JHPButton(this.getGenericDao());
        this.add(btn);
    }

    @Override
    public void addNormalComponent(HLine hLine) throws Exception {
        if (hLine.isForm()) {
            JPanel pan = new JHTextField(hLine);
            this.add(pan);
        } else {
            JHLineDesc liste = new JHLineDesc(hLine);
            this.add(liste);
        }
    }

    public void loadLineDesc(HLine hLine, ObjectBdd<?> dao) throws Exception {
        JHTr jhTr = new JHTr(hLine, dao);
        this.add(jhTr);
    }

    @Override
    public void addArrayComponent(HLine hLine) throws Exception {
        ObjectBdd<?>[] liste = (ObjectBdd<?>[]) hLine.invokeGet();
        for (int i = 0; i < liste.length; i++) {
            try {
                this.loadLineDesc(hLine, liste[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
