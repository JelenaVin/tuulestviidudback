package ee.valiit.tuulestviidudback.service;

import ee.valiit.tuulestviidudback.controller.beach.BeachDto;
import ee.valiit.tuulestviidudback.infrastructure.exception.ForbiddenException;
import ee.valiit.tuulestviidudback.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import ee.valiit.tuulestviidudback.persistance.beach.BeachMapper;
import ee.valiit.tuulestviidudback.persistance.beach.BeachRepository;
import ee.valiit.tuulestviidudback.persistance.county.County;
import ee.valiit.tuulestviidudback.persistance.county.CountyRepository;
import ee.valiit.tuulestviidudback.persistance.user.User;
import ee.valiit.tuulestviidudback.persistance.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeachService {

    private final BeachMapper beachMapper;
    private final CountyRepository countyRepository;
    private final BeachRepository beachRepository;
    private final UserRepository userRepository;


    public void addBeach(BeachDto beachDto) {
        Integer adminUserId = beachDto.getAdminUserId();
        User user = getValidAdminUser(adminUserId);
        County county = getValidCounty(beachDto.getCountyId());

        Beach beach = beachMapper.toBeach(beachDto);
        beach.setUser(user);
        beach.setCounty(county);
        beachRepository.save(beach);
    }

    private User getValidAdminUser(Integer adminUserId) {
        User user = userRepository.findAdminUserBy(adminUserId)
                .orElseThrow(() -> new ForbiddenException("Ei leia admin kasutajat id: " + adminUserId, 222));
        return user;
    }

    public County getValidCounty(Integer countyId) {
        return countyRepository.findById(countyId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("countyId", countyId));
    }
}
