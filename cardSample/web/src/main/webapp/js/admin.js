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
			"sDom" : 'T<"clear">lfrtip',
			"oTableTools" : {
				"sRowSelect" : "single"
			},
			// bJQueryUI : true,
			bFilter : false,
			bInfo : false,
			// bAutoWidth : true,
			bPaginate : false
		});

		initializeBusinessCardTable(businesscardtable);

		var businessCardSuccessHandler = function(data, textStatus, xhr) {
			if (!data || data.length == 0) {
				// if there are no data, then don't change table and page number
				page--;
				$("#nextPage").parent().addClass("disabled");
			} else {
				var cards = Admin._toBusinessCards(data);
				if (cards.length < pageSize) {
					$("#nextPage").parent().addClass("disabled");
				} else {
					$("#nextPage").parent().removeClass("disabled");
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
				$("#prevPage").parent().addClass("disabled");
			} else {
				$("#prevPage").parent().removeClass("disabled");
			}
		};
		var businessCardErrorHandler = function(xhr, textStatus) {
			showErrorNotification(loadErrMsg);
			page--;
			if (page == 0) {
				$("#prevPage").parent().addClass("disabled");
			} else {
				$("#prevPage").parent().removeClass("disabled");
			}
		};

		// pagination
		$('#prevPage').click(function() {
			if (page > 0) {
				BusinessCardApi.findAllPaged(--page, pageSize, businessCardSuccessHandler, businessCardErrorHandler);
			}
		});
		$('#nextPage').click(function() {
			BusinessCardApi.findAllPaged(++page, pageSize, businessCardSuccessHandler, businessCardErrorHandler);
		});

		// load first page
		BusinessCardApi.findAllPaged(page, pageSize, businessCardSuccessHandler, businessCardErrorHandler);
	},

	// convertors

	_toBusinessCards : function(data) {
		if ($.isArray(data)) {
			return $.map(data, Admin._toBusinessCard);
		}

		// if single user is found and convert an array with 1 element
		return [ Admin._toBusinessCard(data) ];
	},

	_toBusinessCard : function(elem) {
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
