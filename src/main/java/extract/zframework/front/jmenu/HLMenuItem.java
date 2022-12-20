package extract.zframework.front.jmenu;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class HLMenuItem implements MenuListener {
    JHMenuItem jhMenuItem;

    public HLMenuItem(JHMenuItem jhMenuItem) {
        this.setJhMenuItem(jhMenuItem);
    }

    public JHMenuItem getJhMenuItem() {
        return jhMenuItem;
    }

    public void setJhMenuItem(JHMenuItem jhMenuItem) {
        this.jhMenuItem = jhMenuItem;
    }

    public void menuSelected(MenuEvent e) {
        this.getJhMenuItem().onClick();
    }

    public void menuDeselected(MenuEvent e) {
        // TODO Auto-generated method stub

    }

    public void menuCanceled(MenuEvent e) {
        // TODO Auto-generated method stub

    }
}
