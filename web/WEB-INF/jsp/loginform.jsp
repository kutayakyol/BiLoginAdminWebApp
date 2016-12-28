<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>BI Login</title>


        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>

        <div class="login-card">
            <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BI Kullanıcı Giriş</h3>

            <FONT color="red">   ${message}</font>

            <form:form method="post" commandName="acc" action="loginform.html">
                <table cellpadding="2" cellspacing="2">
                    <tr>
                        <td>Kullanıcı</td>
                        <td><form:input path="kullaniciadi" required="required" /></td>
                        <!--td><form:input path="kullaniciadi" required="required" style="display:none;"/></td-->
                    </tr>
                    <tr>
                        <td>Şifre</td>
                        <td><form:password path="sifre" required="required"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><input type="submit" class="login login-submit" value="Login"  /></td>
                    </tr>
                </table>




            </form:form>


        </div>
    </body>
</html>