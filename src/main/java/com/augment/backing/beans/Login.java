package com.augment.backing.beans;

import com.augment.dao.LoginDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
public class Login implements Serializable {
    @ManagedProperty("#{loginDao}")
    private LoginDao loginDao;

    private static final long serialVersionUID = 7082351863960448880L;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public String validateLogin() {
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (userExists(username, password)) {
            session.setAttribute("username", username);
            return "/service-pages/user-home-page.xhtml?faces-redirect=true";
        } else {
            session.setAttribute("invalidLoginMsg", "Invalid Credentials. Please try again.");
            return "/account-pages/login";
        }
    }

    public String logOut() {
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/index.xhtml?faces-redirect=true";
    }

    private boolean userExists(final String username, final String password) {
       return loginDao.getUser(username, password).size() == 1;
    }
}
