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
public class Isletme {
    private BigDecimal isletmeId;
    private String isletmeAdi;
    
    public Isletme(){
    }
    
    public Isletme(BigDecimal isletmeId,String isletmeAdi){
        this.isletmeId=isletmeId;
        this.isletmeAdi=isletmeAdi;
    }

    public BigDecimal getIsletmeId() {
        return isletmeId;
    }

    public void setIsletmeId(BigDecimal isletmeId) {
        this.isletmeId = isletmeId;
    }

    public String getIsletmeAdi() {
        return isletmeAdi;
    }

    public void setIsletmeAdi(String isletmeAdi) {
        this.isletmeAdi = isletmeAdi;
    }
}
