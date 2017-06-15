package gerenciamentoMercado.gui.cliente;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.abstractGUI.TableGUI;
import gerenciamentoMercado.pessoa.Cliente;
import gerenciamentoMercado.pessoa.Endereco;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Created by nding on 10/06/2017.
 */
public class ClientesGUI extends TableGUI {

    public ClientesGUI(MainGUI frame, Dimension screenSize) {
        super(frame, screenSize);
    }

    protected JLabel criarGUILabel() {
        return new JLabel("Clientes");
    }

    @Override
    protected void criarTabela(){
        DefaultTableModel modeloTabela = getModeloTabela();
        JTable tabela = new JTable(modeloTabela);

        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("CPF");
        modeloTabela.addColumn("RG");
        modeloTabela.addColumn("CEP");
        modeloTabela.addColumn("Estado");
        modeloTabela.addColumn("Cidade");
        modeloTabela.addColumn("Bairro");
        modeloTabela.addColumn("Rua");
        modeloTabela.addColumn("Número");
        modeloTabela.addColumn("Complemento");
        modeloTabela.addColumn("Telefone");
        modeloTabela.addColumn("Celular");
        modeloTabela.addColumn("Cartao");
        this.atualizarTabela();

        setTabela(tabela);

    }

    @Override
    public void atualizarTabela(){
        DefaultTableModel modeloTabela = getModeloTabela();
        BancoDeDados bd = getBd();

        modeloTabela.setNumRows(0);

        Vector<Cliente> clientes = bd.mostrarClientes();
        for(Cliente c : clientes){
            Endereco e = c.getEnd();
            modeloTabela.addRow(new Object[]{c.getNome(), c.getCPF(), c.getRG(),
                    e.getCEP(), e.getEstado(), e.getCidade(), e.getBairro(), e.getRua(), e.getNumero(), e.getComplemento(),
                    c.getTelefone(), c.getCelular(), c.getCartao()});
        }
    }

    public void atualizarTabela(Vector conteudo) {
        DefaultTableModel modeloTabela = getModeloTabela();
        modeloTabela.setNumRows(0);

        for(Object o : conteudo){
            if(!(o instanceof Cliente)){
                return;
            }
            Cliente c = (Cliente) o;
            Endereco e = c.getEnd();
            modeloTabela.addRow(new Object[]{c.getNome(), c.getCPF(), c.getRG(),
                    e.getCEP(), e.getEstado(), e.getCidade(), e.getBairro(), e.getRua(), e.getNumero(), e.getComplemento(),
                    c.getTelefone(), c.getCelular(), c.getCartao()});

        }
    }
}
