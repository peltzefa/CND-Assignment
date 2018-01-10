package com.example.database.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Number implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private float value;

    public Long getId() {
        return id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String toString(){
        return "Index " + this.id + " with value: " + this.value + ".<br>";
    }
}
