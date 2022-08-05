package com.minhlv.socialappapi.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.minhlv.socialappapi.entity.SystemUser;
import com.minhlv.socialappapi.entity.ThongKeTruyCapEntity;

public interface ThongKeTruyCapService {

    ThongKeTruyCapEntity save(ThongKeTruyCapEntity thongKeTruyCapEntity, SystemUser userEntity) throws SQLException;

    ThongKeTruyCapEntity findById(long id);

    List<Map<String, Object>> thongKeTheoThang();

    List<Map<String, Object>> thongKeTheoNam();

    List<Map<String, Object>> thongKeTinhHinhSuDung(Integer[] thang, Integer nam);
}
