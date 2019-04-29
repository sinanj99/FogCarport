/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

import java.util.List;

/**
 *
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
