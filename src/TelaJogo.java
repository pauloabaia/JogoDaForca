import java.awt.*;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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
		frame.getContentPane().setBackground(new Color(102, 205, 170));
		frame.setBounds(100, 100, 480, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon forcaIcon = new ImageIcon(getClass().getResource("imagens/forca-icon1.png"));
		frame.setIconImage(forcaIcon.getImage());
		
		iniciarButton = new JButton("Iniciar");
		iniciarButton.setForeground(Color.WHITE);
		iniciarButton.setBackground(new Color(18, 92, 148));
		iniciarButton.setFont(new Font("Tahoma", Font.BOLD, 13));
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
					textFieldLetra.setEnabled(true);
					ImageIcon forcaTrans = new ImageIcon(getClass().getResource("imagens/"+jogo.getNumeroPenalidade()+".png"));
					labelForca.setIcon(forcaTrans);
					labelAviso.setText("");
					frame.getContentPane().setBackground(new Color(102, 205, 170));
				}
				catch (Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});
		iniciarButton.setBounds(181, 378, 95, 32);
		frame.getContentPane().add(iniciarButton);
		
		
		labelForca = new JLabel("New label");
		labelForca.setBounds(63, 99, 157, 142);
		frame.getContentPane().add(labelForca);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		ImageIcon forcaTrans = new ImageIcon(getClass().getResource("imagens/6.png"));
		labelForca.setIcon(forcaTrans);
		
		labelDica = new JLabel("");
		labelDica.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelDica.setForeground(new Color(0, 0, 0));
		labelDica.setBounds(113, 56, 253, 32);
		frame.getContentPane().add(labelDica);
		
		labelDica.setBackground(Color.BLACK);
		
		labelDicaTexto = new JLabel("Dica:");
		labelDicaTexto.setForeground(new Color(0, 0, 0));
		labelDicaTexto.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelDicaTexto.setBounds(63, 55, 56, 32);
		frame.getContentPane().add(labelDicaTexto);
		
		labelAcertosTexto = new JLabel("Acertos:");
		labelAcertosTexto.setForeground(new Color(0, 0, 0));
		labelAcertosTexto.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelAcertosTexto.setBounds(293, 173, 88, 14);
		frame.getContentPane().add(labelAcertosTexto);
		
		labelPenalidadesTexto = new JLabel("Penalidades:");
		labelPenalidadesTexto.setForeground(new Color(0, 0, 0));
		labelPenalidadesTexto.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelPenalidadesTexto.setBounds(293, 117, 117, 14);
		frame.getContentPane().add(labelPenalidadesTexto);
		
		labelPalavraText = new JLabel("Palavra:");
		labelPalavraText.setForeground(new Color(0, 0, 0));
		labelPalavraText.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelPalavraText.setBounds(63, 290, 88, 14);
		frame.getContentPane().add(labelPalavraText);
		
		labelPalavra = new JLabel("");
		labelPalavra.setForeground(new Color(0, 0, 0));
		labelPalavra.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelPalavra.setBounds(63, 315, 174, 20);
		frame.getContentPane().add(labelPalavra);
		
		
		labelLetra = new JLabel("Letra:");
		labelLetra.setForeground(new Color(0, 0, 0));
		labelLetra.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelLetra.setBounds(253, 290, 56, 14);
		frame.getContentPane().add(labelLetra);
		
		labelAviso = new JLabel("");
		labelAviso.setForeground(new Color(18, 92, 148));
		labelAviso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		labelAviso.setBounds(250, 310, 201, 20);
		frame.getContentPane().add(labelAviso);
		
		buttonIr = new JButton("Ir");
		buttonIr.setForeground(Color.WHITE);
		buttonIr.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irLetra();
			}
		});
		 
		textFieldLetra = new JTextField();
		textFieldLetra.setEnabled(false);
		textFieldLetra.setBounds(308, 289, 32, 20); //252
		frame.getContentPane().add(textFieldLetra);
		textFieldLetra.setColumns(10);
		textFieldLetra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					irLetra();
				}
			}
		});
		
		buttonIr.setBounds(350, 289, 48, 20);
		frame.getContentPane().add(buttonIr);
		buttonIr.setBackground(new Color(18, 92, 148));
		buttonIr.setEnabled(false);
		
		labelPenalidades = new JLabel("");
		labelPenalidades.setVerticalAlignment(SwingConstants.TOP);
		labelPenalidades.setForeground(Color.RED);
		labelPenalidades.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelPenalidades.setBounds(293, 142, 151, 20);
		frame.getContentPane().add(labelPenalidades);
		
		labelAcertos = new JLabel("");
		labelAcertos.setForeground(new Color(0, 128, 0));
		labelAcertos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelAcertos.setBounds(293, 190, 141, 14);
		frame.getContentPane().add(labelAcertos);
	}
	
	public void irLetra() {
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
				ImageIcon forcaTrans = new ImageIcon(getClass().getResource("imagens/"+ jogo.getNumeroPenalidade()+".png" ));
				labelForca.setIcon(forcaTrans);
			}
			labelAviso.setText("");
			textFieldLetra.setText("");
			if (jogo.terminou()){
				labelAviso.setText(jogo.getResultado());
				buttonIr.setEnabled(false);
				textFieldLetra.setEnabled(false);
				if (jogo.getResultado().matches("Você foi enforcado!")) {
					frame.getContentPane().setBackground(new Color(255, 102, 102));
				}
				else {
					frame.getContentPane().setBackground(new Color(0, 255, 128));
				}
			}
		}
		catch (Exception ex) {
			labelAviso.setText(ex.getMessage());
			textFieldLetra.setText("");
		}
	}
}
