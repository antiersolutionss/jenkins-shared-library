def call(string REPO_URL , string NEXT_TAG) {
        sh '''
            docker push ${env.REPO_URL}:${env.NEXT_TAG}
        '''
}
