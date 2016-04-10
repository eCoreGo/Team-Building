$(document).on("pageshow", function() {
	var userId = findParameterValue("userId");
	var activityId = findParameterValue("activityId");
	var teamId = findParameterValue("teamId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	
	$("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
	
    var url = $("#backToGroup").attr("href");
    $("#backToGroup").attr("href", url + "?id=" + teamId + "&userId=" + userId);
    
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
        	activityId: activityId,
        	userId: userId,
        },
        url:"service/ActivityAttender/getArangeTaixInfo",
        success: function(activityAttenders) {
        	var activityCarBody = $("#activity-car tbody");
            for(var i=0; i<activityAttenders.length; i++){
            	var tr = "<tr>" +
            			"<th>" + i + "</th>" +
            			"<td><a href='' data-rel='external'>" + activityAttenders[i].seatnumber + "</a></td>" +
            			"<td>" + activityAttenders[i].user_name + "</td>" +
            			"</tr>";
            	activityCarBody.append(tr);
            }
        },
        complete: function() {
            //$("#activitiesListView").listview("refresh");
        }
    });
});

function findParameterValue(parameterKey) {
	var raw = window.location.search;
    var queryString = raw.substr(1);
    var queryParameterMap = parseQueryString(queryString);
    return queryParameterMap[parameterKey];
}

function parseQueryString(queryString) {
    var params = queryString.split("&amp;");
    var temp, query = {};
    for(var i = 0, l = params.length; i < l; i++) {
        temp = params[i].split("=");
        query[temp[0]] = temp[1];
    }
    return query;
}