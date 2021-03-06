package zju.ccnt.mdsp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zju.ccnt.mdsp.settings.Constant;
import zju.ccnt.mdsp.utils.Utils;

/**
 * Created by Dewayne on 2016/12/28.
 */

@RestController
public class UserService {
    @RequestMapping(value = "/users", method = RequestMethod.POST
            , produces="text/plain;charset=UTF-8")
    public ResponseEntity getUsers(@RequestParam String cipher
            , @RequestParam String id, @RequestParam String mac
            , @RequestParam(value = "privacy", defaultValue = "false") boolean privacy) {
        try {
            String userId = Utils.verifyUser(cipher, id, mac);
            if(userId == null) {
                return Utils.genErrorResponse(404, "Not Found");
            }

            String userUrl = Constant.HIS_URL + "/users/essentials/" + userId;
            JSONObject jsonObject = Utils.getByJSONObject(userUrl, null);
            String result = Utils.rmPrivacy(privacy, jsonObject);
            System.out.println("---> getUsers() : result = " + result);
            return ResponseEntity.ok(result);
        } catch (NullPointerException e) {
            return Utils.genErrorResponse(404, "Not Found");
        } catch (Exception e) {
            e.printStackTrace();
            return Utils.genErrorResponse(400, e.getMessage());
        }
    }
}