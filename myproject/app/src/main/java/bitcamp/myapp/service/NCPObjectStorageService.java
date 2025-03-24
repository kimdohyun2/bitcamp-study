package bitcamp.myapp.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;

@Service
public class NCPObjectStorageService implements StorageService {

  @Value("${ncp.end-point}")
  private String endPoint;

  @Value("${ncp.end-name}")
  private String regionName;

  @Value("${ncp.access-key}")
  private String accessKey;

  @Value("${ncp.secret-key}")
  private String secretKey;

  @Value("${ncp.bucket-name}")
  private String bucketName;

  final AmazonS3 s3;

  public NCPObjectStorageService() {
    System.out.println("=====================> "+ endPoint);

    s3 = AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
            .build();
  }

  public void upload(String filePath, InputStream fileIn) {

    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentType("application/x-directory");

    PutObjectRequest putObjectRequest = new PutObjectRequest(
            bucketName, // 버킷 이름
            filePath, // 업로드 파일의 이름 및 디렉토리 경로
            fileIn, // 업로드 할 파일의 InputStream
            objectMetadata // 업로드에 필요한 부가 정보
    );

    try {
      s3.putObject(putObjectRequest);

    } catch (Exception e) {
      throw new StorageServiceException(e);
    }
  }

  @Override
  public void download(String filePath, OutputStream fileOut) {
    try {
      S3Object s3Object = s3.getObject(bucketName, filePath);
      S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();


      byte[] bytesArray = new byte[4096];
      int bytesRead = -1;
      while ((bytesRead = s3ObjectInputStream.read(bytesArray)) != -1) {
        fileOut.write(bytesArray, 0, bytesRead);
      }

      s3ObjectInputStream.close();
      fileOut.close();

    } catch (Exception e) {
      throw new StorageServiceException(e);
    }
  }

  @Override
  public void delete(String filePath) {
    try {
      s3.deleteObject(bucketName, filePath);
    } catch (Exception e) {
      throw new StorageServiceException(e);
    }
  }
}
