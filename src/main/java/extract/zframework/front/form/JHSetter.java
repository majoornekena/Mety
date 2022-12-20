package extract.zframework.front.form;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import extract.zframework.hform.HLine;
import extract.zframework.tools.OwnTool;

public class JHSetter extends JTextField implements DocumentListener {
    HLine hline;

    public JHSetter(HLine hline) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super(JHSetter.getPLaceholder(hline));
        this.setHline(hline);
        this.getDocument().addDocumentListener(this);
    }

    public static String getPLaceholder(HLine hline)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String ans = "";
        if (hline.hasQualifier()) {
            ans = hline.invokeQualifier().toString();
        } else {
            ans = hline.getPlaceholder();
        }
        return ans;
    }

    protected void extractValue() {
        String value = this.getText();
        String type = this.getHline().getMeth().getParameterTypes()[0].toString();
        try {
            Object castValue = OwnTool.cast(value, type);
            this.getHline().getMeth().invoke(this.getHline().getObject(), castValue);
        } catch (Exception e1) {

        }
    }

    public HLine getHline() {
        return hline;
    }

    public void setHline(HLine hline) {
        this.hline = hline;
    }

    public void insertUpdate(DocumentEvent e) {
        this.extractValue();
    }

    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

    }

    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

    }

}
