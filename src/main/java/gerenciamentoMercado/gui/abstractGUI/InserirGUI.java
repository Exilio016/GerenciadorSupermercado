package gerenciamentoMercado.gui.abstractGUI;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.Utils.SpringUtilities;
import gerenciamentoMercado.gui.funcionario.InserirFuncionariosGUI;
import gerenciamentoMercado.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Classe abstrata que representa uma aba da GUI que contém um formulario de inserção no banco de dados
 */
public abstract class InserirGUI extends JPanel implements ActionListener{
    protected Vector<JTextField> campos = new Vector<JTextField>();
    protected MainGUI frame;
    protected TableGUI tableGUI;
    protected JButton cancelar = new JButton("Cancelar");
    protected JButton inserir = new JButton("Inserir");

    /**
     * Contrutor da classe, que cria o formulario
     * @param frame - MainGUI que contém o formulario
     * @param tableGUI - TableGUI que requisitou o formulário
     */
    public InserirGUI(MainGUI frame, TableGUI tableGUI){
        this.frame = frame;
        this.tableGUI = tableGUI;

        this.setLayout(new BorderLayout());

        JPanel centro = new JPanel();
        centro.setLayout(new SpringLayout());

        String[] labels = this.criarLabels();
        int numPairs = labels.length;

        //Código adaptado de "How to use a SpringLayout", "https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html"
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            centro.add(l);

            JTextField textField = new JTextField(10);
            textField.setName(labels[i].substring(0, labels[i].indexOf(":")));
            campos.add(textField);

            l.setLabelFor(textField);
            centro.add(textField);
        }

        SpringUtilities.makeCompactGrid(centro,
                numPairs, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        JPanel sul = new JPanel(new FlowLayout());
        sul.add(inserir);
        sul.add(cancelar);

        cancelar.addActionListener(this);
        inserir.addActionListener(this);
        inserir.setActionCommand("INSERIR");
        cancelar.setActionCommand("CANCELAR");

        this.add(centro, BorderLayout.CENTER);
        this.add(new JPanel(), BorderLayout.NORTH);
        this.add(sul, BorderLayout.SOUTH);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.WEST);

    }

    /**
     * Método que cria os labels do formulário
     * @return - String[] com os conteúdos dos labels
     */
    protected abstract String[] criarLabels();

}
