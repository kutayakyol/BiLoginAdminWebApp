<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kullanıcı Ekleme</title>
        <%@include file="navmenu.jsp" %>
        <link rel="stylesheet" href="resources/css/model.css">
        <link rel="stylesheet" href="resources/css/control.css">
        <style>
            body { background-color:#e6f3ff;}

            .left
            {
                float: left;
                margin-left: -285px;
            }
        </style>

        <script src="resources/js/control_2.3.0.js" type="text/javascript"></script>
        <script src="resources/js/extras-control_2.3.0.js" type="text/javascript"></script>
        <script src="resources/js/jquery.min.js" type="text/javascript"></script>
        <script src="resources/js/json2.js" type="text/javascript"></script>
        
        
        <script type="text/javascript">
            
            function showLoader() {
                $("#spinner").show();
            }

            function hideLoader() {
                $("#spinner").hide();
            }
            
            $(document).ready(function(){
                $('#isletmeAdi').change(function() {
                    
                    var orgName=$('#isletmeAdi').val();
                    showLoader();
                    $.ajax({
                        type: 'GET',
                        url: '${pageContext.request.contextPath}/json/getOrgCode.html',
                        headers: { Accept: "application/json; charset=utf-8",
                            "Content-Type": "application/json; charset=utf-8"  
                        },
                        data: {orgName: orgName},
                        cache: false,
                        async: true,
                        success: function(data){
                            $('#orgCode').val(data.orgKod);
                            hideLoader();
                        },
                        complete: function(){
                            hideLoader();
                        },
                        error:function(){
                            hideLoader();
                        }
                    });
                });
            });
            
            /*
            var changePassSrc="${pageContext.request.contextPath}/sifreDegistirBIReq.html?kisiid=999979320";
            
            //window.location.href = changePassSrc;
            //window.open(changePassSrc);
            window.open(changePassSrc, "_blank", "scrollbars=1,resizable=1,height=800,width=900");
            
            
            $(document).ready(function(){ 
			jQuery.ajax({ 
				type: "GET", 
				url: '${pageContext.request.contextPath}/json/getSifreDegistirBI.html', 
				dataType:"json", 
				headers: { Accept: "application/json; charset=utf-8",
                            "Content-Type": "application/json; charset=utf-8"  
                        },
                cache: false,
                async: true,
				success:function(response){ 
					if (1==1) {
						window.location.href = changePassSrc;
                                                window.open(changePassSrc);
					}
					else {
						// Process the expected results...
					}
				}, 
				error: function(xhr, textStatus, errorThrown) { 
					alert('Error!  Status = ' + xhr.status); 
				} 

			}); 
		}); 
            */
        </script>
        
    </head>


    <body>
        <header class="labelbas">Kullanıcı Ekleme</header>


<!--font color="black" border="solid"  >   ${messageedit}</font-->

        <c:if test="${not empty messageedit}">
            <div class="center">
                <font color="black"  > <b>  ${messageedit} </b></font> 
            </div>

        </c:if>

        </br>

        <label id="yazkullanici" class="label">Kullanici Tipi: Yazilim Kullanicisi</label>
        <label id="gerkullanici" class="label">Kullanici Tipi: Gerçek Kisi</label>

        <input type="submit" id="subdeg" onclick="change()" value="Degistir" name="degis" class="myButton"/>

        </br>

        <form:form id="formadd" method="POST"  action="add.html" commandName="Kullanici" enctype="multipart/form-data">
            <input type="hidden" name="id" id="id" value="${k.id}"/>
            <input type="hidden" name="kisiid" id="kisiid" value="${k.kisiid}"/>
            <input type="hidden" name="sicilno" id="sicilno" value="${k.sicilno}"/>
            <input type="hidden" name="isletmetipiId" id="isletmetipiId" value="${k.isletmetipiId}"/>
            <input type="hidden" name="organizasyonId" id="organizasyonId" value="${k.organizasyonId}"/>
            <input type="hidden" name="personelTip" id="personelTip" value="${k.personelTip}"/>
            <input type="hidden" name="isyazilim" id="isyazilim" value="${k.isyazilim}"/>
            

            <table>
                <tr>
                    <td>
                        <div id="panel">
                            <table>



                                <tr>
                                    <td><label for="tcno" class="label"> TC Kimlik No :</label></td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="tcno" value="${k.tcno}"   id="tcno"  onkeyup="return formaddtrue()" class="textbox" style="background-color: #ffff80;height: 40px;width:200px"/>

                                        <input type="submit" id="subdol"  name="doldur"  value="Doldur" class="myButton" /></td>

                                </tr>
                                <tr>              
                                    <td><label for="ad" class="label"> Ad :</label></td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="ad" value="${k.ad}" id="ad" class="textbox" style="height: 40px;width:250px"/></td>
                                </tr> 
                                <tr>
                                    <td><label for="soyad" class="label"> Soyad :</label></td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="soyad" value="${k.soyad}" id="soyad" class="textbox" style="height: 40px;width:250px"/></td>
                                </tr> 

                                <tr>
                                    <td><label for="yerlesimkod" class="label"> Yerleşim Kodu :</label></td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="yerlesimkod" value="${k.yerlesimkod}" id="yerlesimkod" class="textbox" style="height: 40px;width:150px"/></td>
                                </tr> 
                            </table>
                        </div>
                        <table>
                            <tr>
                                <td><label for="calistigibirim" class="label"> Çalıştığı Birim :</label></td>
                                <td><input type="text" name="calistigibirim" value="${k.calistigibirim}" id="calistigibirim" class="textbox" style="height: 40px;width:550px"/></td>
                            </tr> 
                            <tr>
                                <td><label for="unvan" class="label"> Ünvanı :</label></td>
                                <td><input type="text" name="unvan" value="${k.unvan}" id="unvan" class="textbox" style="height: 40px;width:250px"/></td>
                            </tr> 
                            <tr>
                                <td><label for="tanim" class="label"> Personel Tipi :</label></td>
                                <td><input type="text" name="tanim" value="${pt.tanim}" id="tanim"  class="textbox" style="height: 40px;width:250px"/></td>
                            </tr> 
                            <tr>
                                <td><label for="orgKod" class="label"> Organizasyon Kısa Kodu :</label></td>
                                
                                <td>
                                    <form:select id="orgCode" path="orgKod" class="textbox" multiple="false" style="height:40px"> 
                                        <form:option value="NONE" label="---Organizasyon Kodu Seçiniz---"/>
                                        <c:forEach var="orgCodeItems" items="${orgCodeList}">
                                            <form:option value="${orgCodeItems.orgCode}" label="${orgCodeItems.orgCode}"/>
                                        </c:forEach>
                                    </form:select>
                                </td>
                                <%--
                                <td><input type="text" name="orgKod" value="${k.orgKod}" id="orgCode" class="textbox" style="height: 40px;width:150px"/></td>
                                --%>
                                <td>
                                    <div id="spinner" style="display: none">
                                        <img class="left" id="small_loader" src="resources/images/small_loader.gif" width="40px" height="40px"/>
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <td><label for="isletmeadi" class="label"> Organizasyon Adı :</label></td>
                                <td>
                                    <form:select id="isletmeAdi" path="isletmeadi" class="textbox" style="height:40px;width:550px" multiple="false"> 
                                        <form:option value="NONE" label="---Organizasyon Seçiniz---"/>
                                        <c:forEach var="orgNameItems" items="${orgNameList}">
                                            <form:option value="${orgNameItems.isletmeAdi}" label="${orgNameItems.isletmeAdi}"/>
                                        </c:forEach>
                                    </form:select>
                                </td>
                                <%--
                                <td><input type="text" name="isletmeadi" value="${k.isletmeadi}" id="isletmeAdi" class="textbox" style="height: 40px;width:550px"/></td>
                                --%>
                            </tr>
                            <tr>
                                <td><label for="kullaniciadi" class="label" >Kullanici Adi : </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

                                <td><input type="text" name="kullaniciadi"  value="${k.kullaniciadi}" id="kullaniciadi" class="textbox" style="background-color: #ffff80;height: 40px;width:550px"/></td>
                            </tr>
                        </table>
                    </td>

                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>

                    <td>
                        <div id="panelalt" style="position: absolute;top:130px">
                            <input type="hidden" name="form_name" id="form_form_name" value="form"/>
                            <table class="form" id="form-form"><tbody>
                                    <tr><td>
                                            <table class="fields" id="form-fields"><tbody>
                                                    <tr class="fields">
                                                        <td class="fields" align="left"><label for="form_roles" class="label">Roller</label>&#160;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                        <td align="left"> 
                                                            <table width="400" class="picklist">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Roller</th>
                                                                        <th>&#160;</th>
                                                                        <th>Seçilenler</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td width="50%">
                                                                            <select id="form_roles_unselected" style="font-size: 16;width: 200px" size="7"  multiple="multiple" class="poplistss">

                                                                                <c:forEach items="${leftList}" var="item" varStatus="count"> 
                                                                                    <option style="font-size: 16;width: 200px" value="${item.id}" >${item.roladi}</option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </td>
                                                                        <td valign="middle">
                                                                            <input id="form_roles_add" class="myButton1" type="button" value="&gt;" style="width:60px;" onclick="pickListMove(document.getElementById('form_roles_unselected'), document.getElementById('form_roles'), document.getElementById('form_roles_hidden'), true)"/><br/>
                                                                            <input id="form_roles_remove" class="myButton1" type="button" value="&lt;" style="width:60px;" onclick="pickListMove(document.getElementById('form_roles'), document.getElementById('form_roles_unselected'), document.getElementById('form_roles_hidden'), false)"/><br/>
                                                                            <input id="form_roles_add_all" class="myButton1" type="button" value="&gt;&gt;" style="width:60px;" onclick="pickListMoveAll(document.getElementById('form_roles_unselected'), document.getElementById('form_roles'), document.getElementById('form_roles_hidden'), true)"/><br/>
                                                                            <input id="form_roles_remove_all" class="myButton1" type="button" value="&lt;&lt;" style="width:60px;" onclick="pickListMoveAll(document.getElementById('form_roles'), document.getElementById('form_roles_unselected'), document.getElementById('form_roles_hidden'), false)"/><br/>
                                                                        </td>
                                                                        <td width="50%">
                                                                            <select id="form_roles" name="form_roles" size="7" style="font-size: 16;width: 200px" multiple="multiple" class="poplistss">


                                                                            </select>
                                                                            <select id="form_roles_u" name="form_roles_u" size="7" style="display: none;" style="width:100%;" multiple="multiple">
                                                                                <c:forEach items="${rightList}" var="item" varStatus="count"> 
                                                                                    <option  style="font-size: 16;width: 200px" value="${item.id}" >${item.roladi}</option>
                                                                                </c:forEach>

                                                                            </select>
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                            <select id="form_roles_hidden" name="roles" style="display: none;" multiple="multiple">
                                                                <c:forEach items="${leftList}" var="item" varStatus="count"> 
                                                                    <option value="${item.id}" >${item.roladi}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </tbody></table>
                                        </td></tr>
                                    <!--tr><td align="left">
                                             <table class="buttons" id="form-buttons"><tbody>
                                                     <tr class="buttons"><td class="buttons"><input type="submit" name="ok" id="form_ok" value=" OK "/></td><td class="buttons"><input type="submit" name="cancel" id="form_cancel" value="Cancel"/></td></tr>
                                                 </tbody></table>
                                         </td></tr-->
                                </tbody></table>
                        </div>
                        <div id="panelalt2">
                            <table>
                                <tr>
                                    <td><label for="eposta" class="label"> Eposta :</label></td>
                                    <td>&nbsp;&nbsp;<input type="text" name="eposta" value="${k.eposta}" id="eposta" size="50" class="textbox" style="height: 40px;width:250px"/></td>
                                </tr>


                                <tr>
                                    <td><form:label class="label" path="isletmetipi">İşletme Tipi :</form:label></td>
                                    <td>&nbsp;<form:radiobuttons name="isletmeradtip"  path="isletmetipi" items="${isletmetip}" /></td>
                                </tr>


                                <tr>
                                    <td><text class="label">Resim Seç :</text></td>
                                    <td><input id="fileUpload" type="file" name="fileUpload" size="35" class="myButton1"/></td>
                                    <td><input id="uploadImage" type="submit" name="uploadimage" value="Yükle..." class="myButton"  onclick="return validate_fileupload()"/></td>
                                </tr>

                            </table>
                            <div id="panelresim" style="position: absolute;top:440px">      
                                <table class="form">
                                    <tr>
                                        <td>
                                            <text class="label">Yüklenen Resim</text>
                                        </td>
                                    </tr>  
                                    <tr>
                                        <c:if test="${k.kisiid==null}" >
                                        <td></td>
                                        </c:if>
                                        <c:if test="${k.kisiid!=null}" >
                                        <td>
                                            <img src="imageprsn/KisiResimWithPersIdServlet?personId=${k.kisiid}" width="160px" height="190px" />
                                        </td>
                                        </c:if>
                                    </tr>
                                </table>   
                            </div>   
                        </div>
                    </td>
                </tr>

                <tr>
                    <td>
                        <br/>
                        <input type="submit" id="subsave" value="Kaydet" name="kaydet" onclick="return degist()" class="myButton"  />
                        
                </tr>
            </table>
            <!--input type="submit" id="subsave" value="Kaydet" name="kaydet"/-->                
            <script >
                
                if(document.getElementById('isyazilim').value=='')
                {
                                
                    document.getElementById("yazkullanici").style.display = "none";
                    document.getElementById("gerkullanici").style.display = "";
                    document.getElementById("panel").style.display = "";
                    
                    document.getElementById("orgCode").setAttribute("disabled", "disabled");
                    document.getElementById("isletmeAdi").setAttribute("disabled", "disabled");
                    document.getElementById("tcno").setAttribute("required", "required");
                    // document.getElementById("panelalt").style.display = "";
                                
                }
                else
                {
                                   
                    document.getElementById("yazkullanici").style.display = "";
                    document.getElementById("gerkullanici").style.display = "none";
                    document.getElementById("panel").style.display = "none";
                    
                    document.getElementById("tcno").removeAttribute("required");
                    document.getElementById("orgCode").removeAttribute("disabled");
                    document.getElementById("isletmeAdi").removeAttribute("disabled");
                    //  document.getElementById("tcno").setAttribute("required", "");
                
                
                
                    //  document.getElementById("panelalt").style.display = "none";
                                
                                  
                }
            
            
                //    document.getElementById("isletmeradtip").required=true;
       
    
             
    
                
                var tcno = $("#tcno").val();
                
                if(!check_tcno(tcno))
                {
                    document.getElementById("panelalt").style.display = "none";
                    document.getElementById("panelalt2").style.display= "none";
                    document.getElementById("panelresim").style.display= "none";
                    // document.getElementById("isletmetip").removeAttribute("required");   
                }
                else
                {
                    document.getElementById("panelalt").style.display = "";
                    document.getElementById("panelalt2").style.display = "";
                    document.getElementById("panelresim").style.display = "";
                    
                    //  document.getElementById("isletmetip").setAttribute("required", "required"); 
                }
                
                
                
                ////////////////////////////  FUNCTIONS //////////////////////////
                
                function degist ()
                {
                    document.getElementById("orgCode").removeAttribute("disabled");
                    document.getElementById("isletmeAdi").removeAttribute("disabled");
                    if(document.getElementById("gerkullanici").style.display =="")
                    {
                        var tcno = $("#tcno").val();
                        var email = $("#eposta").val();
                        if(document.getElementById("panelalt").style.display =="none")
                        {
                     
                            if (!check_tcno(tcno)) {
                                alert("Geçersiz Tc No!");
                                document.getElementById("formadd").setAttribute("onSubmit", "return false;");
                            }
                            else
                            {
                                document.getElementById("formadd").setAttribute("onSubmit", "return true;");
                            }
                     
                         
                        }
                        else
                        {
                       
                            if (!check_tcno(tcno)) {
                                alert("Geçersiz Tc No!");
                                document.getElementById("formadd").setAttribute("onSubmit", "return false;");
                            }
                            else if(!validateEmail(email))
                            {
                                alert("Email formatı Yanlış!");
                                document.getElementById("formadd").setAttribute("onSubmit", "return false;"); 
                            }
                            else
                            {
                                document.getElementById("formadd").setAttribute("onSubmit", "return true;");
                            }
                                 
                        }
                    }   
                    else
                    {
                        document.getElementById("formadd").setAttribute("onSubmit", "return true;");
                    }
                    
                }
                function formaddtrue ()
                {
                    
                    document.getElementById("formadd").setAttribute("onSubmit", "return true;");
                        
                    
                }
                
             
                function change() {
                        
                    if(document.getElementById('isyazilim').value=='')
                    {
                        document.getElementById('isyazilim').value=true;
                        document.getElementById("yazkullanici").style.display = "";
                        document.getElementById("gerkullanici").style.display = "none";
                        document.getElementById("panel").style.display = "none";
                        document.getElementById("panelalt").style.display = "";
                        document.getElementById("panelalt2").style.display = "none";
                        document.getElementById("panelresim").style.display = "none";
                        document.getElementById("formadd").setAttribute("onSubmit", "return true;"); 
                    
                        document.getElementById("tcno").removeAttribute("required");
                        document.getElementById("orgCode").removeAttribute("disabled");
                        document.getElementById("isletmeAdi").removeAttribute("disabled");
                        //  document.getElementById("isletmetip").removeAttribute("required");    
                                
                    }
                    else
                    {
                                   
                                  
                        document.getElementById('isyazilim').value='';
                        document.getElementById("yazkullanici").style.display = "none";
                        document.getElementById("gerkullanici").style.display = "";
                        document.getElementById("panel").style.display = "";
                        // document.getElementById("panelalt").style.display = "";
                    
                        document.getElementById("tcno").setAttribute("required", "required");
                        document.getElementById("orgCode").setAttribute("disabled", "disabled");
                        document.getElementById("isletmeAdi").setAttribute("disabled", "disabled");
                    
                        //  document.getElementById("isletmetip").setAttribute("required", "required");  
                        
                        var tcno = $("#tcno").val();
                
                        if(!check_tcno(tcno))
                        {
                            document.getElementById("panelalt").style.display = "none";
                            document.getElementById("panelalt2").style.display = "none";
                            document.getElementById("panelresim").style.display = "none";
                        }
                        else
                        {
                            document.getElementById("panelalt").style.display = ""; 
                            document.getElementById("panelalt2").style.display = "";
                            document.getElementById("panelresim").style.display = "";
                        }
                                  
                    }
                            
                            
                        
                    
                }

                function demoVisibility() {
                    document.getElementById("myP1").style.display= "";
                }
                
                function resimGoster(){
                    document.getElementById("panelresim").style.display= "";
                    return true;
                }
                
                function uploadCheck ()
                {
                    var file = $("#fileUpload").val();
                    if(file==""){
                        alert("Yüklenecek resim seçiniz!");
                        document.getElementById("fileUpload").setAttribute("onSubmit", "return false;");
                        document.getElementById("fileUpload").focus();
                    }
                    else{
                        document.getElementById("fileUpload").setAttribute("onSubmit", "return true;");
                    }
                }
                
                function validate_fileupload()
                {
                    var fileName = $("#fileUpload").val();
                    var allowed_extensions = new Array("jpg","png","gif","JPG","PNG","GIF","jpeg","JPEG");
                    var file_extension = fileName.split('.').pop(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

                    for(var i = 0; i <= allowed_extensions.length; i++)
                    {
                        if(allowed_extensions[i]==file_extension)
                        {
                            return true; // valid file extension
                        }
                    }

                    alert("Yüklenecek resim seçiniz! Resim formatı jpg, png, gif, jpeg olmalıdır...");
                    document.getElementById("fileUpload").focus();
                    return false;
                }
            </script>



        </form:form>

    </body>
</html>
