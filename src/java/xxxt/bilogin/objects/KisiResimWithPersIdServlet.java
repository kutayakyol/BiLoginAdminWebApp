/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.objects;

import java.io.IOException;
import java.math.BigDecimal;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import xxxt.bilogin.db.entities.Kullanici;
import xxxt.login.service.KullaniciService;
/**
 *
 * @author kutay.akyol
 */
@Component("KisiResimWithPersIdServlet")
public class KisiResimWithPersIdServlet implements HttpRequestHandler {

    @Autowired
    private KullaniciService kullaniciService;
    
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String imageName = request.getPathInfo().substring(1); // Returns "foo.png".

        try {
            Kullanici k =new Kullanici();
            String kisiidStr = request.getParameter("personId");
            BigDecimal kisiId=new BigDecimal(kisiidStr);
            
            System.out.println("KisiResimWithPersIdServlet....kisiid:"+kisiId.toString());
            
            byte[] content = kullaniciService.getImage(kisiId,"I");
            response.setContentType("image/jpg");
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
            

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            
            System.out.println("KisiResimWithPersIdServlet....HATA");
        } finally {
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }

    }
}
