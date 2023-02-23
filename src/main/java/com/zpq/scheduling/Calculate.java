package com.zpq.scheduling;

import com.zpq.mapper.StoreMapper;
import com.zpq.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

// 计算排班时间
public class Calculate {
    static SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    static StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);


    public static void main(String[] args) {
        System.out.println(getPrepNum(2));
        System.out.println(getClosureNum(1));
        System.out.println(getNumHalfHour(1,1));
    }


    // 计算店铺准备时间需要的人数
    public static int getPrepNum(int storeId) {
        double size = storeMapper.getSizeByStoreId(storeId);
        int preSize = storeMapper.getEachAreaByStoreId(storeId);
        return (int)Math.ceil(size/preSize);
    }

    // 获取闭店后需要的人数
    public static int getClosureNum(int storeId){
        int eachClean = storeMapper.getEachCleanByStoreId(storeId);
        double size = storeMapper.getSizeByStoreId(storeId);
        return (int)Math.ceil(size/eachClean);
    }

    // 以每半个小时的客流量计算各时间段需要的员工人数
    public static List getNumHalfHour(int storeId,int day){
        List nums = new ArrayList();
        List list = ExcelToList.getDayPredication(day-1,storeId); // 时间存在第二个位置
        nums.add(list.get(2));
        System.out.println(list);
        // 每个员工能服务的客流量
        float eachServe = storeMapper.getEachServeByStoreId(storeId);
        // 客流量为0时的值班人数
        int dutyNum = storeMapper.getDutyNumByStoreId(storeId);
        for (int i = 3; i < list.size(); i++) {
            float flow= Float.parseFloat((String)list.get(i));  // 客流量
            if(flow == 0){// 客流量为0时需要值班的人数
                nums.add(dutyNum);
                continue;
            }
            nums.add((int)Math.ceil(flow/eachServe));
        }
        return nums;
    }

}
