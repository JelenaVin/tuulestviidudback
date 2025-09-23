package ee.valiit.tuulestviidudback.service;

import ee.valiit.tuulestviidudback.controller.beach.BeachDto;
import ee.valiit.tuulestviidudback.infrastructure.exception.ForbiddenException;
import ee.valiit.tuulestviidudback.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import ee.valiit.tuulestviidudback.persistance.beach.BeachMapper;
import ee.valiit.tuulestviidudback.persistance.beach.BeachRepository;
import ee.valiit.tuulestviidudback.persistance.beachimage.BeachImage;
import ee.valiit.tuulestviidudback.persistance.beachimage.BeachImageRepository;
import ee.valiit.tuulestviidudback.persistance.county.County;
import ee.valiit.tuulestviidudback.persistance.county.CountyRepository;
import ee.valiit.tuulestviidudback.persistance.user.User;
import ee.valiit.tuulestviidudback.persistance.user.UserRepository;
import ee.valiit.tuulestviidudback.util.ByteConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeachService {

    private final BeachMapper beachMapper;
    private final CountyRepository countyRepository;
    private final BeachRepository beachRepository;
    private final UserRepository userRepository;
    private final BeachImageRepository beachImageRepository;


    public void addBeach(BeachDto beachDto) {
        Integer adminUserId = beachDto.getAdminUserId();
        User user = getValidAdminUser(adminUserId);
        County county = getValidCounty(beachDto.getCountyId());

        Beach beach = beachMapper.toBeach(beachDto);
        beach.setUser(user);
        beach.setCounty(county);
        beachRepository.save(beach);
        handleAddImageData(beachDto.getImageData(), beach);

        // todo: kas dtos pn pilt???
        // todo: kui jah siis on vaja lisada pilt tabelisse 'beach_image'
        // todo: sellist pilti saab lisada beachRepository abil
        // todo: Selleks et saaks lisada uut rida on meil vaja UUT BeachImage objekti
        // todo: Objekte saame teha mugavalt teha mapperi abil?
        // todo: Aga tuleks siis vaadata, et kas see on mõistilik
        // todo: Selleks tasub vaadata nii dto klassi kui entity klass
        // todo: Kui dto pealt saab vaid võtta ühe välja ja täita entity obkjektil üks väli,
        //  siis vist pole mõtet seda beachImage objekti luua mapperi abil
        // todo: Seega on mõistlike see objekt ise käsitsi luua new võtmesõna abil
    }

    private void handleAddImageData(String imageData, Beach beach) {
        if(!imageData.isEmpty()){
            BeachImage beachImage = new BeachImage();
            beachImage.setBeach(beach);
            beachImage.setImageData(ByteConverter.stringToBytes(imageData));
            beachImageRepository.save(beachImage);
        };
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
