package com.augment.backing.beans;

import com.augment.dao.RegisterDao;
import com.augment.dao.TodayScheduleDao;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.regex.Pattern;

@Getter
@Setter
@ManagedBean
public class Register implements Serializable {
    @ManagedProperty("#{registerDao}")
    private RegisterDao registerDao;
    @ManagedProperty("#{todayScheduleDao}")
    private TodayScheduleDao todayScheduleDao;

    private static final long serialVersionUID = 8413015591882487179L;
    private String username;
    private String password;
    private String email;
    private String passwordConfirm;

    public String signUp() {
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (usernameIsValid(username) && passwordIsValid(password, passwordConfirm) && emailIsValid(email)) {
            registerDao.createUser(username, password, email);
            todayScheduleDao.createDay(username);
            session.setAttribute("username", username);
            return "/account-pages/successful-sign-up.xhtml?faces-redirect=true";
        } else {
            session.setAttribute("invalidSignUpMsg", passwordIsValid(password, passwordConfirm) ? "Username " + username
                    + " or email " + email + " is taken or invalid."
                    : password.equals(passwordConfirm) ? "Password is invalid" : "Password does not match.");
            return "/account-pages/register.xhtml";
        }
    }

    private boolean emailIsValid(final String email) {
        return registerDao.getUserFromEmail(email).size() == 0;
    }

    private boolean usernameIsValid(final String username) {
        return username != null && username.matches("\\A\\w{4,10}\\z") && registerDao.getUser(username).size() == 0;
    }

    private boolean passwordIsValid(final String password, final String passwordConfirm) {
        return password != null && password.equals(passwordConfirm) && Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher(password).matches();
    }
}
