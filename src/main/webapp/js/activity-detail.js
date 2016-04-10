/**
 * Created by huihui.
 */

var activityId;
var userId;
var teamId;
$(document).on("pageshow", function() {
	var raw = window.location.search;
    var queryString = raw.substr(1);
    var query = parseQueryString(queryString);
    activityId = findParameterValue("id");
    userId = findParameterValue("userId");
    
	attendedChange();
	carInfoChange();
	initActivityInfo(activityId);
	initTeamList();
	initAction();
});

function attendActivity() {
	var seatsleave = ($("#hascar").val() == "yes" && $("#attended").val() == "yes")? $("#seatsleave").val():0;
	var data = {
		activityId: activityId,
//		userId: userId,
		userId: 0,
		attended: $("#attended").val() == "no"?false:true,
		seatsleave: seatsleave
	}
	$.ajax({
        type: "POST",
        dataType: "json",
        data: data,
        url:"service/ActivityAttender/updateActivityAttender",
        success: function(result) {
        	alert(result);
        	windows.location = "activity.html";
        },
        complete: function() {
        }
    });
}


function initActivityInfo(activityId) {
	$.ajax({
        type: "POST",
        data: {id:activityId},
        dataType: "json",
        url:"service/Activity/getActivityByActivityId",
        success: function(activity) {
        	initActivityStatus(activity.status);
        	
            $("#activity-name").val(activity.name);
            $("#activity-description").val(activity.description);
            $("#start-time").val(activity.startTime.substr(0,10));
            
            var teambody = $("#team-members tbody");
            var members = activity.team.members;
            teambody.empty();
            for(var i=0; i<members.length; i++){
            	var tr = "<tr>" +
            			"<th>" + i + "</th>" +
            			"<td><a href='' data-rel='external'>" + members[i].name + "</a></td>" +
            			"<td>" + members[i].phone + "</td>" +
            			"</tr>";
            	teambody.append(tr);
            }
        },
        complete: function() {
            $("#teamList").listview("refresh");
        }
    });	
}

function initActivityStatus(status) {
	switch(status) {
	// activity draft
	case 0:
		$("#activity-begin").hide();
		$("#activity-end").hide();
		$("#car-arrangement").hide();
		break;
	// activity todo
	case 1:
		$("#activity-begin").show();
		$("#activity-end").hide();
		$("#car-arrangement").hide();
		break;
	// activity doing
	case 2:
		$("#activity-begin").hide();
		$("#activity-end").hide();
		$("#car-arrangement").show();
		break;
	// activity done
	case 3:
		$("#activity-begin").hide();
		$("#activity-end").show();
		$("#car-arrangement").show();
		break;
	// activity overtime
	case 4:
		$("#activity-begin").hide();
		$("#activity-end").hide();
		$("#car-arrangement").show();
		break;	
	}
	
}

function initTeamList() {
	$.ajax({
        type: "POST",
        dataType: "json",
        url:"service/Team/getAllTeams",
        success: function(result) {
            var select = $("#teamList");
            select.empty();
            $("<option selected disabled>请选择组</option>").appendTo(select);
            for(var i = 0; i < result.length; i++) {
            	select.append(
                     "<option value='" + result[i].id + "'>" + result[i].name + "</option>"
                );
            }
        },
        complete: function() {
            $("#teamList").listview("refresh");
        }
    });	
}

function carInfoChange() {
	$("#hascar").on("change", function(){
		if($(this).val()=="yes") {
			$("#comfirmseatno").show();
		}else{
			$("#comfirmseatno").hide();
		}
	})
}

function attendedChange() {
	$("#attended").on("change", function(){
		if($(this).val()=="yes") {
			$("#carInfo").show();
		}else{
			$("#carInfo").hide();
			$("#seatsleave").val(0);
		}
	})
}

function initAction() {
	$("#refund").on("click", function() {
		var data = {
			activityId:	activityId,
			memberId: userId,
			teamId:teamId,
			exchange:$("#exchange").val(),
			exchangeStatus:$("#exchangeStatus").val() ,
			date: new Date()		
		};
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        data: data,
	        url:"service/ExchangeDetail/insertExchangeDetail",
	        success: function(result) {
	        	alert(result);
	        },
	        complete: function() {
	        }
	    });
	});
}

function findParameterValue(parameterKey) {
	var raw = window.location.search;
    var queryString = raw.substr(1);
    var queryParameterMap = parseQueryString(queryString);
    return queryParameterMap[parameterKey];
}

function parseQueryString(queryString) {
    var params = queryString.split("&");
    var temp, query = {};
    for(var i = 0, l = params.length; i < l; i++) {
        temp = params[i].split("=");
        query[temp[0]] = temp[1];
    }
    return query;
}