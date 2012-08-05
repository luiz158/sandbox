package org.cardsample.frontend.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Generic {@link Controller} that handles the most basic requests, like the entry page (/index), and some other that doesn't have a specific controller
 */
@Controller
public final class MediatorController {

    /**
     * Handles the first request to the page to retrieve the first page
     * 
     * @return the appropriate jsp view based on the role of the authenticated user (if there is one) or the default view (index)
     */
    @RequestMapping(value = "/index")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/")
    public String root() {
        return "index";
    }

}
