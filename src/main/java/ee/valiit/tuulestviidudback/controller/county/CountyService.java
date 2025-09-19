package ee.valiit.tuulestviidudback.controller.county;

import ee.valiit.tuulestviidudback.persistance.county.County;

import ee.valiit.tuulestviidudback.persistance.county.CountyMapper;
import ee.valiit.tuulestviidudback.persistance.county.CountyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountyService {

    private final CountyRepository countyRepository;
    private final CountyMapper countyMapper;

public List<CountyDto> findCounties(){
    List<County> counties = countyRepository.findAll();
    return countyMapper.toCountyDtos(counties);
}
}
