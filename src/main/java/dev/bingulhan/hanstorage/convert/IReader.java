package dev.bingulhan.hanstorage.convert;

import dev.bingulhan.hanstorage.HanStorage;

import java.util.Optional;

public interface IReader {

    Optional<HanStorage> read(String path);

}
