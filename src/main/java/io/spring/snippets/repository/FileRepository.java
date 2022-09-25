package io.spring.snippets.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import io.spring.snippets.model.File;

import java.util.Optional;

public interface FileRepository extends RedisDocumentRepository<File, String> {

    Optional<File> findByDateTime(String dateTime);
    Optional<File> findByFileName(String fileName);
}
