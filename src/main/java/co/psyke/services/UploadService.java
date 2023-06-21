package co.psyke.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.psyke.models.User;
import co.psyke.repositories.UserRepository;
import co.psyke.responses.UploadFileResponse;

import static co.psyke.validation.UserValidation.fieldsValidation;
@Service
public class UploadService {
	
	public static final String tmpDir = System.getProperty("java.io.tmpdir");

	@Autowired
	private UserRepository ur; 

	public File saveFileInTmp(MultipartFile file){
		File tmpFile=new File(tmpDir+file.getOriginalFilename()); 
		try {
			file.transferTo(tmpFile);
		} catch (IOException e) {
			e.printStackTrace();
			return null; 
		}
		return tmpFile; 
	}

	public UploadFileResponse saveFromCsv (File f){
		UploadFileResponse ufr = new UploadFileResponse();
		List<String> lines  = Collections.emptyList();
		List<String> results = new LinkedList<>();
		try {
			lines= Files.readAllLines(Path.of(f.getAbsolutePath()));
		} catch (Exception e) {
			ufr.setStatus(-1);
			ufr.setCsvValidation("cannot read the file:" + e.getMessage());
			return ufr;
		}
		int row=0; 
		boolean error=false;
		for(String s : lines){
			StringBuilder output=new StringBuilder(512).append("row "+ (row++) + ")");
			String [] fields = s.split(";"); 
			if(fields.length!=4){
				output.append("missing fields, it must be: name; last name; email;address");
				results.add(output.toString());
				error=true;
			} else {
				User u = new User();
				if(!fieldsValidation(fields, output)) {
					results.add(output.toString());
					error=true;
					continue;
				}

				u.setNome(fields[0]);
				u.setCognome(fields[1]);
				u.setEmail(fields[2]);
				u.setIndirizzo(fields[3]);

				Long id=ur.save(u).getId();
				output.append(id!=null? "added":"not added");
				
				results.add(output.toString());
			}
		}

		ufr.setCsvValidation(error?"some error reported":"all fine");
		ufr.setStatus(error?0:1);
		ufr.setRowByRowValidation(results);
		return ufr;
	}

	
}
