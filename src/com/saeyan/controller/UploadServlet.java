package com.saeyan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		
		PrintWriter out = response.getWriter();
		
		String savePath = "Upload"; // 저장 경로
		int uploadFileSizeLimit = 5 * 1024 * 1024; // 최대 파일 업로드 사이즈를 5MB로 제한
		String encType = "UTF-8";
		
		ServletContext context = this.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		System.out.println("서버상의 실제 디렉토리 :");
		System.out.println(uploadFilePath);
		
		// request 객체, 서버상의 실제 디렉토리, 최대 업로드 파일 크기, 
		// 인코딩 방법, 동일한 이름이 존재하면 새로운 이름이 부여됨.
		MultipartRequest multi = new MultipartRequest(
				request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		// 업로드된 파일의 이름 얻기
		String fileName = multi.getFilesystemName("uploadFile");
		
		if(fileName == null)
		{
			System.out.print("파일이 업로드 되지 않았음.");
		}
		else
		{
			out.println("<br> 글쓴이 : " + multi.getParameter("name"));
			out.println("<br> 제 &nbsp; 목 : " + multi.getParameter("title"));
			out.println("<br> 파일명 : " + fileName);
		}
	}

}
