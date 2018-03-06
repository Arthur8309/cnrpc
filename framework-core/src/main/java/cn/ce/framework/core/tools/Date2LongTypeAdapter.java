package cn.ce.framework.core.tools;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Date;

public class Date2LongTypeAdapter
  implements JsonSerializer<Date>, JsonDeserializer<Date>
{
  public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
  {
    return new Date(json.getAsJsonPrimitive().getAsLong());
  }

  public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context)
  {
    return new JsonPrimitive(Long.valueOf(src.getTime()));
  }
}