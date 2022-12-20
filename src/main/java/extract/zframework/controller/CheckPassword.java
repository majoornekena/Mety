package extract.zframework.controller;

import extract.zframework.annotation.ECheck;
import extract.zframework.controlExcept.PassWordDigitExcept;
import extract.zframework.controlExcept.PassWordLenExcept;
import extract.zframework.controlExcept.PassWordLowerCaseExcept;
import extract.zframework.controlExcept.PassWordUperCaseExcept;

public class CheckPassword extends CheckFormat {
    String anyCharacter = "[\\w!@&\\#\\(\\)\\[\\]\\{\\}\\?\\|\\çé\\+\\-/:;=àè\\.]";

    public CheckPassword() {
        super(ECheck.Password, "Wrong password Format");
        ParamPattern[] liste = {
                new ParamPattern("[" + this.getAnyCharacter() + "]{8,}", new PassWordLenExcept("short password")),
                new ParamPattern(this.put("[a-z]+"), new PassWordLowerCaseExcept("password must contain lowercase")),
                new ParamPattern(this.put("[A-Z]+"), new PassWordUperCaseExcept("password must contain upercase")),
                new ParamPattern(this.put("[0-9]+"), new PassWordDigitExcept("password must contain number")),
        };
        this.setPathern(liste);
    }

    public String put(String regex) {
        return this.getAnyCharacter() + "*" + regex + this.getAnyCharacter() + "*";
    }

    public String getAnyCharacter() {
        return anyCharacter;
    }

    public void setAnyCharacter(String anyCharacter) {
        this.anyCharacter = anyCharacter;
    }

}
