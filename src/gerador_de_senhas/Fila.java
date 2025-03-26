package gerador_de_senhas;


class Fila {
    private No inico, fim;
    private int tamanho;

    public boolean estaVazia() {
        return inico == null;
    }

    public void inserir(Senha senha) {
        No novoNo = new No(senha);
        if (fim == null) {
            inico = fim = novoNo;
        } else {
            fim.setProximo(novoNo);
            fim = novoNo;
        }
        tamanho++;
    }

    public Senha remover() {
        if (estaVazia()) return null;
        Senha senha = inico.getSenha();
        inico = inico.getProximo();
        if (inico == null) fim = null;
        tamanho--;
        return senha;
    }

    public void listar() {
        No temp = inico;
        while (temp != null) {
            System.out.println("Senha: " + temp.getSenha().getNumero() + " - Tipo: " + temp.getSenha().getTipo());
            temp = temp.getProximo();
        }
    }
}
