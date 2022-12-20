package extract.zframework.tools;

import java.sql.Timestamp;

public class HTimestamp extends HType {

    public HTimestamp() {
        super("timestamp");
    }

    @Override
    public Object cast(String value) {
        return Timestamp.valueOf(value);
    }

}
