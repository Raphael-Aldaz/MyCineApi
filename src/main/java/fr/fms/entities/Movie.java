package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String picture = "/images/default-image.png";

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Seance> seances = new ArrayList<>();


}
