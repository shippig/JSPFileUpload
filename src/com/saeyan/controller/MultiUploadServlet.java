package com.saeyan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class Test
 */
@WebServlet("/upload2.do")
public class MultiUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
		
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String savePath = "Upload";
		int maxPostSize = 5 * 1024 * 1024;
		
		ServletContext context = this.getServletContext();
		String saveDirectory = context.getRealPath(savePath);
		
		System.out.println(saveDirectory);
		
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, 
				maxPostSize, "UTF-8", new DefaultFileRenamePolicy());
		
		Enumeration<String> fileNames = multi.getFileNames();
		
		while(fileNames.hasMoreElements())
		{
			// 파일 이름을 가져온다.
			String file = fileNames.nextElement();
			String fileName = multi.getFilesystemName(file);
			
			// 중복된 파일일 경우 이름이 변경되는데, 원본 파일명을 가져오고 싶을때 사용한다.
			String ori_file_name = multi.getOriginalFileName(file);
			
			out.println("==================================<br>");
			out.println("업로드된 파일명: " + fileName + "<br>");
			out.println("원본 파일명: "+ ori_file_name + "<br>");
			out.println("==================================<br>");
		}
		
	}

}
