package com.jinnjartech.posnip.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table
public class Code {

    @Transient
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    @Transient
    final DateTimeFormatter formatterReal = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    @Id
    @JsonIgnore
    private String uuid;
    @JsonIgnore
    private String realDate;
    @JsonIgnore
    private LocalDateTime unformattedDate;
    @JsonIgnore
    @Column(name = "views_restriction")
    private boolean isRestrictedByViews;
    @JsonIgnore
    @Column(name = "time_restriction")
    private boolean isRestrictedByTime;
    @Column(columnDefinition = "varchar(1000000)")
    private String code;
    private String date;
    private long time;
    private int views;

    public Code() {
        this.uuid = UUID.randomUUID().toString();
        setRestrictedByTime(false);
        setRestrictedByViews(false);
        setDate(LocalDateTime.now());
        setRealDate(LocalDateTime.now());
        setUnformattedDate(LocalDateTime.now());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRealDate() {
        return realDate;
    }

    public LocalDateTime getUnformattedDate() {
        return unformattedDate;
    }

    public void setUnformattedDate(LocalDateTime unformattedDate) {
        this.unformattedDate = unformattedDate;
    }

    @JsonIgnore
    public boolean isRestrictedByViews() {
        return isRestrictedByViews;
    }

    @JsonIgnore
    public void setRestrictedByViews(boolean restrictedByViews) {
        isRestrictedByViews = restrictedByViews;
    }

    @JsonIgnore
    public boolean isRestrictedByTime() {
        return isRestrictedByTime;
    }

    @JsonIgnore
    public void setRestrictedByTime(boolean restrictedByTime) {
        isRestrictedByTime = restrictedByTime;
    }

    public void setRealDate(LocalDateTime realDate) {
        this.realDate = realDate.format(formatterReal);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date.format(formatter);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
