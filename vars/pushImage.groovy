def call() {
    sh """
        sudo docker push ${env.REPO_URL}:${env.NEXT_TAG}
        sudo docker logout ${env.REGISTRY}
    """
}
