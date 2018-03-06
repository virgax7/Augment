package com.augment.backing.beans;

import com.augment.dao.LoginDao;
import com.augment.service.NotificationService;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class Login implements Serializable {
    @ManagedProperty("#{loginDao}")
    private LoginDao loginDao;
    @ManagedProperty("#{notificationService}")
    private NotificationService notificationService;

    private static final long serialVersionUID = 7082351863960448880L;
    private String username;
    private String password;
    private String email;

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

    public String forgotPassword() {
        return "/account-pages/reset-password.xhtml?faces-redirect=true";
    }

    public String resetPassword() {
        notificationService.sendNotification(email);
        return "/account-pages/login?faces-redirect=true";
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
