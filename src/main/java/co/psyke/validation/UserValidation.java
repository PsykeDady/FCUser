package co.psyke.validation;

public final class UserValidation {
	
	private UserValidation(){}


	public static boolean fieldsValidation(String[]fields, StringBuilder sb) {
		boolean flag=true;
		if(fields[0].isEmpty()){
			sb.append("empty first name");
			flag=false;
		}
		if(fields[1].isEmpty()){
			sb.append("empty last name");
			flag=false;
		}
		if(fields[2].isEmpty()){
			sb.append("empty email field");
			flag=false;
		}
		if(!fields[2].matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
			sb.append("empty email field");
			flag=false;
		}
		if(fields[3].isEmpty()){
			sb.append("empty address field");
			flag=false;
		}
		return flag; 
	}
}
