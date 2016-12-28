<%-- 
    Document   : edit
    Created on : Jun 22, 2016, 7:19:03 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Organizasyon Ata</title>
        <%@include file="navmenu.jsp" %>
        <link rel="stylesheet" href="resources/css/model.css">
        <link rel="stylesheet" href="resources/css/control.css">
        
        <style>
            body { background-color:#e6f3ff;}
        </style>
    </head>
    <body>
        <header><b>Organizasyon Ata</b></header>
         <c:if test="${not empty orgmessage}">
                <div class="center">
                <font color="black"  > <b>  ${orgmessage} </b></font> 
                </div>
            </c:if>
        <form:form action="organizasyonataupdate.html" modelAttribute="KullaniciOrganizasyon">
            <table>
                <input type="hidden" name="id" value="${k.id}"/>

                <tr>
                    <td><label for="kullaniciadi" class="label">Kullanıcı Adı :</label></td>
                    <td><input type="text" name="kullaniciadi" value="${k.kullaniciadi}" id="kullaniciadi" readonly="readonly" class="textbox" style="height: 40px;width:200px"/></td>
                </tr>


                <tr>
                    <td><label for="orgid" class="label"> Organizasyon :</label></td>
                    <td><select id="orgid" name="orgid" class="textbox" style="height: 40px;width:500px">
                            <option value="1">${deger}</option>
                            <c:forEach items="${orglist}" var="item" varStatus="count"> 
                                <option value="${item.id}" >${item.description}-${item.organizationCode}-${item.glYerlesim}</option>

                            </c:forEach>



                        </select></td>

                </tr>
            </table>



            <br/>
            <input type="submit" id="subid" value="Organizasyon Ata" class="myButton"/>



        </form:form>

    </body>
</html>
