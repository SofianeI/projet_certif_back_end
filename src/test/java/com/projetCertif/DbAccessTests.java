package com.projetCertif;

import com.projetCertif.config.PasswordEncoderUtil;
import com.projetCertif.dao.entity.Channel;
import com.projetCertif.dao.entity.User;
import com.projetCertif.dao.repository.ChannelRepository;
import com.projetCertif.dao.repository.UserRepository;
import com.projetCertif.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DbAccessTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserService userService;

    @Test
    void shouldSaveAndRetrieveUser(){
        /*User user1 = new User();
        user1.setFirstname("bobby");
        user1.setLastname("lapointe");

        User user2 = new User();
        user2.setFirstname("pennywise");
        user2.setLastname("it");

        User user3 = new User();
        user3.setFirstname("belphegor");
        user3.setLastname("belphegor");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);*/

        /*User retrievedUserWithName = userRepository.findByFirstname("bobby");
        Optional<User> retrievedUserById = userRepository.findById(2L);
        List<User> retrievedUsers = userRepository.findAll();

        assertThat(retrievedUserWithName.getFirstname()).isEqualTo("bobby");
        assertThat(retrievedUserById.get().getId()).isEqualTo(2L);
        assertThat(retrievedUsers.size()).isEqualTo(3);*/

        String username = "monPseudo";
        String firstname = "Bobby";
        String lastname = "Lapointe";
        String rawPassword = "boblapointe";

        User registeredUser = userService.registerUser(username, firstname, lastname, PasswordEncoderUtil.encodePassword(rawPassword));
        Optional<User> isAuthenticated = userService.authenticate(username, rawPassword);

        // Then
        assertTrue(registeredUser.getId() > 0); // User is saved with a valid ID
        assertTrue(isAuthenticated.isPresent()); // User can be authenticated

        // Additional Assertions (Optional)
        List<User> retrievedUsers = userRepository.findAll();
        assertThat(retrievedUsers).hasSizeGreaterThan(0); // Ensure users are retrieved
        assertThat(registeredUser.getFirstname()).isEqualTo(firstname);
    }

    @Test
    void shouldSaveAndRetrieveChannels(){
        /*Channel channel1 = new Channel();
        channel1.setName("general");

        Channel channel2 = new Channel();
        channel2.setName("random");

        Channel channel3 = new Channel();
        channel3.setName("programming");

        channelRepository.save(channel1);
        channelRepository.save(channel2);
        channelRepository.save(channel3);*/

        // Given
        String channelName = "general";
        /*Channel channel1 = new Channel();
        channel1.setName(channelName);
        channelRepository.save(channel1);*/

        // When
        Channel retrievedChannelWithName = channelRepository.findByName(channelName);
        Optional<Channel> retrievedChannelById = channelRepository.findById(1L);
        List<Channel> retrievedChannels = channelRepository.findAll();

        // Then
        assertThat(retrievedChannelWithName.getName()).isEqualTo(channelName);
        assertTrue(retrievedChannelById.isPresent()); // Channel with ID 1 exists
        assertThat(retrievedChannels).hasSizeGreaterThan(0); // Ensure channels are retrieved
    }
}
