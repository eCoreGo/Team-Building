/**
 * Created by stereomatrix on 2016/3/25.
 */
$(document).on("pageshow", function() {

    $.ajax({
        type: "POST",
        dataType: "json",
        url:"service/Member/getMembers",
        success: function(result) {
            var members = result;
            $("#members").empty();
            for(var i = 0; i < members.length; i++) {
                $("#members").append(function() {
                    return "<option value='" + members[i].id + "'>" + members[i].name + "</option>";
                });
            }
            $("#members").selectmenu("refresh");
        },
        complete: function() {

        }
    });

    $("#addGroup").bind("click", function() {
        var name = $("#group-name").val();
        var desc = $("#group-description").val();
        var members = $("#members").val();
        var memberString = members != null ? members.toString() : "";
        $.ajax({
            type: "POST",
            dataType: "json",
            data: {
                name: name,
                desc: desc,
                members: memberString
            },
            url:"service/Team/addTeam",
            success: function(result) {
            },
            complete: function() {

            }
        });
    });
});