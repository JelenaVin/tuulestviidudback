package ee.valiit.tuulestviidudback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TuulestviidudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuulestviidudApplication.class, args);
    }

}

