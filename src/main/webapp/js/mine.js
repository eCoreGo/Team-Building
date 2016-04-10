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
            var profileUrl = $("#mineProfile").attr("href");
            $("#mineProfile").attr("href", profileUrl + "?userId=" + result.id);
            profileUrl = $("#mineFinance").attr("href");
            $("#mineFinance").attr("href", profileUrl + "?userId=" + result.id);
        },
        complete: function() {
            
        }
    });
});