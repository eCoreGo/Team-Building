$(document).on("pageshow", function() {
	var teamsListDiv;
	var userID = 1;
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
		var selectedTeamIds = $('.teams-inputs').map(function(){
			return $(this).is(':checked') ? $(this).val() : undefined;
		}).get();


		console.log("user name: " + $("#user-name").val());
		console.log("phone no: " + $("#phone").val());
		console.log("selected team ids: " +selectedTeamIds);
		
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

	getAllTeams(teamsListDiv);
	//getCheckedTeams(teamsListDiv);

	
	
});
function getAllTeams(teamsListDiv) {
	var demoData = [
		{
			"id": 1,
			"temp": null,
			"name": "eCore",
			"description": "A wonderful team!",
			"totalFoundation": 100,
			"totalUserBalance": 200,
			"creationTime": null,
			"teamMembers": null,
			"members": null
		},
		{
			"id": 2,
			"temp": null,
			"name": "CCAR",
			"description": "ABC!",
			"totalFoundation": 100,
			"totalUserBalance": 200,
			"creationTime": null,
			"teamMembers": null,
			"members": null
		},
		{
			"id": 3,
			"temp": null,
			"name": "retail",
			"description": "sas!",
			"totalFoundation": 100,
			"totalUserBalance": 200,
			"creationTime": null,
			"teamMembers": null,
			"members": null
		}
	];

	/**
	 * Get all teams , and set toggle button to .teams-container
	 */
	$.ajax({
		type: "POST",
		dataType: "json",
		url:"service/Team/getAllTeams",
		complete: function(result) {
			teamsListDiv = $(".teams-list .ui-controlgroup-controls");
			$(demoData).each(function(index){
				var checkId = this.id;
				var checkLabel = this.name;
				$('<input type="checkbox" name="checkbox-'+checkId+'" ' +
				'id="checkbox-'+checkId+'" class="teams-inputs" value="'+checkId+'">' +
				'<label for="checkbox-'+checkId+'">'+checkLabel+'</label>').appendTo(teamsListDiv);
			});
			getCheckedTeams(teamsListDiv);
			teamsListDiv.enhanceWithin().controlgroup("refresh");
		}
	});
	
	
}

function getCheckedTeams(teamsListDiv) {
	var demoMteams = [
		{
			"id": 1,
			"temp": null,
			"name": "eCore",
			"description": "A wonderful team!",
			"totalFoundation": 100,
			"totalUserBalance": 200,
			"creationTime": null,
			"teamMembers": null,
			"members": null
		},
		{
			"id": 2,
			"temp": null,
			"name": "CCAR",
			"description": "ABC!",
			"totalFoundation": 100,
			"totalUserBalance": 200,
			"creationTime": null,
			"teamMembers": null,
			"members": null
		}
	];
	
	$.ajax({
		type: "POST",
		dataType: "json",
		data: {
            id: "1"
        },
		url:"service/Member/getTeams",
		complete: function(result) {
			$(demoMteams).each(function(index){
				aa = $(".teams-list .ui-controlgroup-controls");
				var checkId = this.id;
				$("#checkbox-"+checkId).prop("checked",true).checkboxradio('refresh');
			});
		}
	});
}