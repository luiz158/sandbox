function handleAjaxRequest(type, url, data, overrideSuccessHandler, overrideErrorHandler, overrideBeforeSendHandler, timeout) {
	$.ajax({
		type : type,
		url : url,
		data : data,
		contentType : "application/json",
		dataType : "json",
		timeout : timeout ? timeout : 20000,
		beforeSend : function(xhr) {
			if (overrideBeforeSendHandler) {
				overrideBeforeSendHandler(xhr);
			}
		},
		success : function(msg, textStatus, xhr) {
			hideNotification();
			if (overrideSuccessHandler) {
				overrideSuccessHandler(msg, textStatus, xhr);
			} else {
				defaultSuccessHandler(msg, textStatus, xhr);
			}

		},
		error : function(xhr, textStatus) {
			if (overrideErrorHandler) {
				overrideErrorHandler(xhr, textStatus);
			} else {
				defaultErrorHandler(xhr, textStatus);
			}
		}
	});
}

function handleSimpleAjaxRequest(type, url, data, overrideSuccessHandler, overrideErrorHandler, overrideBeforeSendHandler) {
	$.ajax({
		type : type,
		url : url,
		data : data,
		timeout : 10000,
		beforeSend : function(xhr) {
			if (overrideBeforeSendHandler) {
				overrideBeforeSendHandler(xhr);
			}
		},
		success : function(msg, textStatus, xhr) {
			if (overrideSuccessHandler) {
				overrideSuccessHandler(msg, textStatus, xhr);
			} else {
				defaultSuccessHandler(msg, textStatus, xhr);
			}

		},
		error : function(xhr, textStatus) {
			if (overrideErrorHandler) {
				overrideErrorHandler(xhr, textStatus);
			} else {
				defaultErrorHandler(xhr, textStatus);
			}
		}
	});
}

function defaultSuccessHandler(msg, textStatus, xhr) {
	if (xhr.status == 201) {
		showSuccessNotification(createdMsg);
	}
}

function defaultErrorHandler(xhr, textStatus) {
	if (xhr.status == 409) {
		showErrorNotification(existsMsg);
	}
	else {
		showErrorNotification(opErrMsg);
	}
}
