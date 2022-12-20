package extract.zframework.annotation.relation;

import java.lang.annotation.*;

import extract.zframework.annotation.BaseState;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.METHOD })
public @interface Attribut {
    String value() default "";

    boolean varchar() default false;

    BaseState[] state() default BaseState.INSERT;

    Class<?> classes() default Object.class;
}
