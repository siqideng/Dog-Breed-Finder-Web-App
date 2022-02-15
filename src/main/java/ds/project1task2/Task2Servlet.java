package ds.project1task2;

/**
 * @author
 * Name: Siqi Deng
 * AndrewID: siqideng
 *
 * Controller of MVC.
 * It decides between the two by determining if there is a search parameter or
 * not. If there is no parameter, then it uses the prompt.jsp view, as a
 * starting place. If there is a search parameter, then it searches for a
 * breed's information and uses the result.jsp view.
 * The model is provided by Task2Model.
 */


import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "DogBreedInformation",
        urlPatterns = {"/getDogBreedInformation"})
public class Task2Servlet extends HttpServlet {
    // The "business model" for this app
    Task2Model ipm = null;

    // Initiate this servlet by instantiating the model that it will use.
    @Override
    public void init() {
        ipm = new Task2Model();
    }

    // This servlet will use jsoup to scrape the website
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // get the dog breed if it exists
        String breed = request.getParameter("dog-breeds");

        String nextView;

        /*
         * Check if the search parameter is present.
         * If not, then give the user instructions and prompt for a search string.
         * If there is a search parameter, then do the search and return the result.
         */
        if (breed != null) {
            // Pass the user search string (pictureTag) also to the view.
            String breedURL = ipm.redirect(breed);
            // Instantiate a dog object.
            Task2Model.Dog dog = ipm.extractDog(breed, breedURL);
            // The view if search is valid
            nextView = "result.jsp";
            // Sets the breed information
            request.setAttribute("breed",dog.getBreed());
            // Sets the friendliness information
            request.setAttribute("friendly",dog.getFriendly());
            // Sets the intelligence information
            request.setAttribute("intelligence",dog.getIntelligence());
            // Sets the height information
            request.setAttribute("height",dog.getHeight());
            // Sets the weight information
            request.setAttribute("weight",dog.getWeight());
            // Sets the life span information
            request.setAttribute("life",dog.getLifeSpan());
            // Sets the credit information of texts
            request.setAttribute("creditBreed",breedURL);
            // Sets the image infomration
            request.setAttribute("picture", ipm.doDogPicSearch(breed));
            // Sets the credit information of image
            request.setAttribute("credit","https://dog.ceo/dog-api/");

        } else {
            // no search parameter so choose the prompt view
            nextView = "prompt.jsp";
        }
        // Transfer control over the the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
}

