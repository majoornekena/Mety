package extract.zframework.hform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import extract.zframework.dao.HFormParam;
import extract.zframework.dao.ObjectBdd;

public class HLine {
    int order;
    String label;
    String placeholder;
    String qualifier;
    Method meth;
    Method mQualifier;
    Class<?> cls;
    ObjectBdd<?> object;
    String question;
    boolean isForm;

    public HLine(ObjectBdd<?> object, HFormParam param, Method meth) {
        this(object, param.getLabel(), param.getPlaceholder(), meth, param.getCls(), param.getOrder());
        this.setQuestion(param.getQuestion());
    }

    public HLine(ObjectBdd<?> object, String label, String placeholder, Method meth, Class<?> cls, int order) {
        this.setObject(object);
        this.setLabel(label);
        this.setPlaceholder(placeholder);
        this.setMeth(meth);
        this.setCls(cls);
        this.setOrder(order);
    }

    public Object invokeGet() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return this.getMeth().invoke(this.getObject());
    }

    public void invokeSet(Object value)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.getMeth().invoke(this.getObject(), value);
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

    public Method getMeth() {
        return meth;
    }

    public void setMeth(Method meth) {
        this.meth = meth;
    }

    public ObjectBdd<?> getObject() {
        return object;
    }

    public void setObject(ObjectBdd<?> object) {
        this.object = object;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

    public void checkQualifier() throws NoQualifier {
        if (this.getQualifier() == null) {
            throw new NoQualifier("Qualifier is null.");
        }
    }

    public void searchQualifier() {
        try {
            this.checkQualifier();
            Method meth = this.getObject().getClass().getDeclaredMethod(this.getQualifier());
            this.setmQualifier(meth);
        } catch (NoSuchMethodException e) {
        } catch (NoQualifier e) {
        } catch (SecurityException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasQualifier() {
        return this.getmQualifier() != null;
    }

    public void setQualifier(String qualifier) throws NoSuchMethodException, SecurityException {
        this.qualifier = qualifier;
        this.searchQualifier();
    }

    public Object invokeQualifier() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return this.getmQualifier().invoke(this.getObject());
    }

    public Method getmQualifier() {
        return mQualifier;
    }

    public void setmQualifier(Method mQualifier) {
        this.mQualifier = mQualifier;
    }

}
