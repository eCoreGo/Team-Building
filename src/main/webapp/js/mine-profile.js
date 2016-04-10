
var userId;
$(document).on("pageshow", function() {
	userId = findParameterValue("userId");
	
	var activityLinkUrl = $("#activityLink").attr("href");
	var groupLinkUrl = $("#groupLink").attr("href");
	var mineLinkUrl = $("#mineLink").attr("href");
    $("#activityLink").attr("href", activityLinkUrl + "?userId=" + userId);
    $("#groupLink").attr("href", groupLinkUrl + "?userId=" + userId);
    $("#mineLink").attr("href", mineLinkUrl + "?userId=" + userId);
    
	var teamsListDiv;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            id: userId
        },
        url:"service/Member/getMemberById",
        success: function(result) {
            $("#user-name").val(result.name);
            $("#phone").val(result.phone);
        },
        complete: function(result) {

        }
    });
    
    $("#save").on("click", function() {
		saveBasicInfo(userId);   	
	});

	getAllTeams(teamsListDiv,userId);

});
function getAllTeams(teamsListDiv,userID) {
	/**
	 * Get all teams , and set toggle button to .teams-container
	 */
	$.ajax({
		type: "POST",
		dataType: "json",
		url:"service/Team/getAllTeams",
		success: function(result) {
			teamsListDiv = $(".teams-list .ui-controlgroup-controls");
			$(result).each(function(index){
				var checkId = this.id;
				var checkLabel = this.name;
				$('<input type="checkbox" name="checkbox-'+checkId+'" ' +
				'id="checkbox-'+checkId+'" class="teams-inputs" value="'+checkId+'">' +
				'<label for="checkbox-'+checkId+'">'+checkLabel+'</label>').appendTo(teamsListDiv);
			});
			getCheckedTeams(teamsListDiv,userID);
			
		},
		complete: function(result) {
			teamsListDiv.enhanceWithin().controlgroup("refresh");
        }
	});
	
	
}

function getCheckedTeams(teamsListDiv,userID) {
	$.ajax({
		type: "POST",
		dataType: "json",
		data: {
            memberId: userID
        },
		url:"service/TeamMember/getTeams",
		success: function(result) {
			$(result).each(function(index){
				aa = $(".teams-list .ui-controlgroup-controls");
				var checkId = this.id;
				$("#checkbox-"+checkId).prop("checked",true).checkboxradio('refresh');
			});
			
			updateTeam(userID);
		},
		complete: function(result) {
			//teamsListDiv.enhanceWithin().controlgroup("refresh");
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
        	complete: function(result) {
        		alert("修改成功：" + $("#user-name").val() + ", " +$("#phone").val());
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
    			if (balance != parseFloat(0)) {
    				alert("别离开 : " + teamName + " . 因为你还有余额...详情请咨询管理员吧.");
    				$("#checkbox-"+teamID).prop("checked",true).checkboxradio('refresh');
    			} else {
    				deleteTeamMember(teamID, userID, teamName);
    			}
		},
		complete: function(result) {
		}
	});
}

function addTeamMember(teamId, memberId, teamName) {
		$.ajax({
       	 		type: "POST",
       	 		dataType: "json",
        		data: {
            		teamId: teamId,
            		memberId: memberId,
            		balance: 0.00,
            		Date: new Date()
            		
        		},
        		url:"service/TeamMember/join",
        		complete: function(result) {
        			$("#checkbox-"+teamId).prop("checked",true).checkboxradio('refresh');
           	 		alert("成功入会： " + teamName );
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
        		complete: function(result) {
        			alert("绿水长流 下次再见：" + teamName);
        		}
    		});
}
