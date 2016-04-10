/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageshow", function() {
	var userId = findParameterValue("userId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	
	$("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
	
//    var raw = window.location.search;
//    var queryString = raw.substr(1);
//    var query = parseQueryString(queryString);
//    var teamId = query["id"];
    var teamId = findParameterValue("id");
    var url = $("#backToGroup").attr("href");
    $("#backToGroup").attr("href", url + "?id=" + teamId + "&userId=" + userId);
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
        	userId: userId,
            id: teamId
        },
        url:"service/Activity/getTeamActivities",
        success: function(result) {
            $("#activitiesListView").empty();
            if(result.length == 0) {
                $("#activitiesListView").append("<li><a href='javascript:void(0);'>No record!</a></li>");
            } else {
                for(var i = 0; i < result.length; i++) {
                    $("#activitiesListView").append(function() {
                        return "<li>" +
                            "<a data-ajax = 'false' href='activity-detail.html?userId=" + userId + "&id=" + result[i].id + "'>" +
                            "<h1>" + result[i].name + "</h1>" +
                            "<p><strong>" + result[i].startTime + " - " + result[i].endTime + "</strong></p>" +
                            "<p>From <strong>" + result[i].team.name + "</strong></p>" +
                            "<p>" + result[i].description + "</p>" +
                            "<p class='ui-li-aside'><strong>" + result[i].status + "</strong></p>" +
                            "</a></li>";
                    });
                }
            }
        },
        complete: function() {
            $("#activitiesListView").listview("refresh");
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