/**
 * Created by stereomatrix on 2016/3/25.
 */

var userId;
$(document).on("pageshow", function() {
	userId = findParameterValue("userId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	var homeLinkUrl = $("#homeLink").attr("href");
	
	$("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#homeLink").attr("href", homeLinkUrl + "?userId=" + userId);
    
    getActivitiesByStatus();
    $("fieldset input").bind("change", function() {
        getActivitiesByStatus();
    })
});

function getActivitiesByStatus(status) {
    var status = [];
    $("fieldset input").each(function () {
        if(this.checked == true) {
            status.push(this.value);
        }
    });
    status = status.toString()
    $.ajax({
        type: "POST",
        dataType: "json",
        url:"service/Activity/getAllActivitiesByStatus",
        data: {
            status: status
        },
        success: function(result) {
            $("#activitiesListView").empty();
            if(result.length == 0) {
                $("#activitiesListView").append("<li><a href='javascript:void(0);'>No record!</a></li>");
            } else {
                for(var i = 0; i < result.length; i++) {
                    $("#activitiesListView").append(function() {
                        return "<li>" +
                            "<a data-ajax = 'false' href='activity-detail.html?id=" + result[i].id + "'>" +
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