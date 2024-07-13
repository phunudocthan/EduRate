/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

/**
 *
 * @author Bang
 */

import DAOs.DistrictDAO;
import DAOs.ProvinceDAO;
import DAOs.WardDAO;
import Models.District;
import Models.Province;
import Models.Ward;
import java.sql.SQLException;
import java.util.List;

public class LocationService {
    private final ProvinceDAO provinceDAO = new ProvinceDAO();
    private final DistrictDAO districtDAO = new DistrictDAO();
    private final WardDAO wardDAO = new WardDAO();

    public List<Province> getAllProvinces() throws SQLException {
        return provinceDAO.getAllProvinces();
    }

    public List<Ward> getWardsByDistrictId(int districtId) throws SQLException {
        return wardDAO.getWardsByDistrictId(districtId);
    }

    public List<District> getDistrictsByProvinceId(int provinceId)  throws SQLException {
        return districtDAO.getDistrictsByProvinceId(provinceId);
    }
}

