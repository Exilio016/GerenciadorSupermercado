package gerenciamentoMercado.gui;

import gerenciamentoMercado.controlador.ControladorCaixa;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CaixaGUI extends JPanel{
	private JTextField codigoProduto = new JTextField();
	private JTextField descricaoProduto = new JTextField();
	private JTextField quantidadeProduto = new JTextField();
	private JTextField valorProduto = new JTextField();
	private JTextField valorTotal = new JTextField();
	private JTextArea produtos = new JTextArea();

	private JButton finalizar = new JButton();
	private JButton cancelar = new JButton();
	private JButton fechar = new JButton();

	private BufferedImage backgroundImage;
	private Dimension d;

	public JTextField getCodigoProduto() {
		return codigoProduto;
	}

	public JTextField getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public JTextField getValorProduto() {
		return valorProduto;
	}

	public JTextField getDescricaoProduto() {
		return descricaoProduto;
	}

	public JTextField getValorTotal() {
		return valorTotal;
	}

	public JTextArea getProdutos() {
		return produtos;
	}

	public JButton getFinalizar() {
		return finalizar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public JButton getFechar() {
		return fechar;
	}

	public CaixaGUI(MainGUI frame, Dimension d){
		this.d = d;

		this.setLayout(new BorderLayout());
		this.construirNorte();
		this.construirCentro();
		this.construirLeste();

		try {
			 backgroundImage = ImageIO.read(new File(".\\images\\background.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}

		ControladorCaixa controladorCaixa = new ControladorCaixa(frame, this);

		codigoProduto.setActionCommand("COMPUTAR_PRODUTO");
		codigoProduto.addActionListener(controladorCaixa);
		codigoProduto.addKeyListener(controladorCaixa);

		finalizar.setText("Finalzar compra");
		finalizar.setActionCommand("FINALIZAR_COMPRA");
		finalizar.addActionListener(controladorCaixa);

		cancelar.setText("Cancelar compra");
		cancelar.setActionCommand("CANCELAR_COMPRA");
		cancelar.addActionListener(controladorCaixa);

		fechar.setText("Fechar caixa");
		fechar.setActionCommand("FECHAR_CAIXA");
		fechar.addActionListener(controladorCaixa);

	}


	/**
	 * Método auxiliar na construção da GUI, cria a borda norte do JPanel
	 */
	private void construirNorte(){
		int boxWidth1 = (int) (d.getWidth() * 0.5) ;
		int boxWidth2 = (int) (d.getWidth() * 0.2) ;
		int boxWidth3 = (int) (d.getWidth() * 0.1) ;

		JPanel inserirPanel = new JPanel();
		inserirPanel.setLayout(new GridLayout(2, 1));
		inserirPanel.setOpaque(false);

		JPanel panelAux1 = new JPanel();
		panelAux1.setLayout(new FlowLayout());
		panelAux1.setOpaque(false);

		JLabel l = new JLabel("Descição");
		l.setPreferredSize(new Dimension(boxWidth1, 20));
		panelAux1.add(l);

		JLabel l1 = new JLabel("Codigo do produto");
		l1.setPreferredSize(new Dimension(boxWidth2, 20));
		panelAux1.add(l1);

		panelAux1.add(new JLabel("Quantidade"));
		panelAux1.add(new JLabel(" Valor unitário"));
		inserirPanel.add(panelAux1);


		JPanel panelAux2 = new JPanel();
		panelAux2.setLayout(new FlowLayout());
		panelAux2.add(descricaoProduto);
		panelAux2.add(codigoProduto);
		panelAux2.add(quantidadeProduto);
		panelAux2.add(valorProduto);
		panelAux2.setOpaque(false);
		inserirPanel.add(panelAux2);

		descricaoProduto.setPreferredSize(new Dimension(boxWidth1, 20));
		descricaoProduto.setEditable(false);

		codigoProduto.setPreferredSize(new Dimension(boxWidth2, 20));

		valorProduto.setPreferredSize(new Dimension(boxWidth3, 20));
		valorProduto.setEditable(false);

		quantidadeProduto.setText("1");
		quantidadeProduto.setPreferredSize(new Dimension(boxWidth3, 20));

		this.add(inserirPanel, BorderLayout.NORTH);
	}

	private void construirCentro(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension((int) (0.6 * d.getWidth()), (int)(0.9 * d.getHeight())));
		panel.setOpaque(false);

		JScrollPane centerPanel = new JScrollPane();
		centerPanel.setPreferredSize(new Dimension((int) (0.55 * d.getWidth()), (int)(0.85 * d.getHeight())));
		centerPanel.add(produtos);
		produtos.setEditable(false);
		panel.add(produtos, BorderLayout.CENTER); //Arrumar isso

		JPanel auxPanel1 = new JPanel(new FlowLayout());
		auxPanel1.add(new JLabel("Valor Total: "));

		valorTotal.setEditable(false);
		valorTotal.setPreferredSize(new Dimension((int) (0.4 * d.getHeight()), 30));
		auxPanel1.add(valorTotal);
		panel.add(auxPanel1, BorderLayout.SOUTH);

		this.add(panel, BorderLayout.CENTER);
	}

	private void construirLeste(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.setPreferredSize(new Dimension((int) (0.12 * d.getWidth()), (int)(0.6 * d.getHeight())));
		panel.setOpaque(false);

		JPanel aux1 = new JPanel();
		aux1.setOpaque(false);
		aux1.add(finalizar);

		JPanel aux2 = new JPanel();
		aux2.setOpaque(false);
		aux2.add(cancelar);

		JPanel aux3 = new JPanel();
		aux3.setOpaque(false);
		aux3.add(fechar);

		panel.add(aux1);
		panel.add(aux2);
		panel.add(aux3);

		finalizar.setSize(new Dimension((int) (0.1 * d.getWidth()), 40));
		cancelar.setSize(new Dimension((int) (0.1 * d.getWidth()), 40));
		fechar.setSize(new Dimension((int) (0.1 * d.getWidth()), 40));

		this.add(panel, BorderLayout.EAST);

	}

	@Override
	protected void paintComponent(final Graphics g){
		super.paintComponent(g);

		Rectangle rect  = new Rectangle(0,0, this.getWidth(), this.getHeight());

		TexturePaint p = new TexturePaint(backgroundImage, rect);
		Graphics2D g2 = (Graphics2D)g;

		g2.setPaint(p);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
