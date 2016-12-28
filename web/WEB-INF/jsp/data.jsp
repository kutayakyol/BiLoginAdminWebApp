<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Kullanıcı Listesi</title>
        <%@include file="navmenu.jsp" %>
   
    <link rel="stylesheet" href="resources/css/jquery.dataTables.css">
    <link rel="stylesheet" href="resources/css/control.css">

    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/jquery.dataTables.min.js"></script>
   

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

    <meta name="SKYPE_TOOLBAR" content="SKYPE_TOOLBAR_PARSER_COMPATIBLE" />

</head>
<body>


    <form:form method="post" commandName="acm" action="data.html" >

        <br>
        <div class="centerdiv">
            <table align="center">

                <tr>
                    <td><label for="kullaniciadi" class="label" > Kullanıcı Adı :</label></td>
                    <td><input type="text" name="kullaniciadi"  id="kullaniciadi" class="textboxdata" /></td>&nbsp;
                    <td><label for="ad" class="label" > Ad :</label></td>
                    <td><input type="text" name="ad"  id="ad" class="textboxdata" /></td>&nbsp;
                </tr>
                <tr>
                    <td><label for="soyad" class="label"> Soyad :</label></td>
                    <td><input type="text" name="soyad"  id="soyad" class="textboxdata" /></td>&nbsp;
                    <td><label for="tcno" class="label"> TC Kimlik No :</label></td>
                    <td><input type="text" name="tcno"  id="tcno" class="textboxdata" /></td>&nbsp;
                </tr>  
                <tr>
                    <td><label for="isletmeadi" class="label"> İşletme :</label></td>
                    <td><input type="text" name="isletmeadi"  id="isletmeadi" class="textboxdata" /></td>&nbsp;
                    <td><label for="orgKod" class="label"> Organizasyon Kodu :</label></td>
                    <td><input type="text" name="orgKod"  id="orgKod" class="textboxdata" /></td>&nbsp;
                </tr>
                <tr>
                    <td><label for="sicilno" class="label"> Sicil No :</label></td>
                    <td><input type="text" name="sicilno"  id="sicilno" class="textboxdata"/></td>&nbsp;
                    <td><label for="kisiid" class="label"> Kişi Id :</label></td>
                    <td><input type="text" name="kisiid"  id="kisiid" class="textboxdata" /></td>&nbsp;
                </tr>



            </table>
            <table align="center">
                <tr>
                    <td><input type="submit"  value="Sorgula" class="myButton"/></td>
                    <td>
                        <div id="spinner">
                            <img class="left" id="small_loader" src="resources/images/small_loader.gif" width="40px" height="40px"/>
                        </div>
                    </td>

                </tr>
            </table>
        </div>





    </form:form>



    <br>
    <br>

    <table id="persons" class="display" cellspacing="0" width="100%" style="display: none" >
        <thead>
            <tr>
                <th>Kullanıcı Adı</th>
                <th>Ad</th>
                <th>Soyad</th>
                <th>TC Kimlik No</th>
                <th>İşletme</th>
                <th>Organizasyon Kodu</th>            
                <th>Sicil No</th>                
                <th>Kişi Id</th>
                <th>İşlemler</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lst}" var="p" >

                <tr>

                    <td>${p.kullaniciadi}</td>
                    <td>${p.ad}</td>
                    <td>${p.soyad}</td>
                    <td>${p.tcno}</td>
                    <td>${p.isletmeadi}</td>
                    <td>${p.orgKod}</td>
                    <td>${p.sicilno}</td>                   
                    <td>${p.kisiid}</td>

                    <td>
                        <div width="100%">
                            <a href="edit.html?id=${p.id}&tcno=null"><img src="resources/images/edit.png" height="22px" width="21%" alt="Güncelle" title="Güncelle" /></a>

                            <a href="sifreDegistir.html?id=${p.id}" ><img src="resources/images/icon-password.png" height="22px" width="21%"  title="Şifre Değiştir" /></a>
                            <a href="organizasyonAta.html?id=${p.id}" ><img src="resources/images/org.png" height="22px" width="21%" alt="Organizasyon" title="Organizsyon Değiştir" /></a>
                            <a href="remove.html?id=${p.id}" onclick="return confirm('Silme işlemini onaylıyor musun?')"><img src="resources/images/delete.png" height="22px" width="21%" alt="Sil" title="Sil" /></a>

                            <!--a href="#" ><img src="resources/images/edit.png" height="20px" width="20px" alt="Organizsyon" title="Organizsyon Değiştir" /></a>
                            
                            <a href="#" ><img src="resources/images/edit.png" height="20px" width="20px" alt="Organizsyon" title="Organizsyon Değiştir" /></a>
                            
                            <a href="#" ><img src="resources/images/edit.png" height="20px" width="20px" alt="Organizsyon" title="Organizsyon Değiştir" /></a-->

                            <!--a href="#" onclick="return confirm('Emin misin?')"><img src="resources/images/edit.png" height="20px" width="20px" alt="Sil" title="Sil" /></a-->

                        </div>


                    </td>

                </tr>



            </c:forEach>
        </tbody>

    </table>
    <script>
           
            
        $(function() {
            //hideLoader();
            dt_table= $('#persons').dataTable({"searching":true,"deferRender":true,"autoFill":true,"oSearch": {"bCaseInsensitive":true},"oLanguage": {
                    "sLengthMenu":"_MENU_ Kayıt Aralığı",
                    "sSearch": "Arama:"
                }});
            $("#persons").show(); 
            $("#spinner").hide();
            $('#ad').on( 'keyup', function () {
                dt_table
                .columns( 3 )
                .search( this.value )
                .draw();
            } );
                
                  
            
                
               
                
		
        });      
        /* $('#ad').on( 'keyup', function () { dt_table.search( this.value ).draw(); } );  
            
            $('#kullaniciadi').on( 'keyup change', function () {
                dt_table.search( this.value ).draw();
            } );*/
            
         
            
    
            
          
    </script>
</body>
</html>