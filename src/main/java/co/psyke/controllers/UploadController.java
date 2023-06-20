package co.psyke.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.psyke.services.UploadService;

public class UploadController {
	
	@Autowired
	private UploadService us;

	@PostMapping("/csvupload")
	private ResponseEntity<Boolean> uploadFile(@RequestParam MultipartFile file) {

		us.



		return ResponseEntity.ok().body(true); 

	}
}
