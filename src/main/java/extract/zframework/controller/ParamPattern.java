package extract.zframework.controller;

import extract.zframework.controlExcept.CheckFormatExcept;

public class ParamPattern {
    String pathern;
    CheckFormatExcept except;

    public ParamPattern(String pathern, CheckFormatExcept except) {
        this.setPathern(pathern);
        this.setExcept(except);
    }

    public String getPathern() {
        return pathern;
    }

    public void setPathern(String pathern) {
        this.pathern = pathern;
    }

    public CheckFormatExcept getExcept() {
        return except;
    }

    public void setExcept(CheckFormatExcept except) {
        this.except = except;
    }

}
