/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.objects;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author admin
 */
@Entity
public class Organization {
     @Id
   private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsletmeAdi() {
        return isletmeAdi;
    }

    public void setIsletmeAdi(String isletmeAdi) {
        this.isletmeAdi = isletmeAdi;
    }

    public String getOrganizasyon_kodu() {
        return organizasyon_kodu;
    }

    public void setOrganizasyon_kodu(String organizasyon_kodu) {
        this.organizasyon_kodu = organizasyon_kodu;
    }

    public String getYerlesim() {
        return yerlesim;
    }

    public void setYerlesim(String yerlesim) {
        this.yerlesim = yerlesim;
    }
  private String isletmeAdi;
   private String organizasyon_kodu;
  private String yerlesim;
}
