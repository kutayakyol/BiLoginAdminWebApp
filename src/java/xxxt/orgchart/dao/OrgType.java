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
public class OrgType {

    /**
     *
     * @param orgName
     * @return
     */
    
    public JSONArray getOrgType() {


        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        //Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String getDBUSERByUserIdSql = "{call apps.xxxt_bi_ws_pkg.get_org_type_list(?)}";
        //String jsonInString = null;
        JSONArray arr = new JSONArray();
        try {

            callableStatement = s.connection().prepareCall(getDBUSERByUserIdSql);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeUpdate();
            rs = (java.sql.ResultSet) callableStatement.getObject(1);

            //ObjectMapper mapper = new ObjectMapper();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                String ORG_TYPE_ID = rs.getString(1);
                String ORG_TYPE_NAME = rs.getString(2);
                obj.put("ORG_TYPE_ID", ORG_TYPE_ID).toString();
                obj.put("ORG_TYPE_NAME", ORG_TYPE_NAME).toString();
                arr.put(obj);
                }    
                //jsonInString = mapper.writeValueAsString(arr);
                return arr;
        }catch (Exception e) {
            e.printStackTrace();
            arr = null;
            return arr;
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }
}
