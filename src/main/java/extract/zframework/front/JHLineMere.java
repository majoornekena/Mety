package extract.zframework.front;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SpringLayout.Constraints;

public class JHLineMere extends JPanel {

    public JHLineMere() {
        this.addLayout();
    }

    public void addLayout() {
        SpringLayout sp = new SpringLayout();
        this.setLayout(sp);
    }

    public void arrange() {
        Component[] components = this.getComponents();
        SpringLayout sp = (SpringLayout) this.getLayout();
        Spring xpad = Spring.constant(5);
        Spring ySpring = Spring.constant(5);
        Spring xSpring = xpad;
        for (int i = 0; i < components.length; i++) {
            Constraints cons = sp.getConstraints(components[i]);
            cons.setX(xSpring);
            xSpring = Spring.sum(xpad, cons.getConstraint("East"));
            cons.setY(ySpring);
        }
    }

}
