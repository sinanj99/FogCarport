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
public class ToolShedBOM
{
    public LineItem løsholterGalve(int widthOfToolShed) throws NoSuchMaterialException
    {
        LineItem l = null;
        Material m;
        
        if(widthOfToolShed == 210)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 210);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 240)
        {
             m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 240);
             l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 270)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 270);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 300)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 300);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 330)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 330);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 360)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 360);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 390)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 390);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 420)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 420);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 450)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 450);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 480)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 480);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 510)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 510);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 540)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 540);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 570)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 570);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 600)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 600);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 630)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 630);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 660)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 660);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 690)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 690);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        if(widthOfToolShed == 720)
        {
            m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", 720);
            l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12);
        }
        return l;
    }
    
    public LineItem lægteForDoor() throws NoSuchMaterialException
    {
         Material m = IMaterialMapper.instance().getMaterial_("38x73mm lægte. ubh.");
         return new LineItem(m, 1, "Til z på bagside af dør", m.getPrice());
    }
    public LineItem stalddørsgreb() throws NoSuchMaterialException
    {
         Material m = IMaterialMapper.instance().getMaterial_("50x75mm stalddørsgreb");
         return new LineItem(m, 1, "Til løs på dør i skur", m.getPrice());
    }
    public LineItem tHængsel() throws NoSuchMaterialException
    {
         Material m = IMaterialMapper.instance().getMaterial_("390mm t-hængsel");
         return new LineItem(m, 2, "Til dør i skur", m.getPrice()*2);
    }
}
