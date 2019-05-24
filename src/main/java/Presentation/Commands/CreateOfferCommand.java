/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.BOM;
import Data.Entity.LineItem;
import Data.Entity.Request;
import Data.Entity.Type;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class CreateOfferCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException, NoSuchRequestException,
    NoSuchShedException {
        
        //calculations
        Request r = PresentationFacade.getInstance().getRequest(Integer.parseInt(request.getParameter("requestID")));
        BOM bom;
        try {
            if (r.getCarport().getInclination() != 0) {
                bom = PresentationFacade.getInstance().inclineRoofBOM(r);
            } else {
                bom = PresentationFacade.getInstance().generateFlatRoofCarportBOM(r);
            }
        } catch(SystemErrorException e) {
            request.setAttribute("error", e.getMessage());
            return "jsp/error.jsp";
        }

        List<LineItem> materialsLength = new ArrayList();
        List<LineItem> roofTiles = new ArrayList();
        List<LineItem> roofMaterials = new ArrayList();
        List<LineItem> materialsNoLength = new ArrayList();

        int fullPrice = 0;
        for (LineItem l : bom.getLineitems()) {
            fullPrice += l.getPrice();
            if (l.getType() == Type.LENGTH) {
                materialsLength.add(l);
            } else if (l.getType() == Type.NOLENGTH) {
                materialsNoLength.add(l);
            } else if (l.getRoof() != null) {
                roofTiles.add(l);
            } else {
                roofMaterials.add(l);
            }
        }
        double sellPrice = fullPrice * 12;
        request.setAttribute("request", r);
        request.setAttribute("buyPrice", fullPrice);
        request.setAttribute("sellPrice", (int) sellPrice);
        request.setAttribute("materialLength", materialsLength);
        request.setAttribute("roofTiles", roofTiles);
        request.setAttribute("roofMaterials", roofMaterials);
        request.setAttribute("materialsNoLength", materialsNoLength);

        // drawing

        String svg1 = "";
        String svg2 = "";

        if (r.getCarport().getInclination() == 0) {
            svg1 = PresentationFacade.getInstance().drawFlat(r.getCarport());
            System.out.println(svg1);
        } else {
            svg1 = PresentationFacade.getInstance().drawTopIncline(r.getCarport());
            svg2 = PresentationFacade.getInstance().drawFrontIncline(r.getCarport());
        }
//        String bandSvg = d.drawPerforatedBand(r.getCarport());
        request.setAttribute("svg1", svg1);
        request.setAttribute("svg2", svg2);
//        request.setAttribute("bandSvg", bandSvg);

        return "jsp/create_offer.jsp";
    }

}
