<%-- 
    Document   : editRol
    Created on : Jun 24, 2016, 11:54:48 AM
    Author     : admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Rol Ekle</title>

        <%@include file="navmenu.jsp" %>

        <link rel="stylesheet" href="resources/css/model.css">
        <link rel="stylesheet" href="resources/css/control.css">
        <%--link rel="stylesheet" href="resources/css/jquerysctipttop.css" integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd" crossorigin="anonymous"--%>

        <style>
            body { background-color:#e6f3ff;}
        </style>

    <header class="labelbas"><b>Rol Ekleme</b></header>

</head>
<body>

    <script src="resources/js/control_2.3.0.js" type="text/javascript"></script>
    <script src="resources/js/extras-control_2.3.0.js" type="text/javascript"></script>
    <script src="resources/js/jquery.min.js"></script>


    <%--div class="center">
            <!--font color="black" border="solid"  >   ${messageedit}</font-->

        <c:if test="${not empty messageedit}">
            <font color="black"  > <b> <mark>  ${messageedit} </mark></b></font> 
        </c:if>

    </div--%>

    <form:form method="POST" action="createRol.html" commandName="Rol" >
        <div id="panel">
            <table>
                <tr>
                    <td><label class="label" for="roladi" >Rol Adı :</label></td>
                    <td><input type="text" name="roladi" value="${rol.roladi}" id="roladi" class="textbox" style="height: 40px;width:200px"/></td>
                </tr>
                <tr>
                    <td> <label class="label" for="aciklama"><b>Açıklama :</b>  </label></td>
                    <td> <input type="text" name="aciklama" value="${rol.aciklama}" id="aciklama" class="textbox" style="height: 40px;width:200px"/></td>
                </tr>
                <tr>
                    <td><label class="label" for="aktifmi" ><b>Aktif Mi :&nbsp;&nbsp;&nbsp;</b></label></td>
                    <%--td><form:label path="aktifmi" ><b>Aktif Mi :&nbsp;&nbsp;&nbsp;</b></form:label></td--%>
                    <td><form:checkbox class="" path="aktifmi"></form:checkbox></td>
                </tr>
                <br/>
                <tr>
                    <td><input type="submit" id="subsave" name="save" value="Kaydet" class="myButton"/></td>
                    <td><input type="submit" id="subid" name="cancel" value="İptal" class="myButton"/></td>
                </tr>
            </table>
        </div>            

        <%--
        <input type="hidden" name="form_name" id="form_form_name" value="form"/>
        <table class="form" id="form-form"><tbody>
                <tr><td>
                        <table class="fields" id="form-fields"><tbody>
                                <tr class="fields">
                                    <td class="fields" align="left"><label class="label" for="form_roles"><b>Göreceği Menüler</b></label>&#160;</td>
                                    <td align="left"> 
                                        <table width="400" class="picklist">
                                            <thead>
                                                <tr>
                                                    <th>Menüler</th>
                                                    <th>&#160;</th>
                                                    <th><b>Seçilenler</b></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td width="50%">
                                                        <select id="form_roles_unselected" size="8" style="width:100%;" multiple="multiple" class="poplistss">

                                                            <c:forEach items="${leftList}" var="rolMenu" varStatus="count"> 
                                                                <option value="${rolMenu.id}" >${rolMenu.altmenuadi}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td valign="middle">
                                                        <input class="myButton1" id="form_roles_add" type="button" value="&gt;" style="width:60px;" onclick="pickListMove(document.getElementById('form_roles_unselected'), document.getElementById('form_roles'), document.getElementById('form_roles_hidden'), true)"/><br/>
                                                        <input class="myButton1" id="form_roles_remove" type="button" value="&lt;" style="width:60px;" onclick="pickListMove(document.getElementById('form_roles'), document.getElementById('form_roles_unselected'), document.getElementById('form_roles_hidden'), false)"/><br/>
                                                        <input class="myButton1" id="form_roles_add_all" type="button" value="&gt;&gt;" style="width:60px;" onclick="pickListMoveAll(document.getElementById('form_roles_unselected'), document.getElementById('form_roles'), document.getElementById('form_roles_hidden'), true)"/><br/>
                                                        <input class="myButton1" id="form_roles_remove_all" type="button" value="&lt;&lt;" style="width:60px;" onclick="pickListMoveAll(document.getElementById('form_roles'), document.getElementById('form_roles_unselected'), document.getElementById('form_roles_hidden'), false)"/><br/>
                                                    </td>
                                                    <td width="50%">
                                                        <select id="form_roles" name="form_roles" size="8" style="width:100%;" multiple="multiple" class="poplistss">


                                                        </select>
                                                        <select id="form_roles_u" name="form_roles_u" size="8" style="display: none;" style="width:100%;" multiple="multiple">
                                                            <c:forEach items="${rightList}" var="item" varStatus="count"> 
                                                                <option value="${item.id}" >${item.altmenuadi}</option>
                                                            </c:forEach>

                                                        </select>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <select id="form_roles_hidden" name="roles" style="display: none;" multiple="multiple">
                                            <c:forEach items="${leftList}" var="item" varStatus="count"> 
                                                <option value="${item.id}" >${item.altmenuadi}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody></table>
                    </td></tr>
            </tbody></table>
        --%>



        <script >
            pickliststatu(document.getElementById('form_roles_unselected'),document.getElementById('form_roles_u'), document.getElementById('form_roles'), document.getElementById('form_roles_hidden'), true);
                 
        </script>
    </form:form>



</body>



</html>
