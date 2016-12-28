/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.orgchart.webService;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.json.*;
import xxxt.orgchart.model.*;



@Path("getOrgName/{p_org_type_id}")
public class OrgNameFeed {

    @GET
    @Produces("application/json")
    public String feedname(@PathParam("p_org_type_id") Integer p_org_type_id,@Context HttpServletResponse response) {
        //JSONObject jsonobj= new JSONObject();
        JSONArray feedData = new JSONArray();
//        String feedData="Hello!";
        String s = null;
//        try {
        OrgNamemodel orgNamemodel = new OrgNamemodel();
        feedData = orgNamemodel.GetName(p_org_type_id);
        s = feedData.toString();
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Auth-Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "*");
        //HttpHeaders headers = addAccessControllAllowOrigin();
        //jsonobj.put("arrayname",feedData);
//        } catch (Exception e) {
//            System.out.println("Exception Error"); //Console 
//        } 
        return s;
    }
}
