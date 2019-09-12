package com.lgf.mywanandroid.lgf.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
//        String response= value.string();//把responsebody转为string
//        // 这里只是为了检测code是否==0,所以只解析HttpStatus中的字段,因为只要code和message就可以了
//        Response httpResponse=gson.fromJson(response,Response.class);
//        if(httpResponse.isCodeInvalid()){
//            value.close();
//            try {
//                //抛出一个RuntimeException, 这里抛出的异常会到Subscriber的onError()方法中统一处理
//                throw  new ApiException(httpResponse.getCode(),httpResponse.getMsg());
//            } catch (ApiException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        MediaType contentType = value.contentType();
//        Charset charset =contentType!=null? contentType.charset(UTF_8) : UTF_8;
//        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
//        Reader reader = new InputStreamReader(inputStream, charset);

        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
