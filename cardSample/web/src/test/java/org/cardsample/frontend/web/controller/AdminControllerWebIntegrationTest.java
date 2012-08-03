package org.cardsample.frontend.web.controller;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.cardsample.frontend.spring.web.WebTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;

/**
 * Tests for {@link org.cardsample.security.web.controller.AdminController}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebTestConfig.class })
public class AdminControllerWebIntegrationTest {

    @Autowired
    AdminController adminController;

    // tests

    // ---- edit user

    @Test
    public void whenAccountVerification_thenViewIsCorrect() {
        // when
        final String view = adminController.editUser(randomNumeric(4), new ExtendedModelMap());

        // then
        assertThat(view, is("/admin/edit_user"));
    }

    @Test
    public void whenAccountVerification_thenModelHasIdParam() {
        // when
        final ExtendedModelMap model = new ExtendedModelMap();
        final String id = randomNumeric(4);
        adminController.editUser(id, model);

        // then
        assertThat((String) model.get("id"), is(id));
    }

}
