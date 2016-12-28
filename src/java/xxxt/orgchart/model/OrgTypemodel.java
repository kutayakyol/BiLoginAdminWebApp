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

import xxxt.orgchart.dao.OrgType;

public class OrgTypemodel {

    public JSONArray GetType() {
        JSONArray feeds = new JSONArray();
        feeds = null;
        OrgType orgChart=new OrgType();
        try {
            feeds=orgChart.getOrgType();
        } catch (Exception e) {
//            throw e;
        }
        return feeds;
    }
}