def call() {
    sh '''
        sudo docker login -u "la-north-2@${USERNAME}" -p "${PASSWORD}" ${REGISTRY}
        sudo docker push ${REGISTRY}/${REPO}:${BUILD_ID}
        sudo docker logout ${REGISTRY}
    '''
}

