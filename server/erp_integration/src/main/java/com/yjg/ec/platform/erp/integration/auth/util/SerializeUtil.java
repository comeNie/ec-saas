package com.yjg.ec.platform.erp.integration.auth.util;

import java.io.*;

public class SerializeUtil {

    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
                if (bais != null)
                    bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
