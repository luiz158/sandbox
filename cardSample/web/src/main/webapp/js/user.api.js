
var UserApi = {

	/**
	 * Find a page of users.
	 * @param page the page number.
	 * @param pageSize the items per page.
	 */
	findUsers : function (page, pageSize, successHandler, errorHandler) {
		var serverURL = secUrl() + "/api/user/?page=" + page + "&size=" + pageSize;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	},

	/**
	 * Find a user by id.
	 * @param id the user id.
	 */
	findById : function (id, successHandler, errorHandler) {
		var serverURL = secUrl() + "/api/user/" + id;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler);
	} 
};