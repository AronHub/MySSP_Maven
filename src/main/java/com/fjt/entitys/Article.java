package com.fjt.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 用于测试的实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "article")
@SequenceGenerator(name="seq", sequenceName="art_id_seq")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    private Integer aid;

    private String author;

    private Date createTime;

    private String title;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +
                '}';
    }
}
