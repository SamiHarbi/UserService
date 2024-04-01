package rosol.userservice.service.impl.interservicecomm;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;
import rosol.userservice.config.SystemConfiguration;
import rosol.userservice.model.AppRole;
import rosol.userservice.util.JsonConverterController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * Class to communicate the User Service with the Roles Service. The Roles Service must be available before communicating with it.
 * The requests coming from the User Service to the Roles Service are queries about existing Roles. To send Commands to the
 * Roles Service to modify Roles attributes must be done from the Roles Service user interface. This communication is not
 * oriented to perform CRUD operations in the Roles Service coming from the User Service. Only queries are available.
 */
@Component
public class RolesService {

    /**
     * Function to get the id of a specific role according o its name
     *
     * @param role role name
     * @return role id
     */
    public long getRoleIdByName(String role) throws IOException {
        return processServiceResponse(requestToRolesService(Optional.ofNullable(role), "/roleByName/"), "/roleByName/");
    }

    /**
     * Function to get all existing roles from the Roles Service to match them with the role ids coming from the Users table
     * @return list of Roles from Roles Service
     */
    public List<AppRole> getAllRoles() throws IOException{
        return processServiceResponse(requestToRolesService(null, "/roles"), "/roles");
    }

    /**
     * Function to send a request to get a role according to its name to the Roles Service
     * @param role role name which id is being requested. Optional value
     * @param requestType request type coming from the Client url request
     * @return depending on the request type, returns the id of a specific role, or a list with all possible roles.
     * @throws IOException
     */
    private HttpURLConnection requestToRolesService(Optional<String> role, String requestType) throws IOException {
        URL url;
        switch (requestType){
            case "/roleByName/":{
                url = new URL("http://" + SystemConfiguration.propertiesFile().getProperty("roleServiceIP") + ":" +
                        SystemConfiguration.propertiesFile().getProperty("roleServicePort") + requestType + role.get());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }

                return conn;
            }
            case "/roles":{
                url = new URL("http://" + SystemConfiguration.propertiesFile().getProperty("roleServiceIP") + ":" +
                        SystemConfiguration.propertiesFile().getProperty("roleServicePort") + requestType);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }

                return conn;
            }
        }
        return null;
    }

    /**
     * Function to process the response coming from the backend service after the request
     * @param conn connection that will be used to process the response coming from the backend service
     * @param requestType request type coming from the Client url request
     * @return role id (long) depending on the role name, or a List<AppRole>, depending on the requestType parameter
     * @param <T> the returned <T> T class will be different depending on the requestType parameter
     */
    private <T> T processServiceResponse(HttpURLConnection conn, String requestType) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JsonConverterController converterController = new JsonConverterController();
            switch (requestType){
                case "/roleByName/":{
                    TypeReference<AppRole> typeReference = new TypeReference<AppRole>() {
                    };
                    AppRole role = converterController.getMapper().readValue(response.toString(), typeReference);
                    if (role != null) return (T) role.getId();
                    break;
                }
                case "/roles":{
                    TypeReference<List<AppRole>> listTypeReference = new TypeReference<List<AppRole>>() {
                    };
                    List<AppRole> roles = converterController.getMapper().readValue(response.toString(), listTypeReference);
                    return (T) roles;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
