package com.prod.app.LocalDatabase;

public class LoginEntity {
    private Long id;
    private String eid;
    private String raw_data;


    public LoginEntity() {
    }

    public LoginEntity(Long eid) {
        this.id = id;
    }



    public LoginEntity(Long id, String eid, String raw_data) {
        this.id = id;
        this.eid = eid;
        this.raw_data = raw_data;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getData() {
        return raw_data;
    }

    public void setData(String raw_data) {
        this.raw_data = raw_data;
    }
}