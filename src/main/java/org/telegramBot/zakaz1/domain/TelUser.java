package org.telegramBot.zakaz1.domain;

import org.telegramBot.zakaz1.TypeDoc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
@Entity
public class TelUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chatid;
    private String  type_doc;
    private boolean admin_support;
    private  boolean admin;
    private String name;


    public ArrayList<String> getDocument_path() {
        return document_path;
    }

    private ArrayList<String> document_path=new ArrayList<>();

    public void AddDocument(String path)
    {
        document_path.add(path);
    }

    public TelUser(String chat_id, boolean admin) {
        this.chatid = chat_id;
        this. admin=admin;
    }

    public TelUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean  getAdmin() {
        return admin;
    }
    public void   setAdmin(boolean admin) {
        this.admin = admin;
    }
    public String  getType_doc() {
        return type_doc;
    }
    public void  setType_doc(TypeDoc type_doc) {
        this.type_doc = type_doc.getTitle();
    }

    public boolean isAdmin_support() {
        return admin_support;
    }

    public void setAdmin_support(boolean admin_support) {
        this.admin_support = admin_support;
    }

    public String getChat_id() {
        return chatid;
    }
}
