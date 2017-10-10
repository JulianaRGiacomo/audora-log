# API de Logs
Esta é uma API de logs em Java Spring.

Para salvar logs é necessário estar autenticado. Já existe um usuário padrão estabelecido.

É um pré requisito ter o BD MySQL e nele ter criaco uma base `audora_log`, com usuário e senha `"audora"`.

A autenticação é via token JWT, sendo ele retornado no header da requisição `/login` sendo necessário enviá-lo em todos os endpoints protegidos.

Deve-se cadastrar no log:
* Produto
* Cliente
* Categoria
* Registro

Todos os campos são validados.

Registro é um *Map* que contém: método http, url acessada, usuário que acessou e status que obteve de resposta.

## Rotas
### **[POST]** `/api/log`
Cadastra um log.
#### Parametros esperados:
* produto
* cliente
* categoria
* metodo
* url
* usuario
* status

#### Retorno
Retorna o log cadastrado.

### **[GET]** `/api/log`
Recupera logs de acordo com os parâmetros da busca.
* produto
* cliente
* categoria
* intervalo de horário
  * dataInicial; (`dd/MM/aaaa`)
  * dataFinal; (`dd/MM/aaaa`)
  * horaInicial; (`hh:mm`)
  * horaFinal; (`hh:mm`)

### **[POST]** `/login`
Autentica usuário utilizando `username` : `"admin"` e `password` : `"password"`, retornando um token de autenticação no header.
