package extract.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import extract.auth.Login;
import extract.auth.LoginSer;
import extract.auth.NoLogin;
import extract.auth.Token;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@RestController
@EqualsAndHashCode(callSuper = true)
public class LoginCnt extends Cnt {
    @Autowired
    LoginSer loginSer;

    @PostMapping("/login")
    public String login(@RequestBody Login login) {
        try {
            Token l = this.getLoginSer().login(login);
            Success s = new Success();
            s.setData(l);
            return this.getGson().toJson(s);
        } catch (NoLogin e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("/login/incription")
    public String create(@RequestBody Login login) {
        String ans = "";
        try {
            this.getLoginSer().signUp(login);
            ans = "ok";
        } catch (Exception e) {
            e.printStackTrace();
            ans = e.getMessage();
        }
        Success s = new Success();
        s.setData(ans);
        return this.getGson().toJson(s);
    }
}
