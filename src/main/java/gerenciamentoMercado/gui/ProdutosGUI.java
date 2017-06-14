package gerenciamentoMercado.gui;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.gui.abstractGUI.TableGUI;
import gerenciamentoMercado.produto.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Created by nding on 10/06/2017.
 */
public class ProdutosGUI extends TableGUI {

    public ProdutosGUI(MainGUI frame, Dimension screenSize) {
        super(frame, screenSize);
    }

    @Override
    protected void criarTabela(){
        DefaultTableModel modeloTabela = getModeloTabela();
        JTable tabela = new JTable(modeloTabela);
        modeloTabela.addColumn("Descrição");
        modeloTabela.addColumn("Marca");
        modeloTabela.addColumn("Codigo");
        modeloTabela.addColumn("Valor unitários");
        modeloTabela.addColumn("Quantidade");
        this.atualizarTabela();

        setTabela(tabela);

    }

    @Override
    public void atualizarTabela(){
        DefaultTableModel modeloTabela = getModeloTabela();
        BancoDeDados bd = getBd();

        modeloTabela.setNumRows(0);

        Vector<Produto> produtos = bd.mostrarProdutos();
        for(Produto p : produtos){
            modeloTabela.addRow(new Object[]{p.getDescricao(), p.getMarca(), p.getCodigo(), p.getPreco(), p.getQuantidade()});
        }
    }

    public void atualizarTabela(Vector conteudo) {
        DefaultTableModel modeloTabela = getModeloTabela();
        modeloTabela.setNumRows(0);

        for(Object o : conteudo){
            if(!(o instanceof Produto)){
                return;
            }
            Produto p = (Produto) o;
            modeloTabela.addRow(new Object[]{p.getDescricao(), p.getMarca(), p.getCodigo(), p.getPreco(), p.getQuantidade()});

        }
    }
}
