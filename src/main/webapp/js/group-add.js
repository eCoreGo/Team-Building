/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageinit", function() {
	var userId = findParameterValue("userId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	var groupAddLinkUrl = $("#addGroup").attr("href");
	var homeLinkUrl = $("#homeLink").attr("href");
	
	$("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
    $("#addGroup").attr("href", groupAddLinkUrl + "?userId=" + userId);
    $("#homeLink").attr("href", homeLinkUrl + "?userId=" + userId);

    $.ajax({
        type: "POST",
        dataType: "json",
        url:"service/Member/getMembers",
        data: {
            userId: userId
        },
        success: function(result) {
            var members = result;
            $("#members").empty();
            for(var i = 0; i < members.length; i++) {
                $("#members").append(function() {
                    return "<option value='" + members[i].id + "'>" + members[i].name + "</option>";
                });
            }
            $("#members").selectmenu("refresh");
        },
        complete: function() {

        }
    });

    $("#addGroup").bind("click", function() {
        var name = $("#group-name").val();
        var desc = $("#group-description").val();
        var members = $("#members").val();
        var memberString = members != null ? members.toString() : "";
        $.ajax({
            type: "POST",
            dataType: "json",
            data: {
            	userId: userId,
                name: name,
                desc: desc,
                members: memberString
            },
            url:"service/Team/addTeam",
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