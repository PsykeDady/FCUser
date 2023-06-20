package co.psyke.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.psyke.responses.UploadFileResponse;
import co.psyke.services.UploadService;

public class UploadController {
	
	@Autowired
	private UploadService us;

	/**
	 * Add or update user information uploading a csv.
	 * <br><br>
	 * CSV Format:<br> 
	 * <pre>name;last name;email;address</pre>
	 * 
	 * 
	 * @param file in csv format
	 * @return 
	 */
	@PostMapping("/csvupload")
	private ResponseEntity<UploadFileResponse> uploadFile(@RequestParam MultipartFile file) {

		UploadFileResponse ufr=new UploadFileResponse();
		
		File f = us.saveFileInTmp(file);
		if(f==null) {
			ufr.setStatus(-1);
			ufr.setCsvValidation("cannot save CSV file on FS");
			return ResponseEntity.badRequest().body(ufr);
		}

		ufr = us.saveFromCsv(f);


		return ufr.getStatus()>0 ? ResponseEntity.ok().body(ufr) : ResponseEntity.badRequest().body(ufr); 

	}
}
