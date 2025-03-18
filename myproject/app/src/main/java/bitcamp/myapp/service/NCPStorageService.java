package bitcamp.myapp.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.InputStream;

public class NCPStorageService implements StorageService {

    final String endPoint;
    final String regionName;
    final String accessKey;
    final String secretKey ;
    final String bucketName;

    final AmazonS3 s3;

    public NCPStorageService() {
        s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

    public void upload(String filePath, InputStream fileIn) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("application/x-directory");
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucketName,
                filePath, //업로드 파일의 이름 및 디렉토리 경로
                fileIn, // 파일inputStream
                objectMetadata // 업로드에 필요한 부가 정보
        );

        try {
            s3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageServiceException(e);
        }
    }
}
