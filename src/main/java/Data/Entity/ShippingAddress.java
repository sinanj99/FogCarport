/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * Mirrors shipping_address table in database. 
 * Is associated with a user, and therefore,
 * is used in data mappers when handling users, and in presentation layer, when info
 * about a user is needed.
 * @author sinanjasar
 */
public class ShippingAddress {
    
    private String firstname;
    private String lastname;
    private String address;
    private int zipcode;
    private String city;

    public ShippingAddress(String firstname, String lastname, String address, int zipcode, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
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
    
    

}
