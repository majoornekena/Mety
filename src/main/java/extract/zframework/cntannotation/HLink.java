package extract.zframework.cntannotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HLink {
    EMethod method() default EMethod.GET;

    String url();
}
