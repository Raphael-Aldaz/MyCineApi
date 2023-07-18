package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Movie> movies = new ArrayList<>();

    @ManyToOne
    private City city;
    

}
