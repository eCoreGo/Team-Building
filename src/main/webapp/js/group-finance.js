/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageshow", function() {
    var raw = window.location.search;
    var queryString = raw.substr(1);
    var query = parseQueryString(queryString);
    var teamId = query["id"];

    var url = $("#backToGroup").attr("href");
    $("#backToGroup").attr("href", url + "?id=" + teamId);

    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            id: teamId
        },
        url:"service/Team/getTeamDetail",
        success: function(result) {
            $("#backToGroup").val(result.name + "经费管理");
            $("#group-finance").val(result.totalUserBalance + "RMB");
            $("#group-funds").val(result.totalUserBalance + "RMB");
        },
        complete: function() {

        }
    });

    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            id: teamId
        },
        url:"service/TeamMember/getTeamMembersByTeamId",
        success: function(result) {
            $("#members").empty();
            for(var i = 0; i < result.length; i++) {
                $("#members").append(function() {
                    return "<tr><th>" + i + "</th><td><a href='' data-rel='external'>" + result[i].member.name + "</a></td><td>" + result[i].balance + "</td></tr>";
                });
            }
        },
        complete: function() {
            $("#personl-finance").table("refresh");
        }
    });

    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            id: teamId
        },
        url:"service/Exchange/getExchangesByTeamId",
        success: function(result) {
            $("#exchange_detail").empty();
            for(var i = 0; i < result.length; i++) {
                $("#exchange_detail").append(function() {
                    return "<tr><th>" + i + "</th><td><a href='' data-rel='external'>" + result[i].member.name + "</a></td><td>" + result[i].value + "</td><td>" + result[i].date + "</td><td>" + result[i].type + "</td></tr>";
                });
            }
        },
        complete: function() {
            $("#finance-items").table("refresh");
        }
    });

});

function parseQueryString(queryString) {
    var params = queryString.split("&amp;");
    var temp, query = {};
    for(var i = 0, l = params.length; i < l; i++) {
        temp = params[i].split("=");
        query[temp[0]] = temp[1];
    }
    return query;
}