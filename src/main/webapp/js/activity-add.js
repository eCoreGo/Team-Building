/**
 * Created by huihui.
 */

var userId;
$(document).on("pageshow", function() {
	userId = findParameterValue("userId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	
	$("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
    
	initTeamList();
	foundationCostChange();
});

function addActivity() {
//	var data = $("#addActivityForm").serialize();
	
	$.ajax({
        type: "POST",
        dataType: "json",
        data:
        {
	        name: $("#name").val(),
	        description: $("#description").val(),
	        totalFoundationCost:$("#totalFoundationCost").val(),
	        teamId:$("#teamList").val(),
	        startTime:new Date($("#startTime").val()),
	        endTime:new Date($("#endTime").val()),
	        hasFoundationCost:$("#hasFoundationCost").val(),
	        openCarSchedule:$("#openCarSchedule").val(),
	        openExchangeModule:$("#openExchangeModule").val(),
        },
        url:"service/Activity/addActivity",
        success: function(result) {
        	alert("活动创建成功，可以去activity页面选择发布");
        	$("#activityId").val(result.activityId);
        },
        complete: function() {
        	window.location = "activity.html";
        }
    });
}

function pushActivity() {
	var data = $("#addActivityForm").serialize();
	
	$.ajax({
        type: "POST",
        dataType: "json",
        data: data,
        url:"service/ActivityAttender/initActivityAttender",
        success: function(result) {
        	alert(result);
        	windows.location = "activity.html";
        },
        complete: function() {

        }
    });
}

function invokeTaixSchedule() {
	var data = $("#addActivityForm").serialize();
	
	$.ajax({
        type: "POST",
        dataType: "json",
        data: data,
        url:"service/ActivityAttender/initActivityAttender",
        success: function(result) {
        	alert(result);
        	windows.location = "activity.html";
        },
        complete: function() {

        }
    });
}

//function invokeExchange() {
//	var data = $("#addActivityForm").serialize();
//	
//	$.ajax({
//        type: "POST",
//        dataType: "json",
//        data: data,
//        url:"service/ActivityAttender/initActivityAttender",
//        success: function(result) {
//        	alert(result);
//        	windows.location = "activity.html";
//        },
//        complete: function() {
//
//        }
//    });
//}

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
        	$("#teamList").selectmenu();
        	$("#teamList").selectmenu("refresh", true);
        }
    });	
}

function foundationCostChange() {
	$("#hasFoundationCost").on("change", function(){
		if($(this).val()=="yes") {
			$("#totalFoundationCostDiv").show();
		}else{
			$("#totalFoundationCostDiv").hide();
		}
	})
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