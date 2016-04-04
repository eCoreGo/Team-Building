/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageshow", function() {
    var raw = window.location.search;
    var queryString = raw.substr(1);
    var query = parseQueryString(queryString);
    var teamId = query["id"];
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            id: teamId
        },
        url:"service/Team/getTeamDetail",
        success: function(result) {
            $("#group-title").html(result.name);
            $("#group-name").val(result.name);
            $("#group-id").val(result.id);
            $("#group-description").html(result.description);
            $("#group-totalUserBalance").html(result.totalUserBalance + "RMB");

            var members = result.members;
            $("#members").empty();
            for(var i = 0; i < members.length; i++) {
                $("#members").append(function() {
                    return "<option value='" + members[i].id + "' selected='selected'>" + members[i].name + "</option>";
                });
            }
            $("#members").selectmenu("refresh");
        },
        complete: function() {

        }
    });

    $("#saveGroup").bind("click", function() {
        $.ajax({
            type: "POST",
            dataType: "json",
            data: {
                id: $("#group-id").val(),
                name: $("#group-name").val(),
                desc: $("#group-description").val(),
                members: $("#members").val().toString()
            },
            url:"service/Team/updateTeam",
            success: function(result) {

            },
            complete: function() {

            }
        });
    })
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