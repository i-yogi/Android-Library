package com.androiddeveloper.yogi.hackernewskiiplib;

import java.util.Date;

/**
 * Created by yogi on 3/8/17.
 */

public class MyJsonObj {


    private int id;
    private String title;
    private String author;
    private int score;
    private Date time;
    private int noComments;
    private String orgUrl;

    public MyJsonObj(int id, String title, String author, int score, Date time, int noComments, String orgUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.score = score;
        this.time = time;
        this.noComments = noComments;
        this.orgUrl = orgUrl;
    }

    @Override
    public String toString() {
        return "MyJsonObj{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", score=" + score +
                ", time=" + time +
                ", noComments=" + noComments +
                ", orgUrl='" + orgUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getNoComments() {
        return noComments;
    }

    public void setNoComments(int noComments) {
        this.noComments = noComments;
    }

    public String getOrgUrl() {
        return orgUrl;
    }

    public void setOrgUrl(String orgUrl) {
        this.orgUrl = orgUrl;
    }
}
