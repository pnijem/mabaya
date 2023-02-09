package com.mabaya.advesrtise.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.util.Set;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Converter(autoApply = true)
@Slf4j
@AllArgsConstructor
public class StringSetToJsonConverter implements AttributeConverter<Set<String>, String> {

  private final ObjectMapper objectMapper = JsonMapper.builder()
      .enable(MapperFeature.DEFAULT_VIEW_INCLUSION)
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).build();


  @Override
  public String convertToDatabaseColumn(Set<String> attribute) {
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (JsonProcessingException ex) {
      return null;
      // or throw an error
    }
  }

  @Override
  public Set<String> convertToEntityAttribute(String dbData) {
    try {
      if (dbData == null) {
        return null;
      }
      return objectMapper.readValue(dbData, Set.class);
    } catch (IOException ex) {
      log.error("Unexpected IOEx decoding json from database: " + dbData);
      return null;
    }
  }
}
