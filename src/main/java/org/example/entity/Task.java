package org.example.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Task {

    private Long taskId;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;

}
