package extract.zframework.annotation.relation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MappedBy {
    Class<?> cls();
}
