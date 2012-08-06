var BusinessCardApi = {
	root : '/api/businesscard',
	
	/**
	 * Update the resource.
	 * @param location location to update.
	 */
	notify : function (successHandler, errorHandler) {
		var serverURL = cardUrl() + BusinessCardApi.root + "/notify";
		handleAjaxRequest('POST', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	},
	
	/**
	 * Update the resource.
	 * @param location location to update.
	 */
	update : function (location, successHandler, errorHandler) {
		var serverURL = cardUrl() + BusinessCardApi.root;
		handleAjaxRequest('PUT', serverURL, JSON.stringify(location), successHandler, errorHandler, beforeSendHandler);
	},

	/**
	 * Find a page of resources.
	 * @param page the page number.
	 * @param pageSize the items per page.
	 */
	findAllPaged : function(page, pageSize, successHandler, errorHandler) {
		var serverURL = cardUrl() + BusinessCardApi.root + "?page=" + page + "&size=" + pageSize;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	},

	/**
	 * Find a resource by id.
	 * @param id the user id.
	 */
	findOneById : function(id, successHandler, errorHandler) {
		var serverURL = cardUrl() + BusinessCardApi.root + "/" + id;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	},

	/**
	 * Find all resources of association
	 */
	findAllByAssociation : function(idOfAssociation, successHandler, errorHandler) {
		var serverURL = cardUrl() + BusinessCardApi.root + "/clientcard/" + ididOfAssociation;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	}

};

var ClientCardApi = {
	root : '/api/clientcard',

	/**
	 * Create the location.
	 * @param location location to create.
	 */
	create : function(resource, successHandler, errorHandler) {
		var serverURL = cardUrl() + ClientCardApi.root;
		handleAjaxRequest('POST', serverURL, JSON.stringify(resource), successHandler, errorHandler, beforeSendHandler);
	},
	/**
	 * Find a page of resources.
	 * @param page the page number.
	 * @param pageSize the items per page.
	 */
	findAllPaged : function(page, pageSize, successHandler, errorHandler) {
		var serverURL = cardUrl() + ClientCardApi.root + "?page=" + page + "&size=" + pageSize;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	},

	/**
	 * Find a resource by id.
	 * @param id the user id.
	 */
	findOneById : function(id, successHandler, errorHandler) {
		var serverURL = cardUrl() + ClientCardApi.root + "/" + id;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	},

	/**
	 * Find all resources of association
	 */
	findAllByAssociation : function(idOfAssociation, successHandler, errorHandler) {
		var serverURL = cardUrl() + ClientCardApi.root + "/businesscard/" + idOfAssociation;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	}

};