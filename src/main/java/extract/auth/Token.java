package extract.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToOne
    Login login;

    String value;

    @CreationTimestamp
    Timestamp dateins;

    public Token(Login login) throws NoSuchAlgorithmException {
        this.setLogin(login);
    }

    public void loadBeforeSend() {
        this.setId(null);
        this.setDateins(null);
        this.getLogin().loadBeforeSend();
    }

    public void setLogin(Login login) throws NoSuchAlgorithmException {
        this.login = login;
        this.gToken();
    }

    public void gToken() throws NoSuchAlgorithmException {
        this.setValue(this.gToken(this.getLogin()));
    }

    public String gToken(Login login) throws NoSuchAlgorithmException {
        String ans = login.getId() + login.getEmail() + LocalDateTime.now().toString();
        MessageDigest message = MessageDigest.getInstance("SHA-1");
        byte[] mesDig = message.digest(ans.getBytes());
        BigInteger no = new BigInteger(1, mesDig);
        ans = no.toString(32);
        while (ans.length() < 32) {
            ans = "0" + ans;
        }
        return ans;
    }
}
