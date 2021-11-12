package br.com.edson.msorder.config.validacao;

public class ErroDeRequestDto {
    private String campo;
    private String erro;

    public ErroDeRequestDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
