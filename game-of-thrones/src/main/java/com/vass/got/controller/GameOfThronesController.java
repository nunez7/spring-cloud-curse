package com.vass.got.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/game-of-thrones/characters")
public class GameOfThronesController {

private Faker faker = new Faker();
	
	private List<String> characters = new ArrayList<>();
	
	private static final Logger log = LoggerFactory.getLogger(GameOfThronesController.class);

	@PostConstruct
	public void init() {
		for (int i = 0; i <20; i++) {
			characters.add(faker.gameOfThrones().character());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<String>> get(){
		log.info("Getting characters GOT");
		return ResponseEntity.ok(characters);
	}
	
}
