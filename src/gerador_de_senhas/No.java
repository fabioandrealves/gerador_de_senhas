package gerador_de_senhas;

class No {
    private final Senha senha;
    private No proximo;

    public No(Senha senha) {
        this.senha = senha;
    }

    public Senha getSenha() {
        return senha;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
