package org.sid.cinema.repository;
import org.sid.cinema.entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectionRepoitory extends
JpaRepository<Projection,Long> {
}