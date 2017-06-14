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
 * Created by nding on 14/06/2017.
 */
public abstract class InserirGUI extends JPanel implements ActionListener{
    protected Vector<JTextField> campos = new Vector<JTextField>();
    protected MainGUI frame;
    protected TableGUI tableGUI;
    private JButton inserir = new JButton("Inserir");

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

        JPanel sul = new JPanel();
        sul.add(inserir);
        inserir.addActionListener(this);

        this.add(centro, BorderLayout.CENTER);
        this.add(new JPanel(), BorderLayout.NORTH);
        this.add(sul, BorderLayout.SOUTH);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.WEST);

    }

    protected abstract String[] criarLabels();

}
