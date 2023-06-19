package co.psyke.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor

public class User {

	private String nome; 

	private String cognome;

	private String email;

	private String indirizzo;
	
}
