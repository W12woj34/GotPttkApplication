package com.example.got_pttk_po.utils;



import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtil {

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * @param <D>      Type of result object
     * @param <T>      Type of source object
     * @param entity   Entity to map
     * @param outClass Class of result object
     * @return New object of T type.
     */

    public static <D, T> D map(final T entity, Class<D> outClass) {

        return modelMapper.map(entity, outClass);
    }
}
