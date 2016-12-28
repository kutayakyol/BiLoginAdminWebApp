/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.objects;

import java.io.IOException;

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
@Component("KisiResimServlet")
public class KisiResimServlet implements HttpRequestHandler {

    @Autowired
    private KullaniciService kullaniciService;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String imageName = request.getPathInfo().substring(1); // Returns "foo.png".

        try {
            Kullanici k = new Kullanici();
            int id = 0;
            String idStr = request.getParameter("id");
            if (idStr.contains(".")) {
                String[] s = null;
                System.out.print("11...........kisiresimservlet............idstr: "+idStr);
                s = idStr.split("\\.");
                System.out.print("12...........kisiresimservlet............s[0] : "+s[0]+",  s[1]: "+s[1]);
                id = Integer.parseInt(s[0]);
            } else {
                System.out.println("21.........kisiresimservlet......idstr: "+idStr);
                id = Integer.parseInt(idStr);
            }

            System.out.println("KisiResimServlet....id:" + id);


            k = kullaniciService.getKullanici(id);
            byte[] content = kullaniciService.getImage(k.getKisiid(),"U");
            response.setContentType("image/jpg");
            response.setContentLength(content.length);
            response.getOutputStream().write(content);


        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.

            System.out.println("KisiResimServlet....HATA");
        } finally {
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }

    }
}
