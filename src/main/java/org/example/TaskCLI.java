package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.format.DateTimeFormatter;

public class TaskCLI {
    public static final String FILE_PATH = "tasks.json";
     public static final ObjectMapper objectMapper = new ObjectMapper();
     public static long nextId = 1;
}
