package gerenciamentoMercado.controlador;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.gui.LoginGUI;
import gerenciamentoMercado.gui.caixa.CaixaGUI;
import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.pessoa.Cliente;
import gerenciamentoMercado.pessoa.Pessoa;
import gerenciamentoMercado.produto.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Classe que controla o caixa, como listener dos eventos de CaixaGUI
 */
public class ControladorCaixa implements ActionListener, KeyListener{
    private MainGUI frame;
    private CaixaGUI panel;
    private BancoDeDados bd;
    private String cpf;
    private boolean primeira_execução = true;
    private float valor_final = 0;
    private float desconto = 0;
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    private static final int REMOCAO_OP = -1;
    private static final int ADICAO_OP = 1;

    /**
     * @param frame - MainGUI que contem panel
     * @param panel - CaixaGUI que é a origem dos ActionEvents tratados pela classe
     */
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
             * O Produto encontrado é adicionado em um HashMap, junto com a quantidade
             * Se ja houver um mesmo Produto no map, a quantidade é atualizada somente
             */

            int codigo, quantidade;

            if(primeira_execução){
                this.cpf = JOptionPane.showInputDialog(panel, "Digite o cpf:\n");

                if(!Pessoa.verificaCPF(cpf)){
                    cpf = null;
                }else{
                    String[] split = cpf.split("\\D");
                    cpf = "";
                    for(String s : split){
                        cpf += s;
                    }
                }
                Cliente c = bd.procurarCliente(cpf);
                panel.getProdutos().setText("CPF: " + cpf + "\n");

                if(c != null){
                    panel.getProdutos().setText(panel.getProdutos().getText() + "Cliente VIP!! - Compra com " + c.getDesconto()*100 +"% de desconto!\n");
                    desconto = c.getDesconto();
                }
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
                panel.getValorTotal().setText(Float.toString(valor_final - valor_final * desconto));

            }
        }

        else if(e.getActionCommand().equals("FINALIZAR_COMPRA")){
            DecimalFormat df = new DecimalFormat("#.00");
            float valor_compra = Float.parseFloat(df.format(valor_final - valor_final * desconto).replace(',', '.')); //valor_compra recebe o valor final com o desconto, formatado para duas casas decimais
            String notaFiscal = panel.getProdutos().getText() + "Valor total: " + valor_compra + "\n";

            boolean finalizou = false;
            while(!finalizou) {
                Object[] metodo = {"Dinheiro", "Cheque"};
                String metodoEscolido = (String) JOptionPane.showInputDialog(panel, "Método de pagamento? \nValor a ser pago: " + valor_compra, "Pagamento", JOptionPane.PLAIN_MESSAGE, null, metodo, "Dinheiro");

                boolean continuar = false;
                float valor_recebido = 0;

                while (!continuar) {
                    try {
                        valor_recebido = Float.parseFloat((String) JOptionPane.showInputDialog(panel, "Valor Total: " + valor_compra +"\nValor recebido: "));
                        continuar = true;
                    } catch (Exception ex) {
                        continuar = false;
                    }
                }

                notaFiscal += metodoEscolido + ": " + valor_recebido + '\n';

                finalizou = true;
                if(valor_recebido > valor_compra) {
                    float troco = Float.parseFloat(df.format(valor_recebido - valor_compra).replace(',', '.'));
                    JOptionPane.showMessageDialog(panel, "Troco: " + troco);
                    notaFiscal += "Troco: " + troco + "\n";
                }
                else if(valor_recebido < valor_compra) {
                    valor_compra -= valor_recebido;
                    finalizou = false;
                }
            }

            /*
            Cria arquivo com a nota fiscal da compra
             */
            try {
                File diretorio = new File("log");
                diretorio.mkdir();
                diretorio = new File("log/vendas");
                diretorio.mkdir();

                FileOutputStream nota = new FileOutputStream("./log/vendas/" + cpf + " - " +  System.currentTimeMillis() + ".txt");
                nota.write(notaFiscal.getBytes());
            } catch (Exception e1) {
                ;
            }

            //Laço que diminui a quantidade de produtos no estoque de acordo com a venda
            for(Produto p : produtos){
                bd.venderProduto(p.getCodigo(), p.getQuantidade());
            }

            this.resetarGUI(); //Zera a GUI para a proxima venda

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

        else if(e.getActionCommand().equals("FECHAR_CAIXA")){

            if (JOptionPane.showConfirmDialog(panel, "Deseja fechar o caixa?") == JOptionPane.OK_OPTION) {
                frame.setSize(400, 400);
                panel.setVisible(false);

                frame.setContentPane(new LoginGUI(frame));
                frame.setJMenuBar(null);
                frame.getContentPane().setVisible(true);
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
        this.cpf = JOptionPane.showInputDialog(panel, "Digite o cpf:\n");

        if(!Pessoa.verificaCPF(cpf)){
            cpf = null;
        }else{
            String[] split = cpf.split("\\D");
            cpf = "";
            for(String s : split){
                cpf += s;
            }
        }
        Cliente c = bd.procurarCliente(cpf);
        panel.getProdutos().setText("CPF: " + cpf + "\n");

        if(c != null){
            panel.getProdutos().setText(panel.getProdutos().getText() + "Cliente VIP!! - Compra com " + c.getDesconto()*100 +"% de desconto!\n");
            desconto = c.getDesconto();
        }

        panel.getValorTotal().setText("");
        panel.getCodigoProduto().setText("");
        panel.getDescricaoProduto().setText("");
        panel.getValorProduto().setText("");
        panel.getQuantidadeProduto().setText("1");
    }

    private void atualizarProdutosArea(Produto p, int quantidade, int operacao){
        String prefixo = "Adicionado: ";

        if(operacao == ControladorCaixa.REMOCAO_OP)
            prefixo = "Removido: ";

        JTextArea produtosField = panel.getProdutos();
        String aux = produtosField.getText();
        String produtoString = prefixo + p.getCodigo() + " - " + p.getDescricao() + ", " + p.getMarca() + " , valor :" +
                p.getPreco() + " * " + quantidade + " = " + (operacao * p.getPreco() * quantidade) + "\n";
        produtosField.setText(aux + produtoString);
    }

}
