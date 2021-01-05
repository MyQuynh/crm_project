package com.company;

import java.io.IOException;
import java.text.ParseException;

public interface CSVWriter {
    default void addEntry() throws ParseException {}
    default void updateEntry() throws IOException, ParseException {}
}
