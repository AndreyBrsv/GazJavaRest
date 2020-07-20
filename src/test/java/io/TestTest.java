package io;

import io.dao.DocumentRepository;
import io.entities.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile(value = "test")
public class TestTest {
    @Autowired
    private DocumentRepository documentRepository;

    @Test
    public void method() {
        Document document = Document.builder()
                .documentNumber(111L)
                .companyName("test")
                .openDate(Timestamp.from(Instant.now()))
                .inn("51512")
                .kpp("1515356")
                .build();

        Document result = documentRepository.create(document);
        Assert.assertEquals("1515356", result.getKpp());
    }
}
