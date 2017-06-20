package gerenciamentoMercado.gui.produto;

import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.abstractGUI.InserirGUI;
import gerenciamentoMercado.gui.abstractGUI.TableGUI;
import gerenciamentoMercado.produto.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.security.spec.ECField;

/**
 * Created by nding on 14/06/2017.
 */
public class InserirProdutoGUI extends InserirGUI{
    public InserirProdutoGUI(MainGUI frame, ProdutosGUI produtosGUI) {
        super(frame, produtosGUI);
    }

    protected String[] criarLabels() {
        return new String[] {"Descrição: ", "Marca: ", "Código: ", "Valor unitário: ", "Quantidade: "};
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("INSERIR")) {
            String descricao = campos.get(0).getText();
            String marca = campos.get(1).getText();
            String codigoStr = campos.get(2).getText();
            String valorStr = campos.get(3).getText();
            String quantidadeStr = campos.get(4).getText();

            int codigo, quantidade;
            float valor;

            try {
                codigo = Integer.parseInt(codigoStr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "'Código' deve ser um número inteiro!");
                return;
            }

            try {
                quantidade = Integer.parseInt(quantidadeStr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "'Quantidade' deve ser um número inteiro!");
                return;
            }

            try {
                valor = Float.parseFloat(valorStr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "'Valor unitário' deve ser um número real!\nAtenção: não use vírgula(,) use ponto(.)");
                return;
            }
            
            if(frame.getBanco().procurarProduto(codigo) == null){            
	            Produto produto = new Produto(quantidade, valor, marca, descricao, codigo);
	            frame.getBanco().adicionarProduto(produto);
            }else{
            	JOptionPane.showMessageDialog(this, "Código já cadastrado no sistema!");
            	return;
            }

            this.setVisible(false);
            frame.setContentPane(tableGUI);
            tableGUI.setVisible(true);

            tableGUI.atualizarTabela();
        }
        else {
            this.setVisible(false);
            frame.setContentPane(tableGUI);
            tableGUI.setVisible(true);
        }

    }
}
