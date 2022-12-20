package extract.zframework.controller;

import extract.zframework.annotation.ECheck;
import extract.zframework.controlExcept.NoChecker;

public class CheckManager {
    protected static Checker[] checkers;

    static {
        Checker[] chs = { new CheckMoney(), new CheckNotNull(), new CheckStrAvoid(), new CheckEmail(),
                new CheckPassword(), new CheckDateNow(), new CheckContact() };
        CheckManager.checkers = chs;
    };

    public static Checker getChecker(ECheck eCheck) throws NoChecker {
        for (int i = 0; i < checkers.length; i++) {
            if (checkers[i].geteCheck().equals(eCheck)) {
                return checkers[i];
            }
        }
        throw new NoChecker("No Checker match to this echeck");
    }
}
