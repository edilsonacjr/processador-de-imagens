package entidades;

import java.io.InputStream;

public class Imagem {

    private int cod;
    private InputStream imagem;
    private int usuario;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public InputStream getImagem() {
        return imagem;
    }

    public void setImagem(InputStream imagem) {
        this.imagem = imagem;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

}
