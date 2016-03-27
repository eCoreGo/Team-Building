/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageinit", function() {
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
            $("#group-name").val(result.name);
            $("#group-id").val(result.id);
        },
        complete: function() {

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