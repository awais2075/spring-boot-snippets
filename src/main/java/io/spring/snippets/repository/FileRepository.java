package io.spring.snippets.repository;

import io.spring.snippets.model.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, String> {
}
