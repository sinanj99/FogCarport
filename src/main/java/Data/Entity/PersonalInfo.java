/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * Mirrors users_personalinfo table in database.
 * Is used in datamappers where info is inserted / fetched.
 * @author sinanjasar
 */
public class PersonalInfo {
    int user_id;
    String firstname;
    String lastname;
    String address;
    int zipcode;
    String city;
    String gender;

    public PersonalInfo(String firstname, String lastname, String address, int zipcode, String city, String gender) {
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

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" + "firstname=" + firstname + ", lastname=" + lastname + ", address=" + address + ", zipcode=" + zipcode + ", city=" + city + ", gender=" + gender + '}';
    }
    
    
    
    
}
