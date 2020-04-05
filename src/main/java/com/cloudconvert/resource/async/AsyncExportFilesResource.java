package com.cloudconvert.resource.async;

import com.cloudconvert.client.api.key.ApiKeyProvider;
import com.cloudconvert.client.api.url.ApiUrlProvider;
import com.cloudconvert.client.mapper.ObjectMapperProvider;
import com.cloudconvert.dto.request.AzureBlobExportRequest;
import com.cloudconvert.dto.request.GoogleCloudStorageExportRequest;
import com.cloudconvert.dto.request.OpenStackExportRequest;
import com.cloudconvert.dto.request.S3ExportRequest;
import com.cloudconvert.dto.request.SftpExportRequest;
import com.cloudconvert.dto.request.UrlExportRequest;
import com.cloudconvert.dto.response.TaskResponseData;
import com.cloudconvert.dto.result.AsyncResult;
import com.cloudconvert.executor.AsyncRequestExecutor;
import com.cloudconvert.resource.AbstractExportFilesResource;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class AsyncExportFilesResource extends AbstractExportFilesResource<AsyncResult<TaskResponseData>> {

    private final AsyncRequestExecutor asyncRequestExecutor;

    public AsyncExportFilesResource(
        final ApiUrlProvider apiUrlProvider, final ApiKeyProvider apiKeyProvider,
        final ObjectMapperProvider objectMapperProvider, final AsyncRequestExecutor asyncRequestExecutor
    ) {
        super(apiUrlProvider, apiKeyProvider, objectMapperProvider);

        this.asyncRequestExecutor = asyncRequestExecutor;
    }

    @Override
    public AsyncResult<TaskResponseData> url(
        @NotNull final UrlExportRequest urlExportRequest
    ) throws IOException, URISyntaxException {
        final URI uri = getUri(ImmutableList.of(PATH_SEGMENT_EXPORT, PATH_SEGMENT_URL));
        final HttpEntity httpEntity = getHttpEntity(urlExportRequest);
        final HttpUriRequest httpUriRequest = getHttpUriRequest(HttpPost.class, uri, httpEntity);

        return asyncRequestExecutor.execute(httpUriRequest, TASK_RESPONSE_DATA_TYPE_REFERENCE);
    }

    @Override
    public AsyncResult<TaskResponseData> s3(
        @NotNull final S3ExportRequest s3ExportRequest
    ) throws IOException, URISyntaxException {
        final URI uri = getUri(ImmutableList.of(PATH_SEGMENT_EXPORT, PATH_SEGMENT_S3));
        final HttpEntity httpEntity = getHttpEntity(s3ExportRequest);
        final HttpUriRequest httpUriRequest = getHttpUriRequest(HttpPost.class, uri, httpEntity);

        return asyncRequestExecutor.execute(httpUriRequest, TASK_RESPONSE_DATA_TYPE_REFERENCE);
    }

    @Override
    public AsyncResult<TaskResponseData> azureBlob(
        @NotNull final AzureBlobExportRequest azureBlobExportRequest
    ) throws IOException, URISyntaxException {
        final URI uri = getUri(ImmutableList.<String>builder().add(PATH_SEGMENT_EXPORT).addAll(PATH_SEGMENTS_AZURE_BLOB).build());
        final HttpEntity httpEntity = getHttpEntity(azureBlobExportRequest);
        final HttpUriRequest httpUriRequest = getHttpUriRequest(HttpPost.class, uri, httpEntity);

        return asyncRequestExecutor.execute(httpUriRequest, TASK_RESPONSE_DATA_TYPE_REFERENCE);
    }

    @Override
    public AsyncResult<TaskResponseData> googleCloudStorage(
        @NotNull final GoogleCloudStorageExportRequest googleCloudStorageExportRequest
    ) throws IOException, URISyntaxException {
        final URI uri = getUri(ImmutableList.of(PATH_SEGMENT_EXPORT, PATH_SEGMENT_GOOGLE_CLOUD_STORAGE));
        final HttpEntity httpEntity = getHttpEntity(googleCloudStorageExportRequest);
        final HttpUriRequest httpUriRequest = getHttpUriRequest(HttpPost.class, uri, httpEntity);

        return asyncRequestExecutor.execute(httpUriRequest, TASK_RESPONSE_DATA_TYPE_REFERENCE);
    }

    @Override
    public AsyncResult<TaskResponseData> openStack(
        @NotNull final OpenStackExportRequest openStackExportRequest
    ) throws IOException, URISyntaxException {
        final URI uri = getUri(ImmutableList.of(PATH_SEGMENT_EXPORT, PATH_SEGMENT_OPENSTACK));
        final HttpEntity httpEntity = getHttpEntity(openStackExportRequest);
        final HttpUriRequest httpUriRequest = getHttpUriRequest(HttpPost.class, uri, httpEntity);

        return asyncRequestExecutor.execute(httpUriRequest, TASK_RESPONSE_DATA_TYPE_REFERENCE);
    }

    @Override
    public AsyncResult<TaskResponseData> sftp(
        @NotNull final SftpExportRequest sftpExportRequest
    ) throws IOException, URISyntaxException {
        final URI uri = getUri(ImmutableList.of(PATH_SEGMENT_EXPORT, PATH_SEGMENT_SFTP));
        final HttpEntity httpEntity = getHttpEntity(sftpExportRequest);
        final HttpUriRequest httpUriRequest = getHttpUriRequest(HttpPost.class, uri, httpEntity);

        return asyncRequestExecutor.execute(httpUriRequest, TASK_RESPONSE_DATA_TYPE_REFERENCE);
    }

    @Override
    public void close() throws IOException {
        asyncRequestExecutor.close();
    }
}