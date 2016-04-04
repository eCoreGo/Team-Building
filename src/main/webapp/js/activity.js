/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageshow", function() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url:"service/Activity/getAllActivitiesByMemberId",
        data: {
            memberId: "1"
        },
        success: function(result) {
            $("#activitiesListView").empty();
            for(var i = 0; i < result.length; i++) {
                $("#activitiesListView").append(function() {
                    return "<li>" +
                        "<a data-ajax = 'false' href='activity-item.html?id=" + result[i].id + "'>" +
                        "<h1>" + result[i].name + "</h1>" +
                        "<p><strong>" + result[i].startTime + "-" + result[i].endTime + "</strong></p>" +
                        "<p>" + result[i].description + "</p>" +
                        "<p class='ui-li-aside'><strong>" + result[i].status + "</strong></p>" +
                        "</a></li>";
                });
            }
            $("#activitiesListView").selectmenu("refresh");
        },
        complete: function() {
            $("#teamList").listview("refresh");
        }
    });
});