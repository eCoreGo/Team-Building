/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageshow", function() {

    $("#search").bind({"change": function(){
        $.ajax({
            type: "POST",
            dataType: "json",
            data: {
                name: $("#search").val(),
            },
            url:"service/Team/getTeamsByNameWildCard",
            success: function(result) {
                $("#teamList").empty();
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
    }});
});