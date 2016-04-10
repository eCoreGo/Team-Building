$(document).on("pageshow", function() {
        getTeamMembers();
    filterFiananceDetail();
});

function filterFiananceDetail() {
        var raw = window.location.search;
    var queryString = raw.substr(1);
    var query = parseQueryString(queryString);
    var userId = query["userId"];
    var teamId = $("#groups").val();
    var duration = $("#duration").val();
    
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
                memberId: userId,
                teamId : teamId,
                duration : duration
        },
        url:"service/ExchangeDetail/getExchangeDetails",
        success: function(result) {
            $("#mine-finance-detail").empty();
            for(var i = 0; i < result.length; i++) {
                $("#mine-finance-detail").append(function() {
                        return "<tr><th>" + (i + 1) + "</th><td>" + result[i].activityId + "</td><td>" + result[i].exchangeStatus + "</td><td>" + result[i].exchange + "</td><td>" + result[i].date "</td></tr>";
                });
            }
        },
        complete: function() {
            $("#mine-finance-detail").table("refresh");
        }
    });
}

function getTeamMembers() {
        var raw = window.location.search;
    var queryString = raw.substr(1);
    var query = parseQueryString(queryString);
    var userId = query["userId"];
    var teamId = $("#groups").val();
    var duration = $("#duration").val();
    
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
                memberId: userId
        },
        url:"service/TeamMember/getTeamMembersByMemberId",
        success: function(result) {
                var mineTotal = 0;
            $("#mine-group-finance").empty();
            $("#groups").empty();
            for(var i = 0; i < result.length; i++) {
                mineTotal = mineTotal + result[i].balance;
                $("#mine-group-finance").append(function() {
                        return "<tr><th>" + (i + 1) + "</th><td>" + result[i].team.name + "</td><td>" + result[i].balance + "</td></tr>";
                });
                $("#groups").append(function() {
                    return "<option value='" + result[i].id + "'>" + result[i].team.name + "</option>";
                });
            }
            $("#groups").selectmenu("refresh");
            $("#group-funds").val(mineTotal);
            $("#mine-group-finance").table("refresh");
        },
        complete: function() {
        }
    });
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