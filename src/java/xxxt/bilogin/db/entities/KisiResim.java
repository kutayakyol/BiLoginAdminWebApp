/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.db.entities;

import java.math.BigDecimal;
import java.sql.Blob;

/**
 *
 * @author kutay.akyol
 */
public class KisiResim {

    private Long id;
    private BigDecimal kisiid;
    private Blob resim;

    public KisiResim() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KisiResim(BigDecimal kisiid, Blob resim) {
        this.kisiid = kisiid;
        this.resim = resim;
    }

   
     public BigDecimal getKisiid() {
        return kisiid;
    }

    public void setKisiid(BigDecimal kisiid) {
        this.kisiid = kisiid;
    }

    public Blob getResim() {
        return resim;
    }
 public void setResim(Blob resim) {
        this.resim = resim;
    }
  
   

   
}
