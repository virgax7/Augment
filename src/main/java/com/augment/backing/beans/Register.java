package com.augment.backing.beans;

import com.augment.dao.RegisterDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

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
        if (password.equals(passwordConfirm) && registerDao.usernameNotTaken(username)) {
            registerDao.signUp(username, password);
            session.setAttribute("username", username);
            return "/successful-sign-up.xhtml?faces-redirect=true";
        } else {
            session.setAttribute("invalidSignUpMsg", password.equals(passwordConfirm) ? "Username " + username + " is taken." : "Password does not match.");
            return "/register.xhtml";
        }
    }
}
