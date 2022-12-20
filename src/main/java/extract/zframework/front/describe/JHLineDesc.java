package extract.zframework.front.describe;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import extract.zframework.front.except.IsArray;
import extract.zframework.hform.HLine;

import java.awt.*;

public class JHLineDesc extends JPanel {
    String label;
    String value;
    JLabel jlabel;
    JLabel jValue;
    HLine hline;
    LayoutManager mLayout;

    public JHLineDesc(HLine hline)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IsArray {
        this.setHline(hline);
        this.load();
        this.makeLayout();
        this.putContraint();
    }

    public void putContraint() {
        ((SpringLayout) this.getmLayout()).putConstraint(SpringLayout.WEST, this.getjValue(), 5, SpringLayout.EAST,
                this.getJlabel());
    }

    public void makeLayout() {
        this.setmLayout(new SpringLayout());
        this.setLayout(this.getmLayout());
    }

    public void load() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.loadLabel();
        this.loadValue();
    }

    protected void loadValue() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object object = this.getHline().getMeth().invoke(this.getHline().getObject());
        this.setValue(String.valueOf(object));
    }

    protected void loadLabel() {
        this.setLabel(this.getHline().getLabel());
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
        JLabel jlabel = new JLabel();
        jlabel.setText(this.getLabel());
        jlabel.setPreferredSize(new Dimension(200, 30));
        this.setJlabel(jlabel);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        JLabel jlabel = new JLabel();
        jlabel.setText(this.getValue());
        this.setjValue(jlabel);
    }

    public JLabel getJlabel() {
        return jlabel;
    }

    public void setJlabel(JLabel jlabel) {
        this.jlabel = jlabel;
        this.add(jlabel);
    }

    public JLabel getjValue() {
        return jValue;
    }

    public void setjValue(JLabel jValue) {
        this.jValue = jValue;
        this.add(this.getjValue());
    }

    public HLine getHline() {
        return hline;
    }

    public void verifArray(HLine hline) throws IsArray {
        if (hline.getMeth().getReturnType().isArray()) {
            throw new IsArray();
        }
    }

    public void setHline(HLine hline) throws IsArray {
        this.verifArray(hline);
        this.hline = hline;
    }

    public LayoutManager getmLayout() {
        return mLayout;
    }

    public void setmLayout(LayoutManager mLayout) {
        this.mLayout = mLayout;
    }

}
