/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

import java.util.List;

/**
 * Bill of materials - is instantiated in BOMCalculator and used in ShowRequestCommand
 * which puts the BOM on request attribute so it can be accessed in jsp-files
 * @author sinanjasar
 */
public class BOM {
    private final List<LineItem> lineitems;

    public BOM(List<LineItem> lineitems) {
        this.lineitems = lineitems;
    }
    public List<LineItem> getLineitems() {
        return lineitems;
    }
}
