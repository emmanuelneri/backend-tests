package br.com.devcia.backendtests.comum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmailUtilTest {

    @Test()
    @DisplayName("Deve retornar valido para emails com um @ e final .com")
    public void deveRetornarEmailValidoComApenasUmArrobaEFinalCom() {
        Assertions.assertTrue(EmailUtil.validarEmail("email@email.com"));
    }

    @Test()
    @DisplayName("Deve retornar valido para emails com um @ e final .com.br")
    public void deveRetornarEmailValidoComApenasUmArrobaEFinalComBr() {
        Assertions.assertTrue(EmailUtil.validarEmail("email@email.com.br"));
    }

    @Test
    @DisplayName("Deve retornar invalido para emails sem @")
    public void deveRetornarInvalidoParaEmailSemArroba() {
        Assertions.assertFalse(EmailUtil.validarEmail("email"));
    }

    @Test
    @DisplayName("Deve retornar invalido para emails que inicie com .")
    public void deveRetornarInvalidoParaEmailQueInicieComPonto() {
        Assertions.assertFalse(EmailUtil.validarEmail(".email@email.com"));
    }

    @Test
    @DisplayName("Deve retornar invalido para emails que tenha duas @")
    public void deveRetornarInvalidoParaEmailQueTenhaDuasArrobas() {
        Assertions.assertFalse(EmailUtil.validarEmail("email@email@email.com"));
    }

    @Test
    @DisplayName("Deve retornar invalido para emails que termine com .")
    public void deveRetornarInvalidoParaEmailQueTermineComPonto() {
        Assertions.assertFalse(EmailUtil.validarEmail("email@email."));
    }

}