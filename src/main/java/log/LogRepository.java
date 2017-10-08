package log;

import org.springframework.data.repository.CrudRepository;

import log.Log;

public interface LogRepository extends CrudRepository<Log, Long>{
}