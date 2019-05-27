/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.PersonalInfo;
import Data.Entity.User;
import Presentation.Exceptions.DuplicateException;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.NoMatchException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 * Command that validates user input and registers user with the specified input
 * @author sinanjasar
 */
public class RegisterCommand implements Command {

    public RegisterCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) throws SystemErrorException, NoMatchException, DuplicateException, InvalidInputException {
        String nameRegex = "^[a-zA-Zå\\øæÜÖüö][a-zA-Z\\. å\\øæÜÖüö-]{1,20}$";
        String addressRegex = "[A-Za-zÆØÅæøåÜÖüö 0-9'\\.\\-,#]{1,100}";
        String zipRegex = "[0-9]{4}";
        String cityRegex = "[A-Za-zÆØÅæøåÜÖöü \\.\\-,]{1,20}";
        String pwordRegex = "^(?=.*[A-Za-zÆØÅæøåÜÖöü])(?=.*\\d)[A-Za-zÆØÅæøåÜÖüü\\d]{8,}$";
        String target = "jsp/register.jsp";
        HashMap<String, String> input = new HashMap();
        
        String fname = request.getParameter("fname");
        input.put("fornavn!", fname);
        String lname = request.getParameter("lname");
        input.put("efternavn!", lname);
        String adress = request.getParameter("adress");
        input.put("adresse!", lname);
        
        int zip;
        try {
            zip = Integer.parseInt(request.getParameter("zip"));
        } catch (NumberFormatException e) {
            throw new InvalidInputException(target, "Postnummer skal være 4 cifre!");
        }
        
        input.put("postnummer!", String.valueOf(zip));
        String city = request.getParameter("city");
        input.put("by!", city);
        String email = request.getParameter("email");
        input.put("email!", email);
        String pword = request.getParameter("pword");
        input.put("adgangskode!", lname);
        String pword2 = request.getParameter("pword2");
        String gender = request.getParameter("gender");
        input.put("køn!", lname);

        //loop throug map, check for null values
        for (Map.Entry<String, String> entry : input.entrySet()) {
            if (entry.getValue() == null) {
                throw new InvalidInputException(target, "Indtast venligst en værdi i feltet '" + entry.getKey() + "'");
            }
        }

        //check if input strings live up to regex requirements
        if (!pword.equals(pword2)) {
            throw new NoMatchException(target, "Adgangskoderne matcher ikke!");
        }
        if (!Pattern.matches(nameRegex, fname)) {
            throw new InvalidInputException(target, "Fornavn er ugyldigt!", "Fornavn skal være mellem 1 og 20 bogstaver og "
                    + "må ikke indeholde andre specielle karakterer end ´,´ ´'´ og ´.´");
        }
        if (!Pattern.matches(nameRegex, lname)) {
            throw new InvalidInputException(target, "Efternavn er ugyldigt!", "Efternavn skal være mellem 1 og 20 bogstaver og "
                    + "må ikke indeholde andre specielle karakterer end ´,´ ´'´ og ´.´");
        }
        if (!Pattern.matches(addressRegex, adress)) {
            throw new InvalidInputException(target, "Adressen er ugyldig!", "Adresse skal være mellem 1 og 100 tegn og må ikke indeholde"
                    + "andre specielle karakterer end ´,´ ´-´ ´.´ og ´#´");
        }
        if (!Pattern.matches(zipRegex, String.valueOf(zip))) {
            throw new InvalidInputException(target, "Postnummer er ugyldigt!", "Postnummer skal bestå af 4 cifre");
        }
        if (!Pattern.matches(cityRegex, city)) {
            throw new InvalidInputException(target, "By er ugyldig!", "By skal være mellem 1 og 20 tegn");
        }
        if (!Pattern.matches(pwordRegex, pword)) {
            throw new InvalidInputException(target, "Adgangskode er ugyldig!", "Adgangskode skal minimum være 8 karakterer"
                    + " og skal bestå af minimum et bogstav og et tal");
        }
        boolean emailValid = false;
        if(email.contains("@") && !email.substring(0,email.indexOf("@")).isEmpty() 
                && email.substring(email.indexOf("@")).contains(".") && !email.endsWith(".")) {
            emailValid = true;
        }
            
            
        if (emailValid == false) {
            throw new InvalidInputException(target, "Email er ugyldig!");
        }
        if (!gender.equals("m") && !gender.equals("w")) {
            throw new InvalidInputException(target, "Køn er ugyldigt!");
        }
        try {
            PresentationFacade.getInstance().insertUser(new User(new PersonalInfo(fname, lname, adress, zip, city, gender), email, pword));
        } catch (DuplicateException e) {
            throw new DuplicateException(target, "Email optaget!");
        }

        return "jsp/frontpage.jsp";
    }

}
