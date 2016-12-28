/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.objects;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
@Component("SifreDegistirServlet")
public class SifreDegistirServlet implements HttpRequestHandler {

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
                System.out.print("11...........SifreDegistirServlet............idstr: " + idStr);
                s = idStr.split("\\.");
                System.out.print("12...........SifreDegistirServlet............s[0] : " + s[0] + ",  s[1]: " + s[1]);
                id = Integer.parseInt(s[0]);
            } else {
                System.out.println("21.........SifreDegistirServlet......idstr: " + idStr);
                id = Integer.parseInt(idStr);
            }

            System.out.println("SifreDegistirServlet....id:" + id);


            k = kullaniciService.getKullanici(id);

            Integer i = -1;

            if (k.getSifreDegistir()) {
                i = 1;
            } else {
                i = 0;
            }

            System.out.printf("SifreDegistirServlet....getSifreDegistir(): " + i.toString());

            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Auth-Token");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", "*");

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.append(i.toString());
            out.close();

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.

            System.out.println("SifreDegistirServlet....HATA");
        } finally {
            response.getWriter().flush();
            response.getWriter().close();
        }

    }
}
