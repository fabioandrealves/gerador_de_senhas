package gerador_de_senhas;

import java.time.LocalTime;

class Senha {
    private final int numero;
    private final String tipo;
    private final LocalTime horaChegada;
    private LocalTime horaAtendida;
    private int tentativas;
    private boolean atendida;

    public Senha(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.horaChegada = LocalTime.now();
        this.tentativas = 0;
        this.atendida = false;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalTime getHoraChegada() {
        return horaChegada;
    }

    public LocalTime getHoraAtendida() {
        return horaAtendida;
    }

    public void setHoraAtendida(LocalTime horaAtendida) {
        this.horaAtendida = horaAtendida;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void incrementarTentativas() {
        this.tentativas++;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void marcarAtendida() {
        this.atendida = true;
    }
}