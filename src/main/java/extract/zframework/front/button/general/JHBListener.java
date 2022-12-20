package extract.zframework.front.button.general;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class JHBListener implements ActionListener {

    JHButton jhButton;

    public JHBListener(JHButton jhButton) {
        this.setJhButton(jhButton);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            this.getJhButton().onClick(e);
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    public JHButton getJhButton() {
        return jhButton;
    }

    public void setJhButton(JHButton jhButton) {
        this.jhButton = jhButton;
    }

}
