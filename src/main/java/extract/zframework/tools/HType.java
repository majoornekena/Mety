package extract.zframework.tools;

public abstract class HType {
    String[] pathern;

    public boolean isMe(String value) {
        for (int i = 0; i < pathern.length; i++) {
            if (OwnTool.isMatch(value, this.getPathern()[i])) {
                return true;
            }
        }
        return false;
    }

    public abstract Object cast(String value);

    public HType(String... pathern) {
        this.pathern = pathern;
    }

    public String[] getPathern() {
        return pathern;
    }

    public void setPathern(String[] pathern) {
        this.pathern = pathern;
    }

}
