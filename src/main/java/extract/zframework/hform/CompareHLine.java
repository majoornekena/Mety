package extract.zframework.hform;

import java.util.Comparator;

public class CompareHLine implements Comparator<HLine> {

    public int compare(HLine hfirst, HLine hsecond) {
        return Integer.compare(hfirst.getOrder(), hsecond.getOrder());
    }

}
