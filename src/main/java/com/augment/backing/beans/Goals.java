package com.augment.backing.beans;

import com.augment.dao.GoalsDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
public class Goals implements Serializable {
    @ManagedProperty("#{goalsDao}")
    private GoalsDao goalsDao;
    @ManagedProperty("#{login}")
    private Login login;

    private static final long serialVersionUID = 7952282536271621552L;
    private String title;
    private String description;
    private Date startDate;
    private Date targetDate;

    public void createGoal() {
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session == null) {
            login.logOut();
        }
        goalsDao.createGoal((String) session.getAttribute("username"), title, description, startDate, targetDate);
    }

    public void setGoalsDao(final GoalsDao goalsDao) {
        this.goalsDao = goalsDao;
    }

    public void setLogin(final Login login) {
        this.login = login;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }
}
