package gerenciamentoMercado.gui;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.pessoa.Cliente;
import gerenciamentoMercado.pessoa.Endereco;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Created by nding on 10/06/2017.
 */
public class ClientesGUI extends JPanel {
    private MainGUI frame;
    private Dimension screenSize;
    private BancoDeDados bd;

    private JButton inserir = new JButton("Inserir");
    private JButton remover = new JButton("Remover");
    private JButton editar = new JButton("Editar");
    private DefaultTableModel modeloTabela = new DefaultTableModel();

    public ClientesGUI(MainGUI frame, Dimension screenSize){
        this.frame = frame;
        this.screenSize = screenSize;
        this.bd = frame.getBanco();

        this.setLayout(new BorderLayout());
        this.construirCentro();
    }

    private void construirNorte(){

    }

    private void construirCentro(){
        JPanel panel = new JPanel(new BorderLayout());

        JTable tabela = this.criarTabela();
        JScrollPane rolagem = new JScrollPane(tabela);
        panel.add(rolagem, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new FlowLayout());
        botoes.add(inserir);
        botoes.add(remover);
        botoes.add(editar);
        panel.add(botoes, BorderLayout.SOUTH);

        this.add(panel, BorderLayout.CENTER);

    }

    private JTable criarTabela(){
        JTable tabela = new JTable(modeloTabela);
        tabela.setAutoscrolls(true);

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

        return tabela;
    }

    private void atualizarTabela(){
        modeloTabela.setNumRows(0);

        Vector<Cliente> clientes = bd.mostrarClientes();
        for(Cliente c : clientes){
            Endereco e = c.getEnd();
            modeloTabela.addRow(new Object[]{c.getNome(), c.getCPF(), c.getRG(),
                    e.getCEP(), e.getEstado(), e.getCidade(), e.getBairro(), e.getRua(), e.getNumero(), e.getComplemento(),
                    c.getTelefone(), c.getCelular(), c.getCartao()});
        }
    }


}
