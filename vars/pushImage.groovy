def call() {
    sh '''
        sudo docker push ${REPO_URL}:${env.NEXT_TAG}
        sudo docker logout ${REGISTRY}
    '''
}

