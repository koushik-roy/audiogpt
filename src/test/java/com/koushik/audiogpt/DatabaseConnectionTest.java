package com.koushik.audiogpt;

import com.koushik.audiogpt.repository.SpeakerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private SpeakerRepository repository;

    @Test
    void shouldConnectToDatabase() {

        long count = repository.count();

        assertThat(count)
                .isGreaterThanOrEqualTo(0);
    }
}
