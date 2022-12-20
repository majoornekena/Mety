package extract.zframework.front.date.general;

import java.awt.event.*;

import extract.zframework.front.except.show.ShowSimple;

public class DateListener extends KeyAdapter {
    JHDate jhDate;

    public DateListener(JHDate jhDate) {
        this.setJhDate(jhDate);
    }

    public boolean verifKeyType(KeyEvent e) {
        char c = e.getKeyChar();
        boolean verif = true;
        verif &= (c >= '0') && (c <= '9');
        verif |= (c == KeyEvent.VK_BACK_SPACE);
        verif |= (c == KeyEvent.VK_DELETE);
        verif |= (c == KeyEvent.VK_SLASH);
        return verif;
    }

    public void verifKey(KeyEvent e) throws FormatDateExcept {
        if (!this.verifKeyType(e)) {
            String pathern = "%s cannot be used on date";
            pathern = String.format(pathern, e.getKeyChar());
            throw new FormatDateExcept(pathern);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        try {
            this.verifKey(e);
            this.getJhDate().check(e);
        } catch (FormatDateExcept e1) {
            ShowSimple simple = new ShowSimple(e1.getMessage(), null);
            simple.show();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public JHDate getJhDate() {
        return jhDate;
    }

    public void setJhDate(JHDate jhDate) {
        this.jhDate = jhDate;
    }

}
