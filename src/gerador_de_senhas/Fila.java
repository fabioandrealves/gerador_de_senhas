package gerador_de_senhas;


class Fila {
    private No proximo, anterior;
    private int tamanho;

    public boolean estaVazia() {
        return proximo == null;
    }

    public void inserir(Senha senha) {
        No novoNo = new No(senha);
        if (anterior == null) {
            proximo = anterior;
            anterior = novoNo;
        } else {
            anterior.setProximo(novoNo);
            anterior = novoNo;
        }
        tamanho++;
    }

    public Senha remover() {
        if (estaVazia()) return null;
        Senha senha = proximo.getSenha();
        proximo = proximo.getProximo();
        if (proximo == null) anterior = null;
        tamanho--;
        return senha;
    }

    public void listar() {
        No temp = proximo;
        while (temp != null) {
            System.out.println("Senha: " + temp.getSenha().getNumero() + " - Tipo: " + temp.getSenha().getTipo());
            temp = temp.getProximo();
        }
    }
}
