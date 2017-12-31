package com.augment.backing.beans;

import com.augment.dao.TodayScheduleDao;
import com.augment.vo.schedule.Hour;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ManagedBean
public class TodaySchedule implements Serializable {
    @ManagedProperty("#{todayScheduleDao}")
    private TodayScheduleDao todayScheduleDao;

    private static final long serialVersionUID = 8147350519431847430L;
    private String fourFiveAM;
    private String fiveSixAM;
    private String sixSevenAM;
    private String sevenEightAM;
    private String eightNineAM;
    private String nineTenAM;
    private String tenElevenAM;
    private String elevenTwelvePM;
    private String twelveOnePM;
    private String oneTwoPM;
    private String twoThreePM;
    private String threeFourPM;
    private String fourFivePM;
    private String fiveSixPM;
    private String sixSevenPM;
    private String sevenEightPM;
    private String eightNinePM;
    private String nineTenPM;
    private String tenElevenPM;
    private String elevenTwelveAM;
    private String twelveOneAM;
    private String oneTwoAM;
    private String twoThreeAM;
    private String threeFourAM;

    public String saveSchedule() {
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        todayScheduleDao.updateDay(this, (String) session.getAttribute("username"));
        return "/service-pages/schedule/today.xhtml";
    }

    public List<Hour> getHours() {
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        final String username = (String) session.getAttribute("username");
        final Set<Map.Entry<String, Object>> day = todayScheduleDao.getDay(username).get(0).entrySet();
        final List<Hour> hours = new ArrayList<>();

        int i = 0;
        final Iterator<Map.Entry<String, Object>> dayIterator = day.iterator();
        while (i < Hour.TIME.values().length && dayIterator.hasNext()) {
            hours.add(new Hour(Hour.TIME.values()[i++].toString(), (String) dayIterator.next().getValue()));
        }
        return hours;
    }
}
