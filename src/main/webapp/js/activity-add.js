/**
 * Created by huihui.
 */
$(document).on("pageshow", function() {
	initTeamList();
	foundationCostChange();
});

function addActivity() {
	var data = $("#addActivityForm").serialize();
	
	$.ajax({
        type: "POST",
        dataType: "json",
        data: data,
        url:"service/Activity/addActivity",
        success: function(result) {
        	alert(result);
        	windows.location = "activity.html";
        },
        complete: function() {

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

function foundationCostChange() {
	$("#hasFoundationCost").on("change", function(){
		if($(this).val()=="yes") {
			$("#totalFoundationCostDiv").show();
		}else{
			$("#totalFoundationCostDiv").hide();
		}
	})
}