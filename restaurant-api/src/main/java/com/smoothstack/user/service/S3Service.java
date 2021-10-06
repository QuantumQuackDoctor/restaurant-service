package com.smoothstack.user.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Service
public class S3Service {

    @Value("${csv.bucket}")
    private String bucketName;

    private static final String[] expectedCsvColumns = {
            "address",
            "average_rating",
            "average_time",
            "geolocation",
            "mon",
            "tue",
            "wed",
            "thu",
            "fri",
            "sat",
            "sun",
            "name",
            "price_rating",
            "search_primary",
            "search_secondary"};

    private final AmazonS3 amazonS3;

    public S3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void uploadCSV(MultipartFile file, String fileName) throws IllegalArgumentException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot upload empty file");
        }
        if (!Objects.requireNonNull(file.getContentType()).equalsIgnoreCase("text/csv")) {
            throw new IllegalArgumentException("Only csv files are allowed");
        }
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))
        ) {
            //check that first line has columns
            String[] tokens = br.readLine().split(",");
            Arrays.sort(expectedCsvColumns);
            Arrays.sort(tokens);
            if (!Arrays.equals(expectedCsvColumns, tokens)) {
                throw new IllegalArgumentException("Columns are incorrect should contain " + Arrays.toString(expectedCsvColumns));
            }
            if (br.readLine() == null) {
                throw new IllegalArgumentException("File must contain at least one data column");
            }

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3.putObject(new PutObjectRequest(bucketName, UUID.randomUUID() + "-" + fileName, file.getInputStream(), metadata));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
