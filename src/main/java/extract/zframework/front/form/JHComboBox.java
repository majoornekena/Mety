package extract.zframework.front.form;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComboBox;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.hform.HLine;

public class JHComboBox extends JComboBox<Object> {
    HLine hLine;

    public JHComboBox(HLine hLine) throws Exception {
        super(JHComboBox.init(hLine));
        this.sethLine(hLine);
        this.setDefault();
        this.addActionListener(this);
        this.loadObject();
    }

    public void setDefault() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.gethLine().hasQualifier()) {
            ObjectBdd<?> objectBdd = (ObjectBdd<?>) this.gethLine().invokeQualifier();
            if (objectBdd != null) {
                this.setSelectedItem(objectBdd);
            }
        }
    }

    public void loadObject() {
        try {
            this.gethLine().getMeth().invoke(this.gethLine().getObject(), this.getSelectedObjects()[0]);
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public static ObjectBdd<?>[] init(HLine hLine) throws Exception {
        Object obj = hLine.getMeth().getParameterTypes()[0].getDeclaredConstructor().newInstance();
        if (obj instanceof ObjectBdd) {
            ObjectBdd<?>[] liste = ((ObjectBdd<?>) obj).find(null);
            System.out.println("jhcombo => obj : "+obj.getClass().getName());
            return liste;
        } else {
            throw new Exception();
        }
    }

    public void load() {
        try {
            this.gethLine().getMeth().invoke(this.gethLine().getObject(), this.getSelectedItem());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.load();
    }

    public HLine gethLine() {
        return hLine;
    }

    public void sethLine(HLine hLine) {
        this.hLine = hLine;
    }

}
