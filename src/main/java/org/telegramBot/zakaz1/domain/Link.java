package org.telegramBot.zakaz1.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameBut;
    private String textLink;


    public Link() {
    }

    public Link(String nameBut, String textLink) {
        this.nameBut = nameBut;
        this.textLink = textLink;
    }

    public Long getId() {
        return id;
    }

    public String getNameBut() {
        return nameBut;
    }

    public void setNameBut(String nameBut) {
        this.nameBut = nameBut;
    }

    public String getTextLink() {
        return textLink;
    }

    public void setTextLink(String textLink) {
        this.textLink = textLink;
    }
}
