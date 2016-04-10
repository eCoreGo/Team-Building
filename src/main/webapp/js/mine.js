$(document).on("pageshow", function() {
	var userId = findParameterValue("userId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	var mineFinanceUrl = $("#mineFinance").attr("href");
	var mineProfileUrl = $("#mineProfile").attr("href");
    $("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
    $("#mineFinance").attr("href", mineFinanceUrl + "?userId=" + userId);
    $("#mineProfile").attr("href", mineProfileUrl + "?userId=" + userId);
    
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            id: userId
        },
        url:"service/Member/getMemberById",
        success: function(result) {
            $("#member-name").html(result.name);
            $("#member-phone").html(result.phone);
        },
        complete: function() {
            
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