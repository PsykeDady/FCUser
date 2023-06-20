package co.psyke.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.psyke.repositories.UserRepository;

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

	public void saveFromCsv (File f){
		List<String> lines= Collections.EMPTY_LIST;
		try {
			lines= Files.readAllLines(Path.of(f.getAbsolutePath(), null));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
