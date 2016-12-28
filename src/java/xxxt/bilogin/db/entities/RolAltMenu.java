/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.db.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author kutay akyol
 */
@Entity
@Table(name = "ROL_ALTMENU_XT")
public class RolAltMenu {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private BigDecimal izinVerilenRollerId;
    private BigDecimal altMenulerId;
    private String rolAdi;
    private String altMenuAdi;

    public RolAltMenu() {
    }

    public RolAltMenu(Long id, Long locationId, String locationCode, String description, String townOrCity, Long organizationId, String name, String organizationCode, String glYerlesim) {
        this.id = id;
        this.izinVerilenRollerId = izinVerilenRollerId;
        this.altMenulerId = altMenulerId;
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
    
     public String getRolAdi() {
        return rolAdi;
    }

    public void setRolAdi(String rolAdi) {
        this.rolAdi = rolAdi;
    }

    public String getAltMenuAdi() {
        return altMenuAdi;
    }

    public void setAltMenuAdi(String altMenuAdi) {
        this.altMenuAdi = altMenuAdi;
    }
    
}
