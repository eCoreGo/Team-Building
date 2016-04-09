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
		saveBasicInfo(userID);   	
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

function saveBasicInfo(userID) {
	
		console.log("user name: " + $("#user-name").val());
		console.log("phone no: " + $("#phone").val());
		
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
            	alert("success to set: " + $("#user-name").val() + $("#phone").val());
            	updateTeam(userID);
        	},
        	complete: function() {
        		updateTeam(userID);
        	}
    	});
	
}

function updateTeam(userID) {
		var selectedTeamIds = $('.teams-inputs').map(function(){
			return $(this).is(':checked') ? $(this).val() : undefined;
		}).get();

		console.log("selected team ids: " +selectedTeamIds);
		
		var failedToUpdateTeamMember = '';
		$.each(selectedTeamIds,function(n,value) {
			console.log("for teamId: " +value + " userId : " + userID );
			updateEachTeam(value,userID,failedToUpdateTeamMember);
			
		});
		
		completed(failedToUpdateTeamMember);
        			
}

function completed(failedToUpdateTeamMember) {
	if (failedToUpdateTeamMember == '') {
        	alert("success to update team");
        } else {
        	alert("You still have balance in these teams : " + failedToUpdateTeamMember + " . Please contact admin to leave team.");
        }
}

function updateEachTeam(value,userID,failedToUpdateTeamMember) {
	var demoTeamMember='{"team_id":value,"member_id":"1","balance":value,"attend_time":null}';
	var obj = eval('(' + demoTeamMember + ')'); 
	$.ajax({
       	 		type: "POST",
       	 		dataType: "json",
       	 		async:false,
        		data: {
            		teamId: value,
            		memberId: userID
        		},
        		url:"service/TeamMember/getTeamMemberInfo",
        		success: function(result) {
        			if(value == "3") {
        				result = null;
        			} else {
        				result = obj;
        			}
        			
        			if(result !== '') {
            			var balance = parseFloat(result.balance);
            			if (balance > parseFloat(0)) {
            				failedToUpdate(result.memberId,failedToUpdateTeamMember);
            			} else {
            				alert("success to delete " + result.teamId + " , " +result.memberId);
            				//deleteTeamMember(result.teamId, result.memberId);
            			}
        			} else {
        				alert("success to add " + value + " , " +userID);
        				//addTeamMember(value, userID);
        			}
           	 		
        		},
        		complete: function() {
        			var result;
        			if(value == "3") {
        				result = null;
        			} else {
        				result = obj;
        			}
        			
        			if(result !== undefined) {
            			var balance = parseFloat(1);
            			if (balance > parseFloat(0)) {
            				failedToUpdate(value,failedToUpdateTeamMember);
            			} else {
            				alert("success to delete " + + value + " , " +userID);
            				//deleteTeamMember(result.teamId, result.memberId);
            			}
        			} else {
        				alert("success to add " + value + " , " +userID);
        				//addTeamMember(value, userID);
        			}
        		}
    		});
	
}

function addTeamMember(teamId, memberId) {
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
           	 		
        		},
        		complete: function() {
        		}
    		});
}

function deleteTeamMember(teamId, memberId) {
		$.ajax({
       	 		type: "POST",
       	 		dataType: "json",
        		data: {
            		teamId: teamId,
            		memberId: memberId
        		},
        		url:"service/TeamMember/leave",
        		success: function(result) {
           	 		
        		},
        		complete: function() {
        		}
    		});
}

function failedToUpdate(teamId, failedToUpdateTeamMember) {
		$.ajax({
       	 		type: "POST",
       	 		dataType: "json",
        		data: {
            		teamId: teamId
        		},
        		url:"service/Team/getTeamDetail",
        		success: function(result) {
           	 		failedToUpdateTeamMember = failedToUpdateTeamMember + result.name + " ";
        		},
        		complete: function() {
        			alert("failedToUpdateTeamMember  "+teamId + " ");
        			failedToUpdateTeamMember = failedToUpdateTeamMember + teamId + " ";
        		}
    		});
}