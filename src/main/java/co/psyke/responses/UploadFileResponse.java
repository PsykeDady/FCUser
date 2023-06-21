package co.psyke.responses;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor

@EqualsAndHashCode
@ResponseBody
public class UploadFileResponse {
	private Integer status; 
	private String csvValidation; 
	private List<String> rowByRowValidation; 
}
