package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
	//파일 업로드
	public static String uploadFile(HttpServletRequest req, String sDirectory)
		throws ServletException, IOException{
		//Part객체를 통해 서버로 전송된 파일명 읽어오기
		Part part=req.getPart("ofile");
		
		//Part 객체의 헤더값 중 content-disposition 읽어오기
		String partHeader = part.getHeader("content-disposition");
		// 출력 결과 => partHeader=form-data; name="ofile"; filename="fileName.jpg"
		System.out.println("partHeader="+partHeader);
		
		//헤더값에서 파일명 잘라내기
		//0: "partHeader=form-data; name="ofile";"
		//1: "fileName.jpg"
		String[] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"", "");
		
		//전송된 파일이 있다면 디렉토리에 저장 C:\temp\\upload\...\f.png"
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory+File.separator+originalFileName);
		}
		return originalFileName;
	}
	//다중 업로드
	public static ArrayList<String> multipleFile(HttpServletRequest req, String sDirectory)
			throws ServletException, IOException{
			
			ArrayList<String> listFileName = new ArrayList<String>();
			//Part객체를 통해 서버로 전송된 파일명 읽어오기
			Collection<Part> parts = req.getParts();
			for(Part part:parts) {
				if(!part.getName().equals("ofile")) continue;
				
				//Part 객체의 헤더값 중 content-disposition 읽어오기
				String partHeader = part.getHeader("content-disposition");
				// 출력 결과 => partHeader=form-data; name="ofile"; filename="fileName.jpg"
				System.out.println("partHeader="+partHeader);
				
				//헤더값에서 파일명 잘라내기
				//0: "partHeader=form-data; name="ofile";"
				//1: "fileName.jpg"
				String[] phArr = partHeader.split("filename=");
				String originalFileName = phArr[1].trim().replace("\"", "");
				
				//전송된 파일이 있다면 디렉토리에 저장 C:\temp\\upload\...\f.png"
				if(!originalFileName.isEmpty()) {
					part.write(sDirectory+File.separator+originalFileName);
				}
				listFileName.add(originalFileName);
			}
		return listFileName;
		}
	
	//파일명 변경
	public static String renameFile(String sDirectory, String fileName)
		throws ServletException, IOException{
		//원본파일 확장자 잘라내기
		String ext =fileName.substring(fileName.lastIndexOf("."));
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;
		File oldFile = new File (sDirectory + File.separator + fileName);
		File newFile = new File (sDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}
	
	public static String deleteFile(String sDirectory, String fileName)
		throws ServletException, IOException{
		File deleteFile = new File (sDirectory + File.separator + fileName);
		if( deleteFile.exists() )
			deleteFile.delete() ;
		
		
		return fileName;
	}
}
