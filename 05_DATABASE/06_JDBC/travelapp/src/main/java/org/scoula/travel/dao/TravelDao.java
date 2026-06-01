package org.scoula.travel.dao;

import org.scoula.travel.domain.TravelImageVO;
import org.scoula.travel.domain.TravelVO;

import java.util.List;
import java.util.Optional;
/*
* DAO (Data Access Object)
* - DB 접근을 담당하는 객체
* - VO를 사용해서 DB의 CURD할 때 사용
* */
public interface TravelDao {
    void insert(TravelVO travel);
    void insertImage(TravelImageVO image);

    int getTotalCount();

    List<String> getDistricts();

    List<TravelVO> getTravels();

    List<TravelVO> getTravels(int page);

    List<TravelVO> getTravels(String district);

    Optional<TravelVO> getTravel(Long no);
}