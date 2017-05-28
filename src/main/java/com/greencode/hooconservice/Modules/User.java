package com.greencode.hooconservice.Modules;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Document(collection = "User")
public class User {
    @Id
    private String id;

    /* FIRST NAME AND LAST NAME FIELDS*/
    @Size(min = 2, max =50)
    @Pattern(regexp = "[A-Za-z. ]*", message = "First name requires valid character")
    private String firstname;

    @Size(min = 2, max = 50)
    @Pattern(regexp = "[A-Za-z. ]*", message = "Last name requires contain valid character")
    private String lastname;

    /* VALID PHONE NUMBER FIELDS*/
    @Indexed( unique=true, sparse=true )
    @NotNull(message =  "Phone number requires valid value")
    @NotEmpty(message = "Phone number requires non empty value")
    @Pattern(regexp = "/+[0-9.\\-+ ]*", message = "Phone number requires valid alphanumaric characters")
    private String phoneNumber;

    /* PASSWORD FIELDS*/
    @NotNull(message = "Password requires valid value")
    @NotEmpty(message = "Password requires non empty value")
    private String password;

    @Size(min = 7)
    private int age;

    private String birthday;

    public User(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
