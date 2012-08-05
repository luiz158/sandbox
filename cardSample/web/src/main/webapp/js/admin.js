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

		// I. SUCCESS AND FAILURE HANDLES

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

		var clientCardSuccessHandler = function(data, textStatus, xhr) {
			if (!data || data.length == 0) {
				// if there are no data, then don't change table and page number
			} else {
				var cards = Admin._toCards(data);

				$('#clientcardtable').dataTable().fnClearTable();

				// add new data to the grid
				$.each(cards, function(index, elem) {
					clientcardtable.fnAddData([ elem.id, elem.name ]);
				});

				// change info - a page number
				$("#currentPage").text(page + 1);
			}
		};
		var clientCardErrorHandler = function(xhr, textStatus) {
			showErrorNotification(loadErrMsg);
		};

		// ii. INITS

		initializeBusinessCardTable(businesscardtable, clientcardtable, clientCardSuccessHandler, clientCardErrorHandler);
		initializeClientCardTable(clientcardtable, businesscardtable, businessCardSuccessHandler, businessCardErrorHandler);

		BusinessCardApi.findAllPaged(page, pageSize, businessCardSuccessHandler, businessCardErrorHandler);
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

function initializeBusinessCardTable(bTable, cTable, cSucc, cFail) {
	$("#businesscardtable tbody").click(function(event) {
		$(bTable.fnSettings().aoData).each(function() {
			$(this.nTr).removeClass('row_selected');
		});
		$(event.target.parentNode).addClass('row_selected');
	});

	/* Add a click handler for the delete row */
	$('#businesscardtable tbody').click(function() {
		$('#clientcardtable').dataTable().fnClearTable();

		var anSelected = rowClicked(bTable);
		var id = anSelected[0];
		ClientCardApi.findAllByAssociation(id, cSucc, cFail);
	});
}

function initializeClientCardTable(cTable, bTable, bSucc, bFail) {
	$("#clientcardtable tbody").click(function(event) {
		$(cTable.fnSettings().aoData).each(function() {
			$(this.nTr).removeClass('row_selected');
		});
		$(event.target.parentNode).addClass('row_selected');
	});

	/* Add a click handler for the delete row */
	$('#clientcardtable tbody').click(function() {
		var anSelected = rowClicked(cTable);
		if (anSelected.length !== 0) {
			cTable.fnDeleteRow(anSelected[0]);
		}
	});
}
