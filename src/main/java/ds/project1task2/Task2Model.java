package ds.project1task2;

/**
 * @author
 * Name: Siqi Deng
 * AndrewID: siqideng
 *
 *
 * Model of MVC, and it models the business logic for the web application.
 * The business logic involves providing the detailed website to the breed
 * searched and then screen scraping the HTML that is returned in order
 * to gather dog breed information.
 * Basic information scraped from dog.time using Jsoup.
 * Image example scraped from dog.ceo/api/breed/ with json.
 */

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Task2Model {
        // Variable that stores dogtime.com
        private static final String DOG_URL = "https://dogtime.com/dog-breeds/profiles";

        // Dog class to store dog information
        // of all kinds as an object
        class Dog {
            // member data
            private String breed;
            private String friendly;
            private String intelligence;
            private String height;
            private String weight;
            private String lifeSpan;

            //setters and getters
            public String getBreed() {
                return breed;
            }

            public String getFriendly() {
                return friendly;
            }

            public String getIntelligence() {
                return intelligence;
            }

            public String getHeight() {
                return height;
            }

            public String getWeight() {
                return weight;
            }

            public String getLifeSpan() {
                return lifeSpan;
            }

            public void setBreed(String breed) {
                this.breed = breed;
            }

            public void setFriendly(String friendly) {
                this.friendly = friendly;
            }

            public void setIntelligence(String intelligence) {
                this.intelligence = intelligence;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public void setLifeSpan(String lifeSpan) {
                this.lifeSpan = lifeSpan;
            }
        }

        /**
         * @param breed The type of the dog to be searched for.
         * @return Corresponding detail page url.
         */
        public String redirect(String breed) {
            // using jsoup to scrape
            Document doc;
            try {
                doc = Jsoup.connect(DOG_URL).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String breedURL = doc.getElementsByAttributeValue("alt", breed).get(0).parents().attr("href");
            return breedURL;
        }

        /**
         * @param breed The type of the dog to be searched for.
         * @param breedURL Corresponding detail page.
         * @return dog object containing breed details
         */
        public Dog extractDog(String breed, String breedURL) {
            Dog dog = new Dog();
            // scrape using jsoup
            Document doc;
            try {
                doc = Jsoup.connect(breedURL).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // jsoup select() with precise cssQuery
            String friendlyOriginal = doc.select("body > div.wrapper > div.inner-wrapper > div > article > div > " +
                    "div.breeds-single-details > div.js-listing-box.breed-characteristics-ratings > div:nth-child(3) > " +
                    "div.characteristic-stars.parent-characteristic > div > div").get(0).className();
            String friendlyStar = friendlyOriginal.substring(friendlyOriginal.length()-1);
            String friendly = "Friendly : " + friendlyStar + " Stars";
            String intelligenceStar = doc.select("body > div.wrapper > div.inner-wrapper > div > article > div > " +
                    "div.breeds-single-details > div.js-listing-box.breed-characteristics-ratings > div:nth-child(6) > " +
                    "div:nth-child(3) > a > div.characteristic-star-block > div").get(0).text();
            String intelligence = "Intelligence : " + intelligenceStar + " Stars";
            String height = doc.select("body > div.wrapper > div.inner-wrapper > div > article > div > div.breeds-single-details > " +
                    "div.breed-vital-stats > div > div:nth-child(2)").text();
            String weight = doc.select("body > div.wrapper > div.inner-wrapper > div > article > div > div.breeds-single-details > " +
                    "div.breed-vital-stats > div > div:nth-child(3)").text();
            String life = doc.select("body > div.wrapper > div.inner-wrapper > div > article > div > div.breeds-single-details > " +
                    "div.breed-vital-stats > div > div:nth-child(4)").text();
            // setting data fields of dog
            dog.setBreed("Dog: "+breed);
            dog.setFriendly(friendly);
            dog.setIntelligence(intelligence);
            dog.setHeight(height);
            dog.setWeight(weight);
            dog.setLifeSpan(life);
            return dog;
        }

        /**
         * Arguments.
         *
         * @param breed The type of the dog to be searched for.
         * picture requested.
         */
        public String doDogPicSearch(String breed) throws IOException {
            String pictureURL = "";
            breed = URLEncoder.encode(breed, "UTF-8");

            // Create a URL for the page to be screen scraped
            String dogURL = "https://dog.ceo/api/breed/" + breed.toLowerCase() + "/images";
            String response;
            response = fetch(dogURL);
            // Use Gson to convert the json string into map
            Gson gson = new Gson();
            Map<String, List<String>> map = gson.fromJson(response, Map.class);

            // Return the picture url at random
            Random random = new Random();
            int seed = random.nextInt(map.get("message").size());
            pictureURL = map.get("message").get(seed);

            return pictureURL;
        }

        /**
         * Make an HTTP request to a given URL
         *
         * @param urlString The URL of the request
         * @return A string of the response from the HTTP GET.
         */
        private String fetch(String urlString) {
            String response = "";
            try {
                URL url = new URL(urlString);
                /*
                 * Create an HttpURLConnection.  This is useful for setting headers
                 * and for getting the path of the resource that is returned (which
                 * may be different than the URL above if redirected).
                 * HttpsURLConnection (with an "s") can be used if required by the site.
                 */
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // Read all the text returned by the server
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String str;
                // Read each line of "in" until done, adding each to "response"
                while ((str = in.readLine()) != null) {
                    // str is one line of text readLine() strips newline characters
                    response += str;
                }
                in.close();
            } catch (IOException e) {
                System.out.println("Failed to connect");
            }
            return response;
        }

    }

