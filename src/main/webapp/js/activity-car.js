$(document).on("pageshow", function() {
	var userId = findParameterValue("userId");
	var activityId = findParameterValue("activityId");
	var teamId = findParameterValue("teamId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	var homeLinkUrl = $("#homeLink").attr("href");
	
	$("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
    $("#homeLink").attr("href", homeLinkUrl + "?userId=" + userId);
    
	
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
        success: function(carArranges) {
        	var activityCarBody = $("#activity-car tbody");
        	activityCarBody.empty();
            for(var i=0; i<carArranges.length; i++){
            	var tr = "<tr>" +
            			"<th>" + i + "</th>" +
            			"<td><a href='' data-rel='external'>" + carArranges[i].carNo + "</a></td>" +
            			"<td>" + carArranges[i].passengers + "</td>" +
            			"<td>" + carArranges[i].drivers + "</td>" +
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
    var params = queryString.split("&");
    var temp, query = {};
    for(var i = 0, l = params.length; i < l; i++) {
        temp = params[i].split("=");
        query[temp[0]] = temp[1];
    }
    return query;
}