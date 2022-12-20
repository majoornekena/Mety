package extract.zframework.front;

import javax.swing.*;
import java.awt.*;

public class JFrame_Dab extends JFrame {
    JPanel panel;

    public JFrame_Dab(String titre, int[] pos, int[] grid) {
        super(titre);
        this.setSize(pos[0], pos[1]);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SwingUtilities.updateComponentTreeUI(this);
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(grid[0], grid[1]));
        this.add(this.panel);
    }

    public JFrame_Dab(String titre) {
        this(titre, new int[] { 1300, 700 }, new int[] { 1, 1 });
    }

    public void setFull() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void add_panel(JComponent pan) {
        this.panel.add(pan);
    }

    public JPanel get_panel() {
        return this.panel;
    }

    public void clear_pan() {
        this.panel.removeAll();
    }

    public void set_Layout(int[] grid) {
        this.panel.setLayout(new GridLayout(grid[0], grid[1]));
        SwingUtilities.updateComponentTreeUI(this);

    }
}