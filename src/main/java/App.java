import static java.lang.Integer.reverse;
import static spark.Spark.*;

import java.util.Map;
import java.util.HashMap;

import models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static java.lang.Integer.parseInt;

public class App {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        Integer port;
        if (processBuilder.environment().get("PORT") != null) {
            port = parseInt(processBuilder.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.Animal_type);
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = reverse(0);
            String name = req.queryParams("name");
            String type = req.queryParams("type");
            String age = req.queryParams("age");
            String health = req.queryParams("health");
            String location = req.queryParams("location");
            Animal newAnimal = new Animal(id,name, type, age, health, location);
            newAnimal.save();
            model.put("animals", Animal.Animal_type);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endanger/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "endan-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = reverse(0);
            String name = req.queryParams("name");
            String type = req.queryParams("type");
            String age = req.queryParams("age");
            String health = req.queryParams("health");
            String location = req.queryParams("location");
            Animal newAnimal = new Animal(id, name, type, age, health, location);
            newAnimal.save();
            model.put("animals", Animal.Animal_type);
            return new ModelAndView(model, "success2.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sighting", Sighting.all());
            return new ModelAndView(model, "sight.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
//            model.put("animals", Animal.all());
            model.put("location", Location.all());
            model.put("endangered", Endangered.all());
            model.put("rangers", Ranger.all());
            return new ModelAndView(model, "sight-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sight/new" , (req,res) ->{
            Map<String, Object> model = new HashMap<>();
            Integer id = reverse(0);
            String location = req.queryParams("location");
            String rangername = req.queryParams("rangername");
            String animal_type = req.queryParams("animal_type");
            Sighting newSighting =new Sighting (id,location,rangername,animal_type);
            newSighting.save();
//            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"success-sight.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("location", Location.all());
            return new ModelAndView(model, "location.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations/new", (req,res) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("location", Location.all());
            return new ModelAndView(model,"location-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/loc/new" , (req,res) ->{
            Map<String, Object> model = new HashMap<>();
//            Integer id = reverse(0);
            String sightings_location = req.queryParams("sightings_location");
//            String rangername = req.queryParams("rangername");
//            String animal_type = req.queryParams("animal_type");
            Location newLocation =new Location (sightings_location);
            newLocation.save();
//            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"loc-success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
