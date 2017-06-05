package gerenciamentoMercado.bancoDeDados;

import gerenciamentoMercado.pessoa.Cliente;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Vector;

import static org.junit.Assert.*;

public class BancoDeDadosTest {
    BancoDeDados bd;

    @Before
    public void BancoDeDados() throws SQLException{
        bd = new BancoDeDados();
    }

    @Test
    public void adicionarClienteTest(){
        Cliente c1 = new Cliente("MG", "Monte Santo", "Jd. Magnólia", "Waldemar Pereira Lima", 377, "", "37968000", "09214804664", "18575144", "3535913226", "35991306150", "000000", "Bruno Flavio");

        bd.adicionarCliente(c1);
        Cliente c2 = bd.procurarCliente("09214804664");
        bd.removerCliente(c2.getCPF());

    }

}