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
public class RolErpResponsibility {
    private Integer id;
    private BigDecimal rolid;
    private BigDecimal erpsorumlulularid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRolid() {
        return rolid;
    }

    public void setRolid(BigDecimal rolid) {
        this.rolid = rolid;
    }

    public BigDecimal getErpsorumlulularid() {
        return erpsorumlulularid;
    }

    public void setErpsorumlulularid(BigDecimal erpsorumlulularid) {
        this.erpsorumlulularid = erpsorumlulularid;
    }
    
}
