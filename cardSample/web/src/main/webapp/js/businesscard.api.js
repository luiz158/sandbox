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
	},

	/**
	 * Find all resources of association
	 */
	findAllByAssociation : function(idOfAssociation, successHandler, errorHandler) {
		var serverURL = businessCardUrl() + "/api/businesscard/" + ididOfAssociation;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	}

};

var ClientCardApi = {

	/**
	 * Find a page of resources.
	 * @param page the page number.
	 * @param pageSize the items per page.
	 */
	findAllPaged : function(page, pageSize, successHandler, errorHandler) {
		var serverURL = businessCardUrl() + "/api/clientcard?page=" + page + "&size=" + pageSize;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	},

	/**
	 * Find a resource by id.
	 * @param id the user id.
	 */
	findOneById : function(id, successHandler, errorHandler) {
		var serverURL = businessCardUrl() + "/api/clientcard/" + id;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	}, 
	
	/**
	 * Find all resources of association
	 */
	findAllByAssociation : function(idOfAssociation, successHandler, errorHandler) {
		var serverURL = businessCardUrl() + "/api/clientcard/" + ididOfAssociation;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	}

};