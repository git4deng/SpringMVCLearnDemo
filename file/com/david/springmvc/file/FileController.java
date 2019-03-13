package com.david.springmvc.file;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc,@RequestParam("file") MultipartFile file) throws IOException{
		
		System.out.println("desc:"+desc);
		System.out.println("OriginalFilename:"+file.getOriginalFilename());
		System.out.println("InputStream:"+file.getInputStream());
		System.out.println("Name:"+file.getName());
		return "success";
	}
}
