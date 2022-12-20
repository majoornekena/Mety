package extract.zframework.tools;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HDate extends HType {

    public HDate() {
        super("date","Date");
    }

    @Override
    public Object cast(String value) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = new Date(formatter.parse(value).getTime());
        } catch (ParseException e1) {
            try {
                date = Date.valueOf(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

}
