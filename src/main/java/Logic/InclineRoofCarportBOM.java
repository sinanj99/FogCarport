/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.IMaterialMapper;
import Data.LineItem;
import Data.Material;

/**
 *
 * @author Kasper Jeppesen
 */
public class InclineRoofCarportBOM {

    /**
     *
     * @return (vindskeder)
     */
    public int amountOfSoffits() {
        return 2;
    }
    
    /**
     *
     * @param carportLength
     * @param isShed
     * @param shedLength
     * @return (spær)
     */
    public int amountOfRafters(int carportLength, boolean isShed, int shedLength) {

        float rafterWidth = 4.5f;
        int rafterQty = 3; // minimum qty of rafters is 3. 
        int lastAdded = 1; // minimum qty of added rafter is 1. 
        int spaceAmount = 2;

        float spaceBetweenRafters = (carportLength - rafterWidth*rafterQty) / spaceAmount;

        while (spaceBetweenRafters > 85) {
            lastAdded *= 2; //amount of rafters added is doubled each time. 
            rafterQty += lastAdded;
            spaceAmount *= 2; //amount of spaces added is doubled each time as well. 
            spaceBetweenRafters = (carportLength - rafterWidth*rafterQty) / spaceAmount;
        }
        if (isShed == true) rafterQty += amountOfRaftersShed(shedLength, rafterWidth, rafterQty, lastAdded, spaceAmount);
            return rafterQty; // a package consists of 8 rafters.
    }

    /**
     * Used for above method. 
     * @param shedLength
     * @param rafterWidth
     * @param rafterQty
     * @param lastAdded
     * @param spaceAmount
     * @return (spær for redskabsskur)
     */
    public int amountOfRaftersShed(int shedLength, float rafterWidth, int rafterQty,
            int lastAdded, int spaceAmount) {

        float spaceBetweenRafter = (shedLength - rafterWidth*rafterQty) / spaceAmount;

        while (spaceBetweenRafter > 110) {
            lastAdded *= 2; //amount of rafters added is doubled each time. 
            rafterQty += lastAdded;
            spaceAmount *= 2; //amount of spaces added is doubled each time as well. 
            spaceBetweenRafter = (shedLength - rafterWidth*rafterQty) / spaceAmount;
        }

        return rafterQty;
    }
    
    /**
     * 
     * @param carportWidth
     * @param inclination
     * @return (taglægter)
     */
    public static double amountOfLaths(int carportWidth, int inclination) {
        /*firstly, the roof is divided into 2 right-angled triangles,
        and the hypotenuse is calculated*/
        int triangleWidth = carportWidth/2; // width of each triangle.
        double hypotenuse = triangleWidth/Math.cos(inclination);
        
        /*the hypotenuse is now used to calculate the amount of laths*/
        
        float lathWidth = 3.8f;
        int lathQty = 3; // minimum qty of rafters is 3. 
        int lastAdded = 1; // minimum qty of added rafter is 1. 
        int spaceAmount = 2;

        float spaceBetweenLaths = ((float)hypotenuse - lathWidth*lathQty) / spaceAmount;

        while (spaceBetweenLaths > 45) {
            lastAdded *= 2; //amount of rafters added is doubled each time. 
            lathQty += lastAdded;
            spaceAmount *= 2; //amount of spaces added is doubled each time as well. 
            spaceBetweenLaths = ((float)hypotenuse - lathWidth*lathQty) / spaceAmount;
        }
        return lathQty;
    }
            
            
    public LineItem rafters(int carportLength, boolean isShed, int shedLength) throws NoSuchMaterialException {
        Material m = IMaterialMapper.instance().getMaterial_("fædigskåret (byg-selv spær)");
        return new LineItem(m, amountOfRafters(carportLength, isShed, shedLength), "byg-selv spær (skal samles) 8 stk.", m.getPrice() * amountOfRafters(carportLength, isShed, shedLength));
    }
    
    public static void main(String[] args) {
        amountOfLaths(200,20);
    }
}
