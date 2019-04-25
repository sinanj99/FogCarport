/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author sinanjasar
 */
public class PersonalInfo {
    int user_id;
    String firstname;
    String lastname;
    String address;
    int zipcode;
    String city;
    boolean gender;

    public PersonalInfo(String firstname, String lastname, String address, int zipcode, String city, boolean gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.gender = gender;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public boolean isGender() {
        return gender;
    }
    
    
    
}
