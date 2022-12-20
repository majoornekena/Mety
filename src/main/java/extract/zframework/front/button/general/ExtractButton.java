package extract.zframework.front.button.general;

import java.util.ArrayList;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.button.link.add.JHBFormable;
import extract.zframework.front.button.link.details.JHBDetails;
import extract.zframework.front.button.link.genere.JHBGenerer;
import extract.zframework.front.button.link.liste.JHBListe;

public abstract class ExtractButton {
    ObjectBdd<?> dao;
    ArrayList<JHButton> button = new ArrayList<JHButton>();

    public ExtractButton(ObjectBdd<?> dao) {
        this.setDao(dao);
        this.init();
    }

    public abstract void init();

    public void loadGenere() {
        try {
            JHBGenerer btn = new JHBGenerer(this.getDao());
            this.addBtn(btn);
        } catch (Exception e) {
        }
    }

    public void loadListe() {
        try {
            JHBListe btn = new JHBListe(this.getDao());
            this.addBtn(btn);
        } catch (Exception e) {
        }
    }

    public void loadDetails() {
        try {
            JHBDetails btn = new JHBDetails(this.getDao());
            this.addBtn(btn);
        } catch (Exception e) {
        }
    }

    public void loadAdd() {
        try {
            JHBFormable btn = new JHBFormable(this.getDao());
            this.addBtn(btn);
        } catch (Exception e) {

        }
    }

    public void addBtn(JHButton jhButton) {
        this.getButton().add(jhButton);
    }

    public ObjectBdd<?> getDao() {
        return dao;
    }

    public void setDao(ObjectBdd<?> dao) {
        this.dao = dao;
    }

    public ArrayList<JHButton> getButton() {
        return button;
    }

    public void setButton(ArrayList<JHButton> button) {
        this.button = button;
    }

}
