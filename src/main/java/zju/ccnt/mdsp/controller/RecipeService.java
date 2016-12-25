package zju.ccnt.mdsp.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity getRecipe(@RequestParam("idcard") String idcard) {
        try {
            String userIdUrl = Constant.hisUrl + "/users/id?idcard=" + idcard;
            User user = Utils.getByJSONObject(userIdUrl, null).toJavaObject(User.class);
            if (user.getId() == 0) {
                return Utils.genErrorResponse(400, "Invalid User ID Card");
            }

            String recipeUrl = Constant.hisUrl + "/recipes?userId=" + user.getId();
            Iterator it = Utils.getByJSONArray(recipeUrl, null).iterator();
            List<Recipe> recipes = new ArrayList<Recipe>();
            while (it.hasNext()) {
                JSONObject recipe = (JSONObject) it.next();
                recipes.add(recipe.toJavaObject(Recipe.class));
            }
            return ResponseEntity.ok(recipes);
        } catch (NullPointerException e) {
            return Utils.genErrorResponse(404, "Not Found");
        } catch (Exception e) {
            e.printStackTrace();
            return Utils.genErrorResponse(400, e.getMessage());
        }
    }
}