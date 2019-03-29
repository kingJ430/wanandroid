package com.zjf.network;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.zjf.network.models.ResultData;

import java.io.IOException;


public class ItemTypeAdapterFactory implements TypeAdapterFactory {

    public static final String CODE = "errorCode";
    public static final String RESULTS = "data";
    public static final String MESSAGE = "errorMsg";


    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = elementAdapter.read(in);
                if(type.getRawType() != ResultData.class) {

                    if (jsonElement.isJsonObject()) {
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        if (jsonObject.has(RESULTS) && jsonObject.has(CODE) && (jsonObject.has(MESSAGE))) {
                            if (jsonObject.has(CODE)) {
                                GateWayErrorCenter.throwExceptionWhenNotSuccess(jsonObject);

                                if (jsonObject.has(RESULTS)) {
                                    jsonElement = jsonObject.get(RESULTS);
                                }
                            }
                        }
                    }
                }
                return delegate.fromJsonTree(jsonElement);
            }

        }.nullSafe();
    }

}
