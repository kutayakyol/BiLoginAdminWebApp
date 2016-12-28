/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.orgchart.model;

/**
 *
 * @author admin
 */
import org.json.JSONArray;

import xxxt.orgchart.dao.OrgName;

public class OrgNamemodel {

    public JSONArray GetName(Integer p_org_type_id) {
        JSONArray feeds = new JSONArray();
        feeds = null;
        OrgName orgChart=new OrgName();
        try {
            feeds=orgChart.getOrgName(p_org_type_id);
        } catch (Exception e) {
//            throw e;
        }
        return feeds;
    }
}