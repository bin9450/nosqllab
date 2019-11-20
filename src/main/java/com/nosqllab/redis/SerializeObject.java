package com.nosqllab.redis;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Pan
 * @Date: 2019/11/17 21:40
 * @Description:
 **/
public interface SerializeObject <T> {
        /**
         * 序列化
         * @param t
         * @return
         */

        byte[]serialize(List<T> t) throws IOException;

        /**
         * 反序列化
         * @param bytes
         * @return
         */
        List<T> unSerialize(byte[] bytes);

}
