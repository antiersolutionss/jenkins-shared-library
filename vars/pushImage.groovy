def call(String REPO_URL, String NEXT_TAG) {
    sh """
        docker push ${REPO_URL}:${NEXT_TAG}
    """
}
