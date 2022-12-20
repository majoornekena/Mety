package extract.zframework.controller;

import java.lang.reflect.Field;

import extract.zframework.annotation.ECheck;
import extract.zframework.controlExcept.CheckExcept;
import extract.zframework.controlExcept.DataMissing;
import extract.zframework.dao.ObjectBdd;

public abstract class Checker {
    ECheck eCheck;
    Object value;
    String message;

    // public abstract void test();

    public void checkNull(Field field, Object object, String message)
            throws DataMissing, IllegalArgumentException, IllegalAccessException {
        DataCheck.checkNull(object, "object");
        this.loadValue(field, object, message);
        DataCheck.checkNull(this.getValue(), message);
    }

    public Checker(ECheck eCheck) {
        this.seteCheck(eCheck);
    }

    /**
     * extract field value and set it to value field
     * 
     * @param field
     * @param object
     * @param message
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public void loadValue(Field field, Object object, String message)
            throws IllegalArgumentException, IllegalAccessException {
        field.setAccessible(true);
        this.setValue(field.get(object));
        this.setMessage(message);
    }

    public ECheck geteCheck() {
        return eCheck;
    }

    public void seteCheck(ECheck eCheck) {
        this.eCheck = eCheck;
    }

    /**
     * @param field
     * @param object
     * @param message
     * @throws CheckExcept
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws DataMissing
     */
    public abstract void check(Field field, ObjectBdd<?> object, String message)
            throws CheckExcept, IllegalArgumentException, IllegalAccessException, DataMissing;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
