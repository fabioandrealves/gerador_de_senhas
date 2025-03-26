package gerador_de_senhas;

import java.util.Scanner;

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
            System.out.println("1. Gerar Senha Normal");
            System.out.println("2. Gerar Senha Preferencial");
            System.out.println("3. Chamar Senha");
            System.out.println("4. Listar Senhas na Fila");
            System.out.println("5. Gerar Relatório");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            processarOpcao(opcao);
        } while (opcao != 6);
        scanner.close();
    }
    
    private void processarOpcao(int opcao){
        switch(opcao){
            case 1:
                
        }
    
    }
    
    private void gerarSenha(String tipo){
        Senha senha = new Senha(contadorSenhas++, tipo);
        if (tipo.equals("Preferencial")) {
            filaPreferencial.inserir(senha);
        } else {
            filaNormal.remover(senha);
        }
        totalSenhas++;
        System.out.println("Senha gerada: " + senha.getNumero() + " - Tipo: " + tipo);
    }
    
    
    
}
