package extract.zframework.controller;

import java.sql.Date;

import extract.zframework.controlExcept.DataExist;
import extract.zframework.controlExcept.DataMissing;
import extract.zframework.controlExcept.DateExcept;
import extract.zframework.controlExcept.DebitCreditExcept;
import extract.zframework.message.Message;

public class DataCheck {

    public static void checkDebitCredit(double[] tab) throws DebitCreditExcept {
        double val = Math.abs(tab[0] - tab[1]);
        if (val != 0.0) {
            String str = String.format(Message.godasy, val);
            throw new DebitCreditExcept(str);
        }
    }

    public static void checkExist(Object[] liste, String message) throws DataExist {
        if (liste.length > 0) {
            String str = String.format(Message.dataexist, message);
            throw new DataExist(str);
        }
    }

    public static void checkNull(Object o, String message) throws DataMissing {
        if (o == null) {
            String str = String.format(Message.notSelected, message);
            throw new DataMissing(str);
        }
    }

    public static void checkDate(Date first, Date second) throws DateExcept {
        if (first.after(second)) {
            String message = String.format(Message.datedepass, first, second);
            throw new DateExcept(message);
        }
    }

    public static void checkDate(Date date) throws DateExcept {
        Date now = (Date) new java.util.Date();
        if (date.after(now)) {
            String message = String.format(Message.datedepassnow, date, now);
            throw new DateExcept(message);
        }
    }

    public static void checkDate(Date date, Date first, Date second) throws DateExcept {
        boolean check = true && (date.after(first) || date.compareTo(first) == 0);
        System.out.println("DataCheck => checkFirst : " + check);
        check &= (date.before(second) || date.compareTo(second) == 0);
        System.out.println("DataCheck => checkSecond : " + check);
        if (!check) {
            String message = String.format(Message.dateout, date, first, second);
            throw new DateExcept(message);
        }
    }
}
