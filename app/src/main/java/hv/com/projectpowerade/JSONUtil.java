package hv.com.projectpowerade;

import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Created by brentmarks on 2017-01-08.
 */

public class JSONUtil {

    public String toJson(ArrayList<Genre> genres) {
        try {
            String stuff = "";
            JSONArray jsonArray = new JSONArray();
            if(genres != null) {
                for (Genre g : genres) {
                    JSONObject genre = new JSONObject();
                    genre.put("name", g.getName());
                    genre.put("id", g.getId());
                    if(g.getSubCategories() != null){
                        JSONArray subCategories = new JSONArray();
                        for(Genre subG : g.getSubCategories()){
                            JSONObject genreSub = new JSONObject();
                            genre.put("name", subG.getName());
                            genre.put("id", subG.getId());
                            subCategories.put(genreSub);
                        }
                        genre.put("subCategories", subCategories);
                    }
                    stuff += genre.toString();
                }
            }
            return stuff;

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;

    }
    @SuppressWarnings("unchecked")
    public void writeToFile(ArrayList<Genre> genres) throws IOException {
/*            JSONObject obj = new JSONObject();
            obj.put("Name", "crunchify.com");
            obj.put("Author", "App Shah");

            JSONArray company = new JSONArray();
            company.add("Compnay: eBay");
            company.add("Compnay: Paypal");
            company.add("Compnay: Google");
            obj.put("Company List", company);
*/
            // try-with-resources statement based on post comment below
            try (FileWriter file = new FileWriter(Environment.getExternalStorageDirectory() + "/Android/data/hv.com.projectpowerade/" + "file1.json")) {
                file.write(this.toJson(genres));
                System.out.println("Successfully Copied JSON Object to File...");
                System.out.println("\nJSON Object: ");
            }
        }
    }
