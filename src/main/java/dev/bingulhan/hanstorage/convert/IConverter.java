package dev.bingulhan.hanstorage.convert;

import dev.bingulhan.hanstorage.HanData;
import dev.bingulhan.hanstorage.HanStorage;

public interface IConverter {

    public Object convert(HanStorage storage);
    public String getPackage();
    public String information();





}
