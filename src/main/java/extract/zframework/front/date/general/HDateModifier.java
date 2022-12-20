package extract.zframework.front.date.general;

import javax.swing.JOptionPane;

public class HDateModifier implements Runnable {
    JHDate jhDate;

    public HDateModifier(JHDate jhDate) {
        this.setJhDate(jhDate);
    }

    public void run() {
        String message = "%s format cannot be used as date";
        JOptionPane.showMessageDialog(null, String.format(message, this.getJhDate().getText()));
        this.getJhDate().setText(this.getJhDate().getLastText());
    }

    public JHDate getJhDate() {
        return jhDate;
    }

    public void setJhDate(JHDate jhDate) {
        this.jhDate = jhDate;
    }

}
