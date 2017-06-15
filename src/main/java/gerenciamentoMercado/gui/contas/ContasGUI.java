package gerenciamentoMercado.gui.contas;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.abstractGUI.TableGUI;
import gerenciamentoMercado.pessoa.Conta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Created by nding on 15/06/2017.
 */
public class ContasGUI extends TableGUI{
    public ContasGUI(MainGUI frame, Dimension screenSize) {
        super(frame, screenSize);
    }

    protected JLabel criarGUILabel() {
        return new JLabel("Contas");
    }

    protected void criarTabela() {
        DefaultTableModel modeloTabela = getModeloTabela();
        JTable tabela = new JTable(modeloTabela);
        modeloTabela.addColumn("CPF");
        modeloTabela.addColumn("Usuario");
        modeloTabela.addColumn("Senha");
        modeloTabela.addColumn("Email");
        this.atualizarTabela();

        setTabela(tabela);

    }

    public void atualizarTabela() {
        DefaultTableModel modeloTabela = getModeloTabela();
        BancoDeDados bd = getBd();

        modeloTabela.setNumRows(0);

        Vector<Conta> contas = bd.mostrarContas();
        for(Conta c : contas){
            modeloTabela.addRow(new Object[]{c.getCpf(), c.getUsuario(), "************", c.getEmail()});
        }

    }

    public void atualizarTabela(Vector conteudo) {
        DefaultTableModel modeloTabela = getModeloTabela();
        modeloTabela.setNumRows(0);

        for(Object o : conteudo){
            if(!(o instanceof Conta)){
                return;
            }
            Conta c = (Conta) o;
            modeloTabela.addRow(new Object[]{c.getCpf(), c.getUsuario(), c.getSenha(), c.getEmail()});

        }
    }

}
