

function convert(array){
    var map = {};
    for(var i = 0; i < array.length; i++){
        var obj = array[i];
        obj.children= [];
        
        map[obj.PERSONEL_ID] = obj;
        
        var parent = obj.PARENT_PERSONAL_ID || '-';
        if(!map[parent]){
            map[parent] = {
                children: []
            };
        }
        obj.Id=obj.PERSONEL_ID;
        delete obj.PERSONEL_ID;
        obj.name=obj.PERSONAL_NAME;
        delete obj.PERSONAL_NAME;
        obj.Parent=obj.PARENT_PERSONAL_ID;
        delete obj.PARENT_PERSONAL_ID;
        obj.title=obj.TITLE;
        delete obj.TITLE;
        obj.desc="TCKN: "+obj.TCKN+" Baslangic Tarihi: "+obj.BAS_TARIH+" Hizmet Yili: "+obj.HIZMEY_YILI;
        delete obj.TCKN;
        delete obj.BAS_TARIH;
        delete obj.HIZMEY_YILI;
        delete obj.IMAGE;
        map[parent].children.push(obj);
    }
    
    return map['-'].children;
    
}

function convertToHierarchy(arry) {
    var nodeObjects = createStructure(arry);
    var head=findhead(nodeObjects);
    var j =1;
		
    for (var i = nodeObjects.length - 1; i >= 0; i--) {
        var currentNode = nodeObjects[i];
        if (currentNode.text.Parent === "") {
            continue;
        }
        var parent = getParent(currentNode, nodeObjects);
        if (parent === null) {
            currentNode.HTMLclass='head';
            if (currentNode.text.HIERARCY_ID !== "1"){
                nodeObjects.splice(i, 1);
                continue;
            }
            continue;
        }
			
        var head1=currentNode.text.Parent;
        if (head1 == head){
            currentNode.HTMLclass = 'colour'+j; 
            j++;
            parent.stackChildren = "true";
            parent.children.push(currentNode);
            nodeObjects.splice(i, 1);
				
            continue;
        }

        currentNode.HTMLclass= parent.HTMLclass;
        parent.stackChildren = "true";
        parent.children.push(currentNode);
        nodeObjects.splice(i, 1);
    }
    nodeObjects = colorothers (nodeObjects);
    return nodeObjects;
}
function Iterate(data,htmlclass){
    jQuery.each(data, function (key, value) {
        if (key == 'HTMLclass') {
            data[key] = htmlclass;
            
        }
        if ($.isPlainObject(value) || $.isArray(value)) {
            Iterate(value,htmlclass);
        }
    });
}
function colorothers (nodes) {

    for (var i = 0; i < nodes.length; i++) {
        node=nodes[i];
        for(var j= 0; j< node.children.length; j++){
            nod=node.children[j];
            for(var k=0 ; k< nod.children.length; k++){
                no=nod.children[k]
                Iterate(no,nod.HTMLclass);
            //for(var key in no){
            //if(key =="HTMLclass"){
            //						}
            //}
            //no[key]=nod.HTMLclass;
						
					
            }
        }
			
    }
    return nodes;
}
	
function createStructure(nodes) {
    var objects = [];
    for (var i = 0; i < nodes.length; i++) {
			
			
        nodes[i].Id=nodes[i].PERSONEL_ID;
        delete nodes[i].PERSONEL_ID;
        nodes[i].name=nodes[i].PERSONAL_NAME;
        delete nodes[i].PERSONAL_NAME;
        nodes[i].Parent=nodes[i].PARENT_PERSONAL_ID;
        delete nodes[i].PARENT_PERSONAL_ID;
        nodes[i].title=nodes[i].TITLE;
        delete nodes[i].TITLE;
        var datetext=nodes[i].BAS_TARIH;
        if(typeof datetext === 'undefined'){
            date="Yo";
            nodes[i].desc="TCKN: "+nodes[i].TCKN+" Hizmet Yili: "+nodes[i].HIZMEY_YILI;;
        }
        else{
            var date = new Date(nodes[i].BAS_TARIH);
            var day = date.getDate();
            var monthIndex = date.getMonth();
            var year = date.getFullYear();
            date=day + '/' + monthIndex + '/' + year;
            nodes[i].desc="TCKN: "+nodes[i].TCKN+" Baslangic Tarihi: "+date+" Hizmet Yili: "+nodes[i].HIZMEY_YILI;
        }
			
        delete nodes[i].TCKN;
        delete nodes[i].BAS_TARIH;
        delete nodes[i].HIZMEY_YILI;
        objects.items
        objects.push({
            text: nodes[i], 
            stackChildren: "",
            HTMLclass : "", 
            image:"http://10.0.0.34:7001/BiLogin/imageprsn/KisiResimWithPersIdServlet?personId="+nodes[i].IMAGE, 
            children: []
        });
        delete nodes[i].IMAGE;
    }
    return objects;

}
function getParent(child, nodes) {
    var parent = null;

    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].text.Id === child.text.Parent) {
            return nodes[i];
        }
    }

    return parent;
}
    
function findhead (nodd){
    var head = null;
    for (var i = 0; i < nodd.length; i++) {
        if(nodd[i].text.HIERARCY_ID === "1"){
            head = nodd[i].text.Id
            return head;
        }
    }
}	  

function getParent(child, nodes) {
    var parent = null;

    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].text.Id === child.text.Parent) {
            return nodes[i];
        }
    }

    return parent;
}


function checkEmptyObj(data) {
    $.each(data, function(key, value) {
        if ($.isPlainObject(value) || $.isArray(value)) {
            checkEmptyObj(value);
        }
        //alert(key+":"+$.isEmptyObject(value));
        if (value === "" || value === null || $.isEmptyObject(value)) {
            delete data[key];
        }
    });
}