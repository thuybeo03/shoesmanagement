package com.example.shoesmanagement.repository;

import com.example.shoesmanagement.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GiayChiTietRepository extends JpaRepository<ChiTietGiay, UUID> {
    List<ChiTietGiay> findByGiay(Giay giay);

    List<ChiTietGiay> findByTrangThaiAndGiay(int trangThai, Giay giay);
    List<ChiTietGiay> findByGiayAndMauSac(Giay giay, MauSac mauSac);
    @Query(value = "SELECT DISTINCT ctg.hinhAnh FROM ChiTietGiay ctg WHERE ctg.giay = ?1 AND ctg.mauSac = ?2")
    HinhAnh findDistinctByGiay(Giay giay, MauSac mauSac);
    List<ChiTietGiay> findByMauSacAndGiayAndTrangThai(MauSac mauSac, Giay giay, int trangThai);



}
