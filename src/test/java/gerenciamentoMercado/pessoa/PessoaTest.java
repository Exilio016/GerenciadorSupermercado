package gerenciamentoMercado.pessoa;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nding on 01/06/2017.
 */
public class PessoaTest {
    @Test
    public void verificaCPF() throws Exception {
        assertTrue(Pessoa.verificaCPF("092.148.046-64"));
        assertTrue(Pessoa.verificaCPF("09214804664"));

        assertFalse(Pessoa.verificaCPF("invalido"));
        assertFalse(Pessoa.verificaCPF("123.456.789-10"));
    }

}