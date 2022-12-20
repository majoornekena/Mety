package extract.zframework.front;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SpringLayout.Constraints;

public class JHListeGen extends JPanel {

    public JHListeGen() {
        this.setLayout(new SpringLayout());
    }

    public void arrange() {
        Component[] components = this.getComponents();
        SpringLayout sp = (SpringLayout) this.getLayout();
        Spring ypad = Spring.constant(5);
        Spring ySpring = ypad;
        Spring xSpring = Spring.constant(5);
        for (int i = 0; i < components.length; i++) {
            Constraints cons = sp.getConstraints(components[i]);
            cons.setY(ySpring);
            cons.setX(xSpring);
            ySpring = Spring.sum(ypad, cons.getConstraint(SpringLayout.NORTH));
        }
    }
}
