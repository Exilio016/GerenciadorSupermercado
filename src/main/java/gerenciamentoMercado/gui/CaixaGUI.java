package gerenciamentoMercado.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CaixaGUI extends JPanel{
	private JTextField inserirProduto = new JTextField();
	private JTextField quantidadeProduto = new JTextField();
	private JTextField valorProduto = new JTextField();
	
	public CaixaGUI(MainGUI frame){
		this.setLayout(new BorderLayout());
		
		JPanel inserirPanel = new JPanel();
		inserirPanel.setLayout(new GridLayout(2, 2));
		inserirPanel.add(new JLabel("Codigo do produto"));
		
		JPanel panelAux1 = new JPanel();
		panelAux1.setLayout(new FlowLayout());
		panelAux1.add(new JLabel("Quantidade"));
		panelAux1.add(new JLabel(" Valor unitário"));
		inserirPanel.add(panelAux1);
		
		inserirPanel.add(inserirProduto);
		
		JPanel panelAux2 = new JPanel();
		panelAux2.setLayout(new FlowLayout());
		panelAux2.add(quantidadeProduto);
		panelAux2.add(valorProduto);
		inserirPanel.add(panelAux2);
		
		quantidadeProduto.setText("1");
		quantidadeProduto.setPreferredSize(new Dimension(30, 60));
		
		this.add(inserirPanel, BorderLayout.NORTH);
		
	}
	
}
