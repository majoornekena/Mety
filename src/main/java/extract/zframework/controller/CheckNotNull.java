package extract.zframework.controller;

import java.lang.reflect.Field;

import extract.zframework.annotation.ECheck;
import extract.zframework.controlExcept.CheckExcept;
import extract.zframework.controlExcept.DataMissing;
import extract.zframework.dao.ObjectBdd;

public class CheckNotNull extends Checker {

    public CheckNotNull() {
        super(ECheck.NotNull);
    }

    @Override
    public void check(Field field, ObjectBdd<?> object, String message)
            throws CheckExcept, IllegalArgumentException, IllegalAccessException, DataMissing {
        this.checkNull(field, object, message);
    }

}
