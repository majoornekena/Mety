package extract.zframework.dao;

import java.lang.reflect.Field;
import java.sql.Connection;

import extract.zframework.annotation.ECheck;
import extract.zframework.annotation.MustCheck;
import extract.zframework.controlExcept.CheckExcept;
import extract.zframework.controlExcept.DataMissing;
import extract.zframework.controlExcept.NoChecker;
import extract.zframework.controller.CheckManager;
import extract.zframework.controller.Checker;
import extract.zframework.tools.OwnTool;

public abstract class CheckAble<T extends ObjectBdd<?>> extends ObjectBdd<T> {

    /**
     * @param field
     * @param echeck
     * @param connection
     * @param mustCheck
     * @throws NoChecker
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws CheckExcept
     * @throws DataMissing
     */
    public void check(Field field, ECheck echeck, Connection connection, MustCheck mustCheck)
            throws NoChecker, IllegalArgumentException, IllegalAccessException, CheckExcept, DataMissing {
        Checker checker = CheckManager.getChecker(echeck);
        checker.check(field, this, mustCheck.message());
    }

    /**
     * @param field      : Field(Reflect)
     * @param connection : if null => a connection will be create and close after
     *                   each database access. If
     *                   You want to use one connection, pass connection not null
     * @throws NoChecker                : if the Echeck doesn't much in any Checker
     *                                  class
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws CheckExcept
     * @throws DataMissing
     */
    public void check(Field field, Connection connection)
            throws NoChecker, IllegalArgumentException, IllegalAccessException, CheckExcept, DataMissing {
        MustCheck checkAble = field.getAnnotation(MustCheck.class);
        ECheck[] checkList = checkAble.checkList();
        for (int i = 0; i < checkList.length; i++) {
            this.check(field, checkList[i], connection, checkAble);
        }
    }

    @Override
    public void verif(Connection connection) throws Exception {
        Field[] fields = OwnTool.getAnnotations(this.getClass(), MustCheck.class);
        for (int i = 0; i < fields.length; i++) {
            this.check(fields[i], connection);
        }
    }
}
