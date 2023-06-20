package co.psyke.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor

@EqualsAndHashCode

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long id; 
	
	@NotBlank(message = "Il nome non può mancare")
	private String nome; 

	@NotBlank(message = "Il cognome non può mancare")
	private String cognome;

	@Email(message = "Email mancante o formato errato")
	private String email;

	@NotBlank(message = "L'indirizzo non può mancare")
	private String indirizzo;
	
}
