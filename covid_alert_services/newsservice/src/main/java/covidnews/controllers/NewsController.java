package covidnews.controllers;

import covidnews.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import covidnews.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping
    public List<News> getNews() {
        return service.getNews();
    }
}
