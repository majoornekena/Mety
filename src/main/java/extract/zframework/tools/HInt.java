package extract.zframework.tools;

public class HInt extends HType {

    public HInt() {
        super("int*","serial");
    }

    @Override
    public Object cast(String value) {
        int ans = Integer.parseInt(value);
        return ans;
    }

}
