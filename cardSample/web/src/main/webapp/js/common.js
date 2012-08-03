$(document).ready(function () {

	// ---- Setup defaults for jQuery Validator plugin
	if (jQuery.validator) {
		jQuery.validator.setDefaults({
			errorClass : 'help-inline',
			highlight : function (element, errorClass) {
				$(element).closest(".control-group").addClass("error");
			},
			unhighlight : function (element) {
				$(element).closest(".control-group").removeClass("error");
			}
		});
	}

	// ----- blockUI defaults

	// styling
	if (jQuery.blockUI) {
		$.blockUI.defaults.message = "<h1>"+waitMsg+"</h1>";
		$.blockUI.defaults.css.border = 'none';
		$.blockUI.defaults.css.padding = '15px';
		$.blockUI.defaults.css.backgroundColor = '#000';
		$.blockUI.defaults.css.opacity = 0.5;
		$.blockUI.defaults.css.color = "#fff";
		$.blockUI.defaults.css["-webkit-border-radius"] = '10px';
		$.blockUI.defaults.css["-moz-border-radius"] = '10px';

		$.blockUI.defaults.fadeIn = 0;
		$.blockUI.defaults.fadeOut = 0;
	}

	// Add a notification message wrapper
	$('body').prepend('<div class="notification"><div class="alert alert-error" style="display: none;"></div></div>');

	// show incompatibility message for IE9
	if ($('#showBrowserIncompatibilityWarn').length > 0) {
		if ($.browser.msie && $.browser.version == "9.0") {
			$('#showBrowserIncompatibilityWarn').show();
		}
	}
});

function beforeSendHandler(xhr, authorizationKey) {
	// use basic authorization
	xhr.setRequestHeader("Authorization", "Basic " + authorizationKey);
}

function loadImage(imageLink, onload, onerror) {
	var image = new Image();
	if (onload) {
		image.onload = onload;
	}
	if (onerror) {
		image.onerror = onerror;
	}
	image.src = imageLink;
}

// ---------------------------------------------- Notifications

var hideNotificationTimeout;
var userDetails;

function showNotification(msg, styleClass, timeout) {
	hideNotification();

	$(".notification .alert")
		.removeClass("alert-error")
		.removeClass("alert-success")
		.removeClass("alert-block")
		.removeClass("alert-info")
		.addClass(styleClass)
		.text(msg).fadeIn();

	$(".notification").center(false);

	hideNotificationTimeout = setTimeout(function() {
		$(".notification .alert").fadeOut();
	}, timeout);
}

function hideNotification() {
	clearTimeout(hideNotificationTimeout);

	if ($(".notification .alert").is(":visible")) {
		$(".notification .alert").hide();
	}
}

function showErrorNotification(msg) {
	showNotification(msg, "alert-error", 10000);
}

function showSuccessNotification(msg) {
	showNotification(msg, "alert-success", 5000);
}

function showWarningNotification(msg) {
	showNotification(msg, "alert-block", 8000);
}

function showInfoNotification(msg, timeout) {
	timeout = timeout ? timeout : 5000;
	showNotification(msg, "alert-info", timeout);
}

// ---------------------------------------------- Registration

function doRegistration() {
	var email = $('#registration_email').val();
	var password = $('#registration_password').val();
	var usertype = $('#registration_usertype').val();

	var url = hostUrl() + "/api/registration/owner";
	if (usertype == 'enduser') {
		url = hostUrl() + "/api/registration/enduser";
	}

	var data = '{"password":"' + password + '","email":"' + email + '"}';

	showInfoNotification(regWaitMsg);
	$('#registerBtn').attr("disabled", "disabled");
	handleAjaxRequest('POST', url, data, registrationSuccessHandler, registrationFailedHandler);
}

function registrationSuccessHandler(msg, textStatus, xhr) {
	if (xhr.status == 201) {
		showInfoNotification(regSuccesMsg);
		window.location.href = 'login.html?registered=true';
	}
	else {
		$('#registerBtn').removeAttr("disabled");
	}
}

function registrationFailedHandler(xhr) {
	if (xhr.status == 409) {
		var error = xhr.statusText;
		if (error == "duplicate.email" || error == "DuplicateOwner" || error == "DuplicateEndUser") {
			showErrorNotification(regUserExistMsg);
		}
		else {
			showErrorNotification(regFailedMsg + error);
		}
	}
	$('#registerBtn').removeAttr("disabled");
}

// ------------------------------------------------- Reset Password

function doResetPassword() {
	showInfoNotification(passResetWaitMsg);
	$("#alert").text("").hide();

	var data = {email: $('#email').val()};
	var url = secUrl() + "/api/passwordResetToken/";

	$('#resetPasswordBtn').attr("disabled", "disabled");
	handleSimpleAjaxRequest('POST', url, data, passwordResetSuccessHandler, passwordResetFailedHandler);
}

function passwordResetSuccessHandler(msg, textStatus, xhr) {
	if (xhr.status == 201) {
		hideNotification();

		$('#alert')
			.removeClass("alert-success alert-error")
			.addClass("alert-success")
			.text(passResetInfoMsg)
			.show();

		$("#email").val("");
	}

	$('#resetPasswordBtn').removeAttr("disabled");
}

function passwordResetFailedHandler(xhr) {
	hideNotification();

	if (xhr.status == 409) {
		var error = xhr.statusText;
		$('#alert')
			.removeClass("alert-success alert-error")
			.addClass("alert-error")
			.text(passResetErrMsg + error)
			.show();
	}
	else if (xhr.status == 404) {
		// email is not found
		$('#alert')
			.removeClass("alert-success, alert-error")
			.addClass("alert-error")
			.text(userNotFoundMsg)
			.show();
	}

	$('#resetPasswordBtn').removeAttr("disabled");
}

// ------------------------------------------------------
// Custom jQuery plugins
// ------------------------------------------------------

jQuery.fn.center = function(vertical) {
	this.css("left", (($(window).width() - this.outerWidth()) / 2) + $(window).scrollLeft() + "px");
	if (vertical) {
		this.css("top", (($(window).height() - this.outerHeight()) / 2) + $(window).scrollTop() + "px");
	}
	return this;
}

//------------------------------------------------------
// Load the User details - For profile page
//------------------------------------------------------

function loadUserDetails(authorizationKey) {
	var serverURL = secUrl() + "/api/user/current";
	handleAjaxRequest('GET', serverURL, null, displayUserDetails, null, beforeSendHandler, authorizationKey);
}

function displayUserDetails(data, textStatus, xhr) {
	var encoded = $.toJSON(data);

	if (encoded == 'null') {
		alert(nullMsg);
		return;
	}

	userDetails = jQuery.parseJSON(encoded);

	var id = userDetails.id;
	var suspended = userDetails.suspended;
	var email = userDetails.email;

	$('#emailId').text(email);
}

//------------------------------------------------------
//Update password - For profile page
//------------------------------------------------------

function updatePassword(authorizationKey) {
	//old password
	var password = $('#password').val();
	
	//new password
	var password2 = $('#password2').val();
	var url = secUrl() + "/api/principal";

	userDetails.credential = password2;
	userDetails.oldCredential = password;

	var data = JSON.stringify(userDetails);

	// The current password entered by the user in the profile page is used to authenticate
	handleAjaxRequest('PUT', url, data, updatePasswordSuccessHandler, updatePasswordFailedHandler, beforeSendHandler, authorizationKey);
}

function updatePasswordFailedHandler(xhr, textStatus) {
	if (xhr.statusText) {
		showErrorNotification(xhr.statusText);
	} else {
		showErrorNotification(passUpdateErrMsg);
	}
}

function updatePasswordSuccessHandler(msg, textStatus, xhr) {
	var $dialog = $('<div></div>')
	.html(passUpdateSuccesMsg)
	.dialog({
		autoOpen: false,
		modal: true,
		title: 'Success',
		close: function(event, ui) { window.parent.location.href='/geogrep-client/j_spring_security_logout'; }
	});

	$dialog.dialog('open');
}