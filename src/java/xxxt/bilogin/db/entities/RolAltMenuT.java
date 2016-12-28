/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.db.entities;

import java.math.BigDecimal;

/**
 *
 * @author kutay.akyol
 */
public class RolAltMenuT {
    private Integer id;
    private BigDecimal izinVerilenRollerId;
    private BigDecimal altMenulerId;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getIzinVerilenRollerId() {
        return izinVerilenRollerId;
    }

    public void setIzinVerilenRollerId(BigDecimal izinVerilenRollerId) {
        this.izinVerilenRollerId = izinVerilenRollerId;
    }

    public BigDecimal getAltMenulerId() {
        return altMenulerId;
    }

    public void setAltMenulerId(BigDecimal altMenulerId) {
        this.altMenulerId = altMenulerId;
    }
    
}
