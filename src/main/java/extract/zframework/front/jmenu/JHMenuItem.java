package extract.zframework.front.jmenu;

import javax.swing.JMenu;

import extract.zframework.annotation.EForm;

public abstract class JHMenuItem extends JMenu {
    String label;
    EForm state;

    public JHMenuItem(String label, EForm state) {
        super(label);
        this.setText(label);
        this.setLabel(label);
        this.setState(state);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public EForm getState() {
        return state;
    }

    public void setState(EForm state) {
        this.state = state;
    }

    public abstract void onClick();
}
