package com.example.kapusta.elements2;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Kapusta on 10.09.2017.
 */

public class Element extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private String info;

    public Element() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", name=" + name +
                ", info='" + info + '\'' +
                '}';
    }



}