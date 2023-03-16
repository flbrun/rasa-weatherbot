package de.fhdw.webtec.weatherchat.intents;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@RequiredArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum RasaResponseKeys {
    MESSAGE("message"),
    TEXT("text");
    String expectation;

}
