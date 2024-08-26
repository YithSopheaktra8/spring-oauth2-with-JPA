package co.istad.springoauth.security;


import co.istad.springoauth.domain.Authority;
import co.istad.springoauth.domain.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Set;

@Slf4j
public class CustomUserDetailsDeserializer extends JsonDeserializer<CustomUserDetails> {

    private static final TypeReference<Set<Authority>> SIMPLE_GRANTED_AUTHORITY_LIST = new TypeReference<>() {
    };

    @Override
    public CustomUserDetails deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode jsonNode = mapper.readTree(p);

        JsonNode userJsonNode = readJsonNode(jsonNode, "user");

        Set<Authority> userAuthorities = mapper.convertValue(
                jsonNode.get("userAuthorities"),
                SIMPLE_GRANTED_AUTHORITY_LIST
        );

        log.info("Authorities: {}", userAuthorities);

        Long id = readJsonNode(userJsonNode, "id").asLong();
        String uuid = readJsonNode(userJsonNode, "uuid").asText();
        String username = readJsonNode(userJsonNode, "username").asText();
        String email = readJsonNode(userJsonNode, "email").asText();
        String password = readJsonNode(userJsonNode, "password").asText();

        User user = new User();
        user.setId(id);
        user.setUuid(uuid);
        user.setUsername(username);
        user.setAuthorities(userAuthorities);
        user.setEmail(email);
        user.setPassword(password);

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);

        return customUserDetails;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

}