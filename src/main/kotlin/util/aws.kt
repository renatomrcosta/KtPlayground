package util

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSClientBuilder

private val endpointConfiguration =
    AwsClientBuilder.EndpointConfiguration(
        "sqs.eu-central-1.amazonaws.com",
        "eu-central-1"
    )

val amazonClient = AmazonSQSClientBuilder.standard().withEndpointConfiguration(endpointConfiguration)
    .withCredentials(
        AWSStaticCredentialsProvider(
            BasicAWSCredentials("", "")
        )
    ).build()
