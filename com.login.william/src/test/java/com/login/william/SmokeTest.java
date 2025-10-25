package com.login.william;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Auth")
@Feature("Smoke")
@ExtendWith(AllureJunit5.class)
class SmokeTest {

    @Test
    @Description("Smoke: valida execução do pipeline e geração do Allure.")
    void ok() {
        assertTrue(true);
    }
}
