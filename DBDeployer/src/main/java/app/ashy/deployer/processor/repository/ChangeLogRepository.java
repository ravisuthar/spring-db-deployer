package app.ashy.deployer.processor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.ashy.deployer.entity.ChangeLogBean;

@Repository
public interface ChangeLogRepository extends CrudRepository<ChangeLogBean, Long> {

	public ChangeLogBean findTopByOrderByIdentifierDesc();
	
	public ChangeLogBean findByIdentifier(long id);
}
