package extract.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class TokenSer {
    @Autowired
    TokenRepos tokenRepos;

    public void reloadToken() {
        this.getTokenRepos().removeToken();
    }

    public void askAccess(int id) throws NoAuth {
        if (!this.canAccess(id)) {
            throw new NoAuth();
        }
    }

    public boolean canAccess(int id) {
        this.reloadToken();
        int len = this.getTokenRepos().getByLogin(id).size();
        System.out.println("tk => len :" + id);
        return len > 0;
    }
}
