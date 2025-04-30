def call(String repoUrl, String nextTag, String registry) {
    sh """
        sudo docker push ${repoUrl}:${nextTag}
        sudo docker logout ${registry}
    """
}
