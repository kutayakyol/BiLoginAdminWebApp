	$("#spinner").toggle();
	// GET ORG NAME
var orgname = "";
$(document).ready(function(){
	$("#namebutton").click(function(event){
		event.preventDefault();
		$.when($.ajax({
			type:"GET", 
			dataType: 'json',
			url: "http://10.0.0.36:7001/BiLogin/rest/getOrgName/"+$("#p_org_tip option:selected" ).val(), 
			success: function(data) {
				   //localStorage.setItem('data4', JSON.stringify(data))
				   orgname=data;
			return data;
				}, 
			error: function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				}
		}).done(function(a2){
			$("#p_org_name").empty();
			$(orgname).each(function(index){
				$("#p_org_name").append('<option value="'+ this.ORG_ID + '">' + this.ORG_NAME + '</option>');
			});
		})
		);

	})
});


// GET ORG TYPE

var orgtype = "";
$(document).ready(function(){
	$.when($.ajax({
		type:"GET", 
		dataType: 'json',
		url: "http://iszekasi.euas.gov.tr:7001/BiLogin/rest/getOrgType", 
		success: function(data) {
			   //localStorage.setItem('data4', JSON.stringify(data))
			   orgtype=data;
		return data;
			}, 
		error: function(jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			}
	}).done(function(a2){
	$("#p_org_tip").empty();
			$(orgtype).each(function(index){
				$("#p_org_tip").append('<option value="'+ this.ORG_TYPE_ID + '">' + this.ORG_TYPE_NAME + '</option>');
			});
	})
	);
});

  function frame() {
    if (width >= 100) {
      clearInterval(id);
    } else {
      width++;
      elem.style.width = width + '%';
    }
  }

// GET CHART DATA
var chart_type = "";
$(document).ready(function(){
	$("#buttonsub").click(function(event){
			$("#spinner").toggle();
		$("#OrganiseChart-big-commpany").empty();
		  
		  
		event.preventDefault();
		$.when($.ajax({
			type:"GET", 
			dataType: 'json',
			url: "http://10.0.0.36:7001/BiLogin/rest/getOrgChart/"+$("#p_org_tip option:selected" ).val()+"/"+$("#p_org_name option:selected" ).val(), 
			success: function(data) {
				   //localStorage.setItem('data4', JSON.stringify(data))
				   chart_type=JSON.stringify(data);
			return data;
				}, 
			error: function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				}
		}).done(function(a1){
		// chart settings
		var chart_config = {
			"chart": {
				"container": "#OrganiseChart-big-commpany",
				levelSeparation:    30,
				siblingSeparation:  4,
				subTeeSeparation:  80,
				rootOrientation: "NORTH",
				nodeAlign: "BOTTOM",
				"connectors": {
					"type": "curve"
				},
				"node": {
					"HTMLclass": "nodeExample1"
				}
			}
			};
		// Convert PL/SQL data to hierarchic structure
		chart_type= convertToHierarchy(JSON.parse(chart_type));
		chart_type = chart_type.map(function(e){
		  return JSON.stringify(e);
		});
		chart_type = chart_type.join(",");
		chart_type=JSON.parse(chart_type);
		var nodeStructure = [];
		chart_config.nodeStructure = chart_type;
		checkEmptyObj (chart_config);
		//print chart
		 $.when(new Treant(chart_config)).then($("#spinner").toggle());
		// fix image src
		var domain_name=""
			$(function(){
			   $('#OrganiseChart-big-commpany').find('img').each(function(){
				   var srcpath = $(this).attr('src');
				   if(srcpath.length > 85){
				   srcpath = srcpath.replace(srcpath.substr(srcpath.length - 14),domain_name);
				   $(this).attr('src',srcpath);
				   }
			   });
			});


		})
		)

	})
			   
});