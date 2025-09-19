package ee.valiit.tuulestviidudback.controller.county;



import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor

public class CountyController {

    private final CountyService countyService;

    @GetMapping("/counties")
    @Operation(summary = "Kasutaja valib rippmenüüst soovitud maakonna. Süsteem tagastab countyId ja countyName",
               description = "Tagastab info koos countyId ja cityName'ga")
    public List<CountyDto> findCounties () { return countyService.findCounties();
    }
}

