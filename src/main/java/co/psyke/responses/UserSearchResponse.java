package co.psyke.responses;

import java.util.List;

import co.psyke.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor

@EqualsAndHashCode
public class UserSearchResponse {
	
	private List<User> byName;
	private List<User> byLastName;
	private List<User> byAddress;

}
