package com.projetCertif;

import com.projetCertif.dao.entity.Channel;
import com.projetCertif.dao.entity.User;
import com.projetCertif.dao.repository.ChannelRepository;
import com.projetCertif.dao.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DbAccessTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChannelRepository channelRepository;

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

        User retrievedUserWithName = userRepository.findByFirstname("bobby");
        Optional<User> retrievedUserById = userRepository.findById(2L);
        List<User> retrievedUsers = userRepository.findAll();

        assertThat(retrievedUserWithName.getFirstname()).isEqualTo("bobby");
        assertThat(retrievedUserById.get().getId(message.get().getUser().getId())).isEqualTo(2L);
        assertThat(retrievedUsers.size()).isEqualTo(3);
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

        Channel retrievedChannelWithName = channelRepository.findByName("general");
        Optional<Channel> retrievedChannelById = channelRepository.findById(2L);
        List<Channel> retrievedChannels = channelRepository.findAll();

        assertThat(retrievedChannelWithName.getName()).isEqualTo("general");
        assertThat(retrievedChannelById.get().getId()).isEqualTo(2L);
        assertThat(retrievedChannels.size()).isEqualTo(3);
    }
}
