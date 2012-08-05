
var BusinessCardApi = {

	/**
	 * Find a page of resources.
	 * @param page the page number.
	 * @param pageSize the items per page.
	 */
	findUsers : function (page, pageSize, successHandler, errorHandler) {
		var serverURL = businessCardUrl() + "/api/user/?page=" + page + "&size=" + pageSize;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	},

	/**
	 * Find a resource by id.
	 * @param id the user id.
	 */
	findById : function (id, successHandler, errorHandler) {
		var serverURL = secUrl() + "/api/user/" + id;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	},

	/**
	 * Find resource by email.
	 * @param email the email address.
	 */
	findUserByEmail : function (email, successHandler, errorHandler) {
		var serverURL = secUrl() + "/api/user/?email=" + encodeURIComponent(email);
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	}
};