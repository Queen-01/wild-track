import static spark.Spark.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import models.Animal;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static java.lang.Integer.parseInt;

public class App {
    public static void main(String[] args){
        ProcessBuilder processBuilder = new ProcessBuilder();
        Integer port;
        if(processBuilder.environment().get("PORT") != null){
            port = Integer.parseInt(processBuilder.environment().get("PORT"));
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
            model.put("animals", Animal.ANIMAL_TYPE);
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new-animal",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.queryParams("id"));
            String name = req.queryParams("name");
            Animal newAnimal = new Animal(id,name);
            newAnimal.save(new Animal);
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());
    }
}
