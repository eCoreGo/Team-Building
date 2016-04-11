/**
 * Created by huihui.
 */

var activityId;
var userId;
var teamId;
$(document).on("pageshow", function() {
    activityId = findParameterValue("id");
    userId = findParameterValue("userId");
    addUserItoLink();
    
	attendedChange();
	carInfoChange();
	initActivityInfo();
	initTeamList();
	computeCostAction();
});

function attendActivity() {
	var seatsleave = ($("#hascar").val() == "yes" && $("#attended").val() == "yes")? $("#seatsleave").val():0;
	var data = {
		activityId: activityId,
		userId: userId,
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
        	windows.location = "activity.html?userId=" + userId;
        },
        complete: function() {
        }
    });
}


function initActivityInfo() {
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
            teamId= activity.team.id;
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
		$("#pushActivity").show();
		$("#invokeArrangeCar").hide();
		$("#endActivity").hide();
		$("#activity-begin").hide();
		$("#activity-end").hide();
		$("#car-arrangement").hide();
		break;
	// activity todo
	case 1:
		$("#pushActivity").hide();
		$("#invokeArrangeCar").show();
		$("#endActivity").hide();
		$("#activity-begin").show();
		$("#activity-end").hide();
		$("#car-arrangement").hide();
		break;
	// activity doing
	case 2:
		if(userId=="admin") {
			$("#endActivity").show();
		}
		$("#pushActivity").hide();
		$("#invokeArrangeCar").hide();
		$("#activity-begin").hide();
		$("#activity-end").hide();
		$("#car-arrangement").show();
		break;
	// activity done
	case 3:
		$("#pushActivity").hide();
		$("#invokeArrangeCar").hide();
		$("#endActivity").hide();
		$("#activity-begin").hide();
		$("#activity-end").show();
		$("#car-arrangement").show();
		break;
	// activity overtime
	case 4:
		$("#pushActivity").hide();
		$("#invokeArrangeCar").hide();
		$("#endActivity").hide();
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

function pushActivity() {
	var data = {
			activityId: activityId,
			teamId:teamId
	}
	
	$.ajax({
        type: "POST",
        dataType: "json",
        data: data,
        url:"service/ActivityAttender/initActivityAttender",
        success: function(result) {
        	windows.location = "activity.html?userId="+userId;
        },
        complete: function() {

        }
    });
}

function invokeArrangeCar() {
	var data = {
			activityId: activityId,
			teamId:teamId
	}
	
	$.ajax({
        type: "POST",
        dataType: "json",
        data: data,
        url:"service/ActivityAttender/invokeCarSchedule",
        success: function(result) {
        	alert(result);
        	windows.location = "activity.html?userId="+userId;
        },
        complete: function() {

        }
    });
}

function endActivity() {
	var data = {
			activityId: activityId,
			teamId:teamId
	}
	
	$.ajax({
        type: "POST",
        dataType: "json",
        data: data,
        url:"service/ActivityAttender/endActivity",
        success: function(result) {
        	windows.location = "activity.html?userId="+userId;
        },
        complete: function() {

        }
    });
}

function computeCostAction() {
	$("#refund").on("click", function() {
		var data = {
			activity_id: activityId,
			member_id: userId,
			team_id:teamId,
			value:$("#exchange").val(),
			type:$("#exchangeStatus").val() ,
			date: new Date()		
		};
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        data: data,
	        url:"service/Exchange/addExchange",
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

function addUserItoLink(){
	var carArrangementUrl = $("#car-arrangement").attr("href");
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	
	$("#car-arrangement").attr("href", carArrangementUrl + "?userId=" + userId+ "&activityId=" + activityId);
	$("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
}
