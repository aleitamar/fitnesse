package fitnesse.slim.converters;

import fitnesse.slim.Converter;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Date;

import fitnesse.slim.EnumConverter;

import fitnesse.slim.test.AnEnum;

public class ConverterRegistryTest {

  @Test
  public void checkInitialisationSuccessful() {
    Converter converter = ConverterRegistry.getConverterForClass(Date.class);
    Date converted = (Date) converter.fromString("27-FEB-2012");
    assertNotNull(converted);
  }

  @Test
  public void useConverterFromCustomizing() {
    ConverterRegistry.addConverter(CustomClass.class, new CustomConverter());

    Converter converter = ConverterRegistry.getConverterForClass(CustomClass.class);
    assertEquals("customConverter", converter.toString(new CustomClass()));
  }
  
  @Test
  public void defaultEnumConversion() {
    assertTrue(ConverterRegistry.getConverterForClass(AnEnum.class) instanceof EnumConverter);
  }

  static class CustomClass {

  }

  static class CustomConverter implements Converter {

    public String toString(Object o) {
      return "customConverter";
    }

    public Object fromString(String arg) {
      return "customConverter";
    }
  }
}
