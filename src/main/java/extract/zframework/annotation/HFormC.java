package extract.zframework.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HFormC {
    String ilabel();

    String lilabel();

    String desc();
}
