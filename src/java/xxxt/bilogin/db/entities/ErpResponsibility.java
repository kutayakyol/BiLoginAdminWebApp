/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.db.entities;

import java.math.BigDecimal;

/**
 *
 * @author admin
 */
public class ErpResponsibility {

    private BigDecimal id;
    private String responsibilityname;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getResponsibilityname() {
        return responsibilityname;
    }

    public void setResponsibilityname(String responsibilityname) {
        this.responsibilityname = responsibilityname;
    }
}
