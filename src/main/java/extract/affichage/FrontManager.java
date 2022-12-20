package extract.affichage;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import extract.zframework.annotation.EForm;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.JFrame_Dab;
import extract.zframework.front.describe.JHDescribe;
import extract.zframework.front.form.JHForm;
import extract.zframework.front.jmenu.JHMenuItem;
import extract.zframework.front.liste.JHListe;
import extract.zframework.tools.OwnTool;

public class FrontManager {
    String title;
    ObjectBdd<?> lastObject;
    ObjectBdd<?> currentobject;
    ObjectBdd<?>[] lastArray;
    ObjectBdd<?>[] currentArray;
    JFrame_Dab frame;
    EForm lastState;
    EForm state = EForm.DESC;

    public FrontManager(String title, ObjectBdd<?> currentobject) throws Exception {
        this.setTitle(title);
        this.setCurrentobject(currentobject);
        this.init();
    }

    public void init() throws Exception {
        this.loadJframe();
        this.loadMenu();
        this.loadFront();
    }

    public void show() {
        this.getFrame().setVisible(true);
    }

    public void loadMenu() {
        try {
            JHMenuItem[] liste = OwnTool.loadAllItem("hfuntionality");
            JMenuBar jBar = new JMenuBar();
            for (int i = 0; i < liste.length; i++) {
                jBar.add(liste[i]);
            }
            this.getFrame().setJMenuBar(jBar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDesc() throws Exception {
        JHDescribe jhdesc = new JHDescribe(this.getCurrentobject());
        this.add(jhdesc);
    }

    public void loadForm() throws Exception {
        JHForm form = new JHForm(this.getCurrentobject());
        this.add(form);
    }

    public void loadListe() throws Exception {
        JHListe liste = new JHListe(this.getCurrentArray());
        this.add(liste);
    }

    public void add(JPanel comp) {
        this.getFrame().add_panel(comp);
    }

    public void removeAll() {
        this.getFrame().get_panel().removeAll();
    }

    public void loadFront() throws Exception {
        this.removeAll();
        switch (this.getState()) {
            case DESC:
                this.loadDesc();
                break;
            case Form:
                this.loadForm();
                break;
            case Liste:
                this.loadListe();
                break;
            default:
                break;
        }
        this.referesh();
    }

    public void loadJframe() {
        JFrame_Dab frame = new JFrame_Dab(this.getTitle());
        this.setFrame(frame);
    }

    public void referesh() {
        this.getFrame().refresh();
    }

    public ObjectBdd<?> getCurrentobject() {
        return currentobject;
    }

    public void setCurrentobject(ObjectBdd<?> currentobject) {
        this.setLastObject(this.getCurrentobject());
        this.currentobject = currentobject;
    }

    public ObjectBdd<?>[] getCurrentArray() {
        return currentArray;
    }

    public void setCurrentArray(ObjectBdd<?>[] currentArray) {
        this.setLastArray(this.getCurrentArray());
        this.currentArray = currentArray;
    }

    public JFrame_Dab getFrame() {
        return frame;
    }

    public void setFrame(JFrame_Dab frame) {
        this.frame = frame;
    }

    public EForm getState() {
        return state;
    }

    public void setState(EForm state) throws Exception {
        this.setLastState(this.getState());
        this.state = state;
        this.loadFront();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObjectBdd<?> getLastObject() {
        return lastObject;
    }

    public void setLastObject(ObjectBdd<?> lastObject) {
        this.lastObject = lastObject;
    }

    public ObjectBdd<?>[] getLastArray() {
        return lastArray;
    }

    public void setLastArray(ObjectBdd<?>[] lastArray) {
        this.lastArray = lastArray;
    }

    public EForm getLastState() {
        return lastState;
    }

    public void setLastState(EForm lastState) {
        this.lastState = lastState;
    }

}
