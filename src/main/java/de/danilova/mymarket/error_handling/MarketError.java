package de.danilova.mymarket.error_handling;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor

public class MarketError {
    private int status;
    private List<String> messages;
    private Date timestamp;

    public MarketError(int status, String... messages) {
        this.status = status;
        this.messages = new ArrayList<>(Arrays.asList(messages));
       this.timestamp = new Date();
    }

    public MarketError(int status, Collection<String> messages) {
        this.status = status;
        this.messages = new ArrayList<>(messages);
        this.timestamp = new Date();
    }
}

