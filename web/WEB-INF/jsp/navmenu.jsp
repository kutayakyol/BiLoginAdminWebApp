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
        <title>BI Admin Console - Anasayfa</title>
    
    <link href="resources/css/nav.css" rel="stylesheet" />
    <script src="resources/js/control_2.3.0.js"  type="text/javascript"></script>

    <style>
        body { background-color:#e6f3ff;}
    </style>

</head>


<body>

    <nav>
        <ul class="cf">                
            <li>
                <a class="dropdown" href="#">Kullanıcı</a>
                <ul>
                    <li><a href="personadd.html?tcno=null">Kullanıcı Ekle</a></li>
                    <li><a href="getAll.html">Kullanıcı Listesi</a></li>                        
                </ul>
            </li>

            <li id="rolmenu">
                <a class="dropdown" href="#">Rol</a>
                <ul>
                    <li><a href="addRol.html">Rol Ekle</a></li>
                    <li><a href="getAllRol.html">Rol Listesi</a></li>                        
                </ul>
            </li>

            <li>
                <a href="sifreDegistirBiLogin.html">Şifre Değiştir</a>
            </li>

            <li><a href="loginform.html">Çıkış</a></li>

        </ul>
    </nav>
    <script >
        var user="<%=session.getAttribute("loginkisiid")%>";
        //alert(user);
        if(user =='-1' || user =='52960'|| user =='49640'){
            document.getElementById("rolmenu").style.display= "";
        } else document.getElementById("rolmenu").style.display= "none";
    </script>

</body>
</html>
