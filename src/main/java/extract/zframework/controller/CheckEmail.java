package extract.zframework.controller;

import extract.zframework.annotation.ECheck;
import extract.zframework.controlExcept.EmailFormatExcept;

public class CheckEmail extends CheckFormat {
    public CheckEmail() {
        super(ECheck.Email, "Wrong email format");
        ParamPattern pattern = new ParamPattern("[a-z][a-z0-9_]*@[a-z]{2,10}\\.[a-z]{2,5}",
                new EmailFormatExcept("wrong email format"));
        this.setPathern(pattern);
    }

}
