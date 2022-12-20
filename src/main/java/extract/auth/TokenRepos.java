package extract.auth;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface TokenRepos extends JpaRepository<Token, Integer> {
    @Query(value = "select * from token where login_id=:id", nativeQuery = true)
    public List<Token> getByLogin(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "call removetoken()", nativeQuery = true)
    public void removeToken();
}
