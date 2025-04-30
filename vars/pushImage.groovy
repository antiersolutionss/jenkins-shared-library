def call() {
        sh '''
            docker push ${env.REPO_URL}:${env.NEXT_TAG}
        '''
}
