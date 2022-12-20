package extract.zframework.front;

import javax.swing.JPanel;

import extract.zframework.annotation.EForm;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.except.IsArray;
import extract.zframework.hform.HCForm;
import extract.zframework.hform.HLine;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public abstract class JHShow extends JPanel {
    HCForm hcForm;
    EForm eForm;
    ObjectBdd<?> genericDao;

    public JHShow(ObjectBdd<?> object, EForm eForm) throws Exception {
        this.addLayout();
        this.setGenericDao(object);
        this.seteForm(eForm);
        this.loadHcForm();
        this.init();
    }

    public void loadHcForm() throws NoSuchMethodException, SecurityException {
        this.setHcForm(this.getGenericDao().toInsForm(this.geteForm()));
    }

    public void generateTitle(HCForm hform) {
        JHTitle htitle = new JHTitle(hform.getDao(), this.geteForm());
        this.add(htitle);
    }

    public abstract void doAfter();

    public abstract void addNormalComponent(HLine hLine) throws Exception;

    public abstract void addArrayComponent(HLine hLine) throws Exception;

    public void init() throws Exception {
        this.generateTitle(this.getHcForm());
        for (int i = 0; i < this.getHcForm().gethLines().length; i++) {
            try {
                this.addNormalComponent(this.getHcForm().gethLines()[i]);
            } catch (IsArray e) {
                this.loadTabTitle(this.getHcForm().gethLines()[i]);
                this.addArrayComponent(this.getHcForm().gethLines()[i]);
            }
        }
        this.doAfter();
    }

    public void loadTabTitle(HLine hLine, ObjectBdd<?>... dao) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        JPanel pan = new JHLineTitle(hLine, this.geteForm(), dao);
        this.add(pan);
    }

    public void addLayout() {
        this.setLayout(new GridLayout(20, 1));
    }

    public HCForm getHcForm() {
        return hcForm;
    }

    public void setHcForm(HCForm hcForm) {
        this.hcForm = hcForm;
    }

    public EForm geteForm() {
        return eForm;
    }

    public void seteForm(EForm eForm) {
        this.eForm = eForm;
    }

    public ObjectBdd<?> getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(ObjectBdd<?> genericDao) {
        this.genericDao = genericDao;
    }

}
