package fr.polyetch.examen.service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class S3Service {

    private final MinioClient client;

    private final Environment environment;

    public S3Service(Environment environment) {
        this.environment = environment;
        this.client = MinioClient
                .builder()
                .endpoint(environment.getProperty("s3.hostname"))
                .credentials(environment.getProperty("s3.accesskey"), environment.getProperty("s3.secretkey"))
                .build();
    }

    public String getDownloadUrl(final String objectId) {
        try {
            return this.client.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs
                            .builder()
                            .method(Method.GET)
                            .object(objectId)
                            .bucket(environment.getProperty("s3.buckets.examillustrations"))
                            .build()
            );
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException |
                 ServerException e) {
            throw new RuntimeException(e);
        }
    }

    public String putDownloadUrl(final String objectId) {
        try {
            return this.client.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs
                            .builder()
                            .method(Method.PUT)
                            .object(objectId)
                            .bucket(environment.getProperty("s3.buckets.examillustrations"))
                            .build()
            );
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException |
                 ServerException e) {
            throw new RuntimeException(e);
        }
    }

}
