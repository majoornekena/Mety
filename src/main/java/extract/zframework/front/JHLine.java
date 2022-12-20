package extract.zframework.front;

import java.lang.reflect.InvocationTargetException;

import extract.zframework.annotation.EForm;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.hform.HCForm;
import extract.zframework.hform.HLine;

public abstract class JHLine extends JHLineMere {
    HLine hline;
    Object object;
    EForm eform;
    ObjectBdd<?> dao;

    // public abstract void test();

    public JHLine(HLine hline, EForm eform, ObjectBdd<?>... dao) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        this.setDao(dao);
        this.setEform(eform);
        this.setHline(hline);
        this.init();
        this.arrange();
    }

    public Object loadNormalObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        return this.getHline().getCls().getDeclaredConstructor().newInstance();
    }

    public abstract Object loadObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException;

    public HCForm getHForm(EForm eForm) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        Object obj = this.loadObject();
        this.setObject(obj);
        HCForm hcForm = ((ObjectBdd<?>) obj).toInsForm(eForm);
        return hcForm;
    }

    public HLine getHline() {
        return hline;
    }

    public void addFront(HCForm hcform) {
        this.addLayout();
        for (int i = 0; i < hcform.gethLines().length; i++) {
            this.addComponent(hcform.gethLines()[i]);
        }
    }

    public void setHline(HLine hline) {
        this.hline = hline;
    }

    public void init() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        HCForm hcform = this.getHForm(this.getEform());
        this.addFront(hcform);
    }

    public abstract void addComponent(HLine hLine);

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public EForm getEform() {
        return eform;
    }

    public void setEform(EForm eform) {
        this.eform = eform;
    }

    public ObjectBdd<?> getDao() {
        return dao;
    }

    public void setDao(ObjectBdd<?>... dao) {
        if (dao.length > 0) {
            this.dao = dao[0];
        }
    }
}
