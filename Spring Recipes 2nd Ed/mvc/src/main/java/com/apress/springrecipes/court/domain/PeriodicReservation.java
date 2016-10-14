package com.apress.springrecipes.court.domain;

import java.util.Date;

public class PeriodicReservation {

    private String courtName;
    private Date fromDate;
    private Date toDate;
    private int period;
    private int hour;
    private Player player;

    public String getCourtName() {
        return courtName;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public int getHour() {
        return hour;
    }

    public int getPeriod() {
        return period;
    }

    public Player getPlayer() {
        return player;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
