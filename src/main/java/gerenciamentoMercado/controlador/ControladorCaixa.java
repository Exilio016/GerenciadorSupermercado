package gerenciamentoMercado.controlador;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.gui.CaixaGUI;
import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.produto.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Created by nding on 07/06/2017.
 */
public class ControladorCaixa implements ActionListener, KeyListener{
    private MainGUI frame;
    private CaixaGUI panel;
    private BancoDeDados bd;
    private float valor_final = 0;
    private HashMap<Produto, Integer> produtos = new HashMap<Produto, Integer>();

    public ControladorCaixa(MainGUI frame, CaixaGUI panel){
        this.frame = frame;
        this.panel = panel;
        this.bd = frame.getBanco();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("COMPUTAR_PRODUTO")){
            /*
             * O Programe executa esse 'if' sempre que for apertado ENTER na barra 'Codigo do Produto' da CaixaGUI
             * Quando executada, o programa busca um produto no banco, com o codigo contido na barra
             * O Produto encontrado � adicionado em um HashMap, junto com a quantidade
             * Se ja houver um mesmo Produto no map, a quantidade � atualizada somente
             */

            int codigo, quantidade;

            try {
                codigo = Integer.parseInt(panel.getCodigoProduto().getText());
                quantidade = Integer.parseInt(panel.getQuantidadeProduto().getText());
            }catch(Exception ex){
                codigo = -1;
                quantidade = 0;
            }

            Produto p = bd.mostrarProduto(codigo);
            if(p != null) {
                valor_final += p.getPreco() * quantidade;

                if (!produtos.containsKey(p)) {
                    produtos.put(p, quantidade);
                }
                else {
                    int quantidadeTotal = quantidade + produtos.get(p);
                    produtos.remove(p);
                    produtos.put(p, quantidadeTotal);
                }

                JTextArea produtosField = panel.getProdutos();
                String aux = produtosField.getText();
                String produtoString = codigo + " " + p.getDescricao() + " " + p.getMarca() + " " +
                               p.getPreco() + " * " + quantidade + " = " + (p.getPreco() * quantidade) + "\n";
                produtosField.setText(aux+produtoString); //TODO alterar impress�o aki

                panel.getValorTotal().setText(Float.toString(valor_final));

            }
        }

        else if(e.getActionCommand().equals("FINALIZAR_COMPRA")){
            for(Produto p : produtos.keySet()){
                int quantidade = produtos.remove(p);
                bd.venderProduto(p.getCodigo(), quantidade); //Diminui do banco os produtos ventidos
            }
        }

        else  if(e.getActionCommand().equals("CANCELAR_COMPRA")){
            /*
            O Programa executa esse 'if' quando o bot�o 'Cancelar Compra' for pressionado
            Uma janela de confirma��o surgir� na tela
            Se verdadeiro, os valores impressos na tela ser�o apagados
            E a CaixaGUI voltar� ao estado inicial
             */

             if(JOptionPane.showConfirmDialog(panel, "Deseja cancelar a compra?") == JOptionPane.OK_OPTION){
                 this.resetarGUI();
             }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        /*
         * Esse m�todo � chamado ap�s inserir um caracter na barra codigo do produto da CaixaGUI
         * Sempre que invocado esse metodo busca no banco de dados o produto referente ao c�digo na barra
         * Se encontrado um produto, as barras valor unit�rio e descri��o s�o atualizadas
         */
        int codigo;

        try {
            codigo = Integer.parseInt(panel.getCodigoProduto().getText()); //Recupera o valor de codigoProduto e tenta converter para int
        }catch (Exception ex){
            codigo = -1; //Se a convers�o n�o foi possivel, c�digo recebe um valor de produto inv�lido
        }

        Produto p = bd.mostrarProduto(codigo); //Busca o c�digo no banco
        if(p != null){
            Float valor = p.getPreco();
            panel.getValorProduto().setText(valor.toString()); //Atualiza valor unitario
            panel.getDescricaoProduto().setText(p.getDescricao()); //Atualiza descri��o
        }

    }

    private void resetarGUI(){
        this.valor_final = 0;
        this.produtos.clear();

        panel.getValorTotal().setText("");
        panel.getCodigoProduto().setText("");
        panel.getDescricaoProduto().setText("");
        panel.getValorProduto().setText("");
        panel.getProdutos().setText("");
        panel.getQuantidadeProduto().setText("1");
    }
}
