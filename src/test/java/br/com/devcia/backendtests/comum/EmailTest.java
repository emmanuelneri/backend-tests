package br.com.devcia.backendtests.comum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmailTest {

    @Test()
    @DisplayName("Deve retornar valido para emails com um @ e final .com")
    public void deveRetornarEmailValidoComApenasUmArrobaEFinalCom() {
        Assertions.assertTrue(Email.validarEmail("email@email.com"));
    }

    @Test()
    @DisplayName("Deve retornar valido para emails com um @ e final .com.br")
    public void deveRetornarEmailValidoComApenasUmArrobaEFinalComBr() {
        Assertions.assertTrue(Email.validarEmail("email@email.com.br"));
    }

    @Test
    @DisplayName("Deve retornar invalido para emails sem @")
    public void deveRetornarInvalidoParaEmailSemArroba() {
        Assertions.assertFalse(Email.validarEmail("email"));
    }

    @Test
    @DisplayName("Deve retornar invalido para emails que inicie com .")
    public void deveRetornarInvalidoParaEmailQueInicieComPonto() {
        Assertions.assertFalse(Email.validarEmail(".email@email.com"));
    }

    @Test
    @DisplayName("Deve retornar invalido para emails que tenha duas @")
    public void deveRetornarInvalidoParaEmailQueTenhaDuasArrobas() {
        Assertions.assertFalse(Email.validarEmail("email@email@email.com"));
    }

    @Test
    @DisplayName("Deve retornar invalido para emails que termine com .")
    public void deveRetornarInvalidoParaEmailQueTermineComPonto() {
        Assertions.assertFalse(Email.validarEmail("email@email."));
    }

}