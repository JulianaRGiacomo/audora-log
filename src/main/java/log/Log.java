package log;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

@Entity
public class Log {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String produto;

    private String cliente;

    private String categoria;

    private LocalDateTime dataHora;

    private String metodo;

    private String url;

    private String usuario;

    private int status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, Object> getJson(){
        Map<String, Object> log = new HashMap<>();
        Map<String, Object> registro = new HashMap<>();

        registro.put("metodo", this.metodo);
        registro.put("url", this.url);
        registro.put("usuario", this.usuario);
        registro.put("status", this.status);

        log.put("produto", this.produto);
        log.put("cliente", this.cliente);
        log.put("categoria", this.categoria);
        log.put("dataHora", this.dataHora);
        log.put("registro", registro);

        return log;
    }
}