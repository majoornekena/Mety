package extract.zframework.controller;

import java.lang.reflect.Field;

import extract.zframework.annotation.ECheck;
import extract.zframework.controlExcept.CheckExcept;
import extract.zframework.controlExcept.CheckTypeExcept;
import extract.zframework.controlExcept.DataMissing;
import extract.zframework.controlExcept.StrAvoidExcept;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.message.Message;

public class CheckStrAvoid extends Checker {

    public CheckStrAvoid() {
        super(ECheck.StrAvoid);
    }

    public CheckStrAvoid(Object value) {
        this();
        this.setValue(value);
    }

    public void checkType() throws CheckTypeExcept {
        if (!(this.getValue() instanceof String)) {
            throw new CheckTypeExcept();
        }
    }

    public void checkValue() throws StrAvoidExcept {
        String str = String.valueOf(this.getValue());
        if (str.equals("")) {
            str = String.format(Message.stravoid, "\"\"", this.getMessage());
            throw new StrAvoidExcept(str);
        }
    }

    @Override
    public void check(Field field, ObjectBdd<?> object, String message)
            throws CheckExcept, IllegalArgumentException, IllegalAccessException, DataMissing {
        this.checkNull(field, object, message);
        this.checkType();
        this.checkValue();
    }

}
