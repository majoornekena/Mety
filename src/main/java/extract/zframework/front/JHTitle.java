package extract.zframework.front;

import javax.swing.JLabel;

import extract.zframework.annotation.EForm;
import extract.zframework.annotation.HFormC;

public class JHTitle extends JLabel {
    Object object;
    String pathern;

    public JHTitle(Object object, EForm eform) {
        this.setObject(object);
        this.setPathern("<html><h1>%s</h1></html>");
        this.loadTitle(eform);
    }

    public void loadTitle(EForm eform) {
        switch (eform) {
            case Form:
                this.loadiTitle();
                break;
            case DESC:
                this.loadDTitle();
                break;
            default:
                break;
        }
    }

    @Override
    public void setText(String value) {
        value = value == null ? "Insertion" : value;
        super.setText(value);
    }

    protected void loadDTitle() {
        HFormC form = this.getObject().getClass().getAnnotation(HFormC.class);
        String value = String.format(this.getPathern(), form.desc());
        this.setText(value);
    }

    protected void loadiTitle() {
        HFormC form = this.getObject().getClass().getAnnotation(HFormC.class);
        String value = String.format(this.getPathern(), form.ilabel());
        this.setText(value);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getPathern() {
        return pathern;
    }

    public void setPathern(String pathern) {
        this.pathern = pathern;
    }

}
