import static spark.Spark.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import models.Animal;
import models.Endangered;
import models.Location;
import models.Ranger;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static java.lang.Integer.parseInt;

public class App {
    public static void main(String[] args){
        ProcessBuilder processBuilder = new ProcessBuilder();
        Integer port;
        if(processBuilder.environment().get("PORT") != null){
            port = parseInt(processBuilder.environment().get("PORT"));
        }else{
            port = 4567;
        }
        port(port);

        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.Animal_type);
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new/animals",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            int id = parseInt(req.queryParams("id"));
            String name = req.queryParams("name");
            String type = req.queryParams("type");
            String age = req.queryParams("age");
            String health = req.queryParams("health");
            String location = req.queryParams("location");
            Animal newAnimal = new Animal(name, type, age, health, location);
            newAnimal.save();
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

        get("/endanger/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"endan-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endanger",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.queryParams("id"));
            String name = req.queryParams("name");
            String type = req.queryParams("type");
            String age = req.queryParams("age");
            String health = req.queryParams("health");
            String location = req.queryParams("location");
            Animal newAnimal = new Animal(name,type,age,health ,location);
            newAnimal.save();
            return new ModelAndView(model,"success2.hbs");
        },new HandlebarsTemplateEngine());

        get("/sightings", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.Animal_type);
            return new ModelAndView(model, "sight.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.all());
            model.put("location", Location.all());
            model.put("endangered", Endangered.all());
            model.put("rangers", Ranger.all());
            return new ModelAndView(model, "sight-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/location", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.Animal_type);
            return new ModelAndView(model, "location.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
