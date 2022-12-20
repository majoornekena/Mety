package extract.zframework.front.liste;

import java.awt.*;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.JHListeGen;
import extract.zframework.front.describe.JHTr;

public class JHListe extends JHListeGen {
    ObjectBdd<?>[] liste;

    public JHListe(ObjectBdd<?>[] liste) throws Exception {
        // super();
        this.setLayout(new GridLayout(20, 1));
        this.setListe(liste);
        this.init();
        // this.arrange();
    }

    public void addRow(ObjectBdd<?> dao) throws Exception {
        JHTr jhTr = new JHTr(null, dao);
        this.add(jhTr);
    }

    public void init() throws Exception {
        for (int i = 0; i < liste.length; i++) {
            this.addRow(liste[i]);
        }
    }

    public ObjectBdd<?>[] getListe() {
        return liste;
    }

    public void setListe(ObjectBdd<?>[] liste) {
        this.liste = liste;
    }

}
