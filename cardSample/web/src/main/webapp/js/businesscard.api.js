var BusinessCardApi = {

	/**
	 * Find a page of resources.
	 * @param page the page number.
	 * @param pageSize the items per page.
	 */
	findAllPaged : function(page, pageSize, successHandler, errorHandler) {
		var serverURL = businessCardUrl() + "/api/businesscard?page=" + page + "&size=" + pageSize;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	},

	/**
	 * Find a resource by id.
	 * @param id the user id.
	 */
	findOneById : function(id, successHandler, errorHandler) {
		var serverURL = businessCardUrl() + "/api/businesscard/" + id;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	}

};