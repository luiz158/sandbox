var page = 0;
var pageSize = 5;

var Admin = {

	// page handlers

	loadUsersPage : function (userEditLinkTmpl) {
		$('#reindexLocations').click(function() {
			$.blockUI({message: "<h3>" + reindexBlockMsg + "</h3>"});
			LocationApi.reindex(function() {
				$.unblockUI();
			}, function () {
				$.unblockUI();
			});
		});

		var successHandler = function (data, textStatus, xhr) {
			if (!data || data.length == 0) {
				// if there are no data, then don't change table and page number
				page--;
				$("#nextPage").parent().addClass("disabled");
			}
			else {
				var users = Admin._toUsers(data);
				if (users.length < pageSize) {
					$("#nextPage").parent().addClass("disabled");
				}
				else {
					$("#nextPage").parent().removeClass("disabled");
				}
				table.fnClearTable();

				// add new data to the grid
				$.each(users, function (index, elem) {
					table.fnAddData([elem.email, elem.userType, elem.enabled ? "Yes" : "No", elem.id]);
				});

				// change info - a page number
				$("#currentPage").text(page+1);
			}

			if (page == 0) {
				$("#prevPage").parent().addClass("disabled");
			}
			else {
				$("#prevPage").parent().removeClass("disabled");
			}
		};
		var errorHandler = function (xhr, textStatus) {
			showErrorNotification(loadErrMsg);
			page--;
			if (page == 0) {
				$("#prevPage").parent().addClass("disabled");
			}
			else {
				$("#prevPage").parent().removeClass("disabled");
			}
		};

		var table = $('#datatable').dataTable({
			aoColumns : [
				{ sTitle : "Email", bSearchable : false, bSortable : false },
				{ sTitle : "User Type", bSearchable : false, bSortable : false },
				{ sTitle : "Enabled", bSearchable : false, bSortable : false },
				{ sTitle : "", bSearchable : false,
					bSortable : false, bUseRendered : false,
					fnRender : function (obj) {
						return Mustache.render(userEditLinkTmpl, {id : obj.aData[obj.iDataColumn]});
					}
				}
			],
			bJQueryUI : true,
			bLengthChange : false,
			bFilter : false,
			bInfo : false,
			bAutoWidth : true,
			bPaginate : false
		});

		// filtering
		var filter = $('#filterTmpl').html();
		$($('#datatable_wrapper div.ui-widget-header')[0]).append(filter);

		$('#filterForm').submit(function (e) {
			e.preventDefault();

			hideNotification();
			page = 0;
			var query = $('#filter').val();
			if (query) {
				$("#prevPage").parent().addClass("disabled");
				var searchErrorHandler = function (xhr) {
					if (xhr.status == 404) {
						showErrorNotification(notFoundMsg);
					}
					else {
						showErrorNotification(loadErrMsg);
					}
				};
				UserApi.findUserByEmail(query, successHandler, searchErrorHandler);
			}
			else {
				showWarningNotification(enterEmailMsg);
			}

			return false;
		});

		$('#clearSearch').click(function() {
			page = 0;
			$('#filter').val("");
			hideNotification();
			UserApi.findUsers(page, pageSize, successHandler, errorHandler);
		});

		// pagination
		$('#prevPage').click(function () {
			$('#filter').val("");
			if (page > 0) {
				UserApi.findUsers(--page, pageSize, successHandler, errorHandler);
			}
		});
		$('#nextPage').click(function () {
			$('#filter').val("");
			UserApi.findUsers(++page, pageSize, successHandler, errorHandler);
		});

		// load first page
		UserApi.findUsers(page, pageSize, successHandler, errorHandler);
	},

	editUserPage : function (id) {
		var displayUserDetails = function (json, textStatus, xhr) {
			var user = Admin._toUser(json);
			$('#email').text(user.email);
			$('#userType').text(user.userType);
			$('#enabled').text(user.enabled ? "Yes" : "No");

			$("#switchEmail").val(user.email);
			if (user.enabled) {
				$('#switchBtn').removeAttr("disabled");
			}
		};

		$('#switchBtn').click(function () {
			if (!$(this).attr("disabled")) {
				$('#switchForm').submit()
			}
		});

		UserApi.findById(id, displayUserDetails);
	},

	servicesStatusPage: function(services) {
		var template = $('#serviceTmpl').html();
		var addServiceRow = function(service, url, status, textStatus) {
			$('#services').append(Mustache.render(template, {service: service, url: url, status: status, textStatus: textStatus}));
		};
		for (var i in services) {
			var success = function() {
				var service = i;
				return function(data, textStatus, xhr) {
					addServiceRow(service, services[service], "success", xhr.status + ": " + xhr.statusText);
				}
			};
			var error = function() {
				var service = i;
				return function(xhr, textStatus) {
					addServiceRow(service, services[service], "failed", xhr.status + ": " + xhr.statusText);
				}
			};
			handleAjaxRequest('GET', services[i], null, success(), error(), beforeSendHandler);
		}
	},

	// helpers

	_toUsers : function (data) {
		if ($.isArray(data)) {
			return $.map(data, Admin._toUser);
		}

		// if single user is found and convert an array with 1 element
		return [Admin._toUser(data)];
	},

	_toUser : function (elem) {
		var type = "";
		for (i in elem.privileges) {
			var privilege = elem.privileges[i].name;
			if (privilege == "ROLE_ADMIN") {
				type = "Administrator";
				break
			}
			else if (privilege == "ROLE_OWNER") {
				type = "Owner";
				break
			}
			else if (privilege == "ROLE_ENDUSER") {
				type = "Enduser";
				break
			}
		}

		return {id : elem.id, email : elem.email, userType : type, enabled : !elem.suspended};
	}

};
