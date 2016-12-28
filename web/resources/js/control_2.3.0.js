if(typeof Click=='undefined')
    Click={};
        
if(typeof Click.domready=='undefined'){
    Click.domready={
        events:[],
        ready:false,
        run:function(){
            if(!document.body){
                return setTimeout(arguments.callee,13);
            }
            Click.domready.ready=true;
            var e;
            while(e=Click.domready.events.shift()){
                e();
            }
        }
    }
};
(function(){
    if(document.addEventListener){
        document.addEventListener("DOMContentLoaded",function(){
            document.removeEventListener("DOMContentLoaded",arguments.callee,false);
            Click.domready.run();
        },false);
        window.addEventListener("load",Click.domready.run,false);
    }else if(document.attachEvent){
        document.attachEvent("onreadystatechange",function(){
            if(document.readyState==="complete"){
                document.detachEvent("onreadystatechange",arguments.callee);
                Click.domready.run();
            }
        });
    window.attachEvent("onload",Click.domready.run);
    var toplevel=false;
    try{
        toplevel=window.frameElement==null;
    }catch(e){}
    if(document.documentElement.doScroll&&toplevel){
        (function(){
            try{
                document.documentElement.doScroll('left');
            }catch(e){
                setTimeout(arguments.callee,1);
                return;
            }
            Click.domready.run();
        })();
    }
}
})();
Click.addLoadEvent=function(func){
    if(Click.domready.ready){
        return func();
    }
    Click.domready.events.push(func);
};

addLoadEvent=Click.addLoadEvent;
function doubleFilter(event){
    var keyCode;
    if(document.all){
        keyCode=event.keyCode;
    }else if(document.getElementById){
        keyCode=event.which;
    }else if(document.layers){
        keyCode=event.which;
    }
    if(keyCode>=33&&keyCode<=43){
        return false;
    }else if(keyCode==47){
        return false;
    }else if(keyCode>=58&&keyCode<=126){
        return false;
    }else{
        return true;
    }
}
function integerFilter(event){
    var keyCode;
    if(document.all){
        keyCode=event.keyCode;
    }else if(document.getElementById){
        keyCode=event.which;
    }else if(document.layers){
        keyCode=event.which;
    }
    if(keyCode>=33&&keyCode<=44){
        return false;
    }else if(keyCode>=46&&keyCode<=47){
        return false;
    }else if(keyCode>=58&&keyCode<=126){
        return false;
    }else{
        return true;
    }
}
function noLetterFilter(event){
    var keyCode;
    if(document.all){
        keyCode=event.keyCode;
    }else if(document.getElementById){
        keyCode=event.which;
    }else if(document.layers){
        keyCode=event.which;
    }
    if(keyCode>=33&&keyCode<=39){
        return false;
    }else if(keyCode==47){
        return false;
    }else if(keyCode>=58&&keyCode<=126){
        return false;
    }else{
        return true;
    }
}
function setFocus(id){
    var field=document.getElementById(id);
    if(field&&field.focus&&field.type!="hidden"&&field.disabled!=true){
        try{
            field.focus();
        }catch(err){}
    }
}
function trim(str){
    while(str.charAt(0)==(" ")){
        str=str.substring(1);
    }
    while(str.charAt(str.length- 1)==" "){
        str=str.substring(0,str.length-1);
    }
    return str;
}
Click.hasClass=function(element,cls){
    var className=element.className;
    if(className){
        return new RegExp('\\b'+cls+'\\b').test(className);
    }
    return false;
}
Click.addClass=function(element,cls){
    if(!Click.hasClass(element,cls)){
        element.className+=element.className?' '+ cls:cls;
    }
}
Click.removeClass=function(element,cls){
    var className=element.className;
    if(!className)return;
    if(className.indexOf(' ')<0){
        element.className='';
        return;
    }
    var rep=new RegExp('(^|\\s)'+ cls+'(?:\\s|$)');
    element.className=className.replace(rep,'$1');
}
Click.setFieldValidClass=function(field){
    Click.removeClass(field,'error');
}
Click.setFieldErrorClass=function(field){
    Click.addClass(field,'error');
}
function validateTextField(id,required,minLength,maxLength,msgs){
    var field=document.getElementById(id);
    if(field){
        var value=trim(field.value);
        if(required){
            if(value.length==0){
                Click.setFieldErrorClass(field);
                return msgs[0];
            }
        }
    if(required&&minLength>0){
        if(value.length<minLength){
            Click.setFieldErrorClass(field);
            return msgs[1];
        }
    }
if(maxLength>0){
    if(value.length>maxLength){
        Click.setFieldErrorClass(field);
        return msgs[2];
    }
}
Click.setFieldValidClass(field);
return null;
}else{
    return'Field '+ id+' not found.';
}
}
function validateCheckbox(id,required,msgs){
    var field=document.getElementById(id);
    if(field){
        if(required){
            if(field.checked){
                return null;
            }else{
                return msgs[0];
            }
        }
    }else{
    return'Field '+ id+' not found.';
}
}
function validateSelect(id,defaultValue,required,msgs){
    var field=document.getElementById(id);
    if(field){
        if(required){
            var value=field.value;
            if(value!=defaultValue){
                Click.setFieldValidClass(field);
                return null;
            }else{
                Click.setFieldErrorClass(field);
                return msgs[0];
            }
        }
    }else{
    return'Field '+ id+' not found.';
}
}
function validateRadioGroup(radioName,formId,required,msgs){
    if(required){
        var form=document.getElementById(formId);
        if(form){
            var path=form[radioName];
            for(i=0;i<path.length;i++){
                if(path[i].checked){
                    return null;
                }
            }
        return msgs[0];
    }
}
}
function validateFileField(id,required,msgs){
    var field=document.getElementById(id);
    if(field){
        var value=trim(field.value);
        if(required){
            if(value.length==0){
                Click.setFieldErrorClass(field);
                return msgs[0];
            }
        }
    }else{
    return'Field '+ id+' not found.';
}
}
function validateForm(msgs,id,align,style){
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
            errorsHtml+='<a class="error" href="javascript:setFocus(\'';
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
Click.submit=function(form){
    if(typeof form=='undefined'){
        alert('Error: form is undefined. Usage: Click.submit(form)');
        return false;
    }
    if(form){
        var formElements=form.elements;
        for(var i=0;i<formElements.length;i++){
            var el=formElements[i];
            if(el.name=='submit'){
                alert('Error: In order to submit the Form through JavaScript, buttons and fields must not be named "submit". Please rename the button/field called "submit".');
                return false;
            }
        }
        }
form.submit();
return true;
}