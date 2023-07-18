package com.example.recipe2.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@Service
public class S3Upload {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public S3Upload(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    // s3 -> 서버
    public InputStream downloadFile(String key) {
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket, key));
        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
        return objectInputStream;
    }


    //서버 -> s3 업로드
    public String upload(MultipartFile multipartFile) throws IOException {
        //S3에 저장되는 파일의 이름이 중복되지 않기 위해서 UUID로 생성한 랜덤 값과 파일 이름을 연결하여 S3에 업로드
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        //그리고 Spring Server에서 S3로 파일을 업로드해야 하는데,
        // 이 때 파일의 사이즈를 ContentLength로 S3에 알려주기 위해서 ObjectMetadata를 사용합니다.
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        //그리고 이제 S3 API 메소드인 putObject를 이용하여 파일 Stream을 열어서 S3에 파일을 업로드 합니다.
        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

        //그리고 getUrl 메소드를 통해서 S3에 업로드된 사진 URL을 가져오는 방식입니다.
        return amazonS3.getUrl(bucket, s3FileName).toString();
    }
}
