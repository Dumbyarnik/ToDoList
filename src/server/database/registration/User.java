package server.database.registration;
/*
 * Class created on 30.07.2021
 * Class is used to store information about registration
 * */

import java.io.Serializable;

public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public User(){

    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



}
