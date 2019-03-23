package by.bsuir.karamach.util;

import by.bsuir.karamach.model.store.FigureList;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public final class BsonWorker {

    private static final Logger logger = LogManager.getLogger(BsonWorker.class);

    @Value("${data.sources.directory}")
    private String DATA_PATH;

    private BsonFactory bsonFactory;
    private ObjectMapper mapper;

    private BsonWorker() {
        bsonFactory = new BsonFactory();
        bsonFactory.enable(BsonGenerator.Feature.ENABLE_STREAMING);

        mapper = new ObjectMapper(bsonFactory);
        mapper.enableDefaultTyping();
    }

    public void serializeToFile(FigureList figures, String fileName) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        mapper.writeValue(outputStream, figures);

        String filePath = DATA_PATH + fileName;
        File file = new File(filePath);

        if (!file.createNewFile()) {
            logger.warn("File is already created");
        }

        outputStream.writeTo(new FileOutputStream(filePath));
    }

    public FigureList deserializeFromFile(String fileName) throws IOException {
        String filePath = DATA_PATH + fileName;

        ByteArrayInputStream bais = new ByteArrayInputStream(Files.readAllBytes(Paths.get(filePath)));

        return mapper.readValue(bais, FigureList.class);
    }
}
