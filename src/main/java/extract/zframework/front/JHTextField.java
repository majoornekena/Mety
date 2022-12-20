package extract.zframework.front;

import javax.swing.*;

import extract.zframework.front.except.IsArray;
import extract.zframework.front.form.JHSetters;
import extract.zframework.hform.HLine;

import java.awt.*;
import java.util.ArrayList;

public class JHTextField extends JPanel {
    String label;
    String placeholder;
    JLabel jLabel;
    JComponent jTextField;
    LayoutManager mLayout;
    HLine hLine;
    int xy = 0;

    @Override
    public Component add(Component comp) {
        this.xy++;
        return super.add(comp);
    }

    public JHTextField(HLine hLine) throws IsArray {
        this.verifMethod(hLine);
        this.setLabel(hLine.getLabel());
        this.sethLine(hLine);
        this.setPlaceholder(hLine.getPlaceholder());
        this.setmLayout(new SpringLayout());
        this.setLayout(this.getmLayout());
        this.addLabel();
        this.addTextField();
        this.show();
    }

    public void verifMethod(HLine hLine) throws IsArray {
        Class<?>[] liste = hLine.getMeth().getParameterTypes();
        for (int i = 0; i < liste.length; i++) {
            if (liste[i] == ArrayList.class) {
                throw new IsArray();
            }
        }
    }

    public void show() {
        ((SpringLayout) this.getmLayout()).putConstraint(SpringLayout.WEST, this.getjTextField(), 5, SpringLayout.EAST,
                this.getjLabel());
    }

    public JComponent detJTextField() {
        JHSetters jhSetters = new JHSetters(this.gethLine());
        return jhSetters.getjComponent();
    }

    public void addTextField() {
        JComponent jTextField = this.detJTextField();
        jTextField.setPreferredSize(new Dimension(200, 30));
        jTextField.setMaximumSize(new Dimension(200, 30));
        jTextField.setMinimumSize(new Dimension(200, 30));
        this.setjTextField(jTextField);
    }

    public void addLabel() {
        JLabel jLabel = new JLabel();
        jLabel.setText(this.getLabel());
        jLabel.setPreferredSize(new Dimension(200, 30));
        this.setjLabel(jLabel);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public void setjLabel(JLabel jLabel) {
        this.jLabel = jLabel;
        this.add(jLabel);
    }

    public JComponent getjTextField() {
        return this.jTextField;
    }

    public void setjTextField(JComponent jTextField) {
        this.jTextField = jTextField;
        this.add(jTextField);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String... placeholder) {
        if (placeholder.length > 0) {
            this.placeholder = placeholder[0];
        } else {
            this.placeholder = "";
        }
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public LayoutManager getmLayout() {
        return mLayout;
    }

    public void setmLayout(LayoutManager mLayout) {
        this.mLayout = mLayout;
    }

    public HLine gethLine() {
        return hLine;
    }

    public void sethLine(HLine hLine) {
        this.hLine = hLine;
    }

}
