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
public class Client extends User {
    
    private PersonalInfo info;

    public Client(PersonalInfo info, int id, String email, String password) {
        super(id, email, password);
        this.info = info;
    }

    public Client(PersonalInfo info, String email, String password) {
        super(email, password);
        this.info = info;
    }

    public PersonalInfo getInfo() {
        return info;
    }
   
}
