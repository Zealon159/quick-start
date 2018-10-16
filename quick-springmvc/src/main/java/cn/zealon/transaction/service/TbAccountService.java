package cn.zealon.transaction.service;

import cn.zealon.transaction.dao.TbAccountMapper;
import cn.zealon.transaction.dao.TbCarMapper;
import cn.zealon.transaction.entity.TbAccount;
import cn.zealon.transaction.entity.TbCar;
import cn.zealon.transaction.exception.NotSufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther: Zealon
 * @Date: 2018-07-24 10:57
 */
@Service
@Scope("")
public class TbAccountService {

    @Autowired
    private TbAccountMapper accountMapper;

    @Autowired
    private TbCarMapper carMapper;

    @Transactional
    public void buyCar(String uid,String carName,int number) {
        TbCar car = carMapper.findById(carName);
        TbAccount account = accountMapper.findById(uid);

        int carPrice = getCarPrice(car,carName,number);
        int AccountBalance = getUserAccount(account);

        car.setCarNumber(car.getCarNumber()-number); //数量减少
        carMapper.update(car);

        if(AccountBalance<carPrice){
            throw new NotSufficientFundsException("余额不足！");
        }

        account.setAccount(account.getAccount()-carPrice);//余额减少
        accountMapper.update(account);
    }

    /**
     * 获取要购买的汽车价格
     * @param carName
     * @param number
     * @return
     */
    public int getCarPrice(TbCar car,String carName,int number){
        return car.getCarPrice()*number;
    }

    /**
     * 获取个人总金额
     * @param account
     * @return
     */
    public int getUserAccount(TbAccount account){
        return account.getAccount();
    }
}
