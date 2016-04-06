/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageshow", function() {
    getActivitiesByStatus();
    $("fieldset input").bind("change", function() {
        getActivitiesByStatus();
    })
});

function getActivitiesByStatus(status) {
    var status = [];
    $("fieldset input").each(function () {
        if(this.checked == true) {
            status.push(this.value);
        }
    });
    status = status.toString()
    $.ajax({
        type: "POST",
        dataType: "json",
        url:"service/Activity/getAllActivitiesByStatus",
        data: {
            status: status
        },
        success: function(result) {
            $("#activitiesListView").empty();
            if(result.length == 0) {
                $("#activitiesListView").append("<li><a href='javascript:void(0);'>No record!</a></li>");
            } else {
                for(var i = 0; i < result.length; i++) {
                    $("#activitiesListView").append(function() {
                        return "<li>" +
                            "<a data-ajax = 'false' href='activity-detail.html?id=" + result[i].id + "'>" +
                            "<h1>" + result[i].name + "</h1>" +
                            "<p><strong>" + result[i].startTime + " - " + result[i].endTime + "</strong></p>" +
                            "<p>From <strong>" + result[i].team.name + "</strong></p>" +
                            "<p>" + result[i].description + "</p>" +
                            "<p class='ui-li-aside'><strong>" + result[i].status + "</strong></p>" +
                            "</a></li>";
                    });
                }
            }
        },
        complete: function() {
            $("#activitiesListView").listview("refresh");
        }
    });
}