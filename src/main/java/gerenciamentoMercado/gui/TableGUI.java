package gerenciamentoMercado.gui;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.controlador.ControladoraTabela;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by nding on 12/06/2017.
 */
public abstract class TableGUI extends JPanel{
    private MainGUI frame;
    private Dimension screenSize;
    private BancoDeDados bd;

    private JButton inserir = new JButton("Inserir");
    private JButton remover = new JButton("Remover");
    private JButton editar = new JButton("Editar");
    private JButton buscar = new JButton("Buscar");
    private JTextField campoBusca = new JTextField("");
    private DefaultTableModel modeloTabela = new DefaultTableModel();
    private JTable tabela;

    public TableGUI(MainGUI frame, Dimension screenSize){
        this.frame = frame;
        this.screenSize = screenSize;
        this.bd = frame.getBanco();

        this.setLayout(new BorderLayout());
        this.construirNorte();
        this.construirSul();
        this.construirCentro();
        this.construirLeste();
        this.construirOeste();
    }

    public JTable getTabela() {
        return tabela;
    }

    private void construirOeste() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        this.add(panel, BorderLayout.WEST);
    }

    private void construirLeste() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        this.add(panel, BorderLayout.EAST);
    }

    private void construirNorte(){
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        this.add(panel, BorderLayout.NORTH);
    }

    private void construirSul() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        this.add(panel, BorderLayout.SOUTH);
    }

    private void construirCentro(){
    	ControladoraTabela controlador = new ControladoraTabela(frame, this);
    	
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        criarTabela();
        JScrollPane rolagem = new JScrollPane(tabela);
        panel.add(rolagem, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new FlowLayout());
        botoes.add(inserir);
        botoes.add(remover);
        botoes.add(editar);
        botoes.add(campoBusca);
        botoes.add(buscar);
        panel.add(botoes, BorderLayout.NORTH);

        inserir.setActionCommand("INSERIR");
        remover.setActionCommand("REMOVER");
        editar.setActionCommand("EDITAR");
        buscar.setActionCommand("BUSCAR");

        inserir.addActionListener(controlador);
        remover.addActionListener(controlador);
        editar.addActionListener(controlador);
        buscar.addActionListener(controlador);
        
        campoBusca.setPreferredSize(new Dimension(200, 20));

        this.add(panel, BorderLayout.CENTER);

    }

    protected abstract void criarTabela();

    public abstract void atualizarTabela();

    @Override
    protected void paintComponent(final Graphics g){
        super.paintComponent(g);

        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File(".\\images\\background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle rect  = new Rectangle(0,0, this.getWidth(), this.getHeight());

        TexturePaint p = new TexturePaint(backgroundImage, rect);
        Graphics2D g2 = (Graphics2D)g;

        g2.setPaint(p);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    protected MainGUI getFrame() {
        return frame;
    }

    protected void setFrame(MainGUI frame) {
        this.frame = frame;
    }

    protected Dimension getScreenSize() {
        return screenSize;
    }

    protected void setScreenSize(Dimension screenSize) {
        this.screenSize = screenSize;
    }

    protected BancoDeDados getBd() {
        return bd;
    }

    protected void setBd(BancoDeDados bd) {
        this.bd = bd;
    }

    protected JButton getInserir() {
        return inserir;
    }

    protected void setInserir(JButton inserir) {
        this.inserir = inserir;
    }

    protected JButton getRemover() {
        return remover;
    }

    protected void setRemover(JButton remover) {
        this.remover = remover;
    }

    protected JButton getEditar() {
        return editar;
    }

    protected void setEditar(JButton editar) {
        this.editar = editar;
    }

    protected DefaultTableModel getModeloTabela() {
        return modeloTabela;
    }

    protected void setModeloTabela(DefaultTableModel modeloTabela) {
        this.modeloTabela = modeloTabela;
    }

    protected void setTabela(JTable tabela) {
        this.tabela = tabela;
    }
}
