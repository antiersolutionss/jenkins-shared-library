def call(String repoUrl, String nextTag, String dockerRepoUrl) {
    sh """
        sudo docker push ${repoUrl}:${nextTag}
        sudo docker logout ${dockerRepoUrl}
    """
}
