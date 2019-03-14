package com.david.springmvc.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	private static final String FILE_PATH="F:\\learn\\springmvc1";
	@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc,@RequestParam("file") MultipartFile file,Map<String,Object> map) throws IOException{
		
		String fileRealName = file.getOriginalFilename();
		System.out.println("desc:"+desc);
		System.out.println("OriginalFilename:"+fileRealName);
		//验证文件存储路径是否存在
		File f=new File(FILE_PATH);
		if(!f.exists()){
			//如果不存在，则创建一个目录
			f.mkdir();
		}
		
		String prefix=UUID.randomUUID().toString();
		prefix=prefix.replace("-", "");
		String fileName=prefix+"_"+fileRealName;//使用UUID作为前缀防止文件名重复而导致文件被覆盖
		InputStream in = file.getInputStream();
		//获取输出流
		OutputStream out=new FileOutputStream(new File(FILE_PATH+"\\"+fileName));//指定输出流的位置;
		byte []buffer =new byte[1024];
		 int len=0;
        while((len=in.read(buffer))!=-1){
            out.write(buffer, 0, len);
            out.flush();                
        }
                                        
        out.close();
        in.close();
        map.put("path", FILE_PATH+"\\"+fileName);
        map.put("filename",fileRealName);
		return "success";
	}
	@RequestMapping("/filedownload")
	public ResponseEntity<byte[]> download(@RequestParam("path") String path,@RequestParam("fileName") String fileName) throws IOException{
		//验证 文件是否存在
		File file=new File(path);
		if(!file.exists()) throw new RuntimeException("文件不存在！");
		
		InputStream in=new FileInputStream(file);
		byte[] body=null;
        body=new byte[in.available()];
        in.read(body);
        fileName=new String(fileName.getBytes("gbk"),"iso8859-1");//防止中文乱码
        HttpHeaders headers=new HttpHeaders();//设置响应头
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        HttpStatus statusCode = HttpStatus.OK;//设置响应吗
        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
        
        return response;
	}
	
	@RequestMapping("/testPatchFileUpload")
	public String testPatchFileUpload(@RequestParam("desc") String desc,@RequestParam("files") MultipartFile[] files,Map<String,Object> map) throws IOException{
		
		List<String> paths=new ArrayList<String>();
		List<String> names=new ArrayList<String>();
		for(MultipartFile file :files){
			
			String fileRealName = file.getOriginalFilename();
			
			System.out.println("desc:"+desc);
			System.out.println("OriginalFilename:"+fileRealName);
			//验证文件存储路径是否存在
			File f=new File(FILE_PATH);
			if(!f.exists()){
				//如果不存在，则创建一个目录
				f.mkdir();
			}
			String prefix=UUID.randomUUID().toString();
			prefix=prefix.replace("-", "");
			String fileName=prefix+"_"+fileRealName;//使用UUID作为前缀防止文件名重复而导致文件被覆盖
			InputStream in = file.getInputStream();
			//获取输出流
			OutputStream out=new FileOutputStream(new File(FILE_PATH+"\\"+fileName));//指定输出流的位置;
			byte []buffer =new byte[1024];
			 int len=0;
	        while((len=in.read(buffer))!=-1){
	            out.write(buffer, 0, len);
	            out.flush();                
	        }                             
	        out.close();
	        in.close();
	        paths.add(FILE_PATH+"\\"+fileName);
	        names.add(fileRealName);
		}
        map.put("paths", FILE_PATH+"\\"+paths);
        map.put("filenames",names);
		return "success";
	}
	
}
