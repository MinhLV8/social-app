package com.minhlv.socialappapi.service;

import com.minhlv.socialappapi.entity.SystemUserEntity;
import com.minhlv.socialappapi.entity.ThongKeTruyCapEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public interface ThongKeTruyCapService {

    ThongKeTruyCapEntity save(ThongKeTruyCapEntity thongKeTruyCapEntity, SystemUserEntity userEntity) throws SQLException;

    ThongKeTruyCapEntity findById(long id);

    List<Map<String, Object>> thongKeTheoThang();

    List<Map<String, Object>> thongKeTheoNam();

    List<Map<String, Object>> thongKeTinhHinhSuDung(Integer[] thang, Integer nam);
}
