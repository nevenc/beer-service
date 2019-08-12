package io.pivotal.examples.beers;

import static com.github.javafaker.Faker.instance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
public class BeerInitializer {

    private BeerRepository beerRepository;

    public BeerInitializer(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @GetMapping("/init")
    public String initializeDatabase(@RequestParam(name = "count", defaultValue = "10") int count) {

        IntStream.range(0,count).forEach(
                i -> {
                    Beer beer = new Beer(
                            null,
                            instance().beer().name(),
                            instance().beer().hop(),
                            instance().beer().yeast(),
                            instance().beer().malt(),
                            instance().beer().style()
                    );
                    beerRepository.save(beer);
                }
        );
        return "Created " + count + " record(s)";
    }


}
