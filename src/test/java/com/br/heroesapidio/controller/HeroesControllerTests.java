package com.br.heroesapidio.controller;

import com.br.heroesapidio.document.Heroes;
import com.br.heroesapidio.service.HeroesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.br.heroesapidio.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DirtiesContext
@SpringBootTest
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
public class HeroesControllerTests {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private HeroesService heroesService;

    @Test
    public void getOneHeroById() {
        Heroes heroes = new Heroes("10", "Ten", "DC", 10);

        when(heroesService.findByIdHero(heroes.getId())).thenReturn(Mono.just(heroes));

        webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), heroes.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void getOneHeroNotFound() {
        String heroId = "10";

        when(heroesService.findByIdHero(heroId)).thenReturn(Mono.empty());

        webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), heroId)
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    public void deleteHero() {
        String heroId = "10";

        doNothing().when(heroesService).deleteByIDHero(heroId);

        webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), heroId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }

}
