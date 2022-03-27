package cz.upce.testing;

import cz.upce.testing.shared.GenericResponse;
import cz.upce.testing.user.UserRepository;
import cz.upce.testing.user.UserTestApp;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.DateUtil.now;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class TestingApplicationTests {

    public static final String API_1_0_USERS = "/api/1.0/users";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void cleanup(){
        userRepository.deleteAll();
    }

	@Test
	void postUser_whenUserIsValid_receiveOk() {
        UserTestApp user = getUserTestApp();

        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void postUser_whenUserIsValid_userSavedToDatabase() {
        UserTestApp user = getUserTestApp();

        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    void postUser_whenUserIsValid_userSavedMessage() {
        UserTestApp user = getUserTestApp();

        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(response.getBody().getMessage()).isNotNull();
    }

    @Test
    void postUser_whenUserIsValid_passwordIsHashed() {
        UserTestApp user = getUserTestApp();
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);

        List<UserTestApp> users = userRepository.findAll();
        UserTestApp inDB = users.get(0);
        assertThat(inDB.getPassword()).isNotEqualToIgnoringCase(user.getPassword());
    }

    @Test
    void postUser_whenUsernameIsEmpty_badRequest() {
        UserTestApp user = getUserTestApp();
        user.setUsername(null);
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenEmailIsEmpty_badRequest() {
        UserTestApp user = getUserTestApp();
        user.setEmail(null);
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenEmailIsNotEmail_badRequest() {
        UserTestApp user = getUserTestApp();
        user.setEmail("notvalidmail.cz");
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenBirthDateIsNull_badRequest() {
        UserTestApp user = getUserTestApp();
        user.setBirthDate(null);
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenBirthDateIsNotPastOrPresent_badRequest() throws ParseException {
        UserTestApp user = getUserTestApp();
        user.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2199"));
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenPasswordWithoutUpper_badRequest() {
        UserTestApp user = getUserTestApp();
        user.setPassword("asfaosi87984=");
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenPasswordWithoutNumber_badRequest() {
        UserTestApp user = getUserTestApp();
        user.setPassword("asiofhJHAoi=");
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenPasswordWithoutSpecial_badRequest() {
        UserTestApp user = getUserTestApp();
        user.setPassword("asifnoUOIH79");
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    private UserTestApp getUserTestApp() {
        UserTestApp user = new UserTestApp();
        user.setUsername("test-user");
        user.setDisplayName("test-user");
        user.setPassword("tests987%%AAA");
        user.setEmail("nik@seznam.cz");
        user.setBirthDate(now());
        return user;
    }
}
