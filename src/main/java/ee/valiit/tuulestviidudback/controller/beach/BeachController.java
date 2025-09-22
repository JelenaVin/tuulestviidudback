package ee.valiit.tuulestviidudback.controller.beach;


import ee.valiit.tuulestviidudback.service.BeachService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BeachController {

    private final BeachService beachService;

    @PostMapping("/admin/beach")
    @Operation(summary = "Uue ranna lisamine", description = "imageData ja description väljad pole kohustuslikud")
    public void addBeach(@RequestBody @Valid BeachDto beachDto) {
        beachService.addBeach(beachDto);
    }

}
