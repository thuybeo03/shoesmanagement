package com.example.shoesmanagement.repository;

import com.example.shoesmanagement.model.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, UUID> {
    List<MauSac> findAll();

    @Query(value = "select*from mau_sac\n" +
            "where trang_thai =?1 ", nativeQuery = true)
    List<MauSac> locTrangThai(int trangThai);
}
