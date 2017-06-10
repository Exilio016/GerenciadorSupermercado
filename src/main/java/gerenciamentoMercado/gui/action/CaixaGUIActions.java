package gerenciamentoMercado.gui.action;

import gerenciamentoMercado.controlador.ControladorCaixa;
import gerenciamentoMercado.gui.CaixaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by nding on 09/06/2017.
 */
public class CaixaGUIActions extends AbstractAction{
    private CaixaGUI caixaGUI;
    private ControladorCaixa controladorCaixa;
    private String command;

    public CaixaGUIActions(CaixaGUI caixaGUI, ControladorCaixa controladorCaixa, String command){
        this.caixaGUI = caixaGUI;
        this.command = command;
        this.controladorCaixa = controladorCaixa;
    }

    public void actionPerformed(ActionEvent e) {
        ActionEvent actionEvent = new ActionEvent(caixaGUI, 0, command);
        controladorCaixa.actionPerformed(actionEvent);
    }
}
