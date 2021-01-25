package com.client;


import com.gui.ClientEdit;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


class TestClient {

    @Test
    public void ifPhoneGood() {
        ClientEdit clientEdit = new ClientEdit();
        clientEdit.validPhone("123432123");

        Assert.assertTrue(clientEdit.validPhone("123432123"));
    }

    @Test
    public void ifDateGood() {
        ClientEdit clientEdit = new ClientEdit();
        clientEdit.validDate("1991-11-11");

        Assert.assertTrue(clientEdit.validDate("1991-11-11"));
    }

    @Test
    public void ifPasswordGood() {
        ClientEdit clientEdit = new ClientEdit();
        clientEdit.validPassword("!QAZ2wsx", "!QAZ2wsx");

        Assert.assertTrue(clientEdit.validPassword("!QAZ2wsx", "!QAZ2wsx"));
    }

    @Test
    public void ifDocNumberGood() {
        ClientEdit clientEdit = new ClientEdit();
        clientEdit.validDocumentNumber("CYA123123");

        Assert.assertTrue(clientEdit.validDocumentNumber("CYA123123"));
    }

}
