package fr.codevallee.formation.android_tp12;

import java.io.Serializable;

/**
 * Created by tgoudouneix on 16/10/2017.
 */

public class User implements Serializable {
    private Integer id = null;
    private String firstname;
    private String lastname;
    private Integer age;
    private String work;

    public User(Integer id, String firstname, String lastname, Integer age, String work) {
        this.setId(id);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setAge(age);
        this.setWork(work);
    }

    public User(Integer id, String firstname, String lastname) {
        this(id, firstname, lastname, null, "");
    }

    public User(String firstname, String lastname) {
        this(null, firstname, lastname);
    }

    public Integer getId() {
        return this.id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getWork() {
        return this.work;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String serialize() {
        return "User {"
                + "id: " + id + ", "
                + "firstname: " + firstname + ", "
                + "lastname: " + lastname + ", "
                + "age: " + age + ", "
                + "work: " + work
                + "}";
    }
}
