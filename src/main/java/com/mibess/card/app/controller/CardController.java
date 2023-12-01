package com.mibess.card.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mibess.card.domain.dto.CardDTO;
import com.mibess.card.domain.input.CardInput;
import com.mibess.card.domain.service.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;

    }

    @GetMapping
    public List<CardDTO> listAllCards() {
        return cardService.findAllCards();
    }

    @PostMapping
    public ResponseEntity<CardDTO> createCard(@RequestBody CardInput cardInput) {

        CardDTO cardDTO = cardService.createCard(cardInput);

        return ResponseEntity.status(HttpStatus.CREATED).body(cardDTO);
    }

    @PutMapping("{cardCode}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable String cardCode, @RequestBody CardInput cardInput) {

        CardDTO cardDTO = cardService.updateCard(cardCode, cardInput);

        return ResponseEntity.status(HttpStatus.OK).body(cardDTO);
    }

    @DeleteMapping("{cardCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable String cardCode) {

        cardService.deleteCard(cardCode);

    }
}
