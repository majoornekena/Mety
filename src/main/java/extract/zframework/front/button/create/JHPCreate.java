package extract.zframework.front.button.create;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class JHPCreate extends JPanel {
    JHCreate jhCreate;

    public JHPCreate() {
        this.setLayout(new SpringLayout());
    }

    public JHCreate getJhCreate() {
        return jhCreate;
    }

    public void setJhCreate(JHCreate jhCreate) {
        this.jhCreate = jhCreate;
        this.add(jhCreate);
    }

}
