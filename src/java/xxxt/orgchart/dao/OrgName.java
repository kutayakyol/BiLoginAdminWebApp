/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.orgchart.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import xxxt.bilogin.db.SessionFactoryUtil;

/**
 *
 * @author admin
 */
public class OrgName {

    /**
     *
     * @param orgName
     * @return
     */
    
    public JSONArray getOrgName(Integer p_org_type_id) {


        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        //Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String getDBUSERByUserIdSql = "{call apps.xxxt_bi_ws_pkg.get_org_list(?,?)}";
        //String jsonInString = null;
        JSONArray arr = new JSONArray();
        try {

            callableStatement = s.connection().prepareCall(getDBUSERByUserIdSql);
            //Integer p_org_type_id= 1;
            callableStatement.setInt(1, p_org_type_id);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeUpdate();
            rs = (java.sql.ResultSet) callableStatement.getObject(2);

            //ObjectMapper mapper = new ObjectMapper();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                String ORG_ID = rs.getString(1);
                String ORG_NAME = rs.getString(2);
              
                // Extract data to JSONArray
                obj.put("ORG_ID", ORG_ID).toString();
                obj.put("ORG_NAME", ORG_NAME).toString();
                arr.put(obj);
                }    
                //jsonInString = mapper.writeValueAsString(arr);
            
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            arr = null;
            return arr;
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }
}
