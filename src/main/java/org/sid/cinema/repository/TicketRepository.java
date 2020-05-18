package org.sid.cinema.repository;

import org.sid.cinema.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketRepository extends
JpaRepository<Ticket,Long> {
}