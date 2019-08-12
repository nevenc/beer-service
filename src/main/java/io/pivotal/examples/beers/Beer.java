package io.pivotal.examples.beers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String hop;
    private String yeast;
    private String malt;
    private String style;

}
