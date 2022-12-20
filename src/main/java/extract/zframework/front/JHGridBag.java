package extract.zframework.front;

import javax.swing.JPanel;

import java.awt.*;

public class JHGridBag extends JPanel {

    int y = 0;
    int x = 0;
    int ipady = 50;
    GridBagConstraints gbc;

    public JHGridBag() {
        super();
        this.setLayout(new GridBagLayout());
        this.refresh();
    }

    public void refresh() {
        this.setGbc(new GridBagConstraints());

    }

    public void setHorizental() {
        this.getGbc().fill = GridBagConstraints.HORIZONTAL;
        this.setX(0);
    }

    public void addCmpHor(Component comp) {
        this.setHorizental();
        this.addCmp(comp);
    }

    public void addCmp(Component comp) {
        this.getGbc().weightx = 1;
        this.getGbc().weighty = 1;
        this.getGbc().ipady = this.getIpady();
        this.getGbc().gridx = this.getX();
        this.getGbc().gridy = this.getY();
        super.add(comp, this.getGbc());
    }

    public GridBagConstraints getNextGbc() {
        this.getGbc().weightx = 1;
        this.getGbc().ipady = this.getIpady();
        this.getGbc().gridx = this.getX();
        this.getGbc().gridy = this.getY();
        return this.getGbc();
    }

    public void incrementY() {
        this.setY(this.getY() + 1);
    }

    public void incrementX() {
        this.setX(this.getX() + 1);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getIpady() {
        return ipady;
    }

    public void setIpady(int ipady) {
        this.ipady = ipady;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public void setGbc(GridBagConstraints gbc) {
        this.gbc = gbc;
    }

}
