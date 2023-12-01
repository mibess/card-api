package com.mibess.card.domain.converter;

import org.springframework.stereotype.Component;

import com.mibess.card.domain.Card;
import com.mibess.card.domain.dto.CardDTO;
import com.mibess.card.domain.input.CardInput;

@Component
public class CardConverter {

    public Card toCardSave(CardInput cardInput) {
        Card card = new Card();

        card.setContent(cardInput.content());
        card.setAuthor(cardInput.author());
        card.setModel(cardInput.model());

        return card;

    }

    public Card toCardUpdate(Card card, CardInput cardInput) {

        card.setContent(cardInput.content());
        card.setAuthor(cardInput.author());
        card.setModel(cardInput.model());

        return card;

    }

    public CardDTO toCardDTO(Card card) {
        return new CardDTO(
                card.getCode(),
                card.getContent(),
                card.getAuthor(),
                card.getModel(),
                card.getCreatedAtFormatted());
    }

}
