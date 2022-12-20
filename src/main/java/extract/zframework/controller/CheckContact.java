package extract.zframework.controller;

import extract.zframework.annotation.ECheck;
import extract.zframework.controlExcept.CheckFormatExcept;
import extract.zframework.controlExcept.ContactExcept;

public class CheckContact extends CheckFormat {

    public CheckContact() {
        super(ECheck.Contact, "Wrong contact format");
        CheckFormatExcept check = new ContactExcept("the contact doesn't respect the format");
        String pattern = "[\\+0265]{1,4}[\\+0-9]{9}";
        this.setPathern(new ParamPattern(pattern, check));
    }

    @Override
    public String getStrValue() {
        String str = super.getStrValue();
        String[] liste = str.split(" ");
        str = "";
        for (int i = 0; i < liste.length; i++) {
            str += liste[i];
        }
        return str;
    }

}
