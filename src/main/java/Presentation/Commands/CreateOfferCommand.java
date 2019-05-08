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
import Logic.Calculator.CalculateBOM;
import Logic.Calculator.CalculatePrice;
import Logic.Calculator.DrawSVG;
import Logic.Calculator.InclineRoofCarportBOM;
import Logic.Controller.Manager;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRequestException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.UserNotFoundException;
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
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, ServletException, UserNotFoundException, NoSuchRoofException, SQLException, IOException {
        //calculations
        Request r = Manager.getRequest(Integer.parseInt(request.getParameter("requestID")));
        BOM bom;
        CalculateBOM b = new CalculateBOM();
        if (r.getCarport().getInclination() != 0) {
            bom = b.inclineRoofBOM(r);
        } else {
            bom = b.generateFlatRoofCarportBOM(r);
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
        DrawSVG d = new DrawSVG();
        InclineRoofCarportBOM ic = new InclineRoofCarportBOM();
        String svg = d.drawTopInclineShed(r.getCarport());
        String bandSvg = d.drawPerforatedBand(r.getCarport());
        request.setAttribute("svg", svg);
        request.setAttribute("bandSvg", bandSvg);

        return "create_offer.jsp";
    }

}
