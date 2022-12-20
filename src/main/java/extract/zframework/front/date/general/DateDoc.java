package extract.zframework.front.date.general;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DateDoc implements DocumentListener {
    JHDate jhDate;

    public DateDoc(JHDate jhDate) {
        this.setJhDate(jhDate);
        this.getJhDate().extractValue();
    }

    public void insertUpdate(DocumentEvent e) {
        if (this.getJhDate().getLastText() == null ? true : this.getJhDate().getLastText() == "") {
            this.getJhDate().setLastText(this.getJhDate().getText());
        }
        try {
            this.getJhDate().checkAfter();
            this.getJhDate().extractValue();
        } catch (FormatDateExcept e1) {
            SwingUtilities.invokeLater(new HDateModifier(this.getJhDate()));
        }
    }

    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

    }

    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

    }

    public JHDate getJhDate() {
        return jhDate;
    }

    public void setJhDate(JHDate jhDate) {
        this.jhDate = jhDate;
    }

}
