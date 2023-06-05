package dev.bingulhan.hanstorage.convert.json;

import dev.bingulhan.hanstorage.HanData;
import dev.bingulhan.hanstorage.HanStorage;
import dev.bingulhan.hanstorage.convert.IConverter;
import org.json.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This converter returns data as JSON Object.
 */
public class JSONConverter implements IConverter {
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

    @Override
    public void write(String path, String name,HanStorage storage) {

        File file = new File(path, name+".json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(""+convert(storage).toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
