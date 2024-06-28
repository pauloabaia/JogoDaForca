import java.awt.*;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class TelaJogo {
	private JFrame frame;
	private JButton iniciarButton;
	private JLabel labelForca;
	private JLabel labelDica;
	private JLabel labelDicaTexto;
	private JLabel labelAcertosTexto;
	private JLabel labelPenalidadesTexto;
	private JLabel labelPalavraText;
	private JLabel labelPalavra;
	private JLabel labelLetra;
	private JTextField textFieldLetra;
	private JLabel labelAviso;
	private JButton buttonIr;
	private JLabel labelPenalidades;
	private JLabel labelAcertos;
	private JogoDaForca jogo;
	private ArrayList<Integer> ocorrencias;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaJogo window = new TelaJogo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaJogo() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frame = new JFrame();
		frame.setBounds(100, 100, 380, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon forcaIcon = new ImageIcon(getClass().getResource("imagens/forca-icon1.png"));
		frame.setIconImage(forcaIcon.getImage());
		
		iniciarButton = new JButton("Iniciar");
		iniciarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jogo = new JogoDaForca();
					jogo.iniciar();
					labelDica.setText(jogo.getDica());
					labelPenalidades.setText(jogo.getNomePenalidade());
					labelAcertos.setText(Integer.toString(jogo.getAcertos()));
					labelPalavra.setText(jogo.getPalavraAdivinhada());
					buttonIr.setEnabled(true);
					ImageIcon forcaTrans = new ImageIcon(getClass().getResource("imagens/"+jogo.getNumeroPenalidade()+".png"));
					labelForca.setIcon(forcaTrans);
				}
				catch (Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});
		iniciarButton.setBounds(147, 11, 89, 23);
		frame.getContentPane().add(iniciarButton);
		
		
		labelForca = new JLabel("New label");
		labelForca.setBounds(20, 99, 157, 142);
		frame.getContentPane().add(labelForca);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		ImageIcon forcaTrans = new ImageIcon(getClass().getResource("imagens/6.png"));
		labelForca.setIcon(forcaTrans);
		
		labelDica = new JLabel("");
		labelDica.setBounds(74, 56, 253, 32);
		frame.getContentPane().add(labelDica);
		
		labelDica.setBackground(new Color(0,0,0,0));
		
		labelDicaTexto = new JLabel("Dica:");
		labelDicaTexto.setBounds(30, 56, 46, 32);
		frame.getContentPane().add(labelDicaTexto);
		
		labelAcertosTexto = new JLabel("Acertos:");
		labelAcertosTexto.setBounds(213, 149, 58, 14);
		frame.getContentPane().add(labelAcertosTexto);
		
		labelPenalidadesTexto = new JLabel("Penalidades:");
		labelPenalidadesTexto.setBounds(213, 100, 79, 14);
		frame.getContentPane().add(labelPenalidadesTexto);
		
		labelPalavraText = new JLabel("Palavra:");
		labelPalavraText.setBounds(20, 275, 56, 14);
		frame.getContentPane().add(labelPalavraText);
		
		labelPalavra = new JLabel("");
		labelPalavra.setBounds(74, 275, 222, 14);
		frame.getContentPane().add(labelPalavra);
		
		
		labelLetra = new JLabel("Letra:");
		labelLetra.setBounds(213, 224, 37, 14);
		frame.getContentPane().add(labelLetra);
		
		textFieldLetra = new JTextField();
		textFieldLetra.setBounds(252, 221, 32, 20); //252
		frame.getContentPane().add(textFieldLetra);
		textFieldLetra.setColumns(10);
		
		labelAviso = new JLabel("");
		labelAviso.setBounds(213, 245, 79, 14);
		frame.getContentPane().add(labelAviso);
		
		buttonIr = new JButton("Ir");
		buttonIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ocorrencias = jogo.getOcorrencias(textFieldLetra.getText());
					if (ocorrencias.size() > 0) {
						labelAviso.setText("Você acertou a letra "+ textFieldLetra.getText());
						labelAcertos.setText(Integer.toString(jogo.getAcertos()));
						labelPalavra.setText(jogo.getPalavraAdivinhada());
					}
					else {
						labelAviso.setText("Você errou a letra " + textFieldLetra.getText());
						labelPenalidades.setText(jogo.getNomePenalidade());
						ImageIcon forcaTrans = new ImageIcon(getClass().getResource("projeto1-Jogo-da-forca/src/imagens/"+jogo.getNumeroPenalidade()+".png"));
						labelForca.setIcon(forcaTrans);
					}
					labelAviso.setText("");
					textFieldLetra.setText("");
					if (jogo.terminou()){
						labelAviso.setText(jogo.getResultado());
						buttonIr.setEnabled(false);
					}
				}
				catch (Exception ex) {
					labelAviso.setText(ex.getMessage());
				}
			}
		});
		buttonIr.setBounds(294, 221, 43, 20);
		frame.getContentPane().add(buttonIr);
		buttonIr.setBackground(new Color(255,255,255,255));
		buttonIr.setEnabled(false);
		
		labelPenalidades = new JLabel("");
		labelPenalidades.setBounds(213, 124, 117, 14);
		frame.getContentPane().add(labelPenalidades);
		
		labelAcertos = new JLabel("");
		labelAcertos.setBounds(213, 174, 117, 14);
		frame.getContentPane().add(labelAcertos);
	}
}
