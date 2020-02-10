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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@WebServlet(name = "DrugLabelServlet",  urlPatterns = "/drugLabels")
public class DrugLabelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> drugLabelsContent = Files.readAllLines(Path.of(getServletContext().getRealPath("/WEB-INF/drugLabels.data")));
        Gson gson = new Gson();
        List<Map> drugLabels = drugLabelsContent.stream().map(x -> gson.fromJson(x, Map.class)).collect(toList());
        request.setAttribute("drugLabels", drugLabels);
        request.getRequestDispatcher("/views/drug_labels.jsp").forward(request, response);
    }
}
