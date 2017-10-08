package log;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public String check(){
        boolean check = this.produto == null || this.produto.equals("defensoria") || this.produto.equals("OAB");
        if(!check)
            return "Produto inválido";

        if(usuario == null || url == null || metodo == null)
            return  "Dados não informados";

        if(!this.metodos().contains(metodo))
            return "Método inválido";

        if(status < 100 && status > 600)
            return "Status inválido";

        check = this.cliente == null || this.clientes().contains(this.cliente);
        if(!check)
            return "Cliente inválido";

        check = this.categoria == null || this.categoria().contains(this.categoria);
        if(!check)
            return "Categoria inválida";

        return null;
    }

    private List clientes(){
        List<String> clientes = new ArrayList<String>();
        clientes.add("Alagoas");
        clientes.add("Sergipe");
        clientes.add("Bahia");
        clientes.add("Ceará");
        clientes.add("Pernambuco");
        clientes.add("Maranhão");
        clientes.add("Piauí");
        clientes.add("Rio Grande do Norte");
        clientes.add("Paraíba");

        return clientes;
    }

    private List categoria(){
        List<String> categoria = new ArrayList<String>();
        categoria.add("erro");
        categoria.add("permissão");
        categoria.add("autenticação");
        categoria.add("requisição");
        categoria.add("conexão");

        return categoria;
    }

    private List metodos (){
        List<String> metodos = new ArrayList<String>();
        metodos.add("POST");
        metodos.add("GET");
        metodos.add("PUT");
        metodos.add("DELETE");

        return metodos;
    }

    public static LocalDateTime formatarPrazo(String prazo) {
        String pattern = "((0[1-9])|([1-2][0-9])|(3[0-1]))\\/((0[1-9])|(1[0-2]))\\/\\d{4}((0[0-9])|(1[0-9])|(2[0-3])):((0[0-9])|([1-5][0-9]))";
        if(prazo.matches(pattern)){
            int dia = Integer.parseInt(prazo.substring(0, 2));
            int mes = Integer.parseInt(prazo.substring(3, 5));
            int ano = Integer.parseInt(prazo.substring(6, 10));
            int hora = Integer.parseInt(prazo.substring(10, 12));
            int minuto = Integer.parseInt(prazo.substring(13, 15));
            return LocalDateTime.of(ano, mes, dia, hora, minuto);
        }
        return null;
    }
}