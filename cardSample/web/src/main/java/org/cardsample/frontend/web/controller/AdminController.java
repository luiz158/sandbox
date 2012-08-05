package org.cardsample.frontend.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * {@link Controller} that handles the requests for Admin pages.
 */
@Controller
@RequestMapping(value = "/admin")
public final class AdminController {

    /**
     * Handle the request for editing a user profile.
     * <p/>
     * <b>Security Information: </b>This operation requires the {@link Privileges#ROLE_ADMIN}
     * 
     * @param id
     *            of the user to edit
     * @param model
     *            springframework {@link Model} object
     * 
     * @return the edit_user jsp view
     */
    @RequestMapping(value = "/edit-user", method = RequestMethod.GET, params = "id")
    @Secured({ "ROLE_ADMIN" })
    public String editUser(@RequestParam("id") final String id, final Model model) {
        model.addAttribute("id", id);

        return "/admin/edit_user";
    }
}
