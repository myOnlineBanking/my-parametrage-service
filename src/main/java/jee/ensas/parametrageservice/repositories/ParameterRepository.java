package jee.ensas.parametrageservice.repositories;

import jee.ensas.parametrageservice.daos.Parameter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ParameterRepository extends MongoRepository<Parameter, String> {

}
