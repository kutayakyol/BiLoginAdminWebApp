/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.db.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.Id;

/**
 *
 * @author admin
 */
@Entity
@Table(name="PARAMETRE_V"
)
public class Parametre implements java.io.Serializable  {
    private BigDecimal id;
     private String deger;

     @Id
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDeger() {
        return deger;
    }

    public void setDeger(String deger) {
        this.deger = deger;
    }

    public String getPmetre() {
        return pmetre;
    }

    public void setPmetre(String pmetre) {
        this.pmetre = pmetre;
    }
     private String pmetre;
    
}
