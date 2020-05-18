package com.lian.javareflect.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Ted
 * @date 2020/5/18 18:12
 */
public class JsonUtil {

    public static JSONArray jsonArraySort(JSONArray result, final String sortColName,
                                   final boolean isAsc) {
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < result.size(); i++) {
            jsonValues.add(result.getJSONObject(i));
        }

        //将 JSONArray 转换成 List,本质上还是对 List 进行排序
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                String valA = o1.getString(sortColName);
                String valB = o2.getString(sortColName);
                if (isAsc) {
                    // 升序
                    return -valB.compareTo(valA);
                } else {
                    return -valA.compareTo(valB);
                }
            }
        });
        for (int i = 0; i < result.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }
        return sortedJsonArray;
    }


    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0;i<3;i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tagId",i);
            jsonObject.put("orderId",(Math.random()*i+1));
            jsonArray.add(jsonObject);
        }
        JSONArray arraySort = jsonArraySort(jsonArray, "orderId", true);
        System.out.println(arraySort);
    }
}
