import java.util.*;
import java.io.InputStream;

public class JogoDaForca {
	private ArrayList<String> palavras = new ArrayList<>();
	private ArrayList<String> dicas = new ArrayList<>();
	private String palavraSorteada;
	private int acertos = 0;
	private int penalidade = 0;
	private ArrayList<String> letrasAdivinhadas = new ArrayList<>();
	private ArrayList<String> penalidadeMensagens = new ArrayList<>(Arrays.asList(
			"Sem penalidades",
			"Perdeu primeira perna",
			"Perdeu segunda perna",
			"Perdeu primeiro braço",
			"Perdeu segundo braço",
			"Perdeu tronco",
			"Perdeu cabeça"
	));

	public JogoDaForca() throws Exception {
		//
		//lê as palavras/dicas do arquivo palavras.txt. Lança a exceção “arquivo de palavras inexistente”, caso o
		//arquivo não exista dentro do projeto.
		InputStream stream = this.getClass().getResourceAsStream("dados/palavras.txt");
		if (stream == null)
			throw new Exception("Arquivo de palavras inexistente");
		Scanner arquivo = new Scanner(stream);

		// leitura das linhas do arquivo para as respectivas listas
		String linha;
		while (arquivo.hasNext()) {
			linha = arquivo.nextLine().toUpperCase();
			this.palavras.add(linha.split(";")[0]);
			this.dicas.add(linha.split(";")[1]);
		}
		arquivo.close();
	}

	public void iniciar() {
		//realiza o sorteio de uma palavra/dica.
		Random escolhaPD = new Random();
		palavraSorteada = palavras.get(escolhaPD.nextInt(palavras.size()));
	}

	public String getDica() {
		//retorna a dica associada à palavra sorteada no momento.
		int indexDica = palavras.indexOf(palavraSorteada);
		return dicas.get(indexDica);
	}

	public int getTamanho() {
		//retorna o tamanho da palavra sorteada no momento
		return palavraSorteada.length();
	}

	public ArrayList<Integer> getOcorrencias(String letra) throws Exception {
//		retorna uma lista com as ocorrências (1 a N) encontradas do parâmetro letra dentro da palavra sorteada ou
//	retorna uma lista vazia, caso contrário. Além disso, contabiliza um acerto, para cada ocorrência da letra
//	encontrada ou contabiliza uma penalidade, na ausência da mesma. Lançar uma exceção caso o parâmetro
//	letra for vazio, tiver mais de 1 caractere ou já tenha sido adivinhado anteriormente. O parâmetro letra pode
//	estar em maiúscula ou minúscula.

		ArrayList<Integer> ocorrencias = new ArrayList<>();

		if (letra.isEmpty()) {
			throw new Exception("Digite uma letra");
		}
		if (letra.length() > 1) {
			throw new Exception("Digite apenas uma letra");
		}
		if (letrasAdivinhadas.contains(letra.toUpperCase())){
			throw new Exception("Letra já foi usada");
		}
		if (!letra.matches( "^[a-zA-Z]+$")) {
			penalidade++;
			return ocorrencias;
		}
		String[] palavra = palavraSorteada.toUpperCase().split("");

		for (int i = 0; i < palavraSorteada.length(); i++) {
			if (palavra[i].matches(letra.toUpperCase())) {
				ocorrencias.add(i+1);
			}
		}
		if (ocorrencias.size() > 0) { //se houver ocorrências
			acertos += ocorrencias.size();
			letrasAdivinhadas.add(letra.toUpperCase());
		} else {                      //se não houver
			penalidade++;
		}
		return ocorrencias;
	}

	public boolean terminou() {
		if (penalidade >= 6) {
			return true;
		}
        return acertos == palavraSorteada.length();
		//retorna true se o jogo terminou.
    }

	public String getPalavraAdivinhada() {
		//retorna a palavra contendo as letras adivinhadas até o momento e “*” nas letras ainda não adivinhadas
		String palavraAdivinhada = "";
		String[] palavra = palavraSorteada.split("");
		for (String letra : palavra) {
			palavraAdivinhada += letrasAdivinhadas.contains(letra) ? letra : "*";
		}
		return palavraAdivinhada;
	}

	public int getAcertos() {
		return acertos;
		//retorna o total de acertos
	}
	
	public int getNumeroPenalidade() {
		return penalidade;
		//retorna o numero (0 a 6) da penalidade atual
	}
	
	public String getNomePenalidade() {
		return penalidadeMensagens.get(penalidade);
		//retorna o nome da penalidade atual
	}
	
	public String getResultado() {
		if (penalidade >= 6){
			return "Você foi enforcado!";
		}
		if (acertos == palavraSorteada.length()){
			return "Você ganhou!";
		}
		return "Jogo em andamento";
//		retorna uma das 3 opções: “você venceu” ou “você foi enforcado” (se o jogo terminou) ou “jogo em
//	andamento” (se o jogo ainda não terminou).
	}
	
}
