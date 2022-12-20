package extract.auth;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepos extends JpaRepository<Login, Integer> {
    @Query(value = "select * from login l where email = :user and pwd = :pwd",nativeQuery = true)
    public List<Login> getBy(@Param("user") String user,@Param("pwd") String pwd);
}
