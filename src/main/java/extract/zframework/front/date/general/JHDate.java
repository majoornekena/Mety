package extract.zframework.front.date.general;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFormattedTextField;

import extract.zframework.hform.HLine;
import extract.zframework.tools.OwnTool;

public class JHDate extends JFormattedTextField {
    Date defaultVal;
    Date maxDate;
    String lastText = "";
    HLine hLine;
    protected final int length = 3;

    public JHDate(Format format, Date defaultVal, Date maxDate, HLine hLine)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super(new SimpleDateFormat("dd/MM/yyyy"));
        this.sethLine(hLine);
        this.setDefaultVal(defaultVal);
        this.setMaxDate(maxDate);
        this.addKeyListener(new DateListener(this));
        this.getDocument().addDocumentListener(new DateDoc(this));
    }

    @Override
    public void setText(String t) {
        super.setText(t);
    }

    public Date getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(Date defaultVal)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.gethLine().hasQualifier()) {
            this.defaultVal = (Date) this.gethLine().invokeQualifier();
        } else if (defaultVal == null) {
            Date d = new Date(Calendar.getInstance().getTime().getTime());
            this.defaultVal = d;
        } else {
            this.defaultVal = defaultVal;
        }
        this.setValue(this.getDefaultVal());
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public void checkFormat() {

    }

    public String[] checkLength(String val) throws FormatDateExcept {
        String[] ans = val.split("/");
        if (ans.length != this.length) {
            throw new FormatDateExcept("Trop de / ");
        }
        return ans;
    }

    public void checkDay(String val) throws FormatDateExcept {
        try {
            int ival = Integer.parseInt(val);
            if (ival < 0 || ival > 31) {
                throw new FormatDateExcept(val);
            }
        } catch (NumberFormatException e) {

        }
    }

    public void checkYear(String val) throws FormatDateExcept {
        try {
            int ival = Integer.parseInt(val);
            if (ival < 0) {
                throw new FormatDateExcept(val);
            }
        } catch (NumberFormatException e) {

        }
    }

    public void checkMonth(String val) throws FormatDateExcept {
        try {
            int ival = Integer.parseInt(val);
            if (ival < 0 || ival > 12) {
                throw new FormatDateExcept(val);
            }
        } catch (NumberFormatException e) {

        }
    }

    public void checkValue() throws FormatDateExcept {
        String val = this.getText();
        String[] liste = this.checkLength(val);
        this.checkDay(liste[0]);
        this.checkMonth(liste[1]);
        this.checkYear(liste[2]);
    }

    public void extractValue() {
        String value = this.getText();
        String type = this.gethLine().getMeth().getParameterTypes()[0].toString();
        try {
            Object castValue = OwnTool.cast(value, type);
            this.gethLine().getMeth().invoke(this.gethLine().getObject(), castValue);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void checkAfter() throws FormatDateExcept {
        this.checkValue();
    }

    public void check(KeyEvent e) throws FormatDateExcept {
        this.setLastText(this.getText());
    }

    public HLine gethLine() {
        return hLine;
    }

    public void sethLine(HLine hLine) {
        this.hLine = hLine;
    }

    public int getLength() {
        return length;
    }

    public String getLastText() {
        return lastText;
    }

    public void setLastText(String lastText) {
        this.lastText = lastText;
    }

}
