package zju.ccnt.mdsp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zju.ccnt.mdsp.model.Assay;
import zju.ccnt.mdsp.model.Recipe;
import zju.ccnt.mdsp.model.User;
import zju.ccnt.mdsp.settings.Constant;
import zju.ccnt.mdsp.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dewayne on 2016/12/25.
 */

@RestController
public class RecipeService {
    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public ResponseEntity getRecipes(@RequestParam("idcard") String idcard
            , @RequestParam("privacy") boolean privacy) {
        try {
            String userIdUrl = Constant.hisUrl + "/users/id?idcard=" + idcard;
            User user = Utils.getByJSONObject(userIdUrl, null).toJavaObject(User.class);

            String recipeUrl = Constant.hisUrl + "/recipes?userId=" + user.getId();
            JSONArray jsonArray = Utils.getByJSONArray(recipeUrl, null);
            String result;
            if(privacy) {
                result = JSON.toJSONString(jsonArray, new PropertyFilter() {
                    public boolean apply(Object object, String name, Object value) {
                        // 找到时返回false
                        return !Utils.isMatched(name);
                    }
                });
            }
            else {
                result = JSON.toJSONString(jsonArray);
            }
            return ResponseEntity.ok(result);
        } catch (NullPointerException e) {
            return Utils.genErrorResponse(404, "Not Found");
        } catch (Exception e) {
            e.printStackTrace();
            return Utils.genErrorResponse(400, e.getMessage());
        }
    }

    @RequestMapping(value = "/assays", method = RequestMethod.GET)
    public ResponseEntity getAssays(@RequestParam("idcard") String idcard
            , @RequestParam("privacy") boolean privacy) {
        try {
            String userIdUrl = Constant.hisUrl + "/users/id?idcard=" + idcard;
            User user = Utils.getByJSONObject(userIdUrl, null).toJavaObject(User.class);

            String assayUrl = Constant.hisUrl + "/assays?userId=" + user.getId();
            JSONArray jsonArray = Utils.getByJSONArray(assayUrl, null);
            String result;
            if(privacy) {
                result = JSON.toJSONString(jsonArray, new PropertyFilter() {
                    public boolean apply(Object object, String name, Object value) {
                        // 找到时返回false
                        return !Utils.isMatched(name);
                    }
                });
            }
            else {
                result = JSON.toJSONString(jsonArray);
            }
            return ResponseEntity.ok(result);
        } catch (NullPointerException e) {
            return Utils.genErrorResponse(404, "Not Found");
        } catch (Exception e) {
            e.printStackTrace();
            return Utils.genErrorResponse(400, e.getMessage());
        }
    }
}