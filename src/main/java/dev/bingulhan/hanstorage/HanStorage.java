package dev.bingulhan.hanstorage;

import dev.bingulhan.hanstorage.HanData;
import dev.bingulhan.hanstorage.SoftwareInfo;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author BingulHan
 *
 * The object where the data is kept
 *
 */
public class HanStorage {

    private final File file;

    private HashSet<HanData> datas = new HashSet<>();

    private void readLines() {
        Scanner dosya = null;
        try {
            dosya = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int line = 1;
        while(dosya.hasNextLine()) {
            String source = dosya.nextLine();
            if (source.charAt(0) != '#') {
                String key = source.split("=")[0];
                String value = "";
                for (int i = 1; i < source.split("=").length; i++) {
                    value = value + source.split("=")[i];
                }

                HanData data = new HanData(line);
                data.setType(HanData.DataType.DATA);
                data.setKey(key);
                data.setValue(value);

                datas.add(data);

            }else {
                String value = source.substring(1);

                HanData data = new HanData(line);
                data.setKey("c"+line);
                data.setValue(value);
                data.setType(HanData.DataType.COMMENT);

                datas.add(data);
            }
            line++;

        }


    }

    public final HanStorage addData(HanData data) {

        for (HanData d : datas) {
            if (d.getKey().equals(data.getKey())) {
                System.err.println("This data already exists");
                return this;
            }
        }

        datas.add(data);
        save();

        return this;
    }

    @Override
    protected void finalize() throws Throwable {

        super.finalize();
        save();
        System.out.println("Auto saved");

    }

    public final boolean isSet(String key) {
        return datas.stream().anyMatch(dt -> dt.getKey().equals(key));
    }

    public HanStorage(String path, String fileName) {
        file = new File(path, fileName+"."+ SoftwareInfo.EXTENSION);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        readLines();
        System.err.println("Saved data size of "+fileName+".hst is "+datas.size());

    }




    public final Optional<HanData> getData(String key) {
        return datas.stream().filter(d -> d.getKey().equals(key)).findAny();
    }

    public final void list() {
        List<HanData> data = new ArrayList<>(datas);
        Collections.sort(data);

        data.stream().forEach(d -> {
            System.out.println(d.getLine()+" "+d.getKey()+"="+d.getValue());
        });
    }

    public final ArrayList<HanData> getDataList() {
        return new ArrayList<HanData>(this.datas);
    }

    public final ArrayList<HanData> getDataList(HanData.DataType type) {
        return (ArrayList<HanData>) new ArrayList<HanData>(this.datas).stream().filter(data -> data.getType().equals(type)).collect(Collectors.toList());
    }

    public final Optional<String> getComment(int line) {
        if (isSet("c"+line)) {
            return Optional.of(getData("c"+line).get().getValue());
        }

        return Optional.empty();
    }

    public final boolean updateValue(String key, String value) {
        if (datas.stream().anyMatch(d -> d.getKey().equals(key))) {
            datas.stream().filter(d -> d.getKey().equals(key)).findAny().get().setValue(value);
            save();
            return true;
        }else {
            return false;
        }
    }

    public final int getSize() {
        return datas.size();
    }

    public final int getSize(HanData.DataType type) {
        return getDataList().stream().filter(data -> data.getType().equals(type)).collect(Collectors.toList()).size();
    }

    public final void save() {
        file.delete();

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<HanData> data = new ArrayList<>(datas);
        Collections.sort(data);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            data.stream().forEach(d -> {

                if (d.getType().equals(HanData.DataType.DATA)) {
                    String line = d.getKey()+"="+d.getValue();
                    try {
                        writer.write(line);
                        writer.newLine();
                        writer.flush();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    String line = "#"+d.getValue();
                    try {
                        writer.write(line);
                        writer.newLine();
                        writer.flush();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            writer.close();

        } catch (IOException ex) {
            System.out.println("File could not be created");
        }

    }

    public final void remove(String key) {
        if (datas.stream().anyMatch(d -> d.getKey().equals(key))) {
            datas.remove(datas.stream().filter(d -> d.getKey().equals(key)).findAny().get());
        }else {
            System.err.println("Data not found");
        }
    }

    public final void removeSave(String key) {
        if (datas.stream().anyMatch(d -> d.getKey().equals(key))) {
            datas.remove(datas.stream().filter(d -> d.getKey().equals(key)).findAny().get());
        } else {
            System.err.println("Data not found");
        }

        save();
    }
    }
