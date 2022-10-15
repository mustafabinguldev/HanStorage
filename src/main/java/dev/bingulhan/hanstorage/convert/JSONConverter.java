package dev.bingulhan.hanstorage.convert;

import dev.bingulhan.hanstorage.HanData;
import dev.bingulhan.hanstorage.HanStorage;
import org.json.*;

/**
 * This converter returns data as JSON Object.
 */
public class JSONConverter implements IConverter{
    @Override
    public Object convert(HanStorage storage) {
        String f = "{}";
        JSONObject jsonObject = new JSONObject(f);

        for (HanData data : storage.getDataList()) {
            jsonObject.append(data.getKey(), data.getValue());
        }

        return jsonObject;
    }

    @Override
    public String getPackage() {
        return "org.json.*";
    }

    @Override
    public String information() {
        return "This converter returns data as JSON Object.";
    }
}
