package gerenciamentoMercado.controlador;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.gui.CaixaGUI;
import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.produto.Produto;

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
    private HashMap<Produto, Integer> produtos = new HashMap<Produto, Integer>();

    public ControladorCaixa(MainGUI frame, CaixaGUI panel){
        this.frame = frame;
        this.panel = panel;
        this.bd = frame.getBanco();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("COMPUTAR_PRODUTO")){
            int codigo = Integer.parseInt(panel.getCodigoProduto().getText());
            int quantidade = Integer.parseInt(panel.getQuantidadeProduto().getText());

            Produto p = bd.mostrarProduto(codigo);
            if(p != null) {
                if (!produtos.containsKey(p)) {
                    produtos.put(p, quantidade);
                }
                else {
                    quantidade += produtos.get(p);
                    produtos.remove(p);
                    produtos.put(p, quantidade);
                }
            }
        }

        else if(e.getActionCommand().equals("ENCERRAR_COMPRA")){
            float valor_final = 0;
            for(Produto p : produtos.keySet()){
                int quantidade = produtos.remove(p);
                valor_final += p.getPreco()*quantidade;

                bd.venderProduto(p.getCodigo(), quantidade); //Diminui do banco os produtos ventidos
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        int codigo;

        try {
            codigo = Integer.parseInt(panel.getCodigoProduto().getText());
        }catch (Exception ex){
            codigo = -1;
        }

        Produto p = bd.mostrarProduto(codigo);
        if(p != null){
            Float valor = p.getPreco();
            panel.getValorProduto().setText(valor.toString());
            panel.getDescricaoProduto().setText(p.getDescricao());
        }

    }
}
