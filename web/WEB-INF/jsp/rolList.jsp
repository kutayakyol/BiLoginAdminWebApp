<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Rol Listesi</title>
        <%@include file="navmenu.jsp" %>
        <link rel="stylesheet" href="resources/css/jquery.dataTables.css">
        <link rel="stylesheet" href="resources/css/control.css">
        <style>
            table#persons tr:nth-child(even) {
                background-color: #eee;
            }
            table#persons tr:nth-child(odd) {
                background-color:#fff;
            }
            table#persons th {
                background-color: gray;
                color: white;
            }

        </style>
        <style>
            .centerdiv {
                text-align: center;


                border: 3px solid ;
            }
            .centerdiv1 {
                text-align: center;

            }
        </style>
        
        <style>
            body { background-color:#e6f3ff;}
        </style>
    </head>
    <body>
        <br>
        <br>

        <table id="persons" class="display" cellspacing="0" width="100%">
                       <thead>
                <tr>
                    <th>Rol Adı</th>
                    <th>Rol Açıklaması</th>
                    <th>İşlemler</th>
                </tr>
            </thead>
            
<tbody>
            <c:forEach items="${listRol}" var="rol" >
                
                    <tr>
                        <td>${rol.roladi}</td>
                        <td>${rol.aciklama}</td>
                        <td>
                            <div width="100%" align="center">
                            <a href="editRol.html?id=${rol.id}"><img src="resources/images/edit.png" height="22px" width="22px" alt="Güncelle" title="Güncelle" /></a>
                            
                            <%--a href="rolEsle.html?id=${rol.id}" ><img  src="resources/images/rol.png" height="22px" width="32px" alt="Erp ile Eşle" title="Erp ile Eşle" /></a--%>
                            <a href="removeRol.html?id=${rol.id}" onclick="return confirm('Rolü silme i\u015flemini onaylıyor musun?')"><img src="resources/images/delete.png" height="22px" width="22px" alt="Rolü Sil" title="Rolü Sil" /></a>
                            
                         </div>


                    </td>
                        


                    </tr>


               
            </c:forEach>
                         </tbody>

        </table>
        <script src="resources/js/jquery.min.js"></script>
        <script src="resources/js/jquery.dataTables.min.js"></script>
        <script>
            $(function() {
                $('#persons').dataTable({"searching":false});
                
                
                
		
            });
        </script>
    </body>
</html>