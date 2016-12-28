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
 * @author admin
 */
@Entity
@Table(name="ISLETMETIPI"
)
public class IsletmeTipi {
     private BigDecimal id;
    @Id
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getIsletmetipi() {
        return isletmetipi;
    }

    public void setIsletmetipi(String isletmetipi) {
        this.isletmetipi = isletmetipi;
    }
     private String isletmetipi;
}
