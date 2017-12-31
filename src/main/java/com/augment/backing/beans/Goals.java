package com.augment.backing.beans;

import com.augment.convert.schedule.GoalConverts;
import com.augment.dao.GoalsDao;
import com.augment.vo.schedule.Goal;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ManagedBean
public class Goals implements Serializable {
    @ManagedProperty("#{goalsDao}")
    private GoalsDao goalsDao;

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
        return retrievedGoals.stream().map(GoalConverts::makeGoal).collect(Collectors.toList());
    }
}
