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
import java.util.ArrayList;
import java.util.Iterator;

public class ControladorCaixa implements ActionListener, KeyListener{
    private MainGUI frame;
    private CaixaGUI panel;
    private BancoDeDados bd;
    private String cpf;
    private boolean primeira_execução = true;
    private float valor_final = 0;
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    private static final int REMOCAO_OP = -1;
    private static final int ADICAO_OP = 1;

    public ControladorCaixa(MainGUI frame, CaixaGUI panel){
        this.frame = frame;
        this.panel = panel;
        this.bd = frame.getBanco();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("COMPUTAR_PRODUTO")){
            /*
             * O Programe executa esse 'if' sempre que for apertado ENTER na barra 'Codigo do Produto' da CaixaGUI
             * Quando executada, o programa busca um produto no banco, com o codigo contido na barra
             * O Produto encontrado é adicionado em um HashMap, junto com a quantidade
             * Se ja houver um mesmo Produto no map, a quantidade é atualizada somente
             */

            int codigo, quantidade;

            if(primeira_execução){
                this.cpf = JOptionPane.showInputDialog(panel, "Digite o cpf:\n");
                panel.getProdutos().setText("CPF: "+ cpf + "\n");
                primeira_execução = false;
            }

            try {
                codigo = Integer.parseInt(panel.getCodigoProduto().getText());
                quantidade = Integer.parseInt(panel.getQuantidadeProduto().getText());
            }catch(Exception ex){
                codigo = -1;
                quantidade = 0;
            }

            Produto p = bd.mostrarProduto(codigo);
            if(p != null && quantidade > 0) {
                p.setQuantidade(quantidade);
                valor_final += p.getPreco() * quantidade;

                boolean encontrou = false;
                for(Produto produto : produtos){
                    if(produto.equals(p)){
                        produto.setQuantidade(produto.getQuantidade()+quantidade);
                        encontrou = true;
                        break;
                    }
                }

                if(!encontrou){
                    produtos.add(p);
                }

                atualizarProdutosArea(p, quantidade, ControladorCaixa.ADICAO_OP);
                panel.getValorTotal().setText(Float.toString(valor_final));

            }
        }

        else if(e.getActionCommand().equals("FINALIZAR_COMPRA")){
            float valor_compra = valor_final;
            String notaFiscal = panel.getProdutos().getText();

            //TODO ir para a GUI de finalizar compra
            this.resetarGUI();

        }

        else  if(e.getActionCommand().equals("CANCELAR_COMPRA")){
            /*
            O Programa executa esse 'if' quando o botão 'Cancelar Compra' for pressionado
            Uma janela de confirmação surgirá na tela
            Se verdadeiro, os valores impressos na tela serão apagados
            E a CaixaGUI voltará ao estado inicial
             */

             if(JOptionPane.showConfirmDialog(panel, "Deseja cancelar a compra?") == JOptionPane.OK_OPTION){
                 this.resetarGUI();
             }
        }

        else if(e.getActionCommand().equals("REMOVER_PRODUTO") || e.getActionCommand().equals("F1")){
            int codigo = -1;
            int quantidadeRemovida = 0;

            try {
                codigo = Integer.parseInt(JOptionPane.showInputDialog(panel, "Digite o código do produto a ser removido:"));
                quantidadeRemovida = Integer.parseInt(JOptionPane.showInputDialog(panel, "Digite a quantidade:"));
            }catch (Exception ex){
            }

            if(quantidadeRemovida > 0) {
                //TODO Consertar erro
                Produto p = null;
                Iterator<Produto> it = produtos.iterator();

                boolean encontrou = false;
                while(it.hasNext() && !encontrou){
                    p = it.next();

                    if(p.getCodigo() == codigo)
                        encontrou = true;
                }

                if(encontrou){
                    int quantidade = p.getQuantidade();
                    if(quantidadeRemovida <= quantidade){
                        quantidade -= quantidadeRemovida;
                        valor_final -= p.getPreco() * quantidadeRemovida;

                        if(quantidade > 0){
                            p.setQuantidade(quantidade);
                        }
                        else {
                            produtos.remove(p);
                        }

                        atualizarProdutosArea(p, quantidadeRemovida, ControladorCaixa.REMOCAO_OP);
                        panel.getValorTotal().setText(Float.toString(valor_final));
                    }
                }

            }


        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        /*
         * Esse método é chamado após inserir um caracter na barra codigo do produto da CaixaGUI
         * Sempre que invocado esse metodo busca no banco de dados o produto referente ao código na barra
         * Se encontrado um produto, as barras valor unitário e descrição são atualizadas
         */
        int codigo;

        try {
            codigo = Integer.parseInt(panel.getCodigoProduto().getText()); //Recupera o valor de codigoProduto e tenta converter para int
        }catch (Exception ex){
            codigo = -1; //Se a conversão não foi possivel, código recebe um valor de produto inválido
        }

        Produto p = bd.mostrarProduto(codigo); //Busca o código no banco
        if(p != null){
            Float valor = p.getPreco();
            panel.getValorProduto().setText(valor.toString()); //Atualiza valor unitario
            panel.getDescricaoProduto().setText(p.getDescricao()); //Atualiza descrição
        }

    }

    private void resetarGUI(){
        this.valor_final = 0;
        this.produtos.clear();
        this.cpf = JOptionPane.showInputDialog(panel,"Digite o cpf:\n");

        panel.getValorTotal().setText("");
        panel.getCodigoProduto().setText("");
        panel.getDescricaoProduto().setText("");
        panel.getValorProduto().setText("");
        panel.getProdutos().setText("CPF: " + this.cpf + "\n");
        panel.getQuantidadeProduto().setText("1");
    }

    private void atualizarProdutosArea(Produto p, int quantidade, int operacao){
        String prefixo = "Adicionado: ";

        if(operacao == ControladorCaixa.REMOCAO_OP)
            prefixo = "Removido: ";

        JTextArea produtosField = panel.getProdutos();
        String aux = produtosField.getText();
        String produtoString = prefixo + p.codigo + " - " + p.getDescricao() + ", " + p.getMarca() + " , valor :" +
                p.getPreco() + " * " + quantidade + " = " + (operacao * p.getPreco() * quantidade) + "\n";
        produtosField.setText(aux + produtoString); //TODO alterar impressão aki
    }

}
