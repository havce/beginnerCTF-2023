package it.unipr.soi23.game_web_server.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unipr.soi23.game_web_server.service.Soi23GameWebServerRepoViewService;

@RestController
@RequestMapping("/admin/repo")
public class Soi23GameWebServerRepoViewServiceRest {

    private final Soi23GameWebServerRepoViewService service;

    public Soi23GameWebServerRepoViewServiceRest(Soi23GameWebServerRepoViewService service) {
        this.service = service;
    }

    @GetMapping("/map")
    public Map<String, Object> getRepoMap() {
        return service.getRepoMap();
    }
}
