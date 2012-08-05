var page = 0;
var pageSize = 5;

var Admin = {
	// page handlers

	loadPage : function() {

		var table = $('#businesscardtable').dataTable({
			aoColumns : [
				{ sTitle : "Id", bSearchable : false, bSortable : false },
				{ sTitle : "Name", bSearchable : false, bSortable : false }
			],
			bJQueryUI : true,
			bFilter : false,
			bInfo : false,
			bAutoWidth : true,
			bPaginate : false
		});
		
		var successHandler = function(data, textStatus, xhr) {
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
					table.fnAddData([ elem.id, elem.name ]);
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
		var errorHandler = function(xhr, textStatus) {
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
				BusinessCardApi.findAllPaged(--page, pageSize, successHandler, errorHandler);
			}
		});
		$('#nextPage').click(function() {
			BusinessCardApi.findAllPaged(++page, pageSize, successHandler, errorHandler);
		});

		// load first page
		BusinessCardApi.findAllPaged(page, pageSize, successHandler, errorHandler);
	},

	// helpers

	_toBusinessCards : function(data) {
		if ($.isArray(data)) {
			return $.map(data, Admin._toBusinessCard);
		}

		// if single user is found and convert an array with 1 element
		return [ Admin._toBusinessCard(data) ];
	},

	_toBusinessCard : function(elem) {
		return {id : elem.id, name : elem.name};
	}

};
