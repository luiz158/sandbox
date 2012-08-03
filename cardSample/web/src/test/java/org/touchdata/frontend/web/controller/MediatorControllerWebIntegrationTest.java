package org.touchdata.frontend.web.controller;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.touchdata.frontend.spring.web.WebTestConfig;
import org.touchdata.frontend.testing.security.SecurityClientTestUtil;

/**
 * Tests for {@link MediatorController}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebTestConfig.class })
public class MediatorControllerWebIntegrationTest {

    @Autowired
    MediatorController mediatorController;

    // tests

    // ---- home

    @Test
    public void givenAnonymous_whenRequestHomePage_thenIndexPageReturned() {
        // given
        SecurityClientTestUtil.runAsAnonymous();

        // when
        final String view = mediatorController.home();

        // then
        assertThat(view, is("index"));
    }

    @Test
    public void givenEnduser_whenRequestHomePage_thenEnduserPageReturned() {
        // given
        SecurityClientTestUtil.runAsEnduser();

        // when
        final String view = mediatorController.home();

        // then
        assertThat(view, is("enduser"));
    }

    @Test
    public void givenAdmin_whenRequestHomePage_thenEnduserPageReturned() {
        // given
        SecurityClientTestUtil.runAsAdmin();

        // when
        final String view = mediatorController.home();

        // then
        assertThat(view, is("/admin/admin_console"));
    }

    // account verification

    @Test
    public void whenAccountVerification_thenViewIsCorrect() {
        // when
        final String view = mediatorController.accountVerification(randomNumeric(4), new ExtendedModelMap());

        // then
        assertThat(view, is("account_verification"));
    }

    @Test
    public void whenAccountVerification_thenModelHasIdParam() {
        // when
        final ExtendedModelMap model = new ExtendedModelMap();
        final String id = randomNumeric(4);
        mediatorController.accountVerification(id, model);

        // then
        assertThat((String) model.get("id"), is(id));
    }

}
