public class Robo {
    int codigo; 
    String nome;
    int ataque; 
    int defesa;
    int energiaAtual;
    int vitoria; 
    int derrota;
    int pontos;

    public Robo(int codigo, String nome, int ataque, int defesa){
        this.codigo = codigo; 
        this.nome = nome; 
        this.ataque = ataque; 
        this.defesa = defesa;
        this.energiaAtual = 100;
        this.vitoria = 0; 
        this.derrota = 0; 
        this.pontos = 0; 
    }

    public String verificarSituacao() {
        if (energiaAtual >= 30) {
            return "Disponível";
        } else {
            return "Em recuperação";
        }
    }
    public void ExibirSituacao(){
        System.out.println("Codigo: "+codigo);
        System.out.println("Nome: "+nome);
        System.out.println("Ataque: "+ataque);
        System.out.println("Defesa: "+defesa);
        System.out.println("Energia atual: "+energiaAtual);
        System.out.println("Situação: " +verificarSituacao());
        System.out.println("Vitorias: "+vitoria);
        System.out.println("Derrotas: "+derrota);
        System.out.println("Pontos: "+pontos);

    }
}
