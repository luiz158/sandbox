var page = 0;
var pageSize = 5;

var Admin = {
	// page handlers

	loadPage : function() {

		var businesscardtable = $('#businesscardtable').dataTable({
			aoColumns : [ {
				sTitle : "Id",
				bSearchable : false,
				bSortable : false
			}, {
				sTitle : "Name",
				bSearchable : false,
				bSortable : false
			} ],
			// bJQueryUI : true,
			bFilter : false,
			bInfo : false,
			bAutoWidth : true,
			bPaginate : false
		});
		initializeBusinessCardTable(businesscardtable);

		var businessCardSuccessHandler = function(data, textStatus, xhr) {
			if (!data || data.length == 0) {
				// if there are no data, then don't change table and page number
				page--;
				$("#nextPageBC").parent().addClass("disabled");
			} else {
				var cards = Admin._toCards(data);
				if (cards.length < pageSize) {
					$("#nextPageBC").parent().addClass("disabled");
				} else {
					$("#nextPageBC").parent().removeClass("disabled");
				}

				$('#businesscardtable').dataTable().fnClearTable();

				// add new data to the grid
				$.each(cards, function(index, elem) {
					businesscardtable.fnAddData([ elem.id, elem.name ]);
				});

				// change info - a page number
				$("#currentPage").text(page + 1);
			}

			if (page == 0) {
				$("#prevPageBC").parent().addClass("disabled");
			} else {
				$("#prevPageBC").parent().removeClass("disabled");
			}
		};
		var businessCardErrorHandler = function(xhr, textStatus) {
			showErrorNotification(loadErrMsg);
			page--;
			if (page == 0) {
				$("#prevPageBC").parent().addClass("disabled");
			} else {
				$("#prevPageBC").parent().removeClass("disabled");
			}
		};
		
		// pagination
		$('#prevPageBC').click(function() {
			if (page > 0) {
				BusinessCardApi.findAllPaged(--page, pageSize, businessCardSuccessHandler, businessCardErrorHandler);
			}
		});
		$('#nextPageBC').click(function() {
			BusinessCardApi.findAllPaged(++page, pageSize, businessCardSuccessHandler, businessCardErrorHandler);
		});
		
		// load first page
		BusinessCardApi.findAllPaged(page, pageSize, businessCardSuccessHandler, businessCardErrorHandler);
		
		
		var clientcardtable = $('#clientcardtable').dataTable({
			aoColumns : [ {
				sTitle : "Id",
				bSearchable : false,
				bSortable : false
			}, {
				sTitle : "Name",
				bSearchable : false,
				bSortable : false
			} ],
			// bJQueryUI : true,
			bFilter : false,
			bInfo : false,
			bAutoWidth : true,
			bPaginate : false
		});
		initializeClientCardTable(clientcardtable);

		var clientCardSuccessHandler = function(data, textStatus, xhr) {
			if (!data || data.length == 0) {
				// if there are no data, then don't change table and page number
				page--;
				$("#nextPageCC").parent().addClass("disabled");
			} else {
				var cards = Admin._toCards(data);
				if (cards.length < pageSize) {
					$("#nextPageCC").parent().addClass("disabled");
				} else {
					$("#nextPageCC").parent().removeClass("disabled");
				}

				$('#clientcardtable').dataTable().fnClearTable();

				// add new data to the grid
				$.each(cards, function(index, elem) {
					clientcardtable.fnAddData([ elem.id, elem.name ]);
				});

				// change info - a page number
				$("#currentPage").text(page + 1);
			}

			if (page == 0) {
				$("#prevPageCC").parent().addClass("disabled");
			} else {
				$("#prevPageCC").parent().removeClass("disabled");
			}
		};
		var clientCardErrorHandler = function(xhr, textStatus) {
			showErrorNotification(loadErrMsg);
			page--;
			if (page == 0) {
				$("#prevPageCC").parent().addClass("disabled");
			} else {
				$("#prevPageCC").parent().removeClass("disabled");
			}
		};

		$('#prevPageCC').click(function() {
			if (page > 0) {
				ClientCardApi.findAllPaged(--page, pageSize, clientCardSuccessHandler, clientCardErrorHandler);
			}
		});
		$('#nextPageCC').click(function() {
			ClientCardApi.findAllPaged(++page, pageSize, clientCardSuccessHandler, clientCardErrorHandler);
		});
		
		// load first page
		ClientCardApi.findAllPaged(page, pageSize, clientCardSuccessHandler, clientCardErrorHandler);
	},

	// convertors

	_toCards : function(data) {
		if ($.isArray(data)) {
			return $.map(data, Admin._toCard);
		}

		// if single user is found and convert an array with 1 element
		return [ Admin._toCard(data) ];
	},

	_toCard : function(elem) {
		return {
			id : elem.id,
			name : elem.name
		};
	}
};

function rowClicked(oTableLocal) {
	return oTableLocal.$('tr.row_selected');
}

function initializeBusinessCardTable(oTableLocal) {
	$("#businesscardtable tbody").click(function(event) {
		$(oTableLocal.fnSettings().aoData).each(function() {
			$(this.nTr).removeClass('row_selected');
		});
		$(event.target.parentNode).addClass('row_selected');
	});

	/* Add a click handler for the delete row */
	$('#businesscardtable tbody').click(function() {
		var anSelected = rowClicked(oTableLocal);
		if (anSelected.length !== 0) {
			oTableLocal.fnDeleteRow(anSelected[0]);
		}
	});
}

function initializeClientCardTable(oTableLocal) {
	$("#clientcardtable tbody").click(function(event) {
		$(oTableLocal.fnSettings().aoData).each(function() {
			$(this.nTr).removeClass('row_selected');
		});
		$(event.target.parentNode).addClass('row_selected');
	});

	/* Add a click handler for the delete row */
	$('#clientcardtable tbody').click(function() {
		var anSelected = rowClicked(oTableLocal);
		if (anSelected.length !== 0) {
			oTableLocal.fnDeleteRow(anSelected[0]);
		}
	});
}
