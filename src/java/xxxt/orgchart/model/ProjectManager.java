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

import xxxt.orgchart.dao.OrgchartImpl;

public class ProjectManager {

    public JSONArray GetFeeds(Integer orgName, Integer orgType) {
        JSONArray feeds = new JSONArray();
        feeds = null;
        OrgchartImpl orgChart=new OrgchartImpl();
        try {
            feeds=orgChart.getOrgChart(orgName,orgType);
        } catch (Exception e) {
//            throw e;
        }
        return feeds;
    }
}