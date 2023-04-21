package dev.bingulhan.hanstorage.convert.json;

import dev.bingulhan.hanstorage.HanData;
import dev.bingulhan.hanstorage.HanStorage;
import dev.bingulhan.hanstorage.convert.IReader;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

public class JSONReader implements IReader {
    @Override
    public Optional<HanStorage> read(String path) {
        Optional<HanStorage> result = Optional.empty();

        File file = new File(path);

        if (file.exists()) {
            try {
                String json = new String(Files.readAllBytes(Paths.get(file.getPath())));
                JSONObject jsonObject = new JSONObject(json);
                ArrayList<HanData> dataList = new ArrayList<>();

                for (String key : jsonObject.keySet()) {
                    dataList.add(new HanData().setKey(key).setValue(String.valueOf(jsonObject.get(key))));
                }

                String name = file.getName().split("json")[0];
                name = name.substring(0, name.length()-1);

                System.out.println(name);
                HanStorage storage = new HanStorage(file.getParent(), name);

                dataList.stream().forEach(data -> {
                    storage.addData(data);
                });

                result = Optional.of(storage);
                System.out.println("The JSON object has been read and the resulting data is: "+storage.getSize());


            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

        return result;
    }
}
