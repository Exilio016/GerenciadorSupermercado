package gerenciamentoMercado.gui.cliente;

import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.abstractGUI.InserirGUI;

import java.awt.*;

/**
 * Created by nding on 14/06/2017.
 */
public class InserirClientesGUI extends InserirGUI {

    public InserirClientesGUI(MainGUI frame, Dimension screenSize) {
        super(frame, screenSize);
    }

    protected String[] criarLabels() {
        return new String[] {"Nome: ", "CPF: ", "RG: ", "CEP: ", "Estado: ", "Cidade: ", "Bairro: ", "Rua: ", "Número: ", "Complemento: ", "Telefone: ", "Celular: ", "Cartao: "};
    }
}
