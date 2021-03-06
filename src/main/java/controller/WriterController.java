package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import domain.BoardVO;
import service.WriterServiceImpl;
import util.JavaUtil;

/**
 * Servlet implementation class WriterController
 */
@WebServlet("/WriterCon")
public class WriterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("gallery/writer.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		//멀티 객체 만들기
		String saveFolder = "upload";
		
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		
		File targetDir = new File(realFolder);
		if(!targetDir.exists()) {
			targetDir.mkdir();// 디렉토리 한개
		  //targetDir.mkdirs();
		}
		int maxSize = 20*1024*1024;//20Mb
		String encType = "UTF-8";
		
		
		//넘어온 값을 변수(멀티객체)에 저장
		MultipartRequest multi = 
				new MultipartRequest(request, 
						realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String writer = multi.getParameter("writer");
		
		String realFileName = multi.getOriginalFileName("upfile");
		String realSaveFileName = multi.getFilesystemName("upfile");
		
		//썸네일 만들기
		JavaUtil.createThumbnail(realFolder+"/"+realSaveFileName, 256);
		
		//
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setRealFileName(realFileName);
		vo.setRealSaveFileName(realSaveFileName);
		System.out.println(vo);
		
		
		//
		WriterServiceImpl service = new WriterServiceImpl();
		service.insert(vo);
		
		
		//페이지 이동
		response.sendRedirect("ListCon");
	}

}
