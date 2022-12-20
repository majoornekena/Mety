package extract.zframework.front.except.show;

import javax.swing.JOptionPane;
import java.awt.*;

public class ShowSimple extends JOptionPane {
    String message;
    Container cparent;

    public ShowSimple(String message, Container cparent) {
        this.setMessage(message);
        this.setCparent(cparent);
    }

    public void show() {
        showMessageDialog(this.getCparent(), this.getMessage());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Container getCparent() {
        return cparent;
    }

    public void setCparent(Container cparent) {
        this.cparent = cparent;
    }

}
