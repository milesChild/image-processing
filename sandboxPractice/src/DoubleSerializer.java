import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;

public class DoubleSerializer implements JsonSerializer<Double> {

  @Override
  public JsonElement serialize(Double aDouble, Type type,
                               JsonSerializationContext jsonSerializationContext) {

    double d = aDouble;

    // if the value is 0.0, then make it "N/A"

    if (aDouble == 0.0) {
      return new JsonPrimitive("N/A");
    }

    // if whole number, then return as an integer
    if ((d - (int) d) == 0) {
      return new JsonPrimitive((int) d);
    } else {
      return new JsonPrimitive(aDouble);
    }

  }

}
