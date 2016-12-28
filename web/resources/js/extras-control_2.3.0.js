if(typeof Click=='undefined')
    Click={};

function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}
function check_tcno(a){
  if(a.substr(0,1)==0 || a.length!=11){
    return false;
  }
  var i = 9, md='', mc='', digit, mr='';
  while(digit = a.charAt(--i)){
    i%2==0 ? md += digit : mc += digit;
  }
  if(((eval(md.split('').join('+'))*7)-eval(mc.split('').join('+')))%10!=parseInt(a.substr(9,1),10)){
    return false;
  }
  for (c=0;c<=9;c++){
    mr += a.charAt(c);
  }
  if(eval(mr.split('').join('+'))%10!=parseInt(a.substr(10,1),10)){
    return false;
  }
  return true;
}
        
function validateCreditCardField(id,typeId,required,minLength,maxLength,msgs){
    var msg=validateTextField(id,required,minLength,maxLength,msgs);
    if(msg){
        return msg;
    }else{
        var field=document.getElementById(id);
        var value=field.value;
        var type=document.getElementById(typeId).value;
        if(value.length>0){
            var buffer='';
            for(var i=0;i<value.length;i++){
                var c=value.charAt(i);
                if(c!='='&&c!=' '){
                    buffer=buffer+ c;
                }
            }
            value=buffer;
            var length=value.length;
            if(length<13){
                Click.setFieldErrorClass(field);
                return msgs[3];
            }
            var firstdig=value.charAt(0);
            var seconddig=value.charAt(1);
            var isValid=false;
            if(type=='VISA'){
                isValid=((length==16)||(length==13))&&(firstdig=='4');
            }
            if(type=='MASTER'){
                isValid=(length==16)&&(firstdig=='5')&&("12345".indexOf(seconddig)>=0);
            }
            if(type=='AMEX'){
                isValid=(length==15)&&(firstdig=='3')&&("47".indexOf(seconddig)>=0);
            }
            if(type=='DINERS'){
                isValid=(length==14)&&(firstdig=='3')&&("068".indexOf(seconddig)>=0);
            }
            if(type=='DISCOVER'){
                isValid=(length==16)&&value.startsWith("6011");
            }
            if(!isValid){
                Click.setFieldErrorClass(field);
                return msgs[3];
            }
        }
        Click.setFieldValidClass(field);
        return null;
    }
}
function validateEmailField(id,required,minLength,maxLength,msgs){
    var msg=validateTextField(id,required,minLength,maxLength,msgs);
    if(msg){
        return msg;
    }else{
        var field=document.getElementById(id);
        var value=field.value;
        var length=value.length;
        if(length>0){
            var index=value.indexOf("@");
            if(index<1||index==length- 1){
                Click.setFieldErrorClass(field);
                return msgs[3];
            }
            if(!isLetterOrDigit(value.charAt(0))){
                Click.setFieldErrorClass(field);
                return msgs[3];
            }
            if(!isLetterOrDigit(value.charAt(length- 1))){
                Click.setFieldErrorClass(field);
                return msgs[3];
            }
        }
    }
    Click.setFieldValidClass(field);
    return null;
}
function validateNumberField(id,required,minValue,maxValue,msgs){
    var field=document.getElementById(id);
    if(field){
        var value=field.value;
        if(value.length==0){
            if(required){
                Click.setFieldErrorClass(field);
                return msgs[0];
            }
        }else{
            if(value>maxValue){
                Click.setFieldErrorClass(field);
                return msgs[2];
            }else if(value<minValue){
                Click.setFieldErrorClass(field);
                return msgs[1];
            }
        }
        Click.setFieldValidClass(field);
        return null;
    }else{
        return'Field '+ id+' not found.';
    }
}
function validatePickList(id,required,msgs){
    var field=document.getElementById(id);
    if(field){
        if(field.options.length==0){
            if(required){
                Click.setFieldErrorClass(field);
                return msgs[0];
            }
        }
        Click.setFieldValidClass(field);
        return null;
    }else{
        return'Field '+ id+' not found.';
    }
}
function validateRegexField(id,required,minLength,maxLength,regex,msgs){
    var msg=validateTextField(id,required,minLength,maxLength,msgs);
    if(msg){
        return msg;
    }else{
        var field=document.getElementById(id);
        if(field.value.length==0||field.value.match(new RegExp(regex))){
            Click.setFieldValidClass(field);
            return null;
        }else{
            Click.setFieldErrorClass(field);
            return msgs[3];
        }
    }
    return null;
}
function initMenu(){
    if(document.all&&document.getElementById){
        var navRoot=document.getElementById("dmenu");
        if(navRoot){
            for(i=0;i<navRoot.childNodes.length;i++){
                var node=navRoot.childNodes[i];
                if(node.nodeName=="LI"){
                    node.onmouseover=function(){
                        this.className+=" over";
                    }
                    node.onmouseout=function(){
                        this.className=this.className.replace(" over","");
                    }
                }
            }
        }
    }
}
function isLetter(c){
    return(((c>="a")&&(c<="z"))||((c>="A")&&(c<="Z")))
}
function isDigit(c){
    return((c>="0")&&(c<="9"))
}
function isLetterOrDigit(c){
    return(isLetter(c)||isDigit(c))
}
function pickListMove(from,to,hidden,isSelected){
    var values=new Object();
    for(var i=0;i<from.options.length;i++){
        if(from.options[i].selected){
            values[from.options[i].value]=true;
        }
    }
    pickListMoveItem(from,to,values,hidden,isSelected);
}
function pickListMoveAll(from,to,hidden,isSelected){
    var values=new Object();
    for(i=0;i<from.options.length;i++){
        values[from.options[i].value]=true;
    }
    pickListMoveItem(from,to,values,hidden,isSelected);
}
function pickliststatu(from,database,to,hidden,isSelected)
{    var values=new Object();
    for(var i=0;i<from.options.length;i++){
        for(var y=0;y<database.options.length;y++)
        {
            if(from.options[i].value==database.options[y].value)
            {
                values[from.options[i].value]=true;
            }
        }
     
    }
    pickListMoveItem(from,to,values,hidden,isSelected);
}
function pickListMoveItem(from,to,values,hidden,isSelected){
    for(var i=0;i<hidden.options.length;i++){
        if(values[hidden.options[i].value]){
            hidden.options[i].selected=isSelected;
        }
    }
    for(var i=0;i<from.options.length;i++){
        if(values[from.options[i].value]){
            from.options[i]=null;
            i--;
        }
    }
    var toIndex=0;
    for(var i=0;i<hidden.options.length;i++){
        if(hidden.options[i].selected==isSelected){
            to.options[toIndex]=new Option(hidden.options[i].text,hidden.options[i].value);
            toIndex++;
        }
    }
}
Click.submitLinkAction=function(link,formId){
    var params=Click.getUrlParams(link.href);
    if(params==null){
        return false;
    }
    var form=document.getElementById(formId);
    if(form==null){
        return false;
    }
    for(var i=0;i<params.length;i++){
        var param=params[i];
        var input=document.createElement("input");
        input.setAttribute("type","hidden");
        input.setAttribute("name",param.name);
        input.setAttribute("value",param.value);
        form.appendChild(input);
    }
    form.submit();
    return false;
}
Click.getUrlParams=function(url){
    if(url==null||url==''||url=='undefined'){
        return null;
    }
    url=unescape(url);
    var start=url.indexOf('?')
    if(start==-1){
        return null;
    }
    url=url.substring(start+ 1);
    var pairs=url.split("&");
    var params=new Array();
    for(var i=0;i<pairs.length;i++){
        var param=new Object();
        var pos=pairs[i].indexOf('=');
        if(pos>=0){
            param.name=pairs[i].substring(0,pos);
            param.value=pairs[i].substring(pos+1);
            params.push(param);
        }
    }
    return params;
}
Click.getTabSheetNumber=function(id){
    var node=document.getElementById(id);
    if(!node)return 1;
    var parent=node.parentNode;
    while(parent&&!Click.isTabSheet(parent)){
        parent=parent.parentNode;
    }
    if(parent)return parent.getAttribute("id").substr(10);
    return 1;
}
Click.isTabSheet=function(node){
    if(!node)return false;
    var id=node.getAttribute('id');
    if(id){
        if(id.indexOf('tab-sheet-')>=0)return true;
    }
    return false;
}
Click.validateTabbedForm=function(msgs,id,align,style){
    var errorsHtml='';
    var focusFieldId=null;
    for(i=0;i<msgs.length;i++){
        var value=msgs[i];
        if(value!=null){
            var index=value.lastIndexOf('|');
            var fieldMsg=value.substring(0,index);
            var fieldId=value.substring(index+ 1);
            if(focusFieldId==null){
                focusFieldId=fieldId;
            }
            errorsHtml+='<tr class="errors"><td class="errors" align="';
            errorsHtml+=align;
            if(style!=null){
                errorsHtml+='" style="';
                errorsHtml+=style;
            }
            errorsHtml+='">';
            errorsHtml+='<a class="error" href="javascript:onShowTab(Click.getTabSheetNumber(\''
            + fieldId+'\'));setFocus(\'';
            errorsHtml+=fieldId;
            errorsHtml+='\');">';
            errorsHtml+=fieldMsg;
            errorsHtml+='</a>';
            errorsHtml+='</td></tr>';
        }
    }
    if(errorsHtml.length>0){
        errorsHtml='<table class="errors">'+ errorsHtml+'</table>';
        document.getElementById(id+'-errorsDiv').innerHTML=errorsHtml;
        document.getElementById(id+'-errorsTr').style.display='inline';
        setFocus(focusFieldId);
        return false;
    }else{
        return true;
    }
}