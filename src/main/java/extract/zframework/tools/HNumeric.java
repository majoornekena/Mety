package extract.zframework.tools;

public class HNumeric extends HType {

    public HNumeric() {
        super("numeric","real","float","double");
    }

    @Override
    public Object cast(String value) {
        double ans = Double.parseDouble(value);
        return ans;
    }

}
