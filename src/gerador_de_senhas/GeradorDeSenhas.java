package gerador_de_senhas;

import java.util.Scanner;
import java.time.LocalTime;

public class GeradorDeSenhas {
private final Scanner scanner = new Scanner(System.in);
private final Fila filaNormal = new Fila();
private final Fila filaPreferencial = new Fila();
private int totalSenhas = 0, totalAtendidas = 0, naoAtendidas = 0;
private int contadorSenhas = 1;
private long tempoTotalEspera = 0;
private int opcao;
    
    public static void main(String[] args) {
        new GeradorDeSenhas().executar();
    }
    
    public void executar() {
        do {
            System.out.println("\n1. Gerar Senha Normal");
            System.out.println("2. Gerar Senha Preferencial");
            System.out.println("3. Chamar Senha");
            System.out.println("4. Listar Senhas na Fila");
            System.out.println("5. Gerar Relatório");
            System.out.println("6. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            processarOpcao(opcao);
        } while (opcao != 6);
        System.out.println("\nEncerrando o programa...");
        scanner.close();
        System.exit(0);
    }
    
    private void processarOpcao(int opcao){
        switch(opcao){
            case 1:
                gerarSenha("Normal");
                break;
            case 2:
                gerarSenha("Preferencial");
                break;
            case 3:
                chamarSenha();
                break;
            case 4:
                listarSenhas();
                break;
            case 5:
                gerarRelatorio();
                break;
            case 6:
                System.out.println("Saindo...");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Opção inválida, tente novamente.");
        }
    
    }
    
    private void gerarSenha(String tipo) {
        Senha senha = new Senha(contadorSenhas++, tipo);
        if (tipo.equals("Preferencial")) {
            filaPreferencial.inserir(senha);
        } else {
            filaNormal.inserir(senha);
        }
        totalSenhas++;
        System.out.println("Senha gerada: " + senha.getNumero() + " - Tipo: " + tipo);
    }
    
    private Senha senhaPrioridade(){
        if(filaPreferencial.estaVazia()){
            return filaNormal.remover();
        }else{
            return filaPreferencial.remover();
        }
    }
    
    //////////////////////////////////////////////////
    private void chamarSenha() {
        Senha senha = senhaPrioridade();

        if (senha == null) {
            System.out.println("Nenhuma senha na fila.");
            return;
        }
        senha.incrementarTentativas();
        System.out.println("Chamando senha: " + senha.getNumero() + " - Tipo: " + senha.getTipo());
        System.out.print("A senha foi atendida? (s/n): ");
        char resposta = scanner.next().charAt(0);
        if (resposta == 's' || resposta == 'S') {
            senha.setHoraAtendida(LocalTime.now());
            senha.marcarAtendida();
            totalAtendidas++;
            tempoTotalEspera += senha.getHoraAtendida().toSecondOfDay() - senha.getHoraChegada().toSecondOfDay();
        } else if (senha.getTentativas() >= 3) {
            naoAtendidas++;
        } else {
            if (senha.getTipo().equals("Preferencial")) {
                filaPreferencial.inserir(senha);
            } else {
                filaNormal.inserir(senha);
            }
        }
    }
    
    private void listarSenhas() {
        System.out.println("\nSenhas Preferenciais:");
        filaPreferencial.listar();
        System.out.println("\nSenhas Normais:");
        filaNormal.listar();
    }
    
    private int obterTempoMedioDeEspera(){
            if(totalAtendidas == 0){
                return 0;
        }else{
                return (int) (tempoTotalEspera / totalAtendidas);
            }
    }
    
    private void gerarRelatorio() {
        System.out.println("Relatório de Atendimentos:");
        System.out.println("Total de senhas geradas: " + totalSenhas);
        System.out.println("Total de senhas atendidas: " + totalAtendidas);
        System.out.println("Tempo médio de espera: " + obterTempoMedioDeEspera() + " segundos");
        System.out.println("Senhas não atendidas após três chamadas: " + naoAtendidas);
    }
}
