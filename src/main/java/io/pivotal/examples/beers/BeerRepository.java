package io.pivotal.examples.beers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer,Long> {
}
