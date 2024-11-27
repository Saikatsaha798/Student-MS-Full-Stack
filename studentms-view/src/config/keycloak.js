import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
    url: "http://localhost:9090",
    realm: "student-ms",
    clientId: "res-server",
    pkceMethod: "S256"
});

export default keycloak