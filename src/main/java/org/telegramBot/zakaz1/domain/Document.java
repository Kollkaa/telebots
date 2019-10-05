package org.telegramBot.zakaz1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    private String name;

    private String manual;

    private String list_need_document;

    private String foto;
    private Integer number;


    public Document() {
    }

    public Document(String name, String manual, String list_need_document, String foto,Integer number) {
        this.name = name;
        this.manual = manual;
        this.list_need_document = list_need_document;
        this.foto = foto;
        this.number=number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public String getList_need_document() {
        return list_need_document;
    }

    public void setList_need_document(String list_need_document) {
        this.list_need_document = list_need_document;
    }

}
