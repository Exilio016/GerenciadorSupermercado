package gerenciamentoMercado.gui.action;

import gerenciamentoMercado.controlador.ControladorCaixa;
import gerenciamentoMercado.controlador.ControladoraTabela;
import gerenciamentoMercado.gui.abstractGUI.TableGUI;
import gerenciamentoMercado.gui.caixa.CaixaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by nding on 09/06/2017.
 */
public class TableGUIActions extends AbstractAction{
    private TableGUI tableGUI;
    private ControladoraTabela controladorTabela;
    private String command;

    public TableGUIActions(TableGUI tableGUI, ControladoraTabela controladorTabela, String command){
        this.tableGUI = tableGUI;
        this.command = command;
        this.controladorTabela = controladorTabela;
    }

    public void actionPerformed(ActionEvent e) {
        ActionEvent actionEvent = new ActionEvent(tableGUI, 0, command);
        controladorTabela.actionPerformed(actionEvent);
    }
}
