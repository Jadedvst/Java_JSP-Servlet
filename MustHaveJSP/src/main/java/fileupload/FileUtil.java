package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.catalina.connector.InputBuffer;
import org.xml.sax.InputSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUtil {
	// 파일 업로드
	public static String uploadFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException {
		// Part객체를 통해 서버로 전송된 파일명 읽어오기
		Part part = req.getPart("ofile");

		// Part 객체의 헤더값 중 content-disposition 읽어오기
		String partHeader = part.getHeader("content-disposition");
		// 출력 결과 => partHeader=form-data; name="ofile"; filename="fileName.jpg"
		System.out.println("partHeader=" + partHeader);

		// 헤더값에서 파일명 잘라내기
		// 0: "partHeader=form-data; name="ofile";"
		// 1: "fileName.jpg"
		String[] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"", "");

		// 전송된 파일이 있다면 디렉토리에 저장 C:\temp\\upload\...\f.png"
		if (!originalFileName.isEmpty()) {
			part.write(sDirectory + File.separator + originalFileName);
		}
		return originalFileName;
	}

	// 다중 업로드
	public static ArrayList<String> multipleFile(HttpServletRequest req, String sDirectory)
			throws ServletException, IOException {

		ArrayList<String> listFileName = new ArrayList<String>();
		// Part객체를 통해 서버로 전송된 파일명 읽어오기
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			if (!part.getName().equals("ofile"))
				continue;

			// Part 객체의 헤더값 중 content-disposition 읽어오기
			String partHeader = part.getHeader("content-disposition");
			// 출력 결과 => partHeader=form-data; name="ofile"; filename="fileName.jpg"
			System.out.println("partHeader=" + partHeader);

			// 헤더값에서 파일명 잘라내기
			// 0: "partHeader=form-data; name="ofile";"
			// 1: "fileName.jpg"
			String[] phArr = partHeader.split("filename=");
			String originalFileName = phArr[1].trim().replace("\"", "");

			// 전송된 파일이 있다면 디렉토리에 저장 C:\temp\\upload\...\f.png"
			if (!originalFileName.isEmpty()) {
				part.write(sDirectory + File.separator + originalFileName);
			}
			listFileName.add(originalFileName);
		}
		return listFileName;
	}

	// 파일명 변경
	public static String renameFile(String sDirectory, String fileName) throws ServletException, IOException {
		// 원본파일 확장자 잘라내기
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);

		return newFileName;
	}

	public static String deleteFile(String sDirectory, String fileName) throws ServletException, IOException {
		File deleteFile = new File(sDirectory + File.separator + fileName);
		if (deleteFile.exists())
			deleteFile.delete();

		return fileName;
	}
	
	public static String deleteFile(HttpServletRequest req, String directory, String fileName) throws ServletException, IOException {
		String sDirectory = req.getServletContext().getRealPath(directory);
		File deleteFile = new File(sDirectory + File.separator + fileName);
		if (deleteFile.exists())
			deleteFile.delete();

		return fileName;
	}
	
	// 파일 다운로드
	public static void downloadFile(HttpServletRequest req, HttpServletResponse resp, 
			String directory, String sfileName, String ofileName){
		String sDirectory = req.getServletContext().getRealPath(directory);
		try {
			//find File and Create inputStream
			File file = new File(sDirectory,sfileName);
			InputStream iStream = new FileInputStream(file);
			
			//한글 깨짐 방지
			String client = req.getHeader("User-Agent");
			if(client.indexOf("WOW64")==-1) 
				ofileName = new String(ofileName.getBytes("UTF-8"),"ISO-8859-1");
			else 
				ofileName = new String(ofileName.getBytes("KSC5601"),"ISO-8859-1");
			
			// Set Response Header for File Download
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\""+ofileName+"\"");
			resp.setHeader("Content-Length"		, ""+ file.length());
			
			//out.clear() : 출력 스트림 초기화 서블릿에서는 예외가 발생하지 않는다, JSP에서는 중복으로 열릴시 에러
			
			//response 내장 객체로부터 새로운 출력스트림 생성
			OutputStream oStream = resp.getOutputStream();
			
			//출력 스트림에 파일 내용 출력
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while((readBuffer = iStream.read(b))>0) oStream.write(b, 0, readBuffer);
			iStream.close();
			oStream.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("예외 발생");
			e.printStackTrace();
		}
	}
}
