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
        <title>Şifre Değiştir</title>
        <%@include file="navmenu.jsp" %>
        <link rel="stylesheet" href="resources/css/model.css">
        <link rel="stylesheet" href="resources/css/control.css">
        <style>
            .center {
                text-align: center;
            }
            .error{
                color: #ff0000;
            }
        </style>
    </head>
   
    <body>
         <header><b>Şifre Sıfırla</b></header>
         
        

            <c:if test="${not empty messagepassword}">
                <div class="center">
                <font color="black"  > <b>  ${messagepassword} </b></font> 
                </div>
            </c:if>

        
        <form:form action="passwordzeroupdate.html" modelAttribute="Kullanici">
            <input type="hidden" name="id" value="${k.id}"/>
           </br>
           <label for="kullaniciadi" class="label">Kullanıcı Adı :</label>
           <input type="text"  class="textbox" name="kullaniciadi" value="${k.kullaniciadi}" id="kullaniciadi" style="height: 40px;width:200px"/>
      
              </br>
               </br>
             

            <input type="submit" id="subid" value="Şifre Sıfırla" class="myButton"/>
            
            


        </form:form>
    </body>
</html>
