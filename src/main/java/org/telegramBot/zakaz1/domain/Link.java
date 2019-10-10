package org.telegramBot.zakaz1.domain;


import javax.persistence.*;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String textLink;
    private String nameBut;

    @ManyToOne
    @JoinColumn(name = "link_id")
    private City city;

    public Link() {
    }

    public Link(String nameBut, String textLink) {
        this.textLink = textLink;
        this.nameBut = nameBut;


    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
