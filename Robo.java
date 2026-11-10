public abstract class Robo {
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

    // Ordem do cálculo:
    // 1) ataque + modificador do agressor (sem alterar o atributo ataque)
    // 2) subtrai a defesa do alvo
    // 3) aplica o dano mínimo original
    // 4) soma 5 nas rodadas pares
    // (a redução do robô defensivo é feita depois, em receberDano)
    protected int danoBase(Robo alvo, int rodada, int bonusAtaque) {
        int ataqueEfetivo = this.ataque + bonusAtaque;
        int dano = ataqueEfetivo - alvo.defesa;
        if (dano < 0) {
            dano = 0;
        } else if (dano < 5) {
            dano = 5;
        }
        if (rodada % 2 == 0) {
            dano += 5;
        }
        return dano;
    }

    public abstract int calcularDano(Robo alvo, int rodada);

    public abstract void receberDano(int dano);

    public abstract String getTipo();

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
        System.out.println("Tipo: "+getTipo());
        System.out.println("Ataque: "+ataque);
        System.out.println("Defesa: "+defesa);
        System.out.println("Energia atual: "+energiaAtual);
        System.out.println("Situação: " +verificarSituacao());
        System.out.println("Vitorias: "+vitoria);
        System.out.println("Derrotas: "+derrota);
        System.out.println("Pontos: "+pontos);
    }

    public void RecuperaEnergia(int qtdeEnergia){
        int custoPonto = qtdeEnergia / 10;

        this.energiaAtual += qtdeEnergia; 
        this.pontos -= custoPonto; 
    }

    public boolean ExcluirRobo(int codigoExclusao){
      return this.vitoria == 0 && this.derrota == 0; 
    }
}