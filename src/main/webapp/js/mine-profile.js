$(document).on("pageshow", function() {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            id: "1"
        },
        url:"service/Member/getMemberById",
        success: function(result) {
            $("#user-name").val(result.name);
            $("#phone").val(result.phone);
        },
        complete: function() {
            
        }
    });
    
    $("#save").on("click", function() {
	alert("test to set: " + $("#user-name").val());
	/*
  		$.ajax({
       	 	type: "POST",
       	 	dataType: "json",
        	data: {
            	id: "1",
            	name: "sas",
            	phone: "1212"
        	},
        	url:"service/Member/updateMember",
        	success: function(result) {
            	alert("success to set: " + $("#user-name").val());
        	},
        	complete: function() {
            	        	}
    	});
    	*/
	});
});

