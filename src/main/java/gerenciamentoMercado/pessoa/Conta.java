package gerenciamentoMercado.pessoa;

/**
 * Created by nding on 15/06/2017.
 */
public class Conta {
    private String cpf;
    private String usuario;
    private String senha;
    private String email;

    public Conta (String cpf, String usuario, String senha, String email){
        this.cpf = cpf;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }
}
