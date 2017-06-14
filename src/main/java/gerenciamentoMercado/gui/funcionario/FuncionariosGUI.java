package gerenciamentoMercado.gui.funcionario;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.abstractGUI.TableGUI;
import gerenciamentoMercado.pessoa.Funcionario;
import gerenciamentoMercado.pessoa.Endereco;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Created by nding on 10/06/2017.
 */
public class FuncionariosGUI extends TableGUI {

    public FuncionariosGUI(MainGUI frame, Dimension screenSize) {
        super(frame, screenSize);
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
        modeloTabela.addColumn("Salario");
        modeloTabela.addColumn("Cargo");
        this.atualizarTabela();

        setTabela(tabela);

    }

    @Override
    public void atualizarTabela(){
        DefaultTableModel modeloTabela = getModeloTabela();
        BancoDeDados bd = getBd();

        modeloTabela.setNumRows(0);

        Vector<Funcionario> funcionarios = bd.mostrarFuncionarios();
        for(Funcionario f : funcionarios){
            Endereco e = f.getEnd();
            modeloTabela.addRow(new Object[]{f.getNome(), f.getCPF(), f.getRG(),
                    e.getCEP(), e.getEstado(), e.getCidade(), e.getBairro(), e.getRua(), e.getNumero(), e.getComplemento(),
                    f.getTelefone(), f.getCelular(), f.getSalario(), f.getCargo()});
        }
    }

    public void atualizarTabela(Vector conteudo) {
        DefaultTableModel modeloTabela = getModeloTabela();
        modeloTabela.setNumRows(0);

        for(Object o : conteudo){
            if(!(o instanceof Funcionario)){
                return;
            }
            Funcionario f = (Funcionario) o;
            Endereco e = f.getEnd();
            modeloTabela.addRow(new Object[]{f.getNome(), f.getCPF(), f.getRG(),
                    e.getCEP(), e.getEstado(), e.getCidade(), e.getBairro(), e.getRua(), e.getNumero(), e.getComplemento(),
                    f.getTelefone(), f.getCelular(), f.getSalario(), f.getCargo()});

        }
    }
}
