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
public class IsletmeDetay {
    private BigDecimal isletmeId;
    private String isletmeAdi;
    private String orgKod;
    private String isletmeTipi;

    public IsletmeDetay (){
    }
    
    public IsletmeDetay(String orgKod){
    this.orgKod=orgKod;}
    
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

    public String getOrgKod() {
        return orgKod;
    }

    public void setOrgKod(String orgKod) {
        this.orgKod = orgKod;
    }

    public String getIsletmeTipi() {
        return isletmeTipi;
    }

    public void setIsletmeTipi(String isletmeTipi) {
        this.isletmeTipi = isletmeTipi;
    }
    
}
