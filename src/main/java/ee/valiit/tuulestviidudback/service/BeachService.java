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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeachService {

    public static final String FIELD_NAME_BEACH_ID = "beachId";
    public static final String FIELD_NAME_COUNTY_ID = "countyId";
    private final BeachMapper beachMapper;
    private final CountyRepository countyRepository;
    private final BeachRepository beachRepository;
    private final UserRepository userRepository;
    private final BeachImageRepository beachImageRepository;

    @Transactional
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

    @Transactional
    public void updateBeach(Integer beachId, BeachDto beachDto) {
        Beach beach = handleBeachUpdate(beachId, beachDto);
        if (!beachDto.getImageData().isEmpty()) {
            handleBeachImageUpdate(beachDto, beach);
        }
    }

    private void handleBeachImageUpdate(BeachDto beachDto, Beach beach) {
        Optional<BeachImage> optionalBeachImage = beachImageRepository.findBeachImageBy(beach.getId());
        if (imageUpdateIsRequired(beachDto, optionalBeachImage)) {
            updateAndSaveBeachImage(beachDto, optionalBeachImage);
        } else if (imageDeleteIsRequired(beachDto, optionalBeachImage)) {
            beachImageRepository.delete(optionalBeachImage.get());
        } else if (addImageIsRequired(beachDto, optionalBeachImage)) {
            createAndSaveBeachImage(beachDto, beach);
        }
    }

    private void createAndSaveBeachImage(BeachDto beachDto, Beach beach) {
        BeachImage newBeachImage = createBeachImage(beachDto, beach);
        beachImageRepository.save(Objects.requireNonNull(newBeachImage));
    }

    private static BeachImage createBeachImage(BeachDto beachDto, Beach beach) {
        BeachImage newBeachImage = new BeachImage();
        newBeachImage.setBeach(beach);
        newBeachImage.setImageData(ByteConverter.stringToBytes(beachDto.getImageData()));
        return newBeachImage;
    }

    private boolean addImageIsRequired(BeachDto beachDto, Optional<BeachImage> optionalBeachImage) {
        return imageExists(beachDto.getImageData()) && !optionalBeachImage.isPresent();
    }

    private boolean imageDeleteIsRequired(BeachDto beachDto, Optional<BeachImage> optionalBeachImage) {
        return !imageExists(beachDto.getImageData()) && optionalBeachImage.isPresent();
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
        return beach;
    }

    private Beach getValidBeach(Integer beachId) {
        return beachRepository.findById(beachId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException(FIELD_NAME_BEACH_ID, beachId));
    }

    private void handleAddImageData(String imageData, Beach beach) {
        if (!imageData.isEmpty()) {
            BeachImage beachImage = new BeachImage();
            beachImage.setBeach(beach);
            beachImage.setImageData(ByteConverter.stringToBytes(imageData));
            beachImageRepository.save(beachImage);
        }
    }

    private User getValidAdminUser(Integer adminUserId) {
        return userRepository.findAdminUserBy(adminUserId)
                .orElseThrow(() -> new ForbiddenException("Ei leia admin kasutajat id: " + adminUserId, 222));
    }

    public County getValidCounty(Integer countyId) {
        return countyRepository.findById(countyId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException(FIELD_NAME_COUNTY_ID, countyId));
    }
}
