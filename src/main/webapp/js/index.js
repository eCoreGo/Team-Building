$(document).on("pageshow", function() {
	var userId = findParameterValue("userId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
	var homeLinkUrl = $("#homeLink").attr("href");
    $("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
    $("#homeLink").attr("href", homeLinkUrl + "?userId=" + userId);
});