package com.repill.was.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class MarketEmojiListData implements Serializable {
    private List<String> emojiList;

    public MarketEmojiListData(List<String> emojiList) {
        this.emojiList = emojiList;
    }

    static public MarketEmojiListData from(List<String> emojiList) {
        return Optional.ofNullable(emojiList)
                .map(MarketEmojiListData::new)
                .orElse(null);
    }
}
