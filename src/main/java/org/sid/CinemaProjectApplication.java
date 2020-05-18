package org.sid;

import org.sid.cinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaProjectApplication implements CommandLineRunner{
	
	@Autowired private ICinemaInitService cinemaInitService;
	@Autowired private RepositoryRestConfiguration restConfiguration;
	public static void main(String[] args) {
		
		SpringApplication.run(CinemaProjectApplication.class, args);
	    
	}
	@Override
	public void run(String... args) throws Exception {
		cinemaInitService.initVilles(); 
		cinemaInitService.initCinemas();
		cinemaInitService.initSalles();
		cinemaInitService.initPlaces();
		cinemaInitService.initSeances();
		cinemaInitService.initCategorie();
		cinemaInitService.initFilms();
		cinemaInitService.initProjection();
		cinemaInitService.initTickets();
	}

	
	
	
}
