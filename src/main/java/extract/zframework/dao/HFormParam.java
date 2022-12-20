package extract.zframework.dao;

import extract.zframework.annotation.HForceForm;
import extract.zframework.annotation.HForm;
import extract.zframework.annotation.HListe;

public class HFormParam extends Exception {
    int order;
    String label;
    String placeholder;
    String question;
    String qualifier;
    Class<?> cls;
    boolean isForm;

    public HFormParam(String label, String placeholder, int order, Class<?>... cls) {
        this.setLabel(label);
        this.setPlaceholder(placeholder);
        this.setCls(cls);
        this.setOrder(order);
    }

    public HFormParam(HListe hListe) {
        this(hListe.label(), "", hListe.order());
        this.setQuestion(hListe.question());
    }

    public HFormParam(HForm hform) {
        this(hform.label(), hform.placeholder(), hform.order(), hform.cls());
        this.setQuestion(hform.question());
        this.setQualifier(hform.qualifier());
    }

    public HFormParam(HForceForm hform) {
        this(hform.label(), hform.placeholder(), hform.order(), hform.cls());
        this.setQuestion(hform.question());
        this.setForm(true);
        this.setQualifier(hform.qualifier());
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?>... cls) {
        if (cls.length > 0) {
            this.cls = cls[0];
        } else {
        }
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isForm() {
        return isForm;
    }

    public void setForm(boolean isForm) {
        this.isForm = isForm;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

}
