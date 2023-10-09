package com.exemple.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotEmpty;


@Document(collection = "themeParkRides") // Nom de la collection MongoDB
@Getter
@ToString
@Data
@Builder
public class ThemeParkRide {
    @Id
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    private int thrillFactor;
    private int vomitFactor;
}