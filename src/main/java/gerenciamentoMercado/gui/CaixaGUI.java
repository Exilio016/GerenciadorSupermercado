package gerenciamentoMercado.gui;

import sun.java2d.windows.GDIRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CaixaGUI extends JPanel{
	private JTextField inserirProduto = new JTextField();
	private JTextField quantidadeProduto = new JTextField();
	private JTextField valorProduto = new JTextField();
	private BufferedImage b;
	private Rectangle rect;

	public CaixaGUI(MainGUI frame){
		this.setLayout(new BorderLayout());
		this.construirNorte();

		try {
			 b = ImageIO.read(new File(".\\images\\background.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}


	public void construirNorte(){

		JPanel inserirPanel = new JPanel();
		inserirPanel.setLayout(new GridLayout(2, 1));
		inserirPanel.setOpaque(false);

		JPanel panelAux1 = new JPanel();
		panelAux1.setLayout(new FlowLayout());
		panelAux1.setOpaque(false);

		JLabel l1 = new JLabel("Codigo do produto");
		l1.setPreferredSize(new Dimension(950, 20));
		panelAux1.add(l1);

		panelAux1.add(new JLabel("Quantidade"));
		panelAux1.add(new JLabel(" Valor unitário"));
		inserirPanel.add(panelAux1);


		JPanel panelAux2 = new JPanel();
		panelAux2.setLayout(new FlowLayout());
		panelAux2.add(inserirProduto);
		panelAux2.add(quantidadeProduto);
		panelAux2.add(valorProduto);
		panelAux2.setOpaque(false);
		inserirPanel.add(panelAux2);

		inserirProduto.setPreferredSize(new Dimension(1000, 20));

		valorProduto.setPreferredSize(new Dimension(60, 20));
		valorProduto.setEditable(false);

		quantidadeProduto.setText("1");
		quantidadeProduto.setPreferredSize(new Dimension(45, 20));

		this.add(inserirPanel, BorderLayout.NORTH);
	}

	@Override
	protected void paintComponent(final Graphics g){
		super.paintComponent(g);

		rect  = new Rectangle(0,0, this.getWidth(), this.getHeight());

		TexturePaint p = new TexturePaint(b, rect);
		Graphics2D g2 = (Graphics2D)g;

		g2.setPaint(p);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
