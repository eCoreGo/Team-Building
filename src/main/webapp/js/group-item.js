/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageshow", function() {
//    var raw = window.location.search;
//    var queryString = raw.substr(1);
//    var query = parseQueryString(queryString);
//    var teamId = query["id"];
    var teamId = findParameterValue("id");
    var userId = findParameterValue("userId");
    var homeLinkUrl = $("#homeLink").attr("href");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	var back2GroupLinkUrl = $("#back2GroupLink").attr("href");
	
    $("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
    $("#back2GroupLink").attr("href", back2GroupLinkUrl + "?userId=" + userId);
    $("#homeLink").attr("href", homeLinkUrl + "?userId=" + userId);

    var url = $("#checkActivitiesByGroup").attr("href");
    $("#checkActivitiesByGroup").attr("href", url + "?id=" + teamId + "&userId=" + userId);

    url = $("#group-totalUserBalance").attr("href");
    $("#group-totalUserBalance").attr("href", url + "?id=" + teamId + "&userId=" + userId);
    
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            id: teamId
        },
        url:"service/Team/getTeamDetail",
        success: function(result) {
            $("#group-title").html(result.name);
            $("#group-name").val(result.name);
            $("#group-id").val(result.id);
            $("#group-description").html(result.description);
            $("#group-totalUserBalance").html(result.totalUserBalance + "RMB");

            var members = result.members;
            $("#members").empty();
            for(var i = 0; i < members.length; i++) {
                $("#members").append(function() {
                    return "<option value='" + members[i].id + "' selected='selected'>" + members[i].name + "</option>";
                });
            }
            $("#members").selectmenu("refresh");
        },
        complete: function() {

        }
    });

    $("#saveGroup").bind("click", function() {
        $.ajax({
            type: "POST",
            dataType: "json",
            data: {
            	userId: userId,
                id: $("#group-id").val(),
                name: $("#group-name").val(),
                desc: $("#group-description").val(),
                members: $("#members").val().toString()
            },
            url:"service/Team/updateTeam",
            success: function(result) {

            },
            complete: function() {

            }
        });
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