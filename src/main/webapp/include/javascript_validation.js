/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function check(element) {
    var regex;
    var found = false;
    switch (element.id) {
        case "fname":
        case "lname":
            regex = /^[a-zA-ZÆØÅæøåÜÖüö][a-zA-Z\. ÆØÅåøæÜÖüö-]{1,20}$/;
            break;
        case "address":
            regex = /^[A-Za-zÆØÅæøåÜÖüö 0-9'\.\-,#]{1,100}$/;
            break;
        case "zip":
            regex = /^[0-9]{4}$/;
            break;
        case "city":
            regex = /^[A-Za-zÆØÅæøåÜÖöü \.\-,]{1,20}$/;
            break;
        case "email":
            var email = element.value;
            if (email.indexOf("@") !== -1 && email.substring(0, email.indexOf("@")) !== ''
                    && email.substring(email.indexOf("@")).indexOf(".") !== -1 && !email.endsWith(".") 
            && email.charAt(email.indexOf("@")+1) !== '.' ){
                found = true;
            }
        case "pword":
            regex = /^(?=.*[A-Za-zÆØÅæøåÜÖöü])(?=.*\d)[A-Za-zÆØÅæøåÜÖüü\d]{8,}$/;
            break;
        case "pword2":
            var pword = document.getElementById("pword");
            if (element.value.match(pword.value) && pword.value.length !== 0) {
                found = true;
            }
        case "gender":
            regex = /^[mw]{1}$/;
            break;

    }
    
    if (element.id !== "email" && element.id !== "pword2") {
        found = regex.test(element.value);
    }

    if (element.value.length == 0 || element.value == "n/a") {
        element.style.border = "1px solid #7c7c7c";
        return false;
    } else if (found) {
        element.style.border = "2px solid green";
        return true;
    } else {
        element.style.border = "2px solid red";
        return false;
    }
}

function checkAll() {
    console.log("test");
    var condition = true;
    var inputs = document.getElementsByClassName("check");
    for (var i = 0; i < inputs.length; i++) {
        console.log("test1");
        if (check(inputs[i]) == false) {
            console.log("check false!");
            condition = false;
        }
    }
    if (condition == false) {
        document.getElementById("submit").disabled = true;
    } else {
        document.getElementById("submit").disabled = false;
    }
}