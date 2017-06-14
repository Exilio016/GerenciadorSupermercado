package gerenciamentoMercado.gui.cliente;

import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.abstractGUI.InserirGUI;
import gerenciamentoMercado.pessoa.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by nding on 14/06/2017.
 */
public class InserirClientesGUI extends InserirGUI{

    public InserirClientesGUI(MainGUI frame, ClientesGUI clientesGUI) {
        super(frame, clientesGUI);
    }

    protected String[] criarLabels() {
        return new String[] {"Nome: ", "CPF: ", "RG: ", "CEP: ", "Estado: ", "Cidade: ", "Bairro: ", "Rua: ", "Número: ", "Complemento: ", "Telefone: ", "Celular: ", "Cartao: "};
    }

    public void actionPerformed(ActionEvent e) {
        String nome = campos.get(0).getText();
        String cpf = campos.get(1).getText();
        String rg = campos.get(2).getText();
        String cep = campos.get(3).getText();
        String estado = campos.get(4).getText();
        String cidade = campos.get(5).getText();
        String bairro = campos.get(6).getText();
        String rua = campos.get(7).getText();
        String numeroStr = campos.get(8).getText();
        String complemento = campos.get(9).getText();
        String telefone = campos.get(10).getText();
        String celular = campos.get(11).getText();
        String cartao = campos.get(12).getText();

        int numero;
        try{
            numero = Integer.parseInt(numeroStr);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(this, "'Número' deve ser um número inteiro!");
            return;
        }

        Cliente cliente = new Cliente(estado, cidade, bairro, rua, numero, complemento, cep, cpf, rg, telefone, celular, cartao, nome);
        frame.getBanco().adicionarCliente(cliente);

        tableGUI.atualizarTabela();

        this.setVisible(false);
        frame.setContentPane(tableGUI);
        tableGUI.setVisible(true);
    }
}
