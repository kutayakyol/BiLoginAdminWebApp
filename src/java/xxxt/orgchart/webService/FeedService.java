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



@Path("getOrgChart/{p_org_tip}/{p_org_name}")
public class FeedService {

    @GET
    @Produces("application/json")
    public String feed(@PathParam("p_org_tip") Integer orgName,@PathParam("p_org_name") Integer orgType,@Context HttpServletResponse response) {
        //JSONObject jsonobj= new JSONObject();
        JSONArray feedData = new JSONArray();
//        String feedData="Hello!";
        String s = null;
//        try {
        ProjectManager projectManager = new ProjectManager();
        feedData = projectManager.GetFeeds(orgName, orgType);
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
