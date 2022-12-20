package extract.zframework.annotation.relation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ForeignKey {
    String table() default "";
    Relation relation() default Relation.OneToOne;
}
