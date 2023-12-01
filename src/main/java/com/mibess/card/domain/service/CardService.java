package com.mibess.card.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mibess.card.domain.Card;
import com.mibess.card.domain.converter.CardConverter;
import com.mibess.card.domain.dto.CardDTO;
import com.mibess.card.domain.input.CardInput;
import com.mibess.card.infra.repository.CardRepository;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CardConverter cardConverter;

    public CardService(CardRepository cardRepository, CardConverter cardConverter) {
        this.cardRepository = cardRepository;
        this.cardConverter = cardConverter;
    }

    @Transactional
    public CardDTO createCard(CardInput cardInput) {

        Card card = cardConverter.toCardSave(cardInput);

        cardRepository.save(card);

        return cardConverter.toCardDTO(card);
    }

    @Transactional
    public void deleteCard(String cardCode) {
        Card card = this.findCardByCode(cardCode);

        cardRepository.deleteById(card.getId());
    }

    @Transactional
    public CardDTO updateCard(String cardCode, CardInput cardInput) {
        Card card = this.findCardByCode(cardCode);

        Card cardUpdate = cardConverter.toCardUpdate(card, cardInput);

        return cardConverter.toCardDTO(cardUpdate);
    }

    public Card findCardByCode(String cardCode) {
        return cardRepository.findByCode(cardCode).orElseThrow(
                () -> new NoSuchElementException(String.format("Card não encontrado para o código: %s", cardCode)));
    }

    public List<CardDTO> findAllCards() {

        List<Card> cards = cardRepository.findAll();

        return cards.stream().map(cardConverter::toCardDTO).collect(Collectors.toList());

    }
}
