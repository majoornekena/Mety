package extract.zframework.tools;

public class HVarchar extends HType {

    public HVarchar() {
        super("varchar","String");
    }

    @Override
    public Object cast(String value) {
        return value;
    }

}
