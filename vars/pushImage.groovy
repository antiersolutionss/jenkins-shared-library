def call() {
    withCredentials([usernamePassword(credentialsId: 'huaweicloud-SWR-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh '''
            sudo docker push ${REGISTRY}/${REPO}:{env.NEXT_TAG}
            sudo docker logout ${REGISTRY}
        '''
    }
}
