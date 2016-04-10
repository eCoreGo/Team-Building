/**
 * Created by stereomatrix on 2016/3/25.
 */
var pair = {};
pair[1] = "团队经费";
pair[2] = "坏账";
pair[3] = "充值";
pair[4] = "出租车费"
pair[5] = "退款";
pair[6] = "均摊";
pair[7] = "活动总开销";
$(document).on("pageshow", function() {
//    var raw = window.location.search;
//    var queryString = raw.substr(1);
//    var query = parseQueryString(queryString);
//    var teamId = query["id"];
	var teamId = findParameterValue("id");
	
	var userId = findParameterValue("userId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	
	$("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);

    var url = $("#backToGroup").attr("href");
    $("#backToGroup").attr("href", url + "?id=" + teamId + "&userId=" + userId);

    $("#target_account").bind("change", function() {
        $("#group-finance-add-desc").empty();
        var value = $("#target_account").val();
        if(value != "-1") {
            $("#group-finance-add-desc").append("<option value='3' forteam='0'>充值</option><option value='4'>出租车费</option><option value='5'>退款</option>");
        } else {
            $("#group-finance-add-desc").append("<option value='1' selected='selected'>群组经费</option><option value='2'>坏账</option>");
        }
        $("#group-finance-add-desc").selectmenu('refresh');
    });

    $("#addExchangeDetail").bind("click", function() {
        var value = $("#money").val();
        var target = $("#target_account").val();
        var type = $("#group-finance-add-desc").val();

        var data = {};
        data["userId"] = userId;
        data["value"] = value;
        data["type"] = type;
        data["date"] = new Date();
        data["team_id"] = teamId;

        if(target != '-1') {
            data["member_id"] = target;
        }

        $.ajax({
            type: "POST",
            dataType: "json",
            data: data,
            url:"service/Exchange/addExchange",
            success: function(result) {

            },
            complete: function() {
                getExchangesByTeamId();
            }
        });
    });

    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
        	userId: userId,
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
        	userId: userId,
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

    getExchangesByTeamId();

});

function getExchangesByTeamId() {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            userId: findParameterValue("userId"),
            id: findParameterValue("id")
        },
        url:"service/Exchange/getExchangesByTeamId",
        success: function(result) {
            $("#exchange_detail").empty();
            for(var i = 0; i < result.length; i++) {
                $("#exchange_detail").append(function() {
                    return "<tr><th>" + i + "</th><td><a href='' data-rel='external'>" + result[i].member.name + "</a></td><td>" + result[i].value + "</td><td>" + result[i].date + "</td><td>" + pair[result[i].type] + "</td></tr>";
                });
            }
        },
        complete: function() {
            $("#finance-items").table("refresh");
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