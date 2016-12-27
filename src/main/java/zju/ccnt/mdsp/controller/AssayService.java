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
    @RequestMapping(value = "/assays", method = RequestMethod.GET
            , produces="text/plain;charset=UTF-8")
    public ResponseEntity getAssays(@RequestParam("idcard") String idcard
            , @RequestParam("privacy") boolean privacy) {
        try {
            String userIdUrl = Constant.hisUrl + "/users/id?idcard=" + idcard;
            User user = Utils.getByJSONObject(userIdUrl, null).toJavaObject(User.class);

            String assayUrl = Constant.hisUrl + "/assays?userId=" + user.getId();
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