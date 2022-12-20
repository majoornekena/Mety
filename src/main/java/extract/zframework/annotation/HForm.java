package extract.zframework.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HForm {
    int order();

    String label();

    String placeholder() default "";

    String question() default "";

    Class<?> cls() default Object.class;

    String qualifier() default "";
}
