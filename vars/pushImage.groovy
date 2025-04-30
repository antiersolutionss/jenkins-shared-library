def call(String repoUrl, String nextTag, String registry) {
    echo "Pushing Docker image: ${repoUrl}:${nextTag}"
    echo "Using Docker registry: ${registry}"

    sh """
        sudo docker push ${repoUrl}:${nextTag}
        sudo docker logout ${registry}
    """
}
