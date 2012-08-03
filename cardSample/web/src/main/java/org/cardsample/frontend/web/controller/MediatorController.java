package org.cardsample.frontend.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Generic {@link Controller} that handles the most basic requests, like the
 * entry page (/index), and some other that doesn't have a specific controller
 */
@Controller
public final class MediatorController {

	public MediatorController() {
		super();
	}

	/**
	 * Handles the first request to the page to retrieve the first page
	 * 
	 * @return the appropriate jsp view based on the role of the authenticated
	 *         user (if there is one) or the default view (index)
	 */
	@RequestMapping(value = "/index")
	public String home() {
		return "/admin/admin_console";
	}

	// --- Account Verification

	/**
	 * Handles the request to verify the account recently created, using the
	 * link that was sent by email and that contains a token that will be used
	 * in this verification process
	 * 
	 * @param id
	 *            the value of the verification token used to verify the account
	 * @param model
	 *            {@link Model} springframework object
	 * 
	 * @return the account_verification jsp view
	 */
	@RequestMapping(value = "/account-verification", method = RequestMethod.GET, params = "id")
	public String accountVerification(@RequestParam("id") final String id,
			final Model model) {
		model.addAttribute("id", id);

		return "account_verification";
	}

}
