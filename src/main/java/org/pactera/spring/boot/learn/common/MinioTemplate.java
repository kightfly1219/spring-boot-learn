package org.pactera.spring.boot.learn.common;

import io.minio.*;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;


@Component
public class MinioTemplate {

    private final MinioClient minioClient;

    public MinioTemplate(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String putObject(InputStream inputStream, String contentType) throws Exception {

        // Make 'pactera' bucket if not exist.
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("pactera").build());
        if (!found) {
            // Make a new bucket called 'pactera'.
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("pactera").build());
        } else {
            System.out.println("Bucket 'pactera' already exists.");
        }

        ObjectWriteResponse pacteraResponse = minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket("pactera")
                        .object(UUID.randomUUID().toString())
                        .contentType(contentType)
                        .stream(inputStream, -1, 6 * 1024 * 1024)
                        .build());

        return "http://127.0.0.1:9000/pactera/" + pacteraResponse.object();

    }

    /**
     * 删除对象
     *
     */
    public void delObject(String objectName) throws Exception {
        // Remove object.
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket("pactera")
                        .object(objectName).build());

    }


}
