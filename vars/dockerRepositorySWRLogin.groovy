def dockerRepoLogin(String username, String password, String registry) {
    sh """
        docker login -u ${username} -p ${password} ${registry}
    """
}
