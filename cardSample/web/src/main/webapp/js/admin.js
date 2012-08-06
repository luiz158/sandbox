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
			} else {
				var cards = Admin._toCards(data);

				$('#businesscardtable').dataTable().fnClearTable();

				// add new data to the grid
				$.each(cards, function(index, elem) {
					businesscardtable.fnAddData([ elem.id, elem.name ]);
				});
			}
		};
		var businessCardErrorHandler = function(xhr, textStatus) {
			showErrorNotification(loadErrMsg);
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
		
		var notifySuccess = function(data, textStatus, xhr) {
			alert('text');
		};
		
		// ii. INITS

		initializeBusinessCardTable(businesscardtable, clientCardSuccessHandler, clientCardErrorHandler);
		initializeClientCardTable(clientcardtable, businessCardSuccessHandler, businessCardErrorHandler);

		BusinessCardApi.findAllPaged(page, pageSize, businessCardSuccessHandler, businessCardErrorHandler);
		ClientCardApi.findAllPaged(page, pageSize, clientCardSuccessHandler, clientCardErrorHandler);

		$("#addClientToBusiness").click(function(event) {
			BusinessCardApi.notify(notifySuccess);
		});
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

function initializeBusinessCardTable(bTable, cSucc, cFail) {
	$("#businesscardtable tbody").click(function(event) {
		$(bTable.fnSettings().aoData).each(function() {
			$(this.nTr).removeClass('row_selected');
		});
		$(event.target.parentNode).addClass('row_selected');
	});

	$("#businesscardtable tbody").delegate("tr", "click", function() {
		$('#clientcardtable').dataTable().fnClearTable();

		var id = $("td:first", this).text();
		ClientCardApi.findAllByAssociation(id, cSucc, cFail);
	});

}

function initializeClientCardTable(cTable, bSucc, bFail) {
	$("#clientcardtable tbody").click(function(event) {
		$(cTable.fnSettings().aoData).each(function() {
			$(this.nTr).removeClass('row_selected');
		});
		$(event.target.parentNode).addClass('row_selected');
	});

	$("#clientcardtable tbody").delegate("tr", "click", function() {
		$('#businesscardtable').dataTable().fnClearTable();

		var id = $("td:first", this).text();
		BusinessCardApi.findAllByAssociation(id, bSucc, bFail);
	});
}
