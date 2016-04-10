$(document).on("pageshow", function() {
        getTeamMembers();
    filterFiananceDetail();
});

function filterFiananceDetail() {
        var raw = window.location.search;
    var queryString = raw.substr(1);
    var query = parseQueryString(queryString);
    var userId = query["userId"];
    var teamId = $("#groups").val();
    var duration = $("#duration").val();
    
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
<<<<<<< HEAD
                memberId: userId,
                teamId : teamId,
                duration : duration
=======
            id: userID
>>>>>>> branch 'master' of https://github.com/eCoreGo/Team-Building.git
        },
        url:"service/ExchangeDetail/getExchangeDetails",
        success: function(result) {
            $("#mine-finance-detail").empty();
            for(var i = 0; i < result.length; i++) {
                $("#mine-finance-detail").append(function() {
                        return "<tr><th>" + (i + 1) + "</th><td>" + result[i].activityId + "</td><td>" + result[i].exchangeStatus + "</td><td>" + result[i].exchange + "</td><td>" + result[i].date "</td></tr>";
                });
            }
        },
        complete: function() {
            $("#mine-finance-detail").table("refresh");
        }
    });
<<<<<<< HEAD
=======
    
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
	
	
>>>>>>> branch 'master' of https://github.com/eCoreGo/Team-Building.git
}

<<<<<<< HEAD
function getTeamMembers() {
        var raw = window.location.search;
    var queryString = raw.substr(1);
    var query = parseQueryString(queryString);
    var userId = query["userId"];
    var teamId = $("#groups").val();
    var duration = $("#duration").val();
    
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
                memberId: userId
=======
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
>>>>>>> branch 'master' of https://github.com/eCoreGo/Team-Building.git
        },
<<<<<<< HEAD
        url:"service/TeamMember/getTeamMembersByMemberId",
        success: function(result) {
                var mineTotal = 0;
            $("#mine-group-finance").empty();
            $("#groups").empty();
            for(var i = 0; i < result.length; i++) {
                mineTotal = mineTotal + result[i].balance;
                $("#mine-group-finance").append(function() {
                        return "<tr><th>" + (i + 1) + "</th><td>" + result[i].team.name + "</td><td>" + result[i].balance + "</td></tr>";
                });
                $("#groups").append(function() {
                    return "<option value='" + result[i].id + "'>" + result[i].team.name + "</option>";
                });
            }
            $("#groups").selectmenu("refresh");
            $("#group-funds").val(mineTotal);
            $("#mine-group-finance").table("refresh");
        },
        complete: function() {
        }
    });
}

function parseQueryString(queryString) {
    var params = queryString.split("&amp;");
    var temp, query = {};
    for(var i = 0, l = params.length; i < l; i++) {
        temp = params[i].split("=");
        query[temp[0]] = temp[1];
    }
    return query;
}
=======
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
>>>>>>> branch 'master' of https://github.com/eCoreGo/Team-Building.git
