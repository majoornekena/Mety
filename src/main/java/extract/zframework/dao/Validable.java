package extract.zframework.dao;

import java.sql.Connection;

public interface Validable {
    public void validate(Connection connection) throws Exception;
}
