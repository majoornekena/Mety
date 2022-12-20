package extract.zframework.controller;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import extract.zframework.annotation.ECheck;
import extract.zframework.controlExcept.Breaker;
import extract.zframework.controlExcept.CheckExcept;
import extract.zframework.controlExcept.CheckFormatExcept;
import extract.zframework.controlExcept.DataMissing;
import extract.zframework.controlExcept.StrAvoidExcept;
import extract.zframework.dao.ObjectBdd;

public class CheckFormat extends Checker {
    ParamPattern[] pathern;
    String formatMessage;

    public CheckFormat(ECheck eCheck, String formatMessage) {
        super(eCheck);
        this.setFormatMessage(formatMessage);
    }

    public void checkNull() throws Breaker {
        try {
            DataCheck.checkNull(this.getValue(), "");
        } catch (DataMissing e) {
            throw new Breaker();
        }
    }

    public void checkAvoid() throws StrAvoidExcept {
        CheckStrAvoid strAvoid = new CheckStrAvoid(this.getValue());
        strAvoid.setMessage(this.getMessage());
        strAvoid.checkValue();
    }

    public void checkPathern(ParamPattern pathern, String str) throws CheckFormatExcept {
        if (!Pattern.matches(pathern.getPathern(), str)) {
            throw pathern.getExcept();
        }
    }

    public String getStrValue() {
        return String.valueOf(this.getValue());
    }

    public void checkPathern() throws CheckFormatExcept {
        String str = this.getStrValue();
        for (int i = 0; i < this.getPathern().length; i++) {
            this.checkPathern(this.getPathern()[i], str);
        }
    }

    /**
     * @throws StrAvoidExcept
     * @throws CheckFormatExcept
     */
    public void checkFormat() throws StrAvoidExcept, CheckFormatExcept {
        this.checkAvoid();
        this.checkPathern();
    }

    @Override
    public void check(Field field, ObjectBdd<?> object, String message)
            throws CheckExcept, IllegalArgumentException, IllegalAccessException, DataMissing {
        DataCheck.checkNull(object, "object");
        this.loadValue(field, object, message);
        try {
            this.checkNull();
            this.checkFormat();
        } catch (Breaker e) {
        }
    }

    public ParamPattern[] getPathern() {
        return pathern;
    }

    public void setPathern(ParamPattern... pathern) {
        this.pathern = pathern;
    }

    public String getFormatMessage() {
        return formatMessage;
    }

    public void setFormatMessage(String formatMessage) {
        this.formatMessage = formatMessage;
    }

}
