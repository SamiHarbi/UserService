package rosol.userservice.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import rosol.userservice.model.AppRole;

import java.io.IOException;

/**
 * Controller class to manage the serialization and deserialization of AppRole objects using ObjectMapper
 */
public class JsonConverterController {

    private final ObjectMapper mapper;

    public JsonConverterController() {
        this.mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new RoleObjectSerializer());
        module.addDeserializer(AppRole.class, new RoleObjectDeserializer());
        mapper.registerModule(module);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

}

/**
 * Serializer class for AppUser objects
 */
class RoleObjectSerializer extends StdSerializer<AppRole> {

    public RoleObjectSerializer() {
        this(AppRole.class);
    }

    protected RoleObjectSerializer(Class<AppRole> t) {
        super(t);
    }

    /**
     * Serializer function to map a Role object to a JSON String.
     * @param appRole Role object
     * @param jsonGenerator json generator by default
     * @param serializerProvider serializer provider by default
     * @throws IOException
     */
    @Override
    public void serialize(AppRole appRole, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        //serializing AppUser fields
        jsonGenerator.writeStringField("id", appRole.getId().toString());
        jsonGenerator.writeStringField("name", appRole.getName());
        jsonGenerator.writeStringField("description", appRole.getDescription());
        jsonGenerator.writeEndObject();
    }
}

/**
 * Deserializer class for AppUser objects
 */
class RoleObjectDeserializer extends StdDeserializer<AppRole>{

    public RoleObjectDeserializer() {
        this(AppRole.class);
    }

    protected RoleObjectDeserializer(Class<AppRole> t) {
        super(t);
    }

    /**
     * Deserializer function to map a JSON string containing the information of a role to a Role object
     * @param jsonParser json parser by default
     * @param deserializationContext deserialization context by default
     * @return Role object already mapped
     * @throws IOException
     */
    @Override
    public AppRole deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        AppRole role = new AppRole();

        //deserializing AppUser fields
        role.setId(Long.parseLong(node.get("id").asText()));
        role.setName(node.get("name").asText());
        role.setDescription(node.get("description").asText());
        return role;
    }
}
