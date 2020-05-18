package org.sid.cinema.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sid.cinema.entities.Projection;
import org.sid.cinema.entities.Categorie;
import org.sid.cinema.entities.Cinema;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Place;
import org.sid.cinema.entities.Salle;
import org.sid.cinema.entities.Seance;
import org.sid.cinema.entities.Ticket;
import org.sid.cinema.entities.Ville;
import org.sid.cinema.repository.CategorieRepository;
import org.sid.cinema.repository.CinemaRepository;
import org.sid.cinema.repository.FilmRepository;
import org.sid.cinema.repository.PlaceRepository;
import org.sid.cinema.repository.ProjectionRepoitory;
import org.sid.cinema.repository.SalleRepository;
import org.sid.cinema.repository.SeanceRepository;
import org.sid.cinema.repository.TicketRepository;
import org.sid.cinema.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CinemaInitServiceImplementation implements ICinemaInitService {
   @Autowired
	private VilleRepository villeRepository;
   @Autowired
	private CinemaRepository cinemaRepository;
   @Autowired
  	private SalleRepository salleRepository;
    @Autowired
	private PlaceRepository placeRepository;
    @Autowired
   	private SeanceRepository seanceRepository;
    @Autowired
   	private CategorieRepository categorieRepository;
    @Autowired
   	private FilmRepository filmRepository;
    @Autowired
   	private ProjectionRepoitory projectionRepository;
    
    @Autowired
	private TicketRepository ticketRepository;
    
    @Override
    @Transactional

	public void initVilles() {
		
		Stream.of("Casablanca","Paris","Marrakech","Toulouse").forEach(v->{
			Ville ville=new Ville();
			ville.setName(v);
			villeRepository.save(ville);
		});
	}

	@Override
	@Transactional

	public void initCinemas() {
		// TODO Auto-generated method stub
		villeRepository.findAll().forEach(v->{
			Stream.of("Megarama","Pathé","UGC","MK2").forEach(c->{
				Cinema cinema=new Cinema();
				cinema.setName(c);
				cinema.setNombreSalles(3+(int) (Math.random()*7));
				cinema.setVille(v);
				
			    cinemaRepository.save(cinema);	
			});
			
			
		});
	}

	@Override
	@Transactional

	public void initSalles() {
		cinemaRepository.findAll().forEach(c->{
			for(int i=0;i<c.getNombreSalles();i++)
			{
				Salle salle=new Salle();
				salle.setName("Salle"+(i+1));
				salle.setCinema(c);
				salle.setNombrePlace(30+(int) (Math.random()*10));
				salleRepository.save(salle);
		 }
		});
	}

	@Override
	@Transactional

	public void initPlaces() {
		// TODO Auto-generated method stub
		salleRepository.findAll().forEach(salle->{
			for(int i=0;i<salle.getNombrePlace();i++) {
			Place place=new Place();
			place.setNumero(i+1);
			place.setSalle(salle);
			placeRepository.save(place);
			}
			});
	}

	@Override
	@Transactional
    public void initSeances() {
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");
		Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
		Seance seance=new Seance();
		try {
		seance.setHeureDebut(dateFormat.parse(s));
		seanceRepository.save(seance);
		} catch (ParseException e) {
		e.printStackTrace();
		}
		});
		
		
		
	}

	@Override
	@Transactional

	public void initCategorie() {
		// TODO Auto-generated method stub
		Stream.of("Action","Drame","Guerre","Fantastique","Science-fiction","Thriller").forEach(cat->{
			Categorie categorie=new Categorie();
			categorie.setName(cat);
			categorieRepository.save(categorie);
		});
	}

	@Override
	@Transactional

	public void initFilms() {
		// TODO Auto-generated method stub
		double[] durees=new double[] {1,1.5,2,2.5,3};
        List<Categorie> cat=categorieRepository.findAll(); 
		Stream.of("Lady Bird","The Perks of Being a Wallflower","Inception","Pulp Fiction","Fight Club",
					"Dunkirk","blade runner 2049").forEach(titre->{
						Film film=new Film();
						film.setTitre(titre);
						film.setDuree(durees[new Random().nextInt(durees.length)]);
						film.setPhoto(titre.replaceAll(" ", "")+".jpg");
						film.setCategorie(cat.get(new Random().nextInt(cat.size())));
						filmRepository.save(film);
					});
		
	}

	@Override
	@Transactional

	public void initProjection() {
		// TODO Auto-generated method stub
		double[] prices=new double[] {30,50,60,70,90,100};//
		villeRepository.findAll().forEach(ville->{
		ville.getCinemas().forEach(cinema->{
		cinema.getSalles().forEach(salle->{
		filmRepository.findAll().forEach(film->{
		seanceRepository.findAll().forEach(seance->{
		Projection projection=new Projection();
		projection.setDateProjection(new Date());
		projection.setFilm(film);
		projection.setPrix(prices[new Random().nextInt(prices.length)]);
		projection.setSalle(salle);
		projection.setSeance(seance);
		projectionRepository.save(projection);
		});
		});
		});
		});
		});
		
	}

	@Override
	@Transactional

	public void initTickets() {
		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
			Ticket ticket=new Ticket();
			ticket.setPlace(place);
			ticket.setPrix(p.getPrix());
			ticket.setProjection(p);
			ticket.setReserve(false);
			ticketRepository.save(ticket);
			});
			});
			}
		
	}


