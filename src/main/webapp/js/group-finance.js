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

    $("#target_account").bind("change", function() {
        $("#group-finance-add-desc").empty();
        var value = $("#target_account").val();
        if(value == "1") {
            $("#group-finance-add-desc").append("<option value='3' forteam='0'>充值</option><option value='4'>出租车费</option><option value='5'>退款</option>");
        } else {
            $("#group-finance-add-desc").append("<option value='1' selected='selected'>群组经费</option><option value='2'>坏账</option>");
        }
        $("#group-finance-add-desc").selectmenu('refresh');
    });

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

            $("#target_account").empty();
            $("#target_account").append("<option value='-1' selected='selected'>群组经费</option>");
            var members = result.members;
            for(var i = 0; i < members.length; i++) {
                $("#target_account").append(function() {
                    return "<option value='" + members[i].id + "'>" + members[i].name + "</option>";
                });
            }
        },
        complete: function() {
            $("#target_account").selectmenu("refresh");
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