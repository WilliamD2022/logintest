package com.login.william;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Smoke unit test só para garantir que o estágio unit-tests sempre tem algo para rodar */
@Tag("unit")
class UnitSmokeTest {
  @Test
  void healthcheck() {
    assertTrue(true);
  }
}
