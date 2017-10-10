package log;

import org.springframework.data.repository.CrudRepository;

import log.Log;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends CrudRepository<Log, Long>{

    List<Log> findByProduto(String produto);

    List<Log> findByCliente(String cliente);

    List<Log> findByCategoria(String categora);

    List<Log> findByProdutoAndCategoria(String produto, String categoria);

    List<Log> findByProdutoAndCliente(String Produto, String cliente);

    List<Log> findByClienteAndCategoria(String cliente, String categoria);

    List<Log> findByClienteAndCategoriaAndProduto(String cliente, String categoria, String produto);

    List<Log> findByDataHoraBetween(LocalDateTime data1, LocalDateTime data2);

    List<Log> findByDataHoraBetweenAndProduto(LocalDateTime data1, LocalDateTime data2, String produto);

    List<Log> findByDataHoraBetweenAndCliente(LocalDateTime data1, LocalDateTime data2, String cliente);

    List<Log> findByDataHoraBetweenAndCategoria(LocalDateTime data1, LocalDateTime data2, String categoria);

    List<Log> findByDataHoraBetweenAndProdutoAndCategoria(LocalDateTime data1, LocalDateTime data2,
                                                          String produto, String categoria);

    List<Log> findByDataHoraBetweenAndProdutoAndCliente(LocalDateTime data1, LocalDateTime data2,
                                                          String produto, String cliente);

    List<Log> findByDataHoraBetweenAndClienteAndCategoria(LocalDateTime data1, LocalDateTime data2,
                                                         String cliente, String categoria);

    List<Log> findByDataHoraBetweenAndClienteAndCategoriaAndProduto(LocalDateTime data1, LocalDateTime data2,
                                                          String cliente, String categoria, String Produto);
}