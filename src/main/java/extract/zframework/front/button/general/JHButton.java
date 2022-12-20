package extract.zframework.front.button.general;

import java.awt.event.*;

import javax.swing.JButton;

import extract.zframework.dao.ObjectBdd;

public abstract class JHButton extends JButton {
    ObjectBdd<?> object;

    public abstract void onClick(ActionEvent e) throws Exception;
    public abstract void checkBefore(ObjectBdd<?> o) throws Exception;

    public JHButton(String defaultLab, ObjectBdd<?> object) throws Exception {
        this(null, defaultLab, object);
    }

    public JHButton(String text, String defaultLab, ObjectBdd<?> object) throws Exception {
        super(text == null ? defaultLab : text);
        this.checkBefore(object);
        this.setObject(object);
        this.loadListener();
    }

    public void loadListener() {
        JHBListener listen = new JHBListener(this);
        this.addActionListener(listen);
    }

    public ObjectBdd<?> getObject() {
        return object;
    }

    public void setObject(ObjectBdd<?> object) {
        this.object = object;
    }

}
