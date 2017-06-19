package gerenciamentoMercado.gui.abstractGUI;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.controlador.ControladoraTabela;
import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.action.TableGUIActions;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/**
 * Classe abstrata que representa uma aba do programa, que possua uma tabela
 */
public abstract class TableGUI extends JPanel{
    private MainGUI frame;
    private Dimension screenSize;
    private BancoDeDados bd;

    private JButton inserir = new JButton("Inserir - F4");
    private JButton remover = new JButton("Remover - F5");
    private JButton editar = new JButton("Editar - F6");
    private JButton buscar = new JButton("Buscar - F7");


    private JTextField campoBusca = new JTextField("");
    private DefaultTableModel modeloTabela = new DefaultTableModel();
    private JTable tabela;
    private JLabel label;

    /**
     * Contrutor da classe que configura a GUI
     * @param frame - MainGUI que contém a instancia dessa classe
     * @param screenSize - Dimension que contém o tamanho da tela
     */
    public TableGUI(MainGUI frame, Dimension screenSize){
        this.frame = frame;
        this.screenSize = screenSize;
        this.bd = frame.getBanco();
        this.label = criarGUILabel();

        this.setLayout(new BorderLayout());
        this.construirNorte();
        this.construirSul();
        this.construirCentro();
        this.construirLeste();
        this.construirOeste();
    }

    /**
     * Método que cria um JLabel com o nome da aba
     * @return - JLabel com o nome da aba
     */
    protected abstract JLabel criarGUILabel();

    public JTable getTabela() {
        return tabela;
    }

    /**
     * Método que constroi a borda oeste da GUI dessa aba
     */
    private void construirOeste() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        this.add(panel, BorderLayout.WEST);
    }

    /**
     * Método que constroi a borda leste da GUI dessa aba
     */
    private void construirLeste() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        this.add(panel, BorderLayout.EAST);
    }

    /**
     * Método que constroi a borda norte da GUI dessa aba
     */
    private void construirNorte(){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(label);

        this.add(panel, BorderLayout.NORTH);
    }

    /**
     * Método que constroi a borda sul da GUI dessa aba
     */
    private void construirSul() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        this.add(panel, BorderLayout.SOUTH);
    }

    /**
     * Método que constroi o centro da GUI dessa aba,
     * com uma tabela que contem as informações armazenadas no banco.
     * E configura os listeners dos botões contidos no centro
     */
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

        gerarKeyMap(controlador);
        
        campoBusca.setPreferredSize(new Dimension(200, 20));

        this.add(panel, BorderLayout.CENTER);

    }

    /**
     * Método que gera os atalhos do teclado
     */
    private void gerarKeyMap(ControladoraTabela controladorTabela){
        InputMap imap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap amap = this.getActionMap();

        amap.put("INSERIR", new TableGUIActions(this, controladorTabela, "INSERIR"));
        amap.put("REMOVER", new TableGUIActions(this, controladorTabela, "REMOVER"));
        amap.put("EDITAR", new TableGUIActions(this, controladorTabela, "EDITAR"));
        amap.put("BUSCAR", new TableGUIActions(this, controladorTabela, "BUSCAR"));

        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "INSERIR");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "REMOVER");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "EDITAR");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "BUSCAR");
    }

    /**
     * Método que controi a tabela na GUI dessa aba
     */
    protected abstract void criarTabela();

    /**
     * Método que atualiza a tabela, adicionando as informações armazenadas no banco de dados
     */
    public abstract void atualizarTabela();

    /**
     * Método que atualiza a tabela, adicionando as informações passadas através de um Vector
     * @param conteudo - Vector com os objetos a serem adicionados na tabela
     */
    public abstract void atualizarTabela(Vector conteudo);

    @Override
    protected void paintComponent(final Graphics g){
        super.paintComponent(g);

        BufferedImage backgroundImage = null;
        try {
            InputStream file = getClass().getResourceAsStream("/background.jpg");
            backgroundImage = ImageIO.read(file);

            Rectangle rect  = new Rectangle(0,0, this.getWidth(), this.getHeight());

            TexturePaint p = new TexturePaint(backgroundImage, rect);
            Graphics2D g2 = (Graphics2D)g;

            g2.setPaint(p);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        catch (Exception e) {
        ;
        }
    }

    protected MainGUI getFrame() {
        return frame;
    }

    protected void setFrame(MainGUI frame) {
        this.frame = frame;
    }

    protected BancoDeDados getBd() {
        return bd;
    }

    protected JButton getInserir() {
        return inserir;
    }

    protected void setInserir(JButton inserir) {
        this.inserir = inserir;
    }

    protected DefaultTableModel getModeloTabela() {
        return modeloTabela;
    }

    protected void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    public JTextField getCampoBusca() {
        return campoBusca;
    }
}
