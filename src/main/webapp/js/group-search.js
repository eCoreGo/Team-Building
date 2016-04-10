/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageshow", function() {
	var userId = findParameterValue("userId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	var back2GroupLinkUrl = $("#back2GroupLink").attr("href");
	
	$("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
    $("#back2GroupLink").attr("href", back2GroupLinkUrl + "?userId=" + userId);

    $("#search").bind({"change": function(){
        $.ajax({
            type: "POST",
            dataType: "json",
            data: {
            	userId: userId,
                name: $("#search").val(),
            },
            url:"service/Team/getTeamsByNameWildCard",
            success: function(result) {
                $("#teamList").empty();
                for(var i = 0; i < result.length; i++) {
                    $("#teamList").append(function() {
                        return "<li><a data-ajax = 'false' href='group-item.html?userId=" + userId + "&id=" + result[i].id + "'>" + result[i].name + "</a></li>";
                    });
                }
            },
            complete: function() {
                $("#teamList").listview("refresh");
            }
        });
    }});
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