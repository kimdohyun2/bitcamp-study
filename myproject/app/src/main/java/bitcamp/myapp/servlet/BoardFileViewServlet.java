package bitcamp.myapp.servlet;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.StorageService;
import bitcamp.myapp.vo.AttachedFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLConnection;

@WebServlet("/board/file/view")
public class BoardFileViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int fileNo = Integer.parseInt(req.getParameter("fileNo"));
            BoardService boardService = (BoardService) getServletContext().getAttribute("boardService");
            AttachedFile attachedFile = boardService.getAttachedFile(fileNo);

            // 파일 경로와 파일 확장자 기반으로 MIME 타입 추측
            String filePath = "board/" + attachedFile.getFilename();
            String mimeType = URLConnection.guessContentTypeFromName(filePath);

            if (mimeType == null) {
                mimeType = "application/octet-stream";  // 기본 MIME 타입 설정 (모든 파일 유형에 사용)
            }

            // 응답에 맞는 Content-Type 설정
            resp.setContentType(mimeType);

            // 이미지 파일을 읽어 응답 스트림에 출력
            StorageService storageService = (StorageService) getServletContext().getAttribute("storageService");
            storageService.download(
                    "board/" + attachedFile.getFilename(), // 파일 경로
                    resp.getOutputStream() // 클라이언트로 이미지 데이터 전송
            );

        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            req.setAttribute("exception", stringWriter.toString());
            dispatcher.forward(req, resp);
        }
    }
}
