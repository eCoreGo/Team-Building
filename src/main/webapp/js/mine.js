$(document).on("pageshow", function() {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            id: "1"
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