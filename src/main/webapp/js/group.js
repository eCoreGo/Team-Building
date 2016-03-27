/**
 * Created by stereomatrix on 2016/3/25.
 */
/*$(document).ready(function() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url:"service/Team/getAllTeams",
        success: function(result) {
            for(var i = 0; i < result.length; i++) {
                $("#teamList").append(function() {
                    return "<li><a href='group-item.html?id=" + result[i].id + "'>" + result[i].name + "</a></li>";
                });
            }
        },
        complete: function() {
            $("#teamList").listview("refresh");
        }
    });
});*/

$(document).on("pageinit", function() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url:"service/Team/getAllTeams",
        success: function(result) {
            for(var i = 0; i < result.length; i++) {
                $("#teamList").append(function() {
                    return "<li><a data-ajax = 'false' href='group-item.html?id=" + result[i].id + "'>" + result[i].name + "</a></li>";
                });
            }
        },
        complete: function() {
            $("#teamList").listview("refresh");
        }
    });
});