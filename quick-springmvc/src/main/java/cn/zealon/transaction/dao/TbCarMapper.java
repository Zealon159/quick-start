package cn.zealon.transaction.dao;

import cn.zealon.transaction.entity.TbCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @auther: Zealon
 * @Date: 2018-07-24 10:55
 */
@Mapper
public interface TbCarMapper {

    @Select("select car_Name carName,car_Price carPrice,car_Number carNumber from tb_car where car_Name = #{carName}")
    TbCar findById(String carName);

    @Update("update tb_car set car_Number= #{carNumber} where car_Name = #{carName}")
    int update(TbCar car);
}
