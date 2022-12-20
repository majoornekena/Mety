package extract.zframework.controller;

import java.lang.reflect.Field;

import extract.zframework.annotation.ECheck;
import extract.zframework.controlExcept.CheckExcept;
import extract.zframework.controlExcept.CheckTypeExcept;
import extract.zframework.controlExcept.DataMissing;
import extract.zframework.controlExcept.MoneyExcept;
import extract.zframework.dao.ObjectBdd;

public class CheckMoney extends Checker {
    double dvalue;

    public CheckMoney() {
        super(ECheck.Money);
    }

    public void checkType() throws CheckTypeExcept {
        try {
            this.setDvalue(Double.parseDouble(this.getValue().toString()));
        } catch (NumberFormatException e) {
            throw new CheckTypeExcept();
        }
    }

    public void checkData() throws MoneyExcept {
        if (this.getDvalue() < 0.) {
            throw new MoneyExcept(this.getMessage());
        }
    }

    @Override
    public void check(Field field, ObjectBdd<?> object, String message)
            throws CheckExcept, IllegalArgumentException, IllegalAccessException, DataMissing {
        this.checkNull(field, object, message);
        this.checkType();
        this.checkData();
    }

    public double getDvalue() {
        return dvalue;
    }

    public void setDvalue(double dvalue) {
        this.dvalue = dvalue;
    }

}
