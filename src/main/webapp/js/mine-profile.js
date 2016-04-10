$(document).on("pageshow", function() {
	var teamsListDiv;
	var userID = 1;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            id: userID
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
		saveBasicInfo(userID);   	
	});

	getAllTeams(teamsListDiv,userID);

});
function getAllTeams(teamsListDiv,userID) {
	/*
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
	];*/

	/**
	 * Get all teams , and set toggle button to .teams-container
	 */
	$.ajax({
		type: "POST",
		dataType: "json",
		url:"service/Team/getAllTeams",
		complete: function(result) {
			teamsListDiv = $(".teams-list .ui-controlgroup-controls");
			$(result).each(function(index){
				var checkId = this.id;
				var checkLabel = this.name;
				$('<input type="checkbox" name="checkbox-'+checkId+'" ' +
				'id="checkbox-'+checkId+'" class="teams-inputs" value="'+checkId+'">' +
				'<label for="checkbox-'+checkId+'">'+checkLabel+'</label>').appendTo(teamsListDiv);
			});
			getCheckedTeams(teamsListDiv,userID);
			teamsListDiv.enhanceWithin().controlgroup("refresh");
		}
	});
	
	
}

function getCheckedTeams(teamsListDiv,userID) {
	/*
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
	];*/
	
	$.ajax({
		type: "POST",
		dataType: "json",
		data: {
            id: "1"
        },
		url:"service/TeamMember/getTeams",
		complete: function(result) {
			$(result).each(function(index){
				aa = $(".teams-list .ui-controlgroup-controls");
				var checkId = this.id;
				$("#checkbox-"+checkId).prop("checked",true).checkboxradio('refresh');
			});
			
			updateTeam(userID);
			teamsListDiv.enhanceWithin().controlgroup("refresh");
		}
	});
}

function saveBasicInfo(userID) {
		console.log("user name: " + $("#user-name").val());
		console.log("phone no: " + $("#phone").val());
		
		$.ajax({
       	 	type: "POST",
       	 	dataType: "json",
        	data: {
            	id: userID,
            	name: $("#user-name").val(),
            	phone: $("#phone").val()
        	},
        	url:"service/Member/updateMember",
        	success: function(result) {
            	alert("success to set: " + $("#user-name").val() + $("#phone").val());
        	},
        	complete: function() {
        	}
    	});
	
}

function updateTeam(userID) {
	/*
		var selectedTeamIds = $('.teams-inputs').map(function(){
			return $(this).is(':checked') ? $(this).val() : undefined;
		}).get();

		console.log("selected team ids: " +selectedTeamIds);
		
		$.each(selectedTeamIds,function(n,value) {
			console.log("for teamId: " +value + " userId : " + userID );
			updateEachTeam(value,userID,failedToUpdateTeamMember);
			
		});
		*/
		$('.teams-inputs').each(function() {
			$(this).click(function() {
				if(!$(this).is(':checked')) {
					console.log("for teamId: " +$(this).val() + " teamname: " +  $(this).parent().text() );
					leaveTeam($(this).val(),userID,$(this).parent().text() );
				} else {
					addTeamMember($(this).val(), userID, $(this).parent().text());
				}
				
			});
			
			
		});
}

function leaveTeam(teamID,userID,teamName) {
	$.ajax({
 		type: "POST",
 		dataType: "json",
		data: {
    		teamId: teamID,
    		memberId: userID
		},
		cache:false,
		async:false,
		url:"service/TeamMember/getTeamMemberInfo",
		success: function(result) {
    			var balance = parseFloat(result.balance);
    			if (balance > parseFloat(0)) {
    				alert("You still have balance in these teams : " + teamName + " . Please contact admin to leave team.");
    				$("#checkbox-"+teamID).prop("checked",true).checkboxradio('refresh');
    			} else {
    				deleteTeamMember(teamID, userID, teamName);
    			}
		},
		complete: function() {
		}
	});
}

function addTeamMember(teamId, memberId, teamName) {
		var date = Date();
		$.ajax({
       	 		type: "POST",
       	 		dataType: "json",
        		data: {
            		teamId: teamId,
            		memberId: memberId,
            		balance: 0.00,
            		Date: date
            		
        		},
        		url:"service/TeamMember/join",
        		success: function(result) {
        			$("#checkbox-"+teamId).prop("checked",true).checkboxradio('refresh');
           	 		alert("success to join team " + teamName );
        		},
        		complete: function() {
        		}
    		});
}

function deleteTeamMember(teamId, memberId, teamName) {
		$.ajax({
       	 		type: "POST",
       	 		dataType: "json",
        		data: {
            		teamId: teamId,
            		memberId: memberId
        		},
        		url:"service/TeamMember/leave",
        		success: function(result) {
           	 		alert("success to leave " + teamName + " , " +result.memberId);
        		},
        		complete: function() {
        		}
    		});
}
