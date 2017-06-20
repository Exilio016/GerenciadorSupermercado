package gerenciamentoMercado.gui.caixa;

import gerenciamentoMercado.controlador.ControladorCaixa;
import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.action.CaixaGUIActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Classe que representa a aba do caixa do supermercado
 */
public class CaixaGUI extends JPanel{
	private JTextField codigoProduto = new JTextField();
	private JTextField descricaoProduto = new JTextField();
	private JTextField quantidadeProduto = new JTextField();
	private JTextField valorProduto = new JTextField();
	private JTextField valorTotal = new JTextField();
	private JTextArea produtos = new JTextArea();

	private JButton removerProduto = new JButton();
	private JButton finalizar = new JButton();
	private JButton cancelar = new JButton();
	private JButton fechar = new JButton();

	private BufferedImage backgroundImage;
	private Dimension screenSize;

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

	/**
	 * Contrutor da classe que configura a GUI e os listeners dos botões
	 * @param frame - MainGUI que contem o objeto dessa classe
	 * @param screenSize - Dimension que contem o tamanho da tela
	 */
	public CaixaGUI(MainGUI frame, Dimension screenSize){
		ControladorCaixa controladorCaixa = new ControladorCaixa(frame, this);

		this.screenSize = screenSize;
		this.setLayout(new BorderLayout());
		this.construirNorte();
		this.construirCentro();
		this.construirLeste();
		this.gerarKeyMap(controladorCaixa);

		JPanel borda1 = new JPanel();
		borda1.setOpaque(false);

		JPanel borda2 = new JPanel();
		borda2.setOpaque(false);

		this.add(borda1, BorderLayout.SOUTH);
		this.add(borda2, BorderLayout.WEST);

		try {
			InputStream file = getClass().getResourceAsStream("/background.png");

			backgroundImage = ImageIO.read(file);
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}

		codigoProduto.setActionCommand("COMPUTAR_PRODUTO");
		codigoProduto.addActionListener(controladorCaixa);
		codigoProduto.addKeyListener(controladorCaixa);

		quantidadeProduto.setActionCommand("COMPUTAR_PRODUTO");
		quantidadeProduto.addActionListener(controladorCaixa);

		removerProduto.setText("Remover produto - F1");
		removerProduto.setActionCommand("REMOVER_PRODUTO");
		removerProduto.addActionListener(controladorCaixa);

		finalizar.setText("Finalzar compra - F3");
		finalizar.setActionCommand("FINALIZAR_COMPRA");
		finalizar.addActionListener(controladorCaixa);

		cancelar.setText("Cancelar compra - F2");
		cancelar.setActionCommand("CANCELAR_COMPRA");
		cancelar.addActionListener(controladorCaixa);

		fechar.setText("Fechar caixa - F4");
		fechar.addActionListener(controladorCaixa);
		fechar.setActionCommand("FECHAR_CAIXA");
	}


	/**
	 * Método auxiliar na construção da GUI, cria a borda norte do JPanel
	 */
	private void construirNorte(){
		int boxWidth1 = (int) (screenSize.getWidth() * 0.5) ;
		int boxWidth2 = (int) (screenSize.getWidth() * 0.2) ;
		int boxWidth3 = (int) (screenSize.getWidth() * 0.1) ;

		JPanel inserirPanel = new JPanel();
		inserirPanel.setLayout(new GridLayout(3, 1));
		inserirPanel.setOpaque(false);

		JLabel titulo = new JLabel("Caixa");
		//titulo.setFont(new Font("titulo", Font.BOLD, 32));
		JPanel tituloPanel = new JPanel();
		tituloPanel.setOpaque(false);
		tituloPanel.add(titulo);
		inserirPanel.add(tituloPanel);

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

	/**
	 * Método que configura o centro da GUI dessa aba
	 */
	private void construirCentro(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension((int) (0.6 * screenSize.getWidth()), (int)(0.9 * screenSize.getHeight())));
		panel.setOpaque(false);

		JScrollPane centerPanel = new JScrollPane(produtos);
		produtos.setEditable(false);
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel auxPanel1 = new JPanel(new FlowLayout());
		auxPanel1.add(new JLabel("Valor Total: "));

		valorTotal.setEditable(false);
		valorTotal.setPreferredSize(new Dimension((int) (0.4 * screenSize.getHeight()), 30));
		auxPanel1.add(valorTotal);
		panel.add(auxPanel1, BorderLayout.SOUTH);

		this.add(panel, BorderLayout.CENTER);
	}

	/**
	 * Método que configura a borda leste da GUI dessa aba
	 */
	private void construirLeste(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		panel.setPreferredSize(new Dimension((int) (0.12 * screenSize.getWidth()), (int)(0.6 * screenSize.getHeight())));
		panel.setOpaque(false);

		JPanel aux1 = new JPanel();
		aux1.setOpaque(false);
		aux1.add(removerProduto);

		JPanel aux2 = new JPanel();
		aux2.setOpaque(false);
		aux2.add(cancelar);

		JPanel aux3 = new JPanel();
		aux3.setOpaque(false);
		aux3.add(finalizar);

		JPanel aux4 = new JPanel();
		aux4.setOpaque(false);
		aux4.add(fechar);

		panel.add(aux1);
		panel.add(aux2);
		panel.add(aux3);
		panel.add(aux4);

		this.add(panel, BorderLayout.EAST);

	}

	/**
	 * Método que gera os atalhos do teclado dessa aba
	 * @param controladorCaixa - Classe que contem o actionListener para os botões dessa GUI
	 */
	private void gerarKeyMap(ControladorCaixa controladorCaixa){
		InputMap imap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = this.getActionMap();

		amap.put("REMOVER_PRODUTO", new CaixaGUIActions(this, controladorCaixa, "REMOVER_PRODUTO"));
		amap.put("CANCELAR_COMPRA", new CaixaGUIActions(this, controladorCaixa, "CANCELAR_COMPRA"));
		amap.put("FINALIZAR_COMPRA", new CaixaGUIActions(this, controladorCaixa, "FINALIZAR_COMPRA"));
		amap.put("FECHAR_CAIXA", new CaixaGUIActions(this, controladorCaixa, "FECHAR_CAIXA"));

		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "REMOVER_PRODUTO");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "CANCELAR_COMPRA");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "FINALIZAR_COMPRA");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "FECHAR_CAIXA");
	}

	@Override
	protected void paintComponent(final Graphics g){
		super.paintComponent(g);

		Rectangle rect  = new Rectangle(0,0, this.getWidth(), this.getHeight());

		try{
			TexturePaint p = new TexturePaint(backgroundImage, rect);
			Graphics2D g2 = (Graphics2D)g;
	
			g2.setPaint(p);
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		}catch(Exception e){
			
		}
	}
}
