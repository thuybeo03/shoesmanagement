package com.example.shoesmanagement.repository;

import com.example.shoesmanagement.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SizeRepository extends JpaRepository<Size, UUID> {
    List<Size> findAll();

    @Query(value = "select*from size\n" +
            "where trang_thai =?1", nativeQuery = true)
    List<Size> locTrangThai(int trangThai);
}
