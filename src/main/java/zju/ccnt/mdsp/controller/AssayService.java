package zju.ccnt.mdsp.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zju.ccnt.mdsp.model.User;
import zju.ccnt.mdsp.settings.Constant;
import zju.ccnt.mdsp.utils.Utils;

/**
 * Created by Cc on 2016/12/26.
 */

@RestController
public class AssayService {
    @RequestMapping(value = "/assays", method = RequestMethod.POST
            , produces="text/plain;charset=UTF-8")
    public ResponseEntity getAssays(@RequestParam String cipher
            , @RequestParam String id, @RequestParam String mac
            , @RequestParam("privacy") boolean privacy) {
        try {
            String userId = Utils.verifyUser(cipher, id, mac);
            if(userId == null) {
                return Utils.genErrorResponse(404, "Not Found");
            }

            String assayUrl = Constant.HIS_URL + "/assays?userId=" + userId;
            JSONArray jsonArray = Utils.getJSONArray(assayUrl, null);
            String result = Utils.rmPrivacy(privacy, jsonArray);
            return ResponseEntity.ok(result);
        } catch (NullPointerException e) {
            return Utils.genErrorResponse(404, "Not Found");
        } catch (Exception e) {
            e.printStackTrace();
            return Utils.genErrorResponse(400, e.getMessage());
        }
    }
}