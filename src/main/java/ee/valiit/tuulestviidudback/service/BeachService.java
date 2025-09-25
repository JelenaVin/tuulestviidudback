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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeachService {

    public static final String FIELS_NAME_BEACH_ID = "beachId";
    public static final String FIELD_NAME_COUNTY_ID = "countyId";
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
    }

    public void updateBeach(Integer beachId, BeachDto beachDto) {
        Beach  beach = handleBeachUpdate(beachId, beachDto);
        handleBeachImageUpdate(beachDto, beach);
    }

    private void handleBeachImageUpdate(BeachDto beachDto, Beach beach) {
        Optional<BeachImage> optionalBeachImage = beachImageRepository.findBeachImageBy(beach.getId());
        if(imageUpdateIsRequired(beachDto, optionalBeachImage)) {
            updateAndSaveBeachImage(beachDto, optionalBeachImage);
        }else if (imageDeleteIsRequired(beachDto, optionalBeachImage)){
            beachImageRepository.delete(optionalBeachImage.get());

        }
    }

    private boolean imageDeleteIsRequired(BeachDto beachDto, Optional<BeachImage> optionalBeachImage) {
        return imageExists(beachDto.getImageData()) && !optionalBeachImage.isPresent();
    }

    private void updateAndSaveBeachImage(BeachDto beachDto, Optional<BeachImage> optionalBeachImage) {
        optionalBeachImage.get().setImageData(ByteConverter.stringToBytes(beachDto.getImageData()));
        beachImageRepository.save(optionalBeachImage.get());
    }

    private static boolean imageUpdateIsRequired(BeachDto beachDto, Optional<BeachImage> optionalBeachImage) {
        return imageExists(beachDto.getImageData()) && optionalBeachImage.isPresent();
    }

    private static boolean imageExists(String imageData) {
        return !imageData.isEmpty();
    }

    private Beach handleBeachUpdate(Integer beachId, BeachDto beachDto) {
        County county = getValidCounty(beachDto.getCountyId());

        Beach beach = getValidBeach(beachId);
        beachMapper.partialUpdate(beach, beachDto);
        beach.setCounty(county);
        beachRepository.save(beach);
        return null;
    }

    private Beach getValidBeach(Integer beachId) {
        return beachRepository.findById(beachId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException(FIELS_NAME_BEACH_ID, beachId));
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
                .orElseThrow(() -> new PrimaryKeyNotFoundException(FIELD_NAME_COUNTY_ID, countyId));
    }
}
