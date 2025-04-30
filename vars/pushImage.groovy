def call() {
    sh '''
        sudo docker push ${REGISTRY}/${REPO}:${BUILD_ID}
        sudo docker logout ${REGISTRY}
    '''
}

