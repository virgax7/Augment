package com.augment.backing.beans;

import com.augment.dao.RegisterDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.regex.Pattern;

@ManagedBean
public class Register implements Serializable {
    @ManagedProperty("#{registerDao}")
    private RegisterDao registerDao;

    private static final long serialVersionUID = 8413015591882487179L;
    private String username;
    private String password;
    private String passwordConfirm;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setRegisterDao(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    public String signUp() {
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (usernameIsValid(username) && passwordIsValid(password, passwordConfirm)) {
            registerDao.createUser(username, password);
            session.setAttribute("username", username);
            return "/account-pages/successful-sign-up.xhtml?faces-redirect=true";
        } else {
            session.setAttribute("invalidSignUpMsg", passwordIsValid(password, passwordConfirm) ? "Username " + username + " is taken or invalid."
                    : password.equals(passwordConfirm) ? "Password is invalid" : "Password does not match.");
            return "/account-pages/register.xhtml";
        }
    }

    private boolean usernameIsValid(final String username) {
        return username.matches("\\A\\w{4,10}\\z") && registerDao.getUser(username).size() == 0;
    }

    private boolean passwordIsValid(final String password, final String passwordConfirm) {
        return password.equals(passwordConfirm) && Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher(password).matches();
    }
}
