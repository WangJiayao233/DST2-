package cn.edu.zju.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@WebServlet(name = "DosingGuidelineServlet",  urlPatterns = "/dosingGuideline")
public class DosingGuidelineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> drugLabelsContent = Files.readAllLines(Path.of(getServletContext().getRealPath("/WEB-INF/dosingGuideline.data")));
        Gson gson = new Gson();
        List<Object> drugLabels = drugLabelsContent.stream().map(x -> {
            Map map = gson.fromJson(x, Map.class);
            return map.get("data");
        }).collect(toList());
        request.setAttribute("dosingGuidelines", drugLabels);
        request.getRequestDispatcher("/views/dosing_guideline.jsp").forward(request, response);
    }
}
