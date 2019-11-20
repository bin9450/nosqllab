package com.nosqllab.redis;

import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Pan
 * @Date: 2019/11/19 22:11
 * @Description:
 **/

public class SerializeObjectImpl<T> implements SerializeObject<T> {
    private Class<T> type;

    public SerializeObjectImpl(Class<T> type) {
        this.type = type;
    }

    public T[] create(Class<T> type,int size) {
        return (T[]) Array.newInstance(type, size);
    }

    @Override
    public byte[]serialize(List<T> t) throws IOException {

        byte[] bytes =null;
        ByteArrayOutputStream bos =new ByteArrayOutputStream();
        try {
            ObjectOutputStream out =new ObjectOutputStream(bos);
            //将List转换成数组
            T[] obj = create(type,t.size());
            t.toArray(obj);
            //执行序列化存储
            out.writeObject(obj);
            out.flush();
            bytes = bos.toByteArray ();
            out.close();
            bos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public List<T> unSerialize(byte[] bytes) {
        ObjectInputStream ois =null;
        List<T> list =null;
        try {
            ByteArrayInputStream bis =new ByteArrayInputStream (bytes);
            ois =new ObjectInputStream(bis);
            T[] obj = (T[])ois.readObject();
            //将数组转换成List
            list = Arrays.asList(obj);
            ois.close();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
