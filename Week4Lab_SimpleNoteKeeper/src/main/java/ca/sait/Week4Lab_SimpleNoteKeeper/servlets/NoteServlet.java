
package ca.sait.Week4Lab_SimpleNoteKeeper.servlets;

import ca.sait.Week4Lab_SimpleNoteKeeper.javabeans.Note;
import java.io.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Madhu
 */
public class NoteServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            //get data
        String query = request.getQueryString();
        if(query != null && query.contains("edit")){
            //Display edit form
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
            
        } else{
            //Display viewn note
            String path = getServletContext().getRealPath("/WEB-INF/note.txt");
            
            //Read file
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
            String title = br.readLine();
            String contents = br.readLine();
        
            Note note = new Note(title, contents);
            request.setAttribute("note", note);
            
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
       
        }  
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //update data
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        // Create printwriter to post updated data
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false))); 
        
        pw.println(title);
        pw.println(contents);
         
        pw.close();    
        
        Note note = new Note(title, contents);
        request.setAttribute("note", note);
        
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
        
    }


}
