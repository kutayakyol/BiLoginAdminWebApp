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
        <link rel="stylesheet" href="resources/css/model.css">
        <link rel="stylesheet" href="resources/css/control.css">
        <style>
            body { background-color:#e6f3ff;}
        </style>

    <header><font style="font-family:Helvetica Neue, Helvetica, Arial, sans-serif"size="4"><b>Admin Panel Şifre Değiştirme Ekranı</b></font></header>
</head>

<body>

    <script src="resources/js/control_2.3.0.js" type="text/javascript"></script>
    <script src="resources/js/extras-control_2.3.0.js" type="text/javascript"></script>
    <script src="resources/js/jquery.min.js"></script>

    <c:if test="${not empty messagepassword}">
                <div class="center">
                <font color="black"  > <b>  ${messagepassword} </b></font> 
                </div>
    </c:if>

    <form:form method="POST" action="updateAdminPassword.html" commandName="biAdmin" onsubmit="return checkForm()">
        <input type="hidden" name="id" value="${admin.id}"/>
        <input type="hidden" name="kaydiyapankullanici" value="${admin.kaydiyapankullanici}"/>
        <input type="hidden" name="kaydiguncelleyenkullanici" value="${admin.kaydiguncelleyenkullanici}"/>
        <table cellpadding="2" cellspacing="2">
            <tr>
                <td><label class="label" for="kullaniciadi" >Kullanıcı Adı :</label></td>
                <td><input type="text" class="textbox" id="username" value="${admin.kullaniciadi}" name="kullaniciadi" style="height: 30px;width:170px" disabled="disabled" readonly="readonly"/></td>
            </tr>
            <tr>
                <td><label class="label" for="sifre" >Yeni Şifre :</label></td>
                <td><input type="password" id="pass1" name="sifre" required="required" class="textbox" style="height: 30px;width:170px"/></td>
            </tr>
            <tr>
                <td><label class="label" for="sifre" >Yeni Şifre Tekrar:</label></td>
                <td><input type="password" id="pass2" required="required" class="textbox" style="height: 30px;width:170px"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="updateAdminSifre" class="myButton" value="Şifre Değiştir..."  style="height:35px;width:150px"/></td>
            </tr>
        </table>
            <br/><br/>
        <table>
            <tr>
                <td>
                    <span style="font-size: 1.3em;">

                        <b>Not: </b>Yeni gireceğiniz şifre; 
                        <br/>
                        <br/><i>En az 8 karakterden oluşmalı en az bir sayı,</i> 
                        <br/><i>En az bir büyük harf,</i> 
                        <br/><i>En az bir küçük harf içermelidir.</i>
                        <br/>
                        <br/>Şifreniz değiştikten sonra sistem sizi admin panel uygulaması giriş ekranına yönlendirecektir. 
                        <font style="font-size: 1.0em;" color=#e60000><br/><b>YENİ ŞİFRENİZLE </b>TEKRAR GİRİŞ YAPABİLİRSİNİZ.</font>
                    </span>
                </td>
            </tr>
        </table>

        <script>
        
            function checkForm()
            {
                var username = document.getElementById('username').value;
                var pass1 = document.getElementById('pass1').value;
                var pass2 = document.getElementById('pass2').value;
            
            
            
                if(username == "") {
                    alert("Kullanıcı adı boşluk içermemeli!");
                    document.getElementById('username').focus();
                    return false;
                }

                if(pass1 != "" && pass1 == pass2) {
                    if(pass1.length < 8) {
                        alert("Şifreler en az 8 karakter içermeli!");
                        document.getElementById('pass1').focus();
                        return false;
                    }
                    if(pass1 == username) {
                        alert("Şifre kullanıcı adından farklı olmalı!");
                        document.getElementById('pass1').focus();
                        return false;
                    }
                    re = /[0-9]/;
                    if(!re.test(pass1)) {
                        alert("Şifre en az bir adet rakam (0-9) içermeli!");
                        document.getElementById('pass1').focus();
                        return false;
                    }
                    re = /[a-z]/;
                    if(!re.test(pass1)) {
                        alert("Şifre en az bir adet küçük harf (a-z) içermeli!");
                        document.getElementById('pass1').focus();
                        return false;
                    }
                    re = /[A-Z]/;
                    if(!re.test(pass1)) {
                        alert("Şifre en az bir adet büyük harf (A-Z) içermeli!!");
                        document.getElementById('pass1').focus();
                        return false;
                    }
                } else {
                    alert("Girilen şifreler eşleşmelidir!");
                    document.getElementById('pass1').focus();
                    return false;
                }
                return true;
            }
            
           

                 
        </script>   

    </form:form>
</body>



</html>
