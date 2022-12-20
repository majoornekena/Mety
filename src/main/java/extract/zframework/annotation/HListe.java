package extract.zframework.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HListe {

    String question() default "";

    String label();

    int order();
}
