package ee.valiit.tuulestviidudback.controller.beach;


import ee.valiit.tuulestviidudback.service.BeachService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BeachController {

    private final BeachService beachService;

    @PostMapping("/admin/beach")
    @Operation(summary = "Uue ranna lisamine", description = "imageData ja description väljad pole kohustuslikud")
    public void addBeach(@RequestBody @Valid BeachDto beachDto) {
        beachService.addBeach(beachDto);
    }

    @PutMapping("/admin/beach")
    @Operation(summary = "Muudab olemasoleva ranna andmeid (kirjutab üle).",
            description = "imageData ja description pole kohustuslikud väljad")
    public void updateBeach(@RequestParam Integer beachId, @RequestBody @Valid BeachDto beachDto) {
        beachService.updateBeach(beachId,beachDto);
    }



}
