package com.lmk.reflect;

/**
 * Created by lmk on 2017/6/8.
 */
import com.google.common.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * 这个例子是为了演示使用 google guava 获取泛型类型
 * @param <T>
 */

public class GenericClass<T> {
    private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) { };
    private final Type type = typeToken.getType(); // or getRawType() to return Class<? super T>

    public Type getType() {
        return type;
    }

    public static void main(String[] args) {
        GenericClass<String> example = new GenericClass<String>() { };
        System.out.println(example.getType()); // => class java.lang.String
    }
}