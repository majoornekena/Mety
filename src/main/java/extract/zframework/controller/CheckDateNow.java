package extract.zframework.controller;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.Calendar;

import extract.zframework.annotation.ECheck;
import extract.zframework.controlExcept.CheckExcept;
import extract.zframework.controlExcept.CheckTypeExcept;
import extract.zframework.controlExcept.DataMissing;
import extract.zframework.controlExcept.DateOutOfToday;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.message.Message;

public class CheckDateNow extends Checker {

    public CheckDateNow() {
        super(ECheck.DateNow);
    }

    public void checkType() throws CheckTypeExcept {
        if (!(this.getValue() instanceof Date || this.getValue() instanceof java.util.Date)) {
            throw new CheckTypeExcept(Message.notInstance);
        }
    }

    public void checkValue() throws DateOutOfToday {
        Date d = (Date) this.getValue();
        Calendar calendar = Calendar.getInstance();
        if (calendar.getTime().before(d)) {
            throw new DateOutOfToday();
        }
    }

    public void checkDate() throws CheckTypeExcept, DateOutOfToday {
        this.checkType();
        this.checkValue();
    }

    @Override
    public void check(Field field, ObjectBdd<?> object, String message)
            throws CheckExcept, IllegalArgumentException, IllegalAccessException, DataMissing {
        this.checkNull(field, object, message);
        this.checkDate();
    }

}
