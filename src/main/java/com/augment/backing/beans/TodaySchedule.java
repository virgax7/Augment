package com.augment.backing.beans;

import com.augment.dao.TodayScheduleDao;
import com.augment.vo.schedule.Hour;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ManagedBean
public class TodaySchedule implements Serializable {
    @ManagedProperty("#{todayScheduleDao}")
    TodayScheduleDao todayScheduleDao;

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
        if (session == null) {
            return "/account-pages/login.xhtml?faces-redirect=true";
        }
        todayScheduleDao.updateDay(this, (String) session.getAttribute("username"));
        return "/service-pages/schedule/today.xhtml";
    }

    public List<Hour> getHours() {
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session == null) {
            return new ArrayList<>();
        }
        final String username = (String) session.getAttribute("username");
        final Set<Map.Entry<String, Object>> day = todayScheduleDao.getDay(username).get(0).entrySet();
        final List<Hour> hours = IntStream.range(0, Hour.TIME.values().length).mapToObj($ -> new Hour()).collect(Collectors.toList());

        int i = 0;
        final Iterator<Map.Entry<String, Object>> dayIterator = day.iterator();
        while (i < Hour.TIME.values().length && dayIterator.hasNext()) {
            hours.get(i).setTime(Hour.TIME.values()[i].toString());
            hours.get(i++).setTask((String) dayIterator.next().getValue());
        }
        return hours;
    }

    public void setTodayScheduleDao(TodayScheduleDao todayScheduleDao) {
        this.todayScheduleDao = todayScheduleDao;
    }

    public String getFourFiveAM() {
        return fourFiveAM;
    }

    public void setFourFiveAM(String fourFiveAM) {
        this.fourFiveAM = fourFiveAM;
    }

    public String getFiveSixAM() {
        return fiveSixAM;
    }

    public void setFiveSixAM(String fiveSixAM) {
        this.fiveSixAM = fiveSixAM;
    }

    public String getSixSevenAM() {
        return sixSevenAM;
    }

    public void setSixSevenAM(String sixSevenAM) {
        this.sixSevenAM = sixSevenAM;
    }

    public String getSevenEightAM() {
        return sevenEightAM;
    }

    public void setSevenEightAM(String sevenEightAM) {
        this.sevenEightAM = sevenEightAM;
    }

    public String getEightNineAM() {
        return eightNineAM;
    }

    public void setEightNineAM(String eightNineAM) {
        this.eightNineAM = eightNineAM;
    }

    public String getNineTenAM() {
        return nineTenAM;
    }

    public void setNineTenAM(String nineTenAM) {
        this.nineTenAM = nineTenAM;
    }

    public String getTenElevenAM() {
        return tenElevenAM;
    }

    public void setTenElevenAM(String tenElevenAM) {
        this.tenElevenAM = tenElevenAM;
    }

    public String getElevenTwelvePM() {
        return elevenTwelvePM;
    }

    public void setElevenTwelvePM(String elevenTwelvePM) {
        this.elevenTwelvePM = elevenTwelvePM;
    }

    public String getTwelveOnePM() {
        return twelveOnePM;
    }

    public void setTwelveOnePM(String twelveOnePM) {
        this.twelveOnePM = twelveOnePM;
    }

    public String getOneTwoPM() {
        return oneTwoPM;
    }

    public void setOneTwoPM(String oneTwoPM) {
        this.oneTwoPM = oneTwoPM;
    }

    public String getTwoThreePM() {
        return twoThreePM;
    }

    public void setTwoThreePM(String twoThreePM) {
        this.twoThreePM = twoThreePM;
    }

    public String getThreeFourPM() {
        return threeFourPM;
    }

    public void setThreeFourPM(String threeFourPM) {
        this.threeFourPM = threeFourPM;
    }

    public String getFourFivePM() {
        return fourFivePM;
    }

    public void setFourFivePM(String fourFivePM) {
        this.fourFivePM = fourFivePM;
    }

    public String getFiveSixPM() {
        return fiveSixPM;
    }

    public void setFiveSixPM(String fiveSixPM) {
        this.fiveSixPM = fiveSixPM;
    }

    public String getSixSevenPM() {
        return sixSevenPM;
    }

    public void setSixSevenPM(String sixSevenPM) {
        this.sixSevenPM = sixSevenPM;
    }

    public String getSevenEightPM() {
        return sevenEightPM;
    }

    public void setSevenEightPM(String sevenEightPM) {
        this.sevenEightPM = sevenEightPM;
    }

    public String getEightNinePM() {
        return eightNinePM;
    }

    public void setEightNinePM(String eightNinePM) {
        this.eightNinePM = eightNinePM;
    }

    public String getNineTenPM() {
        return nineTenPM;
    }

    public void setNineTenPM(String nineTenPM) {
        this.nineTenPM = nineTenPM;
    }

    public String getTenElevenPM() {
        return tenElevenPM;
    }

    public void setTenElevenPM(String tenElevenPM) {
        this.tenElevenPM = tenElevenPM;
    }

    public String getElevenTwelveAM() {
        return elevenTwelveAM;
    }

    public void setElevenTwelveAM(String elevenTwelveAM) {
        this.elevenTwelveAM = elevenTwelveAM;
    }

    public String getTwelveOneAM() {
        return twelveOneAM;
    }

    public void setTwelveOneAM(String twelveOneAM) {
        this.twelveOneAM = twelveOneAM;
    }

    public String getOneTwoAM() {
        return oneTwoAM;
    }

    public void setOneTwoAM(String oneTwoAM) {
        this.oneTwoAM = oneTwoAM;
    }

    public String getTwoThreeAM() {
        return twoThreeAM;
    }

    public void setTwoThreeAM(String twoThreeAM) {
        this.twoThreeAM = twoThreeAM;
    }

    public String getThreeFourAM() {
        return threeFourAM;
    }

    public void setThreeFourAM(String threeFourAM) {
        this.threeFourAM = threeFourAM;
    }
}
