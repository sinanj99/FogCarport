/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 *a
 * @author sinanjasar
 */
public class User {
    private int id;
    private boolean seller;
    private final  String email;
    private final String password;
    private PersonalInfo info;
    private boolean admin;
    
    public User(PersonalInfo info, int id, boolean seller, boolean admin, String email, String password) {
        this.info = info;
        this.id = id;
        this.seller = seller;
        this.admin = admin;
        this.email = email;
        this.password = password;
    }

    public User(PersonalInfo info, String email, String password) {
        this.info = info;
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    

    public String getEmail() {
        return email;
    }

    public boolean isSeller() {
        return seller;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public PersonalInfo getInfo() {
        return info;
    }

    public boolean isAdmin() {
        return admin;
    }
    
    @Override
    public String toString() {
        return "User{" + "email=" + email + ", password=" + password + '}';
    }
    
}
