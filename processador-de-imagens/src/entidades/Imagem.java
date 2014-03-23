package entidades;

import java.awt.Image;
import java.io.InputStream;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class Imagem implements Serializable{

    private int cod;
    private String nome;
    //private InputStream imagem;
    private ImageIcon imagem;
    private int usuario;
    private String processamento;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public ImageIcon getImagem() {
        return imagem;
    }

    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProcessamento() {
        return processamento;
    }

    public void setProcessamento(String processamento) {
        this.processamento = processamento;
    }

}
