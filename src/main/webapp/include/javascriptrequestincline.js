function visualizeRoof() {

    var elements = document.getElementsByName("pics");
    for (var i = 0; i < elements.length; i++) {
        elements[i].classList.add("d-none");
    }
    var element = document.getElementById("rchoice");
    var id = element.options[element.selectedIndex].value;
    document.getElementById(id).classList.remove("d-none");
    document.getElementById(id + "text").classList.remove("d-none");
}


function spaceForShed() {
    if (document.getElementById("carportLength").value == "n/a" || document.getElementById("carportWidth").value == "n/a") {
        //explanation:
        //you shouldn't be able to choose a toolshed, if a length or width is not specified. 
        document.getElementById("shedChoice").classList.add("d-none");
        document.getElementById("shedLabel").classList.add("d-none");

    } else if (document.getElementById("carportLength").value < 390) {
        // explanation:
        // minimum length for toolshed is 150cm
        // minimum length for full carport is 240cm (excluding toolshed) for there to be space for a car. 
        // therefore the carport must be at least 390 long (240+150) for there to be space for a tool shed. 
        document.getElementById("shedLabel").classList.add("d-none");
        document.getElementById("shedChoice").classList.add("d-none");
    } else {
        document.getElementById("shedLabel").classList.remove("d-none");
        document.getElementById("shedChoice").classList.remove("d-none");
    }
}
function wantShed() {
    var shedChoice = document.getElementById("shedChoice").value;
    var shedWidth = document.getElementById("shedWidth");
    var shedLength = document.getElementById("shedLength");
    if (shedChoice == 1) {
        shedWidth.classList.remove("d-none");
        shedLength.classList.remove("d-none");
        document.getElementById("shedWidthLabel").classList.remove("d-none");
        document.getElementById("shedLengthLabel").classList.remove("d-none");
    } else if (shedChoice == 2) {
        shedWidth.classList.add("d-none");
        shedLength.classList.add("d-none");
        document.getElementById("shedWidthLabel").classList.add("d-none");
        document.getElementById("shedLengthLabel").classList.add("d-none");
    } else if (shedChoice == "n/a") {
        shedWidth.classList.add("d-none");
        shedLength.classList.add("d-none");
        document.getElementById("shedWidthLabel").classList.add("d-none");
        document.getElementById("shedLengthLabel").classList.add("d-none");
    }
}
function widthSubtract30() {
    var shedLengthOptions = document.querySelectorAll("#shedWidth option");
    shedLengthOptions.forEach(shedOption => {
        shedOption.disabled = false;
    });
    var shedWidth = document.getElementById("shedWidth");
    shedWidth.selectedIndex = 0;
    var chosenWidth = document.getElementById("carportWidth").value;
    chosenWidth = chosenWidth - 0; // doesnt work if you do not do this. 
    for (i = chosenWidth; i <= 720; i += 30) {
        document.getElementById("widthOption" + i).disabled = true;
    }
}
function lengthSubtract30() {
    var shedLengthOptions = document.querySelectorAll("#shedLength option");
    shedLengthOptions.forEach(shedOption => {
        shedOption.disabled = false;
    });
    var shedLength = document.getElementById("shedLength");
    shedLength.selectedIndex = 0;

    var chosenLength = document.getElementById("carportLength").value;
    var maxLength = chosenLength - 210; // 210 and not 240, as the first index is removed. 
    for (i = maxLength; i <= 750; i += 30) {
        document.getElementById("lengthOption" + i).disabled = true;
    }
}