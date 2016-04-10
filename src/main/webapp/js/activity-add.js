/**
 * Created by huihui.
 */
$(document).on("pageshow", function() {
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