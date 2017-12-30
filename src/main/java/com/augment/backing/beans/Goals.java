package com.augment.backing.beans;

import com.augment.dao.GoalsDao;
import com.augment.vo.schedule.Goal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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

    public String createGoal() {
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (goalsDao.getGoal((String) session.getAttribute("username"), title).size() == 0) {
            goalsDao.createGoal((String) session.getAttribute("username"), title, description, startDate, targetDate);
        }
        return "/service-pages/schedule/future.xhtml?faces-redirect=true";
    }

    public List<Goal> getGoalsNotInProgress() {
        return getGoal(Goal.STATUS.NOT_IN_PROGRESS);
    }

    public List<Goal> getGoalsInProgress() {
        return getGoal(Goal.STATUS.IN_PROGRESS);
    }

    public List<Goal> getGoalsAccomplished() {
        return getGoal(Goal.STATUS.ACCOMPLISHED);
    }

    private List<Goal> getGoal(final Goal.STATUS goalStatus){
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        final List<Map<String, Object>> retrievedGoals = goalsDao.getGoal((String) session.getAttribute("username"), goalStatus);
        return retrievedGoals.stream().map(this::makeGoal).collect(Collectors.toList());
    }

    private Goal makeGoal(final Map<String, Object> goal) {
        return new Goal((String) goal.get("title"), (String) goal.get("description"),
                formatToLocalDate(goal.get("start_date").toString()), formatToLocalDate(goal.get("target_date").toString()));
    }

    private String formatToLocalDate(final String dateString) {
        return dateString.replaceAll("(\\d+)-(\\d+)-(\\d+)", "$2/$3/$1");
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
