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
public class OrgchartImpl {

    /**
     *
     * @param orgName
     * @return
     */
    
    public JSONArray getOrgChart(Integer orgName, Integer orgType) {


        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        //Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String getDBUSERByUserIdSql = "{call apps.xxxt_bi_ws_pkg.get_org_hierarcy(?,?,?)}";
        //String jsonInString = null;
        JSONArray arr = new JSONArray();
        try {

            callableStatement = s.connection().prepareCall(getDBUSERByUserIdSql);
            callableStatement.setInt(1, orgName);
            callableStatement.setInt(2, orgType);
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.executeUpdate();
            rs = (java.sql.ResultSet) callableStatement.getObject(3);

            //ObjectMapper mapper = new ObjectMapper();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                String HIERARCY_ID = rs.getString(1);
                String PERSONEL_ID = rs.getString(2);
                String PARENT_PERSONAL_ID = rs.getString(3);
                String PERSONAL_NAME = rs.getString(4);
                String TITLE = rs.getString(5);
                String KISI_ID = rs.getString(6);
                String TCKN = rs.getString(7);
                String BAS_TARIH = rs.getString(8);
                String HIZMEY_YILI = rs.getString(9);
               if (PARENT_PERSONAL_ID == null && !"1".equals(HIERARCY_ID.toString())){
                 continue;
                 }
               else{
                 //Extract data to JSONArray
                obj.put("HIERARCY_ID", HIERARCY_ID).toString();
                obj.put("PERSONEL_ID", PERSONEL_ID).toString();
                obj.put("PARENT_PERSONAL_ID", PARENT_PERSONAL_ID).toString();
                obj.put("PERSONAL_NAME", PERSONAL_NAME).toString();
                obj.put("TITLE", TITLE).toString();
                obj.put("IMAGE", KISI_ID).toString();
                obj.put("TCKN", TCKN).toString();
                obj.put("BAS_TARIH", BAS_TARIH).toString();
                obj.put("HIZMEY_YILI", HIZMEY_YILI).toString();
                arr.put(obj);
              }    
                //jsonInString = mapper.writeValueAsString(arr);

            }
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
